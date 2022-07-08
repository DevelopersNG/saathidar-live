package com.sathidar.service;

import java.sql.Blob;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.sql.rowset.serial.SerialBlob;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sathidar.model.UploadImagesModel;
import com.sathidar.repository.UploadImagesRepository;

@Service
public class UploadImagesServiceImpl implements UploadImagesService {

	@Autowired
	private UploadImagesRepository uploadImagesRepository;

	@Override
	public int saveToImage(UploadImagesModel uploadImagesModel) {
//		return uploadImagesRepository.save(uploadImagesModel) ;
		int response = 0;
		try {
			int member_id = uploadImagesModel.getMember_id();
			String image_name = uploadImagesModel.getImage_name();
			String[] strArray = uploadImagesModel.getImage_base_urls();

			for (int i = 0; i < strArray.length; i++) {
				String base64Image = strArray[i].toString().split(",")[1];
				byte[] data = java.util.Base64.getDecoder().decode(base64Image);
				uploadImagesModel.setImage_url(data);
				uploadImagesModel.setImage_name(i + ".jpg");
				
				member_id = uploadImagesModel.getMember_id();
				image_name = uploadImagesModel.getImage_name();
				byte[] image_blob = uploadImagesModel.getImage_url();
				response = uploadImagesRepository.savePhoto(member_id, image_name, image_blob);
			}

			
			
            
//            String directory=
//            ************************ new code *******************************
//            try( OutputStream stream = new FileOutputStream("d:/saathidar_images"+i+".jpg") ) 
//            {
//               stream.write(data);
//            }
//            JSONObject jsonObject = new JSONObject();
//             jsonObject.put("image_code", base64Image);
//             
//            String data1=jsonObject.toString();
//            String yourURL = "http://www.saathidaar.com/assets/images";
//             URL url = new URL(yourURL);
//             HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//             connection.setDoOutput(true);
//             connection.setDoInput(true);
//             connection.setRequestMethod("POST");
//             connection.setFixedLengthStreamingMode(base64Image.getBytes().length);
//             connection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
//             OutputStream out = new BufferedOutputStream(connection.getOutputStream());
//             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out, "UTF-8"));
//             writer.write(data1);
//             writer.flush();
//             writer.close();
//             out.close();
//             connection.connect();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(" *************************** errorr in photo ");
		}
		return response;
	}

	@Override
	public JSONArray getMemberPhotos(String member_id) {
		JSONArray resultArray = new JSONArray();
		try {
			
			JSONObject json = new JSONObject();
//			Optional<UploadImagesModel> post = uploadImagesRepository.getByMember_Id(member_id);
			
			
			List<UploadImagesModel> post = uploadImagesRepository.getByMember_Id(member_id);
			if(post!=null) {
				for(int i=0;i<post.size();i++) {
					JSONObject jsonObj=new JSONObject();
					byte[] encodeBase64 = Base64.getEncoder().encode(post.get(i).getImage_url());
					String base64Encoded = new String(encodeBase64, "UTF-8");					
					jsonObj.put("member_images","data:image/jpeg;base64,"+ base64Encoded);
//					jsonObj.put("member_images",""+ base64Encoded);
					jsonObj.put("image_id",""+post.get(i).getId());
					resultArray.put(jsonObj);
				}
			}
			
			
			
			
//			System.out.println("member_id- " + member_id);
//			List results=uploadImagesRepository.getMemberPhotos(member_id);
//			if(results.isEmpty() || results.get(0) instanceof Object[]) {
//				List<Object[]> resultList = (List<Object[]>) results;
//				int i=0;
//				
//				for (Object[] obj: resultList) {
//					JSONObject json = new JSONObject();
//					byte[] data = java.util.Base64.getEncoder().encode(String.valueOf(obj[i]).getBytes());
//					json.put("member_images", String.valueOf(obj[i]));
//					resultArray.put(json);
//					System.out.println("multiple in- ");
//					i++;
//				}
//			}else {
//				List<Object> resultList = (List<Object>) results;
//				Blob getValue = (Blob) resultList.get(0);
//				int i = 0;
//				JSONObject json = new JSONObject();
//				byte[] data = java.util.Base64.getEncoder().encode(String.valueOf(obj[i]).getBytes());
//				json.put("member_images","data:image/jpeg;base64,"+ data);
//				resultArray.put(json);
//				System.out.println("single in- ");
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultArray;
	}

	@Override
	public int deleteImagesById(UploadImagesModel uploadImagesModel) {
		return uploadImagesRepository.deleteByPhotoID(uploadImagesModel.getId());
	}
}
