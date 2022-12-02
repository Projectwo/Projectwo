package com.project.Projectwo.QR;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.project.Projectwo.Entity.Attendance;
import com.project.Projectwo.Entity.Course;
import com.project.Projectwo.Entity.Member;
import com.project.Projectwo.Entity.Student;
import com.project.Projectwo.Repository.AttendanceRepository;
import com.project.Projectwo.Repository.CourseRepository;
import com.project.Projectwo.Repository.StudentRepository;
import com.project.Projectwo.Service.AcademyService;
import com.project.Projectwo.Service.AttendanceService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Controller
public class QrController {
	
	private final CourseRepository courseRepository;
	private final AttendanceRepository attendanceRepository;
	private final StudentRepository studentRepository;
	private final AcademyService academyService;
	private final AttendanceService attendanceService;
	
	@GetMapping("/test")
	public String getTest() {
		return "main";
	}

	@GetMapping("/cam")
	public String getCam() {
		return "cam";
	}
	
	@GetMapping("/cam2")
	public String getCam2() {
		return "cam2";
	}

//	@GetMapping("checkToken")
//	@ResponseBody
//	public String checkToken(@RequestParam String token) {
//		
//		log.info("token: " + token);
//		
//		String result = null;
//		try {
//			result = JwtUtil.getSubject(token);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		return result;
//	}

	//by 박은영
	//"http://ip:9090/course/{courseId}/{LocalDate}로 qr생성
	//TODO: courseId 특정 지어서 가져와야 됨
	@GetMapping("/createQr")
	public void createQr(HttpServletRequest request, HttpServletResponse response) {

		QrCodeView qrCodeView = new QrCodeView();
		
		String ip = GetIp.getIp();

		LocalDateTime localDate = LocalDateTime.now();
		String stringDate = localDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
		
		log.info(stringDate);
		
		
		//TODO: session로 teacher, course 특정지어서 강의 하나 가져오기
		Integer lecture = 1;
		String content = "http://" + ip + ":9090/course/" + lecture + "/" + stringDate;
		
		log.info(content);
		
		Map<String, Object> model = new HashMap<>();
		
		
		model.put("content", content);
		try {
			qrCodeView.renderMergedOutputModel(model, request, response);
		
		}catch(Exception e) {
			e.printStackTrace();
		}
	
	}
	
	
	//by 박은영
	//학생 - 입실,퇴실 등록
//	@GetMapping("/course/{courseId}/{date}")
//	public String setAttendance(@PathVariable("courseId") Integer courseId, @PathVariable("date") String date,
//			HttpSession session, Model model1, Model model2, Model model3) {
//		
//		//Date
//		model1.addAttribute("date", date);
//		
//		//Course
//		Course course = academyService.getCourse(courseId);		
//		model2.addAttribute("course", course);
//		
//		log.info("강의명=" + course.getTitle()); 
//		
//		
//		
//
//		//선생님 - 학생들의 출석 상태 조회
//		List<Student> classStudentList = this.academyService.getStudentList(course);
//		
//		//학생 이름 출력
//		ArrayList<Member> studentMemberList = new ArrayList<Member>();
//		
//		for(int i=0; i<classStudentList.size(); i++) {
//
//			Student student = classStudentList.get(i);
//			
//			//학생 이름 가져오기
//			Member member = student.getStudent();
//			studentMemberList.add(i, member);
//			
//			//학생 출결 정보 가져오기
//			Attendance attendance = attendanceService.getTodayAttendance(student);
//			
//			
//			
//			
//
//			
//			log.info("studentNameList[" + i + "]'s name= " + member.getName());
//			
//		}
//		
//		model3.addAttribute("studentMemberList", studentMemberList);
//
//		//TODO: 학생 출결 정보 출력
//
//		
//		
//		
//		
//		
//		
//		//학생 - 입실/퇴실 처리
//		Member sessionMember = (Member)session.getAttribute("member");
//		
//		log.info(("member=" + sessionMember.getName()));
//		
//		Optional<Student> oStudent = studentRepository.findByStudent(sessionMember);
//		
//		Student student = null;
//		if(oStudent.isPresent()) {
//			student = oStudent.get();
//		}
//		
//		log.info("student=" + student.getStudent().getName());
//
//		//입실
//		attendanceService.regAttendance(course, student);
//		
//		
//		
//		
//		
////		//퇴실
////		//course의 startTime과 비교해서 출석 상태 입력 
////		//Attendance attendance = AttendanceRepository
////		
////		
////			
//
//		return "attendance";
//	}
//	
//	
	//선생님 권한으로 학생 출결 정보 조회
	@GetMapping("/course/{courseId}/{date}")
	public String getAttendance(@PathVariable("courseId") Integer courseId, @PathVariable("date") String date,
			Model model1, Model model2, Model model3, Model model4) {
		
		//Date
		model1.addAttribute("date", date);
		
		//Course
		Course course = academyService.getCourse(courseId);		
		model2.addAttribute("course", course);
		
		log.info("강의명=" + course.getTitle()); 
		
		//course로부터 학생 목록 가져오기
		List<Student> classStudentList = this.academyService.getStudentList(course);
		
		
		ArrayList<Member> studentMemberList = new ArrayList<Member>();
		ArrayList<Attendance> todayAttendanceList = new ArrayList<Attendance>();
		
		for(int i=0; i<classStudentList.size(); i++) {

			Student student = classStudentList.get(i);
			
			//student에서 member로 전환 (이름 가져오려고)
			Member member = student.getStudent();
			studentMemberList.add(i, member);
			
			//"당일" 학생 출결 정보 가져오기
			Attendance attendance = attendanceService.getTodayAttendance(student);
			todayAttendanceList.add(i, attendance);
			
			log.info("studentNameList[" + i + "]'s name= " + member.getName());
			log.info("todayAttendanceList[" + i + "]'s status= " + attendance.getStatus());
			
		}
		
		model3.addAttribute("studentMemberList", studentMemberList);
		model4.addAttribute("todayAttendanceList", todayAttendanceList);
		
		return "attendance";
	}
}
