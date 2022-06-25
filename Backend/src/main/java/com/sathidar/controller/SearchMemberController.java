package com.sathidar.controller;

import java.util.HashMap;
import java.util.Map;

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
import com.sathidar.EntityMangerFactory.SearchMemberManagerFactory;
import com.sathidar.model.MemberPreferenceModel;
import com.sathidar.model.SearchMembersModel;
import com.sathidar.service.SearchMemberService;

@CrossOrigin(origins = "http://localhost:4200" , methods = {RequestMethod.OPTIONS, RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE}, allowedHeaders = "*", allowCredentials = "true")
@RestController
@RequestMapping("/api")
public class SearchMemberController {

	
	@Autowired
	private SearchMemberService searchMemberService;
	
	@Autowired
	private SearchMemberManagerFactory searchMemberManagerFactory;
	
	@PostMapping(path = "/member/search/update/{id}")
	public Map<String, String> updateSearchMemberDetails(
			@Validated @RequestBody SearchMembersModel searchMembersModel, @PathVariable("id") int id) {
		HashMap<String, String> map = new HashMap<>();
		if (searchMemberService.updateSearchMemberDetails(searchMembersModel, id) != null) {
			map.put("message", "search members updated...");
		} else {
			map.put("message", "Something wrong , please try again....");
		}
		return map;
	}

	@GetMapping(value = "/member/search/get/{member_id}")
	public  HashMap<String,String>  getSearchMemberDetails(@PathVariable("member_id") int member_id) {
		 HashMap<String,String> map = new HashMap<>();
		 map=searchMemberManagerFactory.getSearchMemberDetails(member_id);
		 if(map==null) {
			map.put("message","something wrong ! record not fetch...");
		 }
		 return map;
	}
}
