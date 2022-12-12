package com.project.Projectwo.Repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.project.Projectwo.Entity.Course;

public interface CourseRepository extends JpaRepository<Course, Integer> {

}
