package com.jslsolucoes.appointment.api.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name = "appointment_sq", allocationSize = 1, initialValue = 1, sequenceName = "appointment_sq")
public class Appointment {

	@Id
	@GeneratedValue(generator = "appointment_sq", strategy = GenerationType.SEQUENCE)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_customer")
	private Customer customer;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_scheduler_slot_time", unique = true)
	private ScheduleSlotTime scheduleSlotTime;
	
	@Enumerated(EnumType.STRING)
	private Status status;

	public Appointment() {

	}

	public Appointment(Customer customer, ScheduleSlotTime scheduleSlotTime, Status status) {
		this.customer = customer;
		this.scheduleSlotTime = scheduleSlotTime;
		this.status = status;
	}

	public Long id() {
		return id;
	}
	
	public Status status() {
		return status;
	}
	
	public Appointment cancel() {
		this.status = Status.CANCELED;
		return this;
	}

	public Customer customer() {
		return customer;
	}

	public ScheduleSlotTime scheduleSlotTime() {
		return scheduleSlotTime;
	}

	public static class Builder {

		private Customer customer;
		private ScheduleSlotTime scheduleSlotTime;
		private Status status;

		private Builder() {

		}

		public static Builder newBuilder() {
			return new Builder();
		}
		
		public Builder withStatus(Status status) {
			this.status = status;
			return this;
		}

		public Builder withCustomer(Customer customer) {
			this.customer = customer;
			return this;
		}

		public Builder withScheduleSlotTime(ScheduleSlotTime scheduleSlotTime) {
			this.scheduleSlotTime = scheduleSlotTime;
			return this;
		}

		public Appointment build() {
			return new Appointment(customer, scheduleSlotTime, status);
		}
	}

	public LocalDateTime localDateTime() {
		return scheduleSlotTime.localDateTime();
	}
	
	public enum Status {
		ACTIVE,
		CANCELED
	}

	public boolean isCanceled() {
		return status == Status.CANCELED;
	}

}
