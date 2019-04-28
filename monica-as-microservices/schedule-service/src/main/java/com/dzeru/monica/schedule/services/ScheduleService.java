package com.dzeru.monica.schedule.services;

import com.dzeru.monica.schedule.domain.Schedule;
import com.dzeru.monica.schedule.repos.ScheduleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

@Service
public class ScheduleService
{
	@Value("${schedule.group}")
	private String group;

	@Autowired
	private ScheduleRepo scheduleRepo;

	public void getScheduleFromWeb()
	{
		String pageWithSchedule = "";

		try
		{
			String schedulePath = "https://www.sgu.ru/schedule/knt/do/" + group;
			URL scheduleUrl = new URL(schedulePath);
			HttpURLConnection connection = (HttpURLConnection) scheduleUrl.openConnection();
			connection.setRequestMethod("GET");

			BufferedReader bf = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while((inputLine = bf.readLine()) != null)
			{
				response.append(inputLine);
			}

			pageWithSchedule = response.toString();

			bf.close();

			pageWithSchedule = pageWithSchedule.substring(pageWithSchedule.indexOf("<table id='schedule'>"), pageWithSchedule.indexOf("</table>") + 8);

			saveScheduleToDB(pageWithSchedule);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public String getScheduleFromDB()
	{
		List<Schedule> schedule = scheduleRepo.findAll();

		if(!schedule.isEmpty())
		{
			return schedule.get(0).getScheduleTable();
		}
		else
		{
			return null;
		}
	}

	private void saveScheduleToDB(String pageWithSchedule)
	{
		Schedule schedule = new Schedule();
		schedule.setScheduleTable(pageWithSchedule);
		scheduleRepo.save(schedule);
	}
}
