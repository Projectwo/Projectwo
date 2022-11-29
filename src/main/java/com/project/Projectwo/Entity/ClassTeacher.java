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

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ClassTeacher {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull
	@ManyToOne
	private Teacher teacher;
	
	@NotNull
	@ManyToOne
	private Lecture lecture;
	
	// by 장유란, 선생님의 스케줄 리스트(시간표)와 작성공지목록
	@OneToMany(mappedBy = "classTeacher", cascade = CascadeType.REMOVE)
	private List<Schedule> scheduleList;
	
	@OneToMany(mappedBy = "classTeacher", cascade = CascadeType.REMOVE)
	private List<ClassNotice> classNotice;
}
