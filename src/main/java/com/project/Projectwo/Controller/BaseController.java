package com.project.Projectwo.Controller;

import java.security.Principal;
import java.util.ArrayList;
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
import com.project.Projectwo.Entity.Student;
import com.project.Projectwo.Entity.Teacher;
<<<<<<< HEAD
import com.project.Projectwo.Form.MemberCreateForm;
=======
>>>>>>> 5430818493af1d974b970b0909faafc881f323a4
import com.project.Projectwo.Service.AcademyService;
import com.project.Projectwo.Service.MemberService;

import lombok.RequiredArgsConstructor;

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
    	if(principal == null) {
    		return "redirect:/";
    	}
    	
    	Member member = this.memberService.getMember(principal.getName());
    	
    	System.out.println(principal.getName());
    	if("admin".equalsIgnoreCase(member.getRole())) {
    		return "academy/academy_main";
    	} else {
    		return "member/member_main";
    	}
    }
    
<<<<<<< HEAD
    @GetMapping("/signup")
	public String signup(MemberCreateForm memberCreateForm) {
		return "signup_form";
	}
	
	public String signup(@Valid MemberCreateForm memberCreateForm,
							BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "signup_form";
		}
		
		if(!memberCreateForm.getPassword1().equals(memberCreateForm.getPassword2())) {
			bindingResult.rejectValue("password2", "passwordIncorrect",
										"2개의 비밀번호가 일치하지 않습니다.");
			return "signup_form";
		}
		
		try {
			academyService.createMember(memberCreateForm.getIdentity(), memberCreateForm.getPassword1(),
					memberCreateForm.getName(), memberCreateForm.getEmail(),
					memberCreateForm.getBirth_date(), memberCreateForm.getAddress(),
					memberCreateForm.getTel());
		} catch(DataIntegrityViolationException e) {
			e.printStackTrace();
			bindingResult.reject("signupFailed", "이미 등록된 사용자입니다.");
			return "signup_form";
		} catch(Exception e) {
			e.printStackTrace();
			bindingResult.reject("signupFailed", e.getMessage());
			return "signup_form";
		}
		
		return "redirect:/";
	}
=======
    @RequestMapping("/getAllCourse")
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
    	List<Member> allTeacher = this.memberService.getAllMemberByRole("teacher");
    	return allTeacher;
    }
    
    @RequestMapping("/getAllStudent")
    @ResponseBody
    public List<Member> getAllStudent() {
    	List<Member> allStudent = this.memberService.getAllMemberByRole("student");
    	return allStudent;
    }
    
    @RequestMapping("/getClassListOfTeacher")
    @ResponseBody
    public List<Teacher> getTeacherClass(@RequestParam HashMap<Object, Object> params) {
    	Member member = this.memberService.getMember(Integer.parseInt((String)params.get("memberId")));
    	List<Teacher> teacherClassList = this.academyService.getAllClassOfTeacher(member);
    	return teacherClassList;
    }
    @RequestMapping("/getClassListOfStudent")
    @ResponseBody
    public List<Student> getStudentClass(@RequestParam HashMap<Object, Object> params) {
    	Member member = this.memberService.getMember(Integer.parseInt((String)params.get("memberId")));
    	List<Student> studentClassList = this.academyService.getAllClassOfStudent(member);
    	return studentClassList;
    }
>>>>>>> 5430818493af1d974b970b0909faafc881f323a4
    
}
