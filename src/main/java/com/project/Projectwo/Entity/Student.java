package com.project.Projectwo.Entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull
	@ManyToOne
	private Member student;
	
	@NotNull
	@ManyToOne
	private Course course;
	
	// by 안준언, 출결 기록
	@JsonBackReference
	@OneToMany(mappedBy = "student", cascade = CascadeType.REMOVE)
	private List<Attendance> attendanceList;
	
	// by 안준언, 강의 공지 리스트
	@JsonBackReference
	@OneToMany(mappedBy = "student", cascade = CascadeType.REMOVE)
	private List<ClassNoticeCheck> classNoticeList;
}
