package com.sathidar.service;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Random;

import javax.transaction.Transactional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.sathidar.EntityMangerFactory.GetNameByIDMangerFactory;
import com.sathidar.EntityMangerFactory.UserEntityManagerFactory;
import com.sathidar.exception.BadRequestException;
import com.sathidar.model.UpdateMember;
import com.sathidar.model.UploadImagesModel;
import com.sathidar.model.User;
import com.sathidar.repository.UpdateMemberRepository;
import com.sathidar.repository.UserRepository;
import com.sathidar.util.SendSMSAction;
import com.sathidar.util.TextLocalSMSSetting;

@Service
public class UpdateMemberServiceImpl implements UpdateMemberService {

	@Autowired
	UpdateMemberRepository updateMemberRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	GetNameByIDMangerFactory getNameByIDMangerFactory;

	@Autowired
	UserEntityManagerFactory userEntityManagerFactory;
	
	@Autowired
	private SendSMSAction sendSMSAction;
	
	@Autowired
	private ServerEmailService serverEmailService;

	public String checkNullValue(String value) {

		if (value != null && !value.equals("null") && !value.equals("")) {
			return value;
		}

		return "";
	}

	public int checkNullValueForNumeric(int value) {

		if (Integer.toString(value) != null) {
			return value;
		}

		return 0;
	}

