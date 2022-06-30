package com.sathidar.util;

import java.util.HashMap;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
public class PrivacyPoliyConstant {
	
	public HashMap<Integer,String> lst_Phone = getPhoneList();
	
	public HashMap<Integer,String> lst_Email = getEmailList();

	public HashMap<Integer,String> lst_Photo = getPhotoList();

	public HashMap<Integer,String> lst_DOB = getDOBList();
	
	public HashMap<Integer,String> lst_Annual_Income = getAnnualIncomeList();

	public HashMap<Integer,String> lst_Horoscope = getHoroscopeList();

	public HashMap<Integer,String> lst_VisitorsSettings = getVisitorsSettingsList();
	
	public HashMap<Integer,String> lst_ShortlistSettings = getShortlistSettingsList();
	
	public HashMap<Integer,String> lst_ProfilePrivacy = getProfilePrivacyList();

	private HashMap<Integer, String> getPhoneList() {
		HashMap<Integer, String> map=new HashMap<Integer, String>();
		map.put(1,"Visible to all Member");
		map.put(2,"Visible to all Premium Members");
		map.put(3,"Keep this private");
//		map.put(1,"Only Premium Members");
//		map.put(2,"Only Premium Members you like");
//		map.put(3,"No one (Matches won't be able to call you)");
//		map.put(4,"Only visible to all your Matches (Expires with Membership)");
		return map;
	}
	
	private HashMap<Integer, String> getEmailList() {
		HashMap<Integer, String> map=new HashMap<Integer, String>();
		map.put(1,"Visible to all Member");
		map.put(2,"Visible to all Premium Members");
		map.put(3,"Keep this private");
//		map.put(1,"Visible to all Premium Members");
//		map.put(2,"Visible to Premium Members you wish to connect to");
//		map.put(3,"Hide my Email Address");
//		map.put(4,"Visible to all your Matches (Expires with Membership)");
		return map;
	}
	
	private HashMap<Integer, String> getPhotoList() {
		HashMap<Integer, String> map=new HashMap<Integer, String>();
		map.put(1,"Visible to all Member");
		map.put(2,"Visible to all Premium Members");
		map.put(3,"Keep this private");
//		map.put(1,"Visible to all Member");
//		map.put(2,"Visible to Members I like and to all Premium Members");
		return map;
	}
	
	private HashMap<Integer, String> getDOBList() {
		HashMap<Integer, String> map=new HashMap<Integer, String>();
		map.put(1,"Show my full Date of Birth (dd/mm/yyyy)");
		map.put(2,"Show only the Month & Year (mm/yyyy)");
		return map;
	}
	
	private HashMap<Integer, String> getAnnualIncomeList() {
		HashMap<Integer, String> map=new HashMap<Integer, String>();
		map.put(1,"Visible to all Member");
		map.put(2,"Visible to all Premium Members");
		map.put(3,"Keep this private");
//		map.put(1,"Visible to all Members");
//		map.put(2,"Keep this private");
		return map;
	}
	
	private HashMap<Integer, String> getHoroscopeList() {
		HashMap<Integer, String> map=new HashMap<Integer, String>();
		map.put(1,"Visible to all Member");
		map.put(2,"Visible to all Premium Members");
		map.put(3,"Keep this private");
//		map.put(1,"Visible to all Members");
//		map.put(2,"Visible to contacted and accepted Members");
//		map.put(3,"Hide from all");
		return map;
	}
	
	
//	************* this part do latter **********************************
	private HashMap<Integer, String> getVisitorsSettingsList() {
		HashMap<Integer, String> map=new HashMap<Integer, String>();
		map.put(1,"Let other Members know that I have visited their Profile");
		map.put(2,"Do not let other Members know that I have visited their Profile");
		return map;
	}
	
	private HashMap<Integer, String> getShortlistSettingsList() {
		HashMap<Integer, String> map=new HashMap<Integer, String>();
		map.put(1,"Let other Members know that I have shortlisted their Profile");
		map.put(2,"Do not let other Members know that I have shortlisted their Profile");
		return map;
	}
	
	private HashMap<Integer, String> getProfilePrivacyList() {
		HashMap<Integer, String> map=new HashMap<Integer, String>();
		map.put(1,"Visible to all, including unregistered visitors (Photo and Name will not be visible on Profile)");
		map.put(2,"Only visible to registered Members");
		return map;
	}
	

	
}
