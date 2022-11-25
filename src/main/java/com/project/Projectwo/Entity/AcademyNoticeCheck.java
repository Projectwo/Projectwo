package com.project.Projectwo.Entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;

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
	private ClassMember classMember;
	
	@OneToMany(mappedBy = "academyNoticeCheck", cascade = CascadeType.REMOVE)
	private List<AcademyNotice> academyNotice;
	
}
