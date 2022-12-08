package com.project.Projectwo.QR;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDate;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import groovy.util.logging.Slf4j;

@Slf4j
public class AndroidPushPeriodicNotifications {
	
	public static String PeriodicNotificationJson() throws JSONException {
		
		LocalDate localDate = LocalDate.now();
		
		String sampleData[] = {"device token value 1", "device token value 2", "device token value 3"};
		
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














