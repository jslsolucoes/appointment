package com.jslsolucoes.appointment.api.domain;

import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name = "schedule_slot_time_sq", allocationSize = 1, initialValue = 1, sequenceName = "schedule_slot_time_sq")
public class ScheduleSlotTime {

	@Id
	@GeneratedValue(generator = "schedule_slot_time_sq", strategy = GenerationType.SEQUENCE)
	@Column(nullable = false)
	private Long id;

	@Column(nullable = false)
	private LocalTime time;

	@Column(nullable = false)
	private Boolean available;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_schedule_slot", nullable = false)
	private ScheduleSlot scheduleSlot;

	public ScheduleSlotTime() {

	}

	public ScheduleSlotTime(Long id) {
		this.id = id;
	}

	public ScheduleSlotTime(ScheduleSlot scheduleSlot,LocalTime time,Boolean available) {
		this.time = time;
		this.available = available;
		this.scheduleSlot = scheduleSlot;
	}

	public ScheduleSlotTime makeUnvailable() {
		this.available = false;
		return this;
	}

	public Long id() {
		return id;
	}

	public ScheduleSlot scheduleSlot() {
		return scheduleSlot;
	}

	public LocalTime time() {
		return time;
	}

	public Boolean available() {
		return available;
	}

	public static class Builder {

		private Long id;

		private Builder() {

		}

		public static Builder newBuilder() {
			return new Builder();
		}

		public Builder withId(Long id) {
			this.id = id;
			return this;
		}

		public ScheduleSlotTime build() {
			return new ScheduleSlotTime(id);
		}
	}

	public LocalDateTime localDateTime() {
		return LocalDateTime.of(scheduleSlot.date(), time);
	}

}
