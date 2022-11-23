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
public class ClassNotice {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull
	@Column(length = 50)
	private String title;
	
	@NotNull
	@Column(columnDefinition = "TEXT")
	private String content;
	
	@NotNull
	private LocalDateTime createDate;
	
	//@NotNull
	private LocalDateTime modifyDate;
	
	@NotNull
	@ManyToOne
	private Lecture lecture;
	
	@NotNull
	@ManyToOne//작성자
	private ClassTeacher classTeacher;
}
