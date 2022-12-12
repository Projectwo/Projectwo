package com.project.Projectwo.Repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.Projectwo.Entity.Attendance;
import com.project.Projectwo.Entity.Student;

public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {
	Optional<Attendance> findByToday(LocalDate date);
	Optional<Attendance> findByStudentAndToday(Student student, LocalDate date);

}
