package com.sathidar.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Random;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sathidar.model.AddAdvertisement;
import com.sathidar.model.SuccessStoriesModel;

import com.sathidar.repository.SuccessStoriesRepository;

import antlr.collections.List;

@Service
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
				
				String saveFolderPath = "/success_story/" + "success_story" + id + ".jpg";
				successStoriesModel.setSuccess_photo(saveFolderPath);

				uploadDir = uploadDir + "/success_story/";
				File theDir = new File(uploadDir);
				if (!theDir.exists()) {
					theDir.mkdirs();
				}

				String base64Image = strArray[i].toString().split(",")[1];
				byte[] data = java.util.Base64.getDecoder().decode(base64Image);
			
				int status = successStoriesRepository.saveMemberPhotos(successStoriesModel.getDescription(),
						successStoriesModel.getSuccess_photo());
				if (status > 0) {
//					try (OutputStream stream = new FileOutputStream(uploadDir + "advertise" + id + ".jpg")) {
//						stream.write(data);
//					}
					try (FileOutputStream stream = new FileOutputStream(uploadDir + "success_story" + id + ".jpg")) {
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
			java.util.List<SuccessStoriesModel> post =  successStoriesRepository.getById();
			if (post != null) {
				for (int i = 0; i < post.size(); i++) {
					JSONObject jsonObj = new JSONObject();
					jsonObj.put("images_path", post.get(i).getSuccess_photo());
					jsonObj.put("image_id", "" + post.get(i).getId());
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
		return successStoriesRepository.deleteByPhotoIDDeleteFlagY(id);
	}

}
