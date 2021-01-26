package com.jslsolucoes.appointment.api.resource;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jslsolucoes.appointment.api.usecase.ScheduleSlotUseCase;

@RestController
@RequestMapping("scheduleSlots")
public class ScheduleSlotResource {

	private ScheduleSlotUseCase scheduleSlotUseCase;

	public ScheduleSlotResource() {

	}

	@Autowired
	public ScheduleSlotResource(ScheduleSlotUseCase scheduleSlotUseCase) {
		this.scheduleSlotUseCase = scheduleSlotUseCase;
	}

	@GetMapping
	public List<SchedulerSlotTO> dates() {
		return scheduleSlotUseCase.dates(7).stream().map(SchedulerSlotTO::new).collect(Collectors.toList());
	}

	@GetMapping("/{idScheduleSlot}")
	public List<ScheduleSlotTimeTO> hours(@PathVariable("idScheduleSlot") Long idScheduleSlot) {
		return scheduleSlotUseCase.hours(idScheduleSlot).stream().map(ScheduleSlotTimeTO::new)
				.collect(Collectors.toList());
	}

}
