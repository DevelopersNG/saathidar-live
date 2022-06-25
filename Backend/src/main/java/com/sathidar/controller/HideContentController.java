package com.sathidar.controller;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sathidar.model.HideContentModel;
import com.sathidar.service.HideContentService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class HideContentController {

	@Autowired
	private HideContentService hideContentService;
	
	@PostMapping(value="/member/hide-content/{member_id}")
	private String MemberHideContent(@Validated @RequestBody HideContentModel hideContentModel,@PathVariable("member_id") int member_id) {
		JSONArray jsonResultsArray=new JSONArray();
		jsonResultsArray= hideContentService.setHideContent(hideContentModel,member_id);
		return jsonResultsArray.toString();
	}
	
	@PostMapping(value="/member/hide-content/delete/{member_id}")
	private String DeleteHideContent(@PathVariable("member_id") int member_id) {
		JSONArray jsonResultsArray=new JSONArray();
		jsonResultsArray= hideContentService.deleteHideContent(member_id);
		return jsonResultsArray.toString();
	}
}
