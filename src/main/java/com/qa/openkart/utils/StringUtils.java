package com.qa.openkart.utils;

public class StringUtils {
	
	public static String randEMailID() {
		String emailId = "testautomation" + System.currentTimeMillis() + "@openkart.com";
		System.out.println("Current email Id is : " + emailId);
		return emailId;
	}

}
