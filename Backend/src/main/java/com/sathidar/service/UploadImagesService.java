package com.sathidar.service;

import org.json.JSONArray;
import org.springframework.stereotype.Service;

import com.sathidar.model.UploadImagesModel;

@Service
public interface UploadImagesService {

	int saveToImage(UploadImagesModel uploadImagesModel);

	JSONArray getMemberPhotos(String member_id);

	int deleteImagesById(UploadImagesModel uploadImagesModel);
	

}
