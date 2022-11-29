package com.project.Projectwo.QR;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.Projectwo.Entity.Member;
import com.project.Projectwo.Repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Controller
public class LoginController {
	
	private final MemberRepository memberRepository;
	
	JwtUtil jwtUtil = new JwtUtil();
	
	@GetMapping("/login")
	public String login(LoginForm loginForm) {
		
		return "loginForm";
	}
	
	@GetMapping("/loginCheck")
	@ResponseBody
	public Member loginCheck(@RequestParam String id, @RequestParam String pw, HttpSession session) {

		Optional<Member> oMember = memberRepository.findByMemberIdAndMemberPw(id, pw);
		
		log.info("id: " + id);
		log.info("pw: " + pw);
		
		
		Member member = null;
		if(oMember.isPresent()) {
			member = oMember.get();
			String token = jwtUtil.createJwt(id);
			
			log.info("token: " + token);
			
			session.setAttribute("member", member);
			
		}
		
		return member;

	}
	
	@GetMapping("/checkMember")
	@ResponseBody
	public String checkMember(HttpSession session) {
		Member member = (Member)session.getAttribute("member");
		
		log.info("id: " + member.getMemberId());
		
		String name = member.getName();
		return name;
	}
}
