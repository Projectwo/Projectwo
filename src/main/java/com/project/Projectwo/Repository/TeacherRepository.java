package com.project.Projectwo.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.project.Projectwo.Entity.Course;
import com.project.Projectwo.Entity.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
	//List<Teacher> findByCourse(Course course);
}
