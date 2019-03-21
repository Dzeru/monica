package com.dzeru.monica.controllers;

import com.dzeru.monica.domain.Measure;
import com.dzeru.monica.dto.MeasureView;
import com.dzeru.monica.repos.MeasureRepo;
import com.dzeru.monica.services.MeasureViewPreparatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/measures")
public class MeasuresController
{
	@Autowired
	private MeasureRepo measureRepo;

	@Autowired
	private MeasureViewPreparatorService measureViewPreparatorService;

	@GetMapping("/measures")
	public String measures(Model model)
	{
		List<Measure> measures = measureRepo.findAll();

		if(measures.isEmpty())
			return "measures";

		List<MeasureView> measureViews = new ArrayList<MeasureView>();

		for(Measure m : measures)
		{
			measureViews.add(measureViewPreparatorService.prepareMeasureView(m));
		}

		model.addAttribute("msrs", measureViews);

		return "measures";
	}

	@PostMapping("/addmeasure")
	public String addmeasure(String name, int allSteps, String note, String color, Model model)
	{
		Measure measure = new Measure();
		measure.setName(name);
		measure.setAllSteps(allSteps);
		measure.setDoneSteps(0);
		measure.setNote(note);
		measure.setColor(color);
		measureRepo.save(measure);

		List<Measure> measures = measureRepo.findAll();
		model.addAttribute("msrs", measures);

		return "redirect:/measures/measures";
	}

	@PostMapping("/plussteps/{id}")
	public String plusSteps(@PathVariable("id") Long id, Model model)
	{
		Measure measure = measureRepo.findByIdEquals(id);
		int newDoneSteps = measure.getDoneSteps() + 1;

		if(newDoneSteps <= measure.getAllSteps())
		{
			measure.setDoneSteps(newDoneSteps);
			measureRepo.save(measure);
		}

		List<Measure> measures = measureRepo.findAll();
		List<MeasureView> measureViews = new ArrayList<MeasureView>();

		for(Measure m : measures)
		{
			measureViews.add(measureViewPreparatorService.prepareMeasureView(m));
		}

		model.addAttribute("msrs", measureViews);

		return "redirect:/measures/measures";
	}

	@PostMapping("/minussteps/{id}")
	public String minusSteps(@PathVariable("id") Long id, Model model)
	{
		Measure measure = measureRepo.findByIdEquals(id);
		int newDoneSteps = measure.getDoneSteps() - 1;

		if(newDoneSteps >= 0)
		{
			measure.setDoneSteps(newDoneSteps);
			measureRepo.save(measure);
		}

		List<Measure> measures = measureRepo.findAll();
		List<MeasureView> measureViews = new ArrayList<MeasureView>();

		for(Measure m : measures)
		{
			measureViews.add(measureViewPreparatorService.prepareMeasureView(m));
		}

		model.addAttribute("msrs", measureViews);

		return "redirect:/measures/measures";
	}

	@PostMapping("/updatemeasure/{id}")
	public String updateMeasure(@PathVariable("id") Long id, String name, int doneSteps, int allSteps, String note, Model model)
	{
		Measure measure = measureRepo.findByIdEquals(id);
		measure.setName(name);
		measure.setDoneSteps(doneSteps);
		measure.setAllSteps(allSteps);
		measure.setNote(note);
		measureRepo.save(measure);

		List<Measure> measures = measureRepo.findAll();
		List<MeasureView> measureViews = new ArrayList<MeasureView>();

		for(Measure m : measures)
		{
			measureViews.add(measureViewPreparatorService.prepareMeasureView(m));
		}

		model.addAttribute("msrs", measureViews);

		return "redirect:/measures/measures";
	}

	@PostMapping("/deletemeasure/{id}")
	public String deleteMeasure(@PathVariable("id") Long id, Model model)
	{
		measureRepo.deleteById(id);

		List<Measure> measures = measureRepo.findAll();
		List<MeasureView> measureViews = new ArrayList<MeasureView>();

		for(Measure m : measures)
		{
			measureViews.add(measureViewPreparatorService.prepareMeasureView(m));
		}

		model.addAttribute("msrs", measureViews);

		return "redirect:/measures/measures";
	}
}