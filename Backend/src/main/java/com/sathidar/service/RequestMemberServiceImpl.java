package com.sathidar.service;

import java.util.ArrayList;
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

	@Override
	public JSONArray SendRequestToMember(RequestMemberModel requestMemberModel) {

		Object requestMemberObject = null;
		JSONArray resultArray = new JSONArray();

		try {
			JSONObject json = new JSONObject();
			int request_from_id = requestMemberModel.getRequest_from_id();
			int request_to_id = requestMemberModel.getRequest_to_id();
			String request_status = requestMemberModel.getRequest_status().trim();
			requestMemberObject = requestMemberRepository.sendRequestToMember(request_from_id, request_to_id,
					request_status);
			
			MembersDetailsAction membersDetailsAction=new MembersDetailsAction();
			// send email and sms to other member
			List lst=new ArrayList();
			lst= getDetailsMemberByMember_id(requestMemberModel.getRequest_from_id());
//			String emailId_to=requestMemberRepository.getEmailId(requestMemberModel.getRequest_to_id());
			
			String fullName="",emailId_to="";
			List<Object[]> results = requestMemberRepository.getUserNameEmailId(requestMemberModel.getRequest_to_id());
			if (results != null) {
				for (Object[] obj : results) {
					int i=0;
					fullName= convertNullToBlank(String.valueOf(obj[i])) + convertNullToBlank(String.valueOf(obj[++i]));
					emailId_to= convertNullToBlank(String.valueOf(obj[++i]));
				}
			}
			
			System.out.println("email id - "+emailId_to + " , fullName -"+ fullName);
			
			String response="";
			if(lst!=null) {
				 response=sentInvitationsByEmail(lst,emailId_to,fullName,requestMemberModel.getRequest_from_id());
			}
			System.out.println("response  -  "+ response);
			
			if (requestMemberObject == null) {
				json.put("message", "request not send..");
				json.put("results", "0");
//				throw new BadRequestException("request not send..");
			}else {
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
	
	private String sentInvitationsByEmail(List lst, String emailId_to,String fullName,int from_id) {
		String respons="";
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
				
				String email_body="<head>\r\n" + 
						"    <meta charset=\"UTF-8\">\r\n" + 
						"    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n" + 
						"    <title>Saathidar</title>\r\n" + 
						"    <style>\r\n" + 
						"        .container{height: 150px; width: 400px;border: #742041 1px solid ;margin-top: 5px;}\r\n" + 
						"        table {font-family: arial, sans-serif;border-collapse: collapse;width: 100%;}\r\n" + 
						"td, th {font-size: 12px;text-align: left;padding: 8px;\r\n" + 
						"}img{height: 150px;}.bg{background-color: #742041;}button{background-color: #742041;color: #ffff;margin: 5px;}\r\n" + 
						"    </style>\r\n" + 
						"</head>\r\n" + 
						"<body style=\"width: 400px;\">\r\n" + 
						"    <div style=\"background-color: #742041;\"><img style=\"width:300px ;\" src=\"www.saathidaar.com/assets/images/logo_eng.png\" alt=\"\"></div>\r\n" + 
						" <div class=\"image\">\r\n" + 
						"   <h4 style=\"text-align: center;color: #742041;font-size: 20px;\">Invitation to become your Saathidar!!!\r\n" + 
						"</h4>\r\n" + 
						"<p style=\"float: left;\"><strong>Hi "+fullName+",</strong></p><br>\r\n" + 
						"<p><strong>"+first_name+" "+last_name+" </strong>has invited you to connect. Let’s Respond</p>\r\n" + 
						"   <table style=\"width: 100%;border: #742041 1px solid;\" class=\"table\">\r\n" + 
						"    <thead>\r\n";
				
				if(!age.equals("")) {
					email_body=email_body +				
						"      <tr >\r\n" + 
						"        <th  scope=\"col\">Age  </th>\r\n" + 
						"        <th  scope=\"col\">: "+age+" </th>\r\n" + 
						"      </tr>\r\n"; }
				
				
				if(!height.equals("")) {
					email_body=email_body +
						"      <tr >\r\n" + 
						"        <th  scope=\"col\"> Height </th>\r\n" + 
						"        <th  scope=\"col\">: "+height+"</th>\r\n" + 
						"      </tr>\r\n";}
				
				
				email_body=email_body +"      <tr>\r\n" + 
						"        <th scope=\"col\">Marital Status </th>\r\n" + 
						"        <th scope=\"col\">: "+marital_status+"</th>\r\n" + 
						"      </tr>\r\n";
				
				
				if(!education.equals("")){
					email_body=email_body +
				
						"      <tr >\r\n" + 
						"        <th  scope=\"col\">Education </th>\r\n" + 
						"        <th  scope=\"col\">: "+education+"</th>\r\n" + 
						"      </tr>\r\n";  }
				
				
				if(!profession.equals("")){
					email_body=email_body +
						"      <tr>\r\n" + 
						"        <th scope=\"col\">Profession </th>\r\n" + 
						"        <th scope=\"col\">: "+profession+"</th>\r\n" + 
						"      </tr>\r\n";}
				
				
				if(!religions.equals("")){
					email_body=email_body +
						"      <tr>\r\n" + 
						"        <th scope=\"col\">Religion / Community </th>\r\n" + 
						"        <th scope=\"col\">: "+religions+"</th>\r\n" + 
						"      </tr>\r\n";} 
				
				if(!mother_tongue.equals("")){
					email_body=email_body+
						"      <tr>\r\n" + 
						"        <th scope=\"col\">Mother Tongue </th>\r\n" + 
						"        <th scope=\"col\">: "+mother_tongue+"</th>\r\n" + 
						"      </tr>\r\n";}
				
				if(!city.equals("")){
					email_body=email_body+
						"      <tr>\r\n" + 
						"        <th scope=\"col\">Location </th>\r\n" + 
						"        <th scope=\"col\">: "+city+"</th>\r\n" + 
						"      </tr>\r\n" ;}
				
				
				email_body=email_body	+"    </thead>\r\n" + 
						"  </table>\r\n" + 
						"  <a href=\"http://localhost:4200/members/profile/"+from_id+"\"  style=\"text-align: center;color: #742041;font-size: 20px;\">View Full Profile</a>\r\n" + 
						" </div>\r\n" + 
						" <div class=\"details\"></div>\r\n" + 
						"  </body>";
			
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

		try {
			JSONObject json = new JSONObject();
			int request_from_id = requestMemberModel.getRequest_from_id();
			int request_to_id = requestMemberModel.getRequest_to_id();
			String request_status = requestMemberModel.getRequest_status().trim();
			requestMemberObject = requestMemberRepository.requestAcceptedAndRejected(request_from_id, request_to_id,
					request_status);
			json.put("message", "request are " + request_status + "..");

			
			System.out.println("******** request_to_id " + request_to_id);
			
			if(request_status.equals("Accepted")) {
				List lst=new ArrayList();
				lst= getDetailsMemberByMember_id(requestMemberModel.getRequest_from_id());

				String fullName="",emailId_to="";
				List<Object[]> results = requestMemberRepository.getUserNameEmailId(requestMemberModel.getRequest_to_id());
				if (results != null) {
					for (Object[] obj : results) {
						int i=0;
						fullName= convertNullToBlank(String.valueOf(obj[i])) + convertNullToBlank(String.valueOf(obj[++i]));
						emailId_to= convertNullToBlank(String.valueOf(obj[++i]));
					}
				}
				
				getBackupDatabase();
				
				sendEmailToUser(lst,fullName,emailId_to,request_to_id);
			}
			
			
			if (requestMemberObject == null) {
				throw new BadRequestException("something wrong request not working..");
			}

			resultArray.put(json);
		} catch (Exception e) {
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

	private void sendEmailToUser(List lst, String fullName, String emailId_to,int request_to_id) {
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
				
				String email_body="<head>\r\n" + 
						"    <meta charset=\"UTF-8\">\r\n" + 
						"    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n" + 
						"    <title>Saathidar</title>\r\n" + 
						"    <style>\r\n" + 
						"        .container{height: 150px; width: 400px;border: #742041 1px solid ;margin-top: 5px;}\r\n" + 
						"        table {font-family: arial, sans-serif;border-collapse: collapse;width: 100%;}\r\n" + 
						"td, th {font-size: 12px;text-align: left;padding: 8px;\r\n" + 
						"}img{height: 150px;}.bg{background-color: #742041;}button{background-color: #742041;color: #ffff;margin: 5px;}\r\n" + 
						"    </style>\r\n" + 
						"</head>\r\n" + 
						"<body style=\"width: 400px;\">\r\n" + 
						"    <div style=\"background-color: #742041;\"><img style=\"width:300px ;\" src=\"www.saathidaar.com/assets/images/logo_eng.png\" alt=\"\"></div>\r\n" + 
						" <div class=\"image\">\r\n" + 
						"   <h4 style=\"text-align: center;color: #742041;font-size: 20px;\">It\'s a Match!!!\r\n" + 
						"\r\n" + 
						"</h4>\r\n" + 
						"<p style=\"float: left;\"><strong>Hi "+fullName+",</strong></p><br>\r\n" + 
						"<p><strong>"+first_name+" "+last_name+" </strong>has accepted your request to connect. Lets take this forward</p>\r\n" + 
						"   <table style=\"width: 100%;border: #742041 1px solid;\" class=\"table\">\r\n" + 
						"    <thead>\r\n";
				
				if(!age.equals("")) {
					email_body=email_body +				
						"      <tr >\r\n" + 
						"        <th  scope=\"col\">Age  </th>\r\n" + 
						"        <th  scope=\"col\">: "+age+" </th>\r\n" + 
						"      </tr>\r\n"; }
				
				
				if(!height.equals("")) {
					email_body=email_body +
						"      <tr >\r\n" + 
						"        <th  scope=\"col\"> Height </th>\r\n" + 
						"        <th  scope=\"col\">: "+height+"</th>\r\n" + 
						"      </tr>\r\n";}
				
				
				email_body=email_body +"      <tr>\r\n" + 
						"        <th scope=\"col\">Marital Status </th>\r\n" + 
						"        <th scope=\"col\">: "+marital_status+"</th>\r\n" + 
						"      </tr>\r\n";
				
				
				if(!education.equals("")){
					email_body=email_body +
				
						"      <tr >\r\n" + 
						"        <th  scope=\"col\">Education </th>\r\n" + 
						"        <th  scope=\"col\">: "+education+"</th>\r\n" + 
						"      </tr>\r\n";  }
				
				
				if(!profession.equals("")){
					email_body=email_body +
						"      <tr>\r\n" + 
						"        <th scope=\"col\">Profession </th>\r\n" + 
						"        <th scope=\"col\">: "+profession+"</th>\r\n" + 
						"      </tr>\r\n";}
				
				
				if(!religions.equals("")){
					email_body=email_body +
						"      <tr>\r\n" + 
						"        <th scope=\"col\">Religion / Community </th>\r\n" + 
						"        <th scope=\"col\">: "+religions+"</th>\r\n" + 
						"      </tr>\r\n";} 
				
				if(!mother_tongue.equals("")){
					email_body=email_body+
						"      <tr>\r\n" + 
						"        <th scope=\"col\">Mother Tongue </th>\r\n" + 
						"        <th scope=\"col\">: "+mother_tongue+"</th>\r\n" + 
						"      </tr>\r\n";}
				
				if(!city.equals("")){
					email_body=email_body+
						"      <tr>\r\n" + 
						"        <th scope=\"col\">Location </th>\r\n" + 
						"        <th scope=\"col\">: "+city+"</th>\r\n" + 
						"      </tr>\r\n" ;}
				
				
				email_body=email_body	+"    </thead>\r\n" + 
						"  </table>\r\n" + 
						"  <a href=\"http://localhost:4200/members/profile/"+request_to_id+"\"  style=\"text-align: center;color: #742041;font-size: 20px;\">View Full Profile</a>\r\n" + 
						" </div>\r\n" + 
						" <div class=\"details\"></div>\r\n" + 
						"  </body>";
			
				System.out.println("************* email id- "+ emailId_to);
							mailSender.send(emailId_to, "Accept Request- Saathidaar", email_body);
		} catch (Exception e) {
			e.printStackTrace();
		}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public JSONArray blockMember(RequestMemberModel requestMemberModel) {
		Object requestMemberObject = null;
		JSONArray resultArray = new JSONArray();

		try {
			JSONObject json = new JSONObject();
			int request_from_id = requestMemberModel.getRequest_from_id();
			int request_to_id = requestMemberModel.getRequest_to_id();
			int block_by_id = requestMemberModel.getBlock_by_id();
			String block_status = requestMemberModel.getBlock_status().trim();

			List<Object[]> objResults = requestBlockMemberEntityManagerFactory
					.getFromRequestAndToRequest(request_from_id, request_to_id);
			if (objResults != null) {
				for (Object[] obj : objResults) {
					int from_id = (int) obj[0];
					int to_id = (int) obj[1];

					requestMemberObject = requestMemberRepository.requestBlockToMember(from_id, to_id, block_by_id,
							block_status);
					if (requestMemberObject != null)
						json.put("message", "request is " + block_status + "..");
				}
			} else {
				throw new BadRequestException("something wrong block/unblock request not working..");
			}

			if (requestMemberObject == null) {
				throw new BadRequestException("something wrong block/unblock request not working..");
			}

			resultArray.put(json);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultArray;
	}

	@Override
	public JSONArray GetSentRequestDetails(String member_id) {
		
//		******************************Column Name******************************************************************
		 String columnName=getCommonColumnForSearch();
		 System.out.println("member_id- *******************************************" +member_id);
//		******************************Query************************************************************************
		 String getSentResuestedIDS=getSentsRequestedIDS(member_id);
			JSONArray resultArray = new JSONArray();
			String sentResuestedQuery="";
		 if(getSentResuestedIDS!=null && !getSentResuestedIDS.equals("")) {
			 sentResuestedQuery=" and md.member_id in ("+getSentResuestedIDS+")";
			 
		Query q = em.createNativeQuery("SELECT " + columnName + "  FROM memberdetails as md "
				+ " join member as m on md.member_id=m.member_id"
				+ " join member_education_career as mec on m.member_id=mec.member_id "
				+ " where m.status='ACTIVE' and md.member_id!= :member_id "+sentResuestedQuery);
		
		System.out.println("SELECT " + columnName + "  FROM memberdetails as md "
				+ " join member as m on md.member_id=m.member_id"
				+ " join member_education_career as mec on m.member_id=mec.member_id "
				+ " where m.status='ACTIVE' and md.member_id!= :member_id "+sentResuestedQuery);
		
		q.setParameter("member_id", member_id);

	
		List<Object[]> results = q.getResultList();
		if (results != null) {
			for (Object[] obj : results) {
				JSONObject json = new JSONObject();
				int count=0;
				json=getCommonJsonOutout(obj,member_id,"Sent");		
				resultArray.put(json);
			}
		}
	}
		return resultArray;
	}

	private String getSentsRequestedIDS(String member_id) {
		String ids="";
		try {
					Query query = em.createNativeQuery("SELECT group_concat(request_to_id) FROM member_request where  request_from_id= :request_from_id");
					query.setParameter("request_from_id", member_id);
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

	private JSONObject getCommonJsonOutout(Object[] obj,String current_Member_ID,String Status) {
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
			json.put("city", getNameByIDMangerFactory.getCityNameByID(convertNullToBlank(String.valueOf(obj[++i]))));
		
//			 ,,Accepted,Rejected
			List<Object[]> results=null;
			Query query = em.createNativeQuery("SELECT request_status, DATE_FORMAT(creation_date,'%d %M %Y') AS showdate FROM member_request where  request_from_id= :request_from_id and request_to_id= :request_to_id");
			if(Status.equals("Sent")){
//				from id=this current member id 
//						to_id=memberID
				query.setParameter("request_from_id", current_Member_ID);
				query.setParameter("request_to_id", memberID);
				results = query.getResultList();
			}
			if(Status.equals("Invitations")){
				query.setParameter("request_from_id",memberID );
				query.setParameter("request_to_id",current_Member_ID);
				results = query.getResultList();
			}
			
			
			if (results != null) {
				for (Object[] objResults : results) {  
					int j = 0;
					String requestStatus=convertNullToBlank(String.valueOf(objResults[j]));
					String requestDate=convertNullToBlank(String.valueOf(objResults[++j]));
					json.put("request_status", requestStatus);
					json.put("request_status_date", requestDate);
					json.put("request_message", "Your Request is "+ requestStatus + " on "+ requestDate);
				}
			}
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
	public JSONArray GetInvitationsDetails(String member_id) {
		
//		******************************Column Name*************************************************************************
		 String columnName=getCommonColumnForSearch();
//		******************************Query*************************************************************************
		 String getInvitationsIDS=getInvitationsIDS(member_id);
		 String initationsQuery="";
			JSONArray resultArray = new JSONArray();
		 if( getInvitationsIDS!=null && !getInvitationsIDS.equals("")) {
			 initationsQuery=" and md.member_id in ("+getInvitationsIDS+")";
			 
			 Query q = em.createNativeQuery("SELECT " + columnName + "  FROM memberdetails as md "
						+ " join member as m on md.member_id=m.member_id"
						+ " join member_education_career as mec on m.member_id=mec.member_id "
						+ " where m.status='ACTIVE' and md.member_id!= :member_id"+initationsQuery);
				
				System.out.println(" invitations -  SELECT " + columnName + "  FROM memberdetails as md "
						+ " join member as m on md.member_id=m.member_id"
						+ " join member_education_career as mec on m.member_id=mec.member_id "
						+ " where m.status='ACTIVE' and md.member_id!= :member_id"+initationsQuery);
				
				q.setParameter("member_id", member_id);
			
			
				List<Object[]> results = q.getResultList();
				if (results != null) {
					for (Object[] obj : results) {  
						JSONObject json = new JSONObject();
						json=getCommonJsonOutout(obj,member_id,"Invitations");		
						resultArray.put(json);
					}
				}
		 }
		
		return resultArray;
	}

	private String getInvitationsIDS(String member_id) {
		String ids="";

		try {
					Query query = em.createNativeQuery("SELECT group_concat(request_from_id) FROM member_request where  request_to_id= :member_to_id and request_status= :member_request_status");
					query.setParameter("member_to_id", member_id);
					query.setParameter("member_request_status", "Pending");
					ids = query.getSingleResult().toString();
		} catch (Exception e) {
		e.printStackTrace();
		}
				return ids;
	}

	@Override
	public JSONArray GetAcceptedDetails(String member_id) {
		JSONArray resultArray = new JSONArray();
		try {
//			******************************Column Name*************************************************************************
			 String columnName=getCommonColumnForSearch();
//			******************************Query*************************************************************************
			 String getAcceptedIDS=getAcceptedIDS(member_id);
			 String initationsQuery="";
			
			 if( getAcceptedIDS!=null && !getAcceptedIDS.equals("")) {
				 initationsQuery=" and md.member_id in ("+getAcceptedIDS+")";
				 
				 Query q = em.createNativeQuery("SELECT " + columnName + "  FROM memberdetails as md "
							+ " join member as m on md.member_id=m.member_id"
							+ " join member_education_career as mec on m.member_id=mec.member_id "
							+ " where m.status='ACTIVE' and md.member_id!= :member_id"+initationsQuery);
					
					System.out.println(" invitations -  SELECT " + columnName + "  FROM memberdetails as md "
							+ " join member as m on md.member_id=m.member_id"
							+ " join member_education_career as mec on m.member_id=mec.member_id "
							+ " where m.status='ACTIVE' and md.member_id!= :member_id"+initationsQuery);
					
					q.setParameter("member_id", member_id);
				
				
					List<Object[]> results = q.getResultList();
					if (results != null) {  
						for (Object[] obj : results) {
							JSONObject json = new JSONObject();
							json=getCommonJsonOutout(obj,member_id,"Accepted");		
							resultArray.put(json);
						}
					}
			 }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultArray;
	}

	private String getAcceptedIDS(String member_id) {
		String ids="";

		try {
					Query query = em.createNativeQuery("SELECT group_concat(request_from_id) FROM member_request where  request_to_id= :member_to_id and request_status= :member_request_status");
					query.setParameter("member_to_id", member_id);
					query.setParameter("member_request_status", "Accepted");
					try {
						List results = query.getResultList();
						if (results.isEmpty() || results==null) System.out.println("blank");
						else if (results.size() == 1) ids= results.get(0).toString();
					} catch (Exception e) {
					}
					
					try {
						// for show other member informations also
						String otherProfilesIds="";
						query = em.createNativeQuery("SELECT group_concat(member_to_id) FROM member_request where  request_from_id= :request_from_id and request_status= :member_request_status");
						query.setParameter("request_from_id", member_id);
						query.setParameter("member_request_status", "Accepted");
						List results = query.getResultList();
						if (results.isEmpty()  || results==null) System.out.println("blank");
						else if (results.size() == 1) otherProfilesIds= results.get(0).toString();
						
						if(!ids.equals("")) {
							if(!otherProfilesIds.equals("")) {
								ids=ids + "," + otherProfilesIds;
							}
						}
					} catch (Exception e) {
						// TODO: handle exception
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
		 String columnName=getCommonColumnForSearch();
//		******************************Query*************************************************************************
		 String getAcceptedIDS=getRejectedIDS(member_id);
		 String initationsQuery="";
			JSONArray resultArray = new JSONArray();
		 if( getAcceptedIDS!=null && !getAcceptedIDS.equals("")) {
			 initationsQuery=" and md.member_id in ("+getAcceptedIDS+")";
			 
			 Query q = em.createNativeQuery("SELECT " + columnName + "  FROM memberdetails as md "
						+ " join member as m on md.member_id=m.member_id"
						+ " join member_education_career as mec on m.member_id=mec.member_id "
						+ " where m.status='ACTIVE' and md.member_id!= :member_id"+initationsQuery);
				
				System.out.println(" invitations -  SELECT " + columnName + "  FROM memberdetails as md "
						+ " join member as m on md.member_id=m.member_id"
						+ " join member_education_career as mec on m.member_id=mec.member_id "
						+ " where m.status='ACTIVE' and md.member_id!= :member_id"+initationsQuery);
				
				q.setParameter("member_id", member_id);
			
			
				List<Object[]> results = q.getResultList();
				if (results != null) { 
					for (Object[] obj : results) {
						JSONObject json = new JSONObject();
						json=getCommonJsonOutout(obj,member_id,"Rejected");		
						resultArray.put(json);
					}
				}
		 }
		return resultArray;
	}

	private String getRejectedIDS(String member_id) {
		String ids="";

		try {
					Query query = em.createNativeQuery("SELECT group_concat(request_from_id) FROM member_request where  request_to_id= :member_to_id and request_status= :member_request_status");
					query.setParameter("member_to_id", member_id);
					query.setParameter("member_request_status", "Rejected");
					ids = query.getSingleResult().toString();
		} catch (Exception e) {
		e.printStackTrace();
		}
				return ids;
	}

	@Transactional
	private List getDetailsMemberByMember_id(Integer request_from_id) {
		List lstAdd=new ArrayList();
		try {
			String columnName = "email_id,first_name,last_name, m.member_id, height,md.age,md.marital_status as maritalStatus,edu.highest_qualification as highest_qualification,edu.working_with as working_with";

			List<Object[]> results = requestMemberRepository.getMemberDeyailsForMail(""+request_from_id);
			if (results != null) {
				for (Object[] obj : results) {
				int i=0;
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
				
				religions=religions;
				if(!caste.equals("")) {
					religions=religions+" / "+caste;
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

}
