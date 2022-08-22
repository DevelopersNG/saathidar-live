package com.sathidar.service;

    import org.json.JSONArray;
import org.springframework.web.multipart.MultipartFile;

import com.sathidar.model.AdminKycModel;

	public interface AdminKycService {
//
//		int deleteImages(AdminKycModel adminKycModel);
//
//		AdminKycModel uploadKYCImages(AdminKycModel adminKycModel, MultipartFile multipartFile);
//
//		JSONArray getKycPhotos(String kyc_id);

		int rejectKYCPhoto(AdminKycModel adminKycModel);

		int approveKYCPhoto(AdminKycModel adminKycModel);
	}



