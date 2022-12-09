package com.project.Projectwo.Controller;

import org.springframework.stereotype.Controller;

import com.project.Projectwo.Service.AcademyService;
import com.project.Projectwo.Service.MemberService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class MemberController {
	
	private final MemberService memberService;
	private final AcademyService academyService;
}
