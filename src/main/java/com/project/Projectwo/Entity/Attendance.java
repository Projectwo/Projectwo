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
public class Attendance {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private LocalDateTime inTime;
	
	private LocalDateTime outTime;
	
	@NotNull
	@Column(length = 20)
	private String status;
	
	@NotNull
	@ManyToOne
	private ClassMember classMember;
	
	@NotNull
	@ManyToOne
	private Schedule schedule;
}
