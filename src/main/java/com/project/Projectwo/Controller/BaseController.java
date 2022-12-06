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

import com.mysql.cj.Session;
import com.project.Projectwo.Entity.Course;
import com.project.Projectwo.Entity.Member;
import com.project.Projectwo.Entity.Student;
import com.project.Projectwo.Entity.Teacher;
import com.project.Projectwo.Form.MemberCreateForm;
import com.project.Projectwo.Service.AcademyService;
import com.project.Projectwo.Service.MemberService;

import groovyjarjarpicocli.CommandLine.Model;
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
    public String step(Principal principal, Model model) {
    	if(principal == null) {
    		return "redirect:/";
    	}
    	
    	Member member = this.memberService.getMember(principal.getName());
    	
    	System.out.println(principal.getName());
    	System.out.println(member.getId());
    	
    	if("admin".equalsIgnoreCase(member.getRole())) {
    		return "academy/academy_main";
    	} else {
    		return "member/member_main";
    	}
    }
    
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
	
	
	// by 장유란, 출석처리(임시)
	//@GetMapping("/course/{courseId}/{date}")으로 매핑 변경필요
	@RequestMapping("/attend")
	public String attend(Model model, @RequestParam(value="UserIdx", required=false) Integer UserIdx){
		System.out.println("app에서 받아온 UserIdx : "+UserIdx);
		///attend?UserIdx=1
		//멤버id로 멤버 특정해서 출석처리
		return "member/member_main";
	}
    
	// by 조성빈, 강의 상세 템플릿 임시 매핑
	@RequestMapping("/lecture/detail")
	public String lectureDetail(){
		return "lecture/lecture_detail";
	}
}
