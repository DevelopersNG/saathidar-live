package com.sathidar.EntityMangerFactory;

import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class DropDownEntityManagerFactory {

	@PersistenceContext
	private EntityManager em;

	public JSONArray getCountryName() {
		JSONArray resultArray = new JSONArray();
		try {
			Query q = em.createNativeQuery("SELECT country_id,country_name FROM country where status='ACTIVE'");
//			Query q = em.createNativeQuery(
//					"SELECT id,country_name FROM country where status='ACTIVE'");
			List<Object[]> results = q.getResultList();
			if (results != null) {
				for (Object[] obj : results) {
					int i = 0;
					JSONObject json = new JSONObject();
					json.put("country_id", String.valueOf(obj[i]));
					json.put("country_name", String.valueOf(obj[++i]));
					resultArray.put(json);
				}
			}
			if (resultArray == null) {
				JSONObject json = new JSONObject();
				json.put("message", "records not foundr");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultArray;
	}

	public JSONArray getStateName(int country_id) {
		JSONArray resultArray = new JSONArray();
		try {

			Query q = em.createNativeQuery(
					"SELECT state_id,state_name FROM states where country_id= :CountryID and status='ACTIVE'");
			q.setParameter("CountryID", country_id);
			List<Object[]> results = q.getResultList();
			if (results != null) {
				for (Object[] obj : results) {
					JSONObject json = new JSONObject();
					int i = 0;
					json.put("state_id", String.valueOf(obj[i]));
					json.put("state_name", String.valueOf(obj[++i]));
					resultArray.put(json);
				}
			}
			if (resultArray == null) {
				JSONObject json = new JSONObject();
				json.put("message", "records not foundr");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultArray;
	}

	public JSONArray getCityName(int state_id) {
		JSONArray resultArray = new JSONArray();
		try {

			Query q = em.createNativeQuery(
					"SELECT city_id,city_name FROM city where state_id= :StateID and status='ACTIVE'");
			q.setParameter("StateID", state_id);
			List<Object[]> results = q.getResultList();
			if (results != null) {
				for (Object[] obj : results) {
					JSONObject json = new JSONObject();
					int i = 0;
					json.put("city_id", String.valueOf(obj[i]));
					json.put("city_name", String.valueOf(obj[++i]));
					resultArray.put(json);
				}
			}
			if (resultArray == null) {
				JSONObject json = new JSONObject();
				json.put("message", "records not foundr");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultArray;
	}

	public JSONArray getReligionName() {
		JSONArray resultArray = new JSONArray();
		try {

			Query q = em.createNativeQuery("SELECT religion_id,religion_name FROM religion where status='ACTIVE'");
			List<Object[]> results = q.getResultList();
			if (results != null) {
				for (Object[] obj : results) {
					int i = 0;
					JSONObject json = new JSONObject();
					json.put("religion_id", String.valueOf(obj[i]));
					json.put("religion_name", String.valueOf(obj[++i]));
					resultArray.put(json);
				}
			}
			if (resultArray == null) {
				JSONObject json = new JSONObject();
				json.put("message", "records not foundr");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultArray;
	}

	public JSONArray getCastName(int religion_id) {
		JSONArray resultArray = new JSONArray();
		try {

			Query q = em.createNativeQuery(
					"SELECT cast_id,cast_name FROM cast where religion_id= :ReligionID and status='ACTIVE'");
			q.setParameter("ReligionID", religion_id);
			List<Object[]> results = q.getResultList();
			if (results != null) {
				for (Object[] obj : results) {
					JSONObject json = new JSONObject();
					int i = 0;
					json.put("cast_id", String.valueOf(obj[i]));
					json.put("cast_name", String.valueOf(obj[++i]));
					resultArray.put(json);
				}
			}
			if (resultArray == null) {
				JSONObject json = new JSONObject();
				json.put("message", "records not foundr");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultArray;
	}

	public JSONArray getAllStateList(int country_id) {
		// TODO Auto-generated method stub
		return null;
	}

	public JSONArray getCityNameByStateName(String state_name) {
		JSONArray resultArray = new JSONArray();
		try {

			Query q = em
					.createNativeQuery("SELECT state_id FROM states where state_name= :state_name and status='ACTIVE'");
			q.setParameter("state_name", state_name);
			String state_id = q.getSingleResult().toString();

			q = em.createNativeQuery("SELECT city_id,city_name FROM city where state_id= :StateID and status='ACTIVE'");
			q.setParameter("StateID", state_id);
			List<Object[]> results = q.getResultList();
			if (results != null) {
				for (Object[] obj : results) {
					JSONObject json = new JSONObject();
					int i = 0;
					json.put("city_id", String.valueOf(obj[i]));
					json.put("city_name", String.valueOf(obj[++i]));
					resultArray.put(json);
				}
			}
			if (resultArray == null) {
				JSONObject json = new JSONObject();
				json.put("message", "records not foundr");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultArray;
	}

	public JSONArray getCastNameByReligionName(String religion_name) {
		JSONArray resultArray = new JSONArray();
		try {

			Query q = em.createNativeQuery(
					"SELECT religion_id FROM religion where religion_name= :religion_name and status='ACTIVE'");
			q.setParameter("religion_name", religion_name);
			String religion_id = q.getSingleResult().toString();

			q = em.createNativeQuery(
					"SELECT cast_id,cast_name FROM cast where religion_id= :ReligionID and status='ACTIVE'");
			q.setParameter("ReligionID", religion_id);
			List<Object[]> results = q.getResultList();
			if (results != null) {
				for (Object[] obj : results) {
					JSONObject json = new JSONObject();
					int i = 0;
					json.put("cast_id", String.valueOf(obj[i]));
					json.put("cast_name", String.valueOf(obj[++i]));
					resultArray.put(json);
				}
			}
			if (resultArray == null) {
				JSONObject json = new JSONObject();
				json.put("message", "records not foundr");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultArray;
	}

	public JSONArray getStateList() {
		JSONArray resultArray = new JSONArray();
		try {

			Query q = em.createNativeQuery("SELECT state_id,state_name FROM states where status='ACTIVE'");
			List<Object[]> results = q.getResultList();
			if (results != null) {
				for (Object[] obj : results) {
					JSONObject json = new JSONObject();
					int i = 0;
					json.put("state_id", String.valueOf(obj[i]));
					json.put("state_name", String.valueOf(obj[++i]));
					resultArray.put(json);
				}
			}
			if (resultArray == null) {
				JSONObject json = new JSONObject();
				json.put("message", "records not foundr");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultArray;
	}

	public JSONArray getAllCityList() {
		JSONArray resultArray = new JSONArray();
		try {
//			Query q = em.createNativeQuery(
//					"SELECT state_id FROM states where status='ACTIVE' ");
//			String state_id = q.getSingleResult().toString();

			Query q = em.createNativeQuery("SELECT city_id,city_name FROM city where status='ACTIVE'");
//			q.setParameter("StateID", state_id);
			List<Object[]> results = q.getResultList();
			if (results != null) {
				for (Object[] obj : results) {
					JSONObject json = new JSONObject();
					int i = 0;
					json.put("city_id", String.valueOf(obj[i]));
					json.put("city_name", String.valueOf(obj[++i]));
					resultArray.put(json);
				}
			}
			if (resultArray == null) {
				JSONObject json = new JSONObject();
				json.put("message", "records not foundr");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultArray;
	}

	public JSONArray getAllCastList() {
		JSONArray resultArray = new JSONArray();
		try {

			Query q = em.createNativeQuery("SELECT cast_id,cast_name FROM cast where status='ACTIVE'");
			List<Object[]> results = q.getResultList();
			if (results != null) {
				for (Object[] obj : results) {
					JSONObject json = new JSONObject();
					int i = 0;
					json.put("cast_id", String.valueOf(obj[i]));
					json.put("cast_name", String.valueOf(obj[++i]));
					resultArray.put(json);
				}
			}
			if (resultArray == null) {
				JSONObject json = new JSONObject();
				json.put("message", "records not foundr");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultArray;
	}

	public JSONArray getStateNameByCountryName(String country_name) {
		JSONArray resultArray = new JSONArray();
		try {

			Query q = em.createNativeQuery(
					"SELECT country_id FROM country where country_name= :country_name and status='ACTIVE'");
			q.setParameter("country_name", country_name);
			String country_id = q.getSingleResult().toString();

			q = em.createNativeQuery(
					"SELECT state_id,state_name FROM states where country_id= :CountryID and status='ACTIVE'");
			q.setParameter("CountryID", country_id);
			List<Object[]> results = q.getResultList();
			if (results != null) {
				for (Object[] obj : results) {
					JSONObject json = new JSONObject();
					int i = 0;
					json.put("state_id", String.valueOf(obj[i]));
					json.put("state_name", String.valueOf(obj[++i]));
					resultArray.put(json);
				}
			}
			if (resultArray == null) {
				JSONObject json = new JSONObject();
				json.put("message", "records not foundr");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultArray;
	}

	public JSONArray getMultipleStateName(int[] country_ids) {
		JSONArray resultArray = new JSONArray();
		try {

			for (int i = 0; i < country_ids.length; i++) {
				int country_id = country_ids[i];

//			Query q = em.createNativeQuery(
//					"SELECT country_id FROM country where country_name= :country_name and status='ACTIVE'");
//			q.setParameter("country_name", country_name);
//			String country_id = q.getSingleResult().toString();

				Query q = em.createNativeQuery(
						"SELECT state_id,state_name FROM states where country_id= :CountryID and status='ACTIVE'");
				q.setParameter("CountryID", country_id);
				List<Object[]> results = q.getResultList();
				if (results != null) {
					for (Object[] obj : results) {
						JSONObject json = new JSONObject();
						int j = 0;
						json.put("state_id", String.valueOf(obj[j]));
						json.put("state_name", String.valueOf(obj[++j]));
						resultArray.put(json);
					}
				}
			}
			if (resultArray.isEmpty()) {
				JSONObject json = new JSONObject();
				json.put("message", "records not found");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultArray;
	}

	public JSONArray getMultipleCityName(int[] state_ids) {
		JSONArray resultArray = new JSONArray();
		try {
			for (int i = 0; i < state_ids.length; i++) {
				int state_id = state_ids[i];

				Query q = em.createNativeQuery(
						"SELECT city_id,city_name FROM city where status='ACTIVE' and state_id= :StateID");
				q.setParameter("StateID", state_id);
				List<Object[]> results = q.getResultList();
				if (results != null) {
					for (Object[] obj : results) {
						JSONObject json = new JSONObject();
						int j = 0;
						json.put("city_id", String.valueOf(obj[j]));
						json.put("city_name", String.valueOf(obj[++j]));
						resultArray.put(json);
					}
				}
			}
			if (resultArray.isEmpty()) {
				JSONObject json = new JSONObject();
				json.put("message", "records not foundr");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultArray;
	}

	public JSONArray getMultiplesStateName(String country_ids) {
		JSONArray resultArray = new JSONArray();
		try {
			String[] splitArray = new String[20];
			if (country_ids.contains(",")) {
				splitArray = country_ids.split(",");
			} else {
				splitArray[0] = country_ids;
			}

			for (int i = 0; i < splitArray.length; i++) {
				String country_id = splitArray[i];
				Query q = em.createNativeQuery(
						"SELECT state_id,state_name FROM states where country_id= :CountryID and status='ACTIVE'");
				q.setParameter("CountryID", country_id);
				List<Object[]> results = q.getResultList();
				if (results != null) {
					for (Object[] obj : results) {
						JSONObject json = new JSONObject();
						int j = 0;
						json.put("state_id", String.valueOf(obj[j]));
						json.put("state_name", String.valueOf(obj[++j]));
						resultArray.put(json);
					}
				}
			}
			if (resultArray.isEmpty()) {
				resultArray = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultArray;
	}

	public JSONArray getMultiplesCityName(String state_ids) {
		JSONArray resultArray = new JSONArray();
		try {
			String[] splitArray = new String[25];
			if (state_ids.contains(",")) {
				splitArray = state_ids.split(",");
			} else {
				splitArray[0] = state_ids;
			}

			for (int i = 0; i < splitArray.length; i++) {
				String state_id = splitArray[i];
				Query q = em.createNativeQuery(
						"SELECT city_id,city_name FROM city where status='ACTIVE' and state_id= :StateID");
				q.setParameter("StateID", state_id);
				List<Object[]> results = q.getResultList();
				if (results != null) {
					for (Object[] obj : results) {
						JSONObject json = new JSONObject();
						int j = 0;
						json.put("city_id", String.valueOf(obj[j]));
						json.put("city_name", String.valueOf(obj[++j]));
						resultArray.put(json);
					}
				}
			}
			if (resultArray.isEmpty()) {
				JSONObject json = new JSONObject();
				json.put("message", "records not foundr");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultArray;
	}

}
