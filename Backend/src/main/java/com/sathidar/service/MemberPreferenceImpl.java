package com.sathidar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sathidar.EntityMangerFactory.GetNameByIDMangerFactory;
import com.sathidar.exception.BadRequestException;
import com.sathidar.model.MemberPreferenceModel;
import com.sathidar.model.UpdateMember;
import com.sathidar.repository.MemberPreferenceRepository;

@Service
public class MemberPreferenceImpl implements MemberPreferenceService {

	@Autowired
	private MemberPreferenceRepository memberPreferenceRepository;

	@Autowired
	private GetNameByIDMangerFactory getNameByIDMangerFactory;
	
	@Override
	public Object updateMemberPreference(MemberPreferenceModel memberPreferenceModel, int id) {

//		String gender="",lifestyle="",job="",education="";
//		int cast_id=0,subcaste_id=0,religion_id=0,state_id=0,city_id=0,fromage=0,toage=0;

			
		Object MemberPreferenceObject=null;
		try {
//			gender=memberPreferenceModel.getGender().trim();
//			lifestyle=memberPreferenceModel.getLifestyle().trim();
//			job=memberPreferenceModel.getJob().trim();
//			education=memberPreferenceModel.getEducation();
//			
//			cast_id= getNameByIDMangerFactory.getCasteID(memberPreferenceModel.getCast_name().trim());
//			subcaste_id= getNameByIDMangerFactory.getSubCasteIdByName(memberPreferenceModel.getSub_cast_name().trim());
//			religion_id= getNameByIDMangerFactory.getReligionID(memberPreferenceModel.getReligion_name().trim());
//			state_id= getNameByIDMangerFactory.getStateIdByName(memberPreferenceModel.getState_name().trim());
//			city_id= getNameByIDMangerFactory.getCityidByName(memberPreferenceModel.getCity_name().trim());
//			
//			fromage=memberPreferenceModel.getFromage();
//			toage=memberPreferenceModel.getToage();

			String partner_marital_status="",partner_mother_tongue="",partner_qualification="",
					partner_working_with="",partner_professional_area="",partner_from_age="",partner_to_age="",
							partner_from_height="",partner_to_height="",partner_manglik_all="",partner_annual_income="",
									partner_profile_created="",partner_lifestyles="",partner_religions="",partner_cast="",partner_country="",partner_state="",partner_city="";
			
			partner_marital_status=checkNullValue(memberPreferenceModel.getPartner_marital_status());
			partner_mother_tongue=checkNullValue(memberPreferenceModel.getPartner_mother_tongue());
			partner_qualification=checkNullValue(memberPreferenceModel.getPartner_qualification());
			partner_working_with=checkNullValue(memberPreferenceModel.getPartner_working_with());
			partner_professional_area=checkNullValue(memberPreferenceModel.getPartner_professional_area());

//			partner_religions=checkNullValue(memberPreferenceModel.getPartner_religions());
//			partner_cast=checkNullValue(memberPreferenceModel.getPartner_cast());
//			partner_country=checkNullValue(memberPreferenceModel.getPartner_country());
//			partner_state=checkNullValue(memberPreferenceModel.getPartner_state());
//			partner_city=checkNullValue(memberPreferenceModel.getPartner_city());
			
			partner_religions=checkIsQuammaSeperatedValue(memberPreferenceModel.getPartner_religions(),"religions");
			partner_cast=checkIsQuammaSeperatedValue(memberPreferenceModel.getPartner_cast(),"cast");
			partner_country=checkIsQuammaSeperatedValue(memberPreferenceModel.getPartner_country(),"country");
			partner_state=checkIsQuammaSeperatedValue(memberPreferenceModel.getPartner_state(),"state");
			partner_city=checkIsQuammaSeperatedValue(memberPreferenceModel.getPartner_city(),"city");
		
			partner_from_age=checkNullValue(memberPreferenceModel.getPartner_from_age());
			partner_to_age=checkNullValue(memberPreferenceModel.getPartner_to_age());
			partner_from_height=checkNullValue(memberPreferenceModel.getPartner_from_height());
			partner_to_height=checkNullValue(memberPreferenceModel.getPartner_to_height());
			partner_manglik_all=checkNullValue(memberPreferenceModel.getPartner_manglik_all());
			partner_annual_income=checkNullValue(memberPreferenceModel.getPartner_annual_income());
			partner_profile_created=checkNullValue(memberPreferenceModel.getPartner_profile_created());
			partner_lifestyles=checkNullValue(memberPreferenceModel.getPartner_lifestyles());
			
			
//		    MemberPreferenceObject = memberPreferenceRepository.updateMemberPreference(id,gender,lifestyle,job,education,
//					cast_id,subcaste_id,religion_id,state_id,city_id,fromage,toage);
			int checkAvailable=memberPreferenceRepository.checkAvailablePartnerPreference(id);
			if(checkAvailable>0) {
		    MemberPreferenceObject = memberPreferenceRepository.updateNewMemberPreference(id,partner_marital_status,partner_mother_tongue,partner_qualification,
		    		partner_working_with,partner_professional_area,partner_religions,partner_cast,partner_country,partner_state,
		    		partner_city,partner_from_age,partner_to_age,partner_from_height,partner_to_height,partner_manglik_all,partner_annual_income,
		    		partner_profile_created,partner_lifestyles);
			}else {
				 MemberPreferenceObject = memberPreferenceRepository.insertNewMemberPreference(id,partner_marital_status,partner_mother_tongue,partner_qualification,
				    		partner_working_with,partner_professional_area,partner_religions,partner_cast,partner_country,partner_state,
				    		partner_city,partner_from_age,partner_to_age,partner_from_height,partner_to_height,partner_manglik_all,partner_annual_income,
				    		partner_profile_created,partner_lifestyles);
			}
			
			if (MemberPreferenceObject == null) {
				throw new BadRequestException("member preference not updated");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return MemberPreferenceObject;
	}
	
	public String checkNullValue(String value) {
		
		if(value==null)
			value="";

	return value;
	}

	
	public String checkIsQuammaSeperatedValue(String val,String category) {
		String results="";
		if(val!=null && !val.equals("")) {
			if(val.contains(",")){
				String[] splitArray=val.split(",");
				for(int i=0;i<splitArray.length;i++) {
					if(i==0) {
						if(category.equals("religions")) {
							results=""+getNameByIDMangerFactory.getReligionID(splitArray[i]);
						}
						if(category.equals("cast")) {
							results=""+getNameByIDMangerFactory.getCasteID(splitArray[i]);
						}
						if(category.equals("country")) {
							results=""+getNameByIDMangerFactory.getCountryIdByName(splitArray[i]);
						}
						if(category.equals("state")) {
							results=""+getNameByIDMangerFactory.getStateIdByName(splitArray[i]);
						}
						if(category.equals("city")) {
							results=""+getNameByIDMangerFactory.getCityidByName(splitArray[i]);
						}
						
					}else { 
						if(category.equals("religions")) {
							results=results+","+getNameByIDMangerFactory.getReligionID(splitArray[i]);
						}
						if(category.equals("cast")) {
							results=results+","+getNameByIDMangerFactory.getCasteID(splitArray[i]);
						}
						if(category.equals("country")) {
							results=results+","+getNameByIDMangerFactory.getCountryIdByName(splitArray[i]);
						}
						if(category.equals("state")) {
							results=results+","+getNameByIDMangerFactory.getStateIdByName(splitArray[i]);
						}
						if(category.equals("city")) {
							results=results+","+getNameByIDMangerFactory.getCityidByName(splitArray[i]);
						}
					}
				}
			}else {
				if(category.equals("religions")) {
					results=""+getNameByIDMangerFactory.getReligionID(val);
				}
				if(category.equals("cast")) {
					results=""+getNameByIDMangerFactory.getCasteID(val);
				}
				if(category.equals("country")) {
					results=""+getNameByIDMangerFactory.getCountryIdByName(val);
				}
				if(category.equals("state")) {
					results=""+getNameByIDMangerFactory.getStateIdByName(val);
				}
				if(category.equals("city")) {
					results=""+getNameByIDMangerFactory.getCityidByName(val);
				}
			}
		}
		
		return results;
	}
	
	
//	@Override
//	public MemberPreferenceModel getMemberPreferenceDetails(int id) {
//		return memberPreferenceRepository.getMemberPreferenceDetails(id);
//	}
}
