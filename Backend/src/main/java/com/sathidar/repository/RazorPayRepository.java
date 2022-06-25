package com.sathidar.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sathidar.model.RazorPayModel;

@Repository
public interface RazorPayRepository extends JpaRepository<RazorPayModel, Integer> {

	@Transactional
	@Modifying
	@Query(value="insert into premium_member (member_id,plan_id) values(:member_id,:plan_id) ",nativeQuery = true)
	int updatePremiumMemberDetails(String member_id, int plan_id);

}
