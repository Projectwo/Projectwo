package com.project.Projectwo.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
<<<<<<< HEAD
import javax.validation.constraints.NotNull;
=======
>>>>>>> 5b424037fec2ecace99705a2ab1eccf4bc93a773

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Room {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
<<<<<<< HEAD

	@NotNull
	@Column(length = 30)
	private String name;
	
	@NotNull
=======
	
	@Column(length = 30)
	private String roomName;
	
>>>>>>> 5b424037fec2ecace99705a2ab1eccf4bc93a773
	private int maxSeat;
	
}
