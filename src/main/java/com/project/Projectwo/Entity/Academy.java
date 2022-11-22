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
public class Academy {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull
	@Column(length = 30)
	private String name;
	
	private String url;
	
	@Column(columnDefinition = "TEXT")
	private String history;
	
	@NotNull
	@Column(length = 50)
	private String tel;
	
	@Column(length = 200)
	private String address;
}
