package com.project.Projectwo.QR;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

@Component
public class QrCodeView extends AbstractView {

    public QrCodeView() {
        setContentType("image/png; charset=UTF-8");
    }

    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
                                           HttpServletResponse response) throws Exception {
        response.setContentType(getContentType());
        response.setHeader("Content-Transfer-Encoding", "binary");

        OutputStream outputStream = response.getOutputStream();

        QRCodeWriter qrCodeWriter = new QRCodeWriter();

        String content = (String) model.get("content");
        
        //한글 데이터 처리
        content = new String(content.getBytes(StandardCharsets.UTF_8), "ISO-8859-1");
        
        //QR코드 생성
        BitMatrix bitMatrix = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, 400, 400);
        
        //QR코드 출력
        MatrixToImageWriter.writeToStream(bitMatrix, "png", outputStream);
        outputStream.flush();
        
        
        //QR코드 저장
        BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix);
		
		//yyyyMMddHHmmss형식의 날짜 및 시간 정보 파일명에 추가
		String datetimeStr = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
		
		String savePath = "C:\\dev\\ws_spring\\qrImage";
		File file = new File(savePath);
		String fileName;
		try {
			fileName = datetimeStr + "qr";
			
			//파일 생성
			File temp = new File(savePath + "/" + fileName + ".png");
			
			//ImageIO를 사용하여 파일 쓰기
			ImageIO.write(bufferedImage, "png", temp);
		}catch (IOException e) {
			e.printStackTrace();
        
		}
    }
}