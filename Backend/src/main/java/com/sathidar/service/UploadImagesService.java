package com.sathidar.service;

import org.json.JSONArray;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sathidar.model.UploadDocumentModel;
import com.sathidar.model.UploadImagesModel;

@Service
public interface UploadImagesService {

	int saveToImage(UploadImagesModel uploadImagesModel);

	JSONArray getMemberPhotos(String member_id);

	int deleteImagesById(UploadImagesModel uploadImagesModel);

	UploadImagesModel uploadImages(UploadImagesModel uploadImagesModel, MultipartFile multipartFile);

	JSONArray getMemberAppPhotos(String member_id);

	int deleteImages(UploadImagesModel uploadImagesModel);

	int setMemberProfilePhoto(UploadImagesModel uploadImagesModel);

	String getMemberProfilePhotoPath(String profile_photo_id);

	String getMemberProfilePhotoID(String string);

	int getPremiumMemberStatus(String memberID);

	String getPhotoPrivacySettings(String memberID);

	int getShortListStatus(String from_id,String to_id);

	int getVisitorsStatus(int login_id, int id);

	UploadDocumentModel uploadKYCImages(UploadDocumentModel uploadImagesModel, MultipartFile multipartFile);

	JSONArray getKYCMemberPhoto(String member_id);

	int saveKYCToImage(UploadDocumentModel uploadImagesModel);

	int deleteMemberplanDetails(int member_id);

	String getDateOfBirthFromMemberDetailsTable(int member_id);

	String getPhonePrivacySettings(String thisMemberID);

	String getEmailPrivacySettings(String thisMemberID);

	String getDOBPrivacySettings(String thisMemberID);

	String getAnnualIncomePrivacySettings(String thisMemberID);

	int checkMemberIdAvailable(String splitGetMemberID);

	String getPLanName(String memberID);

	JSONArray getMyMemberAppPhotos(String string);

	String getPremiumDate(String memberID);
}
