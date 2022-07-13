package com.sathidar.service;

import java.util.HashMap;
import java.util.List;

import org.apache.catalina.core.ApplicationContext;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sathidar.model.PrivacyOptionsModel;
import com.sathidar.repository.PrivacyPolicyRepository;
import com.sathidar.util.PrivacyPoliyConstant;

@Service
public class PrivacyOptionsServiceImpl implements PrivacyOptionsService {

	@Autowired
	private PrivacyPolicyRepository privacyPolicyRepository;

//	@Autowired
//	private PrivacyPoliyConstant privacyPoliyConstant;

	@Override
	public int updatePhonePrivacy(PrivacyOptionsModel privacyOptionsModel) {
		int getStatus = privacyPolicyRepository.findByMember_Id(privacyOptionsModel.getMember_id());
		PrivacyPoliyConstant privacyPoliyConstant = new PrivacyPoliyConstant();

		int keyCode = 0;
		if (!privacyOptionsModel.getValPhone().equals("")) {
			for (java.util.Map.Entry<Integer, String> entry : privacyPoliyConstant.lst_Phone.entrySet()) {
				if (entry.getValue().equals(privacyOptionsModel.getValPhone())) {
					System.out.println(entry.getKey());
					keyCode = entry.getKey();
				}
			}

			if (keyCode > 0) {
				if (getStatus > 0) {
					// update
					return privacyPolicyRepository.updatePhonePrivacy(privacyOptionsModel.getMember_id(), keyCode);

				} else {
					// insert
					return privacyPolicyRepository.insertPhonePrivacy(privacyOptionsModel.getMember_id(), keyCode);
				}
			}
		}
		return 1;
	}

	@Override
	public int updateEmailPrivacy(PrivacyOptionsModel privacyOptionsModel) {
		int getStatus = privacyPolicyRepository.findByMember_Id(privacyOptionsModel.getMember_id());
		PrivacyPoliyConstant privacyPoliyConstant = new PrivacyPoliyConstant();

		int keyCode = 0;
		if (!privacyOptionsModel.getValEmail().equals("")) {
			for (java.util.Map.Entry<Integer, String> entry : privacyPoliyConstant.lst_Email.entrySet()) {
				if (entry.getValue().equals(privacyOptionsModel.getValEmail())) {
					keyCode = entry.getKey();
				}
			}
			if (keyCode > 0) {
				if (getStatus > 0) {
					// update
					return privacyPolicyRepository.updateEmailPrivacy(privacyOptionsModel.getMember_id(), keyCode);

				} else {
					// insert
					return privacyPolicyRepository.insertEmailPrivacy(privacyOptionsModel.getMember_id(), keyCode);
				}
			}
		}
		return 1;
	}

	@Override
	public int updatePhotoPrivacy(PrivacyOptionsModel privacyOptionsModel) {
		int getStatus = privacyPolicyRepository.findByMember_Id(privacyOptionsModel.getMember_id());
		PrivacyPoliyConstant privacyPoliyConstant = new PrivacyPoliyConstant();

		int keyCode = 0;
		if (!privacyOptionsModel.getValPhoto().equals("")) {
			for (java.util.Map.Entry<Integer, String> entry : privacyPoliyConstant.lst_Photo.entrySet()) {
				if (entry.getValue().equals(privacyOptionsModel.getValPhoto())) {
					keyCode = entry.getKey();
				}
			}
			if (keyCode > 0) {
				if (getStatus > 0) {
					// update
					return privacyPolicyRepository.updatePhotoPrivacy(privacyOptionsModel.getMember_id(), keyCode);

				} else {
					// insert
					return privacyPolicyRepository.insertPhotoPrivacy(privacyOptionsModel.getMember_id(), keyCode);
				}
			}
		}
		return 1;
	}

	@Override
	public int updateDOBPrivacy(PrivacyOptionsModel privacyOptionsModel) {
		int getStatus = privacyPolicyRepository.findByMember_Id(privacyOptionsModel.getMember_id());
		PrivacyPoliyConstant privacyPoliyConstant = new PrivacyPoliyConstant();

		int keyCode = 0;
		if (!privacyOptionsModel.getValDob().equals("")) {
			for (java.util.Map.Entry<Integer, String> entry : privacyPoliyConstant.lst_DOB.entrySet()) {
				if (entry.getValue().equals(privacyOptionsModel.getValDob())) {
					keyCode = entry.getKey();
				}
			}
			if (keyCode > 0) {
				if (getStatus > 0) {
					// update
					return privacyPolicyRepository.updateDOBPrivacy(privacyOptionsModel.getMember_id(), keyCode);

				} else {
					// insert
					return privacyPolicyRepository.insertDOBPrivacy(privacyOptionsModel.getMember_id(), keyCode);
				}
			}
		}
		return 1;
	}

