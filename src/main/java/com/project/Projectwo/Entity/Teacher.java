package com.project.Projectwo.Entity;

<<<<<<< HEAD
=======
import javax.persistence.Column;
>>>>>>> 5b424037fec2ecace99705a2ab1eccf4bc93a773
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
<<<<<<< HEAD
import javax.persistence.ManyToOne;
=======
import javax.persistence.OneToOne;
>>>>>>> 5b424037fec2ecace99705a2ab1eccf4bc93a773
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Teacher {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull
<<<<<<< HEAD
	@ManyToOne
	private Member teacher;
	
	@NotNull
	@ManyToOne
	private Course course;
=======
	@OneToOne
	private Member member;
	
	// 프로필 이미지 url 심을 필드
	private String url;
	
	@Column(columnDefinition = "TEXT")
	private String career;
>>>>>>> 5b424037fec2ecace99705a2ab1eccf4bc93a773
}
