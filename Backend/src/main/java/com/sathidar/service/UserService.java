package com.sathidar.service;

import java.util.HashMap;
import java.util.Optional;

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

//	User logoutUser(User user);
}