	@Override
	public int updateAnnualIncomePrivacy(PrivacyOptionsModel privacyOptionsModel) {
		int getStatus = privacyPolicyRepository.findByMember_Id(privacyOptionsModel.getMember_id());
		PrivacyPoliyConstant privacyPoliyConstant = new PrivacyPoliyConstant();

		int keyCode = 0;
		if (!privacyOptionsModel.getValAnnual_income().equals("")) {
			for (java.util.Map.Entry<Integer, String> entry : privacyPoliyConstant.lst_Annual_Income.entrySet()) {
				if (entry.getValue().equals(privacyOptionsModel.getValAnnual_income())) {
					keyCode = entry.getKey();
				}
			}
			if (keyCode > 0) {
				if (getStatus > 0) {
					// update
					return privacyPolicyRepository.updateAnnualIncomePrivacy(privacyOptionsModel.getMember_id(),
							keyCode);

				} else {
					// insert
					return privacyPolicyRepository.insertAnnualIncomePrivacy(privacyOptionsModel.getMember_id(),
							keyCode);
				}
			}
		}
		return 1;
	}

	@Override
	public int updateHoroscopePrivacy(PrivacyOptionsModel privacyOptionsModel) {
		int getStatus = privacyPolicyRepository.findByMember_Id(privacyOptionsModel.getMember_id());
		PrivacyPoliyConstant privacyPoliyConstant = new PrivacyPoliyConstant();

		int keyCode = 0;
		if (!privacyOptionsModel.getValHoroscope().equals("")) {
			for (java.util.Map.Entry<Integer, String> entry : privacyPoliyConstant.lst_Horoscope.entrySet()) {
				if (entry.getValue().equals(privacyOptionsModel.getValHoroscope())) {
					keyCode = entry.getKey();
				}
			}
			if (keyCode > 0) {
				if (getStatus > 0) {
					// update
					return privacyPolicyRepository.updateHoroscopePrivacy(privacyOptionsModel.getMember_id(), keyCode);

				} else {
					// insert
					return privacyPolicyRepository.insertHoroscopePrivacy(privacyOptionsModel.getMember_id(), keyCode);
				}
			}
		}
		return 1;
	}

	@Override
	public int updateVisitorSettingPrivacy(PrivacyOptionsModel privacyOptionsModel) {
		int getStatus = privacyPolicyRepository.findByMember_Id(privacyOptionsModel.getMember_id());
		PrivacyPoliyConstant privacyPoliyConstant = new PrivacyPoliyConstant();

		int keyCode = 0;
		if (!privacyOptionsModel.getValVisitors_setting().equals("")) {
			for (java.util.Map.Entry<Integer, String> entry : privacyPoliyConstant.lst_VisitorsSettings.entrySet()) {
				if (entry.getValue().equals(privacyOptionsModel.getValVisitors_setting())) {
					System.out.println(entry.getKey());
					keyCode = entry.getKey();
				}
			}
			if (keyCode > 0) {
				if (getStatus > 0) {
					// update
					return privacyPolicyRepository.updateVisitorSettingsPrivacy(privacyOptionsModel.getMember_id(),
							keyCode);

				} else {
					// insert
					return privacyPolicyRepository.insertVisitorSettingsPrivacy(privacyOptionsModel.getMember_id(),
							keyCode);
				}
			}
		}
		return 1;
	}

	@Override
	public int updateShortlistSettingPrivacy(PrivacyOptionsModel privacyOptionsModel) {
		int getStatus = privacyPolicyRepository.findByMember_Id(privacyOptionsModel.getMember_id());
		PrivacyPoliyConstant privacyPoliyConstant = new PrivacyPoliyConstant();

		int keyCode = 0;
		if (!privacyOptionsModel.getValShortlist_setting().equals("")) {
			for (java.util.Map.Entry<Integer, String> entry : privacyPoliyConstant.lst_ShortlistSettings.entrySet()) {
				if (entry.getValue().equals(privacyOptionsModel.getValShortlist_setting())) {
					System.out.println(entry.getKey());
					keyCode = entry.getKey();
				}
			}

			if (keyCode > 0) {
				if (getStatus > 0) {
					// update
					return privacyPolicyRepository.updateShortlistPrivacy(privacyOptionsModel.getMember_id(), keyCode);

				} else {
					// insert
					return privacyPolicyRepository.insertShortlistPrivacy(privacyOptionsModel.getMember_id(), keyCode);
				}
			}
		}
		return 1;
	}

