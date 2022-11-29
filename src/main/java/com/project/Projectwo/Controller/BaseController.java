package com.project.Projectwo.Controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.Projectwo.Entity.Member;
import com.project.Projectwo.Service.MemberService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class BaseController {
    
	private final MemberService memberService;
	
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
    
    @RequestMapping("/step")
    public String step(Principal principal) {
    	Member member = this.memberService.getMember(principal.getName());
    	System.out.println(principal.getName());
    	
    	if("teacher".equalsIgnoreCase(member.getRole())) {
    		return "member/member_main";
    	} else {
    		return "academy/academy_main";
    	}
    	
    }
    
}
