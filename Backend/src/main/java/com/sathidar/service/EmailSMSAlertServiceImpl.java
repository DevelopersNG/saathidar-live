package com.sathidar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sathidar.model.EmailSMSAlertModel;
import com.sathidar.repository.EmailSMSRepository;

@Service
public class EmailSMSAlertServiceImpl implements EmailSMSAlertService{

	@Autowired
	private EmailSMSRepository emailSMSRepository;
	
	@Override
	public List<EmailSMSAlertModel> getEmailAlert() {
		return emailSMSRepository.getEmailAlert();
	}

	@Override
	public int getDailyEmailStatusByMemberID(String member_id) {
		return emailSMSRepository.getDailyEmailStatusByMemberID(member_id);
	}

	@Override
	public String getPremiumMatchesIDS(Integer member_id) {
		return emailSMSRepository.getPremiumMatchesIDS(member_id);
	}

	@Override
	public List<Object[]> getMemberDeyailsForMail(String getIDS) {
		return emailSMSRepository.getMemberDeyailsForMail(getIDS);
	}

	@Override
	public List<Object[]> getUserNameEmailId(int member_id) {
		return emailSMSRepository.getUserNameEmailId(member_id);
	}

}
