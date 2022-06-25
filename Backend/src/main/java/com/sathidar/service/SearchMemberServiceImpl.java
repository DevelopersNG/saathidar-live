package com.sathidar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sathidar.EntityMangerFactory.GetNameByIDMangerFactory;
import com.sathidar.exception.BadRequestException;
import com.sathidar.model.MemberPreferenceModel;
import com.sathidar.model.SearchMembersModel;
import com.sathidar.repository.SearchMemberRepository;
@Service
public class SearchMemberServiceImpl  implements SearchMemberService{

	@Autowired
	private SearchMemberRepository searchMemberRepository;
	
	@Autowired
	private GetNameByIDMangerFactory getNameByIDMangerFactory;
	
	@Override
	public Object updateSearchMemberDetails(SearchMembersModel searchMembersModel, int id) {
		Object searchMemberObject=null;
		
		try {
			String search_marital_status="",search_mother_tongue="",search_from_age="",search_to_age="",
							search_from_height="",search_to_height="",search_religions="",search_cast="",search_country="",search_state="",search_city="";
			
			
			search_marital_status=checkNullValue(searchMembersModel.getSearch_marital_status());
			search_mother_tongue=checkNullValue(searchMembersModel.getSearch_mother_tongue());
			search_religions=checkIsQuammaSeperatedValue(searchMembersModel.getSearch_religions(),"religions");
			search_cast=checkIsQuammaSeperatedValue(searchMembersModel.getSearch_cast(),"cast");
			search_country=checkIsQuammaSeperatedValue(searchMembersModel.getSearch_country(),"country");
			search_state=checkIsQuammaSeperatedValue(searchMembersModel.getSearch_state(),"state");
			search_city=checkIsQuammaSeperatedValue(searchMembersModel.getSearch_city(),"city");
			search_from_age=checkNullValue(searchMembersModel.getSearch_from_age());
			search_to_age=checkNullValue(searchMembersModel.getSearch_to_age());
			search_from_height=checkNullValue(searchMembersModel.getSearch_from_height());
			search_to_height=checkNullValue(searchMembersModel.getSearch_to_height());
			
			int checkAvailable=searchMemberRepository.checkAvailablePartnerPreference(id);
			if(checkAvailable>0) {
				searchMemberObject = searchMemberRepository.updateNewMemberPreference(id,search_marital_status,search_mother_tongue,search_religions,search_cast,search_country,search_state,
				    		search_city,search_from_age,search_to_age,search_from_height,search_to_height);
			}else {
					searchMemberObject = searchMemberRepository.insertNewMemberPreference(id,search_marital_status,search_mother_tongue,search_religions,search_cast,search_country,search_state,
				    		search_city,search_from_age,search_to_age,search_from_height,search_to_height);
				}
			if (searchMemberObject == null) {
				throw new BadRequestException("search member not updated");
			}
		} catch (Exception e) {
		e.printStackTrace();
		}
		
		return searchMemberObject;
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
}
