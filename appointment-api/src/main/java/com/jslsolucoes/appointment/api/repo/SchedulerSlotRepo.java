package com.jslsolucoes.appointment.api.repo;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jslsolucoes.appointment.api.domain.ScheduleSlot;
import com.jslsolucoes.appointment.api.domain.ScheduleSlotTime;

@Repository
public interface SchedulerSlotRepo extends CrudRepository<ScheduleSlot, Long> {

	@Query("select slt from ScheduleSlotTime slt inner join slt.scheduleSlot sl where sl.id = :idScheduleSlot and slt.available = true")
	public List<ScheduleSlotTime> hours(@Param("idScheduleSlot") Long idScheduleSlot);

	@Query("select sl from ScheduleSlot sl where sl.date > :min and sl.id in (select sl2.id from ScheduleSlotTime slt inner join slt.scheduleSlot sl2 where slt.available = true)  ")
	public List<ScheduleSlot> dates(@Param("min") LocalDate min);

	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Query("select slt from ScheduleSlotTime slt where slt.id = :idScheduleSlotTime")
	public Optional<ScheduleSlotTime> tryLock(@Param("idScheduleSlotTime") Long idScheduleSlotTime);

}
