package com.project.Projectwo.QR;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class QrController {
	
	JwtUtil jwtUtil = new JwtUtil();
	
	@GetMapping("/main")
	public String main() {
		return "main";
	}
	
	@GetMapping("/test")
	public String test() {
		return "test";
	}
	
	@GetMapping("/cam")
	public String getCam() {
		return "cam";
	}
	
	@GetMapping("/cam2")
	public String getCam2() {
		return "cam2";
	}
	
	@GetMapping("/attendance")
	public String attendance() {

		return "attendance";
	}
	
	
	@GetMapping("checkToken")
	@ResponseBody
	public String checkToken(@RequestParam String token) {
		
		log.info("token: " + token);
		
		String result = null;
		try {
			result = JwtUtil.getSubject(token);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	@GetMapping("/createQr")
	public void createQr(HttpServletRequest request, HttpServletResponse response) {

		QrCodeView qrCodeView = new QrCodeView();
		
		String ip = GetIp.getIp();

		LocalDateTime localDate = LocalDateTime.now();
		String stringDate = localDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
		
		log.info(stringDate);
		
		String lecture = "java";
		String content = "http://" + ip + "/lecture" + lecture + "/" + stringDate;
		
		log.info(content);
		
		Map<String, Object> model = new HashMap<>();
		
		
		model.put("content", content);
		try {
			qrCodeView.renderMergedOutputModel(model, request, response);
		
		}catch(Exception e) {
			e.printStackTrace();
		}
	
	}
	
	@GetMapping("/lecture/{courseId}/{date}")
	@ResponseBody
	public String getAttendance(@PathVariable("courseId") Integer courseId, @PathVariable("date") String date,
			Model model1, Model model2) {
		
		model1.addAttribute("courseId", courseId);
		model2.addAttribute("date", date);
		log.info("lecture: " + courseId);
		log.info("date: " + date);
		
		return "attendance";
	}

}
