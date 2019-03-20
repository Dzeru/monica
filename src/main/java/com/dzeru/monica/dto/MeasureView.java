package com.dzeru.monica.dto;

import com.dzeru.monica.domain.Measure;
import lombok.Data;

@Data
public class MeasureView
{
	Measure measure;
	private int percentage;
	private String htmlView;

	public MeasureView()
	{

	}

	public MeasureView(Measure measure)
	{
		this.measure = measure;
	}
}