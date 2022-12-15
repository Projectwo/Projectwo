package com.project.Projectwo.Service;

import java.net.InetAddress;
import java.net.UnknownHostException;


public class GetIp {
	
	public static String getIp(){
	    String result = null;
	    try {
	        result = InetAddress.getLocalHost().getHostAddress();
	    } catch (UnknownHostException e) {
	        result = "";
	    }
	   return result; 
	}


}
