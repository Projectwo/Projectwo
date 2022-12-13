package com.project.Projectwo.Repository;

<<<<<<< HEAD
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.Projectwo.Entity.Course;
import com.project.Projectwo.Entity.Member;
import com.project.Projectwo.Entity.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
	List<Teacher> findByCourse(Course course);
	List<Teacher> findByTeacher(Member teacher);
	Teacher findByCourseAndTeacher(Course course, Member teacher);
=======
import org.springframework.data.jpa.repository.JpaRepository;

import com.project.Projectwo.Entity.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {

>>>>>>> 5b424037fec2ecace99705a2ab1eccf4bc93a773
}
