package com.project.Projectwo.QR;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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
	public String login(Model model) {
		
		LoginForm loginForm = new LoginForm();
		model.addAttribute("loginForm", loginForm);
		
		
		return "loginForm";
	}
	
	@PostMapping("/login")
	public String postLogin(@ModelAttribute("loginForm") LoginForm loginForm, HttpSession session) {
		
		Optional<Member> oMember = memberRepository.findByIdentityAndPassword(loginForm.getId(), loginForm.getPw());
		
		log.info("id: " + loginForm.getId());
		log.info("pw: " + loginForm.getPw());
		
		
		Member member = null;
		if(oMember.isPresent()) {
			member = oMember.get();
			
		}
		
		session.setAttribute("member", member);
		
		log.info("####LoginController의 member=" + member.getName());
		
		
		return "redirect:/main";
	}
	
	@GetMapping("/loginCheck")
	@ResponseBody
	public Member loginCheck(@RequestParam String id, @RequestParam String pw, HttpSession session) {

		Optional<Member> oMember = memberRepository.findByIdentityAndPassword(id, pw);
		
		log.info("id: " + id);
		log.info("pw: " + pw);
		
		
		Member member = null;
		if(oMember.isPresent()) {
			member = oMember.get();
			
		}
		
		session.setAttribute("member", member);
		
		log.info("####LoginController의 member=" + member.getName());
		
		return member;

	}
	
	@GetMapping("/checkMember")
	@ResponseBody
	public String checkMember(HttpSession session) {
		Member member = (Member)session.getAttribute("member");
		
		log.info("id: " + member.getIdentity());
		
		String name = member.getName();
		return name;
	}
	

}
