package com.sathidar.EntityMangerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sathidar.model.FilterSearchModel;
import com.sathidar.model.UpdateMember;
import com.sathidar.repository.UpdateMemberRepository;
import com.sathidar.service.UpdateMemberService;
import com.sathidar.service.UploadImagesService;
import com.sathidar.util.Constant;
import com.sathidar.util.MatchesConstant;
import com.sathidar.util.MembersDetailsAction;
import com.sathidar.util.SendSMSAction;

@Service
public class UpdateMemberEntityMangerFactory {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	public GetNameByIDMangerFactory getNameByIDMangerFactory;

	@Autowired
	public MatchesConstant matchesConstants;

	@Autowired
	public MembersDetailsAction MembersDetailsAction;

	@Autowired
	private SendSMSAction sendSMSAction;

	@Autowired
	private UploadImagesService uploadImagesService;

	@Autowired
	private UpdateMemberRepository updateMemberRepository;

	@Autowired
	private UserEntityManagerFactory userEntityManagerFactory;

	@Autowired
	private UpdateMemberService updateMemberService;
	
	@Autowired
	private Constant constant;

	public HashMap<String, String> getMemberIdByUserLoginId(int id) {
		HashMap<String, String> map = new HashMap<>();
		try {
			Query q = em.createNativeQuery(
					"SELECT member_number,member_id,user_id,email_id FROM member where  user_id= :UserID and status='ACTIVE'");
			q.setParameter("UserID", id);
			List<Object[]> results = q.getResultList();
			boolean status = false;
			if (results != null) {
				for (Object[] obj : results) {
					int i = 0;
					map.put("profile_id", convertNullToBlank(String.valueOf(obj[i])));
					map.put("member_id", convertNullToBlank(String.valueOf(obj[++i])));
					map.put("user_login_id", convertNullToBlank(String.valueOf(obj[++i])));
					map.put("email", convertNullToBlank(String.valueOf(obj[++i])));
					status = true;
				}
			}
			if (status == false) {
				map.put("message", "record not found");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return map;
	}

	public String convertNullToBlank(String value) {
		if (value != null && !value.equals("") && !value.equals("null")) {
			return value;
		}
		return "";
	}

	public boolean convertBooleanNullToBlank(String value) {
		if (value != null && !value.equals("")) {
			return true;
		}

		return false;
	}

	@Transactional
	public HashMap<String, String> getMember(int id, int login_id) {
		HashMap<String, String> map = new HashMap<>();
		
		String columnName = "member_number,m.member_id, membernative,height,weight,lifestyles,known_languages,education,job,income,hobbies,expectations,first_name,last_name,gender,md.age,"
				+ "contact_number,email_id,profilecreatedby,md.marital_status as maritalStatus,no_of_children,mother_tounge,date_of_birth,"
				+ "health_info,blood_group,gothra,ethnic_corigin,pincode,about_ourself,profile_photo_id,"
				+ "country_id," + "sub_caste_name," + "cast_id," + "religion_id," + "state_id," + "city_id,"
				+ "fd.father_status as father_status,fd.father_company_name as father_company_name,fd.father_designation as father_designation,fd.father_business_name as father_business_name,"
				+ "fd.mother_status as mother_status, fd.mother_company_name as mother_company_name,fd.mother_designation as mother_designation,fd.mother_business_name as mother_business_name,"
				+ "fd.family_location as family_location,fd.native_place as native_place,fd.family_type as family_type,fd.family_values as family_values,fd.family_affluence as family_affluence,"
				+ "fd.married_male as married_male,fd.unmarried_male as unmarried_male,fd.married_female as married_female,fd.unmarried_female as unmarried_female,"
				+ "edu.highest_qualification as highest_qualification,edu.college_attended as college_attended,edu.working_with as working_with,edu.working_as as working_as,edu.employer_name as employer_name,edu.annual_income as annual_income,"
				+ "mh.manglik,mh.nakshatra,mh.time_of_birth,mh.time_status,mh.city_of_birth,mh.hr_dob as horo,"
				+ "mh.country_of_birth,mh.hours,mh.minutes,mh.time";
		try {
			String query = "SELECT " + columnName + "  FROM memberdetails as md "
					+ " join member as m on md.member_id=m.member_id"
					+ " join member_family_details as fd on m.member_id=fd.member_id"
					+ " join member_education_career as edu on m.member_id=edu.member_id "
					+ " join member_horoscope as mh on m.member_id=mh.member_id "
					+ " where md.member_id= :id order by m.member_id desc";

			String query1 = "SELECT count(*) FROM premium_member where member_id= :id and deleteflag='N'";
			Query q1 = em.createNativeQuery(query1);
			q1.setParameter("id", id);
			int premiumStatus = Integer.parseInt(q1.getSingleResult().toString());
			System.out.println("************* premiun status - " + premiumStatus);

			Query q = em.createNativeQuery(query);
			String contact_number = "", email_id = "";
			q.setParameter("id", id);
			boolean status = false;
			List<Object[]> results = q.getResultList();
			if (results != null) {
				for (Object[] obj : results) {
					int i = 0;
					String profileID = convertNullToBlank(String.valueOf(obj[i]));
					String thisMemberID = convertNullToBlank(String.valueOf(obj[++i]));

					// first row
					JSONArray jsonResultsArray = new JSONArray();
					jsonResultsArray = uploadImagesService.getMemberAppPhotos("" + thisMemberID);
//					json.put("images",jsonResultsArray);
					map.put("images_count", "" + jsonResultsArray.length());

					int premium_status = uploadImagesService.getPremiumMemberStatus(thisMemberID);
					if (premium_status > 0) {
						map.put("premium_status", "1");
					} else {
						map.put("premium_status", "0");
					}

					int shortlist_status = uploadImagesService.getShortListStatus("" + login_id, "" + id);
					if (shortlist_status > 0) {
						map.put("shortlist_status", "1");
					} else {
						map.put("shortlist_status", "0");
					}

					int login_premium_status = uploadImagesService.getPremiumMemberStatus("" + login_id);
					if (login_premium_status > 0) {
						map.put("my_premium_status", "2");
					} else {
						map.put("my_premium_status", "0");
					}

					// check photo settings
					String photo_privacy_setting = uploadImagesService.getPhotoPrivacySettings(thisMemberID);
					if (photo_privacy_setting != null && !photo_privacy_setting.equals("")) {
						map.put("photo_privacy", photo_privacy_setting);
					} else {
						map.put("photo_privacy", "2");
					}

					// check phone settings
					String phone_privacy_setting = uploadImagesService.getPhonePrivacySettings(thisMemberID);
					if (phone_privacy_setting != null && !phone_privacy_setting.equals("")) {
						map.put("phone_privacy", phone_privacy_setting);
					} else {
						map.put("phone_privacy", "1");
					}

					// check email settings
					String email_privacy_setting = uploadImagesService.getEmailPrivacySettings(thisMemberID);
					if (email_privacy_setting != null && !email_privacy_setting.equals("")) {
						map.put("email_privacy", email_privacy_setting);
					} else {
						map.put("email_privacy", "1");
					}

					// check DOB settings
					String dob_privacy_setting = uploadImagesService.getDOBPrivacySettings(thisMemberID);
					if (dob_privacy_setting != null && !dob_privacy_setting.equals("")) {
						map.put("dob_privacy", dob_privacy_setting);
					} else {
						map.put("dob_privacy", "1");
					}

					// check Annual Income settings
					String annual_income_privacy_setting = uploadImagesService
							.getAnnualIncomePrivacySettings(thisMemberID);
					if (annual_income_privacy_setting != null && !annual_income_privacy_setting.equals("")) {
						map.put("annual_income_privacy", annual_income_privacy_setting);
					} else {
						map.put("annual_income_privacy", "1");
					}

					map.put("profile_id", profileID);
					map.put("member_id", thisMemberID);
					map.put("native", convertNullToBlank(String.valueOf(obj[++i])));
					String height = convertNullToBlank(String.valueOf(obj[++i]));
					map.put("height", height);
					map.put("weight", convertNullToBlank(String.valueOf(obj[++i])));
					map.put("lifestyles", convertNullToBlank(String.valueOf(obj[++i])));
					map.put("known_languages", convertNullToBlank(String.valueOf(obj[++i])));
					map.put("education", convertNullToBlank(String.valueOf(obj[++i])));
					map.put("job", convertNullToBlank(String.valueOf(obj[++i])));
					map.put("income", convertNullToBlank(String.valueOf(obj[++i])));
					map.put("hobbies", convertNullToBlank(String.valueOf(obj[++i])));
					map.put("expectations", convertNullToBlank(String.valueOf(obj[++i])));
					map.put("first_name", convertNullToBlank(String.valueOf(obj[++i])));
					map.put("last_name", convertNullToBlank(String.valueOf(obj[++i])));
					String gender = convertNullToBlank(String.valueOf(obj[++i]));
					map.put("gender",constant.FirstLetterCapital(gender));
					map.put("age", convertNullToBlank(String.valueOf(obj[++i])));

					// second row
					// check for premium member
					contact_number = convertNullToBlank(String.valueOf(obj[++i]));
					map.put("profile_contact_number", contact_number);
					contact_number = MembersDetailsAction.getPhonePrivacy(login_premium_status, thisMemberID,
							contact_number);
					map.put("contact_number", contact_number);

					// check email for premium member
					email_id = convertNullToBlank(String.valueOf(obj[++i]));
					map.put("profile_email_id", email_id);
					email_id = MembersDetailsAction.getEmailPrivacy(login_premium_status, thisMemberID, email_id);
					map.put("email_id", email_id);

					map.put("profilecreatedby", convertNullToBlank(String.valueOf(obj[++i])));
					String marital_status = convertNullToBlank(String.valueOf(obj[++i]));
					map.put("marital_status", marital_status);
					map.put("no_of_children", convertNullToBlank(String.valueOf(obj[++i])));
					map.put("mother_tounge", convertNullToBlank(String.valueOf(obj[++i])));

					// dob for premium member
					String dob = convertNullToBlank(String.valueOf(obj[++i]));
					dob = MembersDetailsAction.getDateOfBirthPrivacy(login_premium_status, thisMemberID, dob);
					map.put("date_of_birth", dob);

					// third row
					map.put("health_info", convertNullToBlank(String.valueOf(obj[++i])));
					map.put("blood_group", convertNullToBlank(String.valueOf(obj[++i])));
					map.put("gothra", convertNullToBlank(String.valueOf(obj[++i])));
					map.put("ethnic_corigin", convertNullToBlank(String.valueOf(obj[++i])));
					map.put("pincode", convertNullToBlank(String.valueOf(obj[++i])));
					map.put("about_ourself", convertNullToBlank(String.valueOf(obj[++i])));

					String profile_photo_id = convertNullToBlank(String.valueOf(obj[++i]));
					String getProfilePath = "";
					if (!profile_photo_id.equals("") && !profile_photo_id.equals("0")) {
						getProfilePath = uploadImagesService.getMemberProfilePhotoPath(profile_photo_id);
					}

					String my_profile_photo_ID = convertNullToBlank(
							uploadImagesService.getMemberProfilePhotoID(login_id + ""));
					String my_profile_photo = "";
					if (!my_profile_photo_ID.equals("") && !my_profile_photo_ID.equals("0")) {
						my_profile_photo = uploadImagesService.getMemberProfilePhotoPath(my_profile_photo_ID);
					}

					map.put("profile_photo", getProfilePath);
					map.put("my_profile_photo", my_profile_photo);
					// forth row

//					String val= convertNullToBlank(String.valueOf(obj[++i]);
//					map.put("country_name", convertNullToBlank(String.valueOf(obj[++i])));
					String myCountryID = convertNullToBlank(String.valueOf(obj[++i]));
					map.put("country_name", getNameByIDMangerFactory.getCountryNameByID(myCountryID));
					map.put("country_id", myCountryID);
					map.put("sub_caste_name", convertNullToBlank(String.valueOf(obj[++i])));

					// fifth,sixth,seven,eight,nine row
//					map.put("caste", convertNullToBlank(String.valueOf(obj[++i])));
					String myCastID = convertNullToBlank(String.valueOf(obj[++i]));
					map.put("caste", getNameByIDMangerFactory.getCasteNameByID(myCastID));
					map.put("cast_id", myCastID);

//					map.put("subcaste", convertNullToBlank(String.valueOf(obj[++i])));
					String myReligionID = convertNullToBlank(String.valueOf(obj[++i]));
					map.put("religion_name", getNameByIDMangerFactory.getReligionNameByID(myReligionID));
					map.put("religion_id", myReligionID);

//					map.put("state", convertNullToBlank(String.valueOf(obj[++i])));
					String myStateID = convertNullToBlank(String.valueOf(obj[++i]));
					map.put("state", getNameByIDMangerFactory.getStateNameByID(myStateID));
					map.put("state_id", myStateID);

//					map.put("city", convertNullToBlank(String.valueOf(obj[++i])));
					String myCityID = convertNullToBlank(String.valueOf(obj[++i]));
					map.put("city", getNameByIDMangerFactory.getCityNameByID(myCityID));
					map.put("city_id", myCityID);

					// 10th row
					// get family details

					String father_status = convertNullToBlank(String.valueOf(obj[++i]));
					String father_company_name = convertNullToBlank(String.valueOf(obj[++i]));
					String father_designation = convertNullToBlank(String.valueOf(obj[++i]));
					String father_business_name = convertNullToBlank(String.valueOf(obj[++i]));

					// 11th row
					String mother_status = convertNullToBlank(String.valueOf(obj[++i]));
					String mother_company_name = convertNullToBlank(String.valueOf(obj[++i]));
					String mother_designation = convertNullToBlank(String.valueOf(obj[++i]));
					String mother_business_name = convertNullToBlank(String.valueOf(obj[++i]));

					// 12th row
					String family_location = convertNullToBlank(String.valueOf(obj[++i]));
					String native_place = convertNullToBlank(String.valueOf(obj[++i]));
					String family_type = convertNullToBlank(String.valueOf(obj[++i]));
					String familyValues = convertNullToBlank(String.valueOf(obj[++i]));
					String family_affluence = convertNullToBlank(String.valueOf(obj[++i]));

					map.put("father_status", father_status);
					map.put("father_company_name", father_company_name);
					map.put("father_designation", father_designation);
					map.put("father_business_name", father_business_name);
					map.put("mother_status", mother_status);
					map.put("mother_company_name", mother_company_name);
					map.put("mother_designation", mother_designation);
					map.put("mother_business_name", mother_business_name);
					map.put("family_location", family_location);
					map.put("native_place", native_place);
					map.put("family_type", family_type);
					map.put("familyValues", familyValues);
					map.put("family_affluence", family_affluence);

					// 13th row
					map.put("married_male", convertNullToBlank(String.valueOf(obj[++i])));
					map.put("unmarried_male", convertNullToBlank(String.valueOf(obj[++i])));
					map.put("married_female", convertNullToBlank(String.valueOf(obj[++i])));
					map.put("unmarried_female", convertNullToBlank(String.valueOf(obj[++i])));
					String mGender = "";
					if (gender.equals("male") || gender.equals("Male")) {
						mGender = "his";
					}
					if (gender.equals("female") || gender.equals("Female")) {
						mGender = "her";
					}
					String getFamilyDetailsString = getFamilyDetailsInOneStatement(mGender, father_status,
							father_company_name, father_designation, father_business_name, mother_status,
							mother_company_name, mother_designation, mother_business_name, family_location,
							native_place, family_type, familyValues, family_affluence);

					// get edyucation and qualifications details
					map.put("highest_qualification", convertNullToBlank(String.valueOf(obj[++i])));
					map.put("college_attended", convertNullToBlank(String.valueOf(obj[++i])));

					String workingWith = convertNullToBlank(String.valueOf(obj[++i]));
					String workingAs = convertNullToBlank(String.valueOf(obj[++i]));
					String employerName = convertNullToBlank(String.valueOf(obj[++i]));
					String getCareerDetails = getEducationAndCareerDetails(workingWith, workingAs, employerName);

					map.put("working_with", workingWith);
					map.put("working_as", workingAs);
					map.put("employer_name", employerName);

					// check annual income privacy
					String annualIncome = convertNullToBlank(String.valueOf(obj[++i]));
					annualIncome = MembersDetailsAction.getAnnualIncomePrivacy(premiumStatus, thisMemberID,
							annualIncome);
					map.put("annual_income", annualIncome);

					// 14th row
					// + "mh.country_of_birth,mh.hours,mh.minutes,mh.time,mh.time_of_birth";
					map.put("manglik", convertNullToBlank(String.valueOf(obj[++i])));
					map.put("nakshatra", convertNullToBlank(String.valueOf(obj[++i])));
					map.put("time_of_birth", convertNullToBlank(String.valueOf(obj[++i])));
					map.put("time_status", convertNullToBlank(String.valueOf(obj[++i])));
					map.put("city_of_birth", convertNullToBlank(String.valueOf(obj[++i])));
					map.put("horoscope_date_of_birth", convertNullToBlank(String.valueOf(obj[++i])));
					map.put("country_of_birth", convertNullToBlank(String.valueOf(obj[++i])));
					map.put("hours", convertNullToBlank(String.valueOf(obj[++i])));
					map.put("minutes", convertNullToBlank(String.valueOf(obj[++i])));
					map.put("time", convertNullToBlank(String.valueOf(obj[++i])));

					map.put("working_details", getCareerDetails);
					map.put("FamilyDetails", getFamilyDetailsString);

					map.put("message", "success");
					map.put("results", "1");

					// check request are sent to other member
					// get Pending,Rejected,Canceled status
					String request_status = "";
					String block_status = "";
					String getMemberStatus = updateMemberService.getMemberStatus(login_id, thisMemberID);
					if (getMemberStatus != null && !getMemberStatus.equals("")) {
						request_status = getMemberStatus;
					}
					map.put("request_status", request_status);

					String getMemberBlockStatus = updateMemberService.getMemberBlockStatus(login_id, thisMemberID);
					if (getMemberBlockStatus != null && !getMemberBlockStatus.equals("")) {
						block_status = getMemberBlockStatus;
					}
					map.put("block_status", block_status);
					status = true;
				}
			}
			if (login_id != 0) {
				try {
					int statusVisitors = uploadImagesService.getVisitorsStatus(login_id, id);
					if (statusVisitors == 0) {
						Query queryVisitors = em.createNativeQuery(
								"insert into recently_visitors (member_id,visit_to_id,visitor_phone,visitor_email) values (:login_id,:visit_member_ID,:phone,:email)");
						queryVisitors.setParameter("login_id", login_id);
						queryVisitors.setParameter("visit_member_ID", id);
						queryVisitors.setParameter("phone", contact_number);
						queryVisitors.setParameter("email", email_id);
						int visit_status = queryVisitors.executeUpdate();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (status == false) {
				map = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return map;
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

	private String getEducationAndCareerDetails(String workingWith, String workingAs, String employerName) {
		String educationDetails = "";
		try {
			if (workingWith != null && !workingWith.equals("")) {
				if (employerName != null && !employerName.equals("")) {
					educationDetails += educationDetails + " work with " + workingWith;
				} else {
					educationDetails += workingWith;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return educationDetails;
	}

	private String getFamilyDetailsInOneStatement(String mGender, String fatherStatus, String fatherCompanyName,
			String fatherDesignation, String fatherBusinessName, String motherStatus, String motherCompanyName,
			String motherDesignation, String motherBusinesNname, String familyLocation, String nativePlace,
			String familyType, String familyValues, String familyAffluence) {
		// TODO Auto-generated method stub
		String familyDetails = "";
		try {

			// about father
			if (fatherStatus != null && !fatherStatus.equals("")) {
				familyDetails = mGender + " father is " + fatherStatus + ".";
			}
			if (fatherCompanyName != null && !fatherCompanyName.equals("")) {
				familyDetails += mGender + " father working with  " + fatherCompanyName + ".";
			}
			if (fatherDesignation != null && !fatherDesignation.equals("")) {
				familyDetails += mGender + " father designation is  " + fatherDesignation + ".";
			}
			if (fatherBusinessName != null && !fatherBusinessName.equals("")) {
				familyDetails += mGender + " father business is in " + fatherBusinessName + ".";
			}
			// about mother
			if (motherStatus != null && !motherStatus.equals("")) {
				familyDetails += mGender + " mother is " + motherStatus + ".";
			}
			if (motherCompanyName != null && !motherCompanyName.equals("")) {
				familyDetails += mGender + " mother working with  " + motherCompanyName + ".";
			}
			if (motherDesignation != null && !motherDesignation.equals("")) {
				familyDetails += mGender + " mother designation is  " + motherDesignation + ".";
			}
			if (motherBusinesNname != null && !motherBusinesNname.equals("")) {
				familyDetails += mGender + " mother business is in " + motherBusinesNname + ".";
			}
			if (familyLocation != null && !familyLocation.equals("")) {
				familyDetails += " family location is  " + familyLocation + ".";
			}
			if (familyType != null && !familyType.equals("")) {
				familyDetails += " family is  " + familyType + " type.";
			}
			if (familyValues != null && !familyValues.equals("")) {
				familyDetails += "  family values are  " + familyValues + ".";
			}
			if (familyAffluence != null && !familyAffluence.equals("")) {
				familyDetails += " This family Affluence are  " + familyAffluence + ".";
			}

//			map.put("father_status",fatherStatus);
//			map.put("father_company_name", fatherCompanyName);
//			map.put("father_designation", fatherDesignation);
//			map.put("father_business_name", fatherBusinessName);
//			map.put("mother_status", motherStatus);
//			map.put("mother_company_name", motherCompanyName);
//			map.put("mother_designation", motherDesignation);
//			map.put("mother_business_name", motherBusinesNname);
//			
//			map.put("family_location", familyLocation);
//			map.put("native_place", nativePlace);
//			map.put("family_type",familyType);
//			map.put("family_values",familyValues);
//			map.put("family_affluence", familyAffluence);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return familyDetails;

	}

	private String getRequestedIDForMember(int member_id) {
		String ids = "";
		try {
			// check request are sent to other member
			Query query = em.createNativeQuery(
					"SELECT group_concat(request_to_id) FROM member_request where  request_from_id= :member_from_id or request_to_id= :member_from_id");
			query.setParameter("member_from_id", member_id);
			ids = query.getSingleResult().toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ids;
	}

	private int getCountRequestedIDForMember(int member_id) {
		int ids = 0;
//		try {
//			// check request are sent to other member
//			Query query = em.createNativeQuery(
//					"SELECT count(request_to_id) FROM member_request where  request_from_id= :member_from_id");
//			query.setParameter("member_from_id", member_id);
////			ids = query.getSingleResult().toString();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		return ids;
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

	private String getRecentlyVisitorsID(int member_id) {
		String ids = "";
		try {
			// check request are sent to other member
			Query query = em.createNativeQuery(
					"SELECT group_concat(member_id) FROM recently_visitors where  visit_to_id= :visit_to_id and date(visitdatetime) >= DATE_SUB(CURDATE(), INTERVAL 30 DAY)");
			query.setParameter("visit_to_id", member_id);
			ids = convertNullToBlank(query.getSingleResult().toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ids;
	}

	private String getViewedID(int member_id) {
		String ids = "";
		try {
			// check request are sent to other member
			Query query = em.createNativeQuery(
					"SELECT group_concat(visit_to_id) FROM recently_visitors where member_id= :member_id and date(visitdatetime) >= DATE_SUB(CURDATE(), INTERVAL 30 DAY)");
			query.setParameter("member_id", member_id);
			ids = convertNullToBlank(query.getSingleResult().toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ids;
	}

	public int getRecentVisitorsCount(int id, String status) {
		int results = 0;
		try {
			String visitorsIDs = "", viewToIDs = "";
			if (status.equals("Recently_Visitors")) {
				visitorsIDs = getRecentlyVisitorsID(id);
			}
			if (status.equals("View_To")) {
				viewToIDs = getViewedID(id);
			}

			String getBlockedIDS = "";
			try {
				getBlockedIDS = getBlockedIDS(id);
			} catch (Exception e) {
				// TODO: handle exception
			}
			String blockQuery = "";
			System.out.println(" block member ids - " + getBlockedIDS);
			if (getBlockedIDS != null && !getBlockedIDS.equals("")) {
				blockQuery = " and m.member_id not in (" + getBlockedIDS + ")";
			}

			String l_strQuery = "SELECT count(*) FROM memberdetails as md "
					+ " join member as m on md.member_id=m.member_id"
					+ " join member_education_career as edu on m.member_id=edu.member_id "
					+ " where md.member_id!= :member_id and m.status='ACTIVE' " + blockQuery + "";

			if (!visitorsIDs.equals("")) {
				l_strQuery = l_strQuery + " and md.member_id in (" + visitorsIDs + ")";
			} else if (!viewToIDs.equals("")) {
				l_strQuery = l_strQuery + " and md.member_id in (" + viewToIDs + ")";
			} else {
				l_strQuery = l_strQuery + " and md.member_id in ('')";
			}

			if (status.equals("Recently_Visitors")) {
				System.out.println("Recently_Visitors Count Query - " + l_strQuery);
			}
			if (status.equals("View_To")) {
				System.out.println("View_To Count Query - " + l_strQuery);
			}

			Query q = em.createNativeQuery(l_strQuery);
			q.setParameter("member_id", id);
			results = Integer.parseInt(q.getSingleResult().toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
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

	public JSONArray getRecentVisitorsFilter(UpdateMember updateMember, int id, String status) {
		JSONArray resultArray = new JSONArray();
		try {

			String getBlockedIDS = "";
			try {
				getBlockedIDS = getBlockedIDS(id);
			} catch (Exception e) {
				// TODO: handle exception
			}
			String blockQuery = "";
			System.out.println(" block member ids - " + getBlockedIDS);
			if (getBlockedIDS != null && !getBlockedIDS.equals("")) {
				blockQuery = " and m.member_id not in (" + getBlockedIDS + ")";
			}

//			********************** begin check premium status *********************************

			String query1 = "SELECT count(*) FROM premium_member where member_id= :id and deleteflag='N'";
			Query q1 = em.createNativeQuery(query1);
			q1.setParameter("id", id);
			int premiumStatus = Integer.parseInt(q1.getSingleResult().toString());
			System.out.println("************* premiun status - " + premiumStatus);

//			********************** begin column names *********************************

//			String columnName = "first_name,last_name, m.member_id, height,lifestyles,md.age,"
//					+ "md.marital_status as maritalStatus,mother_tounge,gender,profile_photo_id,"
//					+ "(select country_name from country where country_id=(select country_id from memberdetails where member_id= :member_id )) as country_name,country_id,"
//					+ "(select state_name from states where state_id=(select state_id from memberdetails where member_id= :member_id)) as state,state_id,"
//					+ "(select city_name from city where city_id=(select city_id from memberdetails where member_id= :member_id)) as city,city_id,"
//					+ "(select religion_name from religion where religion_id=(select religion_id from memberdetails where member_id= :member_id)) as religion,religion_id,"
//					+ "(select cast_name from cast where cast_id=(select cast_id from memberdetails where member_id= :member_id )) as caste,cast_id,"
//					+ "edu.highest_qualification as highest_qualification,edu.working_with as working_with,edu.working_as as working_as,edu.annual_income as annual_income";

			String columnName = "first_name,last_name, m.member_id, height,lifestyles,md.age,"
					+ "md.marital_status as maritalStatus,mother_tounge,gender,profile_photo_id," + "country_id,"
					+ "state_id," + "city_id," + "religion_id," + "cast_id,"
					+ "edu.highest_qualification as highest_qualification,edu.working_with as working_with,edu.working_as as working_as,edu.annual_income as annual_income";

			String whereClause = setWhereClauseForGetAllMember(updateMember);

			String visitorsIDs = "", viewToIDs = "";
			if (status.equals("Recently_Visitors")) {
				visitorsIDs = getRecentlyVisitorsID(id);
			}
			if (status.equals("View_To")) {
				viewToIDs = getViewedID(id);
			}

			System.out.println("visitors ids -" + visitorsIDs);
			System.out.println("viewToIDs ids -" + viewToIDs);

			String l_strQuery = "SELECT " + columnName + "  FROM memberdetails as md "
					+ " join member as m on md.member_id=m.member_id"
					+ " join member_education_career as edu on m.member_id=edu.member_id "
					+ " where md.member_id!= :member_id and m.status='ACTIVE' " + blockQuery + whereClause + "";

			if (!visitorsIDs.equals("")) {
				l_strQuery = l_strQuery + " and md.member_id in (" + visitorsIDs + ")";
			} else if (!viewToIDs.equals("")) {
				l_strQuery = l_strQuery + " and md.member_id in (" + viewToIDs + ")";
			} else {
				l_strQuery = l_strQuery + " and md.member_id in ('')";
			}

			if (status.equals("Recently_Visitors")) {
				System.out.println("Recently_Visitors Query - " + l_strQuery);
			}
			if (status.equals("View_To")) {
				System.out.println("View_To Query - " + l_strQuery);
			}

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
					String myLifeStyles = convertNullToBlank(String.valueOf(obj[++i]));
					String myAge = convertNullToBlank(String.valueOf(obj[++i]));
					String myMaritalStatus = convertNullToBlank(String.valueOf(obj[++i]));
					String myMotherTongue = convertNullToBlank(String.valueOf(obj[++i]));
					String myGender = convertNullToBlank(String.valueOf(obj[++i]));

					String profile_photo_id = convertNullToBlank(String.valueOf(obj[++i]));
					String getProfilePath = "";
					if (!profile_photo_id.equals("") && !profile_photo_id.equals("0")) {
						getProfilePath = uploadImagesService.getMemberProfilePhotoPath(profile_photo_id);
					}

//					String myCountryName = convertNullToBlank(String.valueOf(obj[++i]));
					String myCountryID = convertNullToBlank(String.valueOf(obj[++i]));
					String myCountryName = getNameByIDMangerFactory.getCountryNameByID(myCountryID);

//					String myStateName = convertNullToBlank(String.valueOf(obj[++i]));
					String myStateID = convertNullToBlank(String.valueOf(obj[++i]));
					String myStateName = getNameByIDMangerFactory.getStateNameByID(myStateID);

//					String myCityName = convertNullToBlank(String.valueOf(obj[++i]));
					String myCityID = convertNullToBlank(String.valueOf(obj[++i]));
					String myCityName = getNameByIDMangerFactory.getCityNameByID(myCityID);

//					String myReligionName = convertNullToBlank(String.valueOf(obj[++i]));
					String myReligionID = convertNullToBlank(String.valueOf(obj[++i]));
					String myReligionName = getNameByIDMangerFactory.getReligionNameByID(myReligionID);

					String myCastID = convertNullToBlank(String.valueOf(obj[++i]));
					String myCastName = getNameByIDMangerFactory.getCasteNameByID(myCastID);
//					String myCastName = convertNullToBlank(String.valueOf(obj[++i]));
					String myQualifications = convertNullToBlank(String.valueOf(obj[++i]));
					String myWorkingWith = convertNullToBlank(String.valueOf(obj[++i]));
					String myWorkingAs = convertNullToBlank(String.valueOf(obj[++i]));
					String myAnnualIncome = convertNullToBlank(String.valueOf(obj[++i]));

					json.put("first_name", first_name);
					json.put("last_name", last_name);
					json.put("gender", constant.FirstLetterCapital(myGender));
					json.put("city", myCityName);
					if (!myAge.equals(""))
						myAge = myAge + " yrs";
					json.put("mage", myAge);
					json.put("religion", myReligionName);
					json.put("maritalStatus", myMaritalStatus);
					json.put("profile_photo", getProfilePath);
					json.put("country", myCountryName);

					json.put("state", myStateName);
					json.put("city", myCityName);
					myAnnualIncome = MembersDetailsAction.getAnnualIncomePrivacy(premiumStatus, memberID,
							myAnnualIncome);

					json.put("income", myAnnualIncome);
					json.put("member_id", memberID);

					JSONArray jsonResultsArray = new JSONArray();
					jsonResultsArray = uploadImagesService.getMemberAppPhotos("" + memberID);
					json.put("images", jsonResultsArray);
					json.put("images_count", jsonResultsArray.length());

					int shortlist_status = uploadImagesService.getShortListStatus("" + id, memberID);
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

					// get Pending,Rejected,Canceled status
					String request_status = "";
					String block_status = "";
					String getMemberStatus = updateMemberService.getMemberStatus(id, memberID);
					if (getMemberStatus != null && !getMemberStatus.equals("")) {
						request_status = getMemberStatus;
					}
					json.put("request_status", request_status);

					String getMemberBlockStatus = updateMemberService.getMemberBlockStatus(id, memberID);
					if (getMemberBlockStatus != null && !getMemberBlockStatus.equals("")) {
						block_status = getMemberBlockStatus;
					}
					json.put("block_status", block_status);

					// get images of member id
					JSONArray jsonResultsImageArray = uploadImagesService.getMemberPhotos(memberID);
					json.put("images", jsonResultsImageArray);

					resultArray.put(json);
				}
			} else {
				resultArray = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultArray;
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

	public JSONArray getAllMemberByFilter(UpdateMember updateMember, int id, String matches_status) {
		JSONArray resultArray = new JSONArray();
		boolean status = false;
		try {
			matchesConstants.getMemberMatchPartnerPreference(id);
			String requestedIds = getRequestedIDForMember(id);
			System.out.println("requestedIds - " + requestedIds);
//			String shortlistIds = getShortListIDForMember(id);
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
//				hideMemberIdsQuery = hideMemberIdsQuery + " and m.member_id not in ("
//						+ getMembersHideIDs.replaceFirst(",", "") + ") ";
				hideMemberIdsQuery = hideMemberIdsQuery + " and m.member_id not in (" + getMembersHideIDs + ") ";
			}

//		********************** begin check member  is block or not , gets ids *********************************

			String getBlockedMemberQuery = "";
			String getMembersBlockIDs = getBlockedIDS("" + id);
			if (getMembersBlockIDs != null && !getMembersBlockIDs.equals("")) {
				getBlockedMemberQuery = getBlockedMemberQuery + " and m.member_id not in (" + getMembersBlockIDs + ") ";
			}

//		******************************Column Name*************************************************************************

			String columnName = "first_name,last_name, m.member_id, height,lifestyles,md.age,"
					+ "md.marital_status as maritalStatus,mother_tounge,gender,profile_photo_id," + "country_id,"
					+ "state_id," + "city_id," + "religion_id," + "cast_id,"
					+ "edu.highest_qualification as highest_qualification,edu.working_with as working_with,edu.working_as as working_as,edu.annual_income as annual_income";

//		******************************begin refine search Filter Data*************************************************************************
//			String whereClause = setWhereClauseForGetAllMember(updateMember);

			String refineWhereClause = "";
			if (matches_status.equals("REFINE-SEARCH")) {
				refineWhereClause = setWhereClauseForGetAllMemberByRefineFilter(updateMember);
			}
			String likeClause = setLikeClauseForGetAllMember(updateMember);

//			******************************Opposite Gender Search*************************************************************************
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
					+ " where m.member_id!= :member_id and m.status='ACTIVE' " + refineWhereClause + matches_id
					+ hideMemberIdsQuery + getBlockedMemberQuery + genderQuery + " order by m.member_id desc");

			System.out.println(" block query check-   SELECT " + columnName + "  FROM memberdetails as md "
					+ " join member as m on md.member_id=m.member_id"
					+ " join member_education_career as edu on m.member_id=edu.member_id "
					+ " where m.member_id!= :member_id and m.status='ACTIVE' " + refineWhereClause + matches_id
					+ hideMemberIdsQuery + getBlockedMemberQuery + genderQuery);

			q.setParameter("member_id", id);
			String first_name = "", last_name = "";

			List<Object[]> results = q.getResultList();
			System.out.println("error1"); 
			if (!results.isEmpty()) {
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
					String myCountryName = getNameByIDMangerFactory.getCountryNameByID(myCountryID);
					if (!myCountryName.equals("")) {
						if (matchesConstants.COUNTRY.contains(myCountryID)) {
							matchesStatus = true;
						}
					}

//					String myStateName = convertNullToBlank(String.valueOf(obj[++i]));
					String myStateID = convertNullToBlank(String.valueOf(obj[++i]).trim());
					String myStateName = getNameByIDMangerFactory.getStateNameByID(myStateID);
					if (!myStateID.equals("")) {
						if (matchesConstants.STATE.contains(myStateID)) {
							matchesStatus = true;
						}
					}

//					String myCityName = convertNullToBlank(String.valueOf(obj[++i]));
					String myCityID = convertNullToBlank(String.valueOf(obj[++i]).trim());
					String myCityName = getNameByIDMangerFactory.getCityNameByID(myCityID);
					if (!myCityID.equals("")) {
						if (matchesConstants.CITY.contains(myCityID)) {
							matchesStatus = true;
						}
					}

//					String myReligionName = convertNullToBlank(String.valueOf(obj[++i]));
					String myReligionID = convertNullToBlank(String.valueOf(obj[++i]).trim());
					String myReligionName = getNameByIDMangerFactory.getReligionNameByID(myReligionID);
					if (!myReligionID.equals("")) {
						if (matchesConstants.RELIGIONS.contains(myReligionID)) {
							matchesStatus = true;
						}
					}

					String myCastID = convertNullToBlank(String.valueOf(obj[++i]).trim());
//					String myCastName = convertNullToBlank(String.valueOf(obj[++i]));
					String myCastName = getNameByIDMangerFactory.getCasteNameByID(myCastID);
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
					
					// check multiple annual income
					String myAnnualIncome = convertNullToBlank(String.valueOf(obj[++i]));
					List<String> anualList  = new ArrayList<String>();
					if (myAnnualIncome != null && !myAnnualIncome.equals("") && !myAnnualIncome.equals("null")) {
						if (!myAnnualIncome.contains(",")) {
							anualList.add(myAnnualIncome);
						} else {
							anualList = new ArrayList<String>(Arrays.asList(myAnnualIncome.split(",")));
						}
					}
					
					List<String> memberAnnualList  = new ArrayList<String>();
					if (matchesConstants.ANNUAL_INCOME != null && !matchesConstants.ANNUAL_INCOME.equals("") && !matchesConstants.ANNUAL_INCOME.equals("null")) {
						if (!matchesConstants.ANNUAL_INCOME.contains(",")) {
							memberAnnualList.add(matchesConstants.ANNUAL_INCOME);
						} else {
							memberAnnualList = new ArrayList<String>(Arrays.asList(matchesConstants.ANNUAL_INCOME.split(",")));
						}
					}
					
					for (int j = 0; j < anualList.size(); j++) {
						for (int k = 0; k < memberAnnualList.size(); k++) {
							if(anualList.get(j).equals(memberAnnualList.get(k))) {
								matchesStatus = true;
							}
						}
					}

//					if (!myAnnualIncome.equals("")) {
//						if (matchesConstants.ANNUAL_INCOME.contains(myAnnualIncome)) {
//							matchesStatus = true;
//						}
//					}
					matchesStatus = true; // by default set matches true for first upto 50 member
					if (matchesStatus) {
						json.put("first_name", first_name);
						json.put("last_name", last_name);
						json.put("gender", constant.FirstLetterCapital(myGender));
						if (!myAge.equals(""))
							myAge = myAge + "yrs";
						json.put("mage", myAge);
						json.put("religion", myReligionName);
						json.put("maritalStatus", myMaritalStatus);
						json.put("country", myCountryName);
						json.put("state", myStateName);
						json.put("city", myCityName);
						myAnnualIncome = MembersDetailsAction.getAnnualIncomePrivacy(premiumStatus, memberID,
								myAnnualIncome);

						json.put("income", myAnnualIncome);
						json.put("member_id", memberID);
//						json.put("request_status", "");
						json.put("block_status", "");
						json.put("profile_photo", getProfilePath);
						json.put("city", myCityName);

						JSONArray jsonResultsArray = new JSONArray();
						jsonResultsArray = uploadImagesService.getMemberAppPhotos(memberID);
						json.put("images", jsonResultsArray);
						json.put("images_count", jsonResultsArray.length());

						int shortlist_status = uploadImagesService.getShortListStatus("" + id, memberID);
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

						// get Pending,Rejected,Canceled status
						String request_status = "";
						String block_status = "";
						String getMemberStatus = updateMemberService.getMemberStatus(id, memberID);
						if (getMemberStatus != null && !getMemberStatus.equals("")) {
							request_status = getMemberStatus;
						}
						json.put("request_status", request_status);

						String getMemberBlockStatus = updateMemberService.getMemberBlockStatus(id, memberID);
						if (getMemberBlockStatus != null && !getMemberBlockStatus.equals("")) {
							block_status = getMemberBlockStatus;
						}
						json.put("block_status", block_status);

						resultArray.put(json);

						status = true;
					} else {
						resultArray = null;
					}
				}
			} else {
				resultArray = null;
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

	private String getCityNameByID(String cityID) {
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

	private String getStateNameByID(String stateID) {
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

	private String getReligionNameByID(String religionID) {
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

	private String getSubCasteNameByID(String subcast_ID) {
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

	private String getCasteNameByID(String casteID) {
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

	private String setLikeClauseForGetAllMember(UpdateMember updateMember) {
		String likeClause = "";
		try {
			if (updateMember.getLifestyles() != null && !updateMember.getLifestyles().equals("")) {
//				if(updateMember.getLifestyles().contains(",")) {
//					String[] splitString = updateMember.getLifestyles().split(",");
//					
//				}else {
				likeClause += " and lifestyles like '%" + updateMember.getLifestyles().trim() + "%'";
//				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return likeClause;
	}

	private String setWhereClauseForGetAllMember(UpdateMember updateMember) {
		String whereClause = "";

		try {
//			if (updateMember.getAge() != null && !updateMember.getAge().equals("")) {
//				whereClause += " md.age=" + updateMember.getAge();
//			}

			String lookingFor = "";
			if (updateMember.getLookingFor() != null && !updateMember.getLookingFor().equals("")) {
				if (updateMember.getLookingFor().equals("Woman")) {
					lookingFor = "female";
					whereClause += " and m.gender ='" + lookingFor + "'";
				} else if (updateMember.getLookingFor().equals("Men")) {
					lookingFor = "male";
					whereClause += " and m.gender ='" + lookingFor + "'";
				}
			}

			if (updateMember.getMarital_status() != null && !updateMember.getMarital_status().equals("")) {
				whereClause += " and md.marital_status ='" + updateMember.getMarital_status() + "'";
			}

			if (updateMember.getFrom_age() != null && !updateMember.getFrom_age().equals("")) {
				whereClause += " and md.age >=" + updateMember.getFrom_age();
			}	

			if (updateMember.getTo_age() != null && !updateMember.getTo_age().equals("")) {
				whereClause += " and md.age <=" + updateMember.getTo_age();
			}

			int casteID = 0, religionID = 0;
			if (updateMember.getReligion_name() != null && !updateMember.getReligion_name().equals("")) {
				religionID = this.getReligionID(updateMember.getReligion_name());
				if (religionID != 0) {
					whereClause += " and religion_id=" + religionID;
				}
			}

			if (updateMember.getCaste_name() != null && !updateMember.getCaste_name().equals("")) {
				if (religionID != 0) {
					casteID = this.getCasteIDByReligionID(updateMember.getCaste_name().trim(), religionID);
				} else {
					casteID = this.getCasteID(updateMember.getCaste_name().trim());
				}
				whereClause += " and cast_id=" + casteID + "";
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		return whereClause;
	}

	private int getReligionID(String religionName) {
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

	private int getCasteIDByReligionID(String casteName, int religionID) {
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

	private int getCasteID(String casteName) {
		int result = 0;
		try {
			Query q = em.createNativeQuery("SELECT cast_id FROM cast where cast_name= :CasteName and status='ACTIVE'");
			q.setParameter("CasteName", casteName);
			result = Integer.parseInt(q.getSingleResult().toString());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public JSONArray getAllMembers(int id) {
		JSONArray resultArray = new JSONArray();
		try {
			String whereClause = "";
			String genderName = "";
//			if(!this.getGenderByID(id).equals(""))
//			{
//				if(this.getGenderByID(id).equals("male")) {
//					genderName="female";
//					whereClause += " m.gender ='"+genderName+"'";
//				}else if(this.getGenderByID(id).equals("female")) {
//					genderName="male";
//					whereClause += " m.gender ='"+genderName+"'";
//				}
//			}
//			******************************Column Name*************************************************************************
			String columnName = " md.member_id,membernative,height,weight,lifestyles,known_languages,education,job,income,hobbies,expectations,first_name,last_name,gender,md.age as mage,"
					+ "contact_number,email_id,profilecreatedby,md.marital_status as maritalStatus,no_of_children,mother_tounge,date_of_birth,"
					+ "cast_id,subcaste_id,religion_id,state_id,city_id";

//			******************************Filter Data*************************************************************************

//			******************************Preference-Recommdations-In-Where-Clause*************************************************************************
//	      if recommodations are given in the table then that details are come in where clause

//			******************************Query*************************************************************************
			Query q = em.createNativeQuery("SELECT " + columnName
					+ "  FROM memberdetails as md join member as m on md.member_id=m.member_id where md.member_id!= :id");

			// System.out.println("SELECT " + columnName +" FROM memberdetails as md join
			// member as m on md.member_id=m.member_id where "+whereClause+ " and
			// md.member_id!= :id and m.status='ACTIVE'");
			q.setParameter("id", id);

			List<Object[]> results = q.getResultList();
			if (results != null) {
				for (Object[] obj : results) {
					JSONObject getJson = new JSONObject();
					int member_id = Integer.parseInt(convertNullToBlank(String.valueOf(obj[0])));
					String fieldName = this.getHideContent(member_id);
					if (fieldName != null && !fieldName.equals("")) {
						getJson = this.setHideContent(fieldName, obj);
					} else {
						getJson = this.setContent(obj);
					}
					resultArray.put(getJson);
				}
			} else {
				resultArray = null;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return resultArray;
	}

	private JSONObject setContent(Object[] obj) {
		JSONObject setJson = new JSONObject();
		try {
			int i = 1;
			setJson.put("native", convertNullToBlank(String.valueOf(obj[i])));
			setJson.put("height", convertNullToBlank(String.valueOf(obj[++i])));
			setJson.put("weight", convertNullToBlank(String.valueOf(obj[++i])));
			setJson.put("lifestyles", convertNullToBlank(String.valueOf(obj[++i])));
			setJson.put("known_languages", convertNullToBlank(String.valueOf(obj[++i])));
			setJson.put("education", convertNullToBlank(String.valueOf(obj[++i])));
			setJson.put("job", convertNullToBlank(String.valueOf(obj[++i])));
			setJson.put("income", convertNullToBlank(String.valueOf(obj[++i])));
			setJson.put("hobbies", convertNullToBlank(String.valueOf(obj[++i])));
			setJson.put("expectations", convertNullToBlank(String.valueOf(obj[++i])));
			setJson.put("first_name", convertNullToBlank(String.valueOf(obj[++i])));
			setJson.put("last_name", convertNullToBlank(String.valueOf(obj[++i])));
			setJson.put("gender", constant.FirstLetterCapital(convertNullToBlank(String.valueOf(obj[++i]))));
			setJson.put("mage", convertNullToBlank(String.valueOf(obj[++i])));
			setJson.put("contact_number", convertNullToBlank(String.valueOf(obj[++i])));
			setJson.put("email_id", convertNullToBlank(String.valueOf(obj[++i])));
			setJson.put("profilecreatedby", convertNullToBlank(String.valueOf(obj[++i])));
			setJson.put("maritalStatus", convertNullToBlank(String.valueOf(obj[++i])));
			setJson.put("no_of_children", convertNullToBlank(String.valueOf(obj[++i])));
			setJson.put("mother_tounge", convertNullToBlank(String.valueOf(obj[++i])));
			setJson.put("date_of_birth", convertNullToBlank(String.valueOf(obj[++i])));
			setJson.put("caste", getCasteNameByID(convertNullToBlank(String.valueOf(obj[++i]))));
			setJson.put("subcaste", getSubCasteNameByID(convertNullToBlank(String.valueOf(obj[++i]))));
			setJson.put("religion", getReligionNameByID(convertNullToBlank(String.valueOf(obj[++i]))));
			setJson.put("state", getStateNameByID(convertNullToBlank(String.valueOf(obj[++i]))));
			setJson.put("city", getCityNameByID(convertNullToBlank(String.valueOf(obj[++i]))));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return setJson;
	}

	private JSONObject setHideContent(String fieldName, Object[] obj) {
		JSONObject setJson = new JSONObject();
		try {
//			String[] splitArray = new String[26];
//
//			if (fieldName.contains(",")) {
//				splitArray = fieldName.split(",");
//			} else {
//				splitArray = new String[] { fieldName };
//			}
//
//			for (int k = 0; k <= splitArray.length; k++) {
			int i = 1;
			if (fieldName.contains("native"))
				setJson.put("native", "");
			else
				setJson.put("native", convertNullToBlank(String.valueOf(obj[i])));

			if (fieldName.contains("height")) {
				setJson.put("height", "");
				++i;
			} else
				setJson.put("height", convertNullToBlank(String.valueOf(obj[++i])));

			if (fieldName.contains("weight")) {
				setJson.put("weight", "");
				++i;
			} else
				setJson.put("weight", convertNullToBlank(String.valueOf(obj[++i])));

			if (fieldName.contains("lifestyles")) {
				setJson.put("lifestyles", "");
				++i;
			} else
				setJson.put("lifestyles", convertNullToBlank(String.valueOf(obj[++i])));

			if (fieldName.contains("known_languages")) {
				setJson.put("known_languages", "");
				++i;
			} else
				setJson.put("known_languages", convertNullToBlank(String.valueOf(obj[++i])));

			if (fieldName.contains("education")) {
				setJson.put("education", "");
				++i;
			} else
				setJson.put("education", convertNullToBlank(String.valueOf(obj[++i])));

			if (fieldName.contains("job")) {
				setJson.put("job", "");
				++i;
			} else
				setJson.put("job", convertNullToBlank(String.valueOf(obj[++i])));

			if (fieldName.contains("income")) {
				setJson.put("income", "");
				++i;
			} else
				setJson.put("income", convertNullToBlank(String.valueOf(obj[++i])));

			if (fieldName.contains("hobbies")) {
				setJson.put("hobbies", "");
				++i;
			} else
				setJson.put("hobbies", convertNullToBlank(String.valueOf(obj[++i])));

			if (fieldName.contains("expectations")) {
				setJson.put("expectations", "");
				++i;
			} else
				setJson.put("expectations", convertNullToBlank(String.valueOf(obj[++i])));

			if (fieldName.contains("first_name")) {
				setJson.put("first_name", "");
				++i;
			} else
				setJson.put("first_name", convertNullToBlank(String.valueOf(obj[++i])));

			if (fieldName.contains("last_name")) {
				setJson.put("last_name", "");
				++i;
			} else
				setJson.put("last_name", convertNullToBlank(String.valueOf(obj[++i])));

			if (fieldName.contains("gender")) {
				setJson.put("gender", "");
				++i;
			} else
				setJson.put("gender", constant.FirstLetterCapital(convertNullToBlank(String.valueOf(obj[++i]))));

			if (fieldName.contains("mage")) {
				setJson.put("mage", "");
				++i;
			} else
				setJson.put("mage", convertNullToBlank(String.valueOf(obj[++i])));

			if (fieldName.contains("contact_number")) {
				setJson.put("contact_number", "");
				++i;
			} else
				setJson.put("contact_number", convertNullToBlank(String.valueOf(obj[++i])));

			if (fieldName.contains("email_id")) {
				setJson.put("email_id", "");
				++i;
			} else
				setJson.put("email_id", convertNullToBlank(String.valueOf(obj[++i])));

			if (fieldName.contains("profilecreatedby")) {
				setJson.put("profilecreatedby", "");
				++i;
			} else
				setJson.put("profilecreatedby", convertNullToBlank(String.valueOf(obj[++i])));

			if (fieldName.contains("maritalStatus")) {
				setJson.put("maritalStatus", "");
				++i;
			} else
				setJson.put("maritalStatus", convertNullToBlank(String.valueOf(obj[++i])));

			if (fieldName.contains("no_of_children")) {
				setJson.put("no_of_children", "");
				++i;
			} else
				setJson.put("no_of_children", convertNullToBlank(String.valueOf(obj[++i])));

			if (fieldName.contains("mother_tounge")) {
				setJson.put("mother_tounge", "");
				++i;
			} else
				setJson.put("mother_tounge", convertNullToBlank(String.valueOf(obj[++i])));

			if (fieldName.contains("date_of_birth")) {
				setJson.put("date_of_birth", "");
				++i;
			} else
				setJson.put("date_of_birth", convertNullToBlank(String.valueOf(obj[++i])));

			if (fieldName.contains("caste")) {
				setJson.put("caste", "");
				++i;
			} else
				setJson.put("caste", convertNullToBlank(String.valueOf(obj[++i])));

			if (fieldName.contains("subcaste")) {
				setJson.put("subcaste", "");
				++i;
			} else
				setJson.put("subcaste", convertNullToBlank(String.valueOf(obj[++i])));

			if (fieldName.contains("religion")) {
				setJson.put("religion", "");
				++i;
			} else
				setJson.put("religion", convertNullToBlank(String.valueOf(obj[++i])));

			if (fieldName.contains("state")) {
				setJson.put("state", "");
				++i;
			} else
				setJson.put("state", convertNullToBlank(String.valueOf(obj[++i])));

			if (fieldName.contains("city")) {
				setJson.put("city", "");
				++i;
			} else
				setJson.put("city", convertNullToBlank(String.valueOf(obj[++i])));

//			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return setJson;
	}

	private String getHideContent(int id) {
		String columnName = "";
		try {
			Query q = em.createNativeQuery(
					"SELECT column_name  FROM hide_content where member_id= :id and status='ACTIVE'");
			q.setParameter("id", id);
			columnName = q.getSingleResult().toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return columnName;

	}

	private String getGenderByID(int id) {
		String result = "";
		try {
			Query q = em.createNativeQuery(
					"SELECT m.gender  FROM memberdetails as md join member as m on md.member_id=m.member_id where md.member_id= :id and m.status='ACTIVE'");
			q.setParameter("id", id);
			result = q.getSingleResult().toString();
			if (result == null)
				result = "";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Transactional
	public int DeactivateMemberDetails(int userID) {
		int statusCount = 0;
		try {
			int result = 0;
			Query q = em.createNativeQuery("SELECT count(*) FROM users where status='ACTIVE' and id= :userID");
			q.setParameter("userID", userID);
			result = Integer.parseInt(q.getSingleResult().toString());

			if (result > 0) {
				Query queryMemberDeativate = em
						.createNativeQuery("update member set status='Deactivate' where user_id= :userID");
				queryMemberDeativate.setParameter("userID", userID);
				statusCount = queryMemberDeativate.executeUpdate();

				Query queryMemberNumber = em
						.createNativeQuery("update users set status='Deactivate' where id= :userID");
				queryMemberNumber.setParameter("userID", userID);
				statusCount = queryMemberNumber.executeUpdate();
			}
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return statusCount;
	}

	@Transactional
	public int activateMember(UpdateMember updateMember) {
		int statusCount = 0;
		try {
			int result = 0;
			Query q = em.createNativeQuery("SELECT user_id FROM member where member_id= :member_id");
			q.setParameter("member_id", updateMember.getMember_id());
			int userID = Integer.parseInt(q.getSingleResult().toString());

			String Status = "status='ACTIVE'";
			if (updateMember.getActivate_id() == 0)
				Status = "status='DE-ACTIVE'";

//				********** send otp on mobile number****************
//				********** send otp on email************************		  
//				if phone otp and email otp are correct then activate member account

			Query queryMemberNumber = em.createNativeQuery("update users set " + Status + " where id= :userID");
			queryMemberNumber.setParameter("userID", userID);
			statusCount = queryMemberNumber.executeUpdate();

			Query queryMemberDeativate = em.createNativeQuery(
					"update member set " + Status + " where user_id= :userID and member_id= :member_id");
			queryMemberDeativate.setParameter("member_id", updateMember.getMember_id());
			queryMemberDeativate.setParameter("userID", userID);
			statusCount = queryMemberDeativate.executeUpdate();

			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return statusCount;
	}

	public JSONArray getMembersPlanDetails() {
		JSONArray resultArray = new JSONArray();
		try {
			Query q = em.createNativeQuery(
					"SELECT plan_id,plan_name,plan_validity,plan_price,plan_discount,discount_price FROM plans where plan_status='ACTIVE'");
			List<Object[]> results = q.getResultList();
			boolean status = false;

			if (results != null) {
				for (Object[] obj : results) {
					JSONObject json = new JSONObject();
					int i = 0;
					String plan_id = convertNullToBlank(String.valueOf(obj[i]));
					json.put("plan_id", plan_id);
					json.put("plan_name", convertNullToBlank(String.valueOf(obj[++i])));
					json.put("plan_validity", convertNullToBlank(String.valueOf(obj[++i])));
					json.put("plan_price", convertNullToBlank(String.valueOf(obj[++i])));
					json.put("plan_discount", convertNullToBlank(String.valueOf(obj[++i])));
					json.put("plan_discount_price", convertNullToBlank(String.valueOf(obj[++i])));

					// getting plan list as per plan name and id
					JSONArray jsonArrayPlanList = new JSONArray();
					Query queryPlanList = em.createNativeQuery(
							"SELECT id,features,valid FROM plan_list where plan_id= :plan_id and delete_flag='N'");
					queryPlanList.setParameter("plan_id", plan_id);
					List<Object[]> resultsPlanList = queryPlanList.getResultList();
					if (resultsPlanList != null) {
						for (Object[] objPlanList : resultsPlanList) {
							int j = 0;
							JSONObject jsonPlanlist = new JSONObject();
							String plan_features_id = convertNullToBlank(String.valueOf(objPlanList[j]));
							String plan_features_name = convertNullToBlank(String.valueOf(objPlanList[++j]));
							String valid = convertNullToBlank(String.valueOf(objPlanList[++j]));

							jsonPlanlist.put("features_id", plan_features_id);
							jsonPlanlist.put("features_name", plan_features_name);
							jsonPlanlist.put("features_valid", valid);
							jsonArrayPlanList.put(jsonPlanlist);
						}
					}
					json.put("features", jsonArrayPlanList);
					resultArray.put(json);
				}
			} else {
				resultArray = null;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return resultArray;
	}

	public JSONArray getMembersPlanDetailsByPlanID(int plan_id) {
		JSONArray resultArray = new JSONArray();
		try {
			Query q = em.createNativeQuery(
					"SELECT plan_id,plan_name,plan_validity,plan_price,plan_discount,discount_price FROM plans where plan_status='ACTIVE' and plan_id=:plan_id");
			q.setParameter("plan_id", plan_id);
			List<Object[]> results = q.getResultList();
			boolean status = false;

			if (results != null) {
				for (Object[] obj : results) {
					JSONObject json = new JSONObject();
					int i = 0;
					String pln_id = convertNullToBlank(String.valueOf(obj[i]));
					json.put("plan_id", plan_id);
					json.put("plan_name", convertNullToBlank(String.valueOf(obj[++i])));
					json.put("plan_validity", convertNullToBlank(String.valueOf(obj[++i])));
					json.put("plan_price", convertNullToBlank(String.valueOf(obj[++i])));
					json.put("plan_discount", convertNullToBlank(String.valueOf(obj[++i])));
					json.put("plan_discount_price", convertNullToBlank(String.valueOf(obj[++i])));

					// getting plan list as per plan name and id
					JSONArray jsonArrayPlanList = new JSONArray();
					Query queryPlanList = em.createNativeQuery(
							"SELECT id,features,valid FROM plan_list where plan_id= :plan_id and delete_flag='N'");
					queryPlanList.setParameter("plan_id", plan_id);
					List<Object[]> resultsPlanList = queryPlanList.getResultList();
					if (resultsPlanList != null) {
						for (Object[] objPlanList : resultsPlanList) {
							int j = 0;
							JSONObject jsonPlanlist = new JSONObject();
							String plan_features_id = convertNullToBlank(String.valueOf(objPlanList[j]));
							String plan_features_name = convertNullToBlank(String.valueOf(objPlanList[++j]));
							String valid = convertNullToBlank(String.valueOf(objPlanList[++j]));

							jsonPlanlist.put("features_id", plan_features_id);
							jsonPlanlist.put("features_name", plan_features_name);
							jsonPlanlist.put("features_valid", valid);
							jsonArrayPlanList.put(jsonPlanlist);
						}
					}
					json.put("features", jsonArrayPlanList);
					resultArray.put(json);
				}
			} else {
				resultArray = null;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return resultArray;
	}

	public HashMap<String, String> getDetailsByMemberWithLoginID(String MemberNumber, int login_id) {
		HashMap<String, String> map = new HashMap<>();
		int checkMemberIdAvailable = uploadImagesService.checkMemberIdAvailable(MemberNumber);

		if (checkMemberIdAvailable > 0) {
			map = this.getMemberNumber(MemberNumber, login_id);
		}
		return map;
	}

	public HashMap<String, String> getDetailsByMemberID(String MemberNumber) {
		HashMap<String, String> map = new HashMap<>();
		int checkMemberIdAvailable = uploadImagesService.checkMemberIdAvailable(MemberNumber);

		if (checkMemberIdAvailable > 0) {
//			int member_length = MemberNumber.length();
//			int splitGetMemberID = Integer.parseInt(MemberNumber.substring(2, member_length));
//			int firstDigit = Integer.parseInt(Integer.toString(splitGetMemberID).substring(0, 1));
//	
//			if (firstDigit == 0) {
//				splitGetMemberID = Integer.parseInt(MemberNumber.substring(3, member_length));
//			}
//			System.out.println("id------------------------ " + splitGetMemberID);
			int login_id = 0;
			map = this.getMemberNumber(MemberNumber, login_id);
		}
		return map;
	}

	public JSONArray getAllMemberByRefineSearch(UpdateMember updateMember, int member_id) {

		String requestedIds = getRequestedIDForMember(member_id);
		String requestIdQuery = "";
		if (!requestedIds.equals("")) {
			requestIdQuery = " and m.member_id not in (" + requestedIds + ")";
		}

//		******************************Column Name*************************************************************************
		String columnName = "m.member_id as member_id,height,lifestyles,known_languages,first_name,last_name,"
				+ "gender,md.age,contact_number,profilecreatedby,md.marital_status,mother_tounge,"
				+ "date_of_birth,mec.annual_income,country_id,cast_id,subcaste_id,religion_id,state_id,city_id";

//	   ******************************Filter Data*************************************************************************

		String whereClause = setWhereClauseForGetAllMemberByRefineFilter(updateMember);

//		   ******************************Query*************************************************************************		
		Query q = em.createNativeQuery(
				"SELECT " + columnName + "  FROM memberdetails as md " + " join member as m on md.member_id=m.member_id"
						+ " join member_education_career as mec on m.member_id=mec.member_id "
//				+ " join member_request as mr on mr.request_from_id!=m.member_id "
						+ " where m.status='ACTIVE' " + whereClause + " and md.member_id!= :member_id "
						+ requestIdQuery);

//		System.out.println("SELECT " + columnName + "  FROM memberdetails as md "
//				+ " join member as m on md.member_id=m.member_id"
//				+ " join member_education_career as mec on m.member_id=mec.member_id "
//				+ " join member_request as mr on mr.request_from_id=mec.member_id "
//						+ " where m.status='ACTIVE' " + whereClause + " and md.member_id!= :member_id " +requestIdQuery);
//		
		q.setParameter("member_id", member_id);

		JSONArray resultArray = new JSONArray();
		List<Object[]> results = q.getResultList();
		if (results != null) {
			for (Object[] obj : results) {
				JSONObject json = new JSONObject();
				int i = 0;

				String memberToID = convertNullToBlank(String.valueOf(obj[i]));
				json.put("member_id", memberToID);
				json.put("height", convertNullToBlank(String.valueOf(obj[++i])));
				json.put("lifestyles", convertNullToBlank(String.valueOf(obj[++i])));
				json.put("known_languages", convertNullToBlank(String.valueOf(obj[++i])));
				json.put("first_name", convertNullToBlank(String.valueOf(obj[++i])));
				json.put("last_name", convertNullToBlank(String.valueOf(obj[++i])));
				json.put("gender", constant.FirstLetterCapital(convertNullToBlank(String.valueOf(obj[++i]))));
				json.put("mage", convertNullToBlank(String.valueOf(obj[++i])));
				json.put("contact_number", convertNullToBlank(String.valueOf(obj[++i])));
				json.put("profilecreatedby", convertNullToBlank(String.valueOf(obj[++i])));
				json.put("maritalStatus", convertNullToBlank(String.valueOf(obj[++i])));
				json.put("mother_tounge", convertNullToBlank(String.valueOf(obj[++i])));
				json.put("date_of_birth", convertNullToBlank(String.valueOf(obj[++i])));
				json.put("income", convertNullToBlank(String.valueOf(obj[++i])));
				json.put("country", getCountryNameByID(convertNullToBlank(String.valueOf(obj[++i]))));
				json.put("caste", getCasteNameByID(convertNullToBlank(String.valueOf(obj[++i]))));
				json.put("subcaste", getSubCasteNameByID(convertNullToBlank(String.valueOf(obj[++i]))));
				json.put("religion", getReligionNameByID(convertNullToBlank(String.valueOf(obj[++i]))));
				json.put("state", getStateNameByID(convertNullToBlank(String.valueOf(obj[++i]))));
				json.put("city", getCityNameByID(convertNullToBlank(String.valueOf(obj[++i]))));
				json.put("len", obj);

//				// check request are sent to other member 
//				Query query = em.createNativeQuery("SELECT request_status,block_status FROM member_request where  request_from_id= :member_from_id and request_to_id= :member_to_id");
//				query.setParameter("member_from_id", member_id);
//				query.setParameter("member_to_id", memberToID);
//				JSONArray resultRequest = new JSONArray();
//				List<Object[]> result = query.getResultList();
//				if (results != null) {
//					for (Object[] objRequest : result) {
//						int j = 0;
//						json.put("request_status", convertNullToBlank(String.valueOf(objRequest[j])));
//						json.put("block_status", convertNullToBlank(String.valueOf(objRequest[++j])));
//					}
//				}else {
//					json.put("request_status","");
//					json.put("block_status", "");
//				}

				resultArray.put(json);

			}
		}
		return resultArray;
	}

	private String getCountryNameByID(String CountryID) {
		String result = "";
		try {
			Query q = em.createNativeQuery(
					"SELECT country_name FROM country where country_id= :CountryID and status='ACTIVE'");
			q.setParameter("CountryID", CountryID);
			result = q.getSingleResult().toString();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	private String setWhereClauseForGetAllMemberByRefineFilter(UpdateMember updateMember) {
		String whereClause = "";
		try {
//			if (updateMember.getAll()!=null && !updateMember.getAll().equals("")) {

			if (updateMember.getReligion_list() != null && !updateMember.getReligion_list().equals("")) {
				String getReligionIDByName = getSelectedReligionIDByName(updateMember.getReligion_list());
				whereClause += " and md.religion_id in (" + getReligionIDByName + ")";
			}
			if (updateMember.getState_list() != null && !updateMember.getState_list().equals("")) {
				String getStateIDByName = getSelectedStateIDByName(updateMember.getState_list());
				whereClause += " and md.state_id in (" + getStateIDByName + ")";
			}
			if (updateMember.getRecentlyJoined() != null && !updateMember.getRecentlyJoined().equals("")) {
				if (updateMember.getRecentlyJoined().equals("Within a day")) {
					whereClause += " and  DATE(m.creation_date)=CURDATE() ";

				} else if (updateMember.getRecentlyJoined().equals("Within a week")) {
					whereClause += " and  m.creation_date >= ( CURDATE() - INTERVAL 7 DAY )";

				} else if (updateMember.getRecentlyJoined().equals("Within a month")) {
					whereClause += " and  m.creation_date >= ( CURDATE() - INTERVAL 40 DAY )";
				}
			}
			if (updateMember.getAnnualIncome() != null && !updateMember.getAnnualIncome().equals("")) {
				String annualIncome = "";
				try {
					if (updateMember.getAnnualIncome().equals("All")) {
//						annualIncome = "'INR 2 Lakh to 4 Lakh','INR 4 Lakh to 7 Lakh','INR 7 Lakh to 10 Lakh','INR 10 Lakh to 15 Lakh','INR 15 Lakh to 20 Lakh','INR 20 Lakh to 30 Lakh'";
						annualIncome = "'Upto INR 1 Lakh','INR 1 Lakh to 5 Lakh','INR 5 Lakh to 10 Lakh','INR 10 Lakh to 15 Lakh','INR 15 Lakh to 20 Lakh','INR 20 Lakh to 25 Lakh','INR 25 Lakh to 50 Lakh','INR 50 Lakh to 75 Lakh','INR 75 Lakh to 1 Crore','INR 1 Crore above','Don't want to specify'";
					} else {	
						annualIncome = setSpecialQuammaAndSingleQuatoToList(updateMember.getAnnualIncome());
					}
					whereClause += " and edu.annual_income in (" + annualIncome + ")";
				}
				catch (Exception e) {
					e.printStackTrace();
				}
				
				
			}
			if (updateMember.getMaritalStatus() != null && !updateMember.getMaritalStatus().equals("")) {
				String maritalStatus = "";
				if (updateMember.getMaritalStatus().equals("All")) {
					maritalStatus = "'Never Married','Divorced','Awaiting Divor','Widowed'";
				} else {
					maritalStatus = setSpecialQuammaAndSingleQuatoToList(updateMember.getMaritalStatus());
				}
				whereClause += " and md.marital_status in (" + maritalStatus + ")";
			}
			if (updateMember.getCountry() != null && !updateMember.getCountry().equals("")) {
				String getCountryIDByName = getCountryIDByName(updateMember.getCountry());
				whereClause += " and md.country_id in (" + getCountryIDByName + ")";
			}
			if (updateMember.getWorkingWith() != null && !updateMember.getWorkingWith().equals("")) {
				String workingWith = "";
				if (updateMember.getWorkingWith().equals("All")) {
					workingWith = "'Private Company','Government / Public Sector','Business / Self Employed','Not Working','Defense / Civil Service'";
				} else {
					workingWith = setSpecialQuammaAndSingleQuatoToList(updateMember.getWorkingWith());
				}

				whereClause += " and edu.working_with in (" + workingWith + ")";
			}
			if (updateMember.getProfileCreadtedBy() != null && !updateMember.getProfileCreadtedBy().equals("")) {
				String profileCreatedBy = "";
				if (updateMember.getProfileCreadtedBy().equals("All")) {
					profileCreatedBy = "'Parents','Self','Sibling','Other','Friends','Relatives'";
				} else {
					profileCreatedBy = setSpecialQuammaAndSingleQuatoToList(updateMember.getProfileCreadtedBy());
				}
				whereClause += " and m.profilecreatedby in (" + profileCreatedBy + ")";
			}
			if (updateMember.getLifeStyles() != null && !updateMember.getLifeStyles().equals("")) {
				String lifeStyles = "";
				if (updateMember.getLifeStyles().equals("All")) {
					lifeStyles = "'Veg','Non-veg','Occasionally Non-Veg','Jain'";
				} else {
					lifeStyles = setSpecialQuammaAndSingleQuatoToList(updateMember.getLifeStyles());
				}
				whereClause += " and md.lifestyles in (" + lifeStyles + ")";

			}
			if (updateMember.getEducationalLevels() != null && !updateMember.getEducationalLevels().equals("")) {
				String educationLevels = "";
				if (updateMember.getEducationalLevels().equals("ALL")) {
					educationLevels = "'Bachelor','Master','Honours','Diploma','Doctorate','Associate'";
				} else {
					educationLevels = setSpecialQuammaAndSingleQuatoToList(updateMember.getEducationalLevels());
				}
				whereClause += " and edu.highest_qualification in (" + educationLevels + ")";
			}
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return whereClause;
	}

	private String getCountryIDByName(String country) {
		String result = "";
		try {
			country = setSpecialQuammaAndSingleQuatoToList(country);
			Query q = em.createNativeQuery("select GROUP_CONCAT(country_id) from country where country_name in ("
					+ country + ") and status='ACTIVE'");
//			q.setParameter("religion_list", religion_list);
//			System.out.println("select GROUP_CONCAT(religion_id) from religion where religion_name in ("+state_list+") and status='ACTIVE'");
			result = q.getSingleResult().toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	private String getSelectedStateIDByName(String state_list) {
		String result = "";
		try {
			state_list = setSpecialQuammaAndSingleQuatoToList(state_list);
			Query q = em.createNativeQuery("select GROUP_CONCAT(state_id) from states where state_name in ("
					+ state_list + ") and status='ACTIVE'");
//			q.setParameter("religion_list", religion_list);
//			System.out.println("select GROUP_CONCAT(religion_id) from religion where religion_name in ("+state_list+") and status='ACTIVE'");
			result = q.getSingleResult().toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	private String getSelectedReligionIDByName(String religion_list) {
		String result = "";
		try {
			religion_list = setSpecialQuammaAndSingleQuatoToList(religion_list);
			Query q = em.createNativeQuery("select GROUP_CONCAT(religion_id) from religion where religion_name in ("
					+ religion_list + ") and status='ACTIVE'");
//			q.setParameter("religion_list", religion_list);
//			System.out.println("select GROUP_CONCAT(religion_id) from religion where religion_name in ("+religion_list+") and status='ACTIVE'");
			result = q.getSingleResult().toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	private String setSpecialQuammaAndSingleQuatoToList(String strValue) {
		String results = "";
		try {
			if (strValue.contains(",")) {
				String mStrArray[] = strValue.split(",");
				for (int i = 0; i < mStrArray.length; i++) {
					if (i == 0) {
						results = "'" + mStrArray[i] + "'";
					} else {
						results = results + ",'" + mStrArray[i] + "'";
					}
				}
			} else {
				results = "'" + strValue + "'";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return results;
	}

	public HashMap<String, String> getHoroscopeDetails(int member_id) {
		HashMap<String, String> map = new HashMap<>();
		try {
			Query q = em.createNativeQuery(
					"SELECT country_of_birth,city_of_birth,time_status,manglik,hours,minutes,time,hr_dob FROM member_horoscope where status='ACTIVE' and member_id= :member_id");
			q.setParameter("member_id", member_id);
			List<Object[]> results = q.getResultList();
			boolean status = false;
			if (results != null) {
				for (Object[] obj : results) {
					int i = 0;
					map.put("country_of_birth", convertNullToBlank(String.valueOf(obj[i])));
					map.put("city_of_birth", convertNullToBlank(String.valueOf(obj[++i])));
					map.put("time_status", convertNullToBlank(String.valueOf(obj[++i])));
					map.put("manglik", convertNullToBlank(String.valueOf(obj[++i])));

					map.put("hours", convertNullToBlank(String.valueOf(obj[++i])));
					map.put("minutes", convertNullToBlank(String.valueOf(obj[++i])));
					map.put("time", convertNullToBlank(String.valueOf(obj[++i])));

					String getBirthdate = convertNullToBlank(
							uploadImagesService.getDateOfBirthFromMemberDetailsTable(member_id));
					map.put("date_of_birth", convertNullToBlank(getBirthdate));
					status = true;
				}
			}
			if (status == false) {
				map = null;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return map;
	}

	public HashMap<String, String> getMatchPartnerPreference(int member_id, int login_id) {
		HashMap<String, String> map = new HashMap<String, String>();
		boolean status = false;
		String columnName = " m.member_id,height,lifestyles,md.age,"
				+ "md.marital_status as maritalStatus,mother_tounge,gender,"
				+ "(select country_name from country where country_id=(select country_id from memberdetails where member_id= :login_id )) as country_name,country_id,"
				+ "(select state_name from states where state_id=(select state_id from memberdetails where member_id= :login_id)) as state,state_id,"
				+ "(select city_name from city where city_id=(select city_id from memberdetails where member_id= :login_id)) as city,city_id,"
				+ "(select religion_name from religion where religion_id=(select religion_id from memberdetails where member_id= :login_id)) as religion,religion_id,"
				+ "(select cast_name from cast where cast_id=(select cast_id from memberdetails where member_id= :login_id )) as caste,cast_id,"
				+ "edu.highest_qualification as highest_qualification,edu.working_with as working_with,edu.working_as as working_as,edu.annual_income as annual_income";
		try {

//			System.out.println("SELECT " + columnName + "  FROM memberdetails as md "
//					+ " join member as m on md.member_id=m.member_id"
//					+ " join member_family_details as fd on m.member_id=fd.member_id"
//					+ " join member_education_career as edu on m.member_id=edu.member_id "
//					+ " join member_horoscope as mh on m.member_id=mh.member_id "
//					+ " where md.member_id= :id and m.status='ACTIVE'");

			Query q = em.createNativeQuery("SELECT " + columnName + "  FROM memberdetails as md "
					+ " join member as m on md.member_id=m.member_id"
					+ " join member_family_details as fd on m.member_id=fd.member_id"
					+ " join member_education_career as edu on m.member_id=edu.member_id "
					+ " join member_horoscope as mh on m.member_id=mh.member_id " + " where md.member_id= :login_id");

			q.setParameter("login_id", login_id);
			List<Object[]> results = q.getResultList();
			if (results != null) {
				for (Object[] obj : results) {
					int i = 0;
					// first row
					String myMemberID = convertNullToBlank(String.valueOf(obj[i]));
					String myHeight = convertNullToBlank(String.valueOf(obj[++i]));
					String myLifeStyles = convertNullToBlank(String.valueOf(obj[++i]));
					String myAge = convertNullToBlank(String.valueOf(obj[++i]));
					String myMaritalStatus = convertNullToBlank(String.valueOf(obj[++i]));
					String myMotherTongue = convertNullToBlank(String.valueOf(obj[++i]));
					String myGender = convertNullToBlank(String.valueOf(obj[++i]));
					String myCountryName = convertNullToBlank(String.valueOf(obj[++i]));
					String myCountryID = convertNullToBlank(String.valueOf(obj[++i]));
					String myStateName = convertNullToBlank(String.valueOf(obj[++i]));
					String myStateID = convertNullToBlank(String.valueOf(obj[++i]));
					String myCityName = convertNullToBlank(String.valueOf(obj[++i]));
					String myCityID = convertNullToBlank(String.valueOf(obj[++i]));
					String myReligionName = convertNullToBlank(String.valueOf(obj[++i]));
					String myReligionID = convertNullToBlank(String.valueOf(obj[++i]));
					String myCastName = convertNullToBlank(String.valueOf(obj[++i]));
					String myCastID = convertNullToBlank(String.valueOf(obj[++i]));
					String myQualifications = convertNullToBlank(String.valueOf(obj[++i]));
					String myWorkingWith = convertNullToBlank(String.valueOf(obj[++i]));
					String myWorkingAs = convertNullToBlank(String.valueOf(obj[++i]));
					String myAnnualIncome = convertNullToBlank(String.valueOf(obj[++i]));

					// get partner preference details and match contents ,,
					// partner_professional_area,
					Query queryPartner = em.createNativeQuery(
							"SELECT partner_marital_status, partner_mother_tongue, partner_qualification, partner_working_with, "
									+ "partner_religions, partner_cast, partner_country, partner_state, partner_city, partner_from_age, partner_to_age, "
									+ " partner_from_height, partner_to_height,partner_annual_income "
									+ "  FROM member_preference where member_id= :member_id");

					queryPartner.setParameter("member_id", member_id);
					List<Object[]> resultsPartner = queryPartner.getResultList();
					if (resultsPartner != null) {
						for (Object[] objPartner : resultsPartner) {
							int j = 0;
							int matchCount = 0;
							int matchPreference = 0;
							int appMatchPreference = 0;
							// Match Marital Status
							String partner_marital_status = convertNullToBlank(String.valueOf(objPartner[j]));
							if (!partner_marital_status.equals("")) {
								if (partner_marital_status.contains(myMaritalStatus)) {
									map.put("partner_marital_status", partner_marital_status);
									map.put("my_marital_status", "Yes");
									++matchCount;
								} else {
									map.put("partner_marital_status", partner_marital_status);
									map.put("my_marital_status", "NO");
								}
								++matchPreference;
							} else {
								map.put("partner_marital_status", "");
								map.put("my_marital_status", "BLANK");
							}
							++appMatchPreference;

							// Match Mother Tongue
							String partner_mother_tongue = convertNullToBlank(String.valueOf(objPartner[++j]));
							if (!partner_mother_tongue.equals("")) {
								if (partner_mother_tongue.contains(myMotherTongue)) {
									map.put("partner_mother_tongue", partner_mother_tongue);
									map.put("my_mother_tongue", "Yes");
									++matchCount;
								} else {
									map.put("partner_mother_tongue", partner_mother_tongue);
									map.put("my_mother_tongue", "NO");
								}
								++matchPreference;
							} else {
								map.put("partner_mother_tongue", "");
								map.put("my_mother_tongue", "BLANK");
							}
							++appMatchPreference;

							// Match Qualifications
							String partner_qualification = convertNullToBlank(String.valueOf(objPartner[++j]));
							if (!partner_qualification.equals("")) {
								if (partner_qualification.contains(myQualifications)) {
									map.put("partner_qualification", partner_qualification);
									map.put("my_qualification", "Yes");
									++matchCount;
								} else {
									map.put("partner_qualification", partner_qualification);
									map.put("my_qualification", "NO");
								}
								++matchPreference;
							} else {
								map.put("partner_qualification", "");
								map.put("my_qualification", "BLANK");
							}
							++appMatchPreference;

							// Match Working With
							String partner_working_with = convertNullToBlank(String.valueOf(objPartner[++j]));
							if (!partner_working_with.equals("")) {
								if (partner_working_with.contains(myWorkingWith)) {
									map.put("partner_working_with", partner_working_with);
									map.put("my_working_with", "Yes");
									++matchCount;
								} else {
									map.put("partner_working_with", partner_working_with);
									map.put("my_working_with", "NO");
								}
								++matchPreference;
							} else {
								map.put("partner_working_with", "");
								map.put("my_working_with", "BLANK");
							}
							++appMatchPreference;

							// Match Professional Area- not included when long registrations form
//									String.valueOf(objPartner[++j]) // for professional 

							// Match Religions
							String checkBothKeywordsForReligionAndCast = "";
							String religionsName = "";
							String partner_religions_ids = convertNullToBlank(String.valueOf(objPartner[++j]));
							if (!partner_religions_ids.equals("")) {
								religionsName = checkIsQuammaSeperatedValue(partner_religions_ids, "religions");

								if (partner_religions_ids.contains(myReligionID)) {
									checkBothKeywordsForReligionAndCast = "Yes";
								} else {
									checkBothKeywordsForReligionAndCast = "Yes";
								}
							} else {
								checkBothKeywordsForReligionAndCast = "BLANK";
							}

							// Match Cast
							String castName = "";
							String partner_cast_ids = convertNullToBlank(String.valueOf(objPartner[++j]));
							if (!partner_cast_ids.equals("")) {
								castName = checkIsQuammaSeperatedValue(partner_cast_ids, "cast");
								if (partner_cast_ids.contains(myCastID)) {
									checkBothKeywordsForReligionAndCast = checkBothKeywordsForReligionAndCast + ",Yes";
								} else {
									checkBothKeywordsForReligionAndCast = checkBothKeywordsForReligionAndCast + ",NO";
								}
							} else {
								checkBothKeywordsForReligionAndCast = checkBothKeywordsForReligionAndCast + ",BLANK";
							}

							if (!checkBothKeywordsForReligionAndCast.equals("")) {
								if (checkBothKeywordsForReligionAndCast.contains("Yes")) {
									String religionsWithCastName = "";
									if (religionsName != null && !religionsName.equals("")) {
										religionsWithCastName = religionsName;
									}
									if (castName != null && !castName.equals("")) {
										if (religionsName != null && !religionsName.equals("")) {
											religionsWithCastName = religionsName + "," + castName;
										} else {
											religionsWithCastName = castName;
										}
									}

									map.put("partner_religions", religionsWithCastName);
									map.put("my_religions", "Yes");
									++matchCount;

								} else if (checkBothKeywordsForReligionAndCast.contains("NO")) {
									map.put("partner_religions", religionsName + ":" + castName);
									map.put("my_religions", "NO");
								} else if (checkBothKeywordsForReligionAndCast.contains("BLANK")) {
									map.put("partner_religions", "");
									map.put("my_religions", "BLANK");
								}
								++matchPreference;

							}
							++appMatchPreference;

							// Match country
							String partner_country_ids = convertNullToBlank(String.valueOf(objPartner[++j]));
							if (!partner_country_ids.equals("")) {
								String countryName = checkIsQuammaSeperatedValue(partner_country_ids, "country");
								if (partner_country_ids.contains(myCountryID)) {
									map.put("partner_country", countryName);
									map.put("my_country", "Yes");
									++matchCount;
								} else {
									map.put("partner_country", countryName);
									map.put("my_country", "NO");
								}
								++matchPreference;
							} else {
								map.put("partner_country", "");
								map.put("my_country", "BLANK");
							}
							++appMatchPreference;

							// Match State
							String partner_state_ids = convertNullToBlank(String.valueOf(objPartner[++j]));
							if (!partner_state_ids.equals("")) {
								String stateName = checkIsQuammaSeperatedValue(partner_state_ids, "state");
								if (partner_state_ids.contains(myStateID)) {
									map.put("partner_state", stateName);
									map.put("my_state", "Yes");
									++matchCount;
								} else {
									map.put("partner_state", stateName);
									map.put("my_state", "NO");
								}
								++matchPreference;
							} else {
								map.put("partner_state", "");
								map.put("my_state", "BLANK");
							}
							++appMatchPreference;

							// Match city
							String partner_city_ids = convertNullToBlank(String.valueOf(objPartner[++j]));
							if (!partner_city_ids.equals("")) {
								String cityName = checkIsQuammaSeperatedValue(partner_city_ids, "city");
								if (partner_city_ids.contains(myCityID)) {
									map.put("partner_city", cityName);
									map.put("my_city", "Yes");
									++matchCount;
								} else {
									map.put("partner_city", cityName);
									map.put("my_city", "NO");
								}
								++matchPreference;
							} else {
								map.put("partner_city", "");
								map.put("my_city", "BLANK");
							}
							++appMatchPreference;

							// age
							String p_from_age = convertNullToBlank(String.valueOf(objPartner[++j]));
							String p_to_age = convertNullToBlank(String.valueOf(objPartner[++j]));

							if (!p_from_age.equals("") && !p_to_age.equals("")) {
								int partner_from_age = Integer.parseInt(p_from_age);
								int partner_to_age = Integer.parseInt(p_to_age);

								if (!myAge.equals("")) {
									int my_age = Integer.parseInt(myAge);

									if (partner_from_age <= my_age && partner_to_age >= my_age) {
										map.put("partner_age", p_from_age + " to " + p_to_age);
										map.put("my_age", "Yes");
										++matchCount;
									} else {
										map.put("partner_age", p_from_age + " to " + p_to_age);
										map.put("my_age", "NO");
									}
								} else {
									map.put("partner_age", "");
									map.put("my_age", "BLANK");
								}

								++matchPreference;
							} else {
								map.put("partner_age", "");
								map.put("my_age", "BLANK");
							}
							++appMatchPreference;

							// height
							String p_from_height = convertNullToBlank(String.valueOf(objPartner[++j]));
							String p_to_height = convertNullToBlank(String.valueOf(objPartner[++j]));

							if (!p_from_height.equals("") && !p_to_height.equals("")) {

								String my_height = myHeight;

								String[] splitArrayFromHeight = p_from_height.split("-");
								String fromHeightString = splitArrayFromHeight[1];
								int partneFromHeight = Integer.parseInt(fromHeightString.replaceAll("[^0-9]", ""));

								String[] splitArrayToHeight = p_to_height.split("-");
								String fromToString = splitArrayToHeight[1];
								int partnerToHeight = Integer.parseInt(fromToString.replaceAll("[^0-9]", ""));

								String[] splitArrayMyHeight = my_height.split("-");
								String fromMyHeightString = splitArrayMyHeight[1];
								int numberMyHeight = Integer.parseInt(fromMyHeightString.replaceAll("[^0-9]", ""));

								if (partneFromHeight <= numberMyHeight && partnerToHeight >= numberMyHeight) {
									map.put("partner_height", fromHeightString + " to " + fromToString);
									map.put("my_height", "Yes");
									++matchCount;
								} else {
									map.put("partner_height", fromHeightString + " to " + fromToString);
									map.put("my_height", "NO");
								}
								++matchPreference;
							} else {
								map.put("partner_height", "");
								map.put("my_height", "BLANK");
							}
							++appMatchPreference;
//									
							// Match Annual Income
							String partner_annual_income = convertNullToBlank(String.valueOf(objPartner[++j]));
							if (!partner_annual_income.equals("")) {
								if (partner_annual_income.contains(myAnnualIncome)) {
									map.put("partner_annual_income", partner_annual_income);
									map.put("my_annual_income", "Yes");
									++matchCount;
								} else {
									map.put("partner_annual_income", partner_annual_income);
									map.put("my_annual_income", "NO");
								}
								++matchPreference;
							} else {
								map.put("partner_annual_income", "");
								map.put("my_annual_income", "BLANK");
							}
							++appMatchPreference;

							map.put("match_count", "" + matchCount);
							map.put("total_preference", "" + matchPreference);
							map.put("app_total_preference", "" + appMatchPreference);

							String title = "", gender_preference = "";
							if (!myGender.equals("")) {
								if (myGender.equals("female") || myGender.equals("Female")) {
									title = "What he is looking for";
									gender_preference = "his Preferences";
								}
								if (myGender.equals("male") || myGender.equals("male")) {
									title = "What She is looking for";
									gender_preference = "her Preferences";
								}
							} else {
								title = "What is looking for";
								gender_preference = "Preferences";
							}

							JSONArray jsonResultsArray = new JSONArray();
							jsonResultsArray = uploadImagesService.getMemberAppPhotos(myMemberID);
//							json.put("images",jsonResultsArray);
							map.put("images_count", "" + jsonResultsArray.length());

							map.put("title", title);
							map.put("gender_preference", gender_preference);
							status = true;
						}

					}

				}
			}
			if (status == false) {
				map = null;
			}

//			System.out.println(q);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	public String checkIsQuammaSeperatedValue(String val, String category) {
		String results = "";
		if (val != null && !val.equals("")) {
			if (val.contains(",")) {
				String[] splitArray = val.split(",");
				for (int i = 0; i < splitArray.length; i++) {
					if (i == 0) {
						if (category.equals("religions")) {
							results = getNameByIDMangerFactory.getReligionNameByID(splitArray[i]);
						}
						if (category.equals("cast")) {
							results = getNameByIDMangerFactory.getCasteNameByID(splitArray[i]);
						}
						if (category.equals("country")) {
							results = getNameByIDMangerFactory.getCountryNameByID(splitArray[i]);
						}
						if (category.equals("state")) {
							results = getNameByIDMangerFactory.getStateNameByID(splitArray[i]);
						}
						if (category.equals("city")) {
							results = getNameByIDMangerFactory.getCityNameByID(splitArray[i]);
						}

					} else {
						if (category.equals("religions")) {
							if (splitArray[i] != null && !splitArray[i].equals("") && !splitArray[i].equals("0")) {
								results = results + "," + getNameByIDMangerFactory.getReligionNameByID(splitArray[i]);
							}
						}
						if (category.equals("cast")) {
							if (splitArray[i] != null && !splitArray[i].equals("") && !splitArray[i].equals("0")) {
								results = results + "," + getNameByIDMangerFactory.getCasteNameByID(splitArray[i]);
							}
						}
						if (category.equals("country")) {
							if (splitArray[i] != null && !splitArray[i].equals("") && !splitArray[i].equals("0")) {
								results = results + "," + getNameByIDMangerFactory.getCountryNameByID(splitArray[i]);
							}
						}
						if (category.equals("state")) {
							if (splitArray[i] != null && !splitArray[i].equals("") && !splitArray[i].equals("0")) {
								results = results + "," + getNameByIDMangerFactory.getStateNameByID(splitArray[i]);
							}
						}
						if (category.equals("city")) {
							if (splitArray[i] != null && !splitArray[i].equals("") && !splitArray[i].equals("0")) {
								results = results + "," + getNameByIDMangerFactory.getCityNameByID(splitArray[i]);
							}
						}
					}
				}
			} else {
				if (category.equals("religions")) {
					results = getNameByIDMangerFactory.getReligionNameByID(val);
				}
				if (category.equals("cast")) {
					results = getNameByIDMangerFactory.getCasteNameByID(val);
				}
				if (category.equals("country")) {
					results = getNameByIDMangerFactory.getCountryNameByID(val);
				}
				if (category.equals("state")) {
					results = getNameByIDMangerFactory.getStateNameByID(val);
				}
				if (category.equals("city")) {
					results = getNameByIDMangerFactory.getCityNameByID(val);
				}
			}
		}

		return results;
	}

	private String getMemberIDForMatches(int member_id, String matches_status) {
		String ids = "";
		try {
			String myQuery = "";
			if (matches_status.equals("NEW_MATCHES")) {
				myQuery = "SELECT group_concat(member_id) FROM member where  member_id!= :member_id  and user_id in (select id from users where otp_verified=1  and short_reg_status=1 and MONTH(creation_date) >= MONTH(CURRENT_DATE - INTERVAL 1 MONTH))";
			} else if (matches_status.equals("MY_MATCHES")) {
				myQuery = "SELECT group_concat(member_id) FROM member where  member_id!= :member_id  and user_id in (select id from users where otp_verified=1 and short_reg_status=1 )";
			} else if (matches_status.equals("TODAYS_MATCHES")) {
				myQuery = "SELECT group_concat(member_id) FROM member where  member_id!= :member_id  and user_id in (select id from users where short_reg_status=1 and otp_verified=1 and DATE(creation_date)= CURDATE())";
			}
			Query query = em.createNativeQuery(myQuery);
			query.setParameter("member_id", member_id);
			ids = query.getSingleResult().toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ids;
	}

	public JSONArray getNewMatches(int member_id, String matches_status) {
		JSONArray resultArray = new JSONArray();
		String first_name = "", last_name = "";
		try {
			String ids = "";
			// get total member id partner perference
			ids = getMemberIDForMatches(member_id, matches_status);

			matchesConstants.getMemberMatchPartnerPreference(member_id);
			String columnName = "first_name,last_name, m.member_id, height,lifestyles,md.age,"
					+ "md.marital_status as maritalStatus,mother_tounge,gender,"
					+ "(select country_name from country where country_id=(select country_id from memberdetails where member_id= :member_id )) as country_name,country_id,"
					+ "(select state_name from states where state_id=(select state_id from memberdetails where member_id= :member_id)) as state,state_id,"
					+ "(select city_name from city where city_id=(select city_id from memberdetails where member_id= :member_id)) as city,city_id,"
					+ "(select religion_name from religion where religion_id=(select religion_id from memberdetails where member_id= :member_id)) as religion,religion_id,"
					+ "(select cast_name from cast where cast_id=(select cast_id from memberdetails where member_id= :member_id )) as caste,cast_id,"
					+ "edu.highest_qualification as highest_qualification,edu.working_with as working_with,edu.working_as as working_as,edu.annual_income as annual_income";

			Query q = em.createNativeQuery("SELECT " + columnName + "  FROM memberdetails as md "
					+ " join member as m on md.member_id=m.member_id"
					+ " join member_education_career as edu on m.member_id=edu.member_id "
					+ " where md.member_id!= :member_id and m.status='ACTIVE'");

			q.setParameter("member_id", member_id);
			List<Object[]> results = q.getResultList();
			if (results != null) {
				for (Object[] obj : results) {
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
						JSONObject json = new JSONObject();
						json.put("first_name", first_name);
						json.put("last_name", last_name);
						json.put("mage", myAge);
						json.put("religion", myReligionName);
						json.put("maritalStatus", myMaritalStatus);
						json.put("income", myAnnualIncome);
						json.put("member_id", memberID);
						json.put("request_status", "");
						json.put("block_status", "");
						resultArray.put(json);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultArray;
	}

	public HashMap<String, String> getMyProfileMember(int id) {
		HashMap<String, String> map = new HashMap<>();

		String columnName = "member_number,m.member_id,m.user_id, membernative,height,weight,lifestyles,known_languages,education,job,income,hobbies,expectations,first_name,last_name,gender,md.age,"
				+ "contact_number,email_id,profilecreatedby,md.marital_status as maritalStatus,no_of_children,mother_tounge,date_of_birth,"
				+ "health_info,blood_group,gothra,ethnic_corigin,pincode,about_ourself,profile_photo_id,"
				+ "(select country_name from country where country_id=(select country_id from memberdetails where member_id= :id )) as country_name,country_id,"
				+ "sub_caste_name,"
				+ "(select cast_name from cast where cast_id=(select cast_id from memberdetails where member_id= :id )) as caste,cast_id,"
				+ "(select subcast_name from subcasts where subcast_id=(select subcaste_id from memberdetails where member_id= :id)) as subcaste,"
				+ "(select religion_name from religion where religion_id=(select religion_id from memberdetails where member_id= :id)) as religion,religion_id,"
				+ "(select state_name from states where state_id=(select state_id from memberdetails where member_id= :id)) as state,state_id,"
				+ "(select city_name from city where city_id=(select city_id from memberdetails where member_id= :id)) as city,city_id,"
				+ "fd.father_status as father_status,fd.father_company_name as father_company_name,fd.father_designation as father_designation,fd.father_business_name as father_business_name,"
				+ "fd.mother_status as mother_status, fd.mother_company_name as mother_company_name,fd.mother_designation as mother_designation,fd.mother_business_name as mother_business_name,"
				+ "fd.family_location as family_location,fd.native_place as native_place,fd.family_type as family_type,fd.family_values as family_values,fd.family_affluence as family_affluence,"
				+ "fd.married_male as married_male,fd.unmarried_male as unmarried_male,fd.married_female as married_female,fd.unmarried_female as unmarried_female,"
				+ "edu.highest_qualification as highest_qualification,edu.college_attended as college_attended,edu.working_with as working_with,edu.working_as as working_as,edu.employer_name as employer_name,edu.annual_income as annual_income,edu.ug_education as ugedu,"
				+ "mh.manglik,mh.nakshatra,mh.time_of_birth,mh.time_status,mh.city_of_birth,mh.hr_dob,"
				+ "mh.country_of_birth,mh.hours,mh.minutes,mh.time ";
		try {

			String query = "SELECT " + columnName + "  FROM memberdetails as md "
					+ " join member as m on md.member_id=m.member_id"
					+ " join member_family_details as fd on m.member_id=fd.member_id"
					+ " join member_education_career as edu on m.member_id=edu.member_id "
					+ " join member_horoscope as mh on m.member_id=mh.member_id "
					+ " where md.member_id= :id order by m.member_id desc ";

			Query q = em.createNativeQuery(query);
//			System.out.println(q);
			String contact_number = "", email_id = "";
			q.setParameter("id", id);
			boolean status = false;
			List<Object[]> results = q.getResultList();
			if (results != null) {
				for (Object[] obj : results) {
					int i = 0;
					String profileID = convertNullToBlank(String.valueOf(obj[i]));
					String thisMemberID = convertNullToBlank(String.valueOf(obj[++i]));
					String thisUserID = convertNullToBlank(String.valueOf(obj[++i]));
					String franchiseCode = userEntityManagerFactory
							.getFranciseCodeByUserID(Integer.parseInt(thisUserID));

					int shortlist_status = uploadImagesService.getShortListStatus("" + id, thisMemberID);
					if (shortlist_status > 0) {
						map.put("shortlist_status", "1");
					} else {
						map.put("shortlist_status", "0");
					}

					// first row
					map.put("profile_id", profileID);
					map.put("member_id", thisMemberID);
					map.put("franchise_code", franchiseCode);
					map.put("native", convertNullToBlank(String.valueOf(obj[++i])));
					String height = convertNullToBlank(String.valueOf(obj[++i]));
					map.put("height", height);
					map.put("weight", convertNullToBlank(String.valueOf(obj[++i])));
					map.put("lifestyles", convertNullToBlank(String.valueOf(obj[++i])));
					map.put("known_languages", convertNullToBlank(String.valueOf(obj[++i])));
					map.put("education", convertNullToBlank(String.valueOf(obj[++i])));
					map.put("job", convertNullToBlank(String.valueOf(obj[++i])));
					map.put("income", convertNullToBlank(String.valueOf(obj[++i])));
					map.put("hobbies", convertNullToBlank(String.valueOf(obj[++i])));
					map.put("expectations", convertNullToBlank(String.valueOf(obj[++i])));
					map.put("first_name", convertNullToBlank(String.valueOf(obj[++i])));
					map.put("last_name", convertNullToBlank(String.valueOf(obj[++i])));
					String gender = convertNullToBlank(String.valueOf(obj[++i]));
					map.put("gender", constant.FirstLetterCapital(gender));
					map.put("age", convertNullToBlank(String.valueOf(obj[++i])));

					// second row
					// check contact privacy
					contact_number = convertNullToBlank(String.valueOf(obj[++i]));
					map.put("contact_number", contact_number);

					// check email privacy
					email_id = convertNullToBlank(String.valueOf(obj[++i]));
					map.put("email_id", email_id);

					map.put("profilecreatedby", convertNullToBlank(String.valueOf(obj[++i])));
					String marital_status = convertNullToBlank(String.valueOf(obj[++i]));
					map.put("marital_status", marital_status);
					map.put("no_of_children", convertNullToBlank(String.valueOf(obj[++i])));
					map.put("mother_tounge", convertNullToBlank(String.valueOf(obj[++i])));

					// dob privacy
					String dob = convertNullToBlank(String.valueOf(obj[++i]));
					map.put("date_of_birth", dob);

					// third row
					map.put("health_info", convertNullToBlank(String.valueOf(obj[++i])));
					map.put("blood_group", convertNullToBlank(String.valueOf(obj[++i])));
					map.put("gothra", convertNullToBlank(String.valueOf(obj[++i])));
					map.put("ethnic_corigin", convertNullToBlank(String.valueOf(obj[++i])));
					map.put("pincode", convertNullToBlank(String.valueOf(obj[++i])));
					map.put("about_ourself", convertNullToBlank(String.valueOf(obj[++i])));

					String profile_photo_id = convertNullToBlank(String.valueOf(obj[++i]));
					String getProfilePath = "";
					if (!profile_photo_id.equals("") && !profile_photo_id.equals("0")) {
						getProfilePath = uploadImagesService.getMemberProfilePhotoPath(profile_photo_id);
					}
					map.put("profile_photo", getProfilePath);

					// forth row
//					String val= convertNullToBlank(String.valueOf(obj[++i]);
					map.put("country_name", convertNullToBlank(String.valueOf(obj[++i])));
					map.put("country_id", convertNullToBlank(String.valueOf(obj[++i])));
					map.put("sub_caste_name", convertNullToBlank(String.valueOf(obj[++i])));

					// fifth,sixth,seven,eight,nine row
					map.put("caste", convertNullToBlank(String.valueOf(obj[++i])));
					map.put("cast_id", convertNullToBlank(String.valueOf(obj[++i])));

					map.put("subcaste", convertNullToBlank(String.valueOf(obj[++i])));
					map.put("religion_name", convertNullToBlank(String.valueOf(obj[++i])));
					map.put("religion_id", convertNullToBlank(String.valueOf(obj[++i])));

					map.put("state", convertNullToBlank(String.valueOf(obj[++i])));
					map.put("state_id", convertNullToBlank(String.valueOf(obj[++i])));

					map.put("city", convertNullToBlank(String.valueOf(obj[++i])));
					map.put("city_id", convertNullToBlank(String.valueOf(obj[++i])));

					// 10th row
					// get family details

					String father_status = convertNullToBlank(String.valueOf(obj[++i]));
					String father_company_name = convertNullToBlank(String.valueOf(obj[++i]));
					String father_designation = convertNullToBlank(String.valueOf(obj[++i]));
					String father_business_name = convertNullToBlank(String.valueOf(obj[++i]));

					// 11th row
					String mother_status = convertNullToBlank(String.valueOf(obj[++i]));
					String mother_company_name = convertNullToBlank(String.valueOf(obj[++i]));
					String mother_designation = convertNullToBlank(String.valueOf(obj[++i]));
					String mother_business_name = convertNullToBlank(String.valueOf(obj[++i]));

					// 12th row
					String family_location = convertNullToBlank(String.valueOf(obj[++i]));
					String native_place = convertNullToBlank(String.valueOf(obj[++i]));
					String family_type = convertNullToBlank(String.valueOf(obj[++i]));
					String familyValues = convertNullToBlank(String.valueOf(obj[++i]));
					String family_affluence = convertNullToBlank(String.valueOf(obj[++i]));

					map.put("father_status", father_status);
					map.put("father_company_name", father_company_name);
					map.put("father_designation", father_designation);
					map.put("father_business_name", father_business_name);
					map.put("mother_status", mother_status);
					map.put("mother_company_name", mother_company_name);
					map.put("mother_designation", mother_designation);
					map.put("mother_business_name", mother_business_name);
					map.put("family_location", family_location);
					map.put("native_place", native_place);
					map.put("family_type", family_type);
					map.put("familyValues", familyValues);
					map.put("family_affluence", family_affluence);

					// 13th row
					map.put("married_male", convertNullToBlank(String.valueOf(obj[++i])));
					map.put("unmarried_male", convertNullToBlank(String.valueOf(obj[++i])));
					map.put("married_female", convertNullToBlank(String.valueOf(obj[++i])));
					map.put("unmarried_female", convertNullToBlank(String.valueOf(obj[++i])));
					String mGender = "";
					if (gender.equals("male")) {
						mGender = "his";
					}
					if (gender.equals("female")) {
						mGender = "her";
					}
					String getFamilyDetailsString = getFamilyDetailsInOneStatement(mGender, father_status,
							father_company_name, father_designation, father_business_name, mother_status,
							mother_company_name, mother_designation, mother_business_name, family_location,
							native_place, family_type, familyValues, family_affluence);

					// get edyucation and qualifications details
					map.put("highest_qualification", convertNullToBlank(String.valueOf(obj[++i])));
					map.put("college_attended", convertNullToBlank(String.valueOf(obj[++i])));

					String workingWith = convertNullToBlank(String.valueOf(obj[++i]));
					String workingAs = convertNullToBlank(String.valueOf(obj[++i]));
					String employerName = convertNullToBlank(String.valueOf(obj[++i]));
					String getCareerDetails = getEducationAndCareerDetails(workingWith, workingAs, employerName);

					map.put("working_with", workingWith);
					map.put("working_as", workingAs);
					map.put("employer_name", employerName);

					// get Pending,Rejected,Canceled status
					String request_status = "";
					String block_status = "";
					String getMemberStatus = updateMemberService.getMemberStatus(id, thisMemberID);
					if (getMemberStatus != null && !getMemberStatus.equals("")) {
						request_status = getMemberStatus;
					}
					map.put("request_status", request_status);

					String getMemberBlockStatus = updateMemberService.getMemberBlockStatus(id, thisMemberID);
					if (getMemberBlockStatus != null && !getMemberBlockStatus.equals("")) {
						block_status = getMemberBlockStatus;
					}
					map.put("block_status", block_status);

					// check annual income privacy
					String annualIncome = convertNullToBlank(String.valueOf(obj[++i]));
					map.put("annual_income", annualIncome);
					map.put("ug_education", convertNullToBlank(String.valueOf(obj[++i])));
					// 14th row
					map.put("manglik", convertNullToBlank(String.valueOf(obj[++i])));
					map.put("nakshatra", convertNullToBlank(String.valueOf(obj[++i])));
					map.put("time_of_birth", convertNullToBlank(String.valueOf(obj[++i])));
					map.put("time_status", convertNullToBlank(String.valueOf(obj[++i])));
					map.put("city_of_birth", convertNullToBlank(String.valueOf(obj[++i])));
					map.put("horoscope_date_of_birth", convertNullToBlank(String.valueOf(obj[++i])));
					map.put("country_of_birth", convertNullToBlank(String.valueOf(obj[++i])));
					map.put("hours", convertNullToBlank(String.valueOf(obj[++i])));
					map.put("minutes", convertNullToBlank(String.valueOf(obj[++i])));
					map.put("time", convertNullToBlank(String.valueOf(obj[++i])));

					map.put("working_details", getCareerDetails);
					map.put("FamilyDetails", getFamilyDetailsString);

					// send sms/mail to visitors ids

					status = true;
				}
			}

			if (status == false) {
				map = null;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return map;
	}

	public int getMatches_Count(int id, String matches_status) {
		JSONArray resultArray = new JSONArray();
		boolean status = false;
		int results = 0;
		try {
			matchesConstants.getMemberMatchPartnerPreference(id);
			String requestedIds = getRequestedIDForMember(id);
//			String shortlistIds = getShortListIDForMember(id);
			String requestIdQuery = "", shortListIdQuery = "", matches_id = "";
//			if (!requestedIds.equals("")) {
//				requestIdQuery = " and m.member_id not in (" + requestedIds.replaceFirst(",", "") + ")";
//			}
//			if (!shortlistIds.equals("")) {
//				shortListIdQueStry = " and m.member_id not in (" + shortlistIds.replaceFirst(",", "") + ")";
//			}

//			// don't show other ids/info which is in inbox
//			String getFromIds=updateMemberRepository.getDetailsFromOfInboxIDs(id);
//			String getToIds=updateMemberRepository.getDetailsOfToInboxIDs(id);

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

//		********************** begin check member profile is hide or not , gets ids *********************************

			String getMembersHideIDs = getMembersHideIDs();
			String hideMemberIdsQuery = "";
			if (getMembersHideIDs != null && !getMembersHideIDs.equals("")) {
//				hideMemberIdsQuery = hideMemberIdsQuery + " and md.member_id not in ("
//						+ getMembersHideIDs.replaceFirst(",", "") + ") ";
				hideMemberIdsQuery = hideMemberIdsQuery + " and m.member_id not in (" + getMembersHideIDs + ")";
			}

//		********************** begin check member  is block or not , gets ids *********************************

			String getBlockedMemberQuery = "";
			String getMembersBlockIDs = getBlockedIDS("" + id);
			if (getMembersBlockIDs != null && !getMembersBlockIDs.equals("")) {
				getBlockedMemberQuery = getBlockedMemberQuery + " and m.member_id not in (" + getMembersBlockIDs + ") ";
			}

////		******************************begin refine search Filter Data*************************************************************************
//			String whereClause = setWhereClauseForGetAllMember(updateMember);
//
//			String refineWhereClause = "";
//			if (matches_status.equals("REFINE-SEARCH")) {
//				refineWhereClause = setWhereClauseForGetAllMemberByRefineFilter(updateMember);
//			}
//			String likeClause = setLikeClauseForGetAllMember(updateMember);

//			******************************Opposite Gender Search*************************************************************************
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

			Query q = em.createNativeQuery(
					"SELECT count(*) FROM memberdetails as md " + " join member as m on md.member_id=m.member_id"
							+ " join member_education_career as edu on m.member_id=edu.member_id "
							+ " where md.member_id!= :member_id and m.status='ACTIVE' " + matches_id
							+ hideMemberIdsQuery + getBlockedMemberQuery + genderQuery + "");

			System.out.println("count query -  SELECT count(*) FROM memberdetails as md "
					+ " join member as m on md.member_id=m.member_id"
					+ " join member_education_career as edu on m.member_id=edu.member_id "
					+ " where md.member_id!= :member_id and m.status='ACTIVE' " + matches_id + hideMemberIdsQuery
					+ getBlockedMemberQuery + genderQuery + "");
			q.setParameter("member_id", id);
			results = Integer.parseInt(q.getSingleResult().toString());
		} catch (Exception e) {
			e.printStackTrace();
			results = 0;
		}
		return results;
	}

	@Transactional
	public JSONArray getAllMemberByFilter(FilterSearchModel filterSearchModel) {
		JSONArray resultArray = new JSONArray();
		boolean status = false;
		try {

//		******************************Column Name*************************************************************************
			String columnName = "first_name,last_name, m.member_id, height,lifestyles,md.age,"
					+ "md.marital_status as maritalStatus,mother_tounge,gender,profile_photo_id,m.plan_id,m.creation_date,"
//					+ "(select country_name from country where country_id=(select country_id from memberdetails where member_id= :member_id )) as country_name,country_id,"
//					+ "(select state_name from states where state_id=(select state_id from memberdetails where member_id= :member_id)) as state,state_id,"
//					+ "(select city_name from city where city_id=(select city_id from memberdetails where member_id= :member_id)) as city,city_id,"
//					+ "(select religion_name from religion where religion_id=(select religion_id from memberdetails where member_id= :member_id)) as religion,religion_id,"
//					+ "(select cast_name from cast where cast_id=(select cast_id from memberdetails where member_id= :member_id )) as caste,cast_id,"
					+ "edu.highest_qualification as highest_qualification,edu.working_with as working_with,edu.working_as as working_as,edu.annual_income as annual_income";

//		******************************begin refine search Filter Data*************************************************************************
			String whereClause = setWhereClauseForGetAllMemberforAdmin(filterSearchModel);

//		******************************Query*************************************************************************

			Query q = em.createNativeQuery("SELECT " + columnName + "  FROM memberdetails as md "
					+ " join member as m on md.member_id=m.member_id"
					+ " join member_education_career as edu on m.member_id=edu.member_id " + " where m.status='ACTIVE' "
					+ whereClause + " order by m.member_id desc");
			String first_name = "", last_name = "";

			System.out.println("SELECT " + columnName + "  FROM memberdetails as md "
					+ " join member as m on md.member_id=m.member_id"
					+ " join member_education_career as edu on m.member_id=edu.member_id " + " where m.status='ACTIVE' "
					+ whereClause + " order by m.member_id desc");

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
					String myLifeStyles = convertNullToBlank(String.valueOf(obj[++i]));
					String myAge = convertNullToBlank(String.valueOf(obj[++i]));
					String myMaritalStatus = convertNullToBlank(String.valueOf(obj[++i]));
					String myMotherTongue = convertNullToBlank(String.valueOf(obj[++i]));
					String myGender = convertNullToBlank(String.valueOf(obj[++i]));
					String profile_photo_id = convertNullToBlank(String.valueOf(obj[++i]));
					String getProfilePath = "";
					if (!profile_photo_id.equals("") && !profile_photo_id.equals("0")) {
						getProfilePath = uploadImagesService.getMemberProfilePhotoPath(profile_photo_id);
					}
					String plan_id = convertNullToBlank(String.valueOf(obj[++i]));
					String date_time = convertNullToBlank(String.valueOf(obj[++i]));
					System.out.println("plan_id -" + plan_id);
					String plan_name = uploadImagesService.getPLanName(plan_id);
					// String myCountryName = convertNullToBlank(String.valueOf(obj[++i]));
//					String myStateName = convertNullToBlank(String.valueOf(obj[++i]));
//					String myCityName = convertNullToBlank(String.valueOf(obj[++i]));
//					String myReligionName = convertNullToBlank(String.valueOf(obj[++i]));
//					String myCastID = convertNullToBlank(String.valueOf(obj[++i]));
//					String myCastName = convertNullToBlank(String.valueOf(obj[++i]));

					String myQualifications = convertNullToBlank(String.valueOf(obj[++i]));
					String myWorkingWith = convertNullToBlank(String.valueOf(obj[++i]));
					String myWorkingAs = convertNullToBlank(String.valueOf(obj[++i]));
					String myAnnualIncome = convertNullToBlank(String.valueOf(obj[++i]));

					String premium_date = uploadImagesService.getPremiumDate(memberID);
					if (premium_date != null && !premium_date.equals("")) {
						date_time = premium_date;
					}

					json.put("date_time", date_time);
					json.put("first_name", first_name);
					json.put("last_name", last_name);
					json.put("gender", myGender.toUpperCase());
					if (!myAge.equals(""))
						myAge = myAge + " yrs";
					json.put("mage", myAge);
					json.put("maritalStatus", myMaritalStatus);
					json.put("member_id", memberID);
					json.put("block_status", "");
					json.put("profile_photo", getProfilePath);
					json.put("qualifications", myQualifications);
					json.put("working_with", myWorkingWith);
					json.put("myWorkingAs", myWorkingAs);
					json.put("annual_income", myAnnualIncome);
					json.put("plan_name", plan_name);

					JSONArray jsonResultsArray = new JSONArray();
					jsonResultsArray = uploadImagesService.getMemberAppPhotos(memberID);
					json.put("images", jsonResultsArray);
					json.put("images_count", jsonResultsArray.length());
					int premium_status = uploadImagesService.getPremiumMemberStatus(memberID);
					if (premium_status > 0) {
						json.put("premium_status", "1");
					} else {
						json.put("premium_status", "0");
					}
					// check photo settings
					String photo_privacy_setting = uploadImagesService.getPhotoPrivacySettings(memberID);
					if (photo_privacy_setting != null && !photo_privacy_setting.equals("")) {
						json.put("photo_privacy", photo_privacy_setting);
					} else {
						json.put("photo_privacy", "2");
					}
					resultArray.put(json);
					status = true;
				}
			} else {
				resultArray = null;
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

	private String setWhereClauseForGetAllMemberforAdmin(FilterSearchModel filterSearchModel) {
		String whereClause = "";
		try {
			if (filterSearchModel.getMembership() != null && !filterSearchModel.getMembership().equals("")
					&& filterSearchModel.getMembership().equals("premium member")) {
				String getPremiumID = GetMemberShipId();
				if (getPremiumID != null && !getPremiumID.equals("")) {
					whereClause += " and m.member_id in (" + getPremiumID + ")";
				}

				if (filterSearchModel.getFrom_date() != null && !filterSearchModel.getFrom_date().equals("")) {
					String memberID = MemberFromDate(filterSearchModel.getFrom_date(), "premium");
					if (memberID != null && !memberID.equals("")) {
						whereClause += " and m.member_id in (" + memberID + ")";
					}
				}

				if (filterSearchModel.getTo_date() != null && !filterSearchModel.getTo_date().equals("")) {
					String memberID = MemberToDate(filterSearchModel.getTo_date(), "premium");
					if (memberID != null && !memberID.equals("")) {
						whereClause += " and m.member_id in (" + memberID + ")";
					}
				}
//				String planID = getPlanId(filterSearchModel.getMembership());
//				String memberID = MemberShipId(planID);
//				if (memberID != null && !memberID.equals("")) {   
//					whereClause += " and m.memberID in (" + memberID + ")";
//				}
			}

			if (filterSearchModel.getMembership() != null && !filterSearchModel.getMembership().equals("")
					&& filterSearchModel.getMembership().equals("non premium member")) {
				String getPremiumID = GetMemberShipId();
				if (getPremiumID != null && !getPremiumID.equals("")) {
					whereClause += " and m.member_id not in (" + getPremiumID + ")";
				}

				if (filterSearchModel.getFrom_date() != null && !filterSearchModel.getFrom_date().equals("")) {
					String memberID = MemberFromDate(filterSearchModel.getFrom_date(), "non premium");
					if (memberID != null && !memberID.equals("")) {
						whereClause += " and m.member_id in (" + memberID + ")";
					}
				}

				if (filterSearchModel.getTo_date() != null && !filterSearchModel.getTo_date().equals("")) {
					String memberID = MemberToDate(filterSearchModel.getTo_date(), "non premium");
					if (memberID != null && !memberID.equals("")) {
						whereClause += " and m.member_id in (" + memberID + ")";
					}
				}
			}

			if (filterSearchModel.getGender() != null && !filterSearchModel.getGender().equals("")) {
				whereClause += " and gender ='" + filterSearchModel.getGender() + "'";
			}

			/*
			 * if (filterSearchModel.getMember() != null &&
			 * !filterSearchModel.getMember().equals("")) { whereClause += " and md.age <="
			 * + filterSearchModel.getMember();
			 * 
			 * }
			 */

		} catch (Exception e) {

			e.printStackTrace();
		}
		return whereClause;
	}

	private String GetMemberShipId() {
		String result = "";
		try {
//					Query q = em.createNativeQuery("SELECT group_concat(member_id) FROM hide_member where status=0");
			Query q = em.createNativeQuery("SELECT group_concat(member_id) FROM premium_member where deleteflag='N' ");
			result = q.getSingleResult().toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	private String getPlanId(String membership) {
		String result = "";
		try {
//					Query q = em.createNativeQuery("SELECT group_concat(member_id) FROM hide_member where status=0");
			Query q = em.createNativeQuery(
					"SELECT plan_id FROM plans where plan_name = '" + membership + "' AND plan_status='ACTIVE'");
			result = q.getSingleResult().toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}

	private String MemberToDate(String to_date, String status) {
		String result = "";
		try {
			if (status.equals("premium")) {
				result = updateMemberService.getToDatePremiumMemberIDs(to_date);
				if (result == null) {
					result = "";
				}
			}
			if (status.equals("non premium")) {
				result = updateMemberService.getToDateNonPremiumMemberIDs(to_date);
				if (result == null) {
					result = "";
				}
			}
//					Query q = em.createNativeQuery("SELECT group_concat(member_id) FROM hide_member where status=0");
//			Query q = em
//					.createNativeQuery("SELECT group_concat(member_id) FROM premium_member where date(datetime)<=date("
//							+ to_date + ") AND deleteflag='N' ");
//			result = q.getSingleResult().toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	private String MemberFromDate(String from_date, String status) {
		String result = "";
		try {
			if (status.equals("premium")) {
				result = updateMemberService.getFromDatePremiumMemberIDs(from_date);
				if (result == null) {
					result = "";
				}
			}
			if (status.equals("non premium")) {
				result = updateMemberService.getFromDateNonPremiumMemberIDs(from_date);
				if (result == null) {
					result = "";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Transactional
	public HashMap<String, String> getMemberNumber(String member_number, int login_id) {
		HashMap<String, String> map = new HashMap<>();

		String columnName = "member_number,m.member_id, membernative,height,weight,lifestyles,known_languages,education,job,income,hobbies,expectations,first_name,last_name,gender,md.age,"
				+ "contact_number,email_id,profilecreatedby,md.marital_status as maritalStatus,no_of_children,mother_tounge,date_of_birth,"
				+ "health_info,blood_group,gothra,ethnic_corigin,pincode,about_ourself,profile_photo_id,"
				+ "(select country_name from country where country_id=(select country_id from memberdetails where member_id= :id )) as country_name,country_id,"
				+ "sub_caste_name,"
				+ "(select cast_name from cast where cast_id=(select cast_id from memberdetails where member_id= :id )) as caste,cast_id,"
				+ "(select subcast_name from subcasts where subcast_id=(select subcaste_id from memberdetails where member_id= :id)) as subcaste,"
				+ "(select religion_name from religion where religion_id=(select religion_id from memberdetails where member_id= :id)) as religion,religion_id,"
				+ "(select state_name from states where state_id=(select state_id from memberdetails where member_id= :id)) as state,state_id,"
				+ "(select city_name from city where city_id=(select city_id from memberdetails where member_id= :id)) as city,city_id,"
				+ "fd.father_status as father_status,fd.father_company_name as father_company_name,fd.father_designation as father_designation,fd.father_business_name as father_business_name,"
				+ "fd.mother_status as mother_status, fd.mother_company_name as mother_company_name,fd.mother_designation as mother_designation,fd.mother_business_name as mother_business_name,"
				+ "fd.family_location as family_location,fd.native_place as native_place,fd.family_type as family_type,fd.family_values as family_values,fd.family_affluence as family_affluence,"
				+ "fd.married_male as married_male,fd.unmarried_male as unmarried_male,fd.married_female as married_female,fd.unmarried_female as unmarried_female,"
				+ "edu.highest_qualification as highest_qualification,edu.college_attended as college_attended,edu.working_with as working_with,edu.working_as as working_as,edu.employer_name as employer_name,edu.annual_income as annual_income,"
				+ "mh.manglik,mh.nakshatra,mh.time_of_birth,mh.time_status,mh.city_of_birth,mh.hr_dob as horo";
		try {

			String query = "SELECT " + columnName + "  FROM memberdetails as md "
					+ " join member as m on md.member_id=m.member_id"
					+ " join member_family_details as fd on m.member_id=fd.member_id"
					+ " join member_education_career as edu on m.member_id=edu.member_id "
					+ " join member_horoscope as mh on m.member_id=mh.member_id "
					+ " where md.member_id= :id order by m.member_id desc";
//			+ " where md.member_id= :id and m.status='ACTIVE' ";

			// get hide member ids for not showing ids
//			String getMembersHideIDs = getMembersHideIDs();
//
//			if (getMembersHideIDs != null && !getMembersHideIDs.equals("")) {
//				query = query + " and md.member_id not in (" + getMembersHideIDs.replaceFirst(",", "") + ") ";
//			}

			String query2 = "SELECT member_id FROM member where member_number= :member_number";
			Query q2 = em.createNativeQuery(query2);
			q2.setParameter("member_number", member_number);
			int id = Integer.parseInt(q2.getSingleResult().toString());

			String query1 = "SELECT count(*) FROM premium_member where member_id= :id and deleteflag='N'";
			Query q1 = em.createNativeQuery(query1);
			q1.setParameter("id", member_number);
			int premiumStatus = Integer.parseInt(q1.getSingleResult().toString());
			System.out.println("************* premiun status - " + premiumStatus);

			Query q = em.createNativeQuery(query);
//			System.out.println(q);
			String contact_number = "", email_id = "";
			q.setParameter("id", id);
			boolean status = false;
			List<Object[]> results = q.getResultList();
			if (results != null) {
				for (Object[] obj : results) {
					int i = 0;
					String profileID = convertNullToBlank(String.valueOf(obj[i]));
					String thisMemberID = convertNullToBlank(String.valueOf(obj[++i]));

					// first row
					JSONArray jsonResultsArray = new JSONArray();
					jsonResultsArray = uploadImagesService.getMemberAppPhotos("" + thisMemberID);
//					json.put("images",jsonResultsArray);
					map.put("images_count", "" + jsonResultsArray.length());

					int premium_status = uploadImagesService.getPremiumMemberStatus(thisMemberID);
					if (premium_status > 0) {
						map.put("premium_status", "1");
					} else {
						map.put("premium_status", "0");
					}

					int shortlist_status = uploadImagesService.getShortListStatus("" + login_id, "" + id);
					if (shortlist_status > 0) {
						map.put("shortlist_status", "1");
					} else {
						map.put("shortlist_status", "0");
					}

					int login_premium_status = uploadImagesService.getPremiumMemberStatus("" + login_id);
					if (login_premium_status > 0) {
						map.put("my_premium_status", "2");
					} else {
						map.put("my_premium_status", "0");
					}

					// check photo settings
					String photo_privacy_setting = uploadImagesService.getPhotoPrivacySettings(thisMemberID);
					if (photo_privacy_setting != null && !photo_privacy_setting.equals("")) {
						map.put("photo_privacy", photo_privacy_setting);
					} else {
						map.put("photo_privacy", "2");
					}

					// check phone settings
					String phone_privacy_setting = uploadImagesService.getPhonePrivacySettings(thisMemberID);
					if (phone_privacy_setting != null && !phone_privacy_setting.equals("")) {
						map.put("phone_privacy", phone_privacy_setting);
					} else {
						map.put("phone_privacy", "1");
					}

					// check email settings
					String email_privacy_setting = uploadImagesService.getEmailPrivacySettings(thisMemberID);
					if (email_privacy_setting != null && !email_privacy_setting.equals("")) {
						map.put("email_privacy", email_privacy_setting);
					} else {
						map.put("email_privacy", "1");
					}

					// check DOB settings
					String dob_privacy_setting = uploadImagesService.getDOBPrivacySettings(thisMemberID);
					if (dob_privacy_setting != null && !dob_privacy_setting.equals("")) {
						map.put("dob_privacy", dob_privacy_setting);
					} else {
						map.put("dob_privacy", "1");
					}

					// check Annual Income settings
					String annual_income_privacy_setting = uploadImagesService
							.getAnnualIncomePrivacySettings(thisMemberID);
					if (annual_income_privacy_setting != null && !annual_income_privacy_setting.equals("")) {
						map.put("annual_income_privacy", annual_income_privacy_setting);
					} else {
						map.put("annual_income_privacy", "1");
					}

					map.put("profile_id", profileID);
					map.put("member_id", thisMemberID);
					map.put("native", convertNullToBlank(String.valueOf(obj[++i])));
					String height = convertNullToBlank(String.valueOf(obj[++i]));
					map.put("height", height);
					map.put("weight", convertNullToBlank(String.valueOf(obj[++i])));
					map.put("lifestyles", convertNullToBlank(String.valueOf(obj[++i])));
					map.put("known_languages", convertNullToBlank(String.valueOf(obj[++i])));
					map.put("education", convertNullToBlank(String.valueOf(obj[++i])));
					map.put("job", convertNullToBlank(String.valueOf(obj[++i])));
					map.put("income", convertNullToBlank(String.valueOf(obj[++i])));
					map.put("hobbies", convertNullToBlank(String.valueOf(obj[++i])));
					map.put("expectations", convertNullToBlank(String.valueOf(obj[++i])));
					map.put("first_name", convertNullToBlank(String.valueOf(obj[++i])));
					map.put("last_name", convertNullToBlank(String.valueOf(obj[++i])));
					String gender =convertNullToBlank(String.valueOf(obj[++i]));
					map.put("gender",constant.FirstLetterCapital( gender));
					map.put("age", convertNullToBlank(String.valueOf(obj[++i])));

					// second row
					// check for premium member
					contact_number = convertNullToBlank(String.valueOf(obj[++i]));
//					map.put("profile_contact_number", contact_number);
//					contact_number = MembersDetailsAction.getPhonePrivacy(login_premium_status, thisMemberID,
//							contact_number);
					map.put("contact_number", contact_number);

					// check email for premium member
					email_id = convertNullToBlank(String.valueOf(obj[++i]));
//					map.put("profile_email_id", email_id);
//					email_id = MembersDetailsAction.getEmailPrivacy(login_premium_status, thisMemberID, email_id);
					map.put("email_id", email_id);

					map.put("profilecreatedby", convertNullToBlank(String.valueOf(obj[++i])));
					String marital_status = convertNullToBlank(String.valueOf(obj[++i]));
					map.put("marital_status", marital_status);
					map.put("no_of_children", convertNullToBlank(String.valueOf(obj[++i])));
					map.put("mother_tounge", convertNullToBlank(String.valueOf(obj[++i])));

					// dob for premium member
					String dob = convertNullToBlank(String.valueOf(obj[++i]));
//					dob = MembersDetailsAction.getDateOfBirthPrivacy(login_premium_status, thisMemberID, dob);
					map.put("date_of_birth", dob);

					// third row
					map.put("health_info", convertNullToBlank(String.valueOf(obj[++i])));
					map.put("blood_group", convertNullToBlank(String.valueOf(obj[++i])));
					map.put("gothra", convertNullToBlank(String.valueOf(obj[++i])));
					map.put("ethnic_corigin", convertNullToBlank(String.valueOf(obj[++i])));
					map.put("pincode", convertNullToBlank(String.valueOf(obj[++i])));
					map.put("about_ourself", convertNullToBlank(String.valueOf(obj[++i])));

					String profile_photo_id = convertNullToBlank(String.valueOf(obj[++i]));
					String getProfilePath = "";
					if (!profile_photo_id.equals("") && !profile_photo_id.equals("0")) {
						getProfilePath = uploadImagesService.getMemberProfilePhotoPath(profile_photo_id);
					}

					String my_profile_photo_ID = convertNullToBlank(
							uploadImagesService.getMemberProfilePhotoID(login_id + ""));
					String my_profile_photo = "";
					if (!my_profile_photo_ID.equals("") && !my_profile_photo_ID.equals("0")) {
						my_profile_photo = uploadImagesService.getMemberProfilePhotoPath(my_profile_photo_ID);
					}

					map.put("profile_photo", getProfilePath);
					map.put("my_profile_photo", my_profile_photo);
					// forth row
//					String val= convertNullToBlank(String.valueOf(obj[++i]);
					map.put("country_name", convertNullToBlank(String.valueOf(obj[++i])));
					map.put("country_id", convertNullToBlank(String.valueOf(obj[++i])));
					map.put("sub_caste_name", convertNullToBlank(String.valueOf(obj[++i])));

					// fifth,sixth,seven,eight,nine row
					map.put("caste", convertNullToBlank(String.valueOf(obj[++i])));
					map.put("cast_id", convertNullToBlank(String.valueOf(obj[++i])));

					map.put("subcaste", convertNullToBlank(String.valueOf(obj[++i])));
					map.put("religion_name", convertNullToBlank(String.valueOf(obj[++i])));
					map.put("religion_id", convertNullToBlank(String.valueOf(obj[++i])));

					map.put("state", convertNullToBlank(String.valueOf(obj[++i])));
					map.put("state_id", convertNullToBlank(String.valueOf(obj[++i])));

					map.put("city", convertNullToBlank(String.valueOf(obj[++i])));
					map.put("city_id", convertNullToBlank(String.valueOf(obj[++i])));

					// 10th row
					// get family details

					String father_status = convertNullToBlank(String.valueOf(obj[++i]));
					String father_company_name = convertNullToBlank(String.valueOf(obj[++i]));
					String father_designation = convertNullToBlank(String.valueOf(obj[++i]));
					String father_business_name = convertNullToBlank(String.valueOf(obj[++i]));

					// 11th row
					String mother_status = convertNullToBlank(String.valueOf(obj[++i]));
					String mother_company_name = convertNullToBlank(String.valueOf(obj[++i]));
					String mother_designation = convertNullToBlank(String.valueOf(obj[++i]));
					String mother_business_name = convertNullToBlank(String.valueOf(obj[++i]));

					// 12th row
					String family_location = convertNullToBlank(String.valueOf(obj[++i]));
					String native_place = convertNullToBlank(String.valueOf(obj[++i]));
					String family_type = convertNullToBlank(String.valueOf(obj[++i]));
					String familyValues = convertNullToBlank(String.valueOf(obj[++i]));
					String family_affluence = convertNullToBlank(String.valueOf(obj[++i]));

					map.put("father_status", father_status);
					map.put("father_company_name", father_company_name);
					map.put("father_designation", father_designation);
					map.put("father_business_name", father_business_name);
					map.put("mother_status", mother_status);
					map.put("mother_company_name", mother_company_name);
					map.put("mother_designation", mother_designation);
					map.put("mother_business_name", mother_business_name);
					map.put("family_location", family_location);
					map.put("native_place", native_place);
					map.put("family_type", family_type);
					map.put("familyValues", familyValues);
					map.put("family_affluence", family_affluence);

					// 13th row
					map.put("married_male", convertNullToBlank(String.valueOf(obj[++i])));
					map.put("unmarried_male", convertNullToBlank(String.valueOf(obj[++i])));
					map.put("married_female", convertNullToBlank(String.valueOf(obj[++i])));
					map.put("unmarried_female", convertNullToBlank(String.valueOf(obj[++i])));
					String mGender = "";
					if (gender.equals("male") || gender.equals("Male")) {
						mGender = "his";
					}
					if (gender.equals("female") || gender.equals("Female")) {
						mGender = "her";
					}
					String getFamilyDetailsString = getFamilyDetailsInOneStatement(mGender, father_status,
							father_company_name, father_designation, father_business_name, mother_status,
							mother_company_name, mother_designation, mother_business_name, family_location,
							native_place, family_type, familyValues, family_affluence);

					// get edyucation and qualifications details
					map.put("highest_qualification", convertNullToBlank(String.valueOf(obj[++i])));
					map.put("college_attended", convertNullToBlank(String.valueOf(obj[++i])));

					String workingWith = convertNullToBlank(String.valueOf(obj[++i]));
					String workingAs = convertNullToBlank(String.valueOf(obj[++i]));
					String employerName = convertNullToBlank(String.valueOf(obj[++i]));
					String getCareerDetails = getEducationAndCareerDetails(workingWith, workingAs, employerName);

					map.put("working_with", workingWith);
					map.put("working_as", workingAs);
					map.put("employer_name", employerName);

					// check annual income privacy
					String annualIncome = convertNullToBlank(String.valueOf(obj[++i]));
					annualIncome = MembersDetailsAction.getAnnualIncomePrivacy(premiumStatus, thisMemberID,
							annualIncome);
					map.put("annual_income", annualIncome);

					// 14th row
					map.put("manglik", convertNullToBlank(String.valueOf(obj[++i])));
					map.put("nakshatra", convertNullToBlank(String.valueOf(obj[++i])));
					map.put("time_of_birth", convertNullToBlank(String.valueOf(obj[++i])));
					map.put("time_status", convertNullToBlank(String.valueOf(obj[++i])));
					map.put("city_of_birth", convertNullToBlank(String.valueOf(obj[++i])));
					map.put("horoscope_date_of_birth", convertNullToBlank(String.valueOf(obj[++i])));

					map.put("working_details", getCareerDetails);
					map.put("FamilyDetails", getFamilyDetailsString);

					map.put("message", "success");
					map.put("results", "1");

					// get Pending,Rejected,Canceled status
					String request_status = "";
					String block_status = "";
					String getMemberStatus = updateMemberService.getMemberStatus(login_id, thisMemberID);
					if (getMemberStatus != null && !getMemberStatus.equals("")) {
						request_status = getMemberStatus;
					}
					map.put("request_status", request_status);

					String getMemberBlockStatus = updateMemberService.getMemberBlockStatus(login_id, thisMemberID);
					if (getMemberBlockStatus != null && !getMemberBlockStatus.equals("")) {
						block_status = getMemberBlockStatus;
					}
					map.put("block_status", block_status);

					status = true;
				}
			}
			if (login_id != 0) {
				try {
					int statusVisitors = uploadImagesService.getVisitorsStatus(login_id, id);
					if (statusVisitors == 0) {
						Query queryVisitors = em.createNativeQuery(
								"insert into recently_visitors (member_id,visit_to_id,visitor_phone,visitor_email) values (:login_id,:visit_member_ID,:phone,:email)");
						queryVisitors.setParameter("login_id", login_id);
						queryVisitors.setParameter("visit_member_ID", id);
						queryVisitors.setParameter("phone", contact_number);
						queryVisitors.setParameter("email", email_id);
						int visit_status = queryVisitors.executeUpdate();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (status == false) {
				map = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return map;
	}

}
