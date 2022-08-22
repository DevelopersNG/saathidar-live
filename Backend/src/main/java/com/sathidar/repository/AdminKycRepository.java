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
	public interface AdminKycRepository extends JpaRepository<AdminKycModel,Integer> {

		@Transactional
		@Modifying
		@Query(value="insert into admin_kyc (kyc_id,image_name,image_url) values (:kyc_id,:image_name,:image_blob)",nativeQuery=true)
		int savePhoto(int kyc_id, String image_name, byte[] image_blob);

		@Query(value="select image_url from admin_kyc where kyc_id= :kyc_id and deleteflag='N'",nativeQuery = true)
		List getKycPhotos(String kyc_id);

		@Query(value="select *  from admin_kyc where member_id= :member_id and deleteflag='N'",nativeQuery = true)
		List<UploadImagesModel> getByKyc_Id(String kyc_id);

		@Transactional
		@Modifying
		@Query(value="update memberdetails set profile_photo_id= :image_id where member_id= :member_id ",nativeQuery = true)
		int saveKycPhoto(Integer _id, String image_id);

		@Transactional
		@Modifying
		@Query(value="delete from admin_kyc where kyc_id= :kyc_id and deleteflag='N'",nativeQuery = true)
		int deleteByPhotoID(Integer kyc_id);

		int savekycPhotos(String image_name, String saveFolderPath, int getid);

		int deleteByPhotoIDDeleteFlagY(Integer id);

		List<UploadImagesModel> getByKyc_Id(Object kyc_id);

		int deleteByPhotoIDDeleteFlagN(Integer id);

		List<AdminKycModel> getById(String id);
	}


