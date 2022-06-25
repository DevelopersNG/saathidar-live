

package com.sathidar.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

//import org.json.simple.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import antlr.Parser;

@Service
public class SendSMSAction {

			
	public String  SendOtpSms(String phoneNumber, String mSender, String smsMessage) {
		 //Your authentication key
	    String authkey = "308548AggtyUbHcd262303063P1";
	    //Multiple mobiles numbers separated by comma
	    String mobiles = phoneNumber;
	    //Sender ID,While using route4 sender id should be 6 characters long.
	    String senderId = mSender;
	    //Your message to send, Add URL encoding here.
//	    String message = smsMessage;

	    //encoding message
//	    String encoded_message=URLEncoder.encode(message);
	    String mainUrl="https://api.msg91.com/api/v5/otp?";
	    String response="",tempResponse="";
	    //Prepare parameter string 
	    StringBuilder sbPostData= new StringBuilder(mainUrl);  
	    sbPostData.append("template_id="+"6230300d15fa9542476097c5");
	    sbPostData.append("&mobile="+mobiles);
	    sbPostData.append("&authkey="+authkey);
	    //final string
	    mainUrl = sbPostData.toString();
	    return getSMSDetails(mainUrl);
	}

	private String getSMSDetails(String mainUrl) {
	    String response="",tempResponse="";
	    try{
	    	 //Prepare Url
		    URLConnection myURLConnection=null;
		    URL myURL=null;
		    BufferedReader reader=null;
	        //prepare connection
	        myURL = new URL(mainUrl);
	        myURLConnection = myURL.openConnection();
	        myURLConnection.connect();
	        reader= new BufferedReader(new InputStreamReader(myURLConnection.getInputStream()));
	        //reading response
	       
	        while ((response = reader.readLine()) != null) {
	        	tempResponse=response;
//		        System.out.println(response);
	        }

	        //finally close connection
	        reader.close();
	    }catch (Exception e) {
	    	e.printStackTrace();
		}
	    return tempResponse;
	}

	public String  VerifyOtpSms(String smsMessage, String phoneNumber,String otp) {

		//Your authentication key
	    String authkey = "308548AggtyUbHcd262303063P1";
	    //Multiple mobiles numbers separated by comma
	    String mobiles = phoneNumber;
	    String mOtp=otp;
	    String otp_expiry="1";
//	    String smsmessage = smsMessage;
	    
	    //Prepare Url
	    //encoding message

//	    String encoded_message=URLEncoder.encode(smsmessage);

	    String mainUrl="https://api.msg91.com/api/v5/otp/verify?";
	    String response="";
	    String tempResponse="";
	    //Prepare parameter string
	    StringBuilder sbPostData= new StringBuilder(mainUrl);
	    sbPostData.append("&mobile="+mobiles);
	    sbPostData.append("&authkey="+authkey);
	    sbPostData.append("&otp="+mOtp);
	    sbPostData.append("&otp_expiry="+1);
	    //final string
	    mainUrl = sbPostData.toString();
	   	return getSMSDetails(mainUrl);
	}
}
