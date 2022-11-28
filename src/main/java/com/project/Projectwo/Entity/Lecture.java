package com.project.Projectwo.Entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
<<<<<<< HEAD
	@Column//(columnDefinition = "TEXT")
=======
	@Column(columnDefinition = "TEXT")
>>>>>>> main
	private String lectureDesc;
	
	@NotNull
	private LocalDate startDate;
	
	@NotNull
	private LocalDate endDate;
	
	@ManyToOne
	private LectureList lectureList;
}
