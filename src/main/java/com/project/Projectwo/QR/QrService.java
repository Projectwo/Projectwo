package com.project.Projectwo.QR;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class QrService {
	
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
	public void createQr(HttpServletRequest request, HttpServletResponse response) {

		QrCodeView qrCodeView = new QrCodeView();
			
		String ip = this.getIp();

		LocalDateTime localDate = LocalDateTime.now();
		String stringDate = localDate.toString();
			
		log.info(stringDate);
			
			
		//TODO: QR생성은 admin에서 일괄처리 courseId에 값 넣는 거는 알아서 하겠지...
		Integer courseId = 1;
		String content = "http://" + ip + ":9090/course/" + courseId + "/" + stringDate;
			
		log.info(content);
			
		Map<String, Object> model = new HashMap<>();
			
			
		model.put("content", content);
		try {
			qrCodeView.renderMergedOutputModel(model, request, response);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
