package com.project.Projectwo.Repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.Projectwo.Entity.Member;
import com.project.Projectwo.Entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

	Integer findById(Member student);
	
}
