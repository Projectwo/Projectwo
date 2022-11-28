package com.project.Projectwo.Controller;

import javax.validation.Valid;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;

import com.project.Projectwo.Form.MemberCreateForm;
import com.project.Projectwo.Service.AcademyService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class AcademyController {
	
	private final AcademyService academyService;
	
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
}
