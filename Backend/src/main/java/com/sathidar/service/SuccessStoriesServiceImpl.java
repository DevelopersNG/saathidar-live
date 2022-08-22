package com.sathidar.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Random;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.sathidar.model.AddAdvertisement;
import com.sathidar.model.SuccessStoriesModel;

import com.sathidar.repository.SuccessStoriesRepository;

import antlr.collections.List;

public class SuccessStoriesServiceImpl implements SuccessStoriesService{
	
	@Autowired 
	SuccessStoriesRepository successStoriesRepository;
	
	@Override
	public int saveToImage(SuccessStoriesModel successStoriesModel) {
		
		int response = 0;
		try {
//			String image_name = uploadImagesModel.getImage_name(); base 64
			String[] strArray = successStoriesModel.getImage_base_urls();
			for (int i = 0; i < strArray.length; i++) {
				Random random = new Random();
				String id = String.format("%04d", random.nextInt(10000));
				
				
				//Constant constant = new Constant();

				String uploadDir =System.getProperty("catalina.base") + "/webapps";
				
				String saveFolderPath = "/member_images/" + "advertise" + id + ".jpg";
				successStoriesModel.setsuccess_photo(saveFolderPath);

				uploadDir = uploadDir + "/member_images/";

				File theDir = new File(uploadDir);
				if (!theDir.exists()) {
					theDir.mkdirs();
				}

				String base64Image = strArray[i].toString().split(",")[1];
				byte[] data = java.util.Base64.getDecoder().decode(base64Image);
			
				int status = successStoriesRepository.saveMemberPhotos(successStoriesModel.getDescription(),
						successStoriesModel.getsuccess_photo());
				if (status > 0) {
//					try (OutputStream stream = new FileOutputStream(uploadDir + "advertise" + id + ".jpg")) {
//						stream.write(data);
//					}
					try (OutputStream stream = new FileOutputStream(uploadDir)) {
						stream.write(data);
					}
				}		
			response=1;
		}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(" *************************** errorr in photo ");
		}
		return response;
	}

	@Override
	public JSONArray getSuccessStory() {
		JSONArray resultArray = new JSONArray();
		try {
			List post =  (List) successStoriesRepository.getById();
			if (post != null) {
				for (int i = 0; i < post.length(); i++) {
					JSONObject jsonObj = new JSONObject();
					jsonObj.put("success_photo", ((AddAdvertisement) ((JSONArray) post).get(i)).getImage_base_urls());
					jsonObj.put("id", "" + ((AddAdvertisement) ((JSONArray) post).get(i)).getId());
					resultArray.put(jsonObj);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultArray=null;
		}
		return resultArray;
	}
	
	

	

	
@Override
	public int deleteImagesById(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

}
