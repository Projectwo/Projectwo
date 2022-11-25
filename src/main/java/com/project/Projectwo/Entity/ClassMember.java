package com.project.Projectwo.Entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ClassMember {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull
	@ManyToOne
	private Lecture lecture;
	
	@NotNull
	@ManyToOne
	private Member member;
	
	//by 장유란, lectureList 추가(학생 한명이 여러 강의 수강가능)
	@OneToOne(mappedBy = "classMember", cascade = CascadeType.REMOVE)
	private LectureList lectureList;
	
	@OneToMany(mappedBy = "classMember", cascade = CascadeType.REMOVE)
	private List<Attendance> attendanceList;

	@OneToMany(mappedBy = "classMember", cascade = CascadeType.REMOVE)
	private List<ClassNoticeCheck> classNoticeCheck;
	
	@OneToMany(mappedBy = "classMember", cascade = CascadeType.REMOVE)
	private List<AcademyNoticeCheck> academyNoticeCheck;

}
