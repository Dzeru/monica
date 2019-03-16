package com.dzeru.monica.services;

import com.dzeru.monica.domain.Measure;
import com.dzeru.monica.dto.MeasureView;
import org.springframework.stereotype.Service;

@Service
public class MeasureViewPreparatorService
{
	public MeasureView prepareMeasureView(Measure measure)
	{
		MeasureView measureView = new MeasureView(measure);

		int percentage = (int) ((measureView.getDoneSteps() * 1.0) / measureView.getAllSteps() * 100);
		measureView.setPercentage(percentage);

		StringBuilder sb = new StringBuilder();
		sb.append("<div class=\"measure\" ");
		sb.append("style=\"background: linear-gradient(to right, green " +
				percentage + "%, white " + percentage + "%)\">");
		sb.append("</div>");
		measureView.setHtmlView(sb.toString());

		return measureView;
	}

}
