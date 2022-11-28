package com.project.Projectwo.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
<<<<<<< HEAD
=======
import javax.validation.constraints.NotNull;
>>>>>>> main

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class LectureList {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(unique = true, length = 50)
	private String lectureTitle;
}
