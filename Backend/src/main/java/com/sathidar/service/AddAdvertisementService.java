package com.sathidar.service;

import org.json.JSONArray;

import com.sathidar.model.AddAdvertisement;

public interface AddAdvertisementService {
	public abstract int saveToImage(AddAdvertisement addAdvertisement);

//	abstract JSONArray getMemberPhotos(String member_id);
//
//	abstract UploadImagesModel uploadImages(UploadImagesModel uploadImagesModel, MultipartFile multipartFile);


//	abstract int deleteImages(UploadImagesModel uploadImagesModel);
//
//	public abstract JSONArray getAdvt_photo(String member_id);

	public abstract int deleteImagesById(AddAdvertisement addAdvertisement);


	
	//JSONArray getAdvertisement(String image_base_urls);

	JSONArray getAdvertise();

	int deleteAdvertisement(AddAdvertisement addAdvertisement);

	public abstract JSONArray getAdvt_photo();

	JSONArray getAdvertisement();

	public abstract int deleteImagesById(int id);

}

