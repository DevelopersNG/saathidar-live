package com.sathidar.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sathidar.model.SearchMembersModel;


@Repository
public interface SearchMemberRepository extends JpaRepository<SearchMembersModel, Integer> {

	@Query(value="select count(*) from search_members where member_id= :member_id and status='ACTIVE'",nativeQuery =true )
	int checkAvailablePartnerPreference(int member_id);

	
	@Transactional
	@Modifying
	@Query(value="update search_members set search_marital_status= :search_marital_status,search_mother_tongue= :search_mother_tongue,"
			+ "search_religions= :search_religions,search_cast= :search_cast,search_country= :search_country,search_state= :search_state,search_city= :search_city,"
			+ "search_from_age= :search_from_age,search_to_age= :search_to_age,search_from_height=:search_from_height,search_to_height=:search_to_height "
			+ "where member_id= :member_id",nativeQuery = true)
	Object updateNewMemberPreference(int member_id, String search_marital_status, String search_mother_tongue,
			String search_religions, String search_cast, String search_country, String search_state,
			String search_city, String search_from_age, String search_to_age, String search_from_height,
			String search_to_height);

	
	@Transactional
	@Modifying
	@Query(value="insert into search_members (member_id,search_marital_status,search_mother_tongue,"
			+ "search_religions,search_cast,search_country,search_state," + 
			"   search_city,search_from_age,search_to_age,search_from_height,search_to_height) "
			+ "values"
			+ " (:member_id,:search_marital_status, :search_mother_tongue,"
			+ ":search_religions,:search_cast,:search_country,:search_state,:search_city,"
			+ ":search_from_age,:search_to_age,:search_from_height,:search_to_height)",nativeQuery = true)
	Object insertNewMemberPreference(int member_id, String search_marital_status, String search_mother_tongue,
			String search_religions, String search_cast, String search_country, String search_state,
			String search_city, String search_from_age, String search_to_age, String search_from_height,
			String search_to_height);

}
