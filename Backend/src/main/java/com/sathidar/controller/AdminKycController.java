package com.sathidar.controller;



import java.util.HashMap;

import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
import com.sathidar.model.AdminKycModel;
import com.sathidar.model.AdminUploadPhotoModel;
import com.sathidar.model.UploadDocumentModel;
import com.sathidar.service.AdminKycService;
   
	@CrossOrigin("*")
	@RestController
	@RequestMapping("/api/admin")
	 @Configuration
	public class AdminKycController {
		
	    
        @Autowired	   
		private AdminKycService adminKycService;
        
        @PostMapping("kyc/reject/photo")
        public HashMap<String, String> rejectKYCPhoto(AdminKycModel adminKycModel) {
			HashMap<String, String> map = new HashMap<>();
			int status = adminKycService.rejectKYCPhoto(adminKycModel);
			if (status > 0) {
				map.put("results", "1");
				map.put("results", ""+status);
			} else {
				map.put("results", "0");
				map.put("results", ""+status);
			}
			return map;
		}		
        
        @PostMapping("kyc/approve/photo")
        public HashMap<String, String> approveKYCPhoto(AdminKycModel adminKycModel) {
			HashMap<String, String> map = new HashMap<>();
			int status = adminKycService.approveKYCPhoto(adminKycModel);
			if (status > 0) {
				map.put("results", "1");
				map.put("results", ""+status);
			} else {
				map.put("results", "0");
				map.put("results", ""+status);
			}
			return map;
		}	
//		@PostMapping("kyc/delete/photo")
//		public HashMap<String, String> deleteMemberPhotos(AdminKycModel adminKycModel) {
//			HashMap<String, String> map = new HashMap<>();
//			int status = adminKycService.deleteImages(adminKycModel);
//			if (status > 0) {
//				map.put("results", "1");
//			} else {
//				map.put("results", "0");
//			}
//			return map;
//		}		
//	
//		@PostMapping("/kyc/approve/photo")
//		public HashMap<String, String> saveMemberKYCPhotoUpload(AdminKycModel adminKycModel, @RequestParam("document") MultipartFile multipartFile) {
//			HashMap<String, String> map = new HashMap<>();
//			adminKycModel = adminKycService.uploadKYCImages(adminKycModel,multipartFile);
//			if (adminKycModel != null) {
//				map.put("results", "1");
//			} else {
//				map.put("results", "0");
//			}
//			return map;
//		}
//
//		@GetMapping(value = "/kyc/get/photo/{id}")
//		private String getKycPhotos(@PathVariable String kyc_id) {
//			JSONArray jsonResultsArray = new JSONArray();
//			JSONObject jsObject = new JSONObject();
//			jsonResultsArray = adminKycService.getKycPhotos(kyc_id);
//			if (jsonResultsArray == null) {
//				jsObject.put("data", jsonResultsArray);
//				jsObject.put("results", "0");
//				jsObject.put("message", "something wrong ! record not fetch...");
//			} else {
//				jsObject.put("data", jsonResultsArray);
//				jsObject.put("results", "1");
//			}
//			return jsObject.toString();
//		}

		}



