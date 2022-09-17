package com.sathidar.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.sathidar.model.ShortListsModel;

public interface ShortListsRepository extends JpaRepository<ShortListsModel, Integer>{

	@Transactional
	@Modifying
	@Query(value="insert into member_shortlists (shortlist_from_id,shortlist_to_id,shortlist_status) values (:shortlist_from_id,:shortlist_to_id,:shortlist_status)",nativeQuery = true)
	int addToShortListMember(String shortlist_from_id, String shortlist_to_id, String shortlist_status);

	
	@Transactional
	@Modifying
	@Query(value="update member_shortlists set shortlist_status= :shortlist_status where shortlist_from_id= :shortlist_from_id and shortlist_to_id= :shortlist_to_id and shortlist_status='add'",nativeQuery = true)
	int removeToShortListMember(String shortlist_from_id, String shortlist_to_id, String shortlist_status);

}
