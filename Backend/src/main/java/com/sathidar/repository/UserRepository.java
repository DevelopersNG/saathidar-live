package com.sathidar.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sathidar.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>  { 

	@Query(value="SELECT * FROM users as u WHERE u.username = :username and u.enabled='1' order by u.id desc limit 1",nativeQuery = true)
	User findByUsername(String username);
	
	User findByConfirmationToken(String confirmationToken);
	
	@Query("SELECT u FROM User u WHERE u.username = :username")
	public User getUserByUsername(@Param("username") String username);

//	@Query("SELECT u FROM User u WHERE u.email = :email and u.status='ACTIVE'")
	@Query("SELECT u FROM User u WHERE u.email = :email and u.enabled='1' and u.short_reg_status=1")
	User findByEmail(String email);
	
//	@Query("SELECT count(*) FROM User  WHERE email = :email and phone = :phone and enabled='1' and short_reg_status=1 and otp_verified=1")
//	int findByPhone(String phone,String email);

	@Query("SELECT count(*) FROM User  WHERE phone = :phone and enabled='1' and short_reg_status=1 and otp_verified=1")
	int findByPhone(String phone);

	
	@Query(value="SELECT count(*) FROM hide_member WHERE member_id = :ID",nativeQuery = true)
	int isAvaialbeHideMember(int ID);

	@Query(value= "SELECT status FROM hide_member WHERE member_id = :memberID order by id desc limit 1",nativeQuery = true)
	int getHideMemberStatus(int memberID);

	@Transactional
	@Modifying    
	@Query(value="update hide_member set status= :getStatus, hide_period_time_month= :hide_period_time, unhide_period_time= :unhide_period_time, created_date=current_date where member_id= :member_id",nativeQuery = true)
	int updatehideMemberForPeriodTime(int getStatus, int member_id,String hide_period_time,String unhide_period_time);

	@Transactional
	@Modifying    
	@Query(value="insert into hide_member (member_id,hide_period_time_month,status,unhide_period_time) values (:member_id,:hide_period_time,:getStatus,:unhide_period_time)",nativeQuery = true)
	int savehideMemberForPeriodTime(int getStatus, int member_id,String hide_period_time,String unhide_period_time);

	@Transactional
	@Modifying    
	@Query(value="update hide_member set status= :getStatus, hide_period_time_month= :hide_period_time, unhide_period_time=current_date, created_date=current_date where member_id= :member_id",nativeQuery = true)
	int updateunhideMemberForPeriodTime(int getStatus, int member_id, String hide_period_time);

	@Query(value="SELECT count(*) FROM hide_member WHERE member_id = :member_id",nativeQuery = true)
	int isMemberAvailable(int member_id);

	@Query(value="SELECT hide_period_time_month FROM hide_member WHERE member_id = :member_id",nativeQuery = true)
	String getActivateMember(int member_id);


	@Transactional
	@Modifying    
	@Query(value="insert into users (first_name,last_name,email,role,gender,phone,profilecreatedby,confirmation_token,password,username,enabled) values (:first_name,:last_name,:email,:role,:gender,:phone,:profilecreatedby,:confirmation_token,:password,:username,:enabled)",nativeQuery = true)
	int saveToUser(String first_name, String last_name, String email, String role, String gender, String phone,
			String profilecreatedby, String confirmation_token, String password,String username, boolean enabled);

	@Query(value="SELECT * FROM users u WHERE u.phone = :phone and u.enabled='1'",nativeQuery = true)
	List<User> getEmailByPhoneNumber(String phone);

	@Query(value="SELECT * FROM users u WHERE u.email = :email and u.enabled='1'",nativeQuery = true)
	List<User> getDetailsByEmailBy(String email);
	
	@Transactional
	@Modifying    
	@Query(value="update users set password= :generatePassword where id= :id",nativeQuery = true)
	int updatePassword(Integer id, String generatePassword);

	@Transactional
	@Modifying    
	@Query(value="insert into tempsendotp (conactno,otp) values (:phoneNo,:otp)",nativeQuery = true)
	int saveOTPDB(String phoneNo, String otp);

	@Query(value="SELECT verify FROM tempsendotp WHERE conactno = :phone order by id desc limit 1",nativeQuery = true)
	String getOtpVerify(String phone);

	@Query(value="SELECT verify FROM tempsendotp where conactno= :phone and otp= :user_otp order by id desc limit 1;",nativeQuery = true)
	int getVerifyOTP(String phone, String user_otp);
	
	@Transactional
	@Modifying    
	@Query(value="update tempsendotp set verify=1 where conactno= :phone and otp= :user_otp order by id desc limit 1",nativeQuery = true)
	int updateOTPStatus(String phone, String user_otp);

	@Query(value="SELECT * FROM users where id= :user_id",nativeQuery = true)
	List<User> getByUserID(int user_id);
	
	@Transactional
	@Modifying
	@Query(value = "update users set "
			+ "  short_reg_status=1 where id= :user_id  ", nativeQuery = true)
	int updateShortRegstInUserTable(int user_id);

	@Query(value="SELECT count(*) FROM tempsendotp where conactno= :phone and otp= :user_otp order by id desc limit 1",nativeQuery = true)
	int verifyUserService(String user_otp, String phone);

	@Transactional
	@Modifying
	@Query(value = "update users set otp_verified='1' where phone= :phone order by id desc limit 1  ", nativeQuery = true)
	int updateUSERTable(String phone);

	@Transactional
	@Modifying    
	@Query(value="insert into change_pswd (email,otp) values (:email,:otp)",nativeQuery = true)
	int updatChangeePasswordEmail(String email, String otp);

	@Query(value="SELECT count(*) FROM change_pswd where email= :email and otp= :user_otp order by id desc limit 1",nativeQuery = true)
	int verifyUserEmailService(String user_otp, String email);
	
}
