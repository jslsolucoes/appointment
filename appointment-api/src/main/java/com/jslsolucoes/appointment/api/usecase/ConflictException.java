package com.jslsolucoes.appointment.api.usecase;

import java.text.MessageFormat;

@SuppressWarnings("serial")
public class ConflictException extends RuntimeException {

	public ConflictException(String message, Object... arguments) {
		super(MessageFormat.format(message, arguments));
	}

}
