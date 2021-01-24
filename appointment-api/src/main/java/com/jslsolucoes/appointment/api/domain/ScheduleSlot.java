package com.jslsolucoes.appointment.api.domain;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name = "schedule_slot_sq", allocationSize = 1, initialValue = 1, sequenceName = "schedule_slot_sq")
public class ScheduleSlot {

	@Id
	@GeneratedValue(generator = "schedule_slot_sq", strategy = GenerationType.SEQUENCE)
	@Column(nullable = false)
	private Long id;

	@Column(nullable = false)
	private LocalDate date;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "scheduleSlot", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<ScheduleSlotTime> times = new HashSet<>();

	public ScheduleSlot() {

	}

	public ScheduleSlot(LocalDate date) {
		this.date = date;
	}

	public Long id() {
		return id;
	}

	public LocalDate date() {
		return date;
	}

	public Set<ScheduleSlotTime> times() {
		return times;
	}
	
	public Set<ScheduleSlotTime> setTimes(Set<ScheduleSlotTime> times) {
		this.times.retainAll(times);
		this.times.addAll(times);
		return this.times;
	}

}
