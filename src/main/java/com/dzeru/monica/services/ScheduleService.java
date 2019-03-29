package com.dzeru.monica.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class ScheduleService
{
	@Value("${schedule.group}")
	private String group;

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

			saveScheduleToFile(pageWithSchedule);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public String getScheduleFromFile()
	{
		String pageWithSchedule = "";
		try
		{
			File scheduleFile = new File("src/main/resources/schedule.txt");

			if(!scheduleFile.exists())
			{
				scheduleFile.createNewFile();
			}

			BufferedReader bf = new BufferedReader(new FileReader(scheduleFile));
			pageWithSchedule = bf.readLine();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		return pageWithSchedule;
	}

	private void saveScheduleToFile(String pageWithSchedule)
	{
		try
		{
			File scheduleFile = new File("src/main/resources/schedule.txt");

			if(!scheduleFile.exists())
			{
				scheduleFile.createNewFile();
			}

			FileWriter fw = new FileWriter(scheduleFile);
			fw.write(pageWithSchedule);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
