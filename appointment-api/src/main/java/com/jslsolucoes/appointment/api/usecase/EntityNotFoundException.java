package com.jslsolucoes.appointment.api.usecase;

import java.text.MessageFormat;

@SuppressWarnings("serial")
public class EntityNotFoundException extends RuntimeException {

	public EntityNotFoundException() {
		super("Entity not found");
	}

	public EntityNotFoundException(String message, Object... arguments) {
		super(MessageFormat.format(message, arguments));
	}
}
