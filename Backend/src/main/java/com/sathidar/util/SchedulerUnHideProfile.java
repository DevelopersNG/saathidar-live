package com.sathidar.util;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SchedulerUnHideProfile {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private SendSMSAction sendSMSAction;
	
	
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
							
							String smsMessage = "Your Profile is unhide  , Saathidaar.com";
							String sender = "SDMOTP";
							String phoneNo = "91" + contactNumber.trim();
							String response = sendSMSAction.SendOtpSms(phoneNo, sender, smsMessage);

							System.out.println("sms send -" +response);
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
}
