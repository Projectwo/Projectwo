package com.project.Projectwo.Controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.google.firebase.messaging.FirebaseMessagingException;
import com.project.Projectwo.Entity.Attendance;
import com.project.Projectwo.Entity.Course;
import com.project.Projectwo.Entity.Member;
import com.project.Projectwo.Entity.Student;
import com.project.Projectwo.Repository.AttendanceRepository;
import com.project.Projectwo.Repository.CourseRepository;
import com.project.Projectwo.Repository.MemberRepository;
import com.project.Projectwo.Repository.StudentRepository;
import com.project.Projectwo.Service.AcademyService;
import com.project.Projectwo.Service.AttendanceService;
import com.project.Projectwo.Service.FCMService;
import com.project.Projectwo.Service.MemberService;
import com.project.Projectwo.Service.QrService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Controller
public class QrController {
	
	private final CourseRepository courseRepository;
	private final AttendanceRepository attendanceRepository;
	private final StudentRepository studentRepository;
	private final MemberRepository memberRepository;
	
	private final AcademyService academyService;
	private final AttendanceService attendanceService;
	private final MemberService memberService;
	private final QrService qrService;
	private final FCMService fcmService;

	//by 박은영
	//"http://ip:9090/course/{courseId}/{LocalDate}로 qr생성
	//TODO: courseId 특정 지어서 가져와야 됨
	@GetMapping("/createQr")
	public void createQr(HttpServletRequest request, HttpServletResponse response) {

		qrService.createQr(request, response);
	
	}
	
	
	//by 박은영
	//학생 - 입실,퇴실 등록
	@GetMapping("/attend/{courseId}/{date}")
	public String setAttendance(@PathVariable("courseId") Integer courseId, @PathVariable("date") String date, HttpSession session) {
		
		//Date
		String stringDate = date;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate localDate = LocalDate.parse(stringDate, formatter);

		//Course
		Course course = academyService.getCourse(courseId);		


		log.info("####localDate=" + localDate.toString()); 
		log.info("####강의명=" + course.getTitle()); 
		log.info("####강의설명=" + course.getDescription()); 

		//Member
		//session으로 안 할거면 이 부분 건드려야 돼
		String sessionMemberName = (String)session.getAttribute("Identity");

		Member member = memberService.getMember("aaa");
		Integer memberId = member.getId();
				
		Student student = memberService.getStudent(member, course);
		
		Attendance attendance = attendanceService.getTodayAttendance(student, localDate);
		
		if(attendance == null) {
			attendanceService.regAttendance(course, student, localDate);
			
		}else {
			attendanceService.regLeave(course, student, localDate);
		}

		return "redirect:/course/" + memberId + "/" + courseId;
	}

	//by 박은영
	//선생님 권한으로 학생 출결 정보 조회
	@GetMapping("/teacher/{courseId}/{date}")
	public String getAttendance(@PathVariable("courseId") Integer courseId, @PathVariable("date") String date,
			Model model1, Model model2, Model model3, Model model4) {
		
		//Date
		String stringDate = date;


		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		LocalDate localDate = LocalDate.parse(stringDate, formatter);
		
		model1.addAttribute("localDate", localDate);
		
		//Course
		Course course = academyService.getCourse(courseId);		
		model2.addAttribute("course", course);
		
		log.info("강의명=" + course.getTitle()); 

		//course로부터 학생 목록 가져오기
		List<Student> classStudentList = this.academyService.getStudentList(course);
		
		
		ArrayList<Member> studentMemberList = new ArrayList<Member>();
		ArrayList<Attendance> todayAttendanceList = new ArrayList<Attendance>();

		Map<Member, Attendance> map = new HashMap<Member, Attendance>();
		
		for(int i=0; i<classStudentList.size(); i++) {

			//student에서 member로 전환 (이름 가져오려고)
			Student student = classStudentList.get(i);
			Member member = student.getStudent();

						
			//"일별" 학생 출결 정보 가져오기
			Attendance attendance = attendanceService.getTodayAttendance(student, localDate);
			
			map.put(member, attendance);

		}
		model3.addAttribute("map", map);
		
		//---------------------------------------------------------------------//

		return "attendance";
	}
}
