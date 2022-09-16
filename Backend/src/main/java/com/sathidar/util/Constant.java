package com.sathidar.util;

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
}
