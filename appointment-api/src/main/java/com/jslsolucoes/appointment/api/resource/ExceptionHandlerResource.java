package com.jslsolucoes.appointment.api.resource;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jslsolucoes.appointment.api.usecase.ConflictException;
import com.jslsolucoes.appointment.api.usecase.EntityNotFoundException;

@RestController
@ControllerAdvice
public class ExceptionHandlerResource {

	@ResponseStatus(HttpStatus.CONFLICT)
	@ExceptionHandler({ ConflictException.class })
	public @ResponseBody ConflictResponse conflictResponse(ConflictException conflictException) {
		return new ConflictResponse(conflictException.getMessage());
	}

	@ResponseStatus(HttpStatus.CONFLICT)
	@ExceptionHandler({ DataIntegrityViolationException.class })
	public @ResponseBody ConflictResponse conflictResponse(
			DataIntegrityViolationException dataIntegrityViolationException) {
		return new ConflictResponse(dataIntegrityViolationException.getMessage());
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler({ EntityNotFoundException.class })
	public @ResponseBody NotFoundResponse notFoundResponse(EntityNotFoundException entityNotFoundException) {
		return new NotFoundResponse(entityNotFoundException.getMessage());
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler({ HttpMessageNotReadableException.class })
	public @ResponseBody BadRequestResponse notFoundResponse(
			HttpMessageNotReadableException httpMessageNotReadableException) {
		return new BadRequestResponse(List.of("Body cannot be null"));
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler({ MethodArgumentNotValidException.class })
	public @ResponseBody BadRequestResponse notFoundResponse(
			MethodArgumentNotValidException methodArgumentNotValidException) {
		List<String> errors = methodArgumentNotValidException.getBindingResult().getFieldErrors().stream()
				.map(fieldError -> "field '" + fieldError.getField() + "' " + fieldError.getDefaultMessage())
				.collect(Collectors.toList());
		return new BadRequestResponse(errors);
	}

	class ConflictResponse {

		private final String message;

		public ConflictResponse(final String message) {
			this.message = message;
		}

		public String message() {
			return message;
		}

	}

	class NotFoundResponse {

		private final String message;

		public NotFoundResponse(final String message) {
			this.message = message;
		}

		public String message() {
			return message;
		}

	}

	class BadRequestResponse {

		private final Collection<String> errors;

		public BadRequestResponse(final Collection<String> errors) {
			this.errors = errors;
		}

		public Collection<String> message() {
			return errors;
		}

	}

}
