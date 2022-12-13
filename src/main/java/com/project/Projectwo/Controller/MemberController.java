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
import com.project.Projectwo.Service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
public class MemberController {
	
	private final MemberService memberService;
	private final AcademyService academyService;
	
	@RequestMapping(value = "/course/{memberId}/{courseId}")
	public String CoursePage(Principal principal, Model model,
								@PathVariable("memberId") Integer memberId,
								@PathVariable("courseId") Integer courseId) {
		if(principal == null) {
			return "redirect:/";
		}
		
		Member member = this.memberService.getMember(memberId);
		Course course = this.academyService.getCourse(courseId);

		// by 장유란, member_main에서 member명, 강의리스트, 전체공지 출력
		List<AcademyNotice> academyNotices = academyService.getAllAcademyNotice();
    	model.addAttribute("today", (LocalDate.now().format(DateTimeFormatter.ofPattern("MM/dd"))));		

		if(member.getRole().equals("student")){
			Student student = this.academyService.getStudent(course, member);
			Attendance attendance = this.academyService.getTodayAttendance(student);			
			List<Attendance> sixAttList = this.academyService.getBefore6Attendance(student);
			model.addAttribute("student", student);
			model.addAttribute("attendance", attendance);
			model.addAttribute("sixAttList", sixAttList);
		}
		
		if(member.getRole().equals("teacher")) {
			Teacher teacher = this.academyService.getTeacher(course, member);
			model.addAttribute("teacher", teacher);
		}
		
		model.addAttribute("member", member);
		model.addAttribute("course", course);
		model.addAttribute("academyNotices", academyNotices);
		
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
	
	@RequestMapping(value = "/course/{memberId}/{courseId}/attendance/{attendanceId}")
	public String AttendancePage(Principal principal, Model model,
									@PathVariable("memberId") Integer memberId,
									@PathVariable("courseId") Integer courseId,
									@PathVariable("attendanceId") Integer attendanceId) {
		
		Member member = this.memberService.getMember(memberId);
		Course course = this.academyService.getCourse(courseId);
		Attendance attendance = this.academyService.getAttendance(attendanceId);
		if(attendance != null) {
			model.addAttribute("_attendance", attendance);
		}
		
		
		Student student = this.academyService.getStudent(course, member);
		Teacher teacher = this.academyService.getTeacher(course, member);
		
		double attCnt = 0;
		for(int i = 0; i<student.getAttendanceList().size(); i++) {
			Attendance att = student.getAttendanceList().get(i);
			if(att.getInTime() != null) {
				attCnt++;
			}
		}
		
		double attAvg = (attCnt/(double)student.getAttendanceList().size())*100;
		
		model.addAttribute("member", member);
		model.addAttribute("course", course);
		model.addAttribute("student", student);
		model.addAttribute("teacher", teacher);
		model.addAttribute("attCnt", attCnt);
		model.addAttribute("attAvg", attAvg);
		
		return "member/student_check";
	}
}
