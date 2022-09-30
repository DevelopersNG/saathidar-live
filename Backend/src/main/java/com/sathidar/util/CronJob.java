package com.sathidar.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.sathidar.model.EmailSMSAlertModel;
import com.sathidar.model.UpdateMember;
import com.sathidar.model.UploadImagesModel;
import com.sathidar.service.EmailSMSAlertService;
import com.sathidar.service.UpdateMemberService;

@Service
public class CronJob {
	
	@Autowired
	private EmailSMSAlertService emailSMSAlertService;

//	@Scheduled(cron = "0 * * * * *")//  every minutes
//    public void demoServiceMethod()
//    {  
//		Constant constant=new Constant();
//	    System.out.println("print daily");
//		//premium_match_mail,recent_visitors_email,today_match_email,member_id
//		List<EmailSMSAlertModel> data = emailSMSAlertService.getEmailAlert();
//		if (data != null) {
//			for (int i = 0; i < data.size(); i++) {
//				String premium_match_mail=constant.convertNullToBlank(data.get(i).getPremium_match_mail());
//				String recent_visitors_email=constant.convertNullToBlank(data.get(i).getRecent_visitors_email());
//				String today_match_email=constant.convertNullToBlank(data.get(i).getToday_match_email());
//				String member_id=constant.convertNullToBlank(""+data.get(i).getMember_id());
//				
//				if(!premium_match_mail.equalsIgnoreCase("") && !premium_match_mail.equalsIgnoreCase("Unsubscribe")) {
//					if(premium_match_mail.equalsIgnoreCase("Daily")) {  // for daily
//						System.out.println("premium_match_mail - "+member_id);
//					} 
//				}
//				
//				if(!recent_visitors_email.equalsIgnoreCase("") && !recent_visitors_email.equalsIgnoreCase("Unsubscribe")) {
//					if(recent_visitors_email.equalsIgnoreCase("Daily")) {  // for daily
//						System.out.println("recent_visitors_email - "+member_id);
//					} 
//				}
//				
//				if(!today_match_email.equalsIgnoreCase("") && !today_match_email.equalsIgnoreCase("Unsubscribe")) {
//					if(today_match_email.equalsIgnoreCase("Daily")) {  // for daily
//						System.out.println("today_match_email - "+member_id);
//					} 
//				}
//			}
//		}
//		 System.out.println("********************************************************************");
//    }
	
//    @Scheduled(cron = "0 1 1 * * ?")//  at 1:1 pm daily
//	@Scheduled(cron = "0 * * * * *")//  every minutes
    public void dailySchedularCall()
    {
    	Constant constant=new Constant();
    	EmailBodyClass emailBodyClass=new EmailBodyClass();
	    System.out.println("print daily");
		//premium_match_mail,recent_visitors_email,today_match_email,member_id
		List<EmailSMSAlertModel> data = emailSMSAlertService.getEmailAlert();
		if (data != null) {
			for (int i = 0; i < data.size(); i++) {
				String premium_match_mail=constant.convertNullToBlank(data.get(i).getPremium_match_mail());
				String recent_visitors_email=constant.convertNullToBlank(data.get(i).getRecent_visitors_email());
				String today_match_email=constant.convertNullToBlank(data.get(i).getToday_match_email());
				String member_id=constant.convertNullToBlank(""+data.get(i).getMember_id());
				
				if(!premium_match_mail.equalsIgnoreCase("") && !premium_match_mail.equalsIgnoreCase("Unsubscribe")) {
					if(premium_match_mail.equalsIgnoreCase("Daily")) {  // for daily
						System.out.println("premium_match_mail - "+member_id);
						// first check is mail sent or not daily basis to member_id
						int status=emailSMSAlertService.getDailyEmailStatusByMemberID(member_id);
						if(status==0) {
							// send match email to user
							// save 1=true status to db
							String fullName="",emailId_send_to="",member_number="",contact_number="";
							String sendCriteria="Premium Match";
							String imageLink=constant.project_logo;
							List lst = new ArrayList();
							lst = getDetailsMemberByMember_id(Integer.parseInt(member_id),sendCriteria);
							String message="These premium match profile is here";
							List<Object[]> results = emailSMSAlertService.getUserNameEmailId(Integer.parseInt(member_id));
							if (results != null) {
								for (Object[] obj : results) {
									int k = 0;
									fullName = constant.convertNullToBlank(String.valueOf(obj[k]))+ " "+ constant.convertNullToBlank(String.valueOf(obj[++k]));
									emailId_send_to = constant.convertNullToBlank(String.valueOf(obj[++k]));
									member_number = constant.convertNullToBlank(String.valueOf(obj[++k]));
									contact_number = constant.convertNullToBlank(String.valueOf(obj[++k]));
								}
							}
							int sendMAilStatus=emailBodyClass.sentEmailAlertToPremiumRecentVisitorsAndTodaysMatch(lst,sendCriteria,message,fullName,emailId_send_to,imageLink);
							if(sendMAilStatus>0) {
								
							}
						}
					} 
				}
				
				if(!recent_visitors_email.equalsIgnoreCase("") && !recent_visitors_email.equalsIgnoreCase("Unsubscribe")) {
					if(recent_visitors_email.equalsIgnoreCase("Daily")) {  // for daily
						System.out.println("recent_visitors_email - "+member_id);
						// first check is mail sent or not daily basis to member_id
					} 
				}
				
				if(!today_match_email.equalsIgnoreCase("") && !today_match_email.equalsIgnoreCase("Unsubscribe")) {
					if(today_match_email.equalsIgnoreCase("Daily")) {  // for daily
						System.out.println("today_match_email - "+member_id);
						// first check is mail sent or not daily basis to member_id
					} 
				}
			}
		}
		 System.out.println("********************************************************************");
    }	
    
    
    @Scheduled(cron = "0 0 21 ? * TUE",zone="Asia/Calcutta")  // weekly once on TUE
    public void weeklySchedularCall() {
    	Constant constant=new Constant();
	    System.out.println("print daily");
		//premium_match_mail,recent_visitors_email,today_match_email,member_id
		List<EmailSMSAlertModel> data = emailSMSAlertService.getEmailAlert();
		if (data != null) {
			for (int i = 0; i < data.size(); i++) {
				String premium_match_mail=constant.convertNullToBlank(data.get(i).getPremium_match_mail());
				String recent_visitors_email=constant.convertNullToBlank(data.get(i).getRecent_visitors_email());
				String today_match_email=constant.convertNullToBlank(data.get(i).getToday_match_email());
				String member_id=constant.convertNullToBlank(""+data.get(i).getMember_id());
				
				if(!premium_match_mail.equalsIgnoreCase("") && !premium_match_mail.equalsIgnoreCase("Unsubscribe")) {
					if(premium_match_mail.equalsIgnoreCase("Weekly")) {  // for daily
						System.out.println("premium_match_mail - "+member_id);
					} 
				}
				
				if(!recent_visitors_email.equalsIgnoreCase("") && !recent_visitors_email.equalsIgnoreCase("Unsubscribe")) {
					if(recent_visitors_email.equalsIgnoreCase("Weekly")) {  // for daily
						System.out.println("recent_visitors_email - "+member_id);
					} 
				}
				
				if(!today_match_email.equalsIgnoreCase("") && !today_match_email.equalsIgnoreCase("Unsubscribe")) {
					if(today_match_email.equalsIgnoreCase("Weekly")) {  // for daily
						System.out.println("today_match_email - "+member_id);
					} 
				}
			}
		}
		 System.out.println("********************************************************************");
    }
    
