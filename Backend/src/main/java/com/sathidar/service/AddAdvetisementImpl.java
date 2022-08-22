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
import com.sathidar.model.AddAdvertisementRepository;

import antlr.collections.List;

@Service
public class AddAdvetisementImpl implements AddAdvertisementService 
{
	@Autowired 
	AddAdvertisementRepository addAdvertisementRepository;
	
	@Override
	public int saveToImage(AddAdvertisement addAdvertisement) {
		
		int response = 0;
		try {
//			String image_name = uploadImagesModel.getImage_name(); base 64
			String[] strArray = addAdvertisement.getImage_base_urls();
			for (int i = 0; i < strArray.length; i++) {
				Random random = new Random();
				String id = String.format("%04d", random.nextInt(10000));
				
				
				//Constant constant = new Constant();

				String uploadDir =System.getProperty("catalina.base") + "/webapps";
				
				String saveFolderPath = "/member_images/" + "advertise" + id + ".jpg";
				addAdvertisement.setAdvt_photo(saveFolderPath);

				uploadDir = uploadDir + "/member_images/";

				File theDir = new File(uploadDir);
				if (!theDir.exists()) {
					theDir.mkdirs();
				}

				String base64Image = strArray[i].toString().split(",")[1];
				byte[] data = java.util.Base64.getDecoder().decode(base64Image);
			
				int status = addAdvertisementRepository.saveMemberPhotos(addAdvertisement.getDescription(),
						addAdvertisement.getAdvertise());
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
	public JSONArray getAdvertise() {
		JSONArray resultArray = new JSONArray();
		try {
			//JSONObject json = new JSONObject();
			List post =  (List) addAdvertisementRepository.getById();
			if (post != null) {
				for (int i = 0; i < post.length(); i++) {
					JSONObject jsonObj = new JSONObject();
					jsonObj.put("member_images", ((AddAdvertisement) ((JSONArray) post).get(i)).getImage_base_urls());
					jsonObj.put("image_id", "" + ((AddAdvertisement) ((JSONArray) post).get(i)).getId());
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
	public int deleteImagesById(AddAdvertisement addAdvertisement) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteAdvertisement(AddAdvertisement addAdvertisement) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public JSONArray getAdvt_photo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONArray getAdvertisement() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteImagesById(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

}

	
