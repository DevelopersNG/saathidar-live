package com.sathidar.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sathidar.model.PrivacyOptionsModel;

@Repository
public interface PrivacyPolicyRepository extends JpaRepository<PrivacyOptionsModel, Integer>{

	@Query(value="select count(*) from privacy_options where member_id= :member_id",nativeQuery = true)
	int findByMember_Id(Integer member_id);

	//phone
	@Transactional
	@Modifying
	@Query(value="insert into privacy_options (phone,member_id) value (:keyCode,:member_id)",nativeQuery = true)
	int insertPhonePrivacy(Integer member_id,Integer keyCode);

	@Transactional
	@Modifying
	@Query(value="update privacy_options set phone= :keyCode where member_id=:member_id",nativeQuery = true)
	int updatePhonePrivacy(Integer member_id, int keyCode);

	//email
	@Transactional
	@Modifying
	@Query(value="update privacy_options set email= :keyCode where member_id=:member_id",nativeQuery = true)
	int updateEmailPrivacy(Integer member_id, int keyCode);

	@Transactional
	@Modifying
	@Query(value="insert into privacy_options (email,member_id) value (:keyCode,:member_id)",nativeQuery = true)
	int insertEmailPrivacy(Integer member_id, int keyCode);

	//Photo
	@Transactional
	@Modifying
	@Query(value="update privacy_options set photo= :keyCode where member_id=:member_id",nativeQuery = true)
	int updatePhotoPrivacy(Integer member_id, int keyCode);


	@Transactional
	@Modifying
	@Query(value="insert into privacy_options (photo,member_id) value (:keyCode,:member_id)",nativeQuery = true)
	int insertPhotoPrivacy(Integer member_id, int keyCode);

	
	//DOB
	@Transactional
	@Modifying
	@Query(value="update privacy_options set dob= :keyCode where member_id=:member_id",nativeQuery = true)
	int updateDOBPrivacy(Integer member_id, int keyCode);

	@Transactional
	@Modifying
	@Query(value="insert into privacy_options (dob,member_id) value (:keyCode,:member_id)",nativeQuery = true)
	int insertDOBPrivacy(Integer member_id, int keyCode);

	// annual income
	@Transactional
	@Modifying
	@Query(value="update privacy_options set annual_income= :keyCode where member_id=:member_id",nativeQuery = true)
	int updateAnnualIncomePrivacy(Integer member_id, int keyCode);

	@Transactional
	@Modifying
	@Query(value="insert into privacy_options (annual_income,member_id) value (:keyCode,:member_id)",nativeQuery = true)
	int insertAnnualIncomePrivacy(Integer member_id, int keyCode);

	// horoscope
	@Transactional
	@Modifying
	@Query(value="update privacy_options set horoscope= :keyCode where member_id=:member_id",nativeQuery = true)
	int updateHoroscopePrivacy(Integer member_id, int keyCode);

	@Transactional
	@Modifying
	@Query(value="insert into privacy_options (horoscope,member_id) value (:keyCode,:member_id)",nativeQuery = true)
	int insertHoroscopePrivacy(Integer member_id, int keyCode);

	// visitors_setting
	@Transactional
	@Modifying
	@Query(value="update privacy_options set visitors_setting= :keyCode where member_id=:member_id",nativeQuery = true)
	int updateVisitorSettingsPrivacy(Integer member_id, int keyCode);

	@Transactional
	@Modifying
	@Query(value="insert into privacy_options (visitors_setting,member_id) value (:keyCode,:member_id)",nativeQuery = true)
	int insertVisitorSettingsPrivacy(Integer member_id, int keyCode);

	//shortlist_setting
	@Transactional
	@Modifying
	@Query(value="update privacy_options set shortlist_setting= :keyCode where member_id=:member_id",nativeQuery = true)
	int updateShortlistPrivacy(Integer member_id, int keyCode);

	@Transactional
	@Modifying
	@Query(value="insert into privacy_options (shortlist_setting,member_id) value (:keyCode,:member_id)",nativeQuery = true)
	int insertShortlistPrivacy(Integer member_id, int keyCode);

	//profile_privacy
	@Transactional
	@Modifying
	@Query(value="update privacy_options set profile_privacy= :keyCode where member_id=:member_id",nativeQuery = true)
	int updateProfilePrivacy(Integer member_id, int keyCode);

	@Transactional
	@Modifying
	@Query(value="insert into privacy_options (profile_privacy,member_id) value (:keyCode,:member_id)",nativeQuery = true)
	int insertProfileePrivacy(Integer member_id, int keyCode);

}
