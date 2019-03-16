package com.dzeru.monica.repos;

import com.dzeru.monica.domain.Measure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
@Repository
public interface MeasureRepo extends JpaRepository<Measure, Long>
{
	Measure findByIdEquals(Long id);
}
