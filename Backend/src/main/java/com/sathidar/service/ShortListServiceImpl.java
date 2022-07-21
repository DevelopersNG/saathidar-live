package com.sathidar.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sathidar.EntityMangerFactory.GetNameByIDMangerFactory;
import com.sathidar.exception.BadRequestException;
import com.sathidar.model.ShortListsModel;
import com.sathidar.repository.ShortListsRepository;

@Service
public class ShortListServiceImpl implements ShortListService {

	@Autowired
	private ShortListsRepository shortListsRepository;
	
	@Autowired
	private GetNameByIDMangerFactory getNameByIDMangerFactory;
	
	@PersistenceContext
	private EntityManager em;

	@Autowired
	private UploadImagesService uploadImagesService;
	
	@Override
	public JSONArray AddToShortLists(ShortListsModel shortListsModel) {
		Object requestMemberObject = null;
		JSONArray resultArray = new JSONArray();

		try {
			JSONObject json = new JSONObject();
			String shortlist_from_id = shortListsModel.getShortlist_from_id();
			String shortlist_to_id = shortListsModel.getShortlist_to_id();
			String shortlist_status = shortListsModel.getShortlist_status();
			requestMemberObject = shortListsRepository.addToShortListMember(shortlist_from_id, shortlist_to_id,
					shortlist_status);
			json.put("message", "added to shortlists");
			json.put("results", "1");

			if (requestMemberObject == null) {
				json.put("message", "not added to shortlists");
				json.put("results", "0");
			}

			resultArray.put(json);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultArray;
	}

	@Override
	public JSONArray GetShortListsMember(String member_id) {
//		******************************Column Name******************************************************************
		 String columnName=getCommonColumnForSearch();
//		******************************Query************************************************************************
		 String getShortListIDS=getShortListsRequestedIDS(member_id);
			JSONArray resultArray = new JSONArray();
			String sentShortListQuery="";
		 if(getShortListIDS!=null && !getShortListIDS.equals("")) {
			 sentShortListQuery=" and md.member_id in ("+getShortListIDS+")";
			 
		Query q = em.createNativeQuery("SELECT " + columnName + "  FROM memberdetails as md "
				+ " join member as m on md.member_id=m.member_id"
				+ " join member_education_career as mec on m.member_id=mec.member_id "
				+ " where m.status='ACTIVE' and md.member_id!= :member_id "+sentShortListQuery);
		
		q.setParameter("member_id", member_id);
	
		List<Object[]> results = q.getResultList();
		if (results != null) {
			for (Object[] obj : results) {
				JSONObject json = new JSONObject();
				int count=0;
				json=getCommonJsonOutout(obj);		
				resultArray.put(json);
			}
		}else {
			resultArray=null;
		}
	}
		return resultArray;
	}
	
	private String getShortListsRequestedIDS(String member_id) {
		String ids="";
		try {
					Query query = em.createNativeQuery("SELECT group_concat(shortlist_to_id) FROM member_shortlists where  shortlist_from_id= :shortlist_from_id and shortlist_status= :shortlist_status");
					query.setParameter("shortlist_from_id", member_id);
					query.setParameter("shortlist_status", "add");
					ids = query.getSingleResult().toString();
		} catch (Exception e) {
		e.printStackTrace();
		}
				return ids;
	}

	private String getCommonColumnForSearch() {
		String columnName="m.member_id as member_id,height,lifestyles,known_languages,first_name,last_name,"
				+ "gender,md.age,contact_number,profilecreatedby,md.marital_status,mother_tounge,"
				+ "date_of_birth,mec.annual_income,country_id,cast_id,subcaste_id,religion_id,state_id,city_id";
		
		return columnName;
	}

	private JSONObject getCommonJsonOutout(Object[] obj) {
		JSONObject json = new JSONObject();
		try {
			int i = 0;
			String memberID=convertNullToBlank(String.valueOf(obj[i]));
			json.put("member_id",memberID );
			json.put("height", convertNullToBlank(String.valueOf(obj[++i])));
			json.put("lifestyles", convertNullToBlank(String.valueOf(obj[++i])));
			json.put("known_languages", convertNullToBlank(String.valueOf(obj[++i])));
			json.put("first_name", convertNullToBlank(String.valueOf(obj[++i])));
			json.put("last_name", convertNullToBlank(String.valueOf(obj[++i])));
			json.put("gender", convertNullToBlank(String.valueOf(obj[++i])));
			json.put("mage", convertNullToBlank(String.valueOf(obj[++i])));
			json.put("contact_number", convertNullToBlank(String.valueOf(obj[++i])));
			json.put("profilecreatedby", convertNullToBlank(String.valueOf(obj[++i])));
			json.put("maritalStatus", convertNullToBlank(String.valueOf(obj[++i])));
			json.put("mother_tounge", convertNullToBlank(String.valueOf(obj[++i])));
			json.put("date_of_birth", convertNullToBlank(String.valueOf(obj[++i])));
			json.put("income", convertNullToBlank(String.valueOf(obj[++i])));
			json.put("country", getNameByIDMangerFactory.getCountryNameByID(convertNullToBlank(String.valueOf(obj[++i]))));
			json.put("caste", getNameByIDMangerFactory.getCasteNameByID(convertNullToBlank(String.valueOf(obj[++i]))));
			json.put("subcaste", getNameByIDMangerFactory.getSubCasteNameByID(convertNullToBlank(String.valueOf(obj[++i]))));
			json.put("religion", getNameByIDMangerFactory.getReligionNameByID(convertNullToBlank(String.valueOf(obj[++i]))));
			json.put("state", getNameByIDMangerFactory.getStateNameByID(convertNullToBlank(String.valueOf(obj[++i]))));
			json.put("city", convertNullToBlank(getNameByIDMangerFactory.getCityNameByID(convertNullToBlank(String.valueOf(obj[++i])))));
		
			JSONArray jsonResultsArray = new JSONArray();
			jsonResultsArray = uploadImagesService.getMemberAppPhotos(""+memberID);
			json.put("images",jsonResultsArray);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	
			return json;
	}
	public String convertNullToBlank(String value) {
		if(value!=null && !value.equals("")) {
			return value;
		}
		return "";
	}

	@Override
	public JSONArray RemoveToShortLists(ShortListsModel shortListsModel) {
		Object requestMemberObject = null;
		JSONArray resultArray = new JSONArray();

		try {
			JSONObject json = new JSONObject();
			String shortlist_from_id = shortListsModel.getShortlist_from_id();
			String shortlist_to_id = shortListsModel.getShortlist_to_id();
			String shortlist_status = shortListsModel.getShortlist_status();
			requestMemberObject = shortListsRepository.removeToShortListMember(shortlist_from_id, shortlist_to_id,
					shortlist_status);
			json.put("message", "remove from shortlists");
			json.put("results", "1");

			if (requestMemberObject == null) {
				json.put("message", "not remove from shortlists");
				json.put("results", "0");
			}

			resultArray.put(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultArray;
	}

}
