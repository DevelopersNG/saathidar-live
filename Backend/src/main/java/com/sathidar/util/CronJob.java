package com.sathidar.util;

import java.util.List;

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
	
    @Scheduled(cron = "0 1 1 * * ?")//  at 1:1 pm daily
    public void dailySchedularCall()
    {
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
					if(premium_match_mail.equalsIgnoreCase("Daily")) {  // for daily
						System.out.println("premium_match_mail - "+member_id);
					} 
				}
				
				if(!recent_visitors_email.equalsIgnoreCase("") && !recent_visitors_email.equalsIgnoreCase("Unsubscribe")) {
					if(recent_visitors_email.equalsIgnoreCase("Daily")) {  // for daily
						System.out.println("recent_visitors_email - "+member_id);
					} 
				}
				
				if(!today_match_email.equalsIgnoreCase("") && !today_match_email.equalsIgnoreCase("Unsubscribe")) {
					if(today_match_email.equalsIgnoreCase("Daily")) {  // for daily
						System.out.println("today_match_email - "+member_id);
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
}
