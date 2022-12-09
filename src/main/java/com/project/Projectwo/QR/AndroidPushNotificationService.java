package com.project.Projectwo.QR;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

//firebase_server_key = firebase project > cloud messaging > server key

@Service
public class AndroidPushNotificationService {
	
	private static final String firebase_server_key = "nMIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCW0fIG6KAoPK9Z\\nci5uays6XGVNqFBweQFjNJuJzPpvkt6bBmuO/qVCh7Ip79YsvG9PRKsxPv0ovanG\\nQsseWQ7+rqOaQkuHfw6g6APS0mHdqXL0H//RJDIRZO/9Ehs157PqlRjnLkq1WMs+\\nKTp9erTgWCS3cmkM80ddCc3UTyNL5iGuIEm5aAnWUeIRlAOV7YvkfgBxj1cB7dy";
	private static final String firbase_api_url = "https://fcm.googleapis.com/fcm/send";
	
	@Async
	public CompletableFuture<String> send(HttpEntity<String> entity) {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
		restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
		        public boolean hasError(ClientHttpResponse response) throws IOException {
		            HttpStatus statusCode = response.getStatusCode();
		            return statusCode.series() == HttpStatus.Series.SERVER_ERROR;
		        }
		});
		
		
		ArrayList<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
		
		interceptors.add(new HeaderRequestInterceptor("Authorization", "key=" + firebase_server_key));
		interceptors.add(new HeaderRequestInterceptor("Content-Type", "application/json; UTF-8"));
		restTemplate.setInterceptors(interceptors);
		
		String firebaseResponse = restTemplate.postForObject(firbase_api_url, entity, String.class);
		
		return CompletableFuture.completedFuture(firebaseResponse);
		
		
		
	}
}
