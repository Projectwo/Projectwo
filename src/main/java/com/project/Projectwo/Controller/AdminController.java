package com.project.Projectwo.Controller;

import java.time.LocalDate;
<<<<<<< HEAD
import java.util.HashMap;
import java.util.List;

import org.aspectj.weaver.NewConstructorTypeMunger;
=======
import java.util.List;

>>>>>>> dev
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.Projectwo.Entity.Lecture;
<<<<<<< HEAD
import com.project.Projectwo.Form.LectureForm;
=======
>>>>>>> dev
import com.project.Projectwo.Repository.LectureRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/admin")
@Controller
public class AdminController {

	private final LectureRepository lectureRepository;
	
	// by 장유란, 관리리스트 더미
	@GetMapping("")
	public String admin(Model model) {
		return "admin/admin_list";
	}
	
	@GetMapping("/lecture")
	public String lectureList(Model model) {
		List<Lecture> lectureList = lectureRepository.findAll();
        model.addAttribute("lectureList", lectureList);
		return "admin/lecture";
	}
<<<<<<< HEAD
	
=======

>>>>>>> dev
	// by 장유란, 강의 개설
	@PostMapping("/createLecture")
	public String createLecture(String lectureName, String lectureDesc, String startDate, String endDate) {
		System.out.println(startDate + " - " + endDate);
		
		Lecture lecture = new Lecture();
		lecture.setLectureName(lectureName);
		lecture.setLectureDesc(lectureDesc);
		lecture.setStartDate(LocalDate.parse(startDate));
		lecture.setEndDate(LocalDate.parse(endDate));
		
		lectureRepository.save(lecture);
		return "redirect:/admin/lecture";
	}
	
<<<<<<< HEAD
	
=======
>>>>>>> dev
	// by 장유란, 강의 수정
	@GetMapping("/lectureModify/{id}")
	@ResponseBody
	public void lectureModify(@RequestParam Integer lectureId, @RequestParam String lectureName, 
			@RequestParam String lectureDesc, @RequestParam String startDate, @RequestParam String endDate) {
		
		Lecture lecture = lectureRepository.getById(lectureId);
		System.out.println(lecture.getLectureName());
		
		lecture.setLectureName(lectureName);
		lecture.setLectureDesc(lectureDesc);
		lecture.setStartDate(LocalDate.parse(startDate));
		lecture.setEndDate(LocalDate.parse(endDate));
		lectureRepository.save(lecture);

	}

	
	// by 장유란, 강의 삭제
	@GetMapping("/lectureDelete/{id}")
	public String languageDelete(@PathVariable("id") Integer id) {
		this.lectureRepository.deleteById(id);
		return "redirect:/admin/lecture";
	}
	
}
