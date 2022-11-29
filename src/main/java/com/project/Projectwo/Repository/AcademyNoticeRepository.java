package com.project.Projectwo.Repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.project.Projectwo.Entity.AcademyNotice;

public interface AcademyNoticeRepository extends JpaRepository<AcademyNotice, Integer> {

	Optional<AcademyNotice> findById(Integer id);
}
