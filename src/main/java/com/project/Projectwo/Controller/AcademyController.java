package com.project.Projectwo.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.Projectwo.Entity.AcademyNotice;
import com.project.Projectwo.Form.AcademyNoticeForm;
import com.project.Projectwo.Service.AcademyService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/academy")
@RequiredArgsConstructor
@Controller
public class AcademyController {

	private final AcademyService academyService;
	
	// by 장유란, Service의 목록 model로 html에 전달, 게시글 권한 없으므로 변동가능성 많음
	@GetMapping(value = "/notice/list")
	public String noticeList(Model model) {
        List<AcademyNotice> noticeList = academyService.getList();
        model.addAttribute("noticeList", noticeList);
		return "academy/notice_list";
	}
	
    @RequestMapping(value = "/notice/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id) {
        return "academy/notice_detail";
    }
    
    @RequestMapping(value = "/notice/create")
    public String noticeCreate(AcademyNoticeForm academyNoticeForm) {
    	return "academy/notice_form";
    }
    
    @PostMapping(value = "/notice/create")
    public String noticeCreate(@Valid AcademyNoticeForm academyNoticeForm, BindingResult bindingResult) {
    	if (bindingResult.hasErrors()) {
    		System.out.println("1");
            return "academy/notice_form";
        }
    	
    	this.academyService.create(academyNoticeForm.getTitle(), academyNoticeForm.getContent());
        return "redirect:/academy/notice/list";
    }
}
