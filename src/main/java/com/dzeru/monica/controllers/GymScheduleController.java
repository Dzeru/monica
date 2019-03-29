package com.dzeru.monica.controllers;

import com.dzeru.monica.services.GymScheduleService;
import com.dzeru.monica.services.GymScheduleViewPreparatorService;
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

	@GetMapping("/gymschedule")
	public String gym(Model model)
	{
		String gymScheduleView = gymScheduleViewPreparatorService.prepareGymScheduleView();

		model.addAttribute("gymScheduleView", gymScheduleView);
		return "gymschedule";
	}

	@PostMapping("/addgymclass")
	public String addGymClass(int lesson, int day, String description)
	{
		gymScheduleService.addGymClass(lesson, day, description);
		return "redirect:/gymschedule/gymschedule";
	}
}
