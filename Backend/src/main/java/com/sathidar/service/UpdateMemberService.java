package com.sathidar.service;


import com.sathidar.model.UpdateMember;

public interface UpdateMemberService {

	Object UpdateMemberDetails(UpdateMember updateMember, int id);

	UpdateMember getDetailsByMemberID(String id);

	Object updateHoroscopeDetails(UpdateMember updateMember, int member_id);

	Object UpdateBasicLifeCycleMemberDetails(UpdateMember updateMember, int id);

	Object updateAppFamilyDetailsMember(UpdateMember updateMember, int id);

	Object updateAppProfessionalDetailsMember(UpdateMember updateMember, int id);


}
