package com.sathidar.service;

    import org.json.JSONArray;
import org.springframework.web.multipart.MultipartFile;

import com.sathidar.model.AdminKycModel;

	public interface AdminKycService {

		public default int saveToImage(AdminKycModel aminKycModel) {
			// TODO Auto-generated method stub
			return 0;
		}

		int deleteImagesById(AdminKycModel adminKycModel);



		int deleteImages(AdminKycModel adminKycModel);

		JSONArray getkycPhotos(String kyc_id);

		JSONArray getKycPhotos(String member_id);

		AdminKycModel uploadKYCImages(AdminKycModel adminKycModel, MultipartFile multipartFile);

	

	}



