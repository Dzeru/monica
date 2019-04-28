package com.dzeru.monica.gymschedule.services;

import com.dzeru.monica.gymschedule.domain.GymLesson;
import com.dzeru.monica.gymschedule.repos.GymLessonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GymScheduleService
{
	@Autowired
	private GymLessonRepo gymLessonRepo;

	public void addGymLesson(int lesson, int day, String note)
	{
		GymLesson gymLesson = new GymLesson();
		gymLesson.setLesson(lesson);
		gymLesson.setDay(day);
		gymLesson.setNote(note);
		gymLessonRepo.save(gymLesson);
	}

	public String[][] loadGymScheduleFromDB()
	{
		String[][] gymSchedule = new String[6][6];

		List<GymLesson> gymLessons = gymLessonRepo.findAll();

		for(GymLesson g : gymLessons)
		{
			gymSchedule[g.getLesson() - 1][g.getDay() - 1] = g.getNote();
		}

		return gymSchedule;
	}
}
