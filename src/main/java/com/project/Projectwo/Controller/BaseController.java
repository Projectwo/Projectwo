package com.project.Projectwo.Controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.Projectwo.Entity.AcademyNotice;
import com.project.Projectwo.Entity.Attendance;
import com.project.Projectwo.Entity.Member;
import com.project.Projectwo.Entity.Student;
import com.project.Projectwo.Entity.Teacher;
import com.project.Projectwo.Repository.MemberRepository;
import com.project.Projectwo.Repository.StudentRepository;
import com.project.Projectwo.Service.AcademyService;
import com.project.Projectwo.Service.MemberService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class BaseController {
    
	private final MemberService memberService;
	private final AcademyService academyService;
	private final StudentRepository studentRepository;
	private final MemberRepository memberRepository;
	static int classCnt = 0;
	@RequestMapping("/")
    public String root(){
        return "member/login";
    }
    
    @RequestMapping("/main")
    public String step(Principal principal, Model model) {
    	if(principal == null) {
    		return "redirect:/";
    	}
    	
    	Member member = this.memberService.getMember(principal.getName());
    	
    	if("admin".equalsIgnoreCase(member.getRole())) {
    		return "academy/academy_main";
    	} else {
    		model.addAttribute("member", member);
    		
    		return "member/member_step";
    	}
    }
    
    // @GetMapping("/signup")
	// public String signup(MemberCreateForm memberCreateForm) {
	// 	return "signup_form";
	// }
	
	// public String signup(@Valid MemberCreateForm memberCreateForm,
	// 						BindingResult bindingResult) {
	// 	if(bindingResult.hasErrors()) {
	// 		return "signup_form";
	// 	}
		
	// 	if(!memberCreateForm.getPassword1().equals(memberCreateForm.getPassword2())) {
	// 		bindingResult.rejectValue("password2", "passwordIncorrect",
	// 									"2개의 비밀번호가 일치하지 않습니다.");
	// 		return "signup_form";
	// 	}
		
	// 	try {
	// 		academyService.createMember(memberCreateForm.getIdentity(), memberCreateForm.getPassword1(),
	// 				memberCreateForm.getName(), memberCreateForm.getEmail(),
	// 				memberCreateForm.getBirth_date(), memberCreateForm.getAddress(),
	// 				memberCreateForm.getTel());
	// 	} catch(DataIntegrityViolationException e) {
	// 		e.printStackTrace();
	// 		bindingResult.reject("signupFailed", "이미 등록된 사용자입니다.");
	// 		return "signup_form";
	// 	} catch(Exception e) {
	// 		e.printStackTrace();
	// 		bindingResult.reject("signupFailed", e.getMessage());
	// 		return "signup_form";
	// 	}

	// 	return "redirect:/";
	// }

	@RequestMapping("/main/lecture/detail")
	public String lectureDetail(Principal principal, Model model){

		Member member = this.memberService.getMember(principal.getName());

		List<AcademyNotice> academyNotices = academyService.getAllAcademyNotice();
		model.addAttribute("academyNotices", academyNotices);

		if(member.getRole().equals("student")) {
			List<Student> student = studentRepository.findByStudent(member);
			List<Attendance> attendanceList= student.get(classCnt).getAttendanceList();
			List<Student> studentClassList = member.getStudentClassList();
			model.addAttribute("classList", studentClassList);
			model.addAttribute("classCnt", classCnt);
			model.addAttribute("attendanceList", attendanceList);
			
		}else if(member.getRole().equals("teacher")){
			List<Teacher> teacherClassList = member.getTeacherClassList();
			model.addAttribute("classList", teacherClassList);
		}

		return "lecture/lecture_detail";
	}

	@RequestMapping("/main/lecture/check/student")
	public String studentCheck(){
		return "member/student_check";
	}

	@RequestMapping("/main/lecture/check/teacher")
	public String teacherCheck(){
		return "member/teacher_check";
	}

	@RequestMapping("/main/password/modify")
	public String passwordModify(){
		return "member/password_modify";
	}

	@RequestMapping("/main/password/modify/complete")
	public String passwordModifyComplete(){
		return "member/password_modify_complete";
	}
	
	@RequestMapping("/password/forgot")
	public String passwordForgot(){
		return "member/password_forgot";
	}

	@GetMapping("/saveToken")
	@ResponseBody
	public void saveToken(@RequestParam HashMap<Object, Object> params, Principal principal){
		String identity = principal.getName();		
		String token_data = (String)params.get("returnData");
		
		Member member = memberService.getMember(identity);
		if (token_data.equals("null") ) {
			System.out.println("token의 값이 null");

		}else {
			member.setToken(token_data);
			memberRepository.save(member);
		}
		System.out.println(token_data);

	}
    
}
