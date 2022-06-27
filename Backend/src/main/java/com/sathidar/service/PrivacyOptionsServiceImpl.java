package com.sathidar.service;

import java.util.HashMap;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sathidar.model.PrivacyOptionsModel;
import com.sathidar.repository.PrivacyPolicyRepository;
import com.sathidar.util.PrivacyPoliyConstant;

@Service
public class PrivacyOptionsServiceImpl implements PrivacyOptionsService{

	@Autowired
	private PrivacyPolicyRepository privacyPolicyRepository;
	
//	@Autowired
//	private PrivacyPoliyConstant privacyPoliyConstant;
	
	@Override
	public int updatePhonePrivacy(PrivacyOptionsModel privacyOptionsModel) {
		int getStatus=privacyPolicyRepository.findByMember_Id(privacyOptionsModel.getMember_id());
		 PrivacyPoliyConstant privacyPoliyConstant=new PrivacyPoliyConstant();
		
		int keyCode=0;
		if(!privacyOptionsModel.getValPhone().equals("")) {
			for (java.util.Map.Entry<Integer, String> entry : privacyPoliyConstant.lst_Phone.entrySet()) {
	            if (entry.getValue().equals(privacyOptionsModel.getValPhone())) {
	                System.out.println(entry.getKey());
	                keyCode=entry.getKey();
	            }
	        }
			
			if(keyCode>0) {
		
			
			if(getStatus>0) {
				//update
				return privacyPolicyRepository.updatePhonePrivacy(privacyOptionsModel.getMember_id(),keyCode);
				
			}else {
				//insert
				return privacyPolicyRepository.insertPhonePrivacy(privacyOptionsModel.getMember_id(),keyCode);
			}
			}
		}
		return 1;
	}

	@Override
	public int updateEmailPrivacy(PrivacyOptionsModel privacyOptionsModel) {
		int getStatus=privacyPolicyRepository.findByMember_Id(privacyOptionsModel.getMember_id());
		 PrivacyPoliyConstant privacyPoliyConstant=new PrivacyPoliyConstant();
		
		int keyCode=0;
		if(!privacyOptionsModel.getValEmail().equals("")) {
			for (java.util.Map.Entry<Integer, String> entry : privacyPoliyConstant.lst_Email.entrySet()) {
	            if (entry.getValue().equals(privacyOptionsModel.getValEmail())) {
	                keyCode=entry.getKey();
	            }
	        }
			if(keyCode>0) {
			if(getStatus>0) {
				//update
				return privacyPolicyRepository.updateEmailPrivacy(privacyOptionsModel.getMember_id(),keyCode);
				
			}else {
				//insert
				return privacyPolicyRepository.insertEmailPrivacy(privacyOptionsModel.getMember_id(),keyCode);
			}
			}
		}
		return 1;
	}

	@Override
	public int updatePhotoPrivacy(PrivacyOptionsModel privacyOptionsModel) {
		int getStatus=privacyPolicyRepository.findByMember_Id(privacyOptionsModel.getMember_id());
		 PrivacyPoliyConstant privacyPoliyConstant=new PrivacyPoliyConstant();
		
		int keyCode=0;
		if(!privacyOptionsModel.getValPhoto().equals("")) {
			for (java.util.Map.Entry<Integer, String> entry : privacyPoliyConstant.lst_Photo.entrySet()) {
	            if (entry.getValue().equals(privacyOptionsModel.getValPhoto())) {
	                keyCode=entry.getKey();
	            }
	        }
			if(keyCode>0) {
			if(getStatus>0) {
				//update
				return privacyPolicyRepository.updatePhotoPrivacy(privacyOptionsModel.getMember_id(),keyCode);
				
			}else {
				//insert
				return privacyPolicyRepository.insertPhotoPrivacy(privacyOptionsModel.getMember_id(),keyCode);
			}
			}
		}
		return 1;
	}

	@Override
	public int updateDOBPrivacy(PrivacyOptionsModel privacyOptionsModel) {
		int getStatus=privacyPolicyRepository.findByMember_Id(privacyOptionsModel.getMember_id());
		 PrivacyPoliyConstant privacyPoliyConstant=new PrivacyPoliyConstant();
		
		int keyCode=0;
		if(!privacyOptionsModel.getValDob().equals("")) {
			for (java.util.Map.Entry<Integer, String> entry : privacyPoliyConstant.lst_DOB.entrySet()) {
	            if (entry.getValue().equals(privacyOptionsModel.getValDob())) {
	                keyCode=entry.getKey();
	            }
	        }
			if(keyCode>0) {
			if(getStatus>0) {
				//update
				return privacyPolicyRepository.updateDOBPrivacy(privacyOptionsModel.getMember_id(),keyCode);
				
			}else {
				//insert
				return privacyPolicyRepository.insertDOBPrivacy(privacyOptionsModel.getMember_id(),keyCode);
			}
			}
		}
		return 1;
	}

