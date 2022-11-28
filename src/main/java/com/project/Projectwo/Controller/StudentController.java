package com.project.Projectwo.Controller;

<<<<<<< HEAD
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.project.Projectwo.Entity.Lecture;
import com.project.Projectwo.Service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
public class StudentController {
	
	private final MemberService memberService;
	
	//박은영
	//내 강의 목록
	@GetMapping("/student/lectureList")
	public String getLectures(Model model) { //HttpSession session 
		
		List<Lecture> lectures = memberService.getLectureList();
		model.addAttribute("lectures", lectures);
		
		return "lecture/lectures";
		
	}
	
=======
import org.springframework.stereotype.Controller;

@Controller
public class StudentController {

>>>>>>> main
}
