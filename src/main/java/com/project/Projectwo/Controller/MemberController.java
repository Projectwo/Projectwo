package com.project.Projectwo.Controller;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

			//by 박은영
			//출석 또는 지각 상태일 경우 강의 종료시간에 맞춰 푸시알림 
			if(attendance != null) {
				if(attendance.getInTime() != null) {
					
					attendanceService.pushNotificationTimer(member, course, attendance);		
				}
			}
		}
		
		if(member.getRole().equals("teacher")) {
			Teacher teacher = this.academyService.getTeacher(course, member);
			model.addAttribute("teacher", teacher);
			
			List<Student> studentList = this.academyService.getStudentList(course);
			int totalStudent = studentList.size();
			model.addAttribute("totalStudent", totalStudent);
			
			int checkedStudent = 0;
			for(int i=0; i<studentList.size(); i++) {
				Attendance att = this.academyService.getTodayAttendance(studentList.get(i));
				if(att == null) {
					break;
				}
				if(att.getInTime() != null) {
					checkedStudent++;
				}
			}
			model.addAttribute("checkedStudent", checkedStudent);
			
			int uncheckedStudent = totalStudent - checkedStudent;
			model.addAttribute("uncheckedStudent", uncheckedStudent);
		}
		
		model.addAttribute("member", member);
		model.addAttribute("course", course);
		model.addAttribute("academyNotices", academyNotices);
		
		return "member/member_main";
	}

	@RequestMapping(value = "/course/{memberId}/{courseId}/detail")
	public String lectureDetail(Principal principal, Model model,
								@PathVariable("memberId") Integer memberId,
								@PathVariable("courseId") Integer courseId){
		
		if(principal == null) {
			return "redirect:/";
		}
		
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
		
		if(principal == null) {
			return "redirect:/";
		}
		
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
	
	@PostMapping("/createNotice/{courseId}")
	public String createCourseNotice(HttpServletRequest request,
										@PathVariable("courseId") Integer courseId,
										@RequestParam String title,
										@RequestParam String content) {
		Course course = this.academyService.getCourse(courseId);
		this.academyService.createClassNotice(title, content, course);
		
		String referer = request.getHeader("Referer");
		
		return "redirect:" + referer;
	}
	
	@RequestMapping(value = "/check/{memberId}/{courseId}")
	public String teacherClassAttendace(Principal principal, Model model,
										@PathVariable("courseId") Integer courseId,
										@PathVariable("memberId") Integer memberId) {
		
		if(principal == null) {
			return "redirect:/";
		}
		
		Member member = this.academyService.getMember(memberId);
		Course course = this.academyService.getCourse(courseId);
		List<Student> studentList = this.academyService.getStudentList(course);
		int totalDay = studentList.get(0).getAttendanceList().size();
		Attendance todayAtt = this.academyService.getTodayAttendance(studentList.get(0));
		
		int today = studentList.get(0).getAttendanceList().indexOf(todayAtt);
		
		double attAvg = (today/(double)totalDay)*100;
		
		List<Student> checkedList = new ArrayList<>();
		List<Student> uncheckedList = new ArrayList<>();
		
		for(int i = 0; i < studentList.size(); i++) {
			Attendance att = this.academyService.getTodayAttendance(studentList.get(i)); 
			if(att.getInTime() != null) {
				checkedList.add(att.getStudent());
			} else {
				uncheckedList.add(att.getStudent());
			}
		}
		
		model.addAttribute("member", member);
		model.addAttribute("course", course);
		model.addAttribute("totalDay", totalDay);
		model.addAttribute("today", today);
		model.addAttribute("attAvg", attAvg);
		model.addAttribute("checkedList", checkedList);
		model.addAttribute("uncheckedList", uncheckedList);
		
		
		return "member/teacher_check";
	}
}
