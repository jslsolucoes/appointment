package com.jslsolucoes.appointment.api.repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jslsolucoes.appointment.api.domain.Appointment;
import com.jslsolucoes.appointment.api.domain.Appointment.Status;

@Repository
public interface AppointmentRepo extends CrudRepository<Appointment, Long> {

	@Query("select ap from Appointment ap inner join fetch ap.customer c inner join fetch ap.scheduleSlotTime slt inner join fetch slt.scheduleSlot sl where c.email= :email and ap.status= :status and sl.date >= :date")
	public List<Appointment> appointments(@Param("email") String email,@Param("status") Status status,@Param("date") LocalDate date);

}
