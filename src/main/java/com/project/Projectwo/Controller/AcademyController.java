package com.project.Projectwo.Controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.Projectwo.Entity.Course;
import com.project.Projectwo.Entity.Member;
import com.project.Projectwo.Entity.Room;
import com.project.Projectwo.Entity.Student;
import com.project.Projectwo.Entity.Teacher;
import com.project.Projectwo.Service.AcademyService;
import com.project.Projectwo.Service.MemberService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class AcademyController {
	
	private final MemberService memberService;
	private final AcademyService academyService;
    
    // by 안준언, Academy 페이지 class 영역 모든 수업 정보 출력 ajax
    @RequestMapping("/getAllCourse")
    @ResponseBody
    public List<Course> getAllCourse() {
    	List<Course> AllCourse = this.academyService.getAllCourse();
    	return AllCourse;
    }
    
    // by 안준언, academy 페이지 class 영역 각 수업의 대표강사 정보 출력을 위한 ajax
    @RequestMapping("/getClassTeacher")
    @ResponseBody
    public List<Teacher> getClassTeacher(@RequestParam HashMap<Object, Object> params) {
    	Course course = this.academyService.getCourse(Integer.parseInt((String)params.get("courseId")));
    	List<Teacher> classTeacherList = this.academyService.getTeacherList(course);
    	return classTeacherList;
    }
    
    // by 안준언, academy 페이지 class 영역 각 수업의 현재 수강인원 정보확인을 위한 ajax
    @RequestMapping("/getClassStudent")
    @ResponseBody
    public List<Student> getClassStudent(@RequestParam HashMap<Object, Object> params) {
    	Course course = this.academyService.getCourse(Integer.parseInt((String)params.get("courseId")));
    	List<Student> classStudentList = this.academyService.getStudentList(course);
    	return classStudentList;
    }
    
    // by 안준언, academy 페이지 teacher 영역 모든 강사 정보 출력 ajax
    @RequestMapping("/getAllTeacher")
    @ResponseBody
    public List<Member> getAllTeacher() {
    	List<Member> allTeacher = this.memberService.getAllMemberByRole("teacher");
    	return allTeacher;
    }
    
    // by 안준언, academy 페이지 teacher 영역 각 강사의 현재 담당하고 있는 수업 수 출력 ajax
    @RequestMapping("/getClassListOfTeacher")
    @ResponseBody
    public List<Teacher> getTeacherClass(@RequestParam HashMap<Object, Object> params) {
    	Member member = this.memberService.getMember(Integer.parseInt((String)params.get("memberId")));
    	List<Teacher> teacherClassList = this.academyService.getAllClassOfTeacher(member);
    	return teacherClassList;
    }
    
    // by 안준언, academy 페이지 student 영역 모든 학생 정보 출력 ajax
    @RequestMapping("/getAllStudent")
    @ResponseBody
    public List<Member> getAllStudent() {
    	List<Member> allStudent = this.memberService.getAllMemberByRole("student");
    	return allStudent;
    }
    
    // by 안준언, academy 페이지 student 영역 각 학생의 현재 수강중인 수업 수 출력 ajax
    @RequestMapping("/getClassListOfStudent")
    @ResponseBody
    public List<Student> getStudentClass(@RequestParam HashMap<Object, Object> params) {
    	Member member = this.memberService.getMember(Integer.parseInt((String)params.get("memberId")));
    	List<Student> studentClassList = this.academyService.getAllClassOfStudent(member);
    	return studentClassList;
    }
	
    // by 안준언, academy 페이지에서 강의 추가시 전체 강의실 정보 출력 ajax
    @RequestMapping("/getAllRoom")
    @ResponseBody
    public List<Room> getAllRoom() {
    	List<Room> RoomList = academyService.getAllRoom();
    	return RoomList;
    }
    
    // by 안준언, academy 페이지에서 강의 추가시, 특정 강의실에 따른 제한 정원 출력
    @RequestMapping("/getRoomByName")
    @ResponseBody
    public Room getRoom(@RequestParam HashMap<Object, Object> params) {
    	Room room = this.academyService.getRoom((String)params.get("roomName"));
    	return room;
    }
    
    // by 안준언, academy 페이지 강의 생성
    @PostMapping("/createCourse")
    @ResponseBody
    public void createCourse(@RequestParam HashMap<Object, Object> params) {
    	String title = (String)params.get("title");
    	String startDate = (String)params.get("startDate");
    	String endDate = (String)params.get("endDate");
    	String startTime = (String)params.get("startTime");
    	String endTime = (String)params.get("endTime");
    	String mon = (String)params.get("mon");
    	String tue = (String)params.get("tue");
    	String wed = (String)params.get("wed");
    	String thu = (String)params.get("thu");
    	String fri = (String)params.get("fri");
    	String sat = (String)params.get("sat");
    	String sun = (String)params.get("sun");
    	
    	String roomName = (String)params.get("roomName");
    	String teacherName = (String)params.get("teacherName");
    	
    	this.academyService.createCourseAndTeacher(title, mon, tue, wed, thu, fri, sat, sun,
    												startDate, endDate, startTime, endTime,
    												roomName, teacherName);
    }

    @PostMapping("/modifyCourse")
    @ResponseBody
    public void modifyCourse(@RequestParam HashMap<Object, Object> params) {
    	String courseId = (String)params.get("id");
    	String title = (String)params.get("title");
    	String startDate = (String)params.get("startDate");
    	String endDate = (String)params.get("endDate");
    	String startTime = (String)params.get("startTime");
    	String endTime = (String)params.get("endTime");
    	String mon = (String)params.get("mon");
    	String tue = (String)params.get("tue");
    	String wed = (String)params.get("wed");
    	String thu = (String)params.get("thu");
    	String fri = (String)params.get("fri");
    	String sat = (String)params.get("sat");
    	String sun = (String)params.get("sun");
    	
    	String roomName = (String)params.get("roomName");
    	String teacherName = (String)params.get("teacherName");
    	
    	this.academyService.modifyCourseAndTeacher(courseId, title, mon, tue, wed, thu, fri, sat, sun,
    												startDate, endDate, startTime, endTime,
    												roomName, teacherName);
    }
    
    // by 안준언, 강의 삭제
    @PostMapping("/deleteCourse")
    @ResponseBody
    public void deleteCourse(@RequestParam HashMap<Object, Object> params) {
    	this.academyService.deleteCourse(Integer.parseInt((String)params.get("courseId")));
    }

    // by 안준언, academy 페이지 강사 생성
    @PostMapping("/createTeacher")
    @ResponseBody
    public void createTeacher(@RequestParam HashMap<Object, Object> params) {
    	String name = (String)params.get("name");
    	String birth_date = (String)params.get("birth_date");
    	String tel = (String)params.get("tel");
    	String email = (String)params.get("email");
    	String address = (String)params.get("address");
    	
    	this.academyService.createTeacher(name, birth_date, tel, email, address);
    }
    
    // by 안준언, academy 페이지 강사 정보 수정
    @PostMapping("/modifyMember")
    @ResponseBody
    public void modifyMember(@RequestParam HashMap<Object, Object> params) {

    	String memberId = (String)params.get("memberId");
    	String name = (String)params.get("name");
    	String birth_date = (String)params.get("birth_date");
    	String tel = (String)params.get("tel");
    	String email = (String)params.get("email");
    	String address = (String)params.get("address");
    	
    	this.academyService.modifyMember(memberId, name, birth_date, tel, email, address);
    }
    
    // by 안준언, academy 페이지 학생 생성
    @PostMapping("/createStudent")
    @ResponseBody
    public void createStudent(@RequestParam HashMap<Object, Object> params) {
    	String name = (String)params.get("name");
    	String birth_date = (String)params.get("birth_date");
    	String tel = (String)params.get("tel");
    	String email = (String)params.get("email");
    	String address = (String)params.get("address");
    	
    	this.academyService.createStudent(name, birth_date, tel, email, address);
    }
    
    // by 안준언, academy 페이지, 학생,강사 정보 수정
    @PostMapping("/getMemberById")
    @ResponseBody
    public Member getStudent(@RequestParam HashMap<Object, Object> params) {
    	Integer memberId = Integer.parseInt((String)params.get("memberId"));
    	Member member = this.memberService.getMember(memberId);
    	
    	return member;
    }
    
	// by 안준언, academy 페이지, 학생,강사 삭제
	@PostMapping("/deleteMember")
	@ResponseBody
	public void deleteMember(@RequestParam HashMap<Object, Object> params) {
		this.academyService.deleteMember(Integer.parseInt((String)params.get("memberId")));
	}

    // by 안준언, academy 페이지 Course 수정 폼 정보 반환
    @PostMapping("/getCourseById")
    @ResponseBody
    public Course getCourseById(@RequestParam HashMap<Object, Object> params) {
    	Integer courseId = Integer.parseInt((String)params.get("courseId"));
    	Course course = this.academyService.getCourse(courseId);
    	
    	return course;
    }
    
	@PostMapping("/registCourse")
    @ResponseBody
    public void registCourse(@RequestParam HashMap<Object, Object> params) {
    	Member member = this.academyService.getMember(Integer.parseInt((String)params.get("memberId")));
    	Course course = this.academyService.getCourse((String)params.get("title"));
    	this.academyService.addStudent(member, course);
    }
}
