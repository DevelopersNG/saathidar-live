package com.sathidar.model;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sathidar.model.AddAdvertisement;



@Repository
public interface AddAdvertisementRepository extends JpaRepository<AddAdvertisement, Integer>{

	
	

	@Transactional
	@Modifying
	@Query(value="insert into advertisement (description,advt_photo) values (:description,:saveFolderPath)",nativeQuery=true)
	int saveMemberPhotos(String description, String saveFolderPath);

	@Transactional
	@Modifying
	@Query(value="update advertisement set deleteflag='Y' where id= :id ",nativeQuery = true)
	int deleteByPhotoIDDeleteFlagY(Integer id);

	@Query(value="select *  from advertisement where deleteflag='N'",nativeQuery = true)
	List<AddAdvertisement> getById();
	
}