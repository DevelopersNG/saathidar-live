package com.sathidar.service;

import org.json.JSONArray;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sathidar.model.UploadImagesModel;

@Service
public interface UploadImagesService {

	int saveToImage(UploadImagesModel uploadImagesModel);

	JSONArray getMemberPhotos(String member_id);

	int deleteImagesById(UploadImagesModel uploadImagesModel);

	UploadImagesModel uploadImages(UploadImagesModel uploadImagesModel, MultipartFile multipartFile);

	JSONArray getMemberAppPhotos(String member_id);

	int deleteImages(UploadImagesModel uploadImagesModel);

	int setMemberProfilePhoto(UploadImagesModel uploadImagesModel);

	String getMemberProfilePhotoPath(String profile_photo_id);
}
