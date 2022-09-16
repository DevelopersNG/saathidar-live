package com.sathidar.service;

import org.json.JSONArray;

import com.sathidar.model.AddAdvertisement;
import com.sathidar.model.SuccessStoriesModel;

public interface SuccessStoriesService {
	
	
	public abstract int saveToImage(SuccessStoriesModel successStoriesModel);

	public abstract int deleteImagesById(String id);

	JSONArray getSuccessStory();



}