	@Override
	public Object UpdateBasicLifeCycleMemberDetails(UpdateMember updateMember, int id) {
		Object memberUpdateStatus = null;
		try {
			double dHeight = 0.0, dWeight = 0.0;
			String mHeight = "", mWeight = "", mNative = "", mLifeStyles = "", mKnown_Languages = "", mEducation = "",
					mJob = "", mIncome = "", mHobbies = "", mother_tounge = "", marital_status = "", noOfChildren = "",
					dateOfBirth = "", mExpectation = "", mhealth_info = "", mblood_group = "", mgothra = "",
					methnic_corigin = "", mpincode = "", mabout_ourself = "", subCast = "", prodile_created = "",
					gender = "", manglik = "", nakshtra = "";
			int religionID = 0, casteID = 0, subCasteID = 0, age = 0, stateID = 0, cityID = 0, countryID = 0;

			try {
//				prodile_created= checkNullValue(updateMember.getProfilecreatedby().trim());
				gender = checkNullValue(updateMember.getGender().trim());
				Object memberTbl = updateMemberRepository.UpdateAppInMemberTable(gender, id);
				memberUpdateStatus = true;
			} catch (Exception e) {
				e.printStackTrace();
			}

			// update member details
			try {
				updateMember.setId(id);

				age = Integer.parseInt(checkNullValue(updateMember.getAge() + "".trim()));
				dateOfBirth = checkNullValue(updateMember.getDate_of_birth().trim());
				marital_status = checkNullValue(updateMember.getMarital_status().trim());
				noOfChildren = checkNullValue(updateMember.getNo_of_children().trim());
				mHeight = checkNullValue(updateMember.getHeight().trim());
				mblood_group = checkNullValue(updateMember.getBlood_group().trim());
				mLifeStyles = checkNullValue(updateMember.getLifestyles().trim());
				mother_tounge = checkNullValue(updateMember.getMother_tounge().trim());
				mhealth_info = checkNullValue(updateMember.getHealth_info().trim());
				religionID = getNameByIDMangerFactory
						.getReligionID(checkNullValue(updateMember.getReligion_name().trim()));
				casteID = getNameByIDMangerFactory.getCasteID(checkNullValue(updateMember.getCaste_name().trim()));
				subCast = checkNullValue(updateMember.getSub_caste_name().trim());
				mgothra = checkNullValue(updateMember.getGothra().trim());
				mabout_ourself = checkNullValue(updateMember.getAbout_ourself().trim());

				mNative = "";
				Object memberDetails = updateMemberRepository.UpdateAppBasicInfoMemberDetails(id, age, dateOfBirth,
						marital_status, noOfChildren, mHeight, mblood_group, mLifeStyles, mother_tounge, mhealth_info,
						religionID, casteID, subCast, mgothra, mabout_ourself);
				memberUpdateStatus = true;
			
				// location of groom
				countryID = getNameByIDMangerFactory
						.getCountryIdByName(checkNullValue(updateMember.getCountry_name().trim()));
				stateID = getNameByIDMangerFactory.getStateIdByName(checkNullValue(updateMember.getState_name().trim()));
				cityID = getNameByIDMangerFactory.getCityidByName(checkNullValue(updateMember.getCity_name().trim()));
				methnic_corigin = checkNullValue(updateMember.getEthnic_corigin().trim());
				mpincode = checkNullValue(updateMember.getPincode().trim());

				Object memberLocationOfGroom = updateMemberRepository.updateLocationOfGroom(id, countryID, stateID, cityID,
						methnic_corigin, mpincode);
				memberUpdateStatus = true;
				
				
				// update horoscope details
				Object memberHoroscopeStatus = null;
				String country_of_birth = checkNullValue(updateMember.getCountry_of_birth());
				String city_of_birth = checkNullValue(updateMember.getCity_of_birth());
				String hours = checkNullValue(updateMember.getHours());
				String minutes = checkNullValue(updateMember.getMinutes());
				String time = checkNullValue(updateMember.getTime());
				String time_status = checkNullValue(updateMember.getTime_status());

				String time_of_birth = checkNullValue(updateMember.getTime_of_birth());
				manglik = checkNullValue(updateMember.getManglik());
				String date_of_birth = checkNullValue(updateMember.getDate_of_birth());
				int isAvailablerRecords = updateMemberRepository.isAvailablerHoroscopeRecords(id);

				System.out.println("isAvailablerRecords id- " + isAvailablerRecords);
				if (isAvailablerRecords > 0) {
					memberHoroscopeStatus = updateMemberRepository.updateHoroscopeDetails(id, country_of_birth,
							city_of_birth, hours, minutes, time, time_status, time_of_birth, manglik, date_of_birth);
				} else {
					memberHoroscopeStatus = updateMemberRepository.insertHoroscopeDetails(id, country_of_birth,
							city_of_birth, hours, minutes, time, time_status, time_of_birth, manglik, date_of_birth);
				}
				memberUpdateStatus = true;
			
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return memberUpdateStatus;
	}

	@Override
	public Object updateAppFamilyDetailsMember(UpdateMember updateMember, int id) {
		Object memberUpdateStatus = null;
		try {
			String mfather_status = "", mfather_company_name = "", mfather_designation = "", mfather_business_name = "",
					mmother_status = "", mmother_company_name = "", mmother_designation = "",
					mmother_business_name = "", mfamily_location = "", mnative_place = "", mfamily_type = "",
					mfamily_values = "", mfamily_affluence = "", married_male = "", unmarried_male = "",
					married_female = "", unmarried_female = "";

			mfather_status = checkNullValue(updateMember.getFather_status().trim());
			mfather_company_name = checkNullValue(updateMember.getFather_company_name().trim());
			mfather_designation = checkNullValue(updateMember.getFather_designation().trim());
			mfather_business_name = checkNullValue(updateMember.getFather_business_name().trim());

			mmother_status = checkNullValue(updateMember.getMother_status().trim());
			mmother_company_name = checkNullValue(updateMember.getMother_company_name().trim());
			mmother_designation = checkNullValue(updateMember.getMother_designation().trim());
			mmother_business_name = checkNullValue(updateMember.getMother_business_name().trim());

			mfamily_location = checkNullValue(updateMember.getFamily_location().trim());
			mnative_place = checkNullValue(updateMember.getNative_place().trim());
			mfamily_type = checkNullValue(updateMember.getFamily_type().trim());
//			mfamily_values=checkNullValue(updateMember.getFamily_values().trim());
			mfamily_affluence = checkNullValue(updateMember.getFamily_affluence().trim());

			married_male = checkNullValue(updateMember.getMarried_male());
			unmarried_male = checkNullValue(updateMember.getUnmarried_male());
			married_female = checkNullValue(updateMember.getMarried_female());
			unmarried_female = checkNullValue(updateMember.getUnmarried_female());

			Object memberFamily = updateMemberRepository.UpdatememberAppFamilyDetails(id, mfather_status,
					mfather_company_name, mfather_designation, mfather_business_name, mmother_status,
					mmother_company_name, mmother_designation, mmother_business_name, mfamily_location, mnative_place,
					mfamily_type, mfamily_affluence, married_male, unmarried_male, married_female, unmarried_female);
			memberUpdateStatus = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return memberUpdateStatus;
	}

	@Override
	public Object updateAppProfessionalDetailsMember(UpdateMember updateMember, int id) {
		Object memberUpdateStatus = null;
		try {
			String mhighest_qualification = "", mcollege_attended = "", mworking_with = "", mworking_as = "",
					ug_education="",memployer_name = "", mannual_income = "", methnic_corigin = "", mpincode = "";

			int countryID = 0, stateID = 0, cityID = 0;

			mhighest_qualification = checkNullValue(updateMember.getHighest_qualification().trim());
			mcollege_attended = checkNullValue(updateMember.getCollege_attended().trim());
			mworking_with = checkNullValue(updateMember.getWorking_with().trim());
			mworking_as = checkNullValue(updateMember.getWorking_as().trim());
//			memployer_name=checkNullValue(updateMember.getEmployer_name().trim());
			memployer_name = "";
			mannual_income = checkNullValue(updateMember.getAnnual_income().trim());
			ug_education=checkNullValue(updateMember.getUg_education().trim());
			
			Object memberEducationCareer = updateMemberRepository.UpdatememberEducationCareerDetails(id,
					mhighest_qualification, mcollege_attended, mworking_with, mworking_as, memployer_name,
					mannual_income,ug_education);
			memberUpdateStatus = true;

//			countryID = getNameByIDMangerFactory
//					.getCountryIdByName(checkNullValue(updateMember.getCountry_name().trim()));
//			stateID = getNameByIDMangerFactory.getStateIdByName(checkNullValue(updateMember.getState_name().trim()));
//			cityID = getNameByIDMangerFactory.getCityidByName(checkNullValue(updateMember.getCity_name().trim()));
//			methnic_corigin = checkNullValue(updateMember.getEthnic_corigin().trim());
//			mpincode = checkNullValue(updateMember.getPincode().trim());
//
//			Object memberLocationOfGroom = updateMemberRepository.updateLocationOfGroom(id, countryID, stateID, cityID,
//					methnic_corigin, mpincode);
//			memberUpdateStatus = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return memberUpdateStatus;
	}

	@Override
	public Object UpdateMemberDetails(UpdateMember updateMember, int id) {

		Object memberUpdateStatus = null;
		double dHeight = 0.0, dWeight = 0.0;
		String mHeight = "", mWeight = "", mNative = "", mLifeStyles = "", mKnown_Languages = "", mEducation = "",
				mJob = "", mIncome = "", mHobbies = "", mother_tounge = "", marital_status = "", noOfChildren = "",
				dateOfBirth = "", mExpectation = "", mhealth_info = "", mblood_group = "", mgothra = "",
				methnic_corigin = "", mpincode = "", mabout_ourself = "", subCast = "", prodile_created = "",
				gender = "", manglik = "", nakshtra = "";
		int religionID = 0, casteID = 0, subCasteID = 0, age = 0, stateID = 0, cityID = 0, countryID = 0;

		// family details
		String mfather_status = "", mfather_company_name = "", mfather_designation = "", mfather_business_name = "",
				mmother_status = "", mmother_company_name = "", mmother_designation = "", mmother_business_name = "",
				mfamily_location = "", mnative_place = "", mfamily_type = "", mfamily_values = "",
				mfamily_affluence = "", married_male = "", unmarried_male = "", married_female = "",
				unmarried_female = "";

		// education details
		String mhighest_qualification = "", mcollege_attended = "", mworking_with = "", mworking_as = "",
				memployer_name = "", mannual_income = "",ug_education="";

		// add member details
		try {
			prodile_created = checkNullValue(updateMember.getProfilecreatedby().trim());
			gender = checkNullValue(updateMember.getGender().trim());
			Object memberTbl = updateMemberRepository.UpdateInMemberTable(prodile_created, gender, id);
			memberUpdateStatus = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		// update member details
		try {
			updateMember.setId(id);
			mNative = "";
			mHeight = checkNullValue(updateMember.getHeight().trim());
//			mWeight=checkNullValue(updateMember.getWeight().trim());
			mLifeStyles = checkNullValue(updateMember.getLifestyles().trim());
			mKnown_Languages = checkNullValue(updateMember.getKnown_languages().trim());
//			mEducation = checkNullValue(updateMember.getEducation().trim());
//			mJob = checkNullValue(updateMember.getJob().trim());
//			mIncome = checkNullValue(updateMember.getIncome().trim());
//			mHobbies = checkNullValue(updateMember.getHobbies().trim());
//			mExpectation = checkNullValue(updateMember.getExpectations().trim());
			mhealth_info = checkNullValue(updateMember.getHealth_info().trim());
			mblood_group = checkNullValue(updateMember.getBlood_group().trim());
			mgothra = checkNullValue(updateMember.getGothra().trim());
			methnic_corigin = checkNullValue(updateMember.getEthnic_corigin().trim());
			mpincode = checkNullValue(updateMember.getPincode().trim());
			mabout_ourself = checkNullValue(updateMember.getAbout_ourself().trim());
			// ------- 23-3-2022 start------------
//			religionID = updateMemberRepository.getReligionID(updateMember.getReligion_name().trim());
//			casteID = updateMemberRepository.getCasteIDByReligionID(updateMember.getCaste_name().trim(), religionID);
//			subCasteID = updateMemberRepository.getSubCasteID(updateMember.getSub_caste_name().trim(), casteID);
//			stateID = updateMemberRepository.getStateID(updateMember.getState_name().trim());
//			cityID = updateMemberRepository.getCityID(updateMember.getCity_name().trim(), stateID);

			casteID = getNameByIDMangerFactory.getCasteID(checkNullValue(updateMember.getCaste_name().trim()));
//			subCasteID= getNameByIDMangerFactory.getSubCasteIdByName(updateMember.getSub_caste_name().trim());
			religionID = getNameByIDMangerFactory.getReligionID(checkNullValue(updateMember.getReligion_name().trim()));
			stateID = getNameByIDMangerFactory.getStateIdByName(checkNullValue(updateMember.getState_name().trim()));
			cityID = getNameByIDMangerFactory.getCityidByName(checkNullValue(updateMember.getCity_name().trim()));
			countryID = getNameByIDMangerFactory
					.getCountryIdByName(checkNullValue(updateMember.getCountry_name().trim()));
//			religionID=updateMember.getReligion_id();
//			casteID=updateMember.getCast_id();
			subCast = checkNullValue(updateMember.getSub_caste_name().trim());

//			countryID=updateMember.getCountry_Id();
//			stateID=updateMember.getState_Id();
//			cityID=updateMember.getCity_Id();

			mother_tounge = checkNullValue(updateMember.getMother_tounge().trim());
			marital_status = checkNullValue(updateMember.getMarital_status().trim());
			noOfChildren = checkNullValue(updateMember.getNo_of_children().trim());
			dateOfBirth = checkNullValue(updateMember.getDate_of_birth().trim());
			age = Integer.parseInt(checkNullValue(updateMember.getAge() + "".trim()));

			Object memberDetails = updateMemberRepository.UpdateMemberDetails(mNative, mHeight, mWeight, mLifeStyles,
					mKnown_Languages, mEducation, mJob, mIncome, mHobbies, mExpectation, id, mother_tounge,
					marital_status, noOfChildren, dateOfBirth, age, religionID, casteID, subCast, stateID, cityID,
					mhealth_info, mblood_group, mgothra, methnic_corigin, mpincode, mabout_ourself, countryID + "");
			memberUpdateStatus = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		// update family details
		try {
			mfather_status = checkNullValue(updateMember.getFather_status().trim());
			mfather_company_name = checkNullValue(updateMember.getFather_company_name().trim());
			mfather_designation = checkNullValue(updateMember.getFather_designation().trim());
			mfather_business_name = checkNullValue(updateMember.getFather_business_name().trim());
			mmother_status = checkNullValue(updateMember.getMother_status().trim());
			mmother_company_name = checkNullValue(updateMember.getMother_company_name().trim());
			mmother_designation = checkNullValue(updateMember.getMother_designation().trim());
			mmother_business_name = checkNullValue(updateMember.getMother_business_name().trim());

			mfamily_location = checkNullValue(updateMember.getFamily_location().trim());
			mnative_place = checkNullValue(updateMember.getNative_place().trim());
			mfamily_type = checkNullValue(updateMember.getFamily_type().trim());
//			mfamily_values=checkNullValue(updateMember.getFamily_values().trim());
			mfamily_values = "";
			mfamily_affluence = checkNullValue(updateMember.getFamily_affluence().trim());

			married_male = checkNullValue(updateMember.getMarried_male());
			unmarried_male = checkNullValue(updateMember.getUnmarried_male());
			married_female = checkNullValue(updateMember.getMarried_female());
			unmarried_female = checkNullValue(updateMember.getUnmarried_female());

			Object memberFamily = updateMemberRepository.UpdatememberFamilyDetails(id, mfather_status,
					mfather_company_name, mfather_designation, mfather_business_name, mmother_status,
					mmother_company_name, mmother_designation, mmother_business_name, mfamily_location, mnative_place,
					mfamily_type, mfamily_values, mfamily_affluence, married_male, unmarried_male, married_female,
					unmarried_female);
			memberUpdateStatus = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		// mhighest_qualification,
		// update qualifications
		try {
			mhighest_qualification = checkNullValue(updateMember.getHighest_qualification().trim());
			mcollege_attended = checkNullValue(updateMember.getCollege_attended().trim());
			mworking_with = checkNullValue(updateMember.getWorking_with().trim());
			mworking_as = checkNullValue(updateMember.getWorking_as().trim());
			memployer_name = checkNullValue(updateMember.getEmployer_name().trim());
			mannual_income = checkNullValue(updateMember.getAnnual_income().trim());
			ug_education=checkNullValue(updateMember.getUg_education().trim());
			Object memberEducationCareer = updateMemberRepository.UpdatememberEducationCareerDetails(id,
					mhighest_qualification, mcollege_attended, mworking_with, mworking_as, memployer_name,
					mannual_income, ug_education);
			memberUpdateStatus = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		// update horoscope details
		try {
			int isAvailablerRecords = updateMemberRepository.isAvailablerHoroscopeRecords(id);
			if (isAvailablerRecords > 0) {
				manglik = checkNullValue(updateMember.getManglik());
				nakshtra = checkNullValue(updateMember.getNakshatra());
				Object memberHoroscope = updateMemberRepository.updateMemberHoroscopeRepository(id, manglik, nakshtra);
			} else {
				Object memberHoroscope = updateMemberRepository.insertMemberHoroscopeRepository(id, manglik, nakshtra);
			}
			memberUpdateStatus = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return memberUpdateStatus;
		// ------- 23-3-2022 end------------

//		return updateMemberRepository.UpdateMemberDetails(mNative,mHeight,mWeight,mLifeStyles,mKnown_Languages,mEducation,mJob,mIncome,mHobbies,mExpectation, id);
	}

	@Override
	public UpdateMember getDetailsByMemberID(String member_number) {

		UpdateMember memberObject = updateMemberRepository.getDetailsByMemberID(member_number);
		if (memberObject == null) {
			throw new BadRequestException(" record not found..");
		}
		return memberObject;
	}

	@Override
	public Object updateHoroscopeDetails(UpdateMember updateMember, int member_id) {
		Object memberHoroscopeStatus = null;
		try {
			String country_of_birth = checkNullValue(updateMember.getCountry_of_birth());
			String city_of_birth = checkNullValue(updateMember.getCity_of_birth());
			String hours = checkNullValue(updateMember.getHours());
			String minutes = checkNullValue(updateMember.getMinutes());
			String time = checkNullValue(updateMember.getTime());
			String time_status = checkNullValue(updateMember.getTime_status());

			String time_of_birth = checkNullValue(updateMember.getTime_of_birth());
			String manglik = checkNullValue(updateMember.getManglik());
			String date_of_birth = checkNullValue(updateMember.getDate_of_birth());
			System.out.println("member id- " + member_id);
			int isAvailablerRecords = updateMemberRepository.isAvailablerHoroscopeRecords(member_id);

			System.out.println("isAvailablerRecords id- " + isAvailablerRecords);
			if (isAvailablerRecords > 0) {
				memberHoroscopeStatus = updateMemberRepository.updateHoroscopeDetails(member_id, country_of_birth,
						city_of_birth, hours, minutes, time, time_status, time_of_birth, manglik, date_of_birth);
				memberHoroscopeStatus = updateMemberRepository.updateDateOfBirthInMemberTable(member_id, date_of_birth);
			} else {
				memberHoroscopeStatus = updateMemberRepository.insertHoroscopeDetails(member_id, country_of_birth,
						city_of_birth, hours, minutes, time, time_status, time_of_birth, manglik, date_of_birth);
				memberHoroscopeStatus = updateMemberRepository.updateDateOfBirthInMemberTable(member_id, date_of_birth);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return memberHoroscopeStatus;
	}

	@Override
	public int activateMember(UpdateMember updateMember) {
		int result = 0;
		try {
			int res = updateMemberRepository.isMemberAvailable(updateMember.getMember_id());
			if (res > 0) {
				result = updateMemberRepository.updateMemberCurrentStatus(updateMember.getMember_id(),
						updateMember.getActivate_id());
			} else {
				result = updateMemberRepository.insertMemberCurrentStatus(updateMember.getMember_id(),
						updateMember.getActivate_id());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int getActivateMember(int member_id) {
		int result = 1;
		try {
			int res = updateMemberRepository.isMemberAvailable(member_id);
			if (res > 0) {
				result = updateMemberRepository.getActivateMember(member_id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	private String getOTP() {
		return new DecimalFormat("0000").format(new Random().nextInt(9999));
	}
	
	@Override
	public int updateRegistrationDetails(UpdateMember updateMember, int user_id) {
//		Object memberUpdateStatus = null;
//
//		double dHeight = 0.0;
//		String mHeight = "", marital_status = "", dateOfBirth = "", mLifeStyles = "";
//		int religionID = 0, countryID = 0;
//
//		// update member details
		int memberDetails = 0;
//		try {
//			User user = userRepository.getByUserID(user_id);
//			System.out.println(user);
//			int getRoleID = userEntityManagerFactory.getRoleID(user.get(0).getRole());
//			int member_id = userEntityManagerFactory.insertRecordToMemberTable(user, getRoleID, user_id);
////				int member_id=updateMemberRepository.getMemberIDByUserIDByMemberID(user_id);
//			if (member_id > 0) {
//				updateMember.setId(member_id);
//				System.out.println("save --- " + updateMember.getReligion());
//
//				religionID = getNameByIDMangerFactory.getReligionID(checkNullValue(updateMember.getReligion().trim()));
//				dateOfBirth = checkNullValue(updateMember.getDate_of_birth().trim());
//				marital_status = checkNullValue(updateMember.getMarital_status().trim());
//				mHeight = checkNullValue(updateMember.getHeight().trim());
//				countryID = getNameByIDMangerFactory
//						.getCountryIdByName(checkNullValue(updateMember.getCountry_name().trim()));
//				mLifeStyles = checkNullValue(updateMember.getLifestyles().trim());
//
//				memberDetails = updateMemberRepository.UpdateRegistrationDetails(member_id, dateOfBirth, marital_status,
//						mHeight, religionID, countryID, mLifeStyles);
//				if (memberDetails > 0) {
////						user_id=updateMemberRepository.getUserIDByMemberID(member_id);
//					int sts = updateMemberRepository.updateShortRegstInUserTable(user_id);
//				}
//				
//				// send otp 
//				String otp = this.getOTP();
//				TextLocalSMSSetting textLocalSMSSetting = new TextLocalSMSSetting();
//				String smsMessage = "Hi, your verification code is "+otp+"\r\n" + 
//						"Saathidaar.com";
//				String sender = "SDOTPM";
//				String phoneNo = "91" + user.get(0).getPhone().trim();
//				String response = textLocalSMSSetting.POSTSendSMS(phoneNo, sender, smsMessage);		
//				System.out.println("sms resonse - " + response);
//				
//				// save otp  
//				int sts = updateMemberRepository.saveOTPOfUserID(user.get(0).getPhone().trim(),user_id,otp);
//				
//				// send email
//				String to=user.get(0).getEmail();
//				String subject="Saathidaar Registration Confirmation";
//				try {
//					this.sendEmailTOUser(user.get(0).getFirstName(),user.get(0).getLastName(),user.get(0).getEmail(),user.get(0).getPhone(),user.get(0).getConfirmationToken());
////					 mailSender.send(to, subject, mailMessage);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}  
//				memberUpdateStatus = true;
//				memberDetails=1;
//			} else {
//				memberDetails=0;
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

		return memberDetails;
	}

	@Override
	public String getShortRegistrationStatus(int member_id) {
		int getUserID = updateMemberRepository.getUserIDByMemberID(member_id);
		return updateMemberRepository.getShortRegistrationStatus(getUserID);
	}

	@Override
	public String getMemberStatus(int login_member_id, String memberID) {
		return updateMemberRepository.getMemberStatus(login_member_id, memberID);
	}

	@Override
	public String getMemberBlockStatus(int login_id, String memberID) {
		return updateMemberRepository.getMemberBlockStatus(login_id, memberID);
	}
	
	private void sendEmailTOUser(String firstName, String lastName, String email, String phone,
			String confirmationToken) {
		String mailMessage="<div style=\"margin-top: 15px;\"> you can change username and password when confirmation is done.  </div><br>\n <div style=\"margin-top: 15px;\">To confirm your e-mail address, please click the link below:\n"
		  		+ "http://103.174.102.195:8080/saathidaar_backend/api/users/confirm?token="+confirmationToken +"<div>";
		try {
			String email_body="<head>\r\n" + 
					"    <meta charset=\"UTF-8\">\r\n" + 
					"    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n" + 
					"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n" + 
					"    <style>\r\n" + 
					"        .container{height: 150px; width: 400px;border: #742041 1px solid ;margin-top: 5px;}\r\n" + 
					"        .image{float: left;}\r\n" + 
					"        .details {float: right;}\r\n"
					+ "		 table{border-collapse: collapse;}" + 
					"       table tr th {text-align: left;border:1px solid}\r\n" + 
					"       table tr td { text-align: left;}\r\n" + 
					"       img{height: 150px;}\r\n" + 
					"       .bg{background-color: #742041;}\r\n" + 
					"    </style>\r\n" + 
					"</head>"
					+ "<body style=\"width: 400px;\">"
					+ "<div style=\"background-color: #742041;\"><img style=\"width:300px ;\" src=\"http://103.150.186.33:8080/saathidaar_logo/saathidaar_logo.jpeg\" alt=\"\"></div>"
					+ " <div class=\"image\">\r\n" + 
					"    <p style=\"float: left;\">Hi</p><br>\r\n" + 
					"   <h4 style=\"text-align: center;\">You have successfully completed user registration on <strong>saathidaar.com</strong></h4>\r\n" + 
					"   <table style=\"width: 100%;border: #742041 1px solid;\" class=\"table\">\r\n" + 
					"    <thead>\r\n" + 
					"      <tr >\r\n" + 
					"        <th scope=\"col\">User Email</th>\r\n" + 
					"        <th scope=\"col\">"+email+"</th>\r\n" + 
					"      </tr>\r\n" + 
					"      <tr>\r\n" + 
					"        <th scope=\"col\">User Password</th>\r\n" + 
					"        <th scope=\"col\">"+phone+"</th>\r\n" + 
					"      </tr>\r\n" + 
					"      <tr>\r\n" + 
					"        <th scope=\"col\">First Name </th>\r\n" + 
					"        <th scope=\"col\">"+firstName+"</th>\r\n" + 
					"      </tr>\r\n" + 
					"      <tr>\r\n" + 
					"        <th scope=\"col\">Last Name</th>\r\n" + 
					"        <th scope=\"col\">"+lastName+"</th>\r\n" + 
					"      </tr>\r\n" + 
					"    </thead>\r\n" + 
					"  </table>\r\n" + 
					" </div>\r\n" + 
					" <div class=\"details\">";
						email_body=email_body+mailMessage+ ""
					+ "</div>\r\n"
					+ "<br>"
					+ "<div style=\"margin-top: 15px;\">If you wish to make any changes please visit the My account page on the website.\r\n" + 
					"May you find your soulmate here! Thank You!\r</div>\n" + 
					"" + 
					"  </body>";
			
			serverEmailService.send(email, "Saathidar-Registrations", email_body);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getToDatePremiumMemberIDs(String to_date) {
		return updateMemberRepository.getToDatePremiumMemberIDs(to_date);
	}

	@Override
	public String getFromDatePremiumMemberIDs(String from_date) {
		return updateMemberRepository.getFromDatePremiumMemberIDs(from_date);
	}

	@Override
	public String getToDateNonPremiumMemberIDs(String to_date) {
		return updateMemberRepository.getToDateNonPremiumMemberIDs(to_date);
	}

	@Override
	public String getFromDateNonPremiumMemberIDs(String from_date) {
		return updateMemberRepository.getFromDateNonPremiumMemberIDs(from_date);
	}
}
