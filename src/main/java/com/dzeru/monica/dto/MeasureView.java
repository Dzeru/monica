package com.dzeru.monica.dto;

import com.dzeru.monica.domain.Measure;
import lombok.Data;

@Data
public class MeasureView
{
	private Long id;
	private String name;
	private int doneSteps;
	private int allSteps;
	private String note;
	private int percentage;
	private String htmlView;

	public MeasureView()
	{

	}

	public MeasureView(Measure measure)
	{
		id = measure.getId();
		name = measure.getName();
		doneSteps = measure.getDoneSteps();
		allSteps = measure.getAllSteps();
		note = measure.getNote();
	}
}