    @Scheduled(cron = "0 0 0 1 * *")  // monthly once
    public void monthlySchedularCall() {
    	Constant constant=new Constant();
	    System.out.println("print daily");
		//premium_match_mail,recent_visitors_email,today_match_email,member_id
		List<EmailSMSAlertModel> data = emailSMSAlertService.getEmailAlert();
		if (data != null) {
			for (int i = 0; i < data.size(); i++) {
				String premium_match_mail=constant.convertNullToBlank(data.get(i).getPremium_match_mail());
				String recent_visitors_email=constant.convertNullToBlank(data.get(i).getRecent_visitors_email());
				String today_match_email=constant.convertNullToBlank(data.get(i).getToday_match_email());
				String member_id=constant.convertNullToBlank(""+data.get(i).getMember_id());
				
				if(!premium_match_mail.equalsIgnoreCase("") && !premium_match_mail.equalsIgnoreCase("Unsubscribe")) {
					if(premium_match_mail.equalsIgnoreCase("Monthly")) {  // for daily
						System.out.println("premium_match_mail - "+member_id);
					} 
				}
				
				if(!recent_visitors_email.equalsIgnoreCase("") && !recent_visitors_email.equalsIgnoreCase("Unsubscribe")) {
					if(recent_visitors_email.equalsIgnoreCase("Monthly")) {  // for daily
						System.out.println("recent_visitors_email - "+member_id);
					} 
				}
				
				if(!today_match_email.equalsIgnoreCase("") && !today_match_email.equalsIgnoreCase("Unsubscribe")) {
					if(today_match_email.equalsIgnoreCase("Monthly")) {  // for daily
						System.out.println("today_match_email - "+member_id);
					} 
				}
			}
		}
		 System.out.println("********************************************************************");
    }
    
