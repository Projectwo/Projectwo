package com.project.Projectwo.Controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.Projectwo.Entity.Member;
import com.project.Projectwo.Form.MemberCreateForm;
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
    
    @RequestMapping("/main")
    public String step(Principal principal, Model model) {
    	if(principal == null) {
    		return "redirect:/";
    	}
    	
    	Member member = this.memberService.getMember(principal.getName());
    	
    	if("admin".equalsIgnoreCase(member.getRole())) {
    		return "academy/academy_main";
    	} else {
    		model.addAttribute("member", member);
    		
    		return "member/member_step";
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

	@RequestMapping("/password/forgot")
	public String forgotPassword(){
		return "member/password_forgot";
	}

	@RequestMapping("/password/modify")
	public String modifyPassword(){
		return "member/password_modify";
	}

	@RequestMapping("/password/modify/complete")
	public String completeModifyPassword(){
		return "member/password_modify_complete";
	}
    
}
