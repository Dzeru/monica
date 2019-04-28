package com.dzeru.monica.gymschedule.repos;

import com.dzeru.monica.gymschedule.domain.GymLesson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GymLessonRepo extends JpaRepository<GymLesson, Long>
{
}
