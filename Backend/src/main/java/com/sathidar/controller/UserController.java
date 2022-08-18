package com.sathidar.controller;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.json.JSONObject;

//import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sathidar.EntityMangerFactory.UserEntityManagerFactory;
import com.sathidar.model.User;
//import com.sathidar.service.EmailService;
import com.sathidar.service.UserService;
import com.sathidar.util.SendSMSAction;
import com.sathidar.util.TextLocalSMSSetting;

//@CrossOrigin(maxAge = 3600) // https://spring.io/guides/gs/rest-service-cors/
//@CrossOrigin(origins = "http://localhost:4200", methods = { RequestMethod.OPTIONS, RequestMethod.GET,
//		RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE }, allowedHeaders = "*", allowCredentials = "true")

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserEntityManagerFactory userEntityManagerFactory;

	@Autowired
	private SendSMSAction sendSMSAction;

//	@Autowired
//	private EmailService emailService;

//	@Value("${webServerUrl}")
//	private String webServerUrl;

//	@GetMapping(path = "/users")
	@RequestMapping(value = "/users", produces = "application/json", method = RequestMethod.GET)
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}

//	@PostMapping(path = "/users/register")
//	public Map<String, String> register(@Validated @RequestBody User user) {
//		 HashMap<String, String> map = new HashMap<>();		 				 
//		if(userService.registerUser(user) !=null) {
//			 map.put("message", "Member Already Register...");
//		}else {
//			map.put("message", "Something wrong , please try again....");
//		}
//		return map;
//	}
//	

	@PostMapping(path = "/users/register")
	public Map<String, String> registerUser(@Validated @RequestBody User user) {
		HashMap<String, String> map = new HashMap<>();
		map = userService.registerNewUser(user);
		return map;
	}

	@PostMapping(path = "/member/otp")
	public Map<String, String> sendOTP(@Validated @RequestBody User user) {
		HashMap<String, String> map = new HashMap<>();
		TextLocalSMSSetting textLocalSMSSetting=new TextLocalSMSSetting();
//		String messageStatus = userService.isUserAlreadyRegister(user);
//		if (messageStatus.equals("success")) {
			String otp = this.getOTP();
			String smsMessage = "Your Verification Code is "+otp+"\n" + 
					"Saathidaar.com";
////			String smsMessage = "Welcome to Saathidar.com. " + otp
////					+ "  is your OTP to login and start finding your soulmate here.\r\n" + "www.Saathidar.com";
			String sender = "SDMOTP";
			String phoneNo = user.getPhone().trim();
			String response = textLocalSMSSetting.POSTSendSMS(phoneNo, sender, smsMessage);
//
////		String response = sendSMSAction.SendOtpSms(phoneNo, sender, smsMessage);
//
			final JSONObject obj = new JSONObject(response);
			obj.toString();
			String type = obj.getString("type");

			if (otp != null && !otp.equals("")) {
				if (type.equals("success")) {
					map.put("message", "success");
					map.put("result", "1");
					// save otp to db
					int status=userService.saveOTPDB(phoneNo,otp);
				} else if (type.equals("error")) {
					map.put("message", "error");
					map.put("result", "0");
				}
			} else {
				map.put("message", "error");
				map.put("result", "0");
			}
//		} else {
//			map.put("message", messageStatus);
//			map.put("result", "0");
//		}

		return map;
	}
	
//	@GetMapping(path = "/member/verify/otp")
	@RequestMapping(value = "/member/verify/otp/{otp}/{phone}", method = RequestMethod.GET)
	public Map<String, String> confirmOTP(User user, @PathVariable("otp") String user_otp,
			@PathVariable("phone") String phone) {
		HashMap<String, String> map = new HashMap<>();
		user.setOtp(user_otp);
		user.setPhone(phone);
		
//		String phoneNo = "91" + user.getPhone().trim();
//		String otp = user.getOtp().trim();
//		String otpVerifyStatus = sendSMSAction.VerifyOtpSms("OTP Verify", phoneNo, otp);
		
		String otpVerifyStatus="";
		int updateStatus=userService.updateOTPStatus(phone,user_otp);
		if(updateStatus>0) {
			otpVerifyStatus="verified";
		}
		
		final JSONObject obj = new JSONObject(otpVerifyStatus);
		obj.toString();
		String message = obj.getString("message");
		String type = obj.getString("type");
		
		if (otpVerifyStatus != null && !otpVerifyStatus.equals("")) {
			map.put("message", "OTP verified");
			map.put("results", "1");
		} else {
			map.put("message",  "OTP not verified");
			map.put("results", "0");
		}
		return map;
	}

	private String getOTP() {
		return new DecimalFormat("0000").format(new Random().nextInt(9999));
	}

	@GetMapping(path = "/users/confirm")
	public String confirm(@RequestParam("token") String token, User user) {
//		User userResults = userService.confirmrUser(token);
//		if(userResults!=null) {
		String message = "User not confirmed.";
		if (userEntityManagerFactory.updateStatusActiveToMemberTable(token)) {
			message = "User confirmed.";
		}
//		}
		return message;
	}

	@GetMapping(path = "/check/profile/id/available/{profile_id}")
	public HashMap<String, String> isUserAvailableOrNot(@PathVariable("profile_id") String profile_id, User user) {
		HashMap<String, String> map = new HashMap<String, String>();
		if (userEntityManagerFactory.isUserAvailableOrNot(profile_id)) {
			map.put("message", "success");
		} else {
			map.put("message", "profile id not found");
		}
//		}
		return map;
	}

	@PostMapping(path = "/users/login")
	public HashMap<String, String> login(@Validated @RequestBody User user) {
		HashMap<String, String> map = new HashMap<String, String>();
		map = userService.loginUser(user);
		return map;
	}

	@PostMapping(path = "/admin-login")
	public User adminLogin(@Validated @RequestBody User user) {
		return userService.loginAdmin(user);
	}

	@PostMapping(path = "/users/changepwd")
	public HashMap<String, String> changePassword(@Validated @RequestBody User user) {
		HashMap<String, String> map = new HashMap<String, String>();
		map = userService.changeUserPassword(user);
		return map;
	}

	@PostMapping(path = "/users/reset")
	public void reset(@Validated @RequestBody User user) {
		User resetUser = userService.resetUser(user);
		if (resetUser != null) {

//			SimpleMailMessage registrationEmail = new SimpleMailMessage();
//			registrationEmail.setTo(user.getEmail());
//			registrationEmail.setSubject("Temporary Password Sent From " + webServerUrl);
//			registrationEmail
//					.setText("To access your account, please use this temporary password:  " + resetUser.getPassword()
//							+ ".\r\nNOTE: This email was sent from an automated system. Please do not reply.");
//			registrationEmail.setFrom("noreply@domain.com");
//			emailService.sendEmail(registrationEmail);
		}
	}

