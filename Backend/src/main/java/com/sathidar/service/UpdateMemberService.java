package com.sathidar.service;


import java.util.List;

import com.sathidar.model.UpdateMember;
import com.sathidar.model.UploadImagesModel;

public interface UpdateMemberService {

	Object UpdateMemberDetails(UpdateMember updateMember, int id);

	UpdateMember getDetailsByMemberID(String id);

	Object updateHoroscopeDetails(UpdateMember updateMember, int member_id);

	Object UpdateBasicLifeCycleMemberDetails(UpdateMember updateMember, int id);

	Object updateAppFamilyDetailsMember(UpdateMember updateMember, int id);

	Object updateAppProfessionalDetailsMember(UpdateMember updateMember, int id);

	int activateMember(UpdateMember updateMember);

	int getActivateMember(int member_id);

	int updateRegistrationDetails(UpdateMember updateMember, int member_id);

	String getShortRegistrationStatus(int member_id);

	String getMemberStatus(int id, String memberID);

	String getMemberBlockStatus(int login_id, String thisMemberID);

	String getToDatePremiumMemberIDs(String to_date);

	String getFromDatePremiumMemberIDs(String from_date);



}
