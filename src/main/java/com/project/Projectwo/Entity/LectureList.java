package com.project.Projectwo.Entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

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
	
	// by 장유란, 학생의 수강리스트
	@OneToMany(mappedBy = "lectureList", cascade = CascadeType.REMOVE)
	private List<Lecture> lecture;
	
	@OneToOne
	private ClassMember classMember;
}
