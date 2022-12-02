package com.project.Projectwo.Controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
public class BaseController {
    
	private final MemberService memberService;
	private final AcademyService academyService;
	
    @RequestMapping("/")
    public String root(){
        return "member/login";
    }

    @RequestMapping("/member/main")
    public String studentMain(){
        return "member/member_main";
    }

    @RequestMapping("/academy/main")
    public String academyMain(){
        return "academy/academy_main";
    }
    
    @RequestMapping("/main")
    public String step(Principal principal) {
    	Member member = this.memberService.getMember(principal.getName());
    	System.out.println(principal.getName());
    	if("admin".equalsIgnoreCase(member.getRole())) {
    		return "academy/academy_main";
    	} else {
    		return "member/member_main";
    	}
    }
    
    @RequestMapping("/getCourse")
    @ResponseBody
    public List<Course> getAllCourse() {
    	List<Course> AllCourse = this.academyService.getAllCourse();
    	return AllCourse;
    }
    
    @RequestMapping("/getClassTeacher")
    @ResponseBody
    public List<Teacher> getClassTeacher(@RequestParam HashMap<Object, Object> params) {
    	Course course = this.academyService.getCourse(Integer.parseInt((String)params.get("courseId")));
    	List<Teacher> classTeacherList = this.academyService.getTeacherList(course);
    	return classTeacherList;
    }

    @RequestMapping("/getClassStudent")
    @ResponseBody
    public List<Student> getClassStudent(@RequestParam HashMap<Object, Object> params) {
    	Course course = this.academyService.getCourse(Integer.parseInt((String)params.get("courseId")));
    	List<Student> classStudentList = this.academyService.getStudentList(course);
    	return classStudentList;
    }

    @RequestMapping("/getAllTeacher")
    @ResponseBody
    public List<Member> getAllTeacher() {
    	List<Member> allTeacher = this.memberService.getAllTeacher();
    	return allTeacher;
    }
    
    @RequestMapping("/getClassListOfTeacher")
    @ResponseBody
    public List<Teacher> getTeacherClass(@RequestParam HashMap<Object, Object> params) {
    	Member member = this.memberService.getMember(Integer.parseInt((String)params.get("memberId")));
    	List<Teacher> teacherClassList = this.academyService.getAllClassOfTeacher(member);
    	return teacherClassList;
    }
    
}
