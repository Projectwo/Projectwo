package com.project.Projectwo.Entity;

import java.time.LocalDateTime;

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
public class AcademyNotice {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length = 50)
	private String title;
	
	@Column(columnDefinition = "TEXT")
	private String content;
	
	@NotNull
	private LocalDateTime createDate;
<<<<<<< HEAD

	private LocalDateTime modifyDate;

=======
	
	@NotNull
	private LocalDateTime modifyDate;
	
>>>>>>> 5b424037fec2ecace99705a2ab1eccf4bc93a773
}
