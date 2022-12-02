package com.project.Projectwo.Controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.Projectwo.Entity.Course;
import com.project.Projectwo.Entity.Member;
import com.project.Projectwo.Entity.Room;
import com.project.Projectwo.Entity.Student;
import com.project.Projectwo.Entity.Teacher;
import com.project.Projectwo.Form.MemberCreateForm;
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
	
    // by 안준언, academy 페이지에서 강의 추가시 강의실 정보 출력 ajax
    @RequestMapping("/getAllRoom")
    @ResponseBody
    public List<Room> getAllRoom() {
    	List<Room> RoomList = academyService.getAllRoom();
    	return RoomList;
    }
}
