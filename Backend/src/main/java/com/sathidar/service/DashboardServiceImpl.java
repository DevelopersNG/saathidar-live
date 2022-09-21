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
import com.sathidar.EntityMangerFactory.UpdateMemberEntityMangerFactory;
import com.sathidar.model.UpdateMember;
import com.sathidar.repository.DashboardRepository;
import com.sathidar.repository.UpdateMemberRepository;
import com.sathidar.util.MatchesConstant;
import com.sathidar.util.MembersDetailsAction;

@Service
public class DashboardServiceImpl implements DashboardService {

	@Autowired
	private DashboardRepository dashboardRepository;

	@Autowired
	private GetNameByIDMangerFactory getNameByIDMangerFactory;

	@PersistenceContext
	private EntityManager em;

	@Autowired
	public MatchesConstant matchesConstants;

	@Autowired
	private UpdateMemberRepository updateMemberRepository;

	@Autowired
	public MembersDetailsAction MembersDetailsAction;

	@Autowired
	private UploadImagesService uploadImagesService;

	@Autowired
	private RequestMemberServiceImpl requestMemberServiceImpl;

	@Autowired
	private ShortListServiceImpl shortListServiceImpl;

	@Autowired
	private UpdateMemberEntityMangerFactory updateMemberEntityMangerFactory;

