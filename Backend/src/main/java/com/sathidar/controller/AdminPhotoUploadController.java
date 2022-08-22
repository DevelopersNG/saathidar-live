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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sathidar.EntityMangerFactory.adminKycEntityManagerFactory;
import com.sathidar.model.AdminKycModel;
import com.sathidar.model.AdminUploadPhotoModel;
import com.sathidar.model.UploadImagesModel;
import com.sathidar.service.AdminKycService;
import com.sathidar.service.AdminUploadPhotoService;

		@CrossOrigin("*")
		@RestController
		@RequestMapping("/api")
		public class AdminPhotoUploadController {
			
			@Autowired
			private AdminUploadPhotoService adminUploadPhotoService;

			@Autowired
			private adminKycEntityManagerFactory adminEntityManagerFactory;

//			
//			@GetMapping(value = "kyc/upload/{id}")
//			private String getMemberPhoto(@PathVariable String member_id) {
//				JSONArray jsonResultsArray = new JSONArray();
//				JSONObject jsObject = new JSONObject();
//				jsonResultsArray = adminKycService.getKycPhotos(member_id);
//				if (jsonResultsArray == null) {
//					jsObject.put("data", jsonResultsArray);
//					jsObject.put("results", "0");
//					jsObject.put("message", "something wrong ! record not fetch...");
//				} else {
//					jsObject.put("data", jsonResultsArray);
//					jsObject.put("results", "1");
//				}
//				return jsObject.toString();
//			}
			
			
			@PostMapping("/member/decline/photo")
			public HashMap<String, String> deleteMemberPhotos(AdminUploadPhotoModel adminUploadPhotoModel) {
				HashMap<String, String> map = new HashMap<>();
				int status = adminUploadPhotoService.deleteImages(adminUploadPhotoModel);
				if (status > 0) {
					map.put("results", "1");
				} else {
					map.put("results", "0");
				}
				return map;
			}
		
			
			@PostMapping("approve/photo/{id}")
			public HashMap<String, String> saveMemberKYCPhotoUpload(AdminUploadPhotoModel adminUploadPhotoModel, @RequestParam("document") MultipartFile multipartFile) {
				HashMap<String, String> map = new HashMap<>();
				 adminUploadPhotoModel = adminUploadPhotoService.ApprovePhoto(adminUploadPhotoModel,multipartFile);
				if (adminUploadPhotoModel != null) {
					map.put("results", "1");
				} else {
					map.put("results", "0");
				}
				return map;
			}

			@GetMapping(value = "/kyc/get/photo/{id}")
			private String getKycPhotos(@PathVariable String photo_id) {
				JSONArray jsonResultsArray = new JSONArray();
				JSONObject jsObject = new JSONObject();
				jsonResultsArray = adminUploadPhotoService.getApprovedPhotos(photo_id);
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

			}
	
	

