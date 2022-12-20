package com.project.Projectwo.Service;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.AndroidConfig;
import com.google.firebase.messaging.AndroidNotification;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FCMService {

    @Value("${fcm.key.path}")
    private String FCM_PRIVATE_KEY_PATH;

    //by 박은영
    //fcm 기본 설정 진행
    //init: Firebase에 Admin계정을 인증하는 작업
    @PostConstruct
    public void init() throws IOException {
    	
    	FileInputStream refreshToken = new FileInputStream(FCM_PRIVATE_KEY_PATH);

        //FirebaseApp name [DEFAULT] already exists 에러 방지
        FirebaseApp firebaseApp = null;
        List<FirebaseApp> firebaseApps = FirebaseApp.getApps();
        
        
        if (firebaseApps != null && !firebaseApps.isEmpty() ) {
        	for(FirebaseApp app : firebaseApps) {
        		if(app.getName().equals(FirebaseApp.DEFAULT_APP_NAME)) {
        			firebaseApp = app;
        		}
        	}
        }else {
        	FirebaseOptions options = FirebaseOptions.builder()
            		.setCredentials(GoogleCredentials.fromStream(refreshToken))
            		.setProjectId("projectapp-a9eb8")
                    .build();

        	firebaseApp = FirebaseApp.initializeApp(options);
        }

    }


    //by 박은영
    //알림 보내기
	public String sendMessage(String registrationToken) throws FirebaseMessagingException {
		int randNum = (int) (Math.random() * 3);
		String msg = "";
		switch (randNum) {
			case 1:
				msg = "퇴실이 완료되지 않았습니다.";
				break;
			case 2:
				msg = "퇴실이 완료되지 않았습니다.";
				break;
			case 3:
				msg = "퇴실이 완료되지 않았습니다.";
				break;
			default:
				msg = "퇴실이 완료되지 않았습니다.";
				break;
			}
			Message message = Message.builder()
				    .setAndroidConfig(AndroidConfig.builder()
				    		.setTtl(3600*1000)
				    		.setPriority(AndroidConfig.Priority.HIGH)
				    		.setRestrictedPackageName("com.project.projectapp")
				    		.setDirectBootOk(true)
				    		.setNotification(AndroidNotification.builder()
				    				.setTitle("@I'm here")
				    				.setBody(msg)
				    				.build())
				    		.build())
				    //.putData("requestId", Integer.toString(requestId))
				    .setToken(registrationToken)
				    .build();
			
			String response = FirebaseMessaging.getInstance().send(message);
			
			return response;
			
	}

}
