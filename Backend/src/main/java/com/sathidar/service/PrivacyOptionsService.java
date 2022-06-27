package com.sathidar.service;

import org.springframework.stereotype.Service;

import com.sathidar.model.PrivacyOptionsModel;

@Service
public interface PrivacyOptionsService {

	int updatePhonePrivacy(PrivacyOptionsModel privacyOptionsModel);

	int updateEmailPrivacy(PrivacyOptionsModel privacyOptionsModel);

	int updatePhotoPrivacy(PrivacyOptionsModel privacyOptionsModel);

	int updateDOBPrivacy(PrivacyOptionsModel privacyOptionsModel);

	int updateAnnualIncomePrivacy(PrivacyOptionsModel privacyOptionsModel);

	int updateHoroscopePrivacy(PrivacyOptionsModel privacyOptionsModel);

	int updateVisitorSettingPrivacy(PrivacyOptionsModel privacyOptionsModel);

	int updateShortlistSettingPrivacy(PrivacyOptionsModel privacyOptionsModel);

	int updateProfilePrivacyPrivacy(PrivacyOptionsModel privacyOptionsModel);

	
	
}
