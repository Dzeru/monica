package com.dzeru.monica.measures.dto;

import com.dzeru.monica.measures.domain.Measure;
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