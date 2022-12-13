package com.project.Projectwo.Entity;

<<<<<<< HEAD
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
=======
>>>>>>> 5b424037fec2ecace99705a2ab1eccf4bc93a773
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
<<<<<<< HEAD
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

=======
import javax.validation.constraints.NotNull;

>>>>>>> 5b424037fec2ecace99705a2ab1eccf4bc93a773
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
<<<<<<< HEAD
	private String identity;
	
	@NotNull
	private String password;
	
	@NotNull
	@Column(length = 30)
	private String name;
=======
	private String member_id;
	
	@NotNull
	private String member_pw;
>>>>>>> 5b424037fec2ecace99705a2ab1eccf4bc93a773
	
	@NotNull
	@Column(unique = true, length = 150)
	private String email;
	
	@NotNull
<<<<<<< HEAD
	@JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="Asia/Seoul")
	private LocalDate birth_date;
	
	@NotNull
	@Column(length = 200)
	private String address;
	
	@NotNull
	@Column(length = 50)
	private String tel;
	
	@NotNull
	private String role;

	// by 장유란, 모바일 토큰 저장
	@Column
	private String token;

	// by 안준언, 내 수업 리스트 (학생 계정)
	@JsonBackReference
	@OneToMany(mappedBy = "student", cascade = CascadeType.REMOVE)
	private List<Student> studentClassList;
	
	// by 안준언, 내 강의 리스트 (선생 계정)
	@JsonBackReference
	@OneToMany(mappedBy ="teacher", cascade = CascadeType.REMOVE)
	private List<Teacher> teacherClassList;
	
	// by 안준언, 학원 전체 강의 리스트
	@JsonBackReference
	@OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
	private List<AcademyNoticeCheck> academyNoticeList;
=======
	@Column(length = 30)
	private String name;
	
	private int age;
	
	@Column(length = 200)
	private String address;
	
	@Column(length = 50)
	private String tel;
	
	private int auth;

>>>>>>> 5b424037fec2ecace99705a2ab1eccf4bc93a773
}
