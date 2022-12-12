package com.project.Projectwo.Controller;

import java.time.LocalDate;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.Projectwo.Entity.Attendance;
import com.project.Projectwo.Entity.Course;
import com.project.Projectwo.Entity.Member;
import com.project.Projectwo.Entity.Student;
import com.project.Projectwo.Entity.Teacher;
import com.project.Projectwo.Service.AcademyService;
import com.project.Projectwo.Service.MemberService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class MemberController {
	
	private final MemberService memberService;
	private final AcademyService academyService;
	
	@RequestMapping(value = "/course/{memberId}/{courseId}")
	public String CoursePage(Model model, @PathVariable("memberId") Integer memberId, @PathVariable("courseId") Integer courseId) {
		Member member = this.memberService.getMember(memberId);
		Course course = this.academyService.getCourse(courseId);
		
		Student student = this.academyService.getStudent(course, member);
		Teacher teacher = this.academyService.getTeacher(course, member);
		Attendance attendance = this.academyService.getTodayAttendance(student);
		
		
		model.addAttribute("member", member);
		model.addAttribute("course", course);
		model.addAttribute("student", student);
		model.addAttribute("teacher", teacher);
		model.addAttribute("attendance", attendance);
		return "member/member_main";
	}
}
