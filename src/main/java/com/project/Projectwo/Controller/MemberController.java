package com.project.Projectwo.Controller;

import java.security.Principal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.Projectwo.Entity.AcademyNotice;
import com.project.Projectwo.Entity.Attendance;
import com.project.Projectwo.Entity.Course;
import com.project.Projectwo.Entity.Member;
import com.project.Projectwo.Entity.Student;
import com.project.Projectwo.Entity.Teacher;

import com.project.Projectwo.Service.AcademyService;
import com.project.Projectwo.Service.AttendanceService;
import com.project.Projectwo.Service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
public class MemberController {
	
	private final MemberService memberService;
	private final AcademyService academyService;
	private final AttendanceService attendanceService;

	
	@RequestMapping(value = "/course/{memberId}/{courseId}")
	public String CoursePage(Principal principal, Model model,
								@PathVariable("memberId") Integer memberId,
								@PathVariable("courseId") Integer courseId) {
		if(principal == null) {
			return "redirect:/";
		}
		
		Member member = this.memberService.getMember(memberId);
		Course course = this.academyService.getCourse(courseId);
		
		Student student = this.academyService.getStudent(course, member);
		Teacher teacher = this.academyService.getTeacher(course, member);
		Attendance attendance = this.academyService.getTodayAttendance(student);

		
		//by 박은영
		//출석 또는 지각 상태일 경우 강의 종료시간에 맞춰 푸시알림 
		if(attendance != null) {

			attendanceService.pushNotificationTimer(member, course, attendance);		
		}
		
		
		// by 장유란, member_main에서 member명, 강의리스트, 전체공지 출력
		List<AcademyNotice> academyNotices = academyService.getAllAcademyNotice();
    	model.addAttribute("today", (LocalDate.now().format(DateTimeFormatter.ofPattern("MM/dd"))));		

		model.addAttribute("member", member);
		model.addAttribute("course", course);
		model.addAttribute("student", student);
		model.addAttribute("teacher", teacher);
		model.addAttribute("attendance", attendance);
		model.addAttribute("academyNotices", academyNotices);

		if(member.getRole().equals("student")){
			List<Attendance> sixAttList = this.academyService.getBefore6Attendance(student);
			model.addAttribute("sixAttList", sixAttList);
		}
		
		
		return "member/member_main";
	}

	@RequestMapping(value = "/course/{memberId}/{courseId}/detail")
	public String lectureDetail(Model model, @PathVariable("memberId") Integer memberId, @PathVariable("courseId") Integer courseId){
		Member member = this.memberService.getMember(memberId);
		Course course = this.academyService.getCourse(courseId);
		
		Student student = this.academyService.getStudent(course, member);
		Teacher teacher = this.academyService.getTeacher(course, member);
		Attendance attendance = this.academyService.getTodayAttendance(student);

		// by 장유란, member_main에서 member명, 강의리스트, 전체공지 출력
		List<AcademyNotice> academyNotices = academyService.getAllAcademyNotice();
    	model.addAttribute("today", (LocalDate.now().format(DateTimeFormatter.ofPattern("MM/dd"))));
		
		model.addAttribute("member", member);
		model.addAttribute("course", course);
		model.addAttribute("student", student);
		model.addAttribute("teacher", teacher);
		model.addAttribute("attendance", attendance);
		model.addAttribute("academyNotices", academyNotices);

		return "lecture/lecture_detail";
	}
}
