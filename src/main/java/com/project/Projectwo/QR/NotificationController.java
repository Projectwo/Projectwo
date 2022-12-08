package com.project.Projectwo.QR;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

/*
 * 프로젝트를 실행한 뒤 "/send" 로 접속하여 동작이 실행되게한다. 
 * firebase project의 server key를 가지고 디바이스 토큰으로 알림을 json 데이터 형식으로 firebase에게 요청한다.
 */

@RestController
@RequiredArgsConstructor
public class NotificationController {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	AndroidPushNotificationService androidPushNotificationService;
	
	@GetMapping("/send")
	public @ResponseBody ResponseEntity<String> send() throws JSONException, InterruptedException {
		String notifications = AndroidPushPeriodicNotifications.PeriodicNotificationJson();
		
		HttpEntity<String> request = new HttpEntity<>(notifications);
		
		CompletableFuture<String> pushNotification = androidPushNotificationService.send(request);
		CompletableFuture.allOf(pushNotification).join();
		
		try {
			String firebaseResponse = pushNotification.get();
			return new ResponseEntity<>(firebaseResponse, HttpStatus.OK);
			
		}catch(InterruptedException e) {
			logger.debug("got interrupted!!");
			throw new InterruptedException();
			
		}catch(ExecutionException e) {
			logger.debug("execution error!!");
		}
		
		return new ResponseEntity<>("Push Notification ERROR!!", HttpStatus.BAD_REQUEST);
		

	}


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}