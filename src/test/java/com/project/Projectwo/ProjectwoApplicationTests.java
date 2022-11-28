package com.project.Projectwo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class ProjectwoApplicationTests {
	

//	// 계정 등록
//	@Test
//	void createMember() {
//		Member member1 = new Member();
//		member1.setIdentity("aaa");
//		member1.setPassword("aaa");
//		member1.setName("aaa");
//		member1.setEmail("aaa@aaa");
//		member1.setBirth_date(LocalDate.of(1991, 01, 01));
//		member1.setAddress("aaa");
//		member1.setTel("aaa");
//		member1.setRole("student");
//		
//		this.memberRepository.save(member1);
//		
//		Member member2 = new Member();
//		member2.setIdentity("bbb");
//		member2.setPassword("bbb");
//		member2.setName("bbb");
//		member2.setEmail("bbb@bbb");
//		member2.setBirth_date(LocalDate.of(1991, 01, 01));
//		member2.setAddress("bbb");
//		member2.setTel("bbb");
//		member2.setRole("student");
//		
//		this.memberRepository.save(member2);
//		
//		Member member3 = new Member();
//		member3.setIdentity("ccc");
//		member3.setPassword("ccc");
//		member3.setName("ccc");
//		member3.setEmail("ccc@ccc");
//		member3.setBirth_date(LocalDate.of(1991, 01, 01));
//		member3.setAddress("ccc");
//		member3.setTel("ccc");
//		member3.setRole("teacher");
//		
//		this.memberRepository.save(member3);
//		
//		Member member4 = new Member();
//		member4.setIdentity("ddd");
//		member4.setPassword("ddd");
//		member4.setName("ddd");
//		member4.setEmail("ddd@ddd");
//		member4.setBirth_date(LocalDate.of(1991, 01, 01));
//		member4.setAddress("ddd");
//		member4.setTel("ddd");
//		member4.setRole("teacher");
//		
//		this.memberRepository.save(member4);
//	}
	
//	// 강의 개설
//	@Test
//	void createCourse() {
//		Course course = new Course();
//		course.setTitle("java");
//		course.setDescription("study basic java");
//		course.setStartDate(LocalDate.of(2022, 11, 01));
//		course.setEndDate(LocalDate.of(2022, 11, 30));
//		
//		this.courseRepository.save(course);
//	}

//	// 강사, 학생 등록
//	@Test
//	void createStudentAndTeacher() {
//		Optional<Course> cs1 = this.courseRepository.findById(1);
//		Course course = cs1.get();
//		
//		Optional<Member> mb1 = this.memberRepository.findByIdentity("aaa");
//		Member member1 = mb1.get();
//		
//		Optional<Member> mb2 = this.memberRepository.findByIdentity("bbb");
//		Member member2 = mb2.get();
//
//		Optional<Member> mb3 = this.memberRepository.findByIdentity("ccc");
//		Member member3 = mb3.get();
//		
//		Optional<Member> mb4 = this.memberRepository.findByIdentity("ddd");
//		Member member4 = mb4.get();
//		
//		this.academyService.createStudent(member1, course);
//		this.academyService.createStudent(member2, course);
//		
//		this.academyService.createTeacher(member3, course);
//		this.academyService.createTeacher(member4, course);
//		
//	}
	


}
