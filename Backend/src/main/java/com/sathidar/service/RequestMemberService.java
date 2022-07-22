package com.sathidar.service;

import org.json.JSONArray;

import com.sathidar.model.RequestMemberModel;

public interface RequestMemberService {

	JSONArray SendRequestToMember(RequestMemberModel requestMemberModel);

	JSONArray RequestAcceptAndRejected(RequestMemberModel requestMemberModel);

	int blockMember(RequestMemberModel requestMemberModel);

	JSONArray GetSentRequestDetails(String member_id);

	JSONArray GetInvitationsDetails(String member_id);

	JSONArray GetAcceptedDetails(String member_id);

	JSONArray GetRejectedDetails(String member_id);

	JSONArray getBlockMember(String member_id);

}
