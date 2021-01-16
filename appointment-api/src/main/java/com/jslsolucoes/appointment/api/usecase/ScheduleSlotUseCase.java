package com.jslsolucoes.appointment.api.usecase;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jslsolucoes.appointment.api.Lists;
import com.jslsolucoes.appointment.api.domain.ScheduleSlot;
import com.jslsolucoes.appointment.api.domain.ScheduleSlotTime;
import com.jslsolucoes.appointment.api.repo.SchedulerSlotRepo;

@Service
public class ScheduleSlotUseCase {

	private SchedulerSlotRepo schedulerSlotRepo;

	public ScheduleSlotUseCase(SchedulerSlotRepo schedulerSlotRepo) {
		this.schedulerSlotRepo = schedulerSlotRepo;
	}

	public List<ScheduleSlot> dates(LocalDate min, LocalDate max) {
		return Optional.of(schedulerSlotRepo.dates(min, max)).filter(Lists::isNotEmpty)
				.orElseThrow(EntityNotFoundException::new);
	}

	public List<ScheduleSlotTime> hours(Long idScheduleSlot) {
		return Optional.of(schedulerSlotRepo.hours(idScheduleSlot)).filter(Lists::isNotEmpty)
				.orElseThrow(EntityNotFoundException::new);
	}

	public ScheduleSlotTime makeUnavailable(Long idScheduleSlotTime) {
		ScheduleSlotTime scheduleSlotTime = schedulerSlotRepo.tryLock(idScheduleSlotTime).orElseThrow(
				() -> new EntityNotFoundException("Could not find slot time with {0}", idScheduleSlotTime));
		if (Boolean.FALSE.equals(scheduleSlotTime.available())) {
			throw new ConflictException("Slot time id {0} its not available anymore", idScheduleSlotTime);
		}
		return scheduleSlotTime.makeUnvailable();
	}

}
