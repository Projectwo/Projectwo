package com.project.Projectwo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.Projectwo.Entity.Course;
import com.project.Projectwo.Entity.Member;
import com.project.Projectwo.Entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {
	List<Student> findByCourse(Course course);
	//List<Student> findByMemberAndCourse(Member member, Course course);
}
