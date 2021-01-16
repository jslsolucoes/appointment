package com.jslsolucoes.appointment.api.resource;

import java.time.LocalDate;

import com.jslsolucoes.appointment.api.domain.ScheduleSlot;

public class SchedulerSlotTO {

	private final Long idSchedule;
	private final LocalDate date;

	public SchedulerSlotTO(final ScheduleSlot scheduleSlot) {
		this.idSchedule = scheduleSlot.id();
		this.date = scheduleSlot.date();
	}

	public Long idSchedule() {
		return idSchedule;
	}

	public LocalDate date() {
		return date;
	}

}