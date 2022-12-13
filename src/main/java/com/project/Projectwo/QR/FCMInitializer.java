package com.project.Projectwo.QR;

import java.io.FileInputStream;
import java.io.IOException;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

@Service
public class FCMInitializer {
	
	@PostConstruct
	public void initialize() throws IOException {
		
		FileInputStream refreshToken = new FileInputStream("/firebase/firebase_service_key");
	
	
		FirebaseOptions options = FirebaseOptions.builder()
				.setCredentials(GoogleCredentials.fromStream(refreshToken))
				.setProjectId("projectapp-a9eb8")
				.build();
		
		FirebaseApp.initializeApp();
				
	}
}
