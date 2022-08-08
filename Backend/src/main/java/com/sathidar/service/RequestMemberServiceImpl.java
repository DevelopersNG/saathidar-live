package com.sathidar.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sathidar.EntityMangerFactory.GetNameByIDMangerFactory;
import com.sathidar.EntityMangerFactory.RequestBlockMemberEntityManagerFactory;
import com.sathidar.exception.BadRequestException;
import com.sathidar.model.RequestMemberModel;
import com.sathidar.repository.RequestMemberRepository;
import com.sathidar.util.MembersDetailsAction;

@Service
public class RequestMemberServiceImpl implements RequestMemberService {

	@Autowired
	private RequestMemberRepository requestMemberRepository;

	@Autowired
	private RequestBlockMemberEntityManagerFactory requestBlockMemberEntityManagerFactory;

	@Autowired
	private GetNameByIDMangerFactory getNameByIDMangerFactory;

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private EmailService mailSender;

	@Autowired
	private UploadImagesService uploadImagesService;

	@Override
	public JSONArray SendRequestToMember(RequestMemberModel requestMemberModel) {

		Object requestMemberObject = null;
		JSONArray resultArray = new JSONArray();

		try {
			JSONObject json = new JSONObject();
			int request_from_id = Integer.parseInt(requestMemberModel.getRequest_from_id());
			int request_to_id = Integer.parseInt(requestMemberModel.getRequest_to_id());
			String request_status = requestMemberModel.getRequest_status().trim();

			int status = requestMemberRepository.getSentRequestedMember(request_from_id, request_to_id);
			if (status > 0) {
				requestMemberObject = requestMemberRepository.requestAcceptedAndRejected(request_from_id, request_to_id,
						request_status);
			} else {
				requestMemberObject = requestMemberRepository.sendRequestToMember(request_from_id, request_to_id,
						request_status);
			}

			MembersDetailsAction membersDetailsAction = new MembersDetailsAction();
			// send email and sms to other member
			List lst = new ArrayList();
			lst = getDetailsMemberByMember_id(Integer.parseInt(requestMemberModel.getRequest_from_id()));
//			String emailId_to=requestMemberRepository.getEmailId(requestMemberModel.getRequest_to_id());

			String fullName = "", emailId_to = "";
			List<Object[]> results = requestMemberRepository
					.getUserNameEmailId(Integer.parseInt(requestMemberModel.getRequest_to_id()));
			if (results != null) {
				for (Object[] obj : results) {
					int i = 0;
					fullName = convertNullToBlank(String.valueOf(obj[i]))
							+ convertNullToBlank(String.valueOf(obj[++i]));
					emailId_to = convertNullToBlank(String.valueOf(obj[++i]));
				}
			}

			System.out.println("email id - " + emailId_to + " , fullName -" + fullName);

			String response = "";
			if (lst != null) {
				response = sentInvitationsByEmail(lst, emailId_to, fullName,
						Integer.parseInt(requestMemberModel.getRequest_from_id()));
			}
			System.out.println("response  -  " + response);

			if (requestMemberObject == null) {
				json.put("message", "request not send..");
				json.put("results", "0");
//				throw new BadRequestException("request not send..");
			} else {
				json.put("message", "request are send..");
				json.put("results", "1");
				// send the email/sms to other member
			}

			resultArray.put(json);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultArray;
	}

//	public String getEmailId(Integer request_to_id) {
//		String response="";
//		try {  
//			//member_number
//			Query q = em.createNativeQuery("SELECT email_id FROM member where member_id= :member_id");
//			q.setParameter("member_id", ""+request_to_id);
//			response = q.getSingleResult().toString();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return response;
//	}

	private String sentInvitationsByEmail(List lst, String emailId_to, String fullName, int from_id) {
		String respons = "";
		try {
			String email_id = convertNullToBlank(lst.get(0).toString());
			String first_name = convertNullToBlank(lst.get(1).toString());
			String last_name = convertNullToBlank(lst.get(2).toString());
			String member_id = convertNullToBlank(lst.get(3).toString());
			String height = convertNullToBlank(lst.get(4).toString());
			String age = convertNullToBlank(lst.get(5).toString());
			String marital_status = convertNullToBlank(lst.get(6).toString());
			String education = convertNullToBlank(lst.get(7).toString());
			String profession = convertNullToBlank(lst.get(8).toString());

			String religions = convertNullToBlank(lst.get(9).toString());
			String city = convertNullToBlank(lst.get(10).toString());
			String mother_tongue = convertNullToBlank(lst.get(11).toString());

//			************************** new start *******************************	

			String email_body = "<head>\r\n" + "    <meta charset=\"UTF-8\">\r\n"
					+ "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n"
					+ "    <title>Saathidar</title>\r\n" + "    <style>\r\n"
					+ "        .container{height: 150px; width: 400px;border: #742041 1px solid ;margin-top: 5px;}\r\n"
					+ "        table {font-family: arial, sans-serif;border-collapse: collapse;width: 100%;}\r\n"
					+ "td, th {font-size: 12px;text-align: left;padding: 8px;\r\n"
					+ "}img{height: 150px;}.bg{background-color: #742041;}button{background-color: #742041;color: #ffff;margin: 5px;}\r\n"
					+ "    </style>\r\n" + "</head>\r\n" + "<body style=\"width: 400px;\">\r\n"
					+ "    <div style=\"background-color: #742041;\"><img style=\"width:300px ;\" src=\"www.saathidaar.com/assets/images/logo_eng.png\" alt=\"\"></div>\r\n"
					+ " <div class=\"image\">\r\n"
					+ "   <h4 style=\"text-align: center;color: #742041;font-size: 20px;\">Invitation to become your Saathidar!!!\r\n"
					+ "</h4>\r\n" + "<p style=\"float: left;\"><strong>Hi " + fullName + ",</strong></p><br>\r\n"
					+ "<p><strong>" + first_name + " " + last_name
					+ " </strong>has invited you to connect. Let\'s Respond</p>\r\n"
					+ "   <table style=\"width: 100%;border: #742041 1px solid;\" class=\"table\">\r\n"
					+ "    <thead>\r\n";

			if (!age.equals("")) {
				email_body = email_body + "      <tr >\r\n" + "        <th  scope=\"col\">Age  </th>\r\n"
						+ "        <th  scope=\"col\">: " + age + " </th>\r\n" + "      </tr>\r\n";
			}

			if (!height.equals("")) {
				email_body = email_body + "      <tr >\r\n" + "        <th  scope=\"col\"> Height </th>\r\n"
						+ "        <th  scope=\"col\">: " + height + "</th>\r\n" + "      </tr>\r\n";
			}

			email_body = email_body + "      <tr>\r\n" + "        <th scope=\"col\">Marital Status </th>\r\n"
					+ "        <th scope=\"col\">: " + marital_status + "</th>\r\n" + "      </tr>\r\n";

			if (!education.equals("")) {
				email_body = email_body +

						"      <tr >\r\n" + "        <th  scope=\"col\">Education </th>\r\n"
						+ "        <th  scope=\"col\">: " + education + "</th>\r\n" + "      </tr>\r\n";
			}

			if (!profession.equals("")) {
				email_body = email_body + "      <tr>\r\n" + "        <th scope=\"col\">Profession </th>\r\n"
						+ "        <th scope=\"col\">: " + profession + "</th>\r\n" + "      </tr>\r\n";
			}

			if (!religions.equals("")) {
				email_body = email_body + "      <tr>\r\n" + "        <th scope=\"col\">Religion / Community </th>\r\n"
						+ "        <th scope=\"col\">: " + religions + "</th>\r\n" + "      </tr>\r\n";
			}

			if (!mother_tongue.equals("")) {
				email_body = email_body + "      <tr>\r\n" + "        <th scope=\"col\">Mother Tongue </th>\r\n"
						+ "        <th scope=\"col\">: " + mother_tongue + "</th>\r\n" + "      </tr>\r\n";
			}

			if (!city.equals("")) {
				email_body = email_body + "      <tr>\r\n" + "        <th scope=\"col\">Location </th>\r\n"
						+ "        <th scope=\"col\">: " + city + "</th>\r\n" + "      </tr>\r\n";
			}

			email_body = email_body + "    </thead>\r\n" + "  </table>\r\n"
					+ "  <a href=\"http://localhost:4200/members/profile/" + from_id
					+ "\"  style=\"text-align: center;color: #742041;font-size: 20px;\">View Full Profile</a>\r\n"
					+ " </div>\r\n" + " <div class=\"details\"></div>\r\n" + "  </body>";

			mailSender.send(emailId_to, "Saathidaar Invitations", email_body);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return respons;
	}

	@Override
	public JSONArray RequestAcceptAndRejected(RequestMemberModel requestMemberModel) {
		Object requestMemberObject = null;
		JSONArray resultArray = new JSONArray();
		JSONObject json = new JSONObject();
		try {
			
			int request_from_id = Integer.parseInt(requestMemberModel.getRequest_from_id());
			int request_to_id = Integer.parseInt(requestMemberModel.getRequest_to_id());
			String request_status = requestMemberModel.getRequest_status().trim();

//			if(request_status.equals("Canceled")) {
//				requestMemberObject = requestMemberRepository.requestCanceled(request_from_id, request_to_id);
//				request_status="Canceled";
//			}else {
			requestMemberObject = requestMemberRepository.requestAcceptedAndRejected(request_from_id, request_to_id,
					request_status);
//			}

			// for mail sending
			if (request_status.equals("Accepted")) {
				List lst = new ArrayList();
				lst = getDetailsMemberByMember_id(Integer.parseInt(requestMemberModel.getRequest_to_id()));

				String fullName = "", emailId_to = "";
				List<Object[]> results = requestMemberRepository
						.getUserNameEmailId(Integer.parseInt(requestMemberModel.getRequest_from_id()));
				if (results != null) {
					for (Object[] obj : results) {
						int i = 0;
						fullName = convertNullToBlank(String.valueOf(obj[i])) + " "+ convertNullToBlank(String.valueOf(obj[++i]));
						emailId_to = convertNullToBlank(String.valueOf(obj[++i]));
					}
				}

//				getBackupDatabase();

				sendEmailToUser(lst, fullName, emailId_to, request_from_id);
			}

			if (requestMemberObject != null) {
				json.put("message", "request are " + request_status + "..");
				json.put("results", "1");
			}
			if (requestMemberObject == null) {
				json.put("message", "request are not " + request_status + "..");
				json.put("results", "0");
			}
			
			resultArray.put(json);
		} catch (Exception e) {
			json.put("results", "0");
			e.printStackTrace();
		}
		return resultArray;
	}

	private void getBackupDatabase() {
//		String dbName = “dbName”;
//		String dbUser = “dbUser”;
//		String dbPass = “dbPass”;
//
//		/***********************************************************/
//		// Execute Shell Command
//		/***********************************************************/
//		String executeCmd = “”;
//		executeCmd = “mysqldump -u “+dbUser+” -p”+dbPass+” “+dbName+” -r backup.sql”;
//		}
//		Process runtimeProcess =Runtime.getRuntime().exec(executeCmd);
//		int processComplete = runtimeProcess.waitFor();
//		if(processComplete == 0){
//
//		out.println(“Backup taken successfully”);
//
//		} else {
//
//		out.println(“Could not take mysql backup”);

	}

	private void sendEmailToUser(List lst, String fullName, String emailId_to, int request_to_id) {
		try {
			try {
				String email_id = convertNullToBlank(lst.get(0).toString());
				String first_name = convertNullToBlank(lst.get(1).toString());
				String last_name = convertNullToBlank(lst.get(2).toString());
				String member_id = convertNullToBlank(lst.get(3).toString());
				String height = convertNullToBlank(lst.get(4).toString());
				String age = convertNullToBlank(lst.get(5).toString());
				String marital_status = convertNullToBlank(lst.get(6).toString());
				String education = convertNullToBlank(lst.get(7).toString());
				String profession = convertNullToBlank(lst.get(8).toString());

				String religions = convertNullToBlank(lst.get(9).toString());
				String city = convertNullToBlank(lst.get(10).toString());
				String mother_tongue = convertNullToBlank(lst.get(11).toString());

//			************************** new start *******************************	

				String email_body = "<head>\r\n" + "    <meta charset=\"UTF-8\">\r\n"
						+ "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n"
						+ "    <title>Saathidar</title>\r\n" + "    <style>\r\n"
						+ "        .container{height: 150px; width: 400px;border: #742041 1px solid ;margin-top: 5px;}\r\n"
						+ "        table {font-family: arial, sans-serif;border-collapse: collapse;width: 100%;}\r\n"
						+ "td, th {font-size: 12px;text-align: left;padding: 8px;\r\n"
						+ "}img{height: 150px;}.bg{background-color: #742041;}button{background-color: #742041;color: #ffff;margin: 5px;}\r\n"
						+ "    </style>\r\n" + "</head>\r\n" + "<body style=\"width: 400px;\">\r\n"
						+ "    <div style=\"background-color: #742041;\"><img style=\"width:300px ;\" src=\"www.saathidaar.com/assets/images/logo_eng.png\" alt=\"\"></div>\r\n"
						+ " <div class=\"image\">\r\n"
						+ "   <h4 style=\"text-align: center;color: #742041;font-size: 20px;\">It\'s a Match!!!\r\n"
						+ "\r\n" + "</h4>\r\n" + "<p style=\"float: left;\"><strong>Hi " + fullName
						+ ",</strong></p><br>\r\n" + "<p><strong>" + first_name + " " + last_name
						+ " </strong>has accepted your request to connect. Let\'s take this forward</p>\r\n"
						+ "   <table style=\"width: 100%;border: #742041 1px solid;\" class=\"table\">\r\n"
						+ "    <thead>\r\n";

				if (!age.equals("")) {
					email_body = email_body + "      <tr >\r\n" + "        <th  scope=\"col\">Age  </th>\r\n"
							+ "        <th  scope=\"col\">: " + age + " </th>\r\n" + "      </tr>\r\n";
				}

				if (!height.equals("")) {
					email_body = email_body + "      <tr >\r\n" + "        <th  scope=\"col\"> Height </th>\r\n"
							+ "        <th  scope=\"col\">: " + height + "</th>\r\n" + "      </tr>\r\n";
				}

				email_body = email_body + "      <tr>\r\n" + "        <th scope=\"col\">Marital Status </th>\r\n"
						+ "        <th scope=\"col\">: " + marital_status + "</th>\r\n" + "      </tr>\r\n";

				if (!education.equals("")) {
					email_body = email_body +

							"      <tr >\r\n" + "        <th  scope=\"col\">Education </th>\r\n"
							+ "        <th  scope=\"col\">: " + education + "</th>\r\n" + "      </tr>\r\n";
				}

				if (!profession.equals("")) {
					email_body = email_body + "      <tr>\r\n" + "        <th scope=\"col\">Profession </th>\r\n"
							+ "        <th scope=\"col\">: " + profession + "</th>\r\n" + "      </tr>\r\n";
				}

				if (!religions.equals("")) {
					email_body = email_body + "      <tr>\r\n"
							+ "        <th scope=\"col\">Religion / Community </th>\r\n"
							+ "        <th scope=\"col\">: " + religions + "</th>\r\n" + "      </tr>\r\n";
				}

				if (!mother_tongue.equals("")) {
					email_body = email_body + "      <tr>\r\n" + "        <th scope=\"col\">Mother Tongue </th>\r\n"
							+ "        <th scope=\"col\">: " + mother_tongue + "</th>\r\n" + "      </tr>\r\n";
				}

				if (!city.equals("")) {
					email_body = email_body + "      <tr>\r\n" + "        <th scope=\"col\">Location </th>\r\n"
							+ "        <th scope=\"col\">: " + city + "</th>\r\n" + "      </tr>\r\n";
				}

				email_body = email_body + "    </thead>\r\n" + "  </table>\r\n"
						+ "  <a href=\"http://localhost:4200/members/profile/" + request_to_id
						+ "\"  style=\"text-align: center;color: #742041;font-size: 20px;\">View Full Profile</a>\r\n"
						+ " </div>\r\n" + " <div class=\"details\"></div>\r\n" + "  </body>";

				System.out.println("************* email id- " + emailId_to);
				mailSender.send(emailId_to, "Accept Request- Saathidaar", email_body);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public int blockMember(RequestMemberModel requestMemberModel) {
		int status = 0;
		try {
			JSONObject json = new JSONObject();
			int request_from_id = Integer.parseInt(requestMemberModel.getRequest_from_id());
			int request_to_id = Integer.parseInt(requestMemberModel.getRequest_to_id());
			int block_by_id = Integer.parseInt(requestMemberModel.getBlock_by_id());
			String block_status = requestMemberModel.getBlock_status().trim();

//			List<Object[]> objResults = requestBlockMemberEntityManagerFactory
//					.getFromRequestAndToRequest(request_from_id, request_to_id);
//			if (objResults != null) {
//				for (Object[] obj : objResults) {
//					int from_id = (int) obj[0];
//					int to_id = (int) obj[1];
//					
//					if(block_status.equals("Block")) {
//						requestMemberObject = requestMemberRepository.requestBlockToMember(from_id, to_id, block_by_id,
//								block_status);
//					}else {
//						requestMemberObject = requestMemberRepository.requestUnBlockToMember(from_id, to_id, block_by_id);
//					}
//				}
//			} else {
//				resultArray=null;
//			}

			int count = requestMemberRepository.getBlockMembers(request_from_id, request_to_id);
			if (count > 0) {
				if (block_status.equals("Block")) {
					status = requestMemberRepository.requestBlockToMember(request_from_id, request_to_id, block_by_id,
							block_status);
				} else {
					status = requestMemberRepository.requestUnBlockToMember(request_from_id, request_to_id,
							block_by_id);
				}
			} else {
				status = requestMemberRepository.insertBlockMembers(request_from_id, request_to_id, block_by_id,
						block_status);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return status;
	}

	@Override
	public JSONArray GetSentRequestDetails(String member_id) {

//		******************************Column Name******************************************************************
		String columnName = getCommonColumnForSearch();
		System.out.println("member_id- *******************************************" + member_id);
//		******************************Block ids************************************************************************
		String getBlockedIDS = getBlockedIDS(member_id);
		String blockQuery = "";
		System.out.println(" block member ids - " + getBlockedIDS);
		if (getBlockedIDS != null && !getBlockedIDS.equals("")) {
			blockQuery = " and md.member_id not in (" + getBlockedIDS + ")";
		}

		JSONArray resultArray = new JSONArray();
		try {
			String getSentResuestedIDS = getSentsRequestedIDS(member_id);

			String sentResuestedQuery = "";
			if (getSentResuestedIDS != null && !getSentResuestedIDS.equals("")) {
				sentResuestedQuery = " and md.member_id in (" + getSentResuestedIDS + ")";

				Query q = em.createNativeQuery("SELECT " + columnName + "  FROM memberdetails as md "
						+ " join member as m on md.member_id=m.member_id"
						+ " join member_education_career as mec on m.member_id=mec.member_id "
						+ " where md.member_id!= :member_id " + sentResuestedQuery + blockQuery);

				System.out.println("SELECT " + columnName + "  FROM memberdetails as md "
						+ " join member as m on md.member_id=m.member_id"
						+ " join member_education_career as mec on m.member_id=mec.member_id "
						+ " where md.member_id!= :member_id " + sentResuestedQuery + blockQuery);

				q.setParameter("member_id", member_id);

				List<Object[]> results = q.getResultList();
				if (results != null) {
					for (Object[] obj : results) {
						JSONObject json = new JSONObject();
						int count = 0;
						json = getCommonJsonOutout(obj, member_id, "Sent");
						resultArray.put(json);
					}
				} else {
					resultArray = null;
				}
			} else {
				resultArray = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultArray;
	}

	private String getSentsRequestedIDS(String member_id) {
		String ids = "";
		try {
			Query query = em.createNativeQuery(
					"SELECT group_concat(request_to_id) FROM member_request where  request_from_id= :request_from_id");
			query.setParameter("request_from_id", member_id);
			ids = query.getSingleResult().toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ids;
	}

	private String getCommonColumnForSearch() {
		String columnName = "m.member_id as member_id,height,lifestyles,known_languages,first_name,last_name,"
				+ "gender,md.age,contact_number,profilecreatedby,md.marital_status,mother_tounge,"
				+ "date_of_birth,mec.annual_income,country_id,cast_id,subcaste_id,religion_id,state_id,city_id,profile_photo_id";
		return columnName;
	}

	private JSONObject getCommonJsonOutout(Object[] obj, String current_Member_ID, String Status) {
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

			String gender = convertNullToBlank(String.valueOf(obj[++i]));

			json.put("gender", gender);
			json.put("mage", convertNullToBlank(String.valueOf(obj[++i])));
			json.put("contact_number", convertNullToBlank(String.valueOf(obj[++i])));
			json.put("profilecreatedby", convertNullToBlank(String.valueOf(obj[++i])));
			json.put("maritalStatus", convertNullToBlank(String.valueOf(obj[++i])));
			json.put("mother_tounge", convertNullToBlank(String.valueOf(obj[++i])));
			json.put("date_of_birth", convertNullToBlank(String.valueOf(obj[++i])));
			json.put("income", convertNullToBlank(String.valueOf(obj[++i])));
			json.put("country", convertNullToBlank(
					getNameByIDMangerFactory.getCountryNameByID(convertNullToBlank(String.valueOf(obj[++i])))));
			json.put("caste", convertNullToBlank(
					getNameByIDMangerFactory.getCasteNameByID(convertNullToBlank(String.valueOf(obj[++i])))));
			json.put("subcaste", convertNullToBlank(
					getNameByIDMangerFactory.getSubCasteNameByID(convertNullToBlank(String.valueOf(obj[++i])))));
			json.put("religion", convertNullToBlank(
					getNameByIDMangerFactory.getReligionNameByID(convertNullToBlank(String.valueOf(obj[++i])))));
			json.put("state", convertNullToBlank(
					getNameByIDMangerFactory.getStateNameByID(convertNullToBlank(String.valueOf(obj[++i])))));
			json.put("city", convertNullToBlank(
					getNameByIDMangerFactory.getCityNameByID(convertNullToBlank(String.valueOf(obj[++i])))));

			String profile_photo_id = convertNullToBlank(String.valueOf(obj[++i]));
			String getProfilePath = "";
			if (!profile_photo_id.equals("") && !profile_photo_id.equals("0")) {
				getProfilePath = uploadImagesService.getMemberProfilePhotoPath(profile_photo_id);
			}
			json.put("profile_photo", getProfilePath);

			int shortlist_status=uploadImagesService.getShortListStatus(current_Member_ID,memberID);
			if(shortlist_status>0) {
				json.put("shortlist_status","1");
			}else {
				json.put("shortlist_status","0");
			}
			
			int premium_status = uploadImagesService.getPremiumMemberStatus(memberID);
			if (premium_status > 0) {
				json.put("premium_status", "1");
			} else {
				json.put("premium_status", "0");
			}

			int login_premium_status = uploadImagesService.getPremiumMemberStatus(""+current_Member_ID);
			if(login_premium_status>0) {
				json.put("my_premium_status","2");
			}else {
				json.put("my_premium_status","0");
			}
			
			// check photo settings
			String photo_privacy_setting = uploadImagesService.getPhotoPrivacySettings(memberID);
			if(photo_privacy_setting!=null && !photo_privacy_setting.equals("")) {
				json.put("photo_privacy",photo_privacy_setting);
			}else {
				json.put("photo_privacy","1");
			}
			
			JSONArray jsonResultsArray = new JSONArray();
			jsonResultsArray = uploadImagesService.getMemberAppPhotos("" + memberID);
			json.put("images", jsonResultsArray);
			json.put("images_count",jsonResultsArray.length());

			String genderMessage = "";
			if (gender != null && !gender.equals("")) {
				if (gender.equals("male")) {
					genderMessage = "him";
				}
				if (gender.equals("female")) {
					genderMessage = "her";
				}
			}

			List<Object[]> results = null;
			Query query = em.createNativeQuery(
					"SELECT request_status, DATE_FORMAT(creation_date,'%d %M %Y') AS showdate FROM member_request where  request_from_id= :request_from_id and request_to_id= :request_to_id");
			String messgae = "";
			if (Status.equals("Sent")) {
//				from id=this current member id 
//						to_id=memberID
				query.setParameter("request_from_id", current_Member_ID);
				query.setParameter("request_to_id", memberID);
				results = query.getResultList();
				messgae = "You sent " + genderMessage + " request on ";
			}
			if (Status.equals("Invitations")) {
				query.setParameter("request_from_id", memberID);
				query.setParameter("request_to_id", current_Member_ID);
				results = query.getResultList();
				messgae = " has invited you to connect on ";
			}

			if (results != null) {
				for (Object[] objResults : results) {
					int j = 0;
					String requestStatus = convertNullToBlank(String.valueOf(objResults[j]));
					String requestDate = convertNullToBlank(String.valueOf(objResults[++j]));
					json.put("request_status", requestStatus);
					json.put("request_status_date", requestDate);
					json.put("request_message", messgae + requestDate);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return json;
	}

	public String convertNullToBlank(String value) {
		if (value != null && !value.equals("")) {
			return value;
		}
		return "";
	}

	@Override
	public JSONArray GetInvitationsDetails(String member_id) {

//		******************************Column Name*************************************************************************
		String columnName = getCommonColumnForSearch();

//		******************************Block ids************************************************************************
		String getBlockedIDS = getBlockedIDS(member_id);
		String blockQuery = "";
		System.out.println(" block member ids - " + getBlockedIDS);
		if (getBlockedIDS != null && !getBlockedIDS.equals("")) {
			blockQuery = " and md.member_id not in (" + getBlockedIDS + ")";
		}

		JSONArray resultArray = null;
		try {
			String getInvitationsIDS = getInvitationsIDS(member_id);
			String initationsQuery = "";

			if (getInvitationsIDS != null && !getInvitationsIDS.equals("")) {
				initationsQuery = " and md.member_id in (" + getInvitationsIDS + ")";

				Query q = em.createNativeQuery("SELECT " + columnName + "  FROM memberdetails as md "
						+ " join member as m on md.member_id=m.member_id"
						+ " join member_education_career as mec on m.member_id=mec.member_id "
						+ " where md.member_id!= :member_id" + initationsQuery + blockQuery);

				System.out.println(" invitations -  SELECT " + columnName + "  FROM memberdetails as md "
						+ " join member as m on md.member_id=m.member_id"
						+ " join member_education_career as mec on m.member_id=mec.member_id "
						+ " where  md.member_id!= :member_id" + initationsQuery + blockQuery);

				q.setParameter("member_id", member_id);
				List<Object[]> results = q.getResultList();
				if (results != null) {
					resultArray = new JSONArray();
					for (Object[] obj : results) {
						JSONObject json = new JSONObject();
						json = getCommonJsonOutout(obj, member_id, "Invitations");
						resultArray.put(json);
					}
				} else {
					resultArray = null;
				}
			} else {
				resultArray = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultArray;
	}

	private String getInvitationsIDS(String member_id) {
		String ids = "";
		try {
			Query query = em.createNativeQuery(
					"SELECT group_concat(request_from_id) FROM member_request where  request_to_id= :member_to_id and request_status= :member_request_status");
			query.setParameter("member_to_id", member_id);
			query.setParameter("member_request_status", "Pending");
			ids = query.getSingleResult().toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ids;
	}

	@Override
	public JSONArray GetMyAcceptedDetails(String member_id) {
		JSONArray resultArray = new JSONArray();
		try {
//			******************************Column Name*************************************************************************
			String columnName = getCommonColumnForSearch();
//			******************************Query*************************************************************************
			String getMyAcceptedIDS = getMyAcceptedIDS(member_id);

			String initationsQuery = "";

//			******************************Block ids************************************************************************
			String getBlockedIDS = getBlockedIDS(member_id);
			String blockQuery = "";
			System.out.println(" block member ids - " + getBlockedIDS);
			if (getBlockedIDS != null && !getBlockedIDS.equals("")) {
				blockQuery = " and md.member_id not in (" + getBlockedIDS + ")";
			}

			String status = "";
			String queryString = "SELECT " + columnName + "  FROM memberdetails as md "
					+ " join member as m on md.member_id=m.member_id"
					+ " join member_education_career as mec on m.member_id=mec.member_id ";

			if (getMyAcceptedIDS != null && !getMyAcceptedIDS.equals("")) {
				initationsQuery = " where md.member_id!=:member_id and md.member_id in (" + getMyAcceptedIDS + ")";
				queryString = queryString + initationsQuery + getBlockedIDS;

				Query q = em.createNativeQuery(queryString);
				q.setParameter("member_id", member_id);
				List<Object[]> results = q.getResultList();
				if (results != null) {
					for (Object[] obj : results) {
						JSONObject json = new JSONObject();
						json = getCommonJsonOutout(obj, member_id, "Accepted");
						resultArray.put(json);
					}
				} else {
					resultArray = null;
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
		return ids;
	}

	private String getOtherAcceptedIDS(String member_id) {
		String ids = "";
		try {
			// for show other member informations also
			String otherProfilesIds = "";
			Query query = em.createNativeQuery(
					"SELECT group_concat(request_to_id ) FROM member_request where  request_from_id= :request_from_id and request_status= :member_request_status order by creation_date desc");
			query.setParameter("request_from_id", member_id);
			query.setParameter("member_request_status", "Accepted");
			try {
				List results = query.getResultList();
				if (results.isEmpty() || results == null)
					System.out.println("blank");
				else if (results.size() == 1)
					ids = results.get(0).toString();
			} catch (Exception e) {
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		System.out.println("ids - " + ids);
		return ids;
	}

	private String getMyAcceptedIDS(String member_id) {
		String ids = "";
		try {
			Query query = em.createNativeQuery(
					"SELECT group_concat(request_from_id) FROM member_request where  request_to_id= :member_to_id and request_status= :member_request_status order by creation_date desc");
			query.setParameter("member_to_id", member_id);
			query.setParameter("member_request_status", "Accepted");
			try {
				List results = query.getResultList();
				if (results.isEmpty() || results == null)
					System.out.println("blank");
				else if (results.size() == 1)
					ids = results.get(0).toString();
			} catch (Exception e) {
			}

			System.out.println("ids - " + ids);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return ids;
	}

	@Override
	public JSONArray GetRejectedDetails(String member_id) {
//		******************************Column Name*************************************************************************
		String columnName = getCommonColumnForSearch();

//		******************************Block ids************************************************************************
		String getBlockedIDS = getBlockedIDS(member_id);
		String blockQuery = "";
		System.out.println(" block member ids - " + getBlockedIDS);
		if (getBlockedIDS != null && !getBlockedIDS.equals("")) {
			blockQuery = " and md.member_id not in (" + getBlockedIDS + ")";
		}

		JSONArray resultArray = new JSONArray();
		try {
			String getAcceptedIDS = getRejectedIDS(member_id);
			String initationsQuery = "";

			if (getAcceptedIDS != null && !getAcceptedIDS.equals("")) {
				initationsQuery = " and md.member_id in (" + getAcceptedIDS + ")";

				Query q = em.createNativeQuery("SELECT " + columnName + "  FROM memberdetails as md "
						+ " join member as m on md.member_id=m.member_id"
						+ " join member_education_career as mec on m.member_id=mec.member_id "
						+ " where  md.member_id!= :member_id" + initationsQuery + getBlockedIDS);

				System.out.println(" invitations -  SELECT " + columnName + "  FROM memberdetails as md "
						+ " join member as m on md.member_id=m.member_id"
						+ " join member_education_career as mec on m.member_id=mec.member_id "
						+ " where md.member_id!= :member_id" + initationsQuery + getBlockedIDS);

				q.setParameter("member_id", member_id);

				List<Object[]> results = q.getResultList();
				if (results != null) {
					for (Object[] obj : results) {
						JSONObject json = new JSONObject();
						json = getCommonJsonOutout(obj, member_id, "Rejected");
						resultArray.put(json);
					}
				} else {
					resultArray = null;
				}
			} else {
				resultArray = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultArray;
	}

	private String getRejectedIDS(String member_id) {
		String ids = "";

		try {
			Query query = em.createNativeQuery(
					"SELECT group_concat(request_from_id) FROM member_request where  request_to_id= :member_to_id and request_status= :member_request_status");
			query.setParameter("member_to_id", member_id);
			query.setParameter("member_request_status", "Rejected");
			ids = query.getSingleResult().toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ids;
	}

	public JSONArray GetRejectedAndCanceledDetails(String member_id) {
		JSONArray resultArray = new JSONArray();
		JSONArray new_json_array = new JSONArray();
		boolean flag=false;
//		******************************Column Name*************************************************************************
		String columnName = getCommonColumnForSearch();
//		******************************Block ids************************************************************************
		
		String getBlockedIDS = getBlockedIDS(member_id);
		String blockQuery = "";
		System.out.println(" block member ids - " + getBlockedIDS);
		if (getBlockedIDS != null && !getBlockedIDS.equals("")) {
			blockQuery = " and md.member_id not in (" + getBlockedIDS + ")";
		}
		
		try {
			Query query = em.createNativeQuery(
					"SELECT request_to_id,request_status,creation_date FROM member_request where request_from_id= :request_from_id and  (request_status= :member_rejected_status or request_status= :member_canceled_status) and (block_status is null  or block_status='')");
			query.setParameter("request_from_id", member_id);
			query.setParameter("member_rejected_status", "Rejected");
			query.setParameter("member_canceled_status", "Canceled");

			List<Object[]> results = query.getResultList();
			JSONObject json = new JSONObject();
			if (results != null) {
				for (Object[] obj : results) {
					int j = 0;
//					String request_from_id = convertNullToBlank(String.valueOf(obj[j]));
					String request_to_id = convertNullToBlank(String.valueOf(obj[j]));
					String request_status = convertNullToBlank(String.valueOf(obj[++j]));
					String creation_date = convertNullToBlank(String.valueOf(obj[++j]));

					String queryString = "SELECT " + columnName + "  FROM memberdetails as md "
							+ " join member as m on md.member_id=m.member_id"
							+ " join member_education_career as mec on m.member_id=mec.member_id "
							+ " where md.member_id= :member_id "+ blockQuery;
					Query qFrom = em.createNativeQuery(queryString);
					qFrom.setParameter("member_id", request_to_id);
					List<Object[]> memberFrom = qFrom.getResultList();
					if (memberFrom != null) {
						for (Object[] memberObj : memberFrom) {
							// your request is decline
							json = getCommonDeleteJsonOutout(memberObj, member_id, request_status, "from", creation_date);
							resultArray.put(json);
							flag=true;
						}
					}
				}
			}

			Query queryTo = em.createNativeQuery(
					"SELECT request_from_id,request_status,creation_date FROM member_request where request_to_id= :request_to_id and  (request_status= :member_rejected_status or request_status= :member_canceled_status) and (block_status is null  or block_status='')");
			queryTo.setParameter("request_to_id", member_id);
			queryTo.setParameter("member_rejected_status", "Rejected");
			queryTo.setParameter("member_canceled_status", "Canceled");

			List<Object[]> resultsTO = queryTo.getResultList();
			if (resultsTO != null) {
				for (Object[] obj : resultsTO) {
					int j = 0;
//					String request_from_id = convertNullToBlank(String.valueOf(obj[j]));
					String request_to_id = convertNullToBlank(String.valueOf(obj[j]));
					String request_status = convertNullToBlank(String.valueOf(obj[++j]));
					String creation_date = convertNullToBlank(String.valueOf(obj[++j]));
					
					String queryString = "SELECT " + columnName + "  FROM memberdetails as md "
							+ " join member as m on md.member_id=m.member_id"
							+ " join member_education_career as mec on m.member_id=mec.member_id "
							+ " where md.member_id= :member_id";
					Query qTo = em.createNativeQuery(queryString);
					qTo.setParameter("member_id", request_to_id);
					List<Object[]> memberTo = qTo.getResultList();
					if (memberTo != null) {
						for (Object[] memberObj : memberTo) {
							// you declient request
							json = getCommonDeleteJsonOutout(memberObj, member_id, request_status, "to", creation_date);
							resultArray.put(json);
							flag=true;
						}
					}
				}
			}
			if(!flag){
				resultArray=null;
			}else {
				// Extract the JSONObjects
				JSONObject[] objects = new JSONObject[resultArray.length()];
				for (int i = 0; i < objects.length; i++) {
				    objects[i] = resultArray.getJSONObject(i);
				}
				
				// Sort the array of JSONObjects
				Arrays.sort(
				    objects,
				    (JSONObject o1, JSONObject o2) ->
				        ((String)o1.keys().next()).compareTo((String)o2.keys().next())
				);
				
				for (JSONObject o : objects) {
					new_json_array.put(o);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new_json_array;
	}

	public JSONArray GetAcceptedDetails(String member_id) {
		JSONArray resultArray = new JSONArray();
		JSONArray new_json_array = new JSONArray();
		boolean flag=false;
//		******************************Column Name*************************************************************************
		String columnName = getCommonColumnForSearch();
//		******************************Block ids************************************************************************
		
		String getBlockedIDS = getBlockedIDS(member_id);
		String blockQuery = "";
		System.out.println(" block member ids - " + getBlockedIDS);
		if (getBlockedIDS != null && !getBlockedIDS.equals("")) {
			blockQuery = " and md.member_id not in (" + getBlockedIDS + ")";
		}
		
		try {
			Query query = em.createNativeQuery(
					"SELECT request_to_id,request_status,creation_date FROM member_request where request_from_id= :request_from_id and request_status= :member_accepted_status and (block_status is null  or block_status='')");
			query.setParameter("request_from_id", member_id);
			query.setParameter("member_accepted_status", "Accepted");

			List<Object[]> results = query.getResultList();
			JSONObject json = new JSONObject();
			if (results != null) {
				for (Object[] obj : results) {
					int j = 0;
//					String request_from_id = convertNullToBlank(String.valueOf(obj[j]));
					String request_to_id = convertNullToBlank(String.valueOf(obj[j]));
					String request_status = convertNullToBlank(String.valueOf(obj[++j]));
					String creation_date = convertNullToBlank(String.valueOf(obj[++j]));

					String queryString = "SELECT " + columnName + "  FROM memberdetails as md "
							+ " join member as m on md.member_id=m.member_id"
							+ " join member_education_career as mec on m.member_id=mec.member_id "
							+ " where md.member_id= :member_id";
					Query qFrom = em.createNativeQuery(queryString);
					qFrom.setParameter("member_id", request_to_id);
					List<Object[]> memberFrom = qFrom.getResultList();
					if (memberFrom != null) {
						for (Object[] memberObj : memberFrom) {
							// your request is decline
							json = getCommonDeleteJsonOutout(memberObj, member_id, request_status, "", creation_date);
							resultArray.put(json);
							flag=true;
						}
					}
				}
			}

			Query queryTo = em.createNativeQuery(
					"SELECT request_from_id,request_status,creation_date FROM member_request where request_to_id= :request_to_id and request_status= :member_accepted_status and (block_status is null  or block_status='')");
			queryTo.setParameter("request_to_id", member_id);
			queryTo.setParameter("member_accepted_status", "Accepted");
			
			List<Object[]> resultsTO = queryTo.getResultList();
			if (resultsTO != null) {
				for (Object[] obj : resultsTO) {
					int j = 0;
//					String request_from_id = convertNullToBlank(String.valueOf(obj[j]));
					String request_to_id = convertNullToBlank(String.valueOf(obj[j]));
					String request_status = convertNullToBlank(String.valueOf(obj[++j]));
					String creation_date = convertNullToBlank(String.valueOf(obj[++j]));
					
					String queryString = "SELECT " + columnName + "  FROM memberdetails as md "
							+ " join member as m on md.member_id=m.member_id"
							+ " join member_education_career as mec on m.member_id=mec.member_id "
							+ " where md.member_id= :member_id";
					Query qTo = em.createNativeQuery(queryString);
					qTo.setParameter("member_id", request_to_id);
					List<Object[]> memberTo = qTo.getResultList();
					if (memberTo != null) {
						for (Object[] memberObj : memberTo) {
							// you declient request
							json = getCommonDeleteJsonOutout(memberObj, member_id, request_status, "", creation_date);
							resultArray.put(json);
							flag=true;
						}
					}
				}
			}
			if(flag==false){
				new_json_array=null;
			}else {
				// Extract the JSONObjects
				JSONObject[] objects = new JSONObject[resultArray.length()];
				for (int i = 0; i < objects.length; i++) {
				    objects[i] = resultArray.getJSONObject(i);
				}
				
				// Sort the array of JSONObjects
				Arrays.sort(
				    objects,
				    (JSONObject o1, JSONObject o2) ->
				        ((String)o1.keys().next()).compareTo((String)o2.keys().next())
				);
				
				for (JSONObject o : objects) {
					new_json_array.put(o);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new_json_array;
	}
	
	private JSONObject getCommonDeleteJsonOutout(Object[] obj, String current_Member_ID, String Status,
			String status_from, String creation_date) {
		JSONObject json = new JSONObject();
		try {
			int i = 0;
			String memberID = convertNullToBlank(String.valueOf(obj[i]));
			json.put("member_id", memberID);
			json.put("creation_date", creation_date);
			json.put("height", convertNullToBlank(String.valueOf(obj[++i])));
			json.put("lifestyles", convertNullToBlank(String.valueOf(obj[++i])));
			json.put("known_languages", convertNullToBlank(String.valueOf(obj[++i])));
			json.put("first_name", convertNullToBlank(String.valueOf(obj[++i])));
			json.put("last_name", convertNullToBlank(String.valueOf(obj[++i])));

			String gender = convertNullToBlank(String.valueOf(obj[++i]));

			json.put("gender", gender);
			json.put("mage", convertNullToBlank(String.valueOf(obj[++i])));
			json.put("contact_number", convertNullToBlank(String.valueOf(obj[++i])));
			json.put("profilecreatedby", convertNullToBlank(String.valueOf(obj[++i])));
			json.put("maritalStatus", convertNullToBlank(String.valueOf(obj[++i])));
			json.put("mother_tounge", convertNullToBlank(String.valueOf(obj[++i])));
			json.put("date_of_birth", convertNullToBlank(String.valueOf(obj[++i])));
			json.put("income", convertNullToBlank(String.valueOf(obj[++i])));
			json.put("country", convertNullToBlank(
					getNameByIDMangerFactory.getCountryNameByID(convertNullToBlank(String.valueOf(obj[++i])))));
			json.put("caste", convertNullToBlank(
					getNameByIDMangerFactory.getCasteNameByID(convertNullToBlank(String.valueOf(obj[++i])))));
			json.put("subcaste", convertNullToBlank(
					getNameByIDMangerFactory.getSubCasteNameByID(convertNullToBlank(String.valueOf(obj[++i])))));
			json.put("religion", convertNullToBlank(
					getNameByIDMangerFactory.getReligionNameByID(convertNullToBlank(String.valueOf(obj[++i])))));
			json.put("state", convertNullToBlank(
					getNameByIDMangerFactory.getStateNameByID(convertNullToBlank(String.valueOf(obj[++i])))));
			json.put("city", convertNullToBlank(
					getNameByIDMangerFactory.getCityNameByID(convertNullToBlank(String.valueOf(obj[++i])))));

			String profile_photo_id = convertNullToBlank(String.valueOf(obj[++i]));
			String getProfilePath = "";
			if (!profile_photo_id.equals("") && !profile_photo_id.equals("0")) {
				getProfilePath = uploadImagesService.getMemberProfilePhotoPath(profile_photo_id);
			}
			json.put("profile_photo", getProfilePath);

			int premium_status = uploadImagesService.getPremiumMemberStatus(memberID);
			if (premium_status > 0) {
				json.put("premium_status", "1");
			} else {
				json.put("premium_status", "0");
			}

			int login_premium_status = uploadImagesService.getPremiumMemberStatus(""+current_Member_ID);
			if(login_premium_status>0) {
				json.put("my_premium_status","2");
			}else {
				json.put("my_premium_status","0");
			}
			
			// check photo settings
			String photo_privacy_setting = uploadImagesService.getPhotoPrivacySettings(memberID);
			if(photo_privacy_setting!=null && !photo_privacy_setting.equals("")) {
				json.put("photo_privacy",photo_privacy_setting);
			}else {
				json.put("photo_privacy","1");
			}
			
			JSONArray jsonResultsArray = new JSONArray();
			jsonResultsArray = uploadImagesService.getMemberAppPhotos("" + memberID);
			json.put("images", jsonResultsArray);
			json.put("images_count",jsonResultsArray.length());
			
			String genderFromMessage = "", genderToMessage = "";
			if (gender != null && !gender.equals("")) {
				if (gender.equals("male")) {
					genderFromMessage = "he";
					genderToMessage = "him";
				}
				if (gender.equals("female")) {
					genderFromMessage = "she";
					genderToMessage = "her";
				}
			}

			String messgae = "";
			if (Status.equals("Rejected")) {
				if (status_from.equals("from")) {
					messgae = genderFromMessage + " declined your request";
				}
				if (status_from.equals("to")) {
					messgae = " you declined " + genderToMessage + " request";
				}
			}
			if (Status.equals("Canceled")) {
				if (status_from.equals("from")) {
					messgae = genderFromMessage + " cancelled your request";
				}
				if (status_from.equals("to")) {
					messgae = " you cancelled " + genderToMessage + " request";
				}
			}
			json.put("request_message", messgae);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return json;
	}

	@Transactional
	private List getDetailsMemberByMember_id(Integer request_from_id) {
		List lstAdd = new ArrayList();
		try {
			String columnName = "email_id,first_name,last_name, m.member_id, height,md.age,md.marital_status as maritalStatus,edu.highest_qualification as highest_qualification,edu.working_with as working_with";

			List<Object[]> results = requestMemberRepository.getMemberDeyailsForMail("" + request_from_id);
			if (results != null) {
				for (Object[] obj : results) {
					int i = 0;
					String email_id = convertNullToBlank(String.valueOf(obj[i]));
					String first_name = convertNullToBlank(String.valueOf(obj[++i]));
					String last_name = convertNullToBlank(String.valueOf(obj[++i]));
					String member_id = convertNullToBlank(String.valueOf(obj[++i]));
					String height = convertNullToBlank(String.valueOf(obj[++i]));
					String age = convertNullToBlank(String.valueOf(obj[++i]));
					String marital_status = convertNullToBlank(String.valueOf(obj[++i]));
					String education = convertNullToBlank(String.valueOf(obj[++i]));
					String profession = convertNullToBlank(String.valueOf(obj[++i]));
					String religions = convertNullToBlank(String.valueOf(obj[++i]));
					String caste = convertNullToBlank(String.valueOf(obj[++i]));
					String city = convertNullToBlank(String.valueOf(obj[++i]));
					String mother_tongue = convertNullToBlank(String.valueOf(obj[++i]));

					lstAdd.add(email_id);
					lstAdd.add(first_name);
					lstAdd.add(last_name);
					lstAdd.add(member_id);
					lstAdd.add(height);
					lstAdd.add(age);
					lstAdd.add(marital_status);
					lstAdd.add(education);
					lstAdd.add(profession);

					religions = religions;
					if (!caste.equals("")) {
						religions = religions + " / " + caste;
					}

					lstAdd.add(religions);
					lstAdd.add(city);
					lstAdd.add(mother_tongue);

				}
			}
			return lstAdd;
		} catch (Exception e) {
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return lstAdd;
	}

	@Override
	public JSONArray getBlockMember(String member_id) {
//		******************************Column Name*************************************************************************
		String columnName = getCommonColumnForSearch();
//		******************************Query*************************************************************************
		JSONArray resultArray = new JSONArray();
		try {
			String getBlockedIDS = getBlockedIDS(member_id);
			String initationsQuery = "";
			System.out.println(" block member ids - " + getBlockedIDS);
			if (getBlockedIDS != null && !getBlockedIDS.equals("")) {
				initationsQuery = " and md.member_id in (" + getBlockedIDS + ")";

				Query q = em.createNativeQuery("SELECT " + columnName + "  FROM memberdetails as md "
						+ " join member as m on md.member_id=m.member_id"
						+ " join member_education_career as mec on m.member_id=mec.member_id "
						+ " where m.status='ACTIVE' and md.member_id!= :member_id" + initationsQuery);

				System.out.println(" blocks -  SELECT " + columnName + "  FROM memberdetails as md "
						+ " join member as m on md.member_id=m.member_id"
						+ " join member_education_career as mec on m.member_id=mec.member_id "
						+ " where m.status='ACTIVE' and md.member_id!= :member_id" + initationsQuery);

				q.setParameter("member_id", member_id);

				List<Object[]> results = q.getResultList();
				if (results != null) {
					for (Object[] obj : results) {
						JSONObject json = new JSONObject();
						json = getCommonJsonOutout(obj, member_id, "Block");
						resultArray.put(json);
					}
				} else {
					resultArray = null;
				}
			} else {
				resultArray = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultArray;
	}

	@Override
	public JSONArray GetOtherAcceptedDetails(String member_id) {
		JSONArray resultArray = new JSONArray();
		try {
//			******************************Column Name*************************************************************************
			String columnName = getCommonColumnForSearch();
//			******************************Query*************************************************************************
			String getOtherAcceptedIDS = getOtherAcceptedIDS(member_id);
			String initationsQuery = "";

//			******************************Block ids************************************************************************
			String getBlockedIDS = getBlockedIDS(member_id);
			String blockQuery = "";
			System.out.println(" block member ids - " + getBlockedIDS);
			if (getBlockedIDS != null && !getBlockedIDS.equals("")) {
				blockQuery = " and md.member_id not in (" + getBlockedIDS + ")";
			}

			String status = "";
			String queryString = "SELECT " + columnName + "  FROM memberdetails as md "
					+ " join member as m on md.member_id=m.member_id"
					+ " join member_education_career as mec on m.member_id=mec.member_id ";

			if (getOtherAcceptedIDS != null && !getOtherAcceptedIDS.equals("")) {
				initationsQuery = " where md.member_id in (" + getOtherAcceptedIDS + ")";
				queryString = queryString + initationsQuery + getBlockedIDS;

				Query q = em.createNativeQuery(queryString);
				List<Object[]> results = q.getResultList();
				if (results != null) {
					for (Object[] obj : results) {
						JSONObject json = new JSONObject();
						json = getCommonJsonOutout(obj, member_id, "Accepted");
						resultArray.put(json);
					}
				} else {
					resultArray = null;
				}
			} else {
				resultArray = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultArray;
	}

}
