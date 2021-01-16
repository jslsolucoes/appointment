package com.jslsolucoes.appointment.api.resource;

import java.time.LocalTime;

import com.jslsolucoes.appointment.api.domain.ScheduleSlotTime;

public class ScheduleSlotTimeTO {

	private final Long idScheduleSlotTime;
	private final LocalTime time;

	public ScheduleSlotTimeTO(final ScheduleSlotTime scheduleSlotTime) {
		this.time = scheduleSlotTime.time();
		this.idScheduleSlotTime = scheduleSlotTime.id();
	}

	public LocalTime time() {
		return time;
	}

	public Long idScheduleSlotTime() {
		return idScheduleSlotTime;
	}

}
