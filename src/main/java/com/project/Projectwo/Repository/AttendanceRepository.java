package com.project.Projectwo.Repository;

<<<<<<< HEAD
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.Projectwo.Entity.Attendance;
import com.project.Projectwo.Entity.Student;

public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {
	Optional<Attendance> findByToday(LocalDate date);
	Optional<Attendance> findByStudentAndToday(Student student, LocalDate date);

}
=======
import org.springframework.data.jpa.repository.JpaRepository;

import com.project.Projectwo.Entity.Attendance;

public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {

}
>>>>>>> 5b424037fec2ecace99705a2ab1eccf4bc93a773