//	@PostMapping(path = "/users/logout")
//	public User logout(@Validated @RequestBody User user) {
//		return userService.logoutUser(user);
//	}

	@PostMapping(path = "/member/hide/{member_id}")
	public HashMap<String, String> hideMemberForPeriodTime(@Validated @RequestBody User user,
			@PathVariable("member_id") String member_id) {
		HashMap<String, String> map = new HashMap<String, String>();
		int check = userService.isAvaialbeHideMember(Integer.parseInt(member_id));

		String hide_period_time = checkNullValue(user.getHide_period_time_month());
		int getStatus = 0;
		int count = 0;

		// get next month interval
		System.out.println("hide_period_time - " + hide_period_time);

		String unhide_period_time = "";
		if (hide_period_time.equals("unhide")) {
			getStatus = 1;
			unhide_period_time = "current_date";
		} else {
			unhide_period_time = userService.getDateIntervalForHideProfile(hide_period_time);
		}
		// status=0 hide / status = 1 unhide
		if (check > 0) {
			// update
			if (getStatus == 1) {
				count = userService.updateunhideMemberForPeriodTime(getStatus, Integer.parseInt(member_id),
						hide_period_time);
			} else {
				count = userService.updatehideMemberForPeriodTime(getStatus, Integer.parseInt(member_id),
						hide_period_time, unhide_period_time);
			}
		} else {
			// insert
			System.out.println("*********** " + getStatus + "," + hide_period_time + "," + member_id);
			count = userService.savehideMemberForPeriodTime(getStatus, Integer.parseInt(member_id), hide_period_time,
					unhide_period_time);
		}
		if (count > 0) {
			map.put("result", "1");
			map.put("message", "Profile is updated");
		} else {
			map.put("result", "0");
			map.put("message", "Profile not getting hide");
		}
		return map;
	}

	@GetMapping(path = "/member/get/hide/{member_id}")
	public Map<String, String> getHideProfileStatus(@PathVariable("member_id") int member_id) {
		HashMap<String, String> map = new HashMap<>();
		String status = userService.getHideProfileStatus(member_id);
		map.put("months", status);
		map.put("results", "1");
		return map;
	}
	
	@PostMapping(path = "/member/forgot/password/otp")
	public Map<String, String> forgotPasswordOTP(@Validated @RequestBody User user) {
		HashMap<String, String> map = new HashMap<>();
		try {
			String otp = this.getOTP();
//			String smsMessage = "Your Verification Code is " + otp + " Saathidaar.com";
			// send email
			String smsMessage = "Welcome to Saathidar.com. " + otp
					+ "  is your OTP to forgot password.\r\n" + "www.Saathidar.com";
			String sender = "SDMREG";
			String phoneNo = "91" + user.getPhone().trim();
			String response = sendSMSAction.SendOtpSms(phoneNo, sender, smsMessage);

			final JSONObject obj = new JSONObject(response);
			obj.toString();
			String type = obj.getString("type");

			if (response != null) {
				if (type.equals("success")) {
					map.put("message", "success");
					map.put("result", "1");
				} else if (type.equals("error")) {
					map.put("message", "error");
					map.put("result", "0");
				}
			} else {
				map.put("message", "error");
				map.put("result", "0");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	@PostMapping(path = "/member/forgot/password/update")
	public Map<String, String> updateForgotPassword(@Validated @RequestBody User user) {
		HashMap<String, String> map = new HashMap<>();
		try {
			String status = userService.updateForgotPassword(user);
			if(!status.equals("")) {
				map.put("message", "password send to email");
				map.put("results", "1");
			}else {
				map.put("message", "something wrong !!! , forgot password not send...");
				map.put("results", "0");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	public String checkNullValue(String value) {
		if (value != null && !value.equals("null")) {
			return value;
		}
		return "";
	}
	
	
	
}
