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
	
	private static final String firebase_server_key = "nMIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCW0fIG6KAoPK9Z\\nci5uays6XGVNqFBweQFjNJuJzPpvkt6bBmuO/qVCh7Ip79YsvG9PRKsxPv0ovanG\\nQsseWQ7+rqOaQkuHfw6g6APS0mHdqXL0H//RJDIRZO/9Ehs157PqlRjnLkq1WMs+\\nKTp9erTgWCS3cmkM80ddCc3UTyNL5iGuIEm5aAnWUeIRlAOV7YvkfgBxj1cB7dy+\\n6DS7wjASc9ire5Enw5X1PUSLVUwmPhViD0Xprad18izE8P9yzCwwfluwh28QUqNH\\nS1yrEm0wn2GVsi1N1DW7dPnftHMMOkCGotx1K88M26CDDAVEUtdOa5RQcQ9IekYd\\nrR/JtDvPAgMBAAECggEAPjsDfAFJcEDV83jPGsWCkTKlpBzij/4F6gq/cOUQoq/9\\nxQapo9eDlgeKpo4tF8lqvlDtuESuzbwwjLtrtyp1KvYhUFmxc4ekIBWL2lXvhrhP\\n09ZORlwCgrXc5lWtv5jK3mzF2UH7Foe8WPpHBZZ3gqgsAjulh+/rzsJoWQWMYVjB\\n+3yvhZvGua8iOMWw5saWDUJq0Hru1gMQ/18RM7c+C/0UdYAOBj1eUuDRF2AtNosj\\n2MT5uUv+JF+DWaVIZnpU6Z6THXV+ZlWMEC9XToRErATU3BvvLzqCOgQFIzwbu2ID\\nz2OfK7hn4dON+ZRyhuL0IE2e9JpH7eaxLvP4JUw+8QKBgQDGwwHJq8Re/yeoZfMr\\nBzj8prIi5fBCNFr+J1NQSrXWFDtnna1DlkAuyQti7+ppWf6qevss9S4Uob5HP1Xc\\n/qQRJKRdoujt4mhTW8w50bHrlx62BAS+A+kVV3JS19PzRYwqZlwzT2CKxg4s4NCy\\n2dwtMZJ4qxkJ1DqccOkXC2d5fwKBgQDCQJ0HxyLishzaqA4sUjdq9fnMWrtZ6+4r\\nIgGnCVHSdlhcIGOq0dK7Bg0fGQR6AGcGBh4VzM0reZff7OmhE4Guyts/0J4ywcZo\\n6ka7c+GdTUeTh0GfXqdo9Y14JyEzgElMtptWTdQ1DmicoQVMQltouhYzTACOWByf\\n+EqerZZFsQKBgEIY42VPa7QzodqMXLs4YakTPjYAxU8e3guaxEf1MFcKz157vvy0\\n4I8uaEz0f72dIeVLlG+08SgHSaGj3Efrnl/dEZPZ8x/4pLzj0ByCMWqpDJt25qTR\\n5Bcmx2vpPiCuRhhrhcnCYdaEWn5uHLj9XgzhaVYbf+4W147itYDmV/i9AoGAHH76\\nCkAOQA886F6q5ZmSA6Dd6jqNLV3M7IrqjyRw2lz+DVW7CT4KmeEDX8J9w5/3fQy8\\nsRoAJxaJTxIkG7sO1SNxEmV6L5v5jT6A7dLMEBVcIKpA2C48hMsw26Vx22pAVn6b\\nPwffz4Czm/aLHmrFmhFiXFgT5M3MNTNpTunMF6ECgYBErEajmvFKYNsFPV6ZZ9fi\\nf3oCybz/7UHedvttWivwS31ulsWz8Ft+6tEmOVEc3JowB470hV9OhYe5sMBKArt3\\nyerZVKZRG701iwsFYyvGDSffTQYQ32UX1AMxfad15B/WOUi5uEKscnyCCjOL4wig\\nqg8fI/rnj6nBAmQ+hM6UpA";
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
