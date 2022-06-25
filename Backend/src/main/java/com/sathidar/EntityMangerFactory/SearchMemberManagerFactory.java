package com.sathidar.EntityMangerFactory;

import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchMemberManagerFactory {

	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	public GetNameByIDMangerFactory getNameByIDMangerFactory;

	public HashMap<String, String> getSearchMemberDetails(int member_id) {
		HashMap<String, String> map = new HashMap<>();
		
		
		
		String columnName="search_from_age,search_to_age,search_from_height,search_to_height,"
				+ "search_marital_status,search_mother_tongue,"
				+ "search_religions,search_cast,search_country,search_state,search_city";
		Query q = em.createNativeQuery("select " + columnName
				+ " from search_members where member_id= :memberID and status='ACTIVE'");
		q.setParameter("memberID", member_id);
		
		JSONArray resultArray = new JSONArray();
		List<Object[]> results = q.getResultList();

		boolean status = false;
		if (results != null) {
			for (Object[] obj : results) {
				int i = 0;
					map.put("search_from_age", String.valueOf(obj[i]));
					map.put("search_to_age", String.valueOf(obj[++i]));
					map.put("search_from_height", String.valueOf(obj[++i]));
					map.put("search_to_height", String.valueOf(obj[++i]));
					map.put("search_marital_status", String.valueOf(obj[++i]));
					
					map.put("search_mother_tongue", String.valueOf(obj[++i]));
					
					String religionsName=checkIsQuammaSeperatedValue(String.valueOf(obj[++i]),"religions");
					map.put("search_religions",religionsName);
					
					String cast_name=checkIsQuammaSeperatedValue(String.valueOf(obj[++i]),"cast");
					map.put("search_cast", cast_name);
					
					String country_name=checkIsQuammaSeperatedValue(String.valueOf(obj[++i]),"country");
					map.put("search_country", country_name);
				
					String state=checkIsQuammaSeperatedValue(String.valueOf(obj[++i]),"state");
					map.put("search_state", state);
					
					String city=checkIsQuammaSeperatedValue(String.valueOf(obj[++i]),"city");
					map.put("search_city",city);
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
							results=results+","+getNameByIDMangerFactory.getReligionNameByID(splitArray[i]);
						}
						if(category.equals("cast")) {
							results=results+","+getNameByIDMangerFactory.getCasteNameByID(splitArray[i]);
						}
						if(category.equals("country")) {
							results=results+","+getNameByIDMangerFactory.getCountryNameByID(splitArray[i]);
						}
						if(category.equals("state")) {
							results=results+","+getNameByIDMangerFactory.getStateNameByID(splitArray[i]);
						}
						if(category.equals("city")) {
							results=results+","+getNameByIDMangerFactory.getCityNameByID(splitArray[i]);
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
	
	
}
