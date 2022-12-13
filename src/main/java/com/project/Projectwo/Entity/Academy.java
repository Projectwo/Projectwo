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
<<<<<<< HEAD

	@Column(columnDefinition = "TEXT")
	private String history;
	
	@Column(length = 200)
	private String address;
	
	@NotNull
	@Column(length = 50)
	private String tel;
=======
	
	private String url;
	
	@Column(columnDefinition = "TEXT")
	private String history;
	
	@NotNull
	@Column(length = 50)
	private String tel;
	
	@Column(length = 200)
	private String address;
>>>>>>> 5b424037fec2ecace99705a2ab1eccf4bc93a773
}
