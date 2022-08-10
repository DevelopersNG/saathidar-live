package com.sathidar.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.sathidar.model.UploadDocumentModel;

@Repository
public interface UploadDocumentRepository  extends JpaRepository<UploadDocumentModel,Integer> {

//	below code for kyc photo
	@Transactional
	@Modifying
	@Query(value="insert into kyc_document (member_id,document_name,document_path,document_type) values (:member_id,:document_name,:document_path,:document_type)",nativeQuery=true)
	int saveKYCMemberPhotos(String document_name, String document_path, Integer member_id, String document_type);

	@Query(value="select * from kyc_document where member_id= :member_id and deleteflag='N' order by id desc limit 1",nativeQuery = true)
	List<UploadDocumentModel> getByKYCMember_Id(String member_id);

}