	@Override
	public int updateProfilePrivacyPrivacy(PrivacyOptionsModel privacyOptionsModel) {
		int getStatus = privacyPolicyRepository.findByMember_Id(privacyOptionsModel.getMember_id());
		PrivacyPoliyConstant privacyPoliyConstant = new PrivacyPoliyConstant();

		int keyCode = 0;
		if (!privacyOptionsModel.getValProfile_privacy().equals("")) {
			for (java.util.Map.Entry<Integer, String> entry : privacyPoliyConstant.lst_ProfilePrivacy.entrySet()) {
				if (entry.getValue().equals(privacyOptionsModel.getValProfile_privacy())) {
					System.out.println(entry.getKey());
					keyCode = entry.getKey();
				}
			}

			if (keyCode > 0) {
				if (getStatus > 0) {
					// update
					return privacyPolicyRepository.updateProfilePrivacy(privacyOptionsModel.getMember_id(), keyCode);

				} else {
					// insert
					return privacyPolicyRepository.insertProfileePrivacy(privacyOptionsModel.getMember_id(), keyCode);
				}
			}
		}
		return 1;
	}

	@Override
	public JSONArray GetAllPrivacy(String member_id) {
		JSONArray resultArray = new JSONArray();

		try {

			List<Object[]> results = privacyPolicyRepository.getAllPrivacy(member_id);
			if (results != null) {
				for (Object[] obj : results) {
					int i = 0;
					JSONObject json = new JSONObject();

					String phone = convertNullToBlank(String.valueOf(obj[i]));
					String email = convertNullToBlank(String.valueOf(obj[++i]));
					String photo = convertNullToBlank(String.valueOf(obj[++i]));
					String dob = convertNullToBlank(String.valueOf(obj[++i]));
					String annual_income = convertNullToBlank(String.valueOf(obj[++i]));
					String horoscope = convertNullToBlank(String.valueOf(obj[++i]));
					String visitors_setting = convertNullToBlank(String.valueOf(obj[++i]));
					String shortlist_setting = convertNullToBlank(String.valueOf(obj[++i]));
					String profile_privacy = convertNullToBlank(String.valueOf(obj[++i]));

					PrivacyPoliyConstant privacyPoliyConstant = new PrivacyPoliyConstant();

					String val_Phone = "", val_email = "", val_photo = "", val_dob = "", val_annual_income = "";
					String val_horoscope = "", val_visitors_setting = "", val_shortlist_setting = "",
							val_profile_privacy = "";

					// Phone
					if (!phone.equals("")) {
						for (java.util.Map.Entry<Integer, String> entry : privacyPoliyConstant.lst_Phone.entrySet()) {
							if (entry.getKey() == Integer.parseInt(phone)) {
								System.out.println("phone entry" + entry.getValue());
								val_Phone = entry.getValue();
							}
						}
					}

					// Email
					if (!email.equals("")) {
						for (java.util.Map.Entry<Integer, String> entry : privacyPoliyConstant.lst_Email.entrySet()) {
							if (entry.getKey() == Integer.parseInt(email)) {
								System.out.println(entry.getValue());
								val_email = entry.getValue();
							}
						}
					}

					// Photo
					if (!photo.equals("")) {
						for (java.util.Map.Entry<Integer, String> entry : privacyPoliyConstant.lst_Photo.entrySet()) {
							if (entry.getKey() == Integer.parseInt(photo)) {
								System.out.println(entry.getValue());
								val_photo = entry.getValue();
							}
						}
					}

					// DOB
					if (!dob.equals("")) {
						for (java.util.Map.Entry<Integer, String> entry : privacyPoliyConstant.lst_DOB.entrySet()) {
							if (entry.getKey() == Integer.parseInt(dob)) {
								System.out.println(entry.getValue());
								val_dob = entry.getValue();
							}
						}
					}

					// Anuual INcome
					if (!annual_income.equals("")) {
						for (java.util.Map.Entry<Integer, String> entry : privacyPoliyConstant.lst_Annual_Income
								.entrySet()) {
							if (entry.getKey() == Integer.parseInt(annual_income)) {
								System.out.println(entry.getValue());
								val_annual_income = entry.getValue();
							}
						}
					}

					// Horoscope
					if (!horoscope.equals("")) {
						for (java.util.Map.Entry<Integer, String> entry : privacyPoliyConstant.lst_Horoscope
								.entrySet()) {
							if (entry.getKey() == Integer.parseInt(horoscope)) {
								System.out.println(entry.getValue());
								val_horoscope = entry.getValue();
							}
						}
					}

					// visitors settings
					if (!visitors_setting.equals("")) {
						for (java.util.Map.Entry<Integer, String> entry : privacyPoliyConstant.lst_VisitorsSettings
								.entrySet()) {
							if (entry.getKey() == Integer.parseInt(visitors_setting)) {
								System.out.println(entry.getValue());
								val_visitors_setting = entry.getValue();
							}
						}
					}

					// shortlist_setting
					if (!shortlist_setting.equals("")) {
						for (java.util.Map.Entry<Integer, String> entry : privacyPoliyConstant.lst_ShortlistSettings
								.entrySet()) {
							if (entry.getKey() == Integer.parseInt(shortlist_setting)) {
								System.out.println(entry.getValue());
								val_shortlist_setting = entry.getValue();
							}
						}
					}

					// profile_setting
					if (!profile_privacy.equals("")) {
						for (java.util.Map.Entry<Integer, String> entry : privacyPoliyConstant.lst_ProfilePrivacy
								.entrySet()) {
							if (entry.getKey() == Integer.parseInt(profile_privacy)) {
								System.out.println(entry.getValue());
								val_profile_privacy = entry.getValue();
							}
						}
					}

					json.put("phone", val_Phone);
					json.put("email", val_email);
					json.put("photo", val_photo);
					json.put("dob", val_dob);
					json.put("annual_income", val_annual_income);
					json.put("horoscope", val_horoscope);
					json.put("visitors_setting", val_visitors_setting);
					json.put("shortlist_setting", val_shortlist_setting);
					json.put("profile_privacy", val_profile_privacy);
					resultArray.put(json);
				}
			}else {
				resultArray=null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultArray;
	}

	public String convertNullToBlank(String value) {
		if (value != null && !value.equals("") && !value.equals("null")) {
			return value;
		}
		return "";
	}

	@Override
	public int updateEmailSMSSetting(PrivacyOptionsModel privacyOptionsModel) {
		int getStatus = privacyPolicyRepository.findByMember_Id_SMS_ALERT(privacyOptionsModel.getMember_id());

		int keyCode = 0;
		String premium_match_mail = convertNullToBlank(privacyOptionsModel.getPremium_match_mail());
		String recent_visitors_email = convertNullToBlank(privacyOptionsModel.getRecent_visitors_email());
		String contact_alert = convertNullToBlank(privacyOptionsModel.getContact_alert());
		String sms_alert = convertNullToBlank(privacyOptionsModel.getSms_alert());
		String message_received_alert = convertNullToBlank(privacyOptionsModel.getMessage_received_alert());
		String today_match_email = convertNullToBlank(privacyOptionsModel.getToday_match_email());
		if (getStatus > 0) {
			// update
			return privacyPolicyRepository.updateeEmailSMS(privacyOptionsModel.getMember_id(), premium_match_mail,
					recent_visitors_email, contact_alert, sms_alert, message_received_alert,today_match_email);
		} else {
			// insert
			return privacyPolicyRepository.inserteEmailSMS(privacyOptionsModel.getMember_id(), premium_match_mail,
					recent_visitors_email, contact_alert, sms_alert, message_received_alert,today_match_email);
		}
	}

	@Override
	public JSONArray GetAllEmailSMSSetting(String member_id) {
		JSONArray resultArray = new JSONArray();
		try {
			List<Object[]> results = privacyPolicyRepository.GetAllEmailSMSSetting(member_id);
			if (results != null) {
				for (Object[] obj : results) {
					int i = 0;
					JSONObject json = new JSONObject();
					json.put("premium_match_mail", convertNullToBlank(String.valueOf(obj[i])));
					json.put("recent_visitors_email", convertNullToBlank(String.valueOf(obj[++i])));
					json.put("contact_alert", convertNullToBlank(String.valueOf(obj[++i])));
					json.put("sms_alert", convertNullToBlank(String.valueOf(obj[++i])));
					json.put("message_received_alert", convertNullToBlank(String.valueOf(obj[++i])));
					json.put("today_match_email", convertNullToBlank(String.valueOf(obj[++i])));
					resultArray.put(json);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultArray;
	}
}
