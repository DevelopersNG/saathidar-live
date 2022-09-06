package com.sathidar.service;

import org.json.JSONArray;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sathidar.model.AdminUploadPhotoModel;
import com.sathidar.model.UploadImagesModel;

	@Service
	public interface AdminUploadPhotoService 
	{

//		int saveToImage(AdminUploadPhotoModel adminUploadImagesModel);
//
//		JSONArray getMemberPhotos1(String member_id);
//
//		int deleteImagesById(AdminUploadPhotoModel uploadImagesModel);
//   
//
		public int deleteImages(AdminUploadPhotoModel adminUploadPhotoModel);
//
//		JSONArray getMemberPhotos(String kyc_id);
//
//		JSONArray getApprovedPhotos(String photo_id);
//
//		AdminUploadPhotoModel UploadPhoto(AdminUploadPhotoModel adminUploadPhotoModel, MultipartFile multipartFile);
//
//		JSONArray getPhotos(String Photo_id);

		public int ApprovePhoto(AdminUploadPhotoModel adminUploadPhotoModel);
	
}