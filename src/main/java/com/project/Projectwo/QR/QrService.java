package com.project.Projectwo.QR;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.project.Projectwo.Entity.Member;
import com.project.Projectwo.Entity.Student;
import com.project.Projectwo.Entity.Teacher;
import com.project.Projectwo.Service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class QrService {
	
	private final MemberService memberService;
	
	//by 박은영 (IP주소 얻기)
	public static String getIp(){
	    String result = null;
	    try {
	        result = InetAddress.getLocalHost().getHostAddress();
	    } catch (UnknownHostException e) {
	        result = "";
	    }
	   return result; 
	}
	
	
	//by 박은영 (QR코드 생성)
	public void createQr(HttpServletRequest request, HttpServletResponse response, Integer courseId) {

		QrCodeView qrCodeView = new QrCodeView();
			
		String ip = this.getIp();

		LocalDate localDate = LocalDate.now();
		String stringDate = localDate.toString();

		String content = "http://" + ip + ":9090/attend/" + courseId + "/" + stringDate;
			
		log.info("####QR주소=" + content);
			
		Map<String, Object> model = new HashMap<>();
			
			
		model.put("content", content);
		try {
			qrCodeView.renderMergedOutputModel(model, request, response);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
