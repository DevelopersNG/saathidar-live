package com.sathidar.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sathidar.model.EmailSMSAlertModel;
import com.sathidar.model.UpdateMember;

@Repository
public interface EmailSMSRepository extends JpaRepository<EmailSMSAlertModel, Integer> {

	@Query(value="SELECT * FROM email_sms_alert",nativeQuery=true)
	List<EmailSMSAlertModel> getEmailAlert();

	@Query(value="SELECT count(*) FROM email_alert_status where member_id= :member_id and date(datetime)=curdate() and deleteflag='N'",nativeQuery=true)
	int getDailyEmailStatusByMemberID(String member_id);

	@Query(value="SELECT group_concat(member_id) FROM premium_member where member_id!= :member_id and deleteflag='N'",nativeQuery=true)
	String getPremiumMatchesIDS(Integer member_id);

	@Transactional
	@Modifying
	@Query(value="SELECT email_id,first_name,last_name, m.member_id, "
			+ "height,md.age,md.marital_status,"
			+ "edu.highest_qualification,edu.working_with,"
			+ "(select religion_name from religion where religion_id=(select religion_id from memberdetails where member_id= :m_id)) as religion,"
			+ "(select cast_name from cast where cast_id=(select cast_id from memberdetails where member_id= :m_id )) as caste,"
			+ "(select city_name from city where city_id=(select city_id from memberdetails where member_id= :m_id)) as city,"
			+ "mother_tounge"
			+ "  FROM "
			+ "memberdetails as md join member as m on md.member_id=m.member_id "
			+ "join member_education_career as edu on m.member_id=edu.member_id "
			+ "where md.member_id= :m_id and m.status='ACTIVE'",nativeQuery = true)
	List<Object[]> getMemberDeyailsForMail(String m_id);

	@Transactional
	@Query(value="SELECT first_name,last_name,email_id,member_number,contact_number FROM member where member_id= :member_id",nativeQuery = true)
	List<Object[]> getUserNameEmailId(int member_id);
}
