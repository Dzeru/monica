package com.dzeru.monica.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class ScheduleService
{
	@Value("${schedule.group}")
	private String group;

	public String getSchedule()
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
			System.out.println(pageWithSchedule);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		return pageWithSchedule;
	}


}
