package com.project.Projectwo.QR;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
				"eQqJhBxSQVuqPNOSgmEOfH:APA91bGA-aZ19LUzr-F65MQ05isTBJej3YCcJVMTkHapAeKENmH2VLAu4MFBoz1Hkl0DrVSQb9EIyBO6GhWzyyhPe8_B76scpiV2rZkrdLaRd5X4kQ0WweCs0H6oclrnWAIFuk5vbjGq"
		};
		
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

