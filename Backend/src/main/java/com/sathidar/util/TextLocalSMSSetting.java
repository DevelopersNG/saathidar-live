package com.sathidar.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class TextLocalSMSSetting {
//						N2Y0YWZiMDAxNWY1OWRhMDFkOTcyMTFmMDRmZDNiM2E=
	String getApiKey = "N2Y0YWZiMDAxNWY1OWRhMDFkOTcyMTFmMDRmZDNiM2E=";
	String postApiKey = "apikey=" + "NjU0ZDM1NzE2ZjY4NGI0OTQ1NzA3NzVhMzM2NDM0NzM=";

	// get request
	public String GetSendSMS(String phoneNumber, String sender, String message) {

		try {
			// Send data
			String data = "https://api.textlocal.in/send/?" + getApiKey + phoneNumber + message + sender;
			URL url = new URL(data);
			URLConnection conn = url.openConnection();
			conn.setDoOutput(true);

			// Get the response
			BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			String sResult = "";
			while ((line = rd.readLine()) != null) {
				// Process line...
				sResult = sResult + line + " ";
			}
			rd.close();

			return sResult;
		} catch (Exception e) {
			System.out.println("Error SMS " + e);
			return "Error " + e;
		}
	}
	
	// post request
	public String POSTSendSMS(String mphoneNumber, String msender, String mmessage) {
		String status="";
		try {
			String message = "&message=" + mmessage;
			String sender = "&sender=" + msender;
			String numbers = "&numbers=91" + mphoneNumber;
			// Send data
			HttpURLConnection conn = (HttpURLConnection) new URL("https://api.textlocal.in/send/?").openConnection();
			String data = postApiKey + numbers + message + sender;
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
			conn.getOutputStream().write(data.getBytes("UTF-8"));
			final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			final StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = rd.readLine()) != null) {
				stringBuffer.append(line);
			}
			rd.close();
			
			return stringBuffer.toString();
		} catch (Exception e) {
		e.printStackTrace();
		status="";
		}
		return status;
	}
	
	
}
