package com.sathidar.util;

import org.springframework.stereotype.Service;

@Service
public class Constant {
	public String image_path="http://103.174.102.195:8080/member_images";
	
	public String document_path="http://103.174.102.195:8080/";
	
	public String project_logo="http://103.174.102.195:8080/saathidaar_logo/saathidaar_logo.jpeg";
//	public String image_path="D:/NG Digital";

	public String convertNullToBlank(String value) {
		if (value != null && !value.equals("") && !value.equals("null")) {
			return value;
		}
		return "";
	}
	
	public String FirstLetterCapital(String str) {
		String res="";
		try {
			if(str!=null && !str.equals("")) {
			String s1 = str.substring(0, 1).toUpperCase();  // first letter = J  
			String s2 = str.substring(1);     // after 1st letter = avatpoint  
			res = str.substring(0, 1).toUpperCase() + str.substring(1); // J + avatpoint  
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
}
