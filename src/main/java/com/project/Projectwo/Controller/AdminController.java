package com.project.Projectwo.Controller;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.Projectwo.Entity.Lecture;
import com.project.Projectwo.Form.LectureForm;
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
	
	// by 장유란, 강의개설
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
	
	@GetMapping("/lectureModify/{id}")
	public String lectureModify(LectureForm lectureForm) {
		return "admin/lecture";
	}
	
	@PostMapping("/lectureModify/{id}")
	public String lectureModify(@Valid LectureForm lectureForm, @PathVariable("id") Integer id) {
		Lecture lecture = lectureRepository.getById(id);
		System.out.println(lectureForm.getLectureDesc());

		lecture.setLectureName(lectureForm.getLectureName());
		lecture.setLectureDesc(lectureForm.getLectureDesc());
		lecture.setStartDate(LocalDate.parse(lectureForm.getStartDate()));
		lecture.setEndDate(LocalDate.parse(lectureForm.getEndDate()));
		lectureRepository.save(lecture);

		return "redirect:/admin/lecture";
	}
	
	// by 장유란, 강의삭제
	@GetMapping("/lectureDelete/{id}")
	public String languageDelete(@PathVariable("id") Integer id) {
		this.lectureRepository.deleteById(id);
		return "redirect:/admin/lecture";
	}
	
}