	@Override
	public int updateAnnualIncomePrivacy(PrivacyOptionsModel privacyOptionsModel) {
		int getStatus=privacyPolicyRepository.findByMember_Id(privacyOptionsModel.getMember_id());
		 PrivacyPoliyConstant privacyPoliyConstant=new PrivacyPoliyConstant();
		
		int keyCode=0;
		if(!privacyOptionsModel.getValAnnual_income().equals("")) {
			for (java.util.Map.Entry<Integer, String> entry : privacyPoliyConstant.lst_Annual_Income.entrySet()) {
	            if (entry.getValue().equals(privacyOptionsModel.getValAnnual_income())) {
	                keyCode=entry.getKey();
	            }
	        }
			if(keyCode>0) {
			if(getStatus>0) {
				//update
				return privacyPolicyRepository.updateAnnualIncomePrivacy(privacyOptionsModel.getMember_id(),keyCode);
				
			}else {
				//insert
				return privacyPolicyRepository.insertAnnualIncomePrivacy(privacyOptionsModel.getMember_id(),keyCode);
			}
		}}
		return 1;
	}

	@Override
	public int updateHoroscopePrivacy(PrivacyOptionsModel privacyOptionsModel) {
		int getStatus=privacyPolicyRepository.findByMember_Id(privacyOptionsModel.getMember_id());
		 PrivacyPoliyConstant privacyPoliyConstant=new PrivacyPoliyConstant();
		
		int keyCode=0;
		if(!privacyOptionsModel.getValHoroscope().equals("")) {
			for (java.util.Map.Entry<Integer, String> entry : privacyPoliyConstant.lst_Horoscope.entrySet()) {
	            if (entry.getValue().equals(privacyOptionsModel.getValHoroscope())) {
	                keyCode=entry.getKey();
	            }
	        }
			if(keyCode>0) {
			if(getStatus>0) {
				//update
				return privacyPolicyRepository.updateHoroscopePrivacy(privacyOptionsModel.getMember_id(),keyCode);
				
			}else {
				//insert
				return privacyPolicyRepository.insertHoroscopePrivacy(privacyOptionsModel.getMember_id(),keyCode);
			}
			}
		}
		return 1;
	}

	@Override
	public int updateVisitorSettingPrivacy(PrivacyOptionsModel privacyOptionsModel) {
		int getStatus=privacyPolicyRepository.findByMember_Id(privacyOptionsModel.getMember_id());
		 PrivacyPoliyConstant privacyPoliyConstant=new PrivacyPoliyConstant();
		
		int keyCode=0;
		if(!privacyOptionsModel.getValVisitors_setting().equals("")) {
			for (java.util.Map.Entry<Integer, String> entry : privacyPoliyConstant.lst_VisitorsSettings.entrySet()) {
	            if (entry.getValue().equals(privacyOptionsModel.getValVisitors_setting())) {
	                System.out.println(entry.getKey());
	                keyCode=entry.getKey();
	            }
	        }
			if(keyCode>0) {
			if(getStatus>0) {
				//update
				return privacyPolicyRepository.updateVisitorSettingsPrivacy(privacyOptionsModel.getMember_id(),keyCode);
				
			}else {
				//insert
				return privacyPolicyRepository.insertVisitorSettingsPrivacy(privacyOptionsModel.getMember_id(),keyCode);
			}
			}
		}
		return 1;
	}

	@Override
	public int updateShortlistSettingPrivacy(PrivacyOptionsModel privacyOptionsModel) {
		int getStatus=privacyPolicyRepository.findByMember_Id(privacyOptionsModel.getMember_id());
		 PrivacyPoliyConstant privacyPoliyConstant=new PrivacyPoliyConstant();
		
		int keyCode=0;
		if(!privacyOptionsModel.getValShortlist_setting().equals("")) {
			for (java.util.Map.Entry<Integer, String> entry : privacyPoliyConstant.lst_ShortlistSettings.entrySet()) {
	            if (entry.getValue().equals(privacyOptionsModel.getValShortlist_setting())) {
	                System.out.println(entry.getKey());
	                keyCode=entry.getKey();
	            }
	        }
			
			if(keyCode>0) {
			if(getStatus>0) {
				//update
				return privacyPolicyRepository.updateShortlistPrivacy(privacyOptionsModel.getMember_id(),keyCode);
				
			}else {
				//insert
				return privacyPolicyRepository.insertShortlistPrivacy(privacyOptionsModel.getMember_id(),keyCode);
			}
			}
		}
		return 1;
	}

	@Override
	public int updateProfilePrivacyPrivacy(PrivacyOptionsModel privacyOptionsModel) {
		int getStatus=privacyPolicyRepository.findByMember_Id(privacyOptionsModel.getMember_id());
		 PrivacyPoliyConstant privacyPoliyConstant=new PrivacyPoliyConstant();
		
		int keyCode=0;
		if(!privacyOptionsModel.getValProfile_privacy().equals("")) {
			for (java.util.Map.Entry<Integer, String> entry : privacyPoliyConstant.lst_ProfilePrivacy.entrySet()) {
	            if (entry.getValue().equals(privacyOptionsModel.getValProfile_privacy())) {
	                System.out.println(entry.getKey());
	                keyCode=entry.getKey();
	            }
	        }
			
			if(keyCode>0) {
			if(getStatus>0) {
				//update
				return privacyPolicyRepository.updateProfilePrivacy(privacyOptionsModel.getMember_id(),keyCode);
				
			}else {
				//insert
				return privacyPolicyRepository.insertProfileePrivacy(privacyOptionsModel.getMember_id(),keyCode);
			}
			}
		}
		return 1;
	}
}
