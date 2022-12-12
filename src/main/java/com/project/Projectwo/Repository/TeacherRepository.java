package com.project.Projectwo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.Projectwo.Entity.Course;
import com.project.Projectwo.Entity.Member;
import com.project.Projectwo.Entity.Student;
import com.project.Projectwo.Entity.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
	List<Teacher> findByCourse(Course course);
	List<Teacher> findByTeacher(Member teacher);
	Teacher findByCourseAndTeacher(Course course, Member teacher);
}
