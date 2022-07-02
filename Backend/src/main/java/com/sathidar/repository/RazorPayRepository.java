package com.sathidar.repository;

import java.util.List;

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

	
	@Query(value="select plan_name,plan_validity,plan_price from plans where plan_id= :plan_id and plan_status='ACTIVE'",nativeQuery = true)
	List<Object[]> getMemberPlanDetailsForMail(String plan_id);

	@Transactional
	@Query(value="SELECT first_name,last_name,email_id FROM member where member_id= :request_to_id",nativeQuery = true)
	List<Object[]> getUserNameEmailId(Integer request_to_id);

}
