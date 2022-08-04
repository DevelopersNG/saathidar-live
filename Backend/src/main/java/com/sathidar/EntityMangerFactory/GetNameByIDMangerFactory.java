package com.sathidar.EntityMangerFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

@Service
public class GetNameByIDMangerFactory {

	
	@PersistenceContext
	private EntityManager em;

//	******************************** get name by id ********************************************
	
	public String getCityNameByID(String cityID) {
		String result = "";
		try {
			Query q = em.createNativeQuery("SELECT city_name FROM city where city_id= :cityID and status='ACTIVE'");
			q.setParameter("cityID", cityID);
			result = q.getSingleResult().toString();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
	
	public String getStateNameByID(String stateID) {
		String result = "";
		try {
			Query q = em
					.createNativeQuery("SELECT state_name FROM states where state_id= :stateID and status='ACTIVE'");
			q.setParameter("stateID", stateID);
			result = q.getSingleResult().toString();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public String getReligionNameByID(String religionID) {
		String result = "";
		try {
			Query q = em.createNativeQuery(
					"select religion_name from religion where religion_id=:religionID and status='ACTIVE'");
			q.setParameter("religionID", religionID);
			result = q.getSingleResult().toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public String getSubCasteNameByID(String subcast_ID) {
		String result = "";
		try {
			Query q = em.createNativeQuery(
					"SELECT subcast_name FROM subcasts where subcast_id= :subcast_ID and status='ACTIVE'");
			q.setParameter("subcast_ID", subcast_ID);
			result = q.getSingleResult().toString();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public String getCasteNameByID(String casteID) {
		String result = "";
		try {
			Query q = em.createNativeQuery("SELECT cast_name FROM cast where cast_id= :casteID and status='ACTIVE'");
			q.setParameter("casteID", casteID);
			result = q.getSingleResult().toString();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
	
//	******************************** get id by name ********************************************
	
	public int getReligionID(String religionName) {
		int result = 0;
		try {
			
			
			
			Query q = em.createNativeQuery(
					"select religion_id from religion where religion_name=:ReligionName and status='ACTIVE'");
			q.setParameter("ReligionName", religionName);
			result = Integer.parseInt(q.getSingleResult().toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int getCasteIDByReligionID(String casteName, int religionID) {
		int result = 0;
		try {
			Query q = em.createNativeQuery(
					"SELECT cast_id FROM cast where cast_name= :CasteName and religion_id= :ReligionID  and status='ACTIVE'");
			q.setParameter("CasteName", casteName);
			q.setParameter("ReligionID", religionID);
			result = Integer.parseInt(q.getSingleResult().toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int getCasteID(String casteName) {
		int result = 0;
		try {
			Query q = em.createNativeQuery("SELECT cast_id FROM cast where cast_name= :CasteName and status='ACTIVE' limit 1");
			q.setParameter("CasteName", casteName);
			result = Integer.parseInt(q.getSingleResult().toString());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public int getSubCasteIdByName(String subcastName) {
		int result =0;
		try {
			Query q = em.createNativeQuery(
					"SELECT subcast_id FROM subcasts where subcast_name= :subcastName and status='ACTIVE'");
			q.setParameter("subcastName", subcastName);
			result = Integer.parseInt(q.getSingleResult().toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public int getStateIdByName(String stateName) {
		int result = 0;
		try {
			Query q = em.createNativeQuery("SELECT state_id FROM states where state_name= :stateName and status='ACTIVE'");
			q.setParameter("stateName", stateName);
			result = Integer.parseInt(q.getSingleResult().toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int getCityidByName(String cityName) {
		int result = 0;
		try {
			Query q = em.createNativeQuery("SELECT city_id FROM city where city_name= :cityName and status='ACTIVE'");
			q.setParameter("cityName", cityName);
			result = Integer.parseInt(q.getSingleResult().toString());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public int getCountryIdByName(String CountryName) {
		int result = 0;
		try {
			Query q = em.createNativeQuery("SELECT country_id FROM country where country_name= :CountryName and status='ACTIVE'");
			q.setParameter("CountryName", CountryName);
			result = Integer.parseInt(q.getSingleResult().toString());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
	
	
	public String getCountryNameByID(String CountryID) {
		String result = "";
		try {
			Query q = em.createNativeQuery("SELECT country_name FROM country where  country_id= :CountryID and status='ACTIVE'");
			q.setParameter("CountryID", CountryID);
			result = q.getSingleResult().toString();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
	
//	**********************  plan details *********************
	
	public int getUpgradePlanIdByName(String planName) {
		int result = 0;
		try {
			Query q = em.createNativeQuery("SELECT plan_id FROM plans where plan_name= :planName and plan_status='ACTIVE'");
			q.setParameter("planName", planName);
			result = Integer.parseInt(q.getSingleResult().toString());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
	
	
}
