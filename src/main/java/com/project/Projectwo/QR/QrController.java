package com.project.Projectwo.QR;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.Projectwo.Entity.Attendance;
import com.project.Projectwo.Entity.Course;
import com.project.Projectwo.Entity.Member;
import com.project.Projectwo.Entity.Student;
import com.project.Projectwo.Entity.Teacher;
import com.project.Projectwo.Repository.AttendanceRepository;
import com.project.Projectwo.Repository.CourseRepository;
import com.project.Projectwo.Repository.StudentRepository;
import com.project.Projectwo.Repository.TeacherRepository;
import com.project.Projectwo.Service.AttendanceService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
public class QrController {
	
	private final TeacherRepository teacherRepository;
	private final StudentRepository studentRepository;
	private final CourseRepository courseRepository;
	private final AttendanceRepository attendanceRepository;
	
	private final AttendanceService attendanceService;
	
	@GetMapping("/main")
	public String main() {
		return "main";
	}
	
	@GetMapping("/test")
	public String test() {
		return "test";
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
	
	
	@GetMapping("/createQr")
	public void createQr(HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {

		QrCodeView qrCodeView = new QrCodeView();

		Member member = (Member)session.getAttribute("member");
		
		
		
		
		Teacher teacher = null;
		if(oTeacher.isPresent()) {
			teacher = oTeacher.get();
		}
		
		String ip = GetIp.getIp();

		LocalDateTime localDate = LocalDateTime.now();
		String stringDate = localDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
		
		log.info(stringDate);

		Course course = teacher.getCourse();
		Integer courseId = course.getId();
		
		String content = "http://" + ip + "/lecture/" + courseId + "/" + stringDate;
		
		log.info(content);
		
		Map<String, Object> model = new HashMap<>();
		
		
		model.put("content", content);
		try {
			qrCodeView.renderMergedOutputModel(model, request, response);
		
		}catch(Exception e) {
			e.printStackTrace();
		}
	
	}
	
	@GetMapping("/lecture/{courseId}/{date}")
	@ResponseBody
	public String getAttendance(@PathVariable("courseId") Integer courseId, @PathVariable("date") String date,
			HttpSession session, Model model1, Model model2) {
		
		//Lecture
		Optional<Course> oCourse = courseRepository.findById(courseId);
		

		Course course = null;		
		if(oCourse.isPresent()) {
			course = oCourse.get();
		}

		//Member
		Member member = (Member)session.getAttribute("member");
		
		//선생님 - 학생들의 출석 상태 조회
		List<Student> studentList = course.getStudentList();
		for(int i=0; i<studentList.size(); i++) {
			
		}
		
		//학생 - 입실/퇴실 처리
		Integer st = studentRepository.findById(member);
		
		Student student = null;
//		if(oStudent.isPresent()) {
//			student = oStudent.get();
//		}
		
	
		//입실
		Attendance attendance = new Attendance();
		attendance.setStudent(student);
		attendance.setToday(LocalDate.now());
		attendance.setInTime(LocalTime.now());
		//attendance.setStatus();
		
		//퇴실
		//course의 startTime과 비교해서 출석 상태 입력 
		//Attendance attendance = AttendanceRepository
		
		
		
		log.info(course.getTitle());
		
		
		
		model1.addAttribute("courseId", courseId);
		model2.addAttribute("date", date);
		log.info("lecture: " + courseId);
		log.info("date: " + date);
		
		return "attendance";
	}
	
	
	@GetMapping("/attendance")
	public String attendance() {

		return "attendance";
	}
	
	

}
