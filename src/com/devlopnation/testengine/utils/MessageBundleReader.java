package com.devlopnation.testengine.utils;

import java.util.ResourceBundle;

public interface MessageBundleReader {
	
	public static String getValue(String key){
		// ResourseBundle class is used to read  the properties file
		ResourceBundle rb = ResourceBundle.getBundle("message");
		String value = rb.getString(key);
		return value;
	}
	
	public static String getConfigValue(String key){
		// ResourseBundle class is used to read  the properties file
		ResourceBundle rb = ResourceBundle.getBundle("config");
		String value = rb.getString(key);
		return value;
	}
}
