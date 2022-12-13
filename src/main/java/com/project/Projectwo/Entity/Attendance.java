package com.project.Projectwo.Entity;

<<<<<<< HEAD
import java.time.LocalDate;
import java.time.LocalTime;
=======
import java.time.LocalDateTime;
>>>>>>> 5b424037fec2ecace99705a2ab1eccf4bc93a773

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

<<<<<<< HEAD
import com.fasterxml.jackson.annotation.JsonFormat;

=======
>>>>>>> 5b424037fec2ecace99705a2ab1eccf4bc93a773
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Attendance {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
<<<<<<< HEAD

	@JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="Asia/Seoul")
	private LocalDate today;

	@JsonFormat(shape= JsonFormat.Shape.STRING, pattern="HH:mm", timezone="Asia/Seoul")
	private LocalTime inTime;
	
	@JsonFormat(shape= JsonFormat.Shape.STRING, pattern="HH:mm", timezone="Asia/Seoul")
	private LocalTime outTime;

=======
	
	private LocalDateTime inTime;
	
	private LocalDateTime outTime;
	
>>>>>>> 5b424037fec2ecace99705a2ab1eccf4bc93a773
	@NotNull
	@Column(length = 20)
	private String status;
	
	@NotNull
	@ManyToOne
<<<<<<< HEAD
	private Student student;
=======
	private ClassMember classMember;
	
	@NotNull
	@ManyToOne
	private Schedule schedule;
>>>>>>> 5b424037fec2ecace99705a2ab1eccf4bc93a773
}
