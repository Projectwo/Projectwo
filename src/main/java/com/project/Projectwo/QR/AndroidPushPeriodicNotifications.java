package com.project.Projectwo.QR;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDate;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import groovy.util.logging.Slf4j;

//'/send'로 접속할 시 푸시알림

@Slf4j
public class AndroidPushPeriodicNotifications {
	
	public static String PeriodicNotificationJson() throws JSONException {
		
		LocalDate localDate = LocalDate.now();
		
		//TODO: 여기에 안드로이드에서 받은 기기 토큰 넣어주기 자동화
		String sampleData[] = { 
				"frhnKXWCQr6-hVhsmascjt:APA91bH19WyAZdN7yOGX9ViMsvwiX3w5o3B5D4FUit1KWMMqE63vqmOa5KWupNsSKDDfq0oa0DM8BueDsS-da6bTTyCDUhT0VcAqDjKvDfA5BUhRcpadB-Kkoe-Ulyj8ZO03HKJveaDG"};
		
		JSONObject body = new JSONObject();
		
		List<String> tokenList = new ArrayList<String>();
		
		for(int i=0; i<sampleData.length; i++) {
			tokenList.add(sampleData[i]);
			
		}
		
		JSONArray jsonArray = new JSONArray();
		
		for(int i=0; i<tokenList.size(); i++) {
			jsonArray.put(tokenList.get(i));

			System.out.println("####jsonArray[" + i + "]= " + jsonArray.getString(i));
		}
		
		body.put("registration_ids", jsonArray);
		
		JSONObject notification = new JSONObject();
		notification.put("title", "ProjectApp");
		notification.put("body", localDate.toString() + " 퇴실 미완료");
		
		body.put("notification", notification);
		
		System.out.println("####JSONObject body= " + body.toString());
		

		return body.toString();
	}

}














