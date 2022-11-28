package com.project.Projectwo.QR;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class QrController {
	
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
		
		return "/attendance";
	}
	
	@GetMapping("/createQr")
	public void createQr(HttpServletRequest request, HttpServletResponse response) {
		
		GetIp getIp = new GetIp();
		QrCodeView qrCodeView = new QrCodeView();
		
		
		//content에 날짜 + 강의정보 넣어서 새로고침할 때마다 새로 생성되게 하기
		
		LocalDateTime now = LocalDateTime.now();
		String stringDate = now.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
		//TO DO: String type의 timeDrop에 저장된 시간만큼 유지되는 QR 이미지 생성
		//예시: http://localhost/날짜/강의명

		
		String ip = getIp.getIp();
		
		
		//String content = "https://www.figma.com/file/xlFuClUUr26OU34EWzA0jT/Untitled?node-id=0%3A1";
		
		String content = "http://" + ip + "/attendance";
		
		log.info(content);
		Map<String, Object> model = new HashMap<>();
		
		
		model.put("content", content);
		try {
			qrCodeView.renderMergedOutputModel(model, request, response);
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		String savePath = "C:\\dev\\ws_spring\\qrImage";
		File file = new File(savePath);
		
		//파일 경로가 없으면 파일 생성 (이게 뭘까.............)
		if(!file.exists()) {
			file.mkdirs();
		}

		String fileName;
		
		try {
			
			//QR 생성
			QRCodeWriter qrCodeWriter = new QRCodeWriter();
			BitMatrix bitMatrix = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, 400, 400);
			BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix);
			
			//yyyyMMddHHmmss형식의 날짜 및 시간 정보 파일명에 추가
			String datetimeStr = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
			
			fileName = datetimeStr + "qr";
			
			//파일 생성
			File temp = new File(savePath + "/" + fileName + ".png");
			
			//ImageIO를 사용하여 파일 쓰기
			ImageIO.write(bufferedImage, "png", temp);
			
			log.info(fileName);
			log.info(temp.toString());

		}catch (IOException | WriterException e) {
			e.printStackTrace();
			
		}
	}

}
