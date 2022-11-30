package com.project.Projectwo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.Projectwo.Entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

}
