package com.dzeru.monica.measures.services;

import com.dzeru.monica.measures.domain.Measure;
import com.dzeru.monica.measures.dto.MeasureView;
import org.springframework.stereotype.Service;

@Service
public class MeasureViewPreparatorService
{
	public MeasureView prepareMeasureView(Measure measure)
	{
		MeasureView measureView = new MeasureView(measure);

		int percentage = (int) ((measureView.getMeasure().getDoneSteps() * 1.0) / measureView.getMeasure().getAllSteps() * 100);
		measureView.setPercentage(percentage);

		StringBuilder sb = new StringBuilder();
		sb.append("<div class=\"measure\" ");
		sb.append("style=\"background: linear-gradient(to right, " + measureView.getMeasure().getColor() + " " +
				percentage + "%, white " + percentage + "%)\">");
		sb.append("</div>");
		measureView.setHtmlView(sb.toString());

		return measureView;
	}

}
