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

}
