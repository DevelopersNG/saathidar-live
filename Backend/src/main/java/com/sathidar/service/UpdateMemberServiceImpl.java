package com.sathidar.service;

import java.text.DecimalFormat;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.sathidar.EntityMangerFactory.GetNameByIDMangerFactory;
import com.sathidar.exception.BadRequestException;
import com.sathidar.model.UpdateMember;
import com.sathidar.repository.UpdateMemberRepository;

@Service
public class UpdateMemberServiceImpl implements UpdateMemberService {

	@Autowired
	UpdateMemberRepository updateMemberRepository;
	
	@Autowired
	GetNameByIDMangerFactory getNameByIDMangerFactory;

	public String checkNullValue(String value) {
		
		if(value!=null && !value.equals("null")) {
			return value;
		}

	return "";
	}
	
	
	public int checkNullValueForNumeric(int value) {
		
		if(Integer.toString(value) != null) {
			return value;
		}

	return 0;
	}

	@Override
	public Object UpdateBasicLifeCycleMemberDetails(UpdateMember updateMember, int id) {
		Object memberUpdateStatus=null;
		try {
			double dHeight = 0.0, dWeight = 0.0;
			String  mHeight = "", mWeight = "" ,mNative = "", mLifeStyles = "", mKnown_Languages = "", mEducation = "", mJob = "", mIncome = "",
					mHobbies = "", mother_tounge = "", marital_status = "", noOfChildren = "", dateOfBirth = "",
					mExpectation = "",mhealth_info="",mblood_group="",mgothra="",methnic_corigin="",mpincode="",mabout_ourself="",subCast="",
					prodile_created="",gender="",manglik="",nakshtra="";
			int religionID = 0, casteID = 0, subCasteID = 0, age = 0,stateID=0,cityID=0,countryID=0;
	
			
			try {
				prodile_created= checkNullValue(updateMember.getProfilecreatedby().trim());
				gender= checkNullValue(updateMember.getGender().trim());
				Object memberTbl=updateMemberRepository.UpdateInMemberTable(prodile_created,gender,id);	
				memberUpdateStatus=true;
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
				mHeight=checkNullValue(updateMember.getHeight().trim());
				mblood_group=checkNullValue(updateMember.getBlood_group().trim());
				mLifeStyles = checkNullValue(updateMember.getLifestyles().trim());
				mother_tounge = checkNullValue(updateMember.getMother_tounge().trim());
				mhealth_info=checkNullValue(updateMember.getHealth_info().trim());
				religionID= getNameByIDMangerFactory.getReligionID(checkNullValue(updateMember.getReligion_name().trim()));
				casteID= getNameByIDMangerFactory.getCasteID(checkNullValue(updateMember.getCaste_name().trim()));
				subCast=checkNullValue(updateMember.getSub_caste_name().trim());
				mgothra=checkNullValue(updateMember.getGothra().trim());
				mabout_ourself=checkNullValue(updateMember.getAbout_ourself().trim());

				
				
				
				mNative ="";
			//				mWeight=checkNullValue(updateMember.getWeight().trim());
		//				mKnown_Languages = checkNullValue(updateMember.getKnown_languages().trim());
//				mEducation = checkNullValue(updateMember.getEducation().trim());
//				mJob = checkNullValue(updateMember.getJob().trim());
//				mIncome = checkNullValue(updateMember.getIncome().trim());
//				mHobbies = checkNullValue(updateMember.getHobbies().trim());
//				mExpectation = checkNullValue(updateMember.getExpectations().trim());
//				methnic_corigin=checkNullValue(updateMember.getEthnic_corigin().trim());
//				mpincode=checkNullValue(updateMember.getPincode().trim());
				// ------- 23-3-2022 start------------
//				religionID = updateMemberRepository.getReligionID(updateMember.getReligion_name().trim());
//				casteID = updateMemberRepository.getCasteIDByReligionID(updateMember.getCaste_name().trim(), religionID);
//				subCasteID = updateMemberRepository.getSubCasteID(updateMember.getSub_caste_name().trim(), casteID);
//				stateID = updateMemberRepository.getStateID(updateMember.getState_name().trim());
//				cityID = updateMemberRepository.getCityID(updateMember.getCity_name().trim(), stateID);

//				subCasteID= getNameByIDMangerFactory.getSubCasteIdByName(updateMember.getSub_caste_name().trim());
	//				stateID= getNameByIDMangerFactory.getStateIdByName(checkNullValue(updateMember.getState_name().trim()));
//				cityID= getNameByIDMangerFactory.getCityidByName(checkNullValue(updateMember.getCity_name().trim()));
//				countryID= getNameByIDMangerFactory.getCountryIdByName(checkNullValue(updateMember.getCountry_name().trim()));
//				religionID=updateMember.getReligion_id();
//				casteID=updateMember.getCast_id();
				
//				countryID=updateMember.getCountry_Id();
//				stateID=updateMember.getState_Id();
//				cityID=updateMember.getCity_Id();
				
				Object memberDetails= updateMemberRepository.UpdateAppBasicInfoMemberDetails(id,age,dateOfBirth,marital_status,noOfChildren,mHeight,mblood_group,mLifeStyles,
						mother_tounge,mhealth_info,religionID,casteID,subCast,mgothra,mabout_ourself);
				memberUpdateStatus=true;
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
		Object memberUpdateStatus=null;
		try {
			String mfather_status="",mfather_company_name="",mfather_designation="",mfather_business_name="",
					mmother_status="",mmother_company_name="",	mmother_designation="",	mmother_business_name="",
					mfamily_location="",mnative_place="",mfamily_type="",mfamily_values="",mfamily_affluence="",
					married_male="",unmarried_male="",married_female="",unmarried_female="";	
			
			mfather_status = checkNullValue(updateMember.getFather_status().trim());
			mfather_company_name=checkNullValue(updateMember.getFather_company_name().trim());
			mfather_designation=checkNullValue(updateMember.getFather_designation().trim());
			mfather_business_name=checkNullValue(updateMember.getFather_business_name().trim());
			
			mmother_status=checkNullValue(updateMember.getMother_status().trim());
			mmother_company_name=checkNullValue(updateMember.getMother_company_name().trim());
			mmother_designation=checkNullValue(updateMember.getMother_designation().trim());
			mmother_business_name=checkNullValue(updateMember.getMother_business_name().trim());
			
			mfamily_location=checkNullValue(updateMember.getFamily_location().trim());
			mnative_place=checkNullValue(updateMember.getNative_place().trim());
			mfamily_type=checkNullValue(updateMember.getFamily_type().trim());
//			mfamily_values=checkNullValue(updateMember.getFamily_values().trim());
			mfamily_affluence=checkNullValue(updateMember.getFamily_affluence().trim());
			
			married_male=checkNullValue(updateMember.getMarried_male());
			unmarried_male=checkNullValue(updateMember.getUnmarried_male());
			married_female=checkNullValue(updateMember.getMarried_female());
			unmarried_female=checkNullValue(updateMember.getUnmarried_female());
		
			
			Object memberFamily=updateMemberRepository.UpdatememberAppFamilyDetails(id,mfather_status,mfather_company_name,mfather_designation,mfather_business_name,
					mmother_status,mmother_company_name,mmother_designation,mmother_business_name,
					mfamily_location,mnative_place,mfamily_type,mfamily_affluence,married_male,unmarried_male,married_female,unmarried_female);
			memberUpdateStatus=true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return memberUpdateStatus;
	}


	@Override
	public Object updateAppProfessionalDetailsMember(UpdateMember updateMember, int id) {
		Object memberUpdateStatus=null;
		try {
			String mhighest_qualification="",mcollege_attended="",mworking_with="",mworking_as="",
					memployer_name="",mannual_income="",methnic_corigin="",mpincode="";		
			
			int countryID=0,stateID=0,cityID=0;
			
			mhighest_qualification=checkNullValue(updateMember.getHighest_qualification().trim());
			mcollege_attended=checkNullValue(updateMember.getCollege_attended().trim());
			mworking_with=checkNullValue(updateMember.getWorking_with().trim());
			mworking_as=checkNullValue(updateMember.getWorking_as().trim());
			memployer_name=checkNullValue(updateMember.getEmployer_name().trim());
			mannual_income=checkNullValue(updateMember.getAnnual_income().trim());
			
			Object memberEducationCareer=updateMemberRepository.UpdatememberEducationCareerDetails(id,mhighest_qualification,mcollege_attended,mworking_with,
					mworking_as,memployer_name,mannual_income);
			memberUpdateStatus=true;
			
			countryID= getNameByIDMangerFactory.getCountryIdByName(checkNullValue(updateMember.getCountry_name().trim()));
			stateID= getNameByIDMangerFactory.getStateIdByName(checkNullValue(updateMember.getState_name().trim()));
			cityID= getNameByIDMangerFactory.getCityidByName(checkNullValue(updateMember.getCity_name().trim()));
			methnic_corigin=checkNullValue(updateMember.getEthnic_corigin().trim());
			mpincode=checkNullValue(updateMember.getPincode().trim());

			Object memberLocationOfGroom=updateMemberRepository.updateLocationOfGroom(countryID,stateID,cityID,methnic_corigin,mpincode);
			memberUpdateStatus=true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return memberUpdateStatus;
	}

	
	@Override
	public Object UpdateMemberDetails(UpdateMember updateMember, int id) {

		Object memberUpdateStatus=null;
		double dHeight = 0.0, dWeight = 0.0;
		String  mHeight = "", mWeight = "" ,mNative = "", mLifeStyles = "", mKnown_Languages = "", mEducation = "", mJob = "", mIncome = "",
				mHobbies = "", mother_tounge = "", marital_status = "", noOfChildren = "", dateOfBirth = "",
				mExpectation = "",mhealth_info="",mblood_group="",mgothra="",methnic_corigin="",mpincode="",mabout_ourself="",subCast="",
				prodile_created="",gender="",manglik="",nakshtra="";
		int religionID = 0, casteID = 0, subCasteID = 0, age = 0,stateID=0,cityID=0,countryID=0;

		// family details
		String mfather_status="",mfather_company_name="",mfather_designation="",mfather_business_name="",
		mmother_status="",mmother_company_name="",mmother_designation="",mmother_business_name="",
		mfamily_location="",mnative_place="",mfamily_type="",mfamily_values="",mfamily_affluence="",
				married_male="",unmarried_male="",married_female="",unmarried_female="";
		
		// education details
		String mhighest_qualification="",mcollege_attended="",mworking_with="",mworking_as="",memployer_name="",mannual_income="";
		
		// add member details
		try {
			prodile_created= checkNullValue(updateMember.getProfilecreatedby().trim());
			gender= checkNullValue(updateMember.getGender().trim());
			Object memberTbl=updateMemberRepository.UpdateInMemberTable(prodile_created,gender,id);	
			memberUpdateStatus=true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		 
		
		// update member details
		try {
			updateMember.setId(id);
			mNative ="";
			mHeight=checkNullValue(updateMember.getHeight().trim());
//			mWeight=checkNullValue(updateMember.getWeight().trim());
			mLifeStyles = checkNullValue(updateMember.getLifestyles().trim());
			mKnown_Languages = checkNullValue(updateMember.getKnown_languages().trim());
//			mEducation = checkNullValue(updateMember.getEducation().trim());
//			mJob = checkNullValue(updateMember.getJob().trim());
//			mIncome = checkNullValue(updateMember.getIncome().trim());
//			mHobbies = checkNullValue(updateMember.getHobbies().trim());
//			mExpectation = checkNullValue(updateMember.getExpectations().trim());
			mhealth_info=checkNullValue(updateMember.getHealth_info().trim());
			mblood_group=checkNullValue(updateMember.getBlood_group().trim());
			mgothra=checkNullValue(updateMember.getGothra().trim());
			methnic_corigin=checkNullValue(updateMember.getEthnic_corigin().trim());
			mpincode=checkNullValue(updateMember.getPincode().trim());
			mabout_ourself=checkNullValue(updateMember.getAbout_ourself().trim());
			// ------- 23-3-2022 start------------
//			religionID = updateMemberRepository.getReligionID(updateMember.getReligion_name().trim());
//			casteID = updateMemberRepository.getCasteIDByReligionID(updateMember.getCaste_name().trim(), religionID);
//			subCasteID = updateMemberRepository.getSubCasteID(updateMember.getSub_caste_name().trim(), casteID);
//			stateID = updateMemberRepository.getStateID(updateMember.getState_name().trim());
//			cityID = updateMemberRepository.getCityID(updateMember.getCity_name().trim(), stateID);

			casteID= getNameByIDMangerFactory.getCasteID(checkNullValue(updateMember.getCaste_name().trim()));
//			subCasteID= getNameByIDMangerFactory.getSubCasteIdByName(updateMember.getSub_caste_name().trim());
			religionID= getNameByIDMangerFactory.getReligionID(checkNullValue(updateMember.getReligion_name().trim()));
			stateID= getNameByIDMangerFactory.getStateIdByName(checkNullValue(updateMember.getState_name().trim()));
			cityID= getNameByIDMangerFactory.getCityidByName(checkNullValue(updateMember.getCity_name().trim()));
			countryID= getNameByIDMangerFactory.getCountryIdByName(checkNullValue(updateMember.getCountry_name().trim()));
//			religionID=updateMember.getReligion_id();
//			casteID=updateMember.getCast_id();
			subCast=checkNullValue(updateMember.getSub_caste_name().trim());
			
//			countryID=updateMember.getCountry_Id();
//			stateID=updateMember.getState_Id();
//			cityID=updateMember.getCity_Id();
			
			mother_tounge = checkNullValue(updateMember.getMother_tounge().trim());
			marital_status = checkNullValue(updateMember.getMarital_status().trim());
			noOfChildren = checkNullValue(updateMember.getNo_of_children().trim());
			dateOfBirth = checkNullValue(updateMember.getDate_of_birth().trim());
			age = Integer.parseInt(checkNullValue(updateMember.getAge() + "".trim()));

			Object memberDetails= updateMemberRepository.UpdateMemberDetails(mNative, mHeight, mWeight, mLifeStyles, mKnown_Languages,
					mEducation, mJob, mIncome, mHobbies, mExpectation, id, mother_tounge, marital_status, noOfChildren,
					dateOfBirth, age, religionID, casteID, subCast, stateID, cityID,mhealth_info,mblood_group,mgothra,methnic_corigin,
					mpincode,mabout_ourself,countryID+"");
			memberUpdateStatus=true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		// update family details
		try {
			mfather_status = checkNullValue(updateMember.getFather_status().trim());
			mfather_company_name=checkNullValue(updateMember.getFather_company_name().trim());
			mfather_designation=checkNullValue(updateMember.getFather_designation().trim());
			mfather_business_name=checkNullValue(updateMember.getFather_business_name().trim());
			mmother_status=checkNullValue(updateMember.getMother_status().trim());
			mmother_company_name=checkNullValue(updateMember.getMother_company_name().trim());
			mmother_designation=checkNullValue(updateMember.getMother_designation().trim());
			mmother_business_name=checkNullValue(updateMember.getMother_business_name().trim());
			
			mfamily_location=checkNullValue(updateMember.getFamily_location().trim());
			mnative_place=checkNullValue(updateMember.getNative_place().trim());
			mfamily_type=checkNullValue(updateMember.getFamily_type().trim());
//			mfamily_values=checkNullValue(updateMember.getFamily_values().trim());
			mfamily_values="";
			mfamily_affluence=checkNullValue(updateMember.getFamily_affluence().trim());
			
			married_male=checkNullValue(updateMember.getMarried_male());
			unmarried_male=checkNullValue(updateMember.getUnmarried_male());
			married_female=checkNullValue(updateMember.getMarried_female());
			unmarried_female=checkNullValue(updateMember.getUnmarried_female());
		
			
			Object memberFamily=updateMemberRepository.UpdatememberFamilyDetails(id,mfather_status,mfather_company_name,mfather_designation,mfather_business_name,
					mmother_status,mmother_company_name,mmother_designation,mmother_business_name,
					mfamily_location,mnative_place,mfamily_type,mfamily_values,mfamily_affluence,married_male,unmarried_male,married_female,unmarried_female);
			memberUpdateStatus=true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		  //mhighest_qualification,
		// update qualifications
		try {
			mhighest_qualification=checkNullValue(updateMember.getHighest_qualification().trim());
			mcollege_attended=checkNullValue(updateMember.getCollege_attended().trim());
			mworking_with=checkNullValue(updateMember.getWorking_with().trim());
			mworking_as=checkNullValue(updateMember.getWorking_as().trim());
			memployer_name=checkNullValue(updateMember.getEmployer_name().trim());
			mannual_income=checkNullValue(updateMember.getAnnual_income().trim());
			
			Object memberEducationCareer=updateMemberRepository.UpdatememberEducationCareerDetails(id,mhighest_qualification,mcollege_attended,mworking_with,
					mworking_as,memployer_name,mannual_income);
			memberUpdateStatus=true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		// update horoscope details
		try {
			int isAvailablerRecords=updateMemberRepository.isAvailablerHoroscopeRecords(id);
			if(isAvailablerRecords>0) {
				manglik=checkNullValue(updateMember.getManglik());
				nakshtra=checkNullValue(updateMember.getNakshatra());
				Object memberHoroscope=updateMemberRepository.updateMemberHoroscopeRepository(id,manglik,nakshtra);
			}else {
				Object memberHoroscope=updateMemberRepository.insertMemberHoroscopeRepository(id,manglik,nakshtra);
			}
			memberUpdateStatus=true;
		} catch (Exception e) {
		e.printStackTrace();
		}
		
		
		return memberUpdateStatus;
		// ------- 23-3-2022 end------------

//		return updateMemberRepository.UpdateMemberDetails(mNative,mHeight,mWeight,mLifeStyles,mKnown_Languages,mEducation,mJob,mIncome,mHobbies,mExpectation, id);
	}




	@Override
	public UpdateMember getDetailsByMemberID(String member_number) {
		
		UpdateMember memberObject=updateMemberRepository.getDetailsByMemberID(member_number);
		if(memberObject==null) {
				throw new BadRequestException(" record not found..");
		}
				return memberObject;
	}



	@Override
	public Object updateHoroscopeDetails(UpdateMember updateMember, int member_id) {
		Object memberHoroscopeStatus=null;
		try {
			String country_of_birth= checkNullValue(updateMember.getCountry_of_birth()); 
			String city_of_birth= checkNullValue(updateMember.getCity_of_birth()); 
			String hours= checkNullValue(updateMember.getHours());
			String minutes= checkNullValue(updateMember.getMinutes());
			String time= checkNullValue(updateMember.getTime());
			String time_status= checkNullValue(updateMember.getTime_status());
			
			String time_of_birth=  checkNullValue(updateMember.getTime_of_birth());
			String manglik= checkNullValue(updateMember.getManglik());
			
			int isAvailablerRecords=updateMemberRepository.isAvailablerHoroscopeRecords(member_id);
			if(isAvailablerRecords>0) {
				memberHoroscopeStatus=updateMemberRepository.updateHoroscopeDetails(member_id,country_of_birth,city_of_birth, hours,minutes,time ,time_status, time_of_birth,manglik);
			}else {
				memberHoroscopeStatus =updateMemberRepository.insertHoroscopeDetails(member_id,country_of_birth,city_of_birth, hours,minutes,time ,time_status, time_of_birth,manglik);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return memberHoroscopeStatus;
	}


	@Override
	public int activateMember(UpdateMember updateMember) {
		int result=0;
		try {
			int res=updateMemberRepository.isMemberAvailable(updateMember.getMember_id());
			if(res>0) {
				 result=updateMemberRepository.updateMemberCurrentStatus(updateMember.getMember_id(),updateMember.getActivate_id());
			}else {
				 result=updateMemberRepository.insertMemberCurrentStatus(updateMember.getMember_id(),updateMember.getActivate_id());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int getActivateMember(int member_id) {
		int result=1;
		try {
			int res=updateMemberRepository.isMemberAvailable(member_id);
			if(res>0) {
				 result=updateMemberRepository.getActivateMember(member_id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}



	



}
