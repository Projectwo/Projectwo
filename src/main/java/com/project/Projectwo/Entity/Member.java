package com.project.Projectwo.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

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
	private String member_id;
	
	@NotNull
	private String member_pw;
	
	@NotNull
	@Column(unique = true, length = 150)
	private String email;
	
	@NotNull
	@Column(length = 30)
	private String name;
	
	private int age;
	
	@Column(length = 200)
	private String address;
	
	@Column(length = 50)
	private String tel;

}