	@Override
	public JSONArray GetAllCountDetails(String member_id) {
		JSONArray resultArray = new JSONArray();
		try {
			JSONObject json = new JSONObject();
			// count for total accept requests
//			int accept_count = dashboardRepository.getTotalCountAcceptRequest(member_id, "Accepted");
			int accept_count = requestMemberServiceImpl.GetAcceptedCount(member_id);
			json.put("accept_request_count", "" + accept_count);

			// count for total sent requests
//			int sent_count = dashboardRepository.getTotalCountSentRequest(member_id);
			int sent_count = requestMemberServiceImpl.GetSentRequestCount(member_id);
			json.put("sent_request_count", "" + sent_count);

			// count for total block requests
//			int block_count = dashboardRepository.getTotalBlockSentRequest(member_id, "Block");
			int block_count = requestMemberServiceImpl.getBlockMemberCount(member_id);
			json.put("block_request_count", "" + block_count);

			// count for total deleted requests
//			int deleted_count = dashboardRepository.getTotalDeletedSentRequest(member_id, "Deleted");
			int deleted_count = requestMemberServiceImpl.GetRejectedAndCanceledCount(member_id);
			json.put("deleted_request_count", "" + deleted_count);

			// get total count of recent visitors
//			int recent_visitors_count = dashboardRepository.getRecentVisitorsCount(member_id);
			int recent_visitors_count = updateMemberEntityMangerFactory
					.getRecentVisitorsCount(Integer.parseInt(member_id), "Recently_Visitors");
			json.put("recent_visitors_count", "" + recent_visitors_count);

			int recent_view_to = updateMemberEntityMangerFactory.getRecentVisitorsCount(Integer.parseInt(member_id),
					"View_To");
			json.put("recent_view_to", "" + recent_view_to);

			// get total count of invitations
//			int invitations_count = dashboardRepository.getInvitations(member_id,"Pending");
			int invitations_count = requestMemberServiceImpl.GetInvitationsCount(member_id);
			json.put("invitations_count", "" + invitations_count);

			// get total count of shortlists
//			int shortlist_count = dashboardRepository.getShortlistsCount(member_id,"add");
			int shortlist_count = shortListServiceImpl.GetShortListsMemberCount(member_id);
			json.put("shortlists_count", "" + shortlist_count);

			// get total count of new matches
//			ids = getMemberIDForMatches1(Integer.parseInt(member_id), "NEW_MATCHES");
//			if (!ids.equals("")) {
//				matches_id = ids.replaceFirst(",", "");
//			}
//			int new_matches_count = dashboardRepository.getMatchesCount(member_id, ids);
			int new_matches_count = updateMemberEntityMangerFactory.getMatches_Count(Integer.parseInt(member_id),
					"NEW_MATCHES");
			json.put("new_matches_count", "" + new_matches_count);

//			ids = getMemberIDForMatches1(Integer.parseInt(member_id), "MY_MATCHES");
//			if (!ids.equals("")) {
//				matches_id = ids.replaceFirst(",", "");
//			}
//
//			int my_matches_count = dashboardRepository.getMatchesCount(member_id, ids);
			int my_matches_count = updateMemberEntityMangerFactory.getMatches_Count(Integer.parseInt(member_id),
					"MY_MATCHES");
			json.put("my_matches_count", "" + my_matches_count);

//			ids = getMemberIDForMatches1(Integer.parseInt(member_id), "TODAYS_MATCHES");
//			if (!ids.equals("")) {
//				matches_id = ids.replaceFirst(",", "");
//			}
//			int todays_matches_count = dashboardRepository.getMatchesCount(member_id, ids);
			int todays_matches_count = updateMemberEntityMangerFactory.getMatches_Count(Integer.parseInt(member_id),
					"TODAYS_MATCHES");
			json.put("todays_matches_count", "" + todays_matches_count);

			String premium_matches_count=GetNewPremiumMatchesDashboardCount(member_id, "PREMIUM_MATCHES");
			json.put("premium_matches_count", "" + premium_matches_count);

			int login_premium_status = uploadImagesService.getPremiumMemberStatus("" + member_id);
			if (login_premium_status > 0) {
				json.put("my_premium_status", "2");
			} else {
				json.put("my_premium_status", "0");
			}
			
			
			resultArray.put(json);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultArray;
	}

	private String getMemberIDForMatches1(int member_id, String matches_status) {
		String ids = "";
		try {
			String myQuery = "";
			if (matches_status.equals("NEW_MATCHES")) {
				myQuery = "SELECT group_concat(member_id) FROM member where  member_id!= :member_id  and user_id in (select id from users where enabled=1 and MONTH(creation_date) >= MONTH(CURRENT_DATE - INTERVAL 1 MONTH))";
			} else if (matches_status.equals("MY_MATCHES")) {
				myQuery = "SELECT group_concat(member_id) FROM member where  member_id!= :member_id  and user_id in (select id from users where enabled=1)";
			} else if (matches_status.equals("TODAYS_MATCHES")) {
				myQuery = "SELECT group_concat(member_id) FROM member where  member_id!= :member_id  and user_id in (select id from users where enabled=1 and DATE(creation_date)= CURDATE())";
			}
			Query query = em.createNativeQuery(myQuery);
			query.setParameter("member_id", member_id);
			ids = query.getSingleResult().toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ids;
	}

	@Override
	public JSONArray GetNewMatchesDashboard(String member_id) {
		JSONArray resultArray = new JSONArray();
		try {
			// set column name
			String columnName = getCommonColumnForSearch();

			// set filtered data in where clause
			String whereClause = setWhereClausePartnerPreference(member_id);

			Query q = em.createNativeQuery("SELECT " + columnName + "  FROM memberdetails as md "
					+ " join member as m on md.member_id=m.member_id"
					+ " join member_education_career as mec on m.member_id=mec.member_id "
					+ " join member_horoscope as mh on m.member_id=mh.member_id "
					+ " where m.status='ACTIVE' and md.member_id!= :member_id and" + whereClause);

			List<Object[]> results = q.getResultList();
			if (results != null) {
				for (Object[] obj : results) {
					JSONObject json = new JSONObject();
					int count = 0;
					json = getCommonJsonOutout(obj);
					resultArray.put(json);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultArray;
	}

	public String convertNullToBlank(String value) {
		if (value != null && !value.equals("")) {
			return value;
		}
		return "";
	}

	private JSONObject getCommonJsonOutout(Object[] obj) {
		JSONObject json = new JSONObject();
		try {
			int i = 0;
			String memberID = convertNullToBlank(String.valueOf(obj[i]));
			json.put("member_id", memberID);
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
			json.put("country",
					getNameByIDMangerFactory.getCountryNameByID(convertNullToBlank(String.valueOf(obj[++i]))));
			json.put("caste", getNameByIDMangerFactory.getCasteNameByID(convertNullToBlank(String.valueOf(obj[++i]))));
			json.put("subcaste",
					getNameByIDMangerFactory.getSubCasteNameByID(convertNullToBlank(String.valueOf(obj[++i]))));
			json.put("religion",
					getNameByIDMangerFactory.getReligionNameByID(convertNullToBlank(String.valueOf(obj[++i]))));
			json.put("state", getNameByIDMangerFactory.getStateNameByID(convertNullToBlank(String.valueOf(obj[++i]))));
			json.put("city", getNameByIDMangerFactory.getCityNameByID(convertNullToBlank(String.valueOf(obj[++i]))));

			// check photo settings
				String photo_privacy_setting = uploadImagesService.getPhotoPrivacySettings(memberID);
						if(photo_privacy_setting!=null && !photo_privacy_setting.equals("")) {
							json.put("photo_privacy",photo_privacy_setting);
						}else {
							json.put("photo_privacy","2");
						}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return json;
	}

	private String setWhereClausePartnerPreference(String member_id) {
		String whereClauseFields = "";
		try {
			String partnerPreferenceColumnName = "partner_from_age,partner_to_age,partner_from_height,partner_to_height,partner_manglik_all,partner_annual_income,partner_profile_created,partner_lifestyles,"
					+ "partner_marital_status,partner_mother_tongue,partner_qualification,partner_working_with,partner_professional_area,"
					+ "partner_religions,partner_cast,partner_country,partner_state,partner_city";

			Query queryPreference = em.createNativeQuery("select " + partnerPreferenceColumnName
					+ " from member_preference where member_id= :memberID and status='ACTIVE'");
			queryPreference.setParameter("memberID", member_id);

			JSONArray resultArray = new JSONArray();
			List<Object[]> results = queryPreference.getResultList();

			boolean status = false;
			if (results != null) {
				for (Object[] obj : results) {
					int i = 0;

					// partner from age
					String partner_from_age = convertNullToBlank(String.valueOf(obj[i]));
					if (!convertNullToBlank(partner_from_age).equals("")) {
						whereClauseFields = whereClauseFields + ", " + Integer.parseInt("age") + "<"
								+ Integer.parseInt(partner_from_age);
					}

					// partner to age
					String partner_to_age = convertNullToBlank(String.valueOf(obj[++i]));
					if (!convertNullToBlank(partner_to_age).equals("")) {
						whereClauseFields = whereClauseFields + ", " + Integer.parseInt("age") + ">"
								+ Integer.parseInt(partner_to_age);
					}

					// partner_from_height && to height
					String p_from_height = convertNullToBlank(String.valueOf(obj[++i]));
					String p_to_height = convertNullToBlank(String.valueOf(obj[++i]));

					// partner_manglik_all
					String partner_manglik_all = convertNullToBlank(String.valueOf(obj[++i]));
					if (!convertNullToBlank(partner_manglik_all).equals("")) {
						whereClauseFields = whereClauseFields + " or mh.manglik=" + partner_manglik_all;
					}

					// partner_annual_income
					String partner_annual_income = convertNullToBlank(String.valueOf(obj[++i]));
					if (!convertNullToBlank(partner_annual_income).equals("")) {
						whereClauseFields = whereClauseFields + " or mec.annual_income=" + partner_annual_income;
					}

					// partner_profile_created
					String partner_profile_created = convertNullToBlank(String.valueOf(obj[++i]));
					if (!convertNullToBlank(partner_profile_created).equals("")) {
						whereClauseFields = whereClauseFields + " or md.profilecreatedby=" + partner_profile_created;
					}

					// partner_lifestyles
					String partner_lifestyles = convertNullToBlank(String.valueOf(obj[++i]));
					if (!convertNullToBlank(partner_lifestyles).equals("")) {
						whereClauseFields = whereClauseFields + " or md.lifestyles=" + partner_profile_created;
					}

					// partner_marital_status
					String partner_marital_status = convertNullToBlank(String.valueOf(obj[++i]));
					if (!convertNullToBlank(partner_marital_status).equals("")) {
						whereClauseFields = whereClauseFields + " or md.marital_status=" + partner_marital_status;
					}

					// partner_mother_tongue
					String partner_mother_tongue = convertNullToBlank(String.valueOf(obj[++i]));
					if (!convertNullToBlank(partner_mother_tongue).equals("")) {
						whereClauseFields = whereClauseFields + " or md.mother_tounge=" + partner_mother_tongue;
					}

					// partner_qualification
					String partner_qualification = convertNullToBlank(String.valueOf(obj[++i]));
					if (!convertNullToBlank(partner_qualification).equals("")) {
						whereClauseFields = whereClauseFields + " or mec.highest_qualification="
								+ partner_qualification;
					}

//								String  partner_professional_area  =convertNullToBlank(String.valueOf(obj[++i]));

					// religionsName
					String religionsName = convertNullToBlank(String.valueOf(obj[++i]));
					if (!convertNullToBlank(religionsName).equals("")) {
						whereClauseFields = whereClauseFields + " or md.member_id in (" + religionsName + ") ";
					}

					// cast_name
					String cast_name = convertNullToBlank(String.valueOf(obj[++i]));
					if (!convertNullToBlank(cast_name).equals("")) {
						whereClauseFields = whereClauseFields + " or md.cast_id in (" + cast_name + ") ";
					}

					// country_name
					String country_name = convertNullToBlank(String.valueOf(obj[++i]));
					if (!convertNullToBlank(country_name).equals("")) {
						whereClauseFields = whereClauseFields + " or md.country_id in (" + country_name + ") ";
					}

					// partner_state
					String partner_state = convertNullToBlank(String.valueOf(obj[++i]));
					if (!convertNullToBlank(partner_state).equals("")) {
						whereClauseFields = whereClauseFields + " or md.state_id in (" + partner_state + ") ";
					}

					// partner_state
					String partner_city = convertNullToBlank(String.valueOf(obj[++i]));
					if (!convertNullToBlank(partner_city).equals("")) {
						whereClauseFields = whereClauseFields + " or md.city_id in (" + partner_city + ") ";
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return whereClauseFields;
	}

	private String getCommonColumnForSearch() {
		String columnName = "m.member_id as member_id,height,lifestyles,known_languages,first_name,last_name,"
				+ "gender,md.age,contact_number,profilecreatedby,md.marital_status,mother_tounge,"
				+ "date_of_birth,mec.annual_income,country_id,cast_id,subcaste_id,religion_id,state_id,city_id";

		return columnName;
	}

	private String getShortListIDForMember(int member_id) {
		String ids = "";
		try {
			// check request are sent to other member
			Query query = em.createNativeQuery(
					"SELECT group_concat(shortlist_to_id) FROM member_shortlists where  shortlist_from_id= :shortlist_from_id");
			query.setParameter("shortlist_from_id", member_id);
			ids = convertNullToBlank(query.getSingleResult().toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ids;
	}

	private String getPremiumMemberIDForMatches(String member_id) {
		String ids = "";
		try {
			// check request are sent to other member
			Query query = em.createNativeQuery("SELECT group_concat(member_id) FROM premium_member");
			ids = convertNullToBlank(query.getSingleResult().toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ids;
	}

	private String getMembersHideIDs() {
		String result = "";
		try {
			Query q = em.createNativeQuery("SELECT group_concat(member_id) FROM hide_member where status=0");
			result = q.getSingleResult().toString();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	private String getBlockedIDS(String member_id) {
		String ids = "";
		try {
			Query query = em.createNativeQuery(
					"SELECT group_concat(request_from_id) FROM member_request where  request_to_id= :member_id and block_by_id= :member_id and block_status= :member_request_status");
			query.setParameter("member_id", member_id);
			query.setParameter("member_request_status", "Block");
			List results = query.getResultList();
			if (results.isEmpty() || results == null)
				System.out.println("blank");
			else if (results.size() == 1)
				ids = results.get(0).toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(" block id- " + ids);
		return ids;
	}

	public String GetNewPremiumMatchesDashboardCount(String id, String matches_status)
	{
		String premiumCount="";
		try {
			boolean status = false;
			String  premium_ids = "";
			String ids = "";
			if (matches_status.equals("PREMIUM_MATCHES")) {
				ids = getPremiumMemberIDForMatches(id);
				if (!ids.equals("")) {
					premium_ids = " and m.member_id in (" + ids + ")";
				}else {
					premium_ids=" and m.member_id in ('') ";
				}
			}

//			********************** begin check member profile is hide or not , gets ids *********************************

			String getMembersHideIDs = getMembersHideIDs();
			String hideMemberIdsQuery = "";
			if (getMembersHideIDs != null && !getMembersHideIDs.equals("")) {
//					hideMemberIdsQuery = hideMemberIdsQuery + " and m.member_id not in ("
//							+ getMembersHideIDs.replaceFirst(",", "") + ") ";
				hideMemberIdsQuery = hideMemberIdsQuery + " and m.member_id not in (" + getMembersHideIDs + ") ";
			}

//			********************** begin check member  is block or not , gets ids *********************************

			String getBlockedMemberQuery = "";
			String getMembersBlockIDs = getBlockedIDS("" + id);
			if (getMembersBlockIDs != null && !getMembersBlockIDs.equals("")) {
				getBlockedMemberQuery = getBlockedMemberQuery + " and m.member_id not in (" + getMembersBlockIDs + ") ";
			}

			String query1 = "SELECT count(*) FROM premium_member where member_id= :id and deleteflag='N'";
			Query q1 = em.createNativeQuery(query1);
			q1.setParameter("id", id);
			int premiumStatus = Integer.parseInt(q1.getSingleResult().toString());

			matchesConstants.getMemberMatchPartnerPreference(Integer.parseInt(id));
//			******************************Column Name*************************************************************************
//			String columnName = "m.member_id as member_id,height,lifestyles,known_languages,first_name,last_name,"
//					+ "gender,md.age,contact_number,profilecreatedby,md.marital_status,mother_tounge,"
//					+ "date_of_birth,mec.annual_income,country_id,cast_id,subcaste_id,religion_id,state_id,city_id";
			String columnName = "first_name,last_name, m.member_id, height,lifestyles,md.age,"
					+ "md.marital_status as maritalStatus,mother_tounge,gender,profile_photo_id,"
					+ "(select country_name from country where country_id=(select country_id from memberdetails where member_id= :member_id )) as country_name,country_id,"
					+ "(select state_name from states where state_id=(select state_id from memberdetails where member_id= :member_id)) as state,state_id,"
					+ "(select city_name from city where city_id=(select city_id from memberdetails where member_id= :member_id)) as city,city_id,"
					+ "(select religion_name from religion where religion_id=(select religion_id from memberdetails where member_id= :member_id)) as religion,religion_id,"
					+ "(select cast_name from cast where cast_id=(select cast_id from memberdetails where member_id= :member_id )) as caste,cast_id,"
					+ "edu.highest_qualification as highest_qualification,edu.working_with as working_with,edu.working_as as working_as,edu.annual_income as annual_income";

//			******************************Opposite Gender Search*************************************************************************
			String genderQuery = "";
			String gender = updateMemberRepository.getGenderByMemberID(Integer.parseInt(id));
			if (gender != null && !gender.equals("")) {
				if (gender.equals("male")) {
					genderQuery = " and gender='female' ";
				}
				if (gender.equals("female")) {
					genderQuery = " and gender='male' ";
				}
			}

			String l_strQuery = "SELECT count(*) FROM memberdetails as md "
					+ " join member as m on md.member_id=m.member_id"
					+ " join member_education_career as edu on m.member_id=edu.member_id "
					+ " where m.member_id!= :member_id and m.status='ACTIVE' " + premium_ids + genderQuery
					+ hideMemberIdsQuery + getBlockedMemberQuery + " order by m.member_id desc";
			Query q = em.createNativeQuery(l_strQuery);
			q.setParameter("member_id", id);
			premiumCount=q.getSingleResult().toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return premiumCount;
	}
	
	public JSONArray GetNewPremiumMatchesDashboard(String id, String matches_status) {
		JSONArray resultArray = new JSONArray();
		try {
			boolean status = false;
			String premium_ids = "";

			String ids = "";
		
			ids = "";
			if (matches_status.equals("PREMIUM_MATCHES")) {
				ids = getPremiumMemberIDForMatches(id);
				if (!ids.equals("")) {
					premium_ids = " and m.member_id in (" + ids + ")";
				}else {
					premium_ids=" and m.member_id in ('') ";
				}
			}

//			********************** begin check member profile is hide or not , gets ids *********************************

			String getMembersHideIDs = getMembersHideIDs();
			String hideMemberIdsQuery = "";
			if (getMembersHideIDs != null && !getMembersHideIDs.equals("")) {
//					hideMemberIdsQuery = hideMemberIdsQuery + " and m.member_id not in ("
//							+ getMembersHideIDs.replaceFirst(",", "") + ") ";
				hideMemberIdsQuery = hideMemberIdsQuery + " and m.member_id not in (" + getMembersHideIDs + ") ";
			}

//			********************** begin check member  is block or not , gets ids *********************************

			String getBlockedMemberQuery = "";
			String getMembersBlockIDs = getBlockedIDS("" + id);
			if (getMembersBlockIDs != null && !getMembersBlockIDs.equals("")) {
				getBlockedMemberQuery = getBlockedMemberQuery + " and m.member_id not in (" + getMembersBlockIDs + ") ";
			}

			String query1 = "SELECT count(*) FROM premium_member where member_id= :id and deleteflag='N'";
			Query q1 = em.createNativeQuery(query1);
			q1.setParameter("id", id);
			int premiumStatus = Integer.parseInt(q1.getSingleResult().toString());

			matchesConstants.getMemberMatchPartnerPreference(Integer.parseInt(id));
//			******************************Column Name*************************************************************************
//			String columnName = "first_name,last_name, m.member_id, height,lifestyles,md.age,"
//					+ "md.marital_status as maritalStatus,mother_tounge,gender,profile_photo_id,"
//					+ "(select country_name from country where country_id=(select country_id from memberdetails where member_id= :member_id )) as country_name,country_id,"
//					+ "(select state_name from states where state_id=(select state_id from memberdetails where member_id= :member_id)) as state,state_id,"
//					+ "(select city_name from city where city_id=(select city_id from memberdetails where member_id= :member_id)) as city,city_id,"
//					+ "(select religion_name from religion where religion_id=(select religion_id from memberdetails where member_id= :member_id)) as religion,religion_id,"
//					+ "(select cast_name from cast where cast_id=(select cast_id from memberdetails where member_id= :member_id )) as caste,cast_id,"
//					+ "edu.highest_qualification as highest_qualification,edu.working_with as working_with,edu.working_as as working_as,edu.annual_income as annual_income";
//
//			
			String columnName = "first_name,last_name, m.member_id, height,lifestyles,md.age,"
					+ "md.marital_status as maritalStatus,mother_tounge,gender,profile_photo_id,"
					+ "country_id,"
					+ "state_id,"
					+ "city_id,"
					+ "religion_id,"
					+ "cast_id,"
					+ "edu.highest_qualification as highest_qualification,edu.working_with as working_with,edu.working_as as working_as,edu.annual_income as annual_income";

			
			
//			******************************Opposite Gender Search*************************************************************************
			String genderQuery = "";
			String gender = updateMemberRepository.getGenderByMemberID(Integer.parseInt(id));
			if (gender != null && !gender.equals("")) {
				if (gender.equals("male")) {
					genderQuery = " and gender='female' ";
				}
				if (gender.equals("female")) {
					genderQuery = " and gender='male' ";
				}
			}

			String l_strQuery = "SELECT " + columnName + "  FROM memberdetails as md "
					+ " join member as m on md.member_id=m.member_id"
					+ " join member_education_career as edu on m.member_id=edu.member_id "
					+ " where m.member_id!= :member_id and m.status='ACTIVE' " + premium_ids + genderQuery
					+ hideMemberIdsQuery + getBlockedMemberQuery + " order by m.member_id desc";
			Query q = em.createNativeQuery(l_strQuery);

			q.setParameter("member_id", id);
			String first_name = "", last_name = "";

			List<Object[]> results = q.getResultList();
			if (results != null) {
				for (Object[] obj : results) {
					JSONObject json = new JSONObject();
					int i = 0;
					boolean matchesStatus = false;
					first_name = convertNullToBlank(String.valueOf(obj[i]));
					last_name = convertNullToBlank(String.valueOf(obj[++i]));
					String memberID = convertNullToBlank(String.valueOf(obj[++i]));
					String myHeight = convertNullToBlank(String.valueOf(obj[++i]));

					if (!myHeight.equals("")) {
						String[] splitArrayMyHeight = myHeight.split("-");
						String fromMyHeightString = splitArrayMyHeight[1];
						int numberMyHeight = Integer.parseInt(fromMyHeightString.replaceAll("[^0-9]", ""));

						if (matchesConstants.FROM_HEIGHT <= numberMyHeight
								&& matchesConstants.TO_HEIGHT >= numberMyHeight) {
							matchesStatus = true;
						}
					}

					String myLifeStyles = convertNullToBlank(String.valueOf(obj[++i]));

					String myAge = convertNullToBlank(String.valueOf(obj[++i]));
					if (!myAge.equals("")) {
						int age = Integer.parseInt(myAge);
						if (matchesConstants.FROM_AGE <= age && matchesConstants.TO_AGE >= age) {
							matchesStatus = true;
						}
					}

					String myMaritalStatus = convertNullToBlank(String.valueOf(obj[++i]));
					if (!myMaritalStatus.equals("")) {
						if (matchesConstants.MARITAL_STATUS.contains(myMaritalStatus)) {
							matchesStatus = true;
						}
					}

					String myMotherTongue = convertNullToBlank(String.valueOf(obj[++i]));
					if (!myMotherTongue.equals("")) {
						if (matchesConstants.MOTHER_TONGUE.contains(myMotherTongue)) {
							matchesStatus = true;
						}
					}

					String myGender = convertNullToBlank(String.valueOf(obj[++i]));

					String profile_photo_id = convertNullToBlank(String.valueOf(obj[++i]));
					String getProfilePath = "";
					if (!profile_photo_id.equals("") && !profile_photo_id.equals("0")) {
						getProfilePath = uploadImagesService.getMemberProfilePhotoPath(profile_photo_id);
					}

//					String myCountryName = convertNullToBlank(String.valueOf(obj[++i]));
					String myCountryID = convertNullToBlank(String.valueOf(obj[++i]).trim());
					String myCountryName=getNameByIDMangerFactory.getCountryNameByID(myCountryID);
					if (!myCountryName.equals("")) {
						if (matchesConstants.COUNTRY.contains(myCountryID)) {
							matchesStatus = true;
						}
					}

//					String myStateName = convertNullToBlank(String.valueOf(obj[++i]));
					String myStateID = convertNullToBlank(String.valueOf(obj[++i]).trim());
					String myStateName=getNameByIDMangerFactory.getStateNameByID(myStateID);
					if (!myStateID.equals("")) {
						if (matchesConstants.STATE.contains(myStateID)) {
							matchesStatus = true;
						}
					}
					
//					String myCityName = convertNullToBlank(String.valueOf(obj[++i]));
					String myCityID = convertNullToBlank(String.valueOf(obj[++i]).trim());
					String myCityName=getNameByIDMangerFactory.getCityNameByID(myCityID);
					if (!myCityID.equals("")) {
						if (matchesConstants.CITY.contains(myCityID)) {
							matchesStatus = true;
						}
					}

//					String myReligionName = convertNullToBlank(String.valueOf(obj[++i]));
					String myReligionID = convertNullToBlank(String.valueOf(obj[++i]).trim());
					String myReligionName=getNameByIDMangerFactory.getReligionNameByID(myReligionID);
					if (!myReligionID.equals("")) {
						if (matchesConstants.RELIGIONS.contains(myReligionID)) {
							matchesStatus = true;
						}
					}

					String myCastID = convertNullToBlank(String.valueOf(obj[++i]).trim());
//					String myCastName = convertNullToBlank(String.valueOf(obj[++i]));
					String myCastName=getNameByIDMangerFactory.getCasteNameByID(myCastID);
					if (!myCastID.equals("")) {
						if (matchesConstants.CAST.contains(myReligionID)) {
							matchesStatus = true;
						}
					}


					String myQualifications = convertNullToBlank(String.valueOf(obj[++i]));
					if (!myQualifications.equals("")) {
						if (matchesConstants.QUALIFICATIONS.contains(myQualifications)) {
							matchesStatus = true;
						}
					}

					String myWorkingWith = convertNullToBlank(String.valueOf(obj[++i]));
					if (!myWorkingWith.equals("")) {
						if (matchesConstants.WORKING_WITH.contains(myWorkingWith)) {
							matchesStatus = true;
						}
					}

					String myWorkingAs = convertNullToBlank(String.valueOf(obj[++i]));

					String myAnnualIncome = convertNullToBlank(String.valueOf(obj[++i]));
					if (!myAnnualIncome.equals("")) {
						if (matchesConstants.ANNUAL_INCOME.contains(myAnnualIncome)) {
							matchesStatus = true;
						}
					}
					
					matchesStatus = true;
					if (matchesStatus) {
						json.put("first_name", first_name);
						json.put("last_name", last_name);
						json.put("gender", myGender);
						if (!myAge.equals(""))
							myAge = myAge + "yrs";
						json.put("mage", myAge);
						json.put("religion", myReligionName);
						json.put("maritalStatus", myMaritalStatus);
						json.put("city", myCityName);  
						json.put("country", myCountryName);
						json.put("state", myStateName);
						json.put("city", myCityName);
						myAnnualIncome = MembersDetailsAction.getAnnualIncomePrivacy(premiumStatus, memberID,
								myAnnualIncome);

						json.put("income", myAnnualIncome);
						json.put("member_id", memberID);
						json.put("request_status", "");
						json.put("block_status", "");
						json.put("profile_photo", getProfilePath);

						JSONArray jsonResultsArray = new JSONArray();
						jsonResultsArray = uploadImagesService.getMemberAppPhotos(memberID);
						json.put("images", jsonResultsArray);
						json.put("images_count", jsonResultsArray.length());

						int shortlist_status = uploadImagesService.getShortListStatus("" + id, "" + memberID);
						if (shortlist_status > 0) {
							json.put("shortlist_status", "1");
						} else {
							json.put("shortlist_status", "0");
						}

						int premium_status = uploadImagesService.getPremiumMemberStatus(memberID);
						if (premium_status > 0) {
							json.put("premium_status", "1");
						} else {
							json.put("premium_status", "0");
						}

						int login_premium_status = uploadImagesService.getPremiumMemberStatus("" + id);
						if (login_premium_status > 0) {
							json.put("my_premium_status", "2");
						} else {
							json.put("my_premium_status", "0");
						}

						// check photo settings
						String photo_privacy_setting = uploadImagesService.getPhotoPrivacySettings(memberID);
						if (photo_privacy_setting != null && !photo_privacy_setting.equals("")) {
							json.put("photo_privacy", photo_privacy_setting);
						} else {
							json.put("photo_privacy", "2");
						}

						// check request are sent to other member
						Query query = em.createNativeQuery(
								"SELECT request_status,block_status FROM member_request where  request_from_id= :member_from_id and request_to_id= :member_to_id");
						query.setParameter("member_from_id", id);
						query.setParameter("member_to_id", memberID);
						JSONArray resultRequest = new JSONArray();
						List<Object[]> result = query.getResultList();
						if (results != null) {
							for (Object[] objRequest : result) {
								int j = 0;
								json.put("request_status", convertNullToBlank(String.valueOf(objRequest[j])));
								json.put("block_status", convertNullToBlank(String.valueOf(objRequest[++j])));
							}
						} else {
							json.put("request_status", "");
							json.put("block_status", "");
						}

						resultArray.put(json);
						status = true;
					} else {
						resultArray = null;
					}
				}
			}
			if (status == false) {
				resultArray = null;
			}

		} catch (Exception e) {
			e.printStackTrace();
			resultArray = null;
		}
		return resultArray;
	}

	private String getMemberIDForMatches(int member_id, String matches_status) {
		String ids = "";
		try {
			String myQuery = "";
			if (matches_status.equals("NEW_MATCHES")) {
				myQuery = "SELECT group_concat(member_id) FROM member where  member_id!= :member_id  and user_id in (select id from users where enabled=1 and MONTH(datetime) >= MONTH(CURRENT_DATE - INTERVAL 1 MONTH))";
			} else if (matches_status.equals("MY_MATCHES")) {
				myQuery = "SELECT group_concat(member_id) FROM member where  member_id!= :member_id  and user_id in (select id from users where enabled=1)";
			} else if (matches_status.equals("TODAYS_MATCHES")) {
				myQuery = "SELECT group_concat(member_id) FROM member where  member_id!= :member_id  and user_id in (select id from users where enabled=1 and DATE(datetime)= CURDATE())";
			}
			Query query = em.createNativeQuery(myQuery);
			query.setParameter("member_id", member_id);
			ids = query.getSingleResult().toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ids;
	}

	

}
