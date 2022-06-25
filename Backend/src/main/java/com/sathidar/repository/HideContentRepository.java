package com.sathidar.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sathidar.model.HideContentModel;

@Repository
public interface HideContentRepository extends JpaRepository<HideContentModel, Integer>{
	
	@Transactional
	@Modifying
	@Query(value=" insert into hide_content (column_name,member_id) values (:columnName,:member_id)",nativeQuery = true)
	Object setHideContent(String columnName, int member_id);

	@Transactional
	@Modifying
	@Query(value="update hide_content set status='DEACTIVATE' where member_id=:memberID and status='ACTIVE' ",nativeQuery = true)
	Object deleteHideContent(int memberID);

	@Query(value="select count(*) from hide_content where member_id=:member_id and status='ACTIVE'",nativeQuery = true)
	int checkDuplicate(int member_id);	
	
	@Transactional
	@Modifying
	@Query(value=" update hide_content set column_name= :columnName where member_id= :member_id and status='ACTIVE'",nativeQuery = true)
	Object updateHideContent(String columnName, int member_id);

}
