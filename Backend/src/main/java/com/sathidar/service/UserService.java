package com.sathidar.service;

import java.util.HashMap;
import java.util.Optional;

import com.sathidar.model.UpdateMember;
import com.sathidar.model.User;

import antlr.collections.List;

public interface UserService {

	java.util.List<User> getAllUsers();
	
	Object registerUser(User user);
	
	User resetUser(User user);
	
	HashMap<String, String>  changeUserPassword(User user);
	
	User confirmrUser(String token);
	
	HashMap<String, String> loginUser(User user);

	String generatePassword(int length);

	User loginAdmin(User user);

	HashMap<String, String> registerNewUser(User user);

	String isUserAlreadyRegister(User user);

	int isAvaialbeHideMember(int parseInt);

	int getHideMemberStatus(int parseInt);

	int savehideMemberForPeriodTime(int getStatus, int member_id,String hide_period_time,String unhide_period_time);

	int updatehideMemberForPeriodTime(int getStatus, int member_id, String hide_period_time,String unhide_period_time);

	String getDateIntervalForHideProfile(String hide_period_time);

	int updateunhideMemberForPeriodTime(int getStatus, int parseInt, String hide_period_time);

	String getHideProfileStatus(int member_id);

	String updateForgotPassword(User user);

	int saveOTPDB(String phoneNo, String otp);

	int getVerifyOTP(String phone, String user_otp);

	int updateOTPStatus(String phone, String user_otp);

	int updateRegistrationDetails(UpdateMember updateMember, int user_id);

	String getShortRegistrationStatus(int member_id);

	int verifyUserService(String user_otp, String phone);

	int updateUSERTable(String phone, String user_otp);

	int updatePasswordEmail(String email, String otp);

	int verifyUserEmailService(String user_otp, String email);

	int isAvailableEmail(String email);

	String getUserIDByVerifyNumber(String phone);

	int updateStatusACTIVEToMemberTable(String user_id);

//	User logoutUser(User user);
}
