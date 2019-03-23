package com.dzeru.monica.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GymScheduleViewPreparatorService
{
	@Autowired
	private GymScheduleService gymScheduleService;

	private final static String BACKGROUND = "background:#3f7;";
	private final static String BORDER = "border-width:thin;border-style:solid;border-color:#000;";
	private final static String CELL_SIZE = "width:15vh;height:15vh;";
	private final static String TEXT_ALIGN = "text-align:center;vertical-align:middle;";

	public String prepareGymScheduleView()
	{
		int rowAmount = 6;
		int columnAmount = 6;
		String[][] gymSchedule = gymScheduleService.loadGymScheduleFromFile();
		StringBuilder sb = new StringBuilder();

		sb.append("<table style=\"display: inline-block\">");
		sb.append("<tr>");
		sb.append("<td style=\"" + BORDER + TEXT_ALIGN + "\">Понедельник</td>");
		sb.append("<td style=\"" + BORDER + TEXT_ALIGN + "\">Вторник</td>");
		sb.append("<td style=\"" + BORDER + TEXT_ALIGN + "\">Среда</td>");
		sb.append("<td style=\"" + BORDER + TEXT_ALIGN + "\">Четверг</td>");
		sb.append("<td style=\"" + BORDER + TEXT_ALIGN + "\">Пятница</td>");
		sb.append("<td style=\"" + BORDER + TEXT_ALIGN + "\">Суббота</td>");
		sb.append("</tr>");

		for(int ri = 0; ri < rowAmount; ri++)
		{
			sb.append("<tr>");
			for(int ci = 0; ci < columnAmount; ci++)
			{

				if(gymSchedule[ri][ci] != null && !gymSchedule[ri][ci].isEmpty())
				{
					sb.append("<td style=\"" + BACKGROUND + CELL_SIZE + BORDER + TEXT_ALIGN + "\">");
					sb.append(gymSchedule[ri][ci]);
				}
				else
				{
					sb.append("<td style=\"" + CELL_SIZE + BORDER + TEXT_ALIGN + "\">");
					sb.append(".");
				}

				sb.append("</td>");
			}
			sb.append("</tr>");
		}

		sb.append("</table");

		return sb.toString();
	}
}
