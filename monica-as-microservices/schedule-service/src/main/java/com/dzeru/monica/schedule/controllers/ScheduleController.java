package com.dzeru.monica.schedule.controllers;

import com.dzeru.monica.schedule.domain.Schedule;
import com.dzeru.monica.schedule.repos.ScheduleRepo;
import com.dzeru.monica.schedule.services.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/schedule")
public class ScheduleController
{
	@Value("${schedule.group}")
	private String group;

	@Autowired
	ScheduleService scheduleService;

	@Autowired
	ScheduleRepo scheduleRepo;

	@GetMapping("/")
	public String index()
	{
		return "redirect:/schedule/schedule";
	}

	@GetMapping("/updateschedule")
	public String updateSchedule(Model model)
	{
		scheduleService.getScheduleFromWeb();
		return "redirect:/schedule/schedule";
	}

	@GetMapping("/schedule")
	public String schedule(Model model)
	{
		String schedule = scheduleService.getScheduleFromDB();

		if(schedule == null || schedule.isEmpty())
		{
			return "redirect:/schedule/updateschedule";
		}

		model.addAttribute("schedule", schedule);
		return "schedule";
	}

	@GetMapping("/editschedule")
	public String editSchedule(Model model)
	{
		String schedule = scheduleService.getScheduleFromDB();
		model.addAttribute("schedule", schedule);
		return "editschedule";
	}

	@PostMapping("/saveeditedschedule")
	public String saveEditedSchedule(String schedule)
	{
		List<Schedule> scheduleList = scheduleRepo.findAll();
		Schedule scheduleToUpdate = scheduleList.get(0);
		scheduleToUpdate.setScheduleTable(schedule);
		scheduleRepo.save(scheduleToUpdate);

		return "redirect:/schedule/schedule";
	}
}
