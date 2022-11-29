package com.project.Projectwo.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
<<<<<<< HEAD:src/main/java/com/project/Projectwo/Entity/LectureList.java
<<<<<<< HEAD
=======
import javax.validation.constraints.NotNull;
>>>>>>> main
=======
import javax.persistence.ManyToOne;
>>>>>>> origin/dev:src/main/java/com/project/Projectwo/Entity/AcademyNoticeCheck.java

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class AcademyNoticeCheck {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	private Member member;
	
	@ManyToOne
	private AcademyNotice academyNotice;
	
	private boolean checked;

}
