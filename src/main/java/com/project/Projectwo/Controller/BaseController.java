package com.project.Projectwo.Controller;

import java.security.Principal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mysql.cj.protocol.x.Notice;
import com.project.Projectwo.Entity.AcademyNotice;
import com.project.Projectwo.Entity.AcademyNoticeCheck;
import com.project.Projectwo.Entity.Attendance;
import com.project.Projectwo.Entity.Course;
import com.project.Projectwo.Entity.Member;
import com.project.Projectwo.Entity.Student;
import com.project.Projectwo.Entity.Teacher;
import com.project.Projectwo.Form.MemberCreateForm;
import com.project.Projectwo.Repository.CourseRepository;
import com.project.Projectwo.Repository.StudentRepository;
import com.project.Projectwo.Service.AcademyService;
import com.project.Projectwo.Service.MemberService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class BaseController {
    
	private final MemberService memberService;
	private final AcademyService academyService;
	private final StudentRepository studentRepository;
	static int classCnt = 0;
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
    public String step(Principal principal, Model model) {
    	if(principal == null) {
    		return "redirect:/";
    	}
    	
    	Member member = this.memberService.getMember(principal.getName());
    	
    	// by 장유란, member_main에서 member명, 강의리스트, 전체공지 출력
    	List<AcademyNotice> academyNotices = academyService.getAllAcademyNotice();
    	model.addAttribute("today", (LocalDate.now().format(DateTimeFormatter.ofPattern("MM/dd"))));

    	System.out.println(principal.getName());

    	model.addAttribute("member", member);
    	model.addAttribute("academyNotices", academyNotices);
    	
    	if("admin".equalsIgnoreCase(member.getRole())) {
    		return "academy/academy_main";
    	} else {
    		if(member.getRole().equals("student")) {
    			List<Student> student = studentRepository.findByStudent(member);
    			if(student.size()>0) {
        			List<Attendance> attendanceList= student.get(classCnt).getAttendanceList();
        			model.addAttribute("classCnt", classCnt);
        	    	model.addAttribute("attendanceList", attendanceList);
    			}
    	    	List<Student> studentClassList = member.getStudentClassList();
    	    	model.addAttribute("classList", studentClassList);
    	    	
    		}else if(member.getRole().equals("teacher")){
    	    	List<Teacher> teacherClassList = member.getTeacherClassList();
    	    	model.addAttribute("classList", teacherClassList);
    		}
    		return "member/member_main";
    	}
    }
    
    // @GetMapping("/signup")
	// public String signup(MemberCreateForm memberCreateForm) {
	// 	return "signup_form";
	// }
	
	// public String signup(@Valid MemberCreateForm memberCreateForm,
	// 						BindingResult bindingResult) {
	// 	if(bindingResult.hasErrors()) {
	// 		return "signup_form";
	// 	}
		
	// 	if(!memberCreateForm.getPassword1().equals(memberCreateForm.getPassword2())) {
	// 		bindingResult.rejectValue("password2", "passwordIncorrect",
	// 									"2개의 비밀번호가 일치하지 않습니다.");
	// 		return "signup_form";
	// 	}
		
	// 	try {
	// 		academyService.createMember(memberCreateForm.getIdentity(), memberCreateForm.getPassword1(),
	// 				memberCreateForm.getName(), memberCreateForm.getEmail(),
	// 				memberCreateForm.getBirth_date(), memberCreateForm.getAddress(),
	// 				memberCreateForm.getTel());
	// 	} catch(DataIntegrityViolationException e) {
	// 		e.printStackTrace();
	// 		bindingResult.reject("signupFailed", "이미 등록된 사용자입니다.");
	// 		return "signup_form";
	// 	} catch(Exception e) {
	// 		e.printStackTrace();
	// 		bindingResult.reject("signupFailed", e.getMessage());
	// 		return "signup_form";
	// 	}

	// 	return "redirect:/";
	// }

	@RequestMapping("/main/lecture/detail")
	public String lectureDetail(Principal principal, Model model){

		Member member = this.memberService.getMember(principal.getName());

		List<AcademyNotice> academyNotices = academyService.getAllAcademyNotice();
		model.addAttribute("academyNotices", academyNotices);

		if(member.getRole().equals("student")) {
			List<Student> student = studentRepository.findByStudent(member);
			List<Attendance> attendanceList= student.get(classCnt).getAttendanceList();
			List<Student> studentClassList = member.getStudentClassList();
			model.addAttribute("classList", studentClassList);
			model.addAttribute("classCnt", classCnt);
			model.addAttribute("attendanceList", attendanceList);
			
		}else if(member.getRole().equals("teacher")){
			List<Teacher> teacherClassList = member.getTeacherClassList();
			model.addAttribute("classList", teacherClassList);
		}

		return "lecture/lecture_detail";
	}

	@RequestMapping("/main/lecture/check/student")
	public String studentCheck(){
		return "member/student_check";
	}

	@RequestMapping("/main/lecture/check/teacher")
	public String teacherCheck(){
		return "member/teacher_check";
	}

	@RequestMapping("/main/password/modify")
	public String passwordModify(){
		return "member/password_modify";
	}

	@RequestMapping("/main/password/modify/complete")
	public String passwordModifyComplete(){
		return "member/password_modify_complete";
	}
	
	@RequestMapping("/password/forgot")
	public String passwordForgot(){
		return "member/password_forgot";
	}

	@GetMapping("/saveToken")
	@ResponseBody
	public String saveToken(){
		System.out.println("token DB저장용 메소드");
		return "token 저장";
	}
    
}
