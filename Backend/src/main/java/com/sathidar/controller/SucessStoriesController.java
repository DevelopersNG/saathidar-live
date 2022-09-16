package com.sathidar.controller;

import java.util.HashMap;

import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sathidar.EntityMangerFactory.UploadPhotoEntityManagerFactory;
import com.sathidar.model.AddAdvertisement;
import com.sathidar.model.SuccessStoriesModel;
import com.sathidar.service.AddAdvertisementService;
import com.sathidar.service.SuccessStoriesService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/admin")
public class SucessStoriesController {
	
	@Autowired
	private SuccessStoriesService successStoriesService;
	
	@Autowired
	private UploadPhotoEntityManagerFactory uploadPhotoEntityManagerFactory;

	@PostMapping(path = "/success/story/add")
	public HashMap<String, String> uploadImages(@Validated @RequestBody SuccessStoriesModel successStoriesModel) {
		HashMap<String, String> map = new HashMap<>();
		int results = successStoriesService.saveToImage(successStoriesModel);
		if (results > 0) {
			map.put("results", "1");
		} else {
			map.put("results", "0");
		}
		return map;
	}	
	
	@GetMapping(value = "/success/story/get")
	private String getAdvertisement() {
		JSONArray jsonResultsArray = new JSONArray();
		JSONObject jsObject = new JSONObject();
		jsonResultsArray = successStoriesService.getSuccessStory();
		if (jsonResultsArray == null) {
			jsObject.put("data", jsonResultsArray);
			jsObject.put("results", "0");
			jsObject.put("message", "something wrong ! record not fetch...");
		} else {
			jsObject.put("data", jsonResultsArray);
			jsObject.put("results", "1");
		}
		return jsObject.toString();
	}
	
	@PostMapping(path = "/success/story/delete/{id}")
	public HashMap<String, String> deleteImages(@PathVariable String id ) {
		HashMap<String, String> map = new HashMap<>();
		System.out.println("id- "+ id);
		int results = successStoriesService.deleteImagesById(id);
		if (results > 0) {
			map.put("results", "1");
		} else {
			map.put("results", "0");
		}
		return map;
	}
}
