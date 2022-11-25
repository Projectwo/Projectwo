package com.project.Projectwo.Entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ClassNoticeCheck {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	private ClassMember classMember;
	
	@OneToMany(mappedBy = "classNoticeCheck", cascade = CascadeType.REMOVE)
	private List<ClassNotice> classNotice;
}
