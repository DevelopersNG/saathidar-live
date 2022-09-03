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
import com.sathidar.model.UpdateMember;
import com.sathidar.model.User;
import com.sathidar.service.ServerEmailService;
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
	
	@Autowired
	private ServerEmailService serverEmailService;

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

	@PostMapping(path = "/users/register")
	public Map<String, String> registerUser(@Validated @RequestBody User user) {
		HashMap<String, String> map = new HashMap<>();
		map = userService.registerNewUser(user);
		return map;
	}

//	@PostMapping(path = "/member/otp")
//	public Map<String, String> sendOTP(@Validated @RequestBody User user) {
//		HashMap<String, String> map = new HashMap<>();
//		
//		map.put("message", "success");
//		map.put("result", "1");
//		
//		return map;
//	}
	
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
		
		int count=userService.verifyUserService(user_otp,phone);
		if(count>0) {
			// save user otp table verify status=1
			int updateStatus=userService.updateUSERTable(phone,user_otp);
			if(updateStatus>0) {
				map.put("message", "OTP verified");
				map.put("results", "1");
			}else {
				map.put("message", "Somthing wrong ! OTP is not verify");
				map.put("results", "0");
			}
		}else {
			map.put("message", "OTP doesn't match");
			map.put("results", "0");
		}
		
		String otpVerifyStatus="";
		return map;
	}

	private String getOTP() {
		return new DecimalFormat("0000").format(new Random().nextInt(9999));
	}

	@GetMapping(path = "/users/confirm")
	public String confirm(@RequestParam("token") String token, User user) {
//		User userResults = userService.confirmrUser(token);
//		if(userResults!=null) {
		String status = "Something went wrong ! Please try again";
		if (userEntityManagerFactory.updateStatusActiveToMemberTable1(token)) {
			status = "Your verification is completed.";
		}
		String message = "<div style=\"text-align:center;\">\r\n" + 
				"<h1>Thank you!</h1>\r\n" + 
				"<p>"+status+"</p>\r\n" + 
				"</div>\r\n" + 
				"<script type=\"text/javascript\">\r\n" + 
				"var count = 5;\r\n" + 
				"var redirect = \"http://103.174.102.195:8080/saathidaar/#/\";\r\n" + 
				"function countDown() {\r\n" + 
				"if(count >= 0){\r\n" + 
				"document.getElementById(\"timer\").innerHTML = count--;\r\n" + 
				"setTimeout(\"countDown()\", 1000);\r\n" + 
				"}else{\r\n" + 
				"window.location.href = redirect;\r\n" + 
				"}}countDown();\r\n" + 
				"</script>";
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
	
	@RequestMapping(value = "/member/verify/otp/email/{otp}/{email}", method = RequestMethod.GET)
	public Map<String, String> confirmEMAILOTP(User user, @PathVariable("otp") String user_otp,
			@PathVariable("email") String email) {
		HashMap<String, String> map = new HashMap<>();
		user.setOtp(user_otp);
		user.setEmail(email);
		
//		String phoneNo = "91" + user.getPhone().trim();
//		String otp = user.getOtp().trim();
//		String otpVerifyStatus = sendSMSAction.VerifyOtpSms("OTP Verify", phoneNo, otp);
		
		int count=userService.verifyUserEmailService(user_otp,email);
		if(count>0) {
			// save user otp table verify status=1
//			int updateStatus=userService.updateUSERTable(phone,user_otp);
//			if(updateStatus>0) {
				map.put("message", "OTP verified");
				map.put("results", "1");
				
				
				
				
//			}else {
//				map.put("message", "Somthing wrong ! OTP is not verify");
//				map.put("results", "0");
//			}
		}else {
			map.put("message", "OTP doesnt match");
			map.put("results", "0");
		}
		
		String otpVerifyStatus="";
		return map;
	}
	
	@PostMapping(path = "/member/forgot/password/otp")
	public Map<String, String> forgotPasswordOTP(@Validated @RequestBody User user) {
		HashMap<String, String> map = new HashMap<>();
		String otp = this.getOTP();
		
		String firstName=userEntityManagerFactory.getFirstNameByEmail(user.getEmail());
		String lastName=userEntityManagerFactory.getLastNameByEmail(user.getEmail());

		String email_body="<html>\r\n"
		 		+ "  <head>\r\n"
		 		+ "    <meta name=\"viewport\" content=\"width=device-width\" />\r\n"
		 		+ "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\r\n"
		 		+ "  \r\n"
		 		+ "    <style>\r\n"
		 		+ "      /* -------------------------------------\r\n"
		 		+ "          GLOBAL RESETS\r\n"
		 		+ "      ------------------------------------- */\r\n"
		 		+ "      img {\r\n"
		 		+ "        border: none;\r\n"
		 		+ "        -ms-interpolation-mode: bicubic;\r\n"
		 		+ "        max-width: 100%; }\r\n"
		 		+ "      body {\r\n"
		 		+ "        background-color: #f6f6f6;\r\n"
		 		+ "        font-family: sans-serif;\r\n"
		 		+ "        -webkit-font-smoothing: antialiased;\r\n"
		 		+ "        font-size: 14px;\r\n"
		 		+ "        line-height: 1.4;\r\n"
		 		+ "        margin: 0;\r\n"
		 		+ "        padding: 0; \r\n"
		 		+ "        -ms-text-size-adjust: 100%;\r\n"
		 		+ "        -webkit-text-size-adjust: 100%; }\r\n"
		 		+ "      table {\r\n"
		 		+ "        border-collapse: separate;\r\n"
		 		+ "        mso-table-lspace: 0pt;\r\n"
		 		+ "        mso-table-rspace: 0pt;\r\n"
		 		+ "        width: 100%; }\r\n"
		 		+ "        table td {\r\n"
		 		+ "          font-family: sans-serif;\r\n"
		 		+ "          font-size: 14px;\r\n"
		 		+ "          vertical-align: top; }\r\n"
		 		+ "      /* -------------------------------------\r\n"
		 		+ "          BODY & CONTAINER\r\n"
		 		+ "      ------------------------------------- */\r\n"
		 		+ "      .body {\r\n"
		 		+ "        background-color: #f6f6f6;\r\n"
		 		+ "        width: 100%; }\r\n"
		 		+ "      /* Set a max-width, and make it display as block so it will automatically stretch to that width, but will also shrink down on a phone or something */\r\n"
		 		+ "      .container {\r\n"
		 		+ "        display: block;\r\n"
		 		+ "        Margin: 0 auto !important;\r\n"
		 		+ "        /* makes it centered */\r\n"
		 		+ "        max-width: 580px;\r\n"
		 		+ "        padding: 10px;\r\n"
		 		+ "        width: 580px; }\r\n"
		 		+ "      /* This should also be a block element, so that it will fill 100% of the .container */\r\n"
		 		+ "      .content {\r\n"
		 		+ "        box-sizing: border-box;\r\n"
		 		+ "        display: block;\r\n"
		 		+ "        Margin: 0 auto;\r\n"
		 		+ "        max-width: 580px;\r\n"
		 		+ "        padding: 10px; }\r\n"
		 		+ "      /* -------------------------------------\r\n"
		 		+ "          HEADER, FOOTER, MAIN\r\n"
		 		+ "      ------------------------------------- */\r\n"
		 		+ "      .main {\r\n"
		 		+ "        background: #fff;\r\n"
		 		+ "        border-radius: 3px;\r\n"
		 		+ "        width: 100%; }\r\n"
		 		+ "      .wrapper {\r\n"
		 		+ "        box-sizing: border-box;\r\n"
		 		+ "        padding: 20px; }\r\n"
		 		+ "      .footer {\r\n"
		 		+ "        clear: both;\r\n"
		 		+ "        padding-top: 10px;\r\n"
		 		+ "        text-align: center;\r\n"
		 		+ "        width: 100%; }\r\n"
		 		+ "        .footer td,\r\n"
		 		+ "        .footer p,\r\n"
		 		+ "        .footer span,\r\n"
		 		+ "        .footer a {\r\n"
		 		+ "          color: #999999;\r\n"
		 		+ "          font-size: 12px;\r\n"
		 		+ "          text-align: center; }\r\n"
		 		+ "      /* -------------------------------------\r\n"
		 		+ "          TYPOGRAPHY\r\n"
		 		+ "      ------------------------------------- */\r\n"
		 		+ "      h1,\r\n"
		 		+ "      h2,\r\n"
		 		+ "      h3,\r\n"
		 		+ "      h4 {\r\n"
		 		+ "        color: #000000;\r\n"
		 		+ "        font-family: sans-serif;\r\n"
		 		+ "        font-weight: 400;\r\n"
		 		+ "        line-height: 1.4;\r\n"
		 		+ "        margin: 0;\r\n"
		 		+ "        Margin-bottom: 30px; }\r\n"
		 		+ "      h1 {\r\n"
		 		+ "        font-size: 35px;\r\n"
		 		+ "        font-weight: 300;\r\n"
		 		+ "        text-align: center;\r\n"
		 		+ "        text-transform: capitalize; }\r\n"
		 		+ "      p,\r\n"
		 		+ "      ul,\r\n"
		 		+ "      ol {\r\n"
		 		+ "        font-family: sans-serif;\r\n"
		 		+ "        font-size: 14px;\r\n"
		 		+ "        font-weight: normal;\r\n"
		 		+ "        margin: 0;\r\n"
		 		+ "        Margin-bottom: 15px; }\r\n"
		 		+ "        p li,\r\n"
		 		+ "        ul li,\r\n"
		 		+ "        ol li {\r\n"
		 		+ "          list-style-position: inside;\r\n"
		 		+ "          margin-left: 5px; }\r\n"
		 		+ "      a {\r\n"
		 		+ "        color: #3498db;\r\n"
		 		+ "        text-decoration: underline; }\r\n"
		 		+ "      /* -------------------------------------\r\n"
		 		+ "          BUTTONS\r\n"
		 		+ "      ------------------------------------- */\r\n"
		 		+ "      .btn {\r\n"
		 		+ "        box-sizing: border-box;\r\n"
		 		+ "        width: 100%; }\r\n"
		 		+ "        .btn > tbody > tr > td {\r\n"
		 		+ "          padding-bottom: 15px; }\r\n"
		 		+ "        .btn table {\r\n"
		 		+ "          width: auto; }\r\n"
		 		+ "        .btn table td {\r\n"
		 		+ "          background-color: #ffffff;\r\n"
		 		+ "          border-radius: 5px;\r\n"
		 		+ "          text-align: center; }\r\n"
		 		+ "        .btn a {\r\n"
		 		+ "          background-color: #ffffff;\r\n"
		 		+ "          border: solid 1px #3498db;\r\n"
		 		+ "          border-radius: 5px;\r\n"
		 		+ "          box-sizing: border-box;\r\n"
		 		+ "          color: #3498db;\r\n"
		 		+ "          cursor: pointer;\r\n"
		 		+ "          display: inline-block;\r\n"
		 		+ "          font-size: 14px;\r\n"
		 		+ "          font-weight: bold;\r\n"
		 		+ "          margin: 0;\r\n"
		 		+ "          padding: 12px 25px;\r\n"
		 		+ "          text-decoration: none;\r\n"
		 		+ "          text-transform: capitalize; }\r\n"
		 		+ "      .btn-primary table td {\r\n"
		 		+ "        background-color: #3498db; }\r\n"
		 		+ "      .btn-primary a {\r\n"
		 		+ "        background-color: #3498db;\r\n"
		 		+ "        border-color: #3498db;\r\n"
		 		+ "        color: #ffffff; }\r\n"
		 		+ "      /* -------------------------------------\r\n"
		 		+ "          OTHER STYLES THAT MIGHT BE USEFUL\r\n"
		 		+ "      ------------------------------------- */\r\n"
		 		+ "      .last {\r\n"
		 		+ "        margin-bottom: 0; }\r\n"
		 		+ "      .first {\r\n"
		 		+ "        margin-top: 0; }\r\n"
		 		+ "      .align-center {\r\n"
		 		+ "        text-align: center; }\r\n"
		 		+ "      .align-right {\r\n"
		 		+ "        text-align: right; }\r\n"
		 		+ "      .align-left {\r\n"
		 		+ "        text-align: left; }\r\n"
		 		+ "      .clear {\r\n"
		 		+ "        clear: both; }\r\n"
		 		+ "      .mt0 {\r\n"
		 		+ "        margin-top: 0; }\r\n"
		 		+ "      .mb0 {\r\n"
		 		+ "        margin-bottom: 0; }\r\n"
		 		+ "      .preheader {\r\n"
		 		+ "        color: transparent;\r\n"
		 		+ "        display: none;\r\n"
		 		+ "        height: 0;\r\n"
		 		+ "        max-height: 0;\r\n"
		 		+ "        max-width: 0;\r\n"
		 		+ "        opacity: 0;\r\n"
		 		+ "        overflow: hidden;\r\n"
		 		+ "        mso-hide: all;\r\n"
		 		+ "        visibility: hidden;\r\n"
		 		+ "        width: 0; }\r\n"
		 		+ "      .powered-by a {\r\n"
		 		+ "        text-decoration: none; }\r\n"
		 		+ "      hr {\r\n"
		 		+ "        border: 0;\r\n"
		 		+ "        border-bottom: 1px solid #f6f6f6;\r\n"
		 		+ "        Margin: 20px 0; }\r\n"
		 		+ "        table {\r\n"
		 		+ "  font-family: arial, sans-serif;\r\n"
		 		+ "  border-collapse: collapse;\r\n"
		 		+ "  width: 100%;\r\n"
		 		+ "}\r\n"
		 		+ "td, th {\r\n"
		 		+ " font-size: 12px;\r\n"
		 		+ "  text-align: left;\r\n"
		 		+ "  padding: 8px;\r\n"
		 		+ "}\r\n"
		 		+ "       img\r\n"
		 		+ "       {\r\n"
		 		+ "        height: 150px;\r\n"
		 		+ "       }\r\n"
		 		+ "       .bg\r\n"
		 		+ "       {\r\n"
		 		+ "        background-color: #742041;\r\n"
		 		+ "       }\r\n"
		 		+ "       button\r\n"
		 		+ "       {\r\n"
		 		+ "        background-color: #742041;color: #ffff;margin: 5px;\r\n"
		 		+ "       }\r\n"
		 		+ "      /* -------------------------------------\r\n"
		 		+ "          RESPONSIVE AND MOBILE FRIENDLY STYLES\r\n"
		 		+ "      ------------------------------------- */\r\n"
		 		+ "      @media only screen and (max-width: 620px) {\r\n"
		 		+ "        table[class=body] h1 {\r\n"
		 		+ "          font-size: 28px !important;\r\n"
		 		+ "          margin-bottom: 10px !important; }\r\n"
		 		+ "        table[class=body] p,\r\n"
		 		+ "        table[class=body] ul,\r\n"
		 		+ "        table[class=body] ol,\r\n"
		 		+ "        table[class=body] td,\r\n"
		 		+ "        table[class=body] span,\r\n"
		 		+ "        table[class=body] a {\r\n"
		 		+ "          font-size: 16px !important; }\r\n"
		 		+ "        table[class=body] .wrapper,\r\n"
		 		+ "        table[class=body] .article {\r\n"
		 		+ "          padding: 10px !important; }\r\n"
		 		+ "        table[class=body] .content {\r\n"
		 		+ "          padding: 0 !important; }\r\n"
		 		+ "        table[class=body] .container {\r\n"
		 		+ "          padding: 0 !important;\r\n"
		 		+ "          width: 100% !important; }\r\n"
		 		+ "        table[class=body] .main {\r\n"
		 		+ "          border-left-width: 0 !important;\r\n"
		 		+ "          border-radius: 0 !important;\r\n"
		 		+ "          border-right-width: 0 !important; }\r\n"
		 		+ "        table[class=body] .btn table {\r\n"
		 		+ "          width: 100% !important; }\r\n"
		 		+ "        table[class=body] .btn a {\r\n"
		 		+ "          width: 100% !important; }\r\n"
		 		+ "        table[class=body] .img-responsive {\r\n"
		 		+ "          height: auto !important;\r\n"
		 		+ "          max-width: 100% !important;\r\n"
		 		+ "          width: auto !important; }}\r\n"
		 		+ "      @media all {\r\n"
		 		+ "        .ExternalClass {\r\n"
		 		+ "          width: 100%; }\r\n"
		 		+ "        .ExternalClass,\r\n"
		 		+ "        .ExternalClass p,\r\n"
		 		+ "        .ExternalClass span,\r\n"
		 		+ "        .ExternalClass font,\r\n"
		 		+ "        .ExternalClass td,\r\n"
		 		+ "        .ExternalClass div {\r\n"
		 		+ "          line-height: 100%; }\r\n"
		 		+ "        .apple-link a {\r\n"
		 		+ "          color: inherit !important;\r\n"
		 		+ "          font-family: inherit !important;\r\n"
		 		+ "          font-size: inherit !important;\r\n"
		 		+ "          font-weight: inherit !important;\r\n"
		 		+ "          line-height: inherit !important;\r\n"
		 		+ "          text-decoration: none !important; } \r\n"
		 		+ "        .btn-primary table td:hover {\r\n"
		 		+ "          background-color: #34495e !important; }\r\n"
		 		+ "        .btn-primary a:hover {\r\n"
		 		+ "          background-color: #34495e !important;\r\n"
		 		+ "          border-color: #34495e !important; } }\r\n"
		 		+ "    </style>\r\n"
		 		+ "  </head>\r\n"
		 		+ "  <body class=\"\">\r\n"
		 		+ "    <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"body\">\r\n"
		 		+ "      <tr>\r\n"
		 		+ "        <td>&nbsp;</td>\r\n"
		 		+ "        <td class=\"container\">\r\n"
		 		+ "          <div class=\"content\">\r\n"
		 		+ "            <table class=\"main\">\r\n"
		 		+ "\r\n"
		 		+ "              <!-- START MAIN CONTENT AREA -->\r\n"
		 		+ "              <tr>\r\n"
		 		+ "                <td class=\"wrapper\">\r\n"
		 		+ "                  <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\r\n"
		 		+ "                    <tr>\r\n"
		 		+ "                      <td>\r\n"
		 		+ "                        <h1><img src=\"http://103.174.102.195:8080/saathidaar_logo/saathidaar_logo.jpeg\" alt=\"\"></h1>\r\n"
		 		+ "                        <h2>Hi "+firstName+"&nbsp;  "+lastName+"</h2>\r\n"
		 		+ "                        <h5>Hi, your verification code is <strong> "+otp+"</strong></h5>\r\n"
		 		+ "                        <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"btn btn-primary\">\r\n"
		 		+ "                          <tbody>\r\n"
		 		+ "                            <tr>\r\n"
		 		+ "                              <td align=\"left\">\r\n"
		 		+ "                              </td>\r\n"
		 		+ "                            </tr>\r\n"
		 		+ "                          </tbody>\r\n"
		 		+ "                        </table>\r\n"
		 		+ "                       <a href=\"\">Saathidaar.com </a>\r\n"
		 		+ "                        \r\n"
		 		+ "                        </table>\r\n"
//		 		+ "                        <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\r\n"
//		 		+ "                          <tbody>\r\n"
//		 		+ "                            <tr>\r\n"
//		 		+ "                              <td> <a href=\"http://htmlemail.io\" target=\"_blank\">View Full Profile</a> </td>\r\n"
//		 		+ "                            </tr>\r\n"
//		 		+ "                          </tbody>\r\n"
//		 		+ "                        </table>\r\n"
		 		+ "                      </td>\r\n"
		 		+ "                    </tr>\r\n"
		 		+ "                  </table>\r\n"
		 		+ "                </td>\r\n"
		 		+ "              </tr>\r\n"
		 		+ "\r\n"
		 		+ "            <!-- END MAIN CONTENT AREA -->\r\n"
		 		+ "            </table>\r\n"
		 		+ "\r\n"
		 		+ "         \r\n"
		 		+ "            \r\n"
		 		+ "          <!-- END CENTERED WHITE CONTAINER -->\r\n"
		 		+ "          </div>\r\n"
		 		+ "        </td>\r\n"
		 		+ "        <td>&nbsp;</td>\r\n"
		 		+ "      </tr>\r\n"
		 		+ "    </table>\r\n"
		 		+ "  </body>\r\n"
		 		+ "</html>\r\n"
		 		+ "\r\n"
		 		+ "";
		
		try {
			int status=userService.updatePasswordEmail(user.getEmail(),otp);
			if(status>0) {
				serverEmailService.send(user.getEmail(), "Saathidaar-Forgot Password", email_body);
				map.put("results", "1");
				map.put("message", "success");
			}else {
				map.put("message", "error");
				map.put("results", "0");
			}
			
//			String otp = this.getOTP();
////			String smsMessage = "Your Verification Code is " + otp + " Saathidaar.com";
//			// send email
//			String smsMessage = "Welcome to Saathidar.com. " + otp
//					+ "  is your OTP to forgot password.\r\n" + "www.Saathidar.com";
//			String sender = "SDMREG";
//			String phoneNo = "91" + user.getPhone().trim();
//			String response = sendSMSAction.SendOtpSms(phoneNo, sender, smsMessage);
//
//			final JSONObject obj = new JSONObject(response);
//			obj.toString();
//			String type = obj.getString("type");
//
//			if (response != null) {
//				if (type.equals("success")) {
//					map.put("message", "success");
//					map.put("result", "1");
//				} else if (type.equals("error")) {
//					map.put("message", "error");
//					
//				}
//			} else {
//				map.put("message", "error");
//				map.put("result", "0");
//			}

		} catch (Exception e) {
			e.printStackTrace();
			map.put("message","error");
			map.put("results", "0");
		}
		return map;
	}
	
	@PostMapping(path = "/member/forgot/password/update")
	public Map<String, String> updateForgotPassword(@Validated @RequestBody User user) {
		HashMap<String, String> map = new HashMap<>();
		try 	{
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
	
	
//	******************** short registation form ************************************************************
	
	@PostMapping(path = "/member/short-registration/update/{member_id}")
	public Map<String, String> updateRegistrationDetails(@Validated @RequestBody UpdateMember updateMember,
			@PathVariable("member_id") int user_id) {
		HashMap<String, String> map = new HashMap<>();
		JSONObject jsObject = new JSONObject();
		int status=userService.updateRegistrationDetails(updateMember, user_id);
		if (status>0) {
				map.put("results", "1");
		} else {
			map.put("results", "0");
		}
		return map;
	}
	
	@GetMapping(value = "/member/get/short-registration/status/{member_id}")
	public Map<String, String> getShortRegistrationStatus(@PathVariable("member_id") int member_id) {
		HashMap<String, String> map = new HashMap<>();
		String short_registration_status = userService.getShortRegistrationStatus(member_id);
		if (short_registration_status==null && short_registration_status.equals("")) {
			map.put("results", "0");
			map.put("message", "something wrong ! record not fetch...");
		}else {
			map.put("results",short_registration_status);
			map.put("message", "short registration form updated...");
		}
		return map;
	}
	
	
}
