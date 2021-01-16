package com.jslsolucoes.appointment.api.usecase;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jslsolucoes.appointment.api.Lists;
import com.jslsolucoes.appointment.api.domain.Appointment;
import com.jslsolucoes.appointment.api.domain.Appointment.Status;
import com.jslsolucoes.appointment.api.domain.Customer;
import com.jslsolucoes.appointment.api.domain.ScheduleSlotTime;
import com.jslsolucoes.appointment.api.repo.AppointmentRepo;

@Service
public class AppointmentUseCase {

	private AppointmentRepo appointmentRepo;
	private CustomerUseCase customerUseCase;
	private ScheduleSlotUseCase scheduleSlotUseCase;

	public AppointmentUseCase() {

	}

	@Autowired
	public AppointmentUseCase(AppointmentRepo appointmentRepo, CustomerUseCase customerUseCase,
			ScheduleSlotUseCase scheduleSlotUseCase) {
		this.appointmentRepo = appointmentRepo;
		this.customerUseCase = customerUseCase;
		this.scheduleSlotUseCase = scheduleSlotUseCase;
	}

	public Appointment cancel(Long id) {
		Appointment appointment = find(id);
		if(appointment.isCanceled()) {
			throw new ConflictException("Appointment {0} already canceled", id) ;
		}
		return appointment.cancel();
	}

	public Appointment createNewOne(String email, Long phone, String name, Long idScheduleSlotTime) {
		Customer customer = customerUseCase.createNewOrGetExistent(name, email, phone);
		ScheduleSlotTime scheduleSlotTime = scheduleSlotUseCase.makeUnavailable(idScheduleSlotTime);
		Appointment appointment = Appointment.Builder.newBuilder().withCustomer(customer)
				.withScheduleSlotTime(scheduleSlotTime).withStatus(Status.ACTIVE).build();
		return appointmentRepo.save(appointment);
	}

	public List<Appointment> appointmentsFor(String email) {
		List<Appointment> appointments = appointmentRepo.appointments(email, Status.ACTIVE, LocalDate.now());
		if (Lists.isEmpty(appointments)) {
			throw new EntityNotFoundException("There is no appointment to {0}", email);
		}
		return appointments;
	}

	public Appointment find(Long id) {
		return appointmentRepo.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("There is no appointment with id {0}", id));
	}

}
