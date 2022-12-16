package com.project.Projectwo.Controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.Projectwo.Entity.AcademyNotice;
import com.project.Projectwo.Entity.Attendance;
import com.project.Projectwo.Entity.Member;
import com.project.Projectwo.Entity.Student;
import com.project.Projectwo.Entity.Teacher;
import com.project.Projectwo.Repository.MemberRepository;
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
	private final MemberRepository memberRepository;
	static int classCnt = 0;

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

	@GetMapping("/saveToken")
	@ResponseBody
	public void saveToken(@RequestParam HashMap<Object, Object> params, Principal principal){
		String identity = principal.getName();		
		String token_data = (String)params.get("returnData");
		
		Member member = memberService.getMember(identity);
		if (token_data.equals("null") ) {
			System.out.println("token의 값이 null");

		}else {
			member.setToken(token_data);
			memberRepository.save(member);
		}
		System.out.println(token_data);

	}
    
}
