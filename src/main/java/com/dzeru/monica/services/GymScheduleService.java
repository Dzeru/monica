package com.dzeru.monica.services;

import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class GymScheduleService
{
	private final static String GYM_SCHEDULE_FILE_NAME = "gymSchedule.txt";

	public void addGymClass(int lesson, int day, String description)
	{
		try
		{
			FileWriter fw = new FileWriter("src/main/resources/" + GYM_SCHEDULE_FILE_NAME, true);
			fw.write(lesson + "," + day + "," + description + "\r\n");
			fw.flush();
			fw.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public String[][] loadGymScheduleFromFile()
	{
		String[][] gymSchedule = new String[6][6];

		InputStream s = Thread.currentThread().getContextClassLoader().getResourceAsStream(GYM_SCHEDULE_FILE_NAME);
		BufferedReader reader = new BufferedReader(new InputStreamReader(s));

		try
		{
			String gymString = reader.readLine();

			while(gymString != null)
			{
				String[] parseGymString = gymString.split(",");
				int lesson = Integer.parseInt(parseGymString[0]);
				int day = Integer.parseInt(parseGymString[1]);
				String description = parseGymString[2];
				gymSchedule[lesson - 1][day - 1] = description;
				gymString = reader.readLine();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		return gymSchedule;
	}
}
