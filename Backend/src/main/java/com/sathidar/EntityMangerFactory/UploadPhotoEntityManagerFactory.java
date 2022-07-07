package com.sathidar.EntityMangerFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;

import com.sathidar.model.UploadImagesModel;

@Service
public class UploadPhotoEntityManagerFactory {

	@PersistenceContext
	private EntityManager em;
	
	public String uploadPhotos(UploadImagesModel uploadImagesModel) {
		String results="";
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}
}
