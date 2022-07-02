package com.sathidar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sathidar.model.UploadImagesModel;

@Repository
public interface UploadImagesRepository extends JpaRepository<UploadImagesModel,Integer> {

}
