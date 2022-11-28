package com.project.Projectwo.Entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Member {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull
	@Column(unique = true, length = 50)
	private String identity;
	
	@NotNull
	private String password;
	
	@NotNull
	@Column(length = 30)
	private String name;
	
	@NotNull
	@Column(unique = true, length = 150)
	private String email;
	
	@NotNull
	private LocalDate birth_date;
	
	@NotNull
	@Column(length = 200)
	private String address;
	
	@NotNull
	@Column(length = 50)
	private String tel;
	
	@NotNull
	private String role;

	// by 안준언, 내 수업 리스트 (학생 계정)
	@OneToMany(mappedBy = "student", cascade = CascadeType.REMOVE)
	private List<Student> studentClassList;
	
	// by 안준언, 내 강의 리스트 (선생 계정)
	@OneToMany(mappedBy ="teacher", cascade = CascadeType.REMOVE)
	private List<Teacher> teacherClassList;
	
	// by 안준언, 학원 전체 강의 리스트
	@OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
	private List<AcademyNoticeCheck> academyNoticeList;
}
