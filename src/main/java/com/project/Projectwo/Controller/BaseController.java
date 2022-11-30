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
    
//    @RequestMapping("/getCourse")
//    public List<Course> getAllCourse(){
//    	List<Course> listCourse = ;
//    	return listCourse;
//    }
    
}
