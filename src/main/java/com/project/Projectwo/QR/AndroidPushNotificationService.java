package com.project.Projectwo.QR;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

import org.springframework.http.HttpEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

//firebase_server_key = firebase project > cloud messaging > server key

@Service
public class AndroidPushNotificationService {
	
	private static final String firebase_server_key = "cadd33913ea45ead6070bf049e0c922776ba12f7";
	private static final String firbase_api_url = "https://fcm.googleapis.com/fcm/send";
	
	@Async
	public CompletableFuture<String> send(HttpEntity<String> entity) {
		RestTemplate restTemplate = new RestTemplate();
		
		ArrayList<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
		
		interceptors.add(new HeaderRequestInterceptor("Authorization", "key=" + firebase_server_key));
		interceptors.add(new HeaderRequestInterceptor("Content-Type", "application/json; UTF-8"));
		restTemplate.setInterceptors(interceptors);
		
		String firebaseResponse = restTemplate.postForObject(firbase_api_url, entity, String.class);
		
		return CompletableFuture.completedFuture(firebaseResponse);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
