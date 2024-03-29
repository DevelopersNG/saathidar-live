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

//	@Transactional
//	@Modifying
//	@Query(value="insert into premium_member (member_id,plan_id) values(:member_id,:plan_id) ",nativeQuery = true)
//	int updatePremiumMemberDetails(String member_id, int plan_id);

	
	@Query(value="select plan_name,plan_validity,plan_price from plans where plan_id= :plan_id and plan_status='ACTIVE'",nativeQuery = true)
	List<Object[]> getMemberPlanDetailsForMail(String plan_id);

	@Transactional
	@Query(value="SELECT first_name,last_name,email_id FROM member where member_id= :request_to_id",nativeQuery = true)
	List<Object[]> getUserNameEmailId(Integer request_to_id);


	@Transactional
	@Modifying
	@Query(value="update member set plan_id= :plan_id where member_id= :member_id ",nativeQuery = true)
	int updatePremiumMemberDetailsInMemberTable(String member_id, int plan_id);

	@Transactional
	@Modifying
	@Query(value="insert into premium_member (member_id,plan_id,razorpay_order_id,razorpay_payment_id,razorpay_signature) values(:member_id,:plan_id,:razorpay_order_id,:razorpay_payment_id,:razorpay_signature) ",nativeQuery = true)
	int updatePremiumMemberDetails(String member_id, int plan_id, String razorpay_order_id, String razorpay_payment_id,
			String razorpay_signature);

}
