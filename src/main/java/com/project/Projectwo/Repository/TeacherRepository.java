package com.project.Projectwo.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.Projectwo.Entity.Member;
import com.project.Projectwo.Entity.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
	
	List<Teacher> findByTeacher(Member teacher);
}
