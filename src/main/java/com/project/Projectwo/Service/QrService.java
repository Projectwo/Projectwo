package com.project.Projectwo.Service;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

<<<<<<< HEAD
import com.project.Projectwo.Entity.Member;
import com.project.Projectwo.Entity.Student;
import com.project.Projectwo.Entity.Teacher;
import com.project.Projectwo.Service.MemberService;

=======
>>>>>>> dev
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class QrService {
	
	private final MemberService memberService;
	
<<<<<<< HEAD
	//by 박은영 (IP주소 얻기)
=======
	//by 박은영 
	//IP주소 얻기
>>>>>>> dev
	public static String getIp(){
	    String result = null;
	    try {
	        result = InetAddress.getLocalHost().getHostAddress();
	    } catch (UnknownHostException e) {
	        result = "";
	    }
	   return result; 
	}
	
	
<<<<<<< HEAD
	//by 박은영 (QR코드 생성)
=======
	//by 박은영
	//QR코드 생성
>>>>>>> dev
	public void createQr(HttpServletRequest request, HttpServletResponse response, Integer courseId) {

		QrCodeView qrCodeView = new QrCodeView();
			
		String ip = this.getIp();

		LocalDate localDate = LocalDate.now();
		String stringDate = localDate.toString();

<<<<<<< HEAD
		String content = "http://" + ip + ":9090/attend/" + courseId + "/" + stringDate;
		
=======
		String content = "http://" + "192.168.100.19" + ":9090/attend/" + courseId + "/" + stringDate;
>>>>>>> dev
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
