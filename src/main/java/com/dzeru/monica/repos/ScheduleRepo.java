package com.dzeru.monica.repos;

import com.dzeru.monica.domain.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepo extends JpaRepository<Schedule, Long>
{
}
