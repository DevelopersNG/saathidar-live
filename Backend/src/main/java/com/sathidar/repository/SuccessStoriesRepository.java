package com.sathidar.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sathidar.model.SuccessStoriesModel;

@Repository
public interface SuccessStoriesRepository extends JpaRepository<SuccessStoriesModel,Integer>{
	@Transactional
	@Modifying
	@Query(value="insert into success_story (description,success_photo) values (:description,:saveFolderPath)",nativeQuery=true)
	int saveMemberPhotos(String description, String saveFolderPath);

	
	@Transactional
	@Modifying
	@Query(value="update success_story set deleteflag='Y' where id= :id ",nativeQuery = true)
	int deleteByPhotoIDDeleteFlagY(Integer id);


	antlr.collections.List getById();

}
