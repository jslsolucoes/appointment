package com.jslsolucoes.appointment.api.resource;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.jslsolucoes.appointment.api.domain.Appointment;
import com.jslsolucoes.appointment.api.usecase.AppointmentUseCase;

@RestController
@RequestMapping("appointments")
public class AppointmentResource {

	private AppointmentUseCase appointmentUseCase;

	public AppointmentResource() {

	}

	@Autowired
	public AppointmentResource(AppointmentUseCase appointmentUseCase) {
		this.appointmentUseCase = appointmentUseCase;
	}

	@DeleteMapping("/{id}")
	@Transactional
	public AppointmentTO cancel(@PathVariable("id") Long id) {
		return new AppointmentTO(appointmentUseCase.cancel(id));
	}

	@GetMapping("/users/{email}")
	public List<AppointmentTO> availables(@PathVariable("email") String email) {
		return appointmentUseCase.appointmentsFor(email).stream().map(AppointmentTO::new).collect(Collectors.toList());
	}

	@GetMapping("/{id}")
	public AppointmentTO retrieve(@PathVariable("id") Long id) {
		return new AppointmentTO(appointmentUseCase.find(id));
	}

	@PostMapping
	@Transactional(timeout = 30000)
	public ResponseEntity<AppointmentTO> createNewAppointment(
			@RequestBody @Valid CreateAppointmentRequest createAppointmentRequest) {
		Appointment appointment = appointmentUseCase.createNewOne(createAppointmentRequest.email(),
				createAppointmentRequest.phone(), createAppointmentRequest.name(),
				createAppointmentRequest.idScheduleSlotTime());
		return Responses.created(new AppointmentTO(appointment), "/{id}", appointment.id());
	}

	static class CreateAppointmentRequest {

		@NotNull
		private final String name;

		@NotNull
		private final String email;

		@NotNull
		private final Long phone;

		@NotNull
		private final Long idScheduleSlotTime;

		@JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
		public CreateAppointmentRequest(@JsonProperty("idScheduleSlotTime") final Long idScheduleSlotTime,
				@JsonProperty("name") final String name, @JsonProperty("email") final String email,
				@JsonProperty("phone") @JsonDeserialize(using = PhoneDeserializer.class) final Long phone) {
			this.idScheduleSlotTime = idScheduleSlotTime;
			this.name = name;
			this.email = email;
			this.phone = phone;
		}

		public Long idScheduleSlotTime() {
			return idScheduleSlotTime;
		}

		public String name() {
			return name;
		}

		public String email() {
			return email;
		}

		public Long phone() {
			return phone;
		}
	}

}
