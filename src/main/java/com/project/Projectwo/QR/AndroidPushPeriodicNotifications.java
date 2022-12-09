package com.project.Projectwo.QR;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDate;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import groovy.util.logging.Slf4j;

//하루에 한 번씩 주기적으로 알림보내는 역할
//현재는 아직 구현중이므로 '/send'로 접속할 시에만 푸시알림 감

@Slf4j
public class AndroidPushPeriodicNotifications {
	
	public static String PeriodicNotificationJson() throws JSONException {
		
		LocalDate localDate = LocalDate.now();
		
		String sampleData[] = { 
				"dALQVsrqR1aqdIHN5FEdZX:APA91bFyL-tROMnIvtw9Ypp5xeyTHg821Lb0Nc-ZdvpbyH8e4Mi86oZYq06yGl_XBqlhLgBw_3TGTeGCXKg4mb4Qv63UIkzfLDgZAP6RmLWrjUsHv903__R0HEwv1A2ajdbHAeqlKiAK"};
		
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