    @Scheduled (cron="0 0 0 25 12 ?")  // --- it will run 25th December every year 
    public void CronExpression(){

    //your logic

    }
    
    @Transactional
	private List getDetailsMemberByMember_id(Integer member_id,String sendCriteria) {
		List lstAdd = new ArrayList();
		try {
			String columnName = "email_id,first_name,last_name, m.member_id, height,md.age,md.marital_status as maritalStatus,edu.highest_qualification as highest_qualification,edu.working_with as working_with";
			Constant constant=new Constant();
			
			String getIDS="";
			if(sendCriteria.equals("Premium Match")) {
				getIDS=emailSMSAlertService.getPremiumMatchesIDS(member_id);
			}
			if(!getIDS.equals("")) {
				String[] data=null;
				if(getIDS.contains(",")) {
					data=getIDS.split(",");
				}else {
//					data.append(getIDS);
				}
			
				for(int k=0;k<data.length;k++) {
				List<Object[]> results = emailSMSAlertService.getMemberDeyailsForMail(data[k]);
				if (results != null) {
					for (Object[] obj : results) {
						List lst=new ArrayList(); 
						int i = 0;
						String email_id = constant.convertNullToBlank(String.valueOf(obj[i]));
						String first_name = constant.convertNullToBlank(String.valueOf(obj[++i]));
						String last_name = constant.convertNullToBlank(String.valueOf(obj[++i]));
						String MyMember_id = constant.convertNullToBlank(String.valueOf(obj[++i]));
						String height = constant.convertNullToBlank(String.valueOf(obj[++i]));
						String age = constant.convertNullToBlank(String.valueOf(obj[++i]));
						String marital_status = constant.convertNullToBlank(String.valueOf(obj[++i]));
						String education = constant.convertNullToBlank(String.valueOf(obj[++i]));
						String profession = constant.convertNullToBlank(String.valueOf(obj[++i]));
						String religions = constant.convertNullToBlank(String.valueOf(obj[++i]));
						String caste = constant.convertNullToBlank(String.valueOf(obj[++i]));
						String city = constant.convertNullToBlank(String.valueOf(obj[++i]));
						String mother_tongue = constant.convertNullToBlank(String.valueOf(obj[++i]));
	
						lst.add(email_id);
						lst.add(first_name);
						lst.add(last_name);
						lst.add(MyMember_id);
						lst.add(height);
						lst.add(age);
						lst.add(marital_status);
						lst.add(education);
						lst.add(profession);
	//					religions = religions;
	//					if (!caste.equals("")) {
	//						religions = religions + " / " + caste;
	//					}
						lst.add(religions);
						lst.add(city);
						lst.add(mother_tongue);
						lstAdd.add(lst);
					}
				}
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
