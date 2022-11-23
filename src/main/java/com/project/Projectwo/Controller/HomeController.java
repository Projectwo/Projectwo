package com.project.Projectwo.Controller;

import javax.validation.Valid;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.Projectwo.Form.MemberCreateForm;
import com.project.Projectwo.Service.MemberService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class HomeController {
	
	private final MemberService memberService;

<<<<<<< HEAD
	// by 안준언, 회원 가입 (Get)
	@GetMapping
	public String signUp(MemberCreateForm memberCreateForm) {
		return "signup";
	}
	
	// by 안준언, 회원 가입(Post)
	@PostMapping
	public String signUp(@Valid MemberCreateForm memberCreateForm, 
							BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "signup";
=======
	// by 장유란, 임시매핑, 언제든 수정/이동/삭제가능
	// by 조성빈, 해당 매핑 그대로 fix
	@RequestMapping("/")
	public String index() {
		return "index";
	}

	// by 조성빈, 로그인 매핑(차후 get / post 로 수정 or security 적용 필요)
	@RequestMapping("/login")
	public String login(){
		return "member/login";
	}
	
	// by 안준언, 회원 가입 (Get)
	@GetMapping("/signup")
	public String signUp(MemberCreateForm memberCreateForm) {
		return "member/signup";
	}
	
	// by 안준언, 회원 가입(Post)
	@PostMapping("/signup")
	public String signUp(@Valid MemberCreateForm memberCreateForm, 
							BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "member/signup";
>>>>>>> dev
		}
		
		if(!memberCreateForm.getMember_pw1()
				.equals(memberCreateForm.getMember_pw2())) {
			bindingResult.rejectValue("member_pw2", "passwordInCorrect",
										"2개의 패스워드가 일치하지 않습니다.");
<<<<<<< HEAD
			return "signup";
=======
			return "member/signup";
>>>>>>> dev
		}
		
		try {
			memberService.create(memberCreateForm.getMember_id(), 
					memberCreateForm.getMember_pw1(), 
					memberCreateForm.getEmail(), 
					memberCreateForm.getName(), 
					memberCreateForm.getAge(), 
					memberCreateForm.getAddress(),
					memberCreateForm.getTel());
		} catch (DataIntegrityViolationException e) {
			e.printStackTrace();
			bindingResult.reject("signUpFailed", e.getMessage());
<<<<<<< HEAD
			
			return"signup";
		}
		
		return "redirect:/";
=======

			return"member/signup";
		}
		
		return "redirect:/login";
	}

	// by 조성빈, 아래부터는 임시 매핑(필요한 곳으로 가져가면 됨)
	@RequestMapping("/member/main")
	public String memberMain(){
		return "member/member_main";
>>>>>>> dev
	}

}
