package com.sathidar.controller;

import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sathidar.model.PrivacyOptionsModel;
import com.sathidar.service.PrivacyOptionsService;
import com.sathidar.util.PrivacyPoliyConstant;

@CrossOrigin(origins = "http://localhost:4200" , methods = {RequestMethod.OPTIONS, RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE}, allowedHeaders = "*", allowCredentials = "true")
@RestController
@RequestMapping("/api")
public class PrivacyOptionsController {

	@Autowired
	private PrivacyOptionsService privacyOptionsService;
	
	@PostMapping(value = "/privacy/update/phone")
	public String updatePhone(@Validated @RequestBody PrivacyOptionsModel privacyOptionsModel) {
		 JSONObject jsObject = new JSONObject();
		 HashMap<String,String> map= new HashMap<String, String>(); 
		 int status=privacyOptionsService.updatePhonePrivacy(privacyOptionsModel);
		 if(status>0) {
			 map.put("results",""+1);
			 map.put("message","Phone Privacy Updated");
		 }
		 	 else
		 	 {
		 		map.put("results",""+0);
		 		map.put("message","Something wrong ! phone privacy not updated");
		 	 }
			 return jsObject.put("Data", map).toString();
	}
	
	@PostMapping(value = "/privacy/update/email")
	public String updateEmail(@Validated @RequestBody PrivacyOptionsModel privacyOptionsModel) {
		 JSONObject jsObject = new JSONObject();
		 HashMap<String,String> map= new HashMap<String, String>(); 
		 int status=privacyOptionsService.updateEmailPrivacy(privacyOptionsModel);
		 if(status>0) {
			 map.put("results",""+1);
			 map.put("message","Email Privacy Updated");
		 }
		 	 else
		 	 {
		 		map.put("results",""+0);
		 		map.put("message","Something wrong ! phone privacy not updated");
		 	 }	
			 return jsObject.put("Data", map).toString();
	}
	
	@PostMapping(value = "/privacy/update/photo")
	public String updatePhoto(@Validated @RequestBody PrivacyOptionsModel privacyOptionsModel) {
		 JSONObject jsObject = new JSONObject();
		 HashMap<String,String> map= new HashMap<String, String>(); 
		 int status=privacyOptionsService.updatePhotoPrivacy(privacyOptionsModel);
		 if(status>0) {
			 map.put("results",""+1);
			 map.put("message","Photo Privacy Updated");
		 }
		 	 else
		 	 {
		 		map.put("results",""+0);
		 		map.put("message","Something wrong ! phone privacy not updated");
		 	 }	
			 return jsObject.put("Data", map).toString();
	}
	
	@PostMapping(value = "/privacy/update/dob")
	public String updateDOB(@Validated @RequestBody PrivacyOptionsModel privacyOptionsModel) {
		 JSONObject jsObject = new JSONObject();
		 HashMap<String,String> map= new HashMap<String, String>(); 
		 int status=privacyOptionsService.updateDOBPrivacy(privacyOptionsModel);
		 if(status>0) {
			 map.put("results",""+1);
			 map.put("message","Date Of Birth Privacy Updated");
		 }
		 	 else
		 	 {
		 		map.put("results",""+0);
		 		map.put("message","Something wrong ! phone privacy not updated");
		 	 }	
			 return jsObject.put("Data", map).toString();
	}
	
	@PostMapping(value = "/privacy/update/annual-income")
	public String updateAnnualIncome(@Validated @RequestBody PrivacyOptionsModel privacyOptionsModel) {
		 JSONObject jsObject = new JSONObject();
		 HashMap<String,String> map= new HashMap<String, String>(); 
		 int status=privacyOptionsService.updateAnnualIncomePrivacy(privacyOptionsModel);
		 if(status>0) {
			 map.put("results",""+1);
			 map.put("message","Annual Income Privacy Updated");
		 }
		 	 else
		 	 {
		 		map.put("results",""+0);
		 		map.put("message","Something wrong ! phone privacy not updated");
		 	 }	
			 return jsObject.put("Data", map).toString();
	}
	
	@PostMapping(value = "/privacy/update/horoscope")
	public String updateHoroscope(@Validated @RequestBody PrivacyOptionsModel privacyOptionsModel) {
		 JSONObject jsObject = new JSONObject();
		 HashMap<String,String> map= new HashMap<String, String>(); 
		 int status=privacyOptionsService.updateHoroscopePrivacy(privacyOptionsModel);
		 if(status>0) {
			 map.put("results",""+1);
			 map.put("message","Horoscope Privacy Updated");
		 }
		 	 else
		 	 {
		 		map.put("results",""+0);
		 		map.put("message","Something wrong ! phone privacy not updated");
		 	 }	
			 return jsObject.put("Data", map).toString();
	}
	
	@PostMapping(value = "/privacy/update/visitors-settings")
	public String updateVisitorsSetting(@Validated @RequestBody PrivacyOptionsModel privacyOptionsModel) {
		 JSONObject jsObject = new JSONObject();
		 HashMap<String,String> map= new HashMap<String, String>(); 
		 int status=privacyOptionsService.updateVisitorSettingPrivacy(privacyOptionsModel);
		 if(status>0) {
			 map.put("results",""+1);
			 map.put("message","Visitors Privacy Updated");
		 }
		 	 else
		 	 {
		 		map.put("results",""+0);
		 		map.put("message","Something wrong ! phone privacy not updated");
		 	 }	
			 return jsObject.put("Data", map).toString();
	}
	
	@PostMapping(value = "/privacy/update/shortlist-settings")
	public String updateShortlistSetting(@Validated @RequestBody PrivacyOptionsModel privacyOptionsModel) {
		 JSONObject jsObject = new JSONObject();
		 HashMap<String,String> map= new HashMap<String, String>(); 
		 int status=privacyOptionsService.updateShortlistSettingPrivacy(privacyOptionsModel);
		 if(status>0) {
			 map.put("results",""+1);
			 map.put("message","Shortlist Privacy Updated");
		 }
		 	 else
		 	 {
		 		map.put("results",""+0);
		 		map.put("message","Something wrong ! phone privacy not updated");
		 	 }	
			 return jsObject.put("Data", map).toString();
	}
	
	@PostMapping(value = "/privacy/update/profile-privacy")
	public String updateProfilePrivacySetting(@Validated @RequestBody PrivacyOptionsModel privacyOptionsModel) {
		 JSONObject jsObject = new JSONObject();
		 HashMap<String,String> map= new HashMap<String, String>(); 
		 int status=privacyOptionsService.updateProfilePrivacyPrivacy(privacyOptionsModel);
		 if(status>0) {
			 map.put("results",""+1);
			 map.put("message","Profile Privacy Updated");
		 }
		 	 else
		 	 {
		 		map.put("results",""+0);
		 		map.put("message","Something wrong ! phone privacy not updated");
		 	 }	
			 return jsObject.put("Data", map).toString();
	}
}