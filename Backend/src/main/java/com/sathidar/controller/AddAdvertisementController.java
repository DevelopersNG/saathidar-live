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

import com.sathidar.model.AddAdvertisement;
import com.sathidar.service.AddAdvertisementService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/admin")
public class AddAdvertisementController {
	
	@Autowired
	private AddAdvertisementService addAdvertisementService;
	
	@PostMapping(path = "/advertisement/add/advt_photo")
	public HashMap<String, String> uploadImages(@Validated @RequestBody AddAdvertisement addAdvertisement) {
		HashMap<String, String> map = new HashMap<>();
		int results = addAdvertisementService.saveToImage(addAdvertisement);
		if (results > 0) {
			map.put("results", "1");
		} else {
			map.put("results", "0");
		}
		return map;
	}	
	
	@GetMapping(value = "/advertisement/app/get")
	private String getAdvertisement() {
		JSONArray jsonResultsArray = new JSONArray();
		JSONObject jsObject = new JSONObject();
		jsonResultsArray = addAdvertisementService.getAdvertise();
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
	
	@PostMapping(path = "/advertisement/delete/advt_photo/{id}")
		public HashMap<String, String> deleteImages(@PathVariable int id) {
			HashMap<String, String> map = new HashMap<>();
			int results = addAdvertisementService.deleteImagesById(id);
			if (results > 0) {
				map.put("results", "1");
			} else {
				map.put("results", "0");
			}
			return map;
	}
}
