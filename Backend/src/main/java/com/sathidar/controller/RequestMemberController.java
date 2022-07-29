package com.sathidar.controller;

import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sathidar.model.RequestMemberModel;
import com.sathidar.service.EmailService;
import com.sathidar.service.RequestMemberService;
import com.sathidar.util.Constant;
import com.sathidar.util.SendSMSAction;
import com.sathidar.util.TextLocalSMSSetting;

//@CrossOrigin(origins = "http://localhost:4200", methods = { RequestMethod.OPTIONS, RequestMethod.GET,
//		RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE }, allowedHeaders = "*", allowCredentials = "true")

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class RequestMemberController {

	@Autowired
	private RequestMemberService requestMemberService;

	@Autowired
	private SendSMSAction sendSMSAction;

	@Autowired
	private EmailService mailSender;

	@PostMapping(value = "/member/send-request")
	private String SendRequestToMember(@Validated @RequestBody RequestMemberModel requestMemberModel) {
		JSONArray jsonResultsArray = new JSONArray();
		jsonResultsArray = requestMemberService.SendRequestToMember(requestMemberModel);
		return jsonResultsArray.toString();
	}

	@PostMapping(value = "/member/request-accept-reject")
	private String RequestAcceptAndRejected(@Validated @RequestBody RequestMemberModel requestMemberModel) {
		JSONArray jsonResultsArray = new JSONArray();
		jsonResultsArray = requestMemberService.RequestAcceptAndRejected(requestMemberModel);
		return jsonResultsArray.toString();
	}

	@PostMapping(value = "/member/block-member")
	private String BlockMember(@Validated @RequestBody RequestMemberModel requestMemberModel) {
		JSONObject jsObject = new JSONObject();
		int status = requestMemberService.blockMember(requestMemberModel);
		if (status > 0) {
			jsObject.put("message", "Member is blocked...");
			jsObject.put("results", "1");
		} else {
			jsObject.put("message", "Member is not block...");
			jsObject.put("results", "0");
		}
		return jsObject.toString();
	}

	@GetMapping(value = "/member/get/block-member/{member_id}")
	private String getBlockMember(@PathVariable String member_id) {
		JSONArray jsonResultsArray = new JSONArray();
		JSONObject jsObject = new JSONObject();
		jsonResultsArray = requestMemberService.getBlockMember(member_id);
		if (jsonResultsArray != null) {
			jsObject.put("data", jsonResultsArray);
			jsObject.put("results", "1");
		} else {
			jsObject.put("data", jsonResultsArray);
			jsObject.put("results", "0");
		}
		return jsObject.toString();
	}
	
	@GetMapping(value = "/request/sent/get/all/{member_id}")
	private String GetSentRequestDetails(@PathVariable String member_id) {
		JSONArray jsonResultsArray = new JSONArray();
		JSONObject jsObject = new JSONObject();
		jsonResultsArray = requestMemberService.GetSentRequestDetails(member_id);
		if (jsonResultsArray != null) {
			jsObject.put("data", jsonResultsArray);
			jsObject.put("results", "1");
		} else {
			jsObject.put("data", jsonResultsArray);
			jsObject.put("results", "0");
		}
		return jsObject.toString();
	}

	@GetMapping(value = "/request/invitations/get/all/{member_id}")
	private String GetInvitationsDetails(@PathVariable String member_id) {
		JSONArray jsonResultsArray = new JSONArray();
		JSONObject jsObject = new JSONObject();
		jsonResultsArray = requestMemberService.GetInvitationsDetails(member_id);
		if (jsonResultsArray != null) {
			jsObject.put("data", jsonResultsArray);
			jsObject.put("results", "1");
		} else {
			jsObject.put("data", jsonResultsArray);
			jsObject.put("results", "0");
		}
		return jsObject.toString();
	}

	@GetMapping(value = "/request/accepted/get/all/{member_id}")
	private String GetAcceptedDetails(@PathVariable String member_id) {
		JSONArray jsonMyResultsArray = new JSONArray();
		JSONArray jsonOtherResultsArray = new JSONArray();
		
		JSONObject jsObject = new JSONObject();
		jsonMyResultsArray = requestMemberService.GetAcceptedDetails(member_id);
		if (jsonMyResultsArray != null) {
			jsObject.put("data", jsonMyResultsArray);
			jsObject.put("results", "1");
		} else {
			jsObject.put("data", "");
			jsObject.put("results", "0");
		}
		return jsObject.toString();
	}

	@GetMapping(value = "/request/rejected/get/all/{member_id}")
	private String GetRejectedDetails(@PathVariable String member_id) {
		JSONArray jsonResultsArray = new JSONArray();
		JSONObject jsObject = new JSONObject();
		jsonResultsArray = requestMemberService.GetRejectedAndCanceledDetails(member_id);
		if (jsonResultsArray != null) {
			jsObject.put("data", jsonResultsArray);
			jsObject.put("results", "1");
		} else {
			jsObject.put("data", jsonResultsArray);
			jsObject.put("results", "0");
		}
		return jsObject.toString();
	}

//	**************** send sms ***********************************************
	@PostMapping(value = "/member/send-sms")
	private String sendSMSVIATextLocal(@Validated @RequestBody RequestMemberModel requestMemberModel) {
		TextLocalSMSSetting textLocalSMSSetting = new TextLocalSMSSetting();

		String messgeBody = "Hi, you have received i nterest from REG ID : 25666\r\n"
				+ "If i t sounds good then take the follow up.\r\n" + "Regards,\r\n" + "Saathidaar.com";

//		String messgeBody="Your Verification Code is 12315\r\n" + 
//				"MyMealDabba.com";

		String response = textLocalSMSSetting.POSTSendSMS(requestMemberModel.getPhone_number(), "SMMSG", messgeBody);
		System.out.println("sms resonse - " + response);

		return response;
	}

//	**************** send email ***********************************************

	@PostMapping(value = "/member/send-email")
	private String sendEmailByGmail(@Validated @RequestBody RequestMemberModel requestMemberModel) {
		String response = "";
		Constant constant=new Constant();
		try {
			String email_body = "<head>\r\n" + 
					"    <meta charset=\"UTF-8\">\r\n" + 
					"    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n" + 
					"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n" + 
					"    <style>\r\n" + 
					"\r\n" + 
					"        .container\r\n" + 
					"        {\r\n" + 
					"           height: 150px; width: 400px;border: #742041 1px solid ;margin-top: 5px;\r\n" + 
					"        }\r\n" + 
					"       \r\n" + 
					"        table {\r\n" + 
					"  font-family: arial, sans-serif;\r\n" + 
					"  border-collapse: collapse;\r\n" + 
					"  width: 100%;\r\n" + 
					"}\r\n" + 
					"\r\n" + 
					"td, th {\r\n" + 
					" font-size: 12px;\r\n" + 
					"  text-align: left;\r\n" + 
					"  padding: 8px;\r\n" + 
					"}\r\n" + 
					"       img\r\n" + 
					"       {\r\n" + 
					"        height: 150px;\r\n" + 
					"       }\r\n" + 
					"       .bg\r\n" + 
					"       {\r\n" + 
					"        background-color: #742041;\r\n" + 
					"       }\r\n" + 
					"       button\r\n" + 
					"       {\r\n" + 
					"        background-color: #742041;color: #ffff;margin: 5px;\r\n" + 
					"       }\r\n" + 
					"    </style>\r\n" + 
					"       \r\n" + 
					"</head>\r\n" + 
					"\r\n" + 
					"<body style=\"width: 400px;\">\r\n" + 
					"    <div style=\"background-color: #742041;\"><img style=\"width:300px ;\" src=\""+constant.project_logo+" alt=\"\"></div>\r\n" + 
					" <div class=\"image\">\r\n" + 
					"   <h4 style=\"text-align: center;color: #742041;\">Invitation to become your Saathidar!!!\r\n" + 
					"</h4>\r\n" + 
					"<p style=\"float: left;\">Hi</p><br>\r\n" + 
					"<p>[Sender’s Name] has invited you to connect. Let’s Respond</p>\r\n" + 
					"   <table style=\"width: 100%;border: #742041 1px solid;\" class=\"table\">\r\n" + 
					"    <thead>\r\n" + 
					"      <tr >\r\n" + 
					"        <th  scope=\"col\">Age  :</th>\r\n" + 
					"        <th  scope=\"col\">25 </th>\r\n" + 
					"      </tr>\r\n" + 
					"      <tr >\r\n" + 
					"        <th  scope=\"col\"> Height :</th>\r\n" + 
					"        <th  scope=\"col\"> 5'5\" (166 cm)</th>\r\n" + 
					"      </tr>\r\n" + 
					"      <tr>\r\n" + 
					"        <th scope=\"col\">Marital Status :</th>\r\n" + 
					"        <th scope=\"col\">Never Married</th>\r\n" + 
					"      </tr>\r\n" + 
					"      <tr >\r\n" + 
					"        <th  scope=\"col\">Education :</th>\r\n" + 
					"        <th  scope=\"col\">PGDM</th>\r\n" + 
					"      </tr>\r\n" + 
					"      <tr>\r\n" + 
					"        <th scope=\"col\">Profession :</th>\r\n" + 
					"        <th scope=\"col\">Consultant / Supervisor / Team Leads</th>\r\n" + 
					"      </tr>\r\n" + 
					"    </thead>\r\n" + 
					"  </table>\r\n" + 
					"  <button type=\"button\">ACCEPT</button><button type=\"button\">DECLINE</button>\r\n" + 
					" </div>\r\n" + 
					" <div class=\"details\"></div>\r\n" + 
					"\r\n" + 
					"  </body>";
//			email_body="test";
			mailSender.send(requestMemberModel.getEmail_to(), requestMemberModel.getEmail_subject(), email_body);
			response="success";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

}
