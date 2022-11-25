package com.project.Projectwo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class HomeController {
	
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
	
	// by 조성빈, 아래부터는 임시 매핑(필요한 곳으로 가져가면 됨)
	@RequestMapping("/member/main")
	public String memberMain(){
		return "member/member_main";
	}

}
