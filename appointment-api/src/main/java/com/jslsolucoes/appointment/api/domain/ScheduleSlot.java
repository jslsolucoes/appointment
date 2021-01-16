package com.jslsolucoes.appointment.api.domain;

import java.time.LocalDate;
import java.util.Set;

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
	private Long id;

	private LocalDate date;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "scheduleSlot")
	private Set<ScheduleSlotTime> times;

	public Long id() {
		return id;
	}

	public LocalDate date() {
		return date;
	}

	public Set<ScheduleSlotTime> times() {
		return times;
	}

}
