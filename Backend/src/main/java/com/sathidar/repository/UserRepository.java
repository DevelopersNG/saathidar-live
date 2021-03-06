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

	@Query("SELECT u FROM User u WHERE u.username = :username and u.enabled='1'")
	User findByUsername(String username);
	
	User findByConfirmationToken(String confirmationToken);
	
	@Query("SELECT u FROM User u WHERE u.username = :username")
	public User getUserByUsername(@Param("username") String username);

//	@Query("SELECT u FROM User u WHERE u.email = :email and u.status='ACTIVE'")
	@Query("SELECT u FROM User u WHERE u.email = :email and u.enabled='1'")
	User findByEmail(String email);
	
//	@Query("SELECT u FROM User u WHERE u.phone = :phone and u.status='ACTIVE'")
	@Query("SELECT u FROM User u WHERE u.phone = :phone and u.enabled='1'")
	User findByPhone(String phone);

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

	@Transactional
	@Modifying    
	@Query(value="update users set password= :generatePassword where id= :id",nativeQuery = true)
	int updatePassword(Integer id, String generatePassword);
}
