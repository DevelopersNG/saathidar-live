package com.sathidar.util;

import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

@Service
public class MatchesConstant {
	
	@PersistenceContext
	private EntityManager em;

	public String MARITAL_STATUS="";
	public String MOTHER_TONGUE="";
	public String QUALIFICATIONS="";
	public String WORKING_WITH="";
	public String RELIGIONS="";
	public String CAST="";
	public String COUNTRY="";
	public String STATE="";
	public String CITY="";
	public int FROM_AGE=0;
	public int TO_AGE=0;
	public int FROM_HEIGHT=0;
	public int TO_HEIGHT=0;
	public String ANNUAL_INCOME="";
	
	public String getMemberMatchPartnerPreference(int member_id) {
		Query queryPartner = em.createNativeQuery("SELECT partner_marital_status, partner_mother_tongue, partner_qualification, partner_working_with, "
				+ "partner_religions, partner_cast, partner_country, partner_state, partner_city, partner_from_age, partner_to_age, "
				+ " partner_from_height, partner_to_height,partner_annual_income "
				+ "  FROM member_preference where member_id= :member_id and status='ACTIVE'");
	
				queryPartner.setParameter("member_id", member_id);
				List<Object[]> resultsPartner = queryPartner.getResultList();
				if (resultsPartner != null) {
					for (Object[] objPartner : resultsPartner) {
						int j = 0;
						MARITAL_STATUS=convertNullToBlank(String.valueOf(objPartner[j]));
						MOTHER_TONGUE=convertNullToBlank(String.valueOf(objPartner[++j]));
						QUALIFICATIONS=convertNullToBlank(String.valueOf(objPartner[++j]));
						WORKING_WITH=convertNullToBlank(String.valueOf(objPartner[++j]));
						RELIGIONS=convertNullToBlank(String.valueOf(objPartner[++j]));
						CAST=convertNullToBlank(String.valueOf(objPartner[++j]));
						COUNTRY=convertNullToBlank(String.valueOf(objPartner[++j]));
						STATE=convertNullToBlank(String.valueOf(objPartner[++j]));
						CITY=convertNullToBlank(String.valueOf(objPartner[++j]));
						
						String temp_from_age=convertNullToBlank(String.valueOf(objPartner[++j]));
						String temp_to_age=convertNullToBlank(String.valueOf(objPartner[++j]));
					
						if(!temp_from_age.equals("") && !temp_to_age.equals("") ) {
							FROM_AGE=Integer.parseInt(temp_from_age);
							TO_AGE=Integer.parseInt(temp_to_age);
						}
						
						
						
						
						String temp_from_height=convertNullToBlank(String.valueOf(objPartner[++j]));
						String temp_to_height=convertNullToBlank(String.valueOf(objPartner[++j]));
					if(!temp_from_height.equals("")  && !temp_to_height.equals("")) {
						
						String[] splitArrayFromHeight=temp_from_height.split("-");
						String fromHeightString=splitArrayFromHeight[1];
						FROM_HEIGHT= Integer.parseInt(fromHeightString.replaceAll("[^0-9]", ""));
						
						String[] splitArrayToHeight=temp_to_height.split("-");
						String fromToString=splitArrayToHeight[1];
						TO_HEIGHT=  Integer.parseInt(fromToString.replaceAll("[^0-9]", ""));
					}
						ANNUAL_INCOME=convertNullToBlank(String.valueOf(objPartner[++j]));
					}
				}
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "";
	}
	
	public String convertNullToBlank(String value) {
		if(value!=null && !value.equals("")) {
			return value;
		}
		return "";
	}
	
	
	
}
