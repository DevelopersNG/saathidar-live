package com.sathidar.controller;

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

import com.google.gson.JsonObject;
import com.sathidar.model.ShortListsModel;
import com.sathidar.service.ShortListService;

@CrossOrigin(origins = "http://localhost:4200" , methods = {RequestMethod.OPTIONS, RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE}, allowedHeaders = "*", allowCredentials = "true")
@RestController
@RequestMapping("/api")
public class ShortListsController {

	@Autowired
	private ShortListService shortListService;
	

	@PostMapping(value="/member/add-to-shortlist")
	private String AddToShortLists(@Validated @RequestBody ShortListsModel shortListsModel) {
		JSONArray jsonResultsArray=new JSONArray();
		jsonResultsArray= shortListService.AddToShortLists(shortListsModel);
		return jsonResultsArray.toString();
	}
	
	@GetMapping(value="/shortlist/get/all/{member_id}")
	private String GetShortListsMember(@PathVariable String member_id ) {
		JSONArray jsonResultsArray=new JSONArray();
		JSONObject jsObject=new JSONObject();
		jsonResultsArray= shortListService.GetShortListsMember(member_id);
		 if(jsonResultsArray==null) {
			 jsObject.put("data",jsonResultsArray);
			 jsObject.put("results","0");
			 jsObject.put("message","something wrong ! record not fetch...");
		 }else {
			 jsObject.put("data",jsonResultsArray);
			 jsObject.put("results","1");
		 }
		return jsObject.toString();
	}
	
	@PostMapping(value="/member/remove-to-shortlist")
	private String RemoveToShortLists(@Validated @RequestBody ShortListsModel shortListsModel) {
		JSONArray jsonResultsArray=new JSONArray();
		jsonResultsArray= shortListService.RemoveToShortLists(shortListsModel);
		return jsonResultsArray.toString();
	}
}
