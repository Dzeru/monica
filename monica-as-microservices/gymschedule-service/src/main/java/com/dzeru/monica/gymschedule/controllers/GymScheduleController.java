package com.dzeru.monica.gymschedule.controllers;

import com.dzeru.monica.gymschedule.services.GymScheduleService;
import com.dzeru.monica.gymschedule.services.GymScheduleViewPreparatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/gymschedule")
public class GymScheduleController
{
	@Autowired
	GymScheduleService gymScheduleService;

	@Autowired
	GymScheduleViewPreparatorService gymScheduleViewPreparatorService;
	
	@GetMapping("/")
	public String index()
	{
		return "redirect:/gymschedule/gymschedule";
	}

	@GetMapping("/gymschedule")
	public String gym(Model model)
	{
		String gymScheduleView = gymScheduleViewPreparatorService.prepareGymScheduleView();

		model.addAttribute("gymScheduleView", gymScheduleView);
		return "gymschedule";
	}

	@PostMapping("/addgymlesson")
	public String addGymLesson(int lesson, int day, String note)
	{
		gymScheduleService.addGymLesson(lesson, day, note);
		return "redirect:/gymschedule/gymschedule";
	}
}
