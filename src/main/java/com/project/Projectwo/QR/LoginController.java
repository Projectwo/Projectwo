package com.project.Projectwo.QR;

import java.util.Optional;

import org.springframework.http.HttpHeaders;
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
	public Member loginCheck(@RequestParam String id, @RequestParam String pw) {

		Optional<Member> oMember = memberRepository.findByMemberId(id);
		
		Member member = null;
		if(oMember.isPresent()) {
			member = oMember.get();
			String token = jwtUtil.createJwt(id);
			
			log.info("token: " + token);
		}
		
		log.info("id: " + id);
		log.info("pw: " + pw);
		
		return member;

	}
}
