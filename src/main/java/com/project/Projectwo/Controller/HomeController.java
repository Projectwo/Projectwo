package com.project.Projectwo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	// by 장유란, 임시매핑, 언제든 수정/이동/삭제가능
	@RequestMapping("/")
	public String index() {
		return "index";
	}

}
