package com.jslsolucoes.appointment.api;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.jslsolucoes.appointment.api.domain.ScheduleSlot;
import com.jslsolucoes.appointment.api.domain.ScheduleSlotTime;
import com.jslsolucoes.appointment.api.repo.SchedulerSlotRepo;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner loadData(SchedulerSlotRepo schedulerSlotRepo) {
		return (args) -> {
			List<ScheduleSlot> scheduleSlots = IntStream.range(0, 1000).boxed().map(this::scheduleSlot)
					.collect(Collectors.toList());
			schedulerSlotRepo.saveAll(scheduleSlots);
		};
	}

	private ScheduleSlot scheduleSlot(Integer index) {
		ScheduleSlot scheduleSlot = new ScheduleSlot(LocalDate.now().plusDays(index));
		scheduleSlot.setTimes(schedulerTimes(scheduleSlot));
		return scheduleSlot;
	}

	private Set<ScheduleSlotTime> schedulerTimes(ScheduleSlot scheduleSlot) {
		return IntStream.range(0, 9).boxed()
			.map(index -> new ScheduleSlotTime(scheduleSlot,LocalTime.of(10,00).plusHours(index),true))
			.collect(Collectors.toSet());
	}
}