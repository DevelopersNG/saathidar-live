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
import com.sathidar.service.RequestMemberService;
import com.sathidar.util.SendSMSAction;

@CrossOrigin(origins = "http://localhost:4200" , methods = {RequestMethod.OPTIONS, RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE}, allowedHeaders = "*", allowCredentials = "true")
@RestController
@RequestMapping("/api")
public class RequestMemberController {

	@Autowired
	private RequestMemberService requestMemberService;

	@Autowired
	private SendSMSAction sendSMSAction;
	
	@PostMapping(value="/member/send-request")
	private String SendRequestToMember(@Validated @RequestBody RequestMemberModel requestMemberModel) {
		JSONArray jsonResultsArray=new JSONArray();
		jsonResultsArray= requestMemberService.SendRequestToMember(requestMemberModel);
		return jsonResultsArray.toString();
	}
	
	@PostMapping(value="/member/request-accept-reject")
	private String RequestAcceptAndRejected(@Validated @RequestBody RequestMemberModel requestMemberModel) {
		JSONArray jsonResultsArray=new JSONArray();
		jsonResultsArray= requestMemberService.RequestAcceptAndRejected(requestMemberModel);
		return jsonResultsArray.toString();
	}
	
	@PostMapping(value="/member/block-member")
	private String BlockMember(@Validated @RequestBody RequestMemberModel requestMemberModel) {
		JSONArray jsonResultsArray=new JSONArray();
		jsonResultsArray= requestMemberService.blockMember(requestMemberModel);
		return jsonResultsArray.toString();
	}
	
	@GetMapping(value="/request/sent/get/all/{member_id}")
	private String GetSentRequestDetails(@PathVariable String member_id ) {
		JSONArray jsonResultsArray=new JSONArray();
		JSONObject jsObject = new JSONObject();  
		jsonResultsArray= requestMemberService.GetSentRequestDetails(member_id);
		if(jsonResultsArray!=null) {
			jsObject.put("data",jsonResultsArray);
			jsObject.put("results","1");
		}else {
			jsObject.put("data",jsonResultsArray);
			jsObject.put("results","0");
		}
		return jsObject.toString();
	}
	
	@GetMapping(value="/request/invitations/get/all/{member_id}")
	private String GetInvitationsDetails(@PathVariable String member_id ) {
		JSONArray jsonResultsArray=new JSONArray();
		JSONObject jsObject = new JSONObject();  
		jsonResultsArray= requestMemberService.GetInvitationsDetails(member_id);
		if(jsonResultsArray!=null) {
			jsObject.put("data",jsonResultsArray);
			jsObject.put("results","1");
		}else {
			jsObject.put("data",jsonResultsArray);
			jsObject.put("results","0");
		}
		return jsObject.toString();
	}
	
	@GetMapping(value="/request/accepted/get/all/{member_id}")
	private String GetAcceptedDetails(@PathVariable String member_id ) {
		JSONArray jsonResultsArray=new JSONArray();
		JSONObject jsObject = new JSONObject();  
		jsonResultsArray= requestMemberService.GetAcceptedDetails(member_id);
		if(jsonResultsArray!=null) {
			jsObject.put("data",jsonResultsArray);
			jsObject.put("results","1");
		}else {
			jsObject.put("data",jsonResultsArray);
			jsObject.put("results","0");
		}
		return jsObject.toString();
	}
	
	@GetMapping(value="/request/rejected/get/all/{member_id}")
	private String GetRejectedDetails(@PathVariable String member_id ) {
		JSONArray jsonResultsArray=new JSONArray();
		JSONObject jsObject = new JSONObject();  
		jsonResultsArray= requestMemberService.GetRejectedDetails(member_id);
		if(jsonResultsArray!=null) {
			jsObject.put("data",jsonResultsArray);
			jsObject.put("results","1");
		}else {
			jsObject.put("data",jsonResultsArray);
			jsObject.put("results","0");
		}
		return jsObject.toString();
	}
	
}
