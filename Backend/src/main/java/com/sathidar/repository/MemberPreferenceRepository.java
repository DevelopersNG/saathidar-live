package com.sathidar.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sathidar.model.MemberPreferenceModel;
import com.sathidar.model.UpdateMember;

@Repository
public interface MemberPreferenceRepository extends JpaRepository<MemberPreferenceModel, Integer> {

	
//	@Transactional
//	@Modifying
//	@Query(value="update member_preference set gender= :gender,lifestyle= :lifestyle,job= :job,education= :education,"
//			+ "cast_id= :cast_id,subcaste_id= :subcaste_id,religion_id= :religion_id,state_id= :state_id,city_id= :city_id,"
//			+ "fromage= :fromage,toage= :toage,status='ACTIVE' where member_id= :member_id",nativeQuery = true)
//	Object updateMemberPreference(int member_id, String gender, String lifestyle, String job, String education, int cast_id,
//			int subcaste_id, int religion_id, int state_id, int city_id, int fromage, int toage);
//
//	
	

	@Transactional
	@Modifying
	@Query(value="update member_preference set partner_marital_status= :partner_marital_status,partner_mother_tongue= :partner_mother_tongue,"
			+ "partner_qualification= :partner_qualification,partner_working_with= :partner_working_with,partner_professional_area= :partner_professional_area,"
			+ "partner_religions= :partner_religions,partner_cast= :partner_cast,partner_country= :partner_country,partner_state= :partner_state,partner_city= :partner_city,"
			+ "partner_from_age= :partner_from_age,partner_to_age= :partner_to_age,partner_from_height=:partner_from_height,partner_to_height=:partner_to_height,"
			+ "partner_manglik_all= :partner_manglik_all,partner_annual_income= :partner_annual_income,partner_profile_created= :partner_profile_created,"
			+ "partner_lifestyles= :partner_lifestyles where member_id= :member_id",nativeQuery = true)
	Object updateNewMemberPreference(int member_id, String partner_marital_status, String partner_mother_tongue,
			String partner_qualification, String partner_working_with, String partner_professional_area,
			String partner_religions, String partner_cast, String partner_country, String partner_state, String partner_city,
			String partner_from_age, String partner_to_age, String partner_from_height, String partner_to_height,
			String partner_manglik_all, String partner_annual_income, String partner_profile_created,
			String partner_lifestyles);

	
	@Transactional
	@Modifying
	@Query(value="insert into member_preference (member_id,partner_marital_status,partner_mother_tongue,partner_qualification," + 
			"	partner_working_with,partner_professional_area,partner_religions,partner_cast,partner_country,partner_state," + 
			"   partner_city,partner_from_age,partner_to_age,partner_from_height,partner_to_height,partner_manglik_all,partner_annual_income," + 
			"   partner_profile_created,partner_lifestyles) "
			+ "values"
			+ " (:member_id,:partner_marital_status, :partner_mother_tongue,"
			+ ":partner_qualification,:partner_working_with,:partner_professional_area,"
			+ ":partner_religions,:partner_cast,:partner_country,:partner_state,:partner_city,"
			+ ":partner_from_age,:partner_to_age,:partner_from_height,:partner_to_height,"
			+ ":partner_manglik_all,:partner_annual_income,:partner_profile_created,"
			+ ":partner_lifestyles)",nativeQuery = true)
	Object insertNewMemberPreference(int member_id, String partner_marital_status, String partner_mother_tongue,
			String partner_qualification, String partner_working_with, String partner_professional_area,
			String partner_religions, String partner_cast, String partner_country, String partner_state, String partner_city,
			String partner_from_age, String partner_to_age, String partner_from_height, String partner_to_height,
			String partner_manglik_all, String partner_annual_income, String partner_profile_created,
			String partner_lifestyles);

	@Query(value="select count(*) from member_preference where member_id= :member_id and status='ACTIVE'",nativeQuery =true )
	int checkAvailablePartnerPreference(int member_id);
}

