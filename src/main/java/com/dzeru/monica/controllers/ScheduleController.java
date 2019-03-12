package com.dzeru.monica.controllers;

import com.dzeru.monica.services.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/schedule")
public class ScheduleController
{
	@Autowired
	ScheduleService scheduleService;

	@GetMapping("/schedule")
	public String schedule()
	{
		scheduleService.getSchedule();
		return "schedule";
	}
}
