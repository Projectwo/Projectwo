package com.project.Projectwo.Entity;

import java.time.LocalTime;

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
public class Subject {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull
	@Column(length = 50)
	private String title;
	
	@NotNull
	@Column(length = 20)
	private String dayOfWeek;
	
	@NotNull
	private LocalTime startTime;
	
	@NotNull
	private LocalTime endTime;
	
	@NotNull
	@ManyToOne
	private Course course;
	
	@NotNull
	@ManyToOne
	private Room room;
}
