package com.sathidar.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sathidar.model.PlanDetailsModel;

@Repository
public interface PlanDetailsRepository  extends JpaRepository<PlanDetailsModel, Integer> { 
		@Query(value="select p.plan_name,p.plan_amount,p.plan_validity, from plans p, where plan_id= :plan_id and deleteflag='N'",nativeQuery =true )
			int getpanDetails1(int plan_id);

		@Transactional
		@Modifying
		@Query(value="update plan_list set valid=1 where id= :feature_id",nativeQuery = true)
		int setValidFeatureID(String feature_id);

		@Transactional
		@Modifying
		@Query(value="update plan_list set valid=0 where id= :feature_id",nativeQuery = true)
		int setInValidFeatureID(String feature_id);
//		@Query(value="select (p.plan_id,pl.id) from plans p,join fetch plan_list pl where plan_id= :plan_id and plan_status='1'",nativeQuery =true )
//			int getpanDetails(int plan_id);
//		
//		@Query(value="select (pl.id) from plan p,join fetch from member,pl.(member_id)member_details,member_education_details,member_horoscope,member_photo where member_id= :member_id and status='ACTIVE'",nativeQuery =true )
//		int getPlanDetails(int plan_id);
//			
//		PlanDetailsModel save(PlanDetailsModel planDetailsModel);
		
		@Transactional
		@Modifying
		@Query(value="update plan_list set features= :feature_name where id= :feature_id",nativeQuery = true)
		int setInValidFeatureID(String feature_id, String feature_name);

		@Transactional
		@Modifying
		@Query(value="update plans set plan_name= :plan_name,plan_validity= :plan_validity,plan_price= :plan_price,plan_discount= :plan_discount,discount_price= :discount_price where plan_id= :plan_id",nativeQuery = true)
		int updatePlanDetails(int plan_id, String plan_name, String plan_validity, String plan_price,String plan_discount,String discount_price);

		@Query(value="select count(*) from plans where plan_name= :plan_name and plan_status='ACTIVE'",nativeQuery =true )
		int isAvailablePlan(String plan_name);

		
		@Query(value="select count(*) from plans where plan_name= :plan_name and plan_id!= :plan_id and plan_status='ACTIVE'",nativeQuery =true )
		int isAvailablePlanForUpdate(String plan_name,int plan_id);

		@Transactional
		@Modifying
		@Query(value="insert into plans (plan_name,plan_validity,plan_price,plan_discount,discount_price) values (:plan_name,:plan_validity,:plan_price,:plan_discount,:discount_price)",nativeQuery = true)
		int insertPlanDetails(String plan_name, String plan_validity, String plan_price,String plan_discount,String discount_price);

		@Transactional
		@Modifying
		@Query(value="update plans set  plan_status='De-Active' where plan_id= :plan_id",nativeQuery = true)
		int deletePlanDetails(int plan_id);

		@Query(value="select plan_id from plans where plan_name= :plan_name and plan_status='ACTIVE'",nativeQuery = true)
		int getPlanNameByID(String plan_name);

		@Transactional
		@Modifying
		@Query(value="insert into plan_list (features,plan_id) values (:features,:getPlanID)",nativeQuery = true)
		int insertFeaturesName(String features, int getPlanID);

		@Transactional
		@Modifying
		@Query(value="insert into plan_list (valid,features,plan_id) values (:valid,:features_name,:getPlanID)",nativeQuery = true)
		int insertNewFeaturesName(String valid, String features_name, int getPlanID);

		@Transactional
		@Modifying
		@Query(value="update plan_list set valid= :valid,features= :features_name where id= :getFeatureID and delete_flag='N'",nativeQuery = true)
		int updateNewFeaturesName(String valid, String features_name, int getFeatureID);

		@Transactional
		@Modifying
		@Query(value="update plan_list set delete_flag='Y' where id= :id and delete_flag='N'",nativeQuery = true)
		int deleteFeaturesPlanDetails(int id);

}
