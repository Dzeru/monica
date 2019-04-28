package com.dzeru.monica.schedule.repos;

import com.dzeru.monica.schedule.domain.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepo extends JpaRepository<Schedule, Long>
{
}
