package com.sathidar.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.sathidar.repository.PrivacyPolicyRepository;
import com.sathidar.repository.UpdateMemberRepository;
import com.sathidar.service.UploadImagesService;

@Component
public class SchedulerUnHideProfile {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private SendSMSAction sendSMSAction;
	
	@Autowired
	private PrivacyPolicyRepository privacyPolicyRepository;

	@Autowired
	private UpdateMemberRepository updateMemberRepository;
	
	@Autowired
	private UploadImagesService uploadImagesService;
	@Transactional
//	@Scheduled(initialDelay = 5000, fixedDelay = 9000) 
//	@Scheduled(cron = "0 0 00:00 * * ?") //  for daily once
	public void scheduledMethod() {
		// status=0 for hide member
		// status=1 for un-hide member
		int statusCount=0;
		try {
			Query q = em.createNativeQuery(
					"select  group_concat(member_id) from hide_member where status=0 and date(unhide_period_time)=curdate()");
			String result = q.getSingleResult().toString();
			
			if(result!=null && !result.equals("")) {
				
				String[] l_strArray = null;
				if(result.contains(",")) {
					l_strArray=result.split(",");
				}else {
					l_strArray=new String[1];
					l_strArray[0]=result;
				}
				
				System.out.println("member id " + l_strArray[0]);
				for (int i=0;i<l_strArray.length;i++) {
					Query queryGetPhone = em.createNativeQuery(
							"select  contact_number,email_id from member where member_id='"+l_strArray[i]+"'");
					List<Object[]> results = queryGetPhone.getResultList();
					if (results != null) {
						for (Object[] obj : results) {
							int j = 0;
							String contactNumber= String.valueOf(obj[j]);
							String contactEmail= String.valueOf(obj[++j]);
						
							// here we will sent email
							
//							String smsMessage = "Your Profile is unhide  , Saathidaar.com";
//							String sender = "SDMOTP";
//							String phoneNo = "91" + contactNumber.trim();
//							String response = sendSMSAction.SendOtpSms(phoneNo, sender, smsMessage);
//
//							System.out.println("sms send -" +response);
						}
					}
				}
			}
			
			
			Query query = em.createNativeQuery(
					"update hide_member set status=1 where member_id in ('"+result+"') and status=0");
			statusCount = query.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("hits count are :" +statusCount + " on "+new Date());
	}
	
//	@Scheduled(cron = "0 0 00:00 * * ?") //  for daily once
	public void scheduledEmailAlerts() {
		try {
			Constant constant=new Constant();
			List<Object[]> results = privacyPolicyRepository.getAllEmailAlertSettings();
			if (results != null) {
				for (Object[] obj : results) {
					int i = 0;
					JSONObject json = new JSONObject();

					String premium = constant.convertNullToBlank(String.valueOf(obj[i]));
					String recent = constant.convertNullToBlank(String.valueOf(obj[++i]));
					String todays = constant.convertNullToBlank(String.valueOf(obj[++i]));
					String member_id = constant.convertNullToBlank(String.valueOf(obj[++i]));
					
					int getStatus = privacyPolicyRepository.findByMember_Id_SMS_ALERT(Integer.parseInt(member_id));
					if(getStatus>0) {
						if(!premium.equals("")) {
							if(premium.equalsIgnoreCase("Daily")) {
								// send daily id
								String status=privacyPolicyRepository.getDailyMatchesIDS(member_id);
								if(Integer.parseInt(status) > 0) {
									// not sent
								}else {
									// sent today matches
									// save to db
								List lst=getMemberList(Integer.parseInt(member_id),"PREMIUM_MATCHES");
								// send to mail
								}
							}
							if(premium.equalsIgnoreCase("Monthly")) {
								// send Monthly
							}
							if(premium.equalsIgnoreCase("Weekly")) {
								// send Weekly
							}
						}
						
						if(!recent.equals("")) {
							if(recent.equalsIgnoreCase("Daily")) {
								// send daily id
								String status=privacyPolicyRepository.getDailyMatchesIDS(member_id);
								if(Integer.parseInt(status) > 0) {
									// not sent
								}else {
									// sent today matches
									// save to db
								List lst=getMemberList(Integer.parseInt(member_id),"Daily");
								// send to mail
								}
							}
							if(recent.equalsIgnoreCase("Monthly")) {
								// send Monthly
							}
							if(recent.equalsIgnoreCase("Weekly")) {
								// send Weekly
							}
						}
						
						if(!todays.equals("")) {
							if(todays.equalsIgnoreCase("Daily")) {
								// send daily id
								String status=privacyPolicyRepository.getDailyMatchesIDS(member_id);
								if(Integer.parseInt(status) > 0) {
									// not sent
								}else {
									// sent today matches
									// save to db
								List lst=getMemberList(Integer.parseInt(member_id),"Daily");
								// send to mail
								}
							}
							if(todays.equalsIgnoreCase("Monthly")) {
								// send Monthly
							}
							if(todays.equalsIgnoreCase("Weekly")) {
								// send Weekly
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List getMemberList(int id,String matches_status) {
		List lst=new ArrayList<>();
		try {
			MatchesConstant matchesConstants=new MatchesConstant();
			matchesConstants.getMemberMatchPartnerPreference(id);
			String requestedIds = getRequestedIDForMember(id);
			String requestIdQuery = "", shortListIdQuery = "", matches_id = "";

			String ids = "";
			if (matches_status.equals("NEW_MATCHES") || matches_status.equals("MY_MATCHES")
					|| matches_status.equals("TODAYS_MATCHES")) {
				ids = getMemberIDForMatches(id, matches_status);

				if (!ids.equals("")) {
					matches_id = " and m.member_id in (" + ids.replaceFirst(",", "") + ")";
				} else {
					if (matches_status.equals("TODAYS_MATCHES")) {
						matches_id = " and m.member_id in ('')";
					}
				}
			}
			
//		********************** premium member ids *********************************

			ids = "";
			String premium_ids="";
			
			if (matches_status.equals("PREMIUM_MATCHES")) {
				ids = getPremiumMemberIDForMatches(""+id);
				if (!ids.equals("")) {
					premium_ids = " and m.member_id in (" + ids + ")";
				}
			}

//		********************** begin check premium status *********************************

			String query1 = "SELECT count(*) FROM premium_member where member_id= :id and deleteflag='N'";
			Query q1 = em.createNativeQuery(query1);
			q1.setParameter("id", id);
			int premiumStatus = Integer.parseInt(q1.getSingleResult().toString());
			System.out.println("************* premiun status - " + premiumStatus);

//		********************** begin check member profile is hide or not , gets ids *********************************

			String getMembersHideIDs = getMembersHideIDs();
			String hideMemberIdsQuery = "";
			if (getMembersHideIDs != null && !getMembersHideIDs.equals("")) {
				hideMemberIdsQuery = hideMemberIdsQuery + " and m.member_id not in (" + getMembersHideIDs + ") ";
			}

//		********************** begin check member is block or not , gets ids *********************************

			String getBlockedMemberQuery = "";
			
			try {
				String getMembersBlockIDs = getBlockedIDS(id);
				if (getMembersBlockIDs != null && !getMembersBlockIDs.equals("")) {
					getBlockedMemberQuery = getBlockedMemberQuery + " and m.member_id not in (" + getMembersBlockIDs + ") ";
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			

//		******************************Column Name*************************************************************************
			String columnName = "first_name,last_name, m.member_id, height,lifestyles,md.age,"
					+ "md.marital_status as maritalStatus,mother_tounge,gender,profile_photo_id,"
					+ "(select country_name from country where country_id=(select country_id from memberdetails where member_id= :member_id )) as country_name,country_id,"
					+ "(select state_name from states where state_id=(select state_id from memberdetails where member_id= :member_id)) as state,state_id,"
					+ "(select city_name from city where city_id=(select city_id from memberdetails where member_id= :member_id)) as city,city_id,"
					+ "(select religion_name from religion where religion_id=(select religion_id from memberdetails where member_id= :member_id)) as religion,religion_id,"
					+ "(select cast_name from cast where cast_id=(select cast_id from memberdetails where member_id= :member_id )) as caste,cast_id,"
					+ "edu.highest_qualification as highest_qualification,edu.working_with as working_with,edu.working_as as working_as,edu.annual_income as annual_income";

			String genderQuery = "";
			String gender = updateMemberRepository.getGenderByMemberID(id);
			if (gender != null && !gender.equals("")) {
				if (gender.equalsIgnoreCase("male")) {
					genderQuery = " and gender='female' ";
				}
				if (gender.equalsIgnoreCase("female")) {
					genderQuery = " and gender='male' ";
				}
			}

//		******************************Query*************************************************************************
			
			Query q = em.createNativeQuery("SELECT " + columnName + "  FROM memberdetails as md "
					+ " join member as m on md.member_id=m.member_id"
					+ " join member_education_career as edu on m.member_id=edu.member_id "
					+ " where m.member_id!= :member_id and m.status='ACTIVE' "  + matches_id
					+ hideMemberIdsQuery + getBlockedMemberQuery + genderQuery + " order by m.member_id desc");

			System.out.println(" block query check-   SELECT " + columnName + "  FROM memberdetails as md "
					+ " join member as m on md.member_id=m.member_id"
					+ " join member_education_career as edu on m.member_id=edu.member_id "
					+ " where m.member_id!= :member_id and m.status='ACTIVE' " + matches_id
					+ hideMemberIdsQuery + getBlockedMemberQuery + genderQuery);

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

					String myCountryName = convertNullToBlank(String.valueOf(obj[++i]));
					String myCountryID = convertNullToBlank(String.valueOf(obj[++i]));
					if (!myCountryName.equals("")) {
						if (matchesConstants.COUNTRY.contains(myCountryID)) {
							matchesStatus = true;
						}
					}

					String myStateName = convertNullToBlank(String.valueOf(obj[++i]));
					String myStateID = convertNullToBlank(String.valueOf(obj[++i]));
					if (!myStateID.equals("")) {
						if (matchesConstants.STATE.contains(myStateName)) {
							matchesStatus = true;
						}
					}

					String myCityName = convertNullToBlank(String.valueOf(obj[++i]));
					String myCityID = convertNullToBlank(String.valueOf(obj[++i]));
					if (!myCityID.equals("")) {
						if (matchesConstants.CITY.contains(myCityName)) {
							matchesStatus = true;
						}
					}

					String myReligionName = convertNullToBlank(String.valueOf(obj[++i]));
					String myReligionID = convertNullToBlank(String.valueOf(obj[++i]));
					if (!myReligionID.equals("")) {
						if (matchesConstants.RELIGIONS.contains(myReligionName)) {
							matchesStatus = true;
						}
					}

					String myCastID = convertNullToBlank(String.valueOf(obj[++i]));
					String myCastName = convertNullToBlank(String.valueOf(obj[++i]));
					if (!myCastID.equals("")) {
						if (matchesConstants.CAST.contains(myCastName)) {
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
					
					if (matchesStatus) {
						lst.add(first_name);
						lst.add(last_name);
						lst.add(myGender);
						lst.add(myAge);
						lst.add(myReligionName);
						lst.add(myMaritalStatus);
						lst.add(memberID);
						lst.add(myAnnualIncome);
						lst.add(getProfilePath);
						lst.add(myCityName);
					} else {
						lst = null;
					}
				}
			} else {
				lst = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lst;
	}
	
	
	
	private String getRequestedIDForMember(int member_id) {
		String ids = "";
		try {
			// check request are sent to other member
			Query query = em.createNativeQuery(
					"SELECT group_concat(request_to_id) FROM member_request where  request_from_id= :member_from_id");
			query.setParameter("member_from_id", member_id);
			ids = query.getSingleResult().toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ids;
	}
	
	private String getMemberIDForMatches(int member_id, String matches_status) {
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

	private String getBlockedIDS(int member_id) {
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
		return ids;
	}
	

	public String convertNullToBlank(String value) {
		if (value != null && !value.equals("") && !value.equals("null")) {
			return value;
		}
		return "";
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
}
