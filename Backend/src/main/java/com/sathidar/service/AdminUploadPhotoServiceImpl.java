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
    import com.sathidar.model.AdminUploadPhotoModel;
    import com.sathidar.model.UploadImagesModel;
	import com.sathidar.repository.AdminKycRepository;
    import com.sathidar.repository.AdminUploadPhotoRepository;
    import com.sathidar.util.Constant;


		@Service
		public class AdminUploadPhotoServiceImpl implements AdminUploadPhotoService {

//			@Autowired
//			private AdminUploadPhotoRepository adminUploadPhotoRepository;
//		
//            @Autowired
//            private AdminUploadPhotoModel adminUploadPhotoModel;
//			
//
//			public int saveToImage( AdminUploadPhotoModel adminUploadPhotoModel) {
////				return uploadImagesRepository.save(uploadImagesModel) ;
//				int response = 0;
//				try {
//					int member_id = adminUploadPhotoModel.getkyc_id();
////					String image_name = uploadImagesModel.getImage_name();
//					String[] strArray = adminUploadPhotoModel.getImage_base_urls();
//					for (int i = 0; i < strArray.length; i++) {
//						Random random = new Random();
//						String id = String.format("%04d", random.nextInt(10000));
//						adminUploadPhotoModel.setImage_name("saathidar" + id + ".jpg");
//						
//						Constant constant = new Constant();
//
//						String uploadDir = constant.image_path + "/" + adminUploadPhotoModel.getkyc_id();
//						uploadDir = System.getProperty("catalina.base") + "/webapps";
//						
//						String saveFolderPath = "/member_images/" + adminUploadPhotoModel.getkyc_id() + "/" + adminUploadPhotoModel.getImage_name();
//
//						uploadDir = uploadDir + "/member_images/" + adminUploadPhotoModel.getkyc_id() + "";
//
//						File theDir = new File(uploadDir);
//						if (!theDir.exists()) {
//							theDir.mkdirs();
//						}
//
//						String base64Image = strArray[i].toString().split(",")[1];
//						byte[] data = java.util.Base64.getDecoder().decode(base64Image);
//					
//						int status = adminUploadPhotoRepository.savePhotos(adminUploadPhotoModel.getImage_name(),
//								saveFolderPath, adminUploadPhotoModel.getkyc_id());
//						if (status > 0) {
//							try (OutputStream stream = new FileOutputStream(uploadDir + "/" + adminUploadPhotoModel.getImage_name())) {
//								stream.write(data);
//							}
//						}	
//						response=1;
//					}
//				} catch (Exception e) {
//					e.printStackTrace();
//					System.out.println(" *************************** errorr in photo ");
//				}
//				return response;
//			}
//			
//			@Override
//			public int deleteImages(AdminUploadPhotoModel adminUploadPhotoModel) {
//				return adminUploadPhotoRepository.deleteByPhotoIDDeleteFlagN(adminUploadPhotoModel.getId());
//			}
//
//			@Override
//			public JSONArray getPhotos(String photo_id) {
//				JSONArray resultArray = new JSONArray();
//				try {
//
//					JSONObject json = new JSONObject();
////					Optional<UploadImagesModel> post = uploadImagesRepository.getByMember_Id(member_id);
//
//					List<UploadImagesModel> post = adminUploadPhotoRepository.getPhoto_Id(photo_id);
//					if (post != null) {
//						for (int i = 0; i < post.size(); i++) {
//							JSONObject jsonObj = new JSONObject();
//							byte[] encodeBase64 = Base64.getEncoder().encode(post.get(i).getImage_url());
//							String base64Encoded = new String(encodeBase64, "UTF-8");
//							jsonObj.put("member_images", "data:image/jpeg;base64," + base64Encoded);
//							jsonObj.put("image_id", "" + post.get(i).getImage_id());
//							resultArray.put(jsonObj);
//						}
//					}
//					System.out.println("  photo id- " + photo_id);
//
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//				return resultArray;
//			}
//
//			@Override
//			public JSONArray getMemberPhotos1(String member_id) {
//				// TODO Auto-generated method stub
//				return null;
//			}
//
//			@Override
//			public int deleteImagesById(AdminUploadPhotoModel uploadImagesModel) {
//				// TODO Auto-generated method stub
//				return 0;
//			}
//
//			@Override
//			public JSONArray getMemberPhotos(String kyc_id) {
//				// TODO Auto-generated method stub
//				return null;
//			}
//
//			@Override
//			public JSONArray getApprovedPhotos(String photo_id) {
//				// TODO Auto-generated method stub
//				return null;
//			}
//
//			@Override
//			public AdminUploadPhotoModel UploadPhoto(AdminUploadPhotoModel adminUploadPhotoModel,
//					MultipartFile multipartFile) {
//				// TODO Auto-generated method stub
//				return null;
//			}
//
//			@Override
//			public AdminUploadPhotoModel ApprovePhoto(AdminUploadPhotoModel adminUploadPhotoModel,
//					MultipartFile multipartFile) {
//				// TODO Auto-generated method stub
//				return null;
//			}

			
		}