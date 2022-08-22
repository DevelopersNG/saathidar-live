package com.sathidar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sathidar.model.PlanDetailsModel;


public interface PlanDetailsRepository  extends JpaRepository<PlanDetailsModel, Integer> { 
		@Query(value="select p.plan_name,p.plan_amount,p.plan_validity, from plans p, where plan_id= :plan_id and deleteflag='N'",nativeQuery =true )
			int getpanDetails1(int plan_id);

//		@Query(value="select (p.plan_id,pl.id) from plans p,join fetch plan_list pl where plan_id= :plan_id and plan_status='1'",nativeQuery =true )
//			int getpanDetails(int plan_id);
//		
//		@Query(value="select (pl.id) from plan p,join fetch from member,pl.(member_id)member_details,member_education_details,member_horoscope,member_photo where member_id= :member_id and status='ACTIVE'",nativeQuery =true )
//		int getPlanDetails(int plan_id);
//			
//		PlanDetailsModel save(PlanDetailsModel planDetailsModel);
}
