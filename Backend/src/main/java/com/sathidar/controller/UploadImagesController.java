package com.sathidar.controller;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Blob;
import java.util.HashMap;

import javax.sql.rowset.serial.SerialBlob;

import org.apache.commons.codec.binary.Base64;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sathidar.EntityMangerFactory.UploadPhotoEntityManagerFactory;
import com.sathidar.model.UploadImagesModel;
import com.sathidar.service.UploadImagesService;

@CrossOrigin(origins = "http://localhost:4200" , methods = {RequestMethod.OPTIONS, RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE}, allowedHeaders = "*", allowCredentials = "true")
@RestController
@RequestMapping("/api")
public class UploadImagesController {

	@Autowired
	private UploadImagesService uploadImagesService;

	@Autowired
	private UploadPhotoEntityManagerFactory uploadPhotoEntityManagerFactory;
	
	@PostMapping(path = "/member/upload/photo")
	public HashMap<String, String> uploadImages(@Validated @RequestBody UploadImagesModel uploadImagesModel) {
		HashMap<String, String> map = new HashMap<>();
		 int results=uploadImagesService.saveToImage(uploadImagesModel);
		 if(results>0) {
			 map.put("results", "1");
		 }else {
			 map.put("results", "0");
		 }
		return map;
	}
	
	@PostMapping(path = "/member/delete/photo")
	public HashMap<String, String> deleteImages(@Validated @RequestBody UploadImagesModel uploadImagesModel) {
		HashMap<String, String> map = new HashMap<>();
		 int results=uploadImagesService.deleteImagesById(uploadImagesModel);
		 if(results>0) {
			 map.put("results", "1");
		 }else {
			 map.put("results", "0");
		 }
		return map;
	}

	@SuppressWarnings("unchecked")
	@GetMapping(value="/member/get/photo/{member_id}")
	private String getMemberPhotos(@PathVariable String member_id) {
		JSONArray jsonResultsArray=new JSONArray();
		JSONObject jsObject=new JSONObject();
		jsonResultsArray= uploadImagesService.getMemberPhotos(member_id);
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
}
