package com.project.Projectwo.Entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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
public class Course {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull
	@Column(unique = true, length = 300)
	private String title;
	
	@NotNull
	@Column(columnDefinition = "TEXT")
	private String description;
	
	@NotNull
	private LocalDate startDate;
	
	@NotNull
	private LocalDate endDate;
	
	@NotNull
	private LocalTime startTime;
	
	@NotNull
	private LocalTime endTime;
	
	private boolean mon;
	
	private boolean tue;
	
	private boolean wed;
	
	private boolean thu;
	
	private boolean fri;
	
	private boolean sat;
	
	private boolean sun;
	
	@NotNull
	@ManyToOne
	private Room room;
	
	// by 안준언, 수강생 리스트
	@JsonBackReference
	@OneToMany(mappedBy = "course", cascade = CascadeType.REMOVE)
	private List<Student> studentList;
	
	// by 안준언, 강사 리스트
	@JsonBackReference
	@OneToMany(mappedBy = "course", cascade = CascadeType.REMOVE)
	private List<Teacher> teacherList;
	
	// by 안준언, 강의 공지 리스트
	@JsonBackReference
	@OneToMany(mappedBy = "course", cascade = CascadeType.REMOVE)
	private List<ClassNotice> classNoticeList;
	
}
