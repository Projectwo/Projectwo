package com.project.Projectwo.Controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminController {
	
	@RequestMapping("/step")
	public String step() {
		return "academy/academy_main";
	}
}
