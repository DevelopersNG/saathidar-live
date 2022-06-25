package com.sathidar.controller;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
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

import com.sathidar.EntityMangerFactory.MemberPreferenceManagerFactory;
import com.sathidar.model.MemberPreferenceModel;
import com.sathidar.model.UpdateMember;
import com.sathidar.service.MemberPreferenceService;

@CrossOrigin(origins = "http://localhost:4200" , methods = {RequestMethod.OPTIONS, RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE}, allowedHeaders = "*", allowCredentials = "true")
@RestController
@RequestMapping("/api")
public class MemberPreferenceController {

	@Autowired
	private MemberPreferenceService memberPreferenceService;
	
	@Autowired
	private MemberPreferenceManagerFactory memberPreferenceManagerFactory;

	@PostMapping(path = "/member/preference/update/{id}")
	public Map<String, String> updateMemberPreference(
			@Validated @RequestBody MemberPreferenceModel memberPreferenceModel, @PathVariable("id") int id) {
		HashMap<String, String> map = new HashMap<>();
		if (memberPreferenceService.updateMemberPreference(memberPreferenceModel, id) != null) {
			map.put("message", "member preference updated...");
		} else {
			map.put("message", "Something wrong , please try again....");
		}
		return map;
	}

	@GetMapping(value = "/member/preference/get/{member_id}")
	public  HashMap<String,String>  getMemberPreferenceDetails(@PathVariable("member_id") int member_id) {
		 HashMap<String,String> map = new HashMap<>();
		 map=memberPreferenceManagerFactory.getMemberPreferenceDetails(member_id);
		 if(map==null) {
			map.put("message","something wrong ! record not fetch...");
		 }
		 return map;
	}
	
	@PostMapping(value = "/member/preference/delete/{member_id}")
	public String deleteMemberPreferenceDetails(@PathVariable("member_id") int member_id) {
		 JSONArray jsonResultArray = new JSONArray();
		 jsonResultArray=memberPreferenceManagerFactory.deleteMemberPreferenceDetails(member_id);
		 return jsonResultArray.toString();
	}
}
