package com.jslsolucoes.appointment.api.resource;

import java.time.LocalDateTime;

import com.jslsolucoes.appointment.api.domain.Appointment;

public class AppointmentTO {
	private final Long id;
	private final LocalDateTime dateTime;

	public AppointmentTO(final Appointment appointment) {
		this.id = appointment.id();
		this.dateTime = appointment.localDateTime();
	}

	public Long id() {
		return id;
	}

	public LocalDateTime dateTime() {
		return dateTime;
	}

}