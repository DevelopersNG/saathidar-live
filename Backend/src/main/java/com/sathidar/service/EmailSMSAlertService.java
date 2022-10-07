package com.sathidar.service;

import java.util.List;

import com.sathidar.model.EmailSMSAlertModel;

public interface EmailSMSAlertService {

	List<EmailSMSAlertModel> getEmailAlert();

	int getDailyEmailStatusByMemberID(String member_id);

	String getPremiumMatchesIDS(Integer member_id);

	List<Object[]> getMemberDeyailsForMail(String getIDS);

	List<Object[]> getUserNameEmailId(int parseInt);

}
