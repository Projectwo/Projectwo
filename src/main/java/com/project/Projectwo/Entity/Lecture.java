package com.project.Projectwo.Entity;

import java.time.LocalDate;
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

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Lecture {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull
	@Column(unique = true, length = 300)
	private String lectureName;
	
	@NotNull
	@Column
	private String lectureDesc;
	
	@NotNull
	private LocalDate startDate;
	
	@NotNull
	private LocalDate endDate;
	
	//by 장유란, 강의리스트가 강의 참조(ManyToOne)
	@ManyToOne
	private LectureList lectureList;

	//by 장유린, 여러 스케줄(9~12/ 13~18)을 하나로 모으는 역할
	@OneToMany(mappedBy = "lecture", cascade = CascadeType.REMOVE)
	private List<Schedule> schedule;
	
}
