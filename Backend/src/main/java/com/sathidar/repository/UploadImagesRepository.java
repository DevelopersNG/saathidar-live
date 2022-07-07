package com.sathidar.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sathidar.model.UploadImagesModel;

@Repository
public interface UploadImagesRepository extends JpaRepository<UploadImagesModel,Integer> {

	@Transactional
	@Modifying
	@Query(value="insert into member_photo (member_id,image_name,image_url) values (:member_id,:image_name,:image_blob)",nativeQuery=true)
	int savePhoto(int member_id, String image_name, byte[] image_blob);

	@Query(value="select image_url from member_photo where member_id= :member_id",nativeQuery = true)
	List getMemberPhotos(String member_id);

	@Query(value="select *  from member_photo where member_id= :member_id",nativeQuery = true)
	List<UploadImagesModel> getByMember_Id(String member_id);
}
