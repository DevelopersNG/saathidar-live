package com.sathidar.repository;


	import java.util.List;

	import javax.transaction.Transactional;

	import org.springframework.data.jpa.repository.JpaRepository;
	import org.springframework.data.jpa.repository.Modifying;
	import org.springframework.data.jpa.repository.Query;
	import org.springframework.stereotype.Repository;

	import com.sathidar.model.AdminKycModel;
	import com.sathidar.model.UploadImagesModel;



	@Repository
	public interface AdminUploadPhotoRepository  extends JpaRepository<AdminKycModel,Integer> {

//		@Transactional
//		@Modifying
//		@Query(value="insert into Kyc_document (id,image_name,image_url) values (:id,:image_name,:image_blob)",nativeQuery=true)
//		int savePhoto(int id, String image_name, byte[] image_blob);
//
//		@Query(value="select image_url from kyc_document where id= :id and deleteflag='N'",nativeQuery = true)
//		List getPhotos(String id);
//
//		@Query(value="select *  from kyc_document where member_id= :member_id and deleteflag='N'",nativeQuery = true)
//		List<AdminKycModel> getById(String id);
//
//	
//
//		@Transactional
//		@Modifying
//		@Query(value="delete from kyc_document where id= :id and deleteflag='Y'",nativeQuery = true)
//		int deleteByPhotoID(Integer kyc_id);
//
//		int savePhotos(String image_name, String saveFolderPath, int getid);
//
//		int deleteByPhotoIDDeleteFlagY(Integer id);
//
//		int deleteByPhotoIDDeleteFlagN(Integer photo_id);
//
//		List<UploadImagesModel> getPhoto_Id(String photo_id);

		//List<UploadImagesModel> getById(Object kyc_id);

		//int deleteByPhotoIDDeleteFlagN(Integer id);
	}

