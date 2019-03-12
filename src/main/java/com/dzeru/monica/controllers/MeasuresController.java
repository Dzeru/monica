package com.dzeru.monica.controllers;

import com.dzeru.monica.domain.Measure;
import com.dzeru.monica.repos.MeasureRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/measures")
public class MeasuresController
{
	@Autowired
	private MeasureRepo measureRepo;

	@GetMapping("/measures")
	public String measures(Model model)
	{
		List<Measure> measures = measureRepo.findAll();
		model.addAttribute("msrs", measures);

		return "measures";
	}

	@PostMapping("/addmeasure")
	public String addmeasure(String name, int allSteps, String note, Model model)
	{
		Measure measure = new Measure();
		measure.setName(name);
		measure.setAllSteps(allSteps);
		measure.setDoneSteps(0);
		measure.setNote(note);
		measureRepo.save(measure);

		List<Measure> measures = measureRepo.findAll();
		model.addAttribute("msrs", measures);

		return "measures";
	}
}
