package com.sathidar.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sathidar.EntityMangerFactory.GetNameByIDMangerFactory;
import com.sathidar.model.RazorPayModel;
import com.sathidar.repository.RazorPayRepository;

@Service
public class RazorPayServiceImpl implements RazorPayService {

	@Autowired
	private RazorPayRepository razorPayRepository;

	@Autowired
	private GetNameByIDMangerFactory getNameByIDMangerFactory;

	@Autowired
	private EmailService mailSender;
	
	@Override
	public int updatePremiumMemberDetails(RazorPayModel razorPayModel) {
		
		String member_id=razorPayModel.getMember_id();
		String plan_name=razorPayModel.getPlan_name();
		String plan_amount=razorPayModel.getPlan_amount();
		
		int plan_id= getNameByIDMangerFactory.getUpgradePlanIdByName(plan_name);
		
		int status=razorPayRepository.updatePremiumMemberDetails(member_id,plan_id);
		
		System.out.println("status plan "+status);
		if(status>0) {
		List lst=new ArrayList();
		lst= getDetailsMemberByMember_id(""+plan_id);
		
		String fullName="",emailId_to="";
		List<Object[]> results = razorPayRepository.getUserNameEmailId(Integer.parseInt(member_id));
		if (results != null) {
			for (Object[] obj : results) {
				int i=0;
				fullName= convertNullToBlank(String.valueOf(obj[i])) +" " + convertNullToBlank(String.valueOf(obj[++i]));
				emailId_to= convertNullToBlank(String.valueOf(obj[++i]));
			}
		}
		String  response="";
		if(lst!=null) {
			 response=sentPlanDetailsByEmail(lst,emailId_to,fullName);
		}
	}
		return status;
	}

	private String sentPlanDetailsByEmail(List lst, String emailId_to, String fullName) {
		String respons="";
		try {
				String plan_name = convertNullToBlank(lst.get(0).toString());
				String plan_amount = convertNullToBlank(lst.get(1).toString());
				String plan_durations = convertNullToBlank(lst.get(2).toString());
				
				String email_body="<head>\r\n" + 
						"    <meta charset=\"UTF-8\">\r\n" + 
						"    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n" + 
						"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n" + 
						"    <style>\r\n" + 
						"        table {font-family: arial, sans-serif;border-collapse: collapse;width: 100%;}\r\n" + 
						"td, th {border: 1px solid #dddddd; text-align: left;padding: 8px;\r\n" + 
						"}img{height: 150px;}.bg{ background-color: #742041;}\r\n" + 
						"    </style>\r\n" + 
						"</head>\r\n" + 
						"<body style=\"width: 400px;\">\r\n" + 
						"    <div style=\"background-color: #742041;\"><img style=\"width:300px ;\" src=\"www.saathidaar.com/assets/images/logo_eng.png\" alt=\"\"></div>\r\n" + 
						"    <p ><strong>Dear "+fullName+", </strong> </p>\r\n" + 
						"    <p style=\"color: #742041;\">Thanks you purchasing <strong>"+plan_name+" Membership</strong> from <a href=\"saathidaar.com\">saathidaar.com</a> \r\n" + 
						"    </p>\r\n" + 
						" <div class=\"image\">\r\n" + 
						"<p>\r\n" + 
						"    Here are your purchase details : \r\n" + 
						"    </p>\r\n" + 
						"   <table style=\"width: 100%;border: #742041 1px solid;\" class=\"table\">\r\n" + 
						"    <thead>\r\n" + 
						"      <tr >\r\n" + 
						"        <th  scope=\"col\">Premium Membership</th>\r\n" + 
						"        <th  scope=\"col\">Amount</th>\r\n" + 
						"\r\n" + 
						"      </tr>\r\n" + 
						"      <tr>\r\n" + 
						"        <th scope=\"col\">"+plan_name+" Membership</th>\r\n" + 
						"        <th scope=\"col\">"+plan_amount+"</th>\r\n" + 
						"      </tr>\r\n" + 
						"    </thead>\r\n" + 
						"  </table>\r\n" + 
						" </div>\r\n" + 
						" <div class=\"details\" style=\"margin-top: 15px;\">Your <strong>"+plan_name+" Membership</strong> of <strong>Rs."+plan_amount+"</strong> is for the duration of <strong>"+plan_durations+"</strong> months starting from today.\r\n" + 
						" <br> If you have any questions please visit our pages - <a href=\"\">HELP</a>, <a href=\"\">Privacy Policy</a>, <a href=\"\">T&C</a>, <a href=\"\">Customer Support</a>. \r\n" + 
						"  </div>\r\n" + 
						"  </body>";
				
				
				mailSender.send(emailId_to, "Plan Purchases", email_body);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return respons;
	}

	private List getDetailsMemberByMember_id(String plan_id) {
		List lstAdd = new ArrayList();
		try {
			List<Object[]> results = razorPayRepository.getMemberPlanDetailsForMail(plan_id);
			if (results != null) {
				for (Object[] obj : results) {
					int i = 0;
					String plan_name = convertNullToBlank(String.valueOf(obj[i]));
					String plan_durations = convertNullToBlank(String.valueOf(obj[++i]));
					String plan_amount = convertNullToBlank(String.valueOf(obj[++i]));
					System.out.println("in");
					lstAdd.add(plan_name);
					lstAdd.add(plan_amount);
					lstAdd.add(plan_durations);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lstAdd;
	}

	public String convertNullToBlank(String value) {
		if (value != null && !value.equals("")) {
			return value;
		}
		return "";
	}

}
