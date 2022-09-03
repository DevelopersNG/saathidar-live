package com.sathidar.EntityMangerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberPreferenceManagerFactory {

	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	public GetNameByIDMangerFactory getNameByIDMangerFactory;
	
	public HashMap<String, String> getMemberPreferenceDetails(int memberID) {
		HashMap<String, String> map = new HashMap<>();
//		String columnName = "mp.gender,lifestyle,job,education,fromage,toage,"
//				+ "(select cast_name from cast where cast_id=(select cast_id from member_preference where member_id= :memberID and status='ACTIVE')) as castName,"
//				+ "(select subcast_name from subcasts where subcast_id=(select subcaste_id from member_preference where member_id= :memberID and status='ACTIVE')) as subcastName,"
//				+ "(select religion_name from religion where religion_id=(select religion_id from member_preference where member_id= :memberID and status='ACTIVE')) as religionName,"
//				+ "(select state_name from states where state_id=(select state_id from member_preference where member_id= :memberID and status='ACTIVE')) as stateName,"
//				+ "(select city_name from city where city_id=(select city_id from member_preference where member_id= :memberID and status='ACTIVE')) as cityName"
//				+ " ";
	 
		String columnName="partner_from_age,partner_to_age,partner_from_height,partner_to_height,partner_manglik_all,partner_annual_income,partner_profile_created,partner_lifestyles,"
				+ "partner_marital_status,partner_mother_tongue,partner_qualification,partner_working_with,partner_professional_area,"
				+ "partner_religions,partner_cast,partner_country,partner_state,partner_city";
		Query q = em.createNativeQuery("select " + columnName
				+ " from member_preference where member_id= :memberID and status='ACTIVE'");
		q.setParameter("memberID", memberID);

//		System.out.println("select " + columnName
//				+ " from member_preference as mp join member as m on mp.member_id=m.member_id where mp.member_id= :memberID and  m.status='ACTIVE' and mp.status='ACTIVE'");
		JSONArray resultArray = new JSONArray();
		List<Object[]> results = q.getResultList();

		boolean status = false;
		if (results != null) {
			for (Object[] obj : results) {
				int i = 0;
					map.put("partner_from_age", String.valueOf(obj[i]));
					map.put("partner_to_age", String.valueOf(obj[++i]));
					map.put("partner_from_height", String.valueOf(obj[++i]));
					map.put("partner_to_height", String.valueOf(obj[++i]));
					map.put("partner_manglik_all", String.valueOf(obj[++i]));
					map.put("partner_annual_income", String.valueOf(obj[++i]));
					map.put("partner_profile_created", String.valueOf(obj[++i]));
					map.put("partner_lifestyles", String.valueOf(obj[++i]));
					map.put("partner_marital_status", String.valueOf(obj[++i]));
					
					map.put("partner_mother_tongue", String.valueOf(obj[++i]));
					map.put("partner_qualification", String.valueOf(obj[++i]));
					map.put("partner_working_with", String.valueOf(obj[++i]));
					
					map.put("partner_professional_area", String.valueOf(obj[++i]));
					
					String religionsName=checkIsQuammaSeperatedValue(String.valueOf(obj[++i]),"religions");
					map.put("partner_religions",religionsName);
					
					String cast_name=checkIsQuammaSeperatedValue(String.valueOf(obj[++i]),"cast");
					map.put("partner_cast", cast_name);
					
					String country_name=checkIsQuammaSeperatedValue(String.valueOf(obj[++i]),"country");
					map.put("partner_country", country_name);
				
					String state=checkIsQuammaSeperatedValue(String.valueOf(obj[++i]),"state");
					map.put("partner_state", state);
					
					String city=checkIsQuammaSeperatedValue(String.valueOf(obj[++i]),"city");
					map.put("partner_city",city);
				status = true;
			}
		}
		if (status == false) {
			map.put("message", "Record not found");
		}

		return map;
	}

		
	public String checkIsQuammaSeperatedValue(String val,String category) {
		String results="";
		if(val!=null && !val.equals("")) {
			if(val.contains(",")){
				String[] splitArray=val.split(",");
				for(int i=0;i<splitArray.length;i++) {
					if(i==0) {
						if(category.equals("religions")) {
							results=getNameByIDMangerFactory.getReligionNameByID(splitArray[i]);
						}
						if(category.equals("cast")) {
							results=getNameByIDMangerFactory.getCasteNameByID(splitArray[i]);
						}
						if(category.equals("country")) {
							results=getNameByIDMangerFactory.getCountryNameByID(splitArray[i]);
						}
						if(category.equals("state")) {
							results=getNameByIDMangerFactory.getStateNameByID(splitArray[i]);
						}
						if(category.equals("city")) {
							results=getNameByIDMangerFactory.getCityNameByID(splitArray[i]);
						}
					}else { 
						if(category.equals("religions")) {
							if(splitArray[i]!=null && !splitArray[i].equals("") && !splitArray[i].equals("0")) {
								results=results+","+getNameByIDMangerFactory.getReligionNameByID(splitArray[i]);
							}
						}
						if(category.equals("cast")) {
							if(splitArray[i]!=null && !splitArray[i].equals("") && !splitArray[i].equals("0")) {
								results=results+","+getNameByIDMangerFactory.getCasteNameByID(splitArray[i]);
							}
						}
						if(category.equals("country")) {
							if(splitArray[i]!=null && !splitArray[i].equals("") && !splitArray[i].equals("0")) {
								results=results+","+getNameByIDMangerFactory.getCountryNameByID(splitArray[i]);
							}
						}
						if(category.equals("state")) {
							if(splitArray[i]!=null && !splitArray[i].equals("") && !splitArray[i].equals("0")) {
								results=results+","+getNameByIDMangerFactory.getStateNameByID(splitArray[i]);
							}
						}
						if(category.equals("city")) {
							if(splitArray[i]!=null && !splitArray[i].equals("") && !splitArray[i].equals("0")) {
								results=results+","+getNameByIDMangerFactory.getCityNameByID(splitArray[i]);
							}
						}
					}
				}
			}else {
				if(category.equals("religions")) {
					results=getNameByIDMangerFactory.getReligionNameByID(val);
				}
				if(category.equals("cast")) {
					results=getNameByIDMangerFactory.getCasteNameByID(val);
				}
				if(category.equals("country")) {
					results=getNameByIDMangerFactory.getCountryNameByID(val);
				}
				if(category.equals("state")) {
					results=getNameByIDMangerFactory.getStateNameByID(val);
				}
				if(category.equals("city")) {
					results=getNameByIDMangerFactory.getCityNameByID(val);
				}
			}
		}
		
		return results;
	}
	
	
	@Transactional
	public JSONArray deleteMemberPreferenceDetails(int member_id) {
		int statusCount=0;
		JSONArray resultArray = new JSONArray();
		try {
			Query queryMemberDeativate = em.createNativeQuery(
					"update member_preference set status='DEACTIVATE' where member_id= :memberID and status='ACTIVE'");
			System.out.println("update member_preference set status='DEACTIVATE' where member_id= :memberID and status='ACTIVE'");
			queryMemberDeativate.setParameter("memberID", member_id);
			statusCount = queryMemberDeativate.executeUpdate();
			JSONObject json = new JSONObject();
			if(statusCount>0) {
				json.put("message", "preference deleted..");
				resultArray.put(json);
			}else {
				json.put("message", "record not deleted..");
				resultArray.put(json);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultArray;
	}

}
