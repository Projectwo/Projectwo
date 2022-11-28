package com.project.Projectwo.Entity;

import java.time.LocalDateTime;

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
public class Schedule {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull
	@Column(length = 20)
<<<<<<< HEAD
	private String dayOfWeek;
=======
	private String day;
>>>>>>> main
	
	@NotNull
	private LocalDateTime startTime;
	
	@NotNull
	private LocalDateTime endTime;
	
	@NotNull
	@ManyToOne
	private Lecture lecture;
	
	@NotNull
	@ManyToOne
	private Room room;
	
	@NotNull
	@ManyToOne
	private ClassTeacher classTeacher;
}
