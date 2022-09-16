package com.sathidar.service;

import java.util.HashMap;

import org.json.JSONArray;

import com.sathidar.model.RequestMemberModel;

public interface RequestMemberService {

	JSONArray SendRequestToMember(RequestMemberModel requestMemberModel);

	JSONArray RequestAcceptAndRejected(RequestMemberModel requestMemberModel);

	int blockMember(RequestMemberModel requestMemberModel);

	JSONArray GetSentRequestDetails(String member_id);

	JSONArray GetInvitationsDetails(String member_id);

	JSONArray GetMyAcceptedDetails(String member_id);
	
	JSONArray GetOtherAcceptedDetails(String member_id);

	JSONArray GetRejectedDetails(String member_id);
	
	JSONArray GetRejectedAndCanceledDetails(String member_id);

	JSONArray getBlockMember(String member_id);
	
	JSONArray GetAcceptedDetails(String member_id);

	HashMap<String, String> RequestAcceptAndRejectedWithHashMap(RequestMemberModel requestMemberModel);

	HashMap<String, String> SendRequestToMemberWithHashMap(RequestMemberModel requestMemberModel);

	int saveOTPDB(String phone_number, String otp);
}
