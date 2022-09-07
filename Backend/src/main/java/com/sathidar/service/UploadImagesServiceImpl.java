package com.sathidar.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Blob;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.sql.rowset.serial.SerialBlob;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.sathidar.model.UploadDocumentModel;
import com.sathidar.model.UploadImagesModel;
import com.sathidar.repository.UploadDocumentRepository;
import com.sathidar.repository.UploadImagesRepository;
import com.sathidar.util.FileUploadUtil;
import com.sathidar.util.Constant;

@Service
public class UploadImagesServiceImpl implements UploadImagesService {

	@Autowired
	private UploadImagesRepository uploadImagesRepository;

	@Autowired
	private UploadDocumentRepository uploadDocumentRepository;

	@Override
	public int saveKYCToImage(UploadDocumentModel uploadDocumentModel) {
		int response = 0;
		try {
			int member_id = uploadDocumentModel.getMember_id();
//			String image_name = uploadImagesModel.getImage_name();
			String[] strArray = uploadDocumentModel.getImage_base_urls();
			for (int i = 0; i < strArray.length; i++) {
				Random random = new Random();
				String id = String.format("%04d", random.nextInt(10000));
//				uploadDocumentModel.setDocument_name(uploadDocumentModel.getDocument_type()+ id + ".jpg");
				
				Constant constant = new Constant();

				String uploadDir = constant.image_path + "/" + uploadDocumentModel.getMember_id();
				uploadDir = System.getProperty("catalina.base") + "/webapps";
				
				String saveFolderPath = "/member_images/" + uploadDocumentModel.getMember_id() + "/" + uploadDocumentModel.getDocument_name();

				uploadDir = uploadDir + "/member_images/" + uploadDocumentModel.getMember_id() + "";

				File theDir = new File(uploadDir);
				if (!theDir.exists()) {
					theDir.mkdirs();
				}

				String base64Image = strArray[i].toString().split(",")[1];
				byte[] data = java.util.Base64.getDecoder().decode(base64Image);
				int status = uploadDocumentRepository.saveKYCMemberPhotos(uploadDocumentModel.getDocument_name(),
						saveFolderPath, uploadDocumentModel.getMember_id(),uploadDocumentModel.getDocument_type());
				if (status > 0) {
					try (OutputStream stream = new FileOutputStream(uploadDir + "/" + uploadDocumentModel.getDocument_name())) {
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
	public int saveToImage(UploadImagesModel uploadImagesModel) {
//		return uploadImagesRepository.save(uploadImagesModel) ;
		int response = 0;
		try {
			int member_id = uploadImagesModel.getMember_id();
//			String image_name = uploadImagesModel.getImage_name();
			String[] strArray = uploadImagesModel.getImage_base_urls();
			for (int i = 0; i < strArray.length; i++) {
				Random random = new Random();
				String id = String.format("%04d", random.nextInt(10000));
				uploadImagesModel.setImage_name("saathidar" + id + ".jpg");
				
				Constant constant = new Constant();

				String uploadDir = constant.image_path + "/" + uploadImagesModel.getMember_id();
				uploadDir = System.getProperty("catalina.base") + "/webapps";
				
				String saveFolderPath = "/member_images/" + uploadImagesModel.getMember_id() + "/" + uploadImagesModel.getImage_name();

				uploadDir="/opt/tomcat/webapps";
				uploadDir = uploadDir + "/member_images/" + uploadImagesModel.getMember_id() + "";

				File theDir = new File(uploadDir);
				if (!theDir.exists()) {
					theDir.mkdirs();
				}
				
				String base64Image = strArray[i].toString().split(",")[1];
				byte[] data = java.util.Base64.getDecoder().decode(base64Image);
			
				int status = uploadImagesRepository.saveMemberPhotos(uploadImagesModel.getImage_name(),
						saveFolderPath, uploadImagesModel.getMember_id());
				if (status > 0) {
					try (FileOutputStream stream = new FileOutputStream(uploadDir + "/" + uploadImagesModel.getImage_name())) {
						stream.write(data);
					}
				}		
			response=1;
		}
			// working code base64
//			for (int i = 0; i < strArray.length; i++) {
//				String base64Image = strArray[i].toString().split(",")[1];
//				System.out.println("base64Image - " + base64Image);
//				byte[] data = java.util.Base64.getDecoder().decode(base64Image);
//				uploadImagesModel.setImage_url(data);
//				uploadImagesModel.setImage_name(i + ".jpg");
//				
//				byte[] image_blob = uploadImagesModel.getImage_url();
//				response = uploadImagesRepository.savePhoto(member_id, image_name, image_blob);
//			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(" *************************** errorr in photo ");
		}
		return response;
	}

	@Override
	public JSONArray getMemberAppPhotos(String member_id) {
		JSONArray resultArray = new JSONArray();
		try {
			JSONObject json = new JSONObject();
			List<UploadImagesModel> post = uploadImagesRepository.getByMember_Id(member_id);
			if (post != null) {
				for (int i = 0; i < post.size(); i++) {
					JSONObject jsonObj = new JSONObject();
					jsonObj.put("member_images", post.get(i).getImage_path());
					jsonObj.put("image_id", "" + post.get(i).getId());
					jsonObj.put("photo_status", "" + post.get(i).getPhoto_status());
					resultArray.put(jsonObj);
				}
			}else {
				resultArray=null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultArray=null;
		}
		return resultArray;
	}
	
	@Override
	public JSONArray getMemberPhotos(String member_id) {
		JSONArray resultArray = new JSONArray();
		try {

			JSONObject json = new JSONObject();
//			Optional<UploadImagesModel> post = uploadImagesRepository.getByMember_Id(member_id);

			List<UploadImagesModel> post = uploadImagesRepository.getByMember_Id(member_id);
			if (post != null) {
				for (int i = 0; i < post.size(); i++) {
					JSONObject jsonObj = new JSONObject();
					byte[] encodeBase64 = Base64.getEncoder().encode(post.get(i).getImage_url());
					String base64Encoded = new String(encodeBase64, "UTF-8");
					jsonObj.put("member_images", "data:image/jpeg;base64," + base64Encoded);
//					jsonObj.put("member_images",""+ base64Encoded);
					jsonObj.put("image_id", "" + post.get(i).getId());
					jsonObj.put("photo_status", "" + post.get(i).getPhoto_status());
					resultArray.put(jsonObj);
				}
			}
			System.out.println("  member id- " + member_id);

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

	@Override
	public UploadImagesModel uploadImages(UploadImagesModel uploadImagesModel, MultipartFile multipartFile) {
		
		Constant constant=new Constant();
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		uploadImagesModel.setImage_name(fileName);
		
		String uploadDir = constant.image_path  + "/" + uploadImagesModel.getMember_id();
		uploadDir = System.getProperty("catalina.base") +"/webapps";
		
//		uploadDir="/home/httpsaathidaar/public_html";
		String saveFolderPath="/member_images/"+uploadImagesModel.getMember_id()+"/"+fileName;
		
		uploadImagesModel.setImage_path(saveFolderPath);
//		System.out.println("tomcat path "+uploadDir );
		int status = uploadImagesRepository.saveMemberPhotos(uploadImagesModel.getImage_name(),saveFolderPath,uploadImagesModel.getMember_id());
		
		uploadDir=uploadDir+"/member_images/"+uploadImagesModel.getMember_id()+"";
		
		System.out.println("path - "+uploadDir );
		if (status != 0) {
			FileUploadUtil fileUploadUtil = new FileUploadUtil();
			try {
				
				File theDir = new File(uploadDir);
				if (!theDir.exists()){
				    theDir.mkdirs();
				}
				
				fileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
			} catch (IOException e) {
				e.printStackTrace();
				uploadImagesModel = null;
			}
		}else {
			uploadImagesModel = null;
		}
		return uploadImagesModel;
	}


	@Override
	public int deleteImages(UploadImagesModel uploadImagesModel) {
		return uploadImagesRepository.deleteByPhotoIDDeleteFlagY(uploadImagesModel.getId());
	}


	@Override
	public int setMemberProfilePhoto(UploadImagesModel uploadImagesModel) {
		return uploadImagesRepository.saveProfilePhto(uploadImagesModel.getMember_id(),uploadImagesModel.getImage_id());
	}


	@Override
	public String getMemberProfilePhotoPath(String image_id) {
		return uploadImagesRepository.getMemberProfilePhotoPath(image_id);
	}


	@Override
	public String getMemberProfilePhotoID(String member_id) {
		return uploadImagesRepository.getMemberProfilePhotoID(member_id);
	}


	@Override
	public int getPremiumMemberStatus(String memberID) {
		return uploadImagesRepository.getPremiumMemberStatus(memberID);
	}


	@Override
	public String getPhotoPrivacySettings(String memberID) {
		return uploadImagesRepository.getPhotoPrivacySettings(memberID);
	}


	@Override
	public int getShortListStatus(String from_Id,String thisMemberID) {
		return uploadImagesRepository.getShortListStatus(from_Id,thisMemberID);
	}


	@Override
	public int getVisitorsStatus(int login_id, int id) {
		return uploadImagesRepository.getVisitorsStatus(login_id,id);
	}

	@Override
	public UploadDocumentModel uploadKYCImages(UploadDocumentModel uploadDocumentModel, MultipartFile multipartFile) {
		Constant constant=new Constant();
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		uploadDocumentModel.setDocument_name(fileName);
		
		String uploadDir = constant.image_path  + "/" + uploadDocumentModel.getMember_id();
		uploadDir = System.getProperty("catalina.base") +"/webapps";
		String saveFolderPath="/member_images/"+uploadDocumentModel.getMember_id()+"/"+fileName;
		
		uploadDocumentModel.setDocument_path(saveFolderPath);
//		System.out.println("tomcat path "+uploadDir );
		int status = uploadDocumentRepository.saveKYCMemberPhotos(uploadDocumentModel.getDocument_name(),saveFolderPath,uploadDocumentModel.getMember_id(),uploadDocumentModel.getDocument_type());
		
		uploadDir=uploadDir+"/member_images/"+uploadDocumentModel.getMember_id()+"";
		if (status != 0) {
			FileUploadUtil fileUploadUtil = new FileUploadUtil();
			try {
				File theDir = new File(uploadDir);
				if (!theDir.exists()){
				    theDir.mkdirs();
				}
				
				fileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
			} catch (IOException e) {
				e.printStackTrace();
				uploadDocumentModel = null;
			}
		}else {
			uploadDocumentModel = null;
		}
		return uploadDocumentModel;
	}


	@Override
	public JSONArray getKYCMemberPhoto(String member_id) {
		JSONArray resultArray = new JSONArray();
		Constant constant=new Constant();
		try {
			JSONObject json = new JSONObject();
			List<UploadDocumentModel> post = uploadDocumentRepository.getByKYCMember_Id(member_id);
			if (post != null) {
				for (int i = 0; i < post.size(); i++) {
					JSONObject jsonObj = new JSONObject();
//					byte[] encodeBase64 = Base64.getEncoder().encode(post.get(i).getImage_url());
//					String base64Encoded = new String(encodeBase64, "UTF-8");
//					jsonObj.put("member_images", "data:image/jpeg;base64," + base64Encoded);
					jsonObj.put("document_id", "" + post.get(i).getId());
					jsonObj.put("document_name", "" + post.get(i).getDocument_name());
					jsonObj.put("document_path", constant.document_path +  post.get(i).getDocument_path());
					jsonObj.put("document_type", "" + post.get(i).getDocument_type());
					jsonObj.put("kyc_status", "" + post.get(i).getKyc_status());
					resultArray.put(jsonObj);
				}
			}else{
				resultArray=null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultArray=null;
		}
		return resultArray;
	}

	@Override
	public int  deleteMemberplanDetails(int member_id) {
		return uploadImagesRepository.deleteByPlanID(member_id);
		
	}

	@Override
	public String getDateOfBirthFromMemberDetailsTable(int member_id) {
		return uploadImagesRepository.getDateOfBirthFromMemberDetailsTable(member_id);
	}

	@Override
	public String getPhonePrivacySettings(String memberID) {
		return uploadImagesRepository.getPhonePrivacySettings(memberID);
	}

	@Override
	public String getEmailPrivacySettings(String thisMemberID) {
		return uploadImagesRepository.getEmailPrivacySettings(thisMemberID);
	}

	@Override
	public String getDOBPrivacySettings(String thisMemberID) {
		return uploadImagesRepository.getDOBPrivacySettings(thisMemberID);
	}

	@Override
	public String getAnnualIncomePrivacySettings(String thisMemberID) {
		return uploadImagesRepository.getAnnualIncomePrivacySettings(thisMemberID);
	}

	@Override
	public int checkMemberIdAvailable(String member_id) {
		return uploadImagesRepository.checkMemberIdAvailable(member_id);
	}
}
