package com.sathidar.repository;

import java.util.List;

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

	@Query(value="select phone from privacy_options where member_id=:member_id",nativeQuery = true)
	String getPhoneRecords(Integer member_id);
	
	//email
	@Transactional
	@Modifying
	@Query(value="update privacy_options set email= :keyCode where member_id=:member_id",nativeQuery = true)
	int updateEmailPrivacy(Integer member_id, int keyCode);

	@Transactional
	@Modifying
	@Query(value="insert into privacy_options (email,member_id) value (:keyCode,:member_id)",nativeQuery = true)
	int insertEmailPrivacy(Integer member_id, int keyCode);

	@Query(value="select email from privacy_options where member_id=:member_id",nativeQuery = true)
	String getEmailRecords(int member_id);

	
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

	@Query(value="select dob from privacy_options where member_id=:member_id",nativeQuery = true)
	String getDateOfBirthRecords(int member_id);
	
	
	// annual income
	@Transactional
	@Modifying
	@Query(value="update privacy_options set annual_income= :keyCode where member_id=:member_id",nativeQuery = true)
	int updateAnnualIncomePrivacy(Integer member_id, int keyCode);

	@Transactional
	@Modifying
	@Query(value="insert into privacy_options (annual_income,member_id) value (:keyCode,:member_id)",nativeQuery = true)
	int insertAnnualIncomePrivacy(Integer member_id, int keyCode);

	@Query(value="select annual_income from privacy_options where member_id=:member_id",nativeQuery = true)
	String getAnnualIncomeRecords(int member_id);
	
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

	@Query(value="select phone,email,photo,dob,annual_income,horoscope,visitors_setting,shortlist_setting,profile_privacy from privacy_options where member_id= :member_id",nativeQuery = true)
	List<Object[]> getAllPrivacy(String member_id);

	@Query(value="select count(*) from email_sms_alert where member_id= :member_id",nativeQuery = true)
	int findByMember_Id_SMS_ALERT(Integer member_id);

//	***************************** update email/sms alert ********************************
	
	@Transactional
	@Modifying
	@Query(value="update email_sms_alert set premium_match_mail= :premium_match_mail,recent_visitors_email= :recent_visitors_email,"
			+ "contact_alert= :contact_alert,sms_alert= :sms_alert,message_received_alert= :message_received_alert  where member_id=:member_id",nativeQuery = true)
	int updateeEmailSMS(Integer member_id, String premium_match_mail, String recent_visitors_email,
			String contact_alert, String sms_alert, String message_received_alert);

	@Transactional
	@Modifying
	@Query(value="insert into email_sms_alert (premium_match_mail,recent_visitors_email,contact_alert,sms_alert,message_received_alert,member_id) value (:premium_match_mail,:recent_visitors_email,:contact_alert,:sms_alert,:message_received_alert,:member_id)",nativeQuery = true)
	int inserteEmailSMS(Integer member_id, String premium_match_mail, String recent_visitors_email,
			String contact_alert, String sms_alert, String message_received_alert);

	@Query(value="select premium_match_mail,recent_visitors_email,contact_alert,sms_alert,message_received_alert from email_sms_alert where member_id= :member_id",nativeQuery = true)
	List<Object[]> GetAllEmailSMSSetting(String member_id);

	

	


	
}
