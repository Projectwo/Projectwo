<<<<<<< HEAD
 package com.project.Projectwo.Entity;
=======
package com.project.Projectwo.Entity;
>>>>>>> 5b424037fec2ecace99705a2ab1eccf4bc93a773

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ClassNotice {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull
	@Column(length = 50)
	private String title;
	
	@NotNull
	@Column(columnDefinition = "TEXT")
	private String content;
	
	@NotNull
	private LocalDateTime createDate;
<<<<<<< HEAD

=======
	
	@NotNull
>>>>>>> 5b424037fec2ecace99705a2ab1eccf4bc93a773
	private LocalDateTime modifyDate;
	
	@NotNull
	@ManyToOne
<<<<<<< HEAD
	private Course course;
=======
	private Lecture lecture;
	
	@NotNull
	@ManyToOne
	private ClassTeacher classTeacher;
>>>>>>> 5b424037fec2ecace99705a2ab1eccf4bc93a773
}
