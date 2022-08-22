package com.sathidar.service;
	import java.io.File;
	import java.io.FileOutputStream;
	import java.io.OutputStream;
	import java.util.Base64;
	import java.util.List;
	import java.util.Random;

	import org.json.JSONArray;
	import org.json.JSONObject;
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sathidar.model.AdminKycModel;
	import com.sathidar.model.UploadImagesModel;
	import com.sathidar.repository.AdminKycRepository;
	import com.sathidar.util.Constant;


		@Service
		public class AdminKycServiceImpl implements AdminKycService {

			@Autowired
			private AdminKycRepository adminKycRepository;
		

			

			public int saveToImage(AdminKycModel adminKycModel) {
//				return uploadImagesRepository.save(uploadImagesModel) ;
				int response = 0;
				try {
					int member_id = adminKycModel.getId();
//					String image_name = uploadImagesModel.getImage_name();
					String[] strArray = adminKycModel.getImage_base_urls();
					for (int i = 0; i < strArray.length; i++) {
						Random random = new Random();
						String id = String.format("%04d", random.nextInt(10000));
						adminKycModel.setDocument_name("saathidar" + id + ".jpg");
						
						Constant constant = new Constant();

						String uploadDir = constant.image_path + "/" + adminKycModel.getId();
						uploadDir = System.getProperty("catalina.base") + "/webapps";
						
						String saveFolderPath = "/member_images/" + adminKycModel.getId() + "/" + adminKycModel.getDocument_name();

						uploadDir = uploadDir + "/member_images/" + adminKycModel.getId() + "";

						File theDir = new File(uploadDir);
						if (!theDir.exists()) {
							theDir.mkdirs();
						}

						String base64Image = strArray[i].toString().split(",")[1];
						byte[] data = java.util.Base64.getDecoder().decode(base64Image);
					
						int status = adminKycRepository.savekycPhotos(adminKycModel.getDocument_name(),
								saveFolderPath, adminKycModel.getId());
						if (status > 0) {
							try (OutputStream stream = new FileOutputStream(uploadDir + "/" + adminKycModel.getDocument_name())) {
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
			public int deleteImages(AdminKycModel adminKycModel) {
				return adminKycRepository.deleteByPhotoIDDeleteFlagN(adminKycModel.getId());
			}

			@Override
			public JSONArray getkycPhotos(String id) {
				JSONArray resultArray = new JSONArray();
				try {

					JSONObject json = new JSONObject();
//					Optional<UploadImagesModel> post = uploadImagesRepository.getByMember_Id(member_id);

					List<AdminKycModel> post = adminKycRepository.getById(id);
					if (post != null) {
						for (int i = 0; i < post.size(); i++) {
							JSONObject jsonObj = new JSONObject();
							byte[] encodeBase64 = Base64.getEncoder().encode(post.get(i).getImage_url());
							String base64Encoded = new String(encodeBase64, "UTF-8");
							jsonObj.put("member_images", "data:image/jpeg;base64," + base64Encoded);

							jsonObj.put("image_id", "" + post.get(i).getId());
							resultArray.put(jsonObj);
						}
					}
					System.out.println("  kyc id- " + id);

				} catch (Exception e) {
					e.printStackTrace();
				}
				return resultArray;
			}

			@Override
			public int deleteImagesById(AdminKycModel adminKycModel) {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public JSONArray getKycPhotos(String member_id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public AdminKycModel uploadKYCImages(AdminKycModel adminKycModel, MultipartFile multipartFile) {
				// TODO Auto-generated method stub
				return null;
			}	
		}	
		
			
		

