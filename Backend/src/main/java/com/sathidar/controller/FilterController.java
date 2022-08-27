package com.sathidar.controller;

import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sathidar.EntityMangerFactory.UpdateMemberEntityMangerFactory;
import com.sathidar.model.FilterSearchModel;
import com.sathidar.model.UpdateMember;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class FilterController {
	
	@Autowired
	private UpdateMemberEntityMangerFactory updateMemberEntityMangerFactory;
	
//	@GetMapping(value = "/member/filterSearch")
//	public String getNewMatches(@Validated @RequestBody FilterSearchModel filterSearchModel){
//		HashMap<String, String> map = new HashMap<>();
//		JSONObject jsObject = new JSONObject();
//		JSONArray jsonResultArray = new JSONArray();
//		UpdateMember updateMember = new UpdateMember();
//		jsonResultArray = updateMemberEntityMangerFactory.getAllMemberByFilter(filterSearchModel);
////		 jsonResultArray=updateMemberEntityMangerFactory.getNewMatches(Integer.parseInt(id),"NEW_MATCHES");	
//		if (jsonResultArray != null) {
//			jsObject.put("data", jsonResultArray);
//			jsObject.put("results", "1");
//		} else {
//			jsObject.put("data", jsonResultArray);
//			jsObject.put("results", "0");
//		}
////		System.out.println("data"+jsObject.toString());
//		return jsObject.toString();
//	}
}
