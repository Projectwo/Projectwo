package com.project.Projectwo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.Projectwo.Entity.Lecture;

public interface LectureRepository extends JpaRepository<Lecture, Integer> {

}
