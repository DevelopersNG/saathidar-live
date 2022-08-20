package com.sathidar.service;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.tomcat.util.json.JSONParser;
import org.hibernate.Session;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.json.*;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sathidar.EntityMangerFactory.UserEntityManagerFactory;
import com.sathidar.exception.BadRequestException;
import com.sathidar.model.Role;
import com.sathidar.model.User;
import com.sathidar.repository.UpdateMemberRepository;
import com.sathidar.repository.UserRepository;
import com.sathidar.service.EmailService;

@Service
@EnableJpaAuditing
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private UserEntityManagerFactory userEntityManagerFactory;

	@Autowired
    private EmailService mailSender;
	
	private static final Random RANDOM = new SecureRandom();
	private static final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

	@Override
	public List<User> getAllUsers() {
		List<User> users = userRepository.findAll();
		for (User user : users) {
			user.setPassword("");
		}
		return users;
	}

	@Override
	@Transactional
	public Object registerUser(User user) {
//		String password = user.getPassword();
		String password = user.getPhone();// set passowrd to phone number by default
		if (password.isEmpty()) {
			throw new BadRequestException("Invalid password.");
		}

		
		String encodedPassword = encoder.encode(password);
		user.setPassword(encodedPassword);
		if (user.getUsername().isEmpty()) {
			user.setUsername(user.getEmail());
		}

//		User userExists = userRepository.findByUsername(user.getUsername());
		User userExists = userRepository.findByEmail(user.getEmail());

		if (userExists != null) {
			throw new BadRequestException(user.getUsername() + " already registered.");
		}
	
		user.setEnabled(false); 	// Disable user until they click on confirmation link in email
//		user.setRole("USER");  // set user role by default USER 

		// Generate random 36-character string token for confirmation link
		user.setConfirmationToken(UUID.randomUUID().toString());
		user.setUsername(user.getEmail());  // set username to email by default
//		User tempUser = userRepository.save(user);
		int tempUser = userRepository.saveToUser(user.getFirstName(),user.getLastName(),user.getEmail(),user.getRole(),
				user.getGender(),user.getPhone(),user.getProfilecreatedby(),user.getConfirmationToken(),user.getPassword(),user.getUsername(),user.getEnabled());
		int getLastInsertedID = userEntityManagerFactory.getLastInsertedID();
		if (tempUser == 0) {
			throw new BadRequestException(user.getUsername() + " is not registered.");
		} else {
			int getRoleID = userEntityManagerFactory.getRoleID(user.getRole());
			if (getRoleID != 0) {
				if (userEntityManagerFactory.saveRoleToMember(getRoleID, getLastInsertedID)) {
					if(userEntityManagerFactory.insertRecordToMemberTable(user,getRoleID, getLastInsertedID)!=0) {
//						 SimpleMailMessage message = new SimpleMailMessage();
//						  message.setFrom("vikas.ngdigital@gmail.com");
//						  message.setTo(user.getEmail());
//						  message.setSubject("Registration Confirmation");
//						  message.setText("Your User Name is "+user.getEmail()+"\n Your Password is "+user.getPhone()+" \n "
//						  		+ "you can change username and password when confirmation is done  \n To confirm your e-mail address, please click the link below:\n"
//						  		+ "http://localhost:9094/api/users/confirm?token="+user.getConfirmationToken());
//						  message.setFrom("noreply@domain.com");
//						status=true;
						String to=user.getEmail();
						String subject="Registration Confirmation";
						String message="Your User Name is "+user.getEmail()+"\n Your Password is "+user.getPhone()+" \n "
						  		+ "you can change username and password when confirmation is done  \n To confirm your e-mail address, please click the link below:\n"
						  		+ "http://localhost:9094/api/users/confirm?token="+user.getConfirmationToken();
						  
//						  mailSender.send(to, subject, message);
					}else {
						throw new BadRequestException(user.getUsername() + " is not registered.");
					}
				}else {
					throw new BadRequestException(user.getUsername() + " is not registered.");
				}
			}
		}
		return tempUser;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public User resetUser(User user) {
		if (user.getUsername().isEmpty()) {
			user.setUsername(user.getEmail());
		}
		User userExists = userRepository.findByUsername(user.getUsername());

		if (userExists == null) {
			throw new BadRequestException(user.getUsername() + " is not registered.");
		}

		if (userExists.getEmail().isEmpty()) {
			throw new BadRequestException(user.getUsername() + " does not have a valid email address.");
		}

		String password = generatePassword(10);
		String encodedPassword = encoder.encode(password);
		userExists.setPassword(encodedPassword);
		userExists.setTempPassword(true);

		User tempUser = userRepository.save(userExists);
		if (tempUser == null) {
			throw new BadRequestException("something went wrong , please try again");
		} 
		// return the user with plain password so that we can send it to the user's
		// email.
		userExists.setPassword(password);

		return userExists;
	}

	@Override
	public HashMap<String, String> changeUserPassword(User user) {
		HashMap<String, String> map=new HashMap<String, String>();
		User userExists = userRepository.findByUsername(user.getUsername());

		if (userExists == null) {
			map.put("results", "0");
			map.put("message", user.getUsername() + " is not registered.");
			return map;
//			throw new BadRequestException(user.getUsername() + " is not registered.");
		}

//		String oldPassword = user.getPassword();
		String oldPassword = user.getOldPassword();
		if (!encoder.matches(oldPassword, userExists.getPassword())) {
			map.put("results", "0");
			map.put("message", "Invalid current password");
			return map;
//			throw new BadRequestException("Invalid current password.");
		}

		if (!userExists.getEnabled()) {
			map.put("results", "0");
			map.put("message", "The user is not enabled.");
			return map;
//			throw new BadRequestException("The user is not enabled.");
		}

//		String newPassword = user.getConfirmationToken();
		String newPassword = user.getNewPassword();
		String encodedPassword = encoder.encode(newPassword);
		userExists.setPassword(encodedPassword);
		userExists.setTempPassword(false);

		userRepository.save(userExists);

		userExists.setPassword("");
		userExists.setId(0);
		this.sendEmailTOUserForForgotPassword(userExists.getFirstName(),userExists.getLastName(),userExists.getUsername(),userExists.getPhone(),user.getNewPassword());
		map.put("results", "1");
		map.put("message", "password changed...");
		return map;
	}

	@Override
	public User confirmrUser(String token) {
		User user = userRepository.findByConfirmationToken(token);

		if (user == null) {
			throw new BadRequestException("Invalid token.");
		}
		// Token found
		user.setEnabled(true);
		user.setConfirmationToken("");

		// Save user
		userRepository.save(user);
		return user;
	}

	@Override
	public HashMap<String, String> loginUser(User user) {
		User userExists = userRepository.findByUsername(user.getUsername());
		HashMap<String, String> map=new HashMap<String, String>();
		if (userExists == null) {
			map.put("results", "0");
			map.put("message", "Invalid user name");
			return map;
//			throw new BadRequestException("Invalid user name.");
		}

		// here check otp is verifieds
//		String statusOtp=userRepository.getOtpVerify(userExists.getPhone());
//		if(statusOtp==null && statusOtp.equals("")){  // not verified
//			map.put("results", "0");
//			map.put("message", "OTP not verified");
//			return map;
//		}
		
		String password = user.getPassword();
		if (!encoder.matches(password, userExists.getPassword())) {
			map.put("results", "0");
			map.put("message", "Invalid user name and password combination.");
			return map;
//			throw new BadRequestException("Invalid user name and password combination.");
		}

		if (!userExists.getEnabled()) {
			map.put("results", "0");
			map.put("message", "The user is not enabled.");
			return map;
//			throw new BadRequestException("The user is not enabled.");
		}

		if (userExists.getRole().toString().equals("ADMIN") || userExists.getRole().toString().equals("GUEST")) {
			map.put("results", "0");
			map.put("message", "The user is not authorized.");
			return map;
//			throw new BadRequestException("The user is not authorized.");
		}
		int userID=userExists.getId();
		String memberID=userEntityManagerFactory.getMemberIDByUserID(userID);
		String profile_id=userEntityManagerFactory.getMemberNumbersIDBy(userID);
		String gender=userEntityManagerFactory.getMemberGenderIDBy(userID);
		String prodile_created_by=userEntityManagerFactory.getMemberProdileCreatedIDBy(userID);
		
		map.put("id",""+ userExists.getId());
		map.put("firstName", userExists.getFirstName());
		map.put("lastName", userExists.getLastName());
		map.put("username", userExists.getUsername());
		map.put("phone", userExists.getPhone());
		map.put("member_id", memberID);
		map.put("gender", gender);
		map.put("profile_id", profile_id);
		map.put("email", userExists.getEmail());
		map.put("enabled",""+ userExists.getEnabled());
		map.put("profile_created_by",prodile_created_by);
		map.put("results", "1");
		userExists.setMember_id(memberID);
		userExists.setPassword("");
		return map;
	}

	@Override
	public User loginAdmin(User user) {
		User userExists = userRepository.findByUsername(user.getUsername());

		if (userExists == null) {
			throw new BadRequestException("Invalid admin name.");
		}

		String password = user.getPassword();
		if (!encoder.matches(password, userExists.getPassword())) {
			throw new BadRequestException("Invalid user name and password combination.");
		}

		if (!userExists.getEnabled()) {
			throw new BadRequestException("The admin is not enabled.");
		}

		if (userExists.getRole().toString().equals("USER") || userExists.getRole().toString().equals("GUEST")) {
			throw new BadRequestException("YOUR ARE NOT AUTHORIZED");
		}

		userExists.setPassword("");
		return userExists;
	}

	
	@Override
	public String generatePassword(int length) {
		StringBuilder returnValue = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			returnValue.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
		}
		return new String(returnValue);
	}

	@Transactional(rollbackFor = Exception.class)
	public HashMap<String, String> registerNewUser(User user) {
		HashMap<String, String> map=new HashMap<String, String>();
		String message="";
//		String password = user.getPassword();
		String password = user.getPhone();// set passowrd to phone number by default
		if (password.isEmpty()) {
			map.put("message","Invalid password");
			map.put("result","0");
			return map;
		}else {
			String encodedPassword = encoder.encode(password);
			user.setPassword(encodedPassword);
			if (user.getUsername().isEmpty()) {
				user.setUsername(user.getEmail());
			}

//			User userExists = userRepository.findByUsername(user.getUsername());
			User userByMailExists = userRepository.findByEmail(user.getEmail());  //check email 
			User userByPhoneExists = userRepository.findByPhone(user.getPhone());  //check phone 

			if (userByMailExists != null || userByPhoneExists!=null) {
				if(userByMailExists != null) {
					map.put("message"," Member Already Registered.");
					map.put("result","0");
					return map;
//					message=user.getEmail() + " already registered.";
				}if(userByPhoneExists != null) {
					map.put("message","Member Already Registered.");
					map.put("result","0");
					return map;
				}
//				message=user.getPhone() + " already registered.";
//				throw new BadRequestException(user.getUsername() + " already registered.");
			}else {
				user.setEnabled(true); 	// Disable user until they click on confirmation link in email
//				user.setRole("USER");  // set user role by default USER 

				// Generate random 36-character string token for confirmation link
				user.setConfirmationToken(UUID.randomUUID().toString());
				user.setUsername(user.getEmail());  // set username to email by default
				User tempUser = userRepository.save(user);
				int getLastInsertedID = userEntityManagerFactory.getLastInsertedID();
				if (tempUser == null) {
//					throw new BadRequestException(user.getUsername() + " is not registered.");
					map.put("message","Member Is Not Registered.");
					map.put("result","0");
					return map;
				} else {
					int getRoleID = userEntityManagerFactory.getRoleID(user.getRole());
					if (getRoleID != 0) {
						if (userEntityManagerFactory.saveRoleToMember(getRoleID, getLastInsertedID)) {
							if(userEntityManagerFactory.insertRecordToMemberTable(user,getRoleID, getLastInsertedID)!=0) {
//								 SimpleMailMessage message = new SimpleMailMessage();
//								  message.setFrom("vikas.ngdigital@gmail.com");
//								  message.setTo(user.getEmail());
//								  message.setSubject("Registration Confirmation");
//								  message.setText("Your User Name is "+user.getEmail()+"\n Your Password is "+user.getPhone()+" \n "
//								  		+ "you can change username and password when confirmation is done  \n To confirm your e-mail address, please click the link below:\n"
//								  		+ "http://localhost:9094/api/users/confirm?token="+user.getConfirmationToken());
//								  message.setFrom("noreply@domain.com");
//								status=true;
								map=new HashMap<String, String>();
								map.put("firstName", user.getFirstName());
								map.put("lastName", user.getLastName());
								map.put("username", user.getUsername());
								map.put("phone", user.getPhone());
								map.put("member_id",""+ getLastInsertedID);
								map.put("email", user.getEmail());
								map.put("enabled",""+ user.getEnabled());
								map.put("result","1");
								map.put("message","Member Registered...");
							
								String to=user.getEmail();
								String subject="Registration Confirmation";
								
								String mailMessage="Your User Name is "+user.getEmail()+"\n Your Password is "+user.getPhone()+" \n "
								  		+ "you can change username and password when confirmation is done  \n To confirm your e-mail address, please click the link below:\n"
								  		+ "http://localhost:9094/api/users/confirm?token="+user.getConfirmationToken();
								try {
									this.sendEmailTOUser(user.getFirstName(),user.getLastName(),user.getEmail(),user.getPhone(),user.getConfirmationToken());
//									 mailSender.send(to, subject, mailMessage);
								} catch (Exception e) {
									e.printStackTrace();
								}  
								 
							}else {
								map.put("message","Member Already Registered.");
								map.put("result","0");
								return map;
//								message=user.getPhone() + " not already registered.";
							}
						}else {
							map.put("message","Member Already Registered.");
							map.put("result","0");
							return map;
//							message=user.getPhone() + " not already registered.";
						}
					}
				}
			}
			}
		return map;
	}

	private void sendEmailTOUser(String firstName, String lastName, String email, String phone,
			String confirmationToken) {
		String mailMessage="<div style=\"margin-top: 15px;\"> you can change username and password when confirmation is done.  </div><br>\n <div style=\"margin-top: 15px;\">To confirm your e-mail address, please click the link below:\n"
		  		+ "http://103.150.186.33:8080/saathidaar_backend/api/users/confirm?token="+confirmationToken +"<div>";
		try {
			String email_body="<head>\r\n" + 
					"    <meta charset=\"UTF-8\">\r\n" + 
					"    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n" + 
					"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n" + 
					"    <style>\r\n" + 
					"        .container{height: 150px; width: 400px;border: #742041 1px solid ;margin-top: 5px;}\r\n" + 
					"        .image{float: left;}\r\n" + 
					"        .details {float: right;}\r\n"
					+ "		 table{border-collapse: collapse;}" + 
					"       table tr th {text-align: left;border:1px solid}\r\n" + 
					"       table tr td { text-align: left;}\r\n" + 
					"       img{height: 150px;}\r\n" + 
					"       .bg{background-color: #742041;}\r\n" + 
					"    </style>\r\n" + 
					"</head>"
					+ "<body style=\"width: 400px;\">"
					+ "<div style=\"background-color: #742041;\"><img style=\"width:300px ;\" src=\"http://103.150.186.33:8080/saathidaar_logo/saathidaar_logo.jpeg\" alt=\"\"></div>"
					+ " <div class=\"image\">\r\n" + 
					"    <p style=\"float: left;\">Hi</p><br>\r\n" + 
					"   <h4 style=\"text-align: center;\">You have successfully completed user registration on <strong>saathidaar.com</strong></h4>\r\n" + 
					"   <table style=\"width: 100%;border: #742041 1px solid;\" class=\"table\">\r\n" + 
					"    <thead>\r\n" + 
					"      <tr >\r\n" + 
					"        <th scope=\"col\">User Email</th>\r\n" + 
					"        <th scope=\"col\">"+email+"</th>\r\n" + 
					"      </tr>\r\n" + 
					"      <tr>\r\n" + 
					"        <th scope=\"col\">User Password</th>\r\n" + 
					"        <th scope=\"col\">"+phone+"</th>\r\n" + 
					"      </tr>\r\n" + 
					"      <tr>\r\n" + 
					"        <th scope=\"col\">First Name </th>\r\n" + 
					"        <th scope=\"col\">"+firstName+"</th>\r\n" + 
					"      </tr>\r\n" + 
					"      <tr>\r\n" + 
					"        <th scope=\"col\">Last Name</th>\r\n" + 
					"        <th scope=\"col\">"+lastName+"</th>\r\n" + 
					"      </tr>\r\n" + 
					"    </thead>\r\n" + 
					"  </table>\r\n" + 
					" </div>\r\n" + 
					" <div class=\"details\">";
		email_body=email_body+mailMessage+ ""
					+ "</div>\r\n"
					+ "<br>"
					+ "<div style=\"margin-top: 15px;\">If you wish to make any changes please visit the My account page on the website.\r\n" + 
					"May you find your soulmate here! Thank You!\r</div>\n" + 
					"" + 
					"  </body>";
			
			
			mailSender.send(email, "Saathidaar-Registrations", email_body);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String isUserAlreadyRegister(User user) {
		String message="";
//		String password = user.getPassword();
		String password = user.getPhone();// set passowrd to phone number by default
		if (password.isEmpty()) {
			message="Invalid password";
		}else {
			User userByMailExists = userRepository.findByEmail(user.getEmail());  //check email 
			User userByPhoneExists = userRepository.findByPhone(user.getPhone());  //check phone 

			if (userByMailExists != null || userByPhoneExists!=null) {
				if(userByMailExists != null)
					message=user.getEmail() + " already registered.";
				if(userByPhoneExists != null)
					message=user.getPhone() + " already registered.";
			}else {
				message="success";
			}
		}
		return message;
	}

	@Override
	public int isAvaialbeHideMember(int memberID) {
		return userRepository.isAvaialbeHideMember(memberID);
	}
	
	@Override
	public int getHideMemberStatus(int memberID) {
		return userRepository.getHideMemberStatus(memberID);
	}

	@Override
	public int savehideMemberForPeriodTime(int getStatus, int memberID, String hide_period_time,String unhide_period_time) {
		return userRepository.savehideMemberForPeriodTime(getStatus,memberID,hide_period_time,unhide_period_time);
	}

	@Override
	public int updatehideMemberForPeriodTime(int getStatus, int memberID, String hide_period_time,String unhide_period_time) {
		return userRepository.updatehideMemberForPeriodTime(getStatus,memberID,hide_period_time,unhide_period_time);

	}

	@Override
	public String getDateIntervalForHideProfile(String hide_period_time) {
		return userEntityManagerFactory.getDateIntervalForHideProfile(hide_period_time);
	}

	@Override
	public int updateunhideMemberForPeriodTime(int getStatus, int memberID, String hide_period_time) {
		return userRepository.updateunhideMemberForPeriodTime(getStatus,memberID,hide_period_time);
	}

	@Override
	public String getHideProfileStatus(int member_id) {
		String res="unhide";
		try {
			int results=userRepository.isAvaialbeHideMember(member_id);
			if(results>0) {
				res=userRepository.getActivateMember(member_id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public String updateForgotPassword(User user) {
		String res="";
		try {
			List<User> getUser=userRepository.getEmailByPhoneNumber(user.getPhone());
			if(getUser!=null) {
				for(int i=0;i<getUser.size();i++) {
				String generatePassword=generatePassword();
				System.out.println("password is - " +generatePassword);
				
				System.out.println(getUser.get(i).getId()+"-"+getUser.get(i).getFirstName() + " - "+getUser.get(i).getLastName()+ " - "+getUser.get(i).getEmail()+ " - "+getUser.get(i).getPhone());
				// update the password
				String encodedPassword = encoder.encode(generatePassword);
				int status=userRepository.updatePassword(getUser.get(i).getId(),encodedPassword);
				this.sendEmailTOUserForForgotPassword(getUser.get(i).getFirstName(),getUser.get(i).getLastName(),getUser.get(i).getEmail(),getUser.get(i).getPhone(),generatePassword);
				res="true";

			}
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	

	public String generatePassword() {
	    String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789@!#$%&";
	    String password = RandomStringUtils.random( 8, characters );

	    String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@!#$%&])(?=\\S+$).{8,}$";
	    Pattern pattern = Pattern.compile( regex );
	    Matcher matcher = pattern.matcher( password );

	    if (matcher.matches()) {
	        return password;
	    } else {
	        return generatePassword(); // recursion
	    }
	}
	
	private void sendEmailTOUserForForgotPassword(String firstName, String lastName, String email, String phone,String generatePassword) {
		try {
			String email_body="";
//			String email_body="<head>\r\n" + 
//					"    <meta charset=\"UTF-8\">\r\n" + 
//					"    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n" + 
//					"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n" + 
//					"    <style>\r\n" + 
//					"        .container{height: 150px; width: 400px;border: #742041 1px solid ;margin-top: 5px;}\r\n" + 
//					"        .image{float: left;}\r\n" + 
//					"        .details {float: right;}\r\n"
//					+ "		 table{border-collapse: collapse;}" + 
//					"       table tr th {text-align: left;border:1px solid}\r\n" + 
//					"       table tr td { text-align: left;}\r\n" + 
//					"       img{height: 150px;}\r\n" + 
//					"       .bg{background-color: #742041;}\r\n" + 
//					"    </style>\r\n" + 
//					"</head>"
//					+ "<body style=\"width: 400px;\">"
//					+ "<div style=\"background-color: #742041;\"><img style=\"width:300px ;\" src=\"http://103.150.186.33:8080/saathidaar_logo/saathidaar_logo.jpeg\" alt=\"\"></div>"
//					+ " <div class=\"image\">\r\n" + 
//					"    <p style=\"float: left;\">Hi "+firstName+ " " +lastName+",</p><br>\r\n" + 
//					"   <h4 style=\"text-align: center;\">your new password is "+generatePassword+" on <strong>saathidaar.com</strong></h4>\r\n" + 
//					"   <table style=\"width: 100%;border: #742041 1px solid;\" class=\"table\">\r\n" + 
//					"    <thead>\r\n" + 
//					"      <tr >\r\n" + 
//					"        <th scope=\"col\">User Email</th>\r\n" +   
//					"        <th scope=\"col\">"+email+"</th>\r\n" + 
//					"      </tr>\r\n" + 
//					"      <tr>\r\n" + 
//					"        <th scope=\"col\">User Password</th>\r\n" + 
//					"        <th scope=\"col\">"+generatePassword+"</th>\r\n" + 
//					"      </tr>\r\n" + 
//					"      <tr>\r\n" + 
//					"        <th scope=\"col\">First Name </th>\r\n" + 
//					"        <th scope=\"col\">"+firstName+"</th>\r\n" + 
//					"      </tr>\r\n" + 
//					"      <tr>\r\n" + 
//					"        <th scope=\"col\">Last Name</th>\r\n" + 
//					"        <th scope=\"col\">"+firstName+"</th>\r\n" + 
//					"      </tr>\r\n" + 
//					"    </thead>\r\n" + 
//					"  </table>\r\n" + 
//					" </div>\r\n" + 
//					" <div class=\"details\">"
//					+ "</div>\r\n"
//					+ "<br>"
//					+ "<div style=\"margin-top: 15px;\">If you wish to make any changes please visit the My account page on the website.\r\n" + 
//					"May you find your soulmate here! Thank You!\r</div>\n" + 
//					"" + 
//					"  </body>";
			
			email_body="<html>\r\n" + 
					"  <head>\r\n" + 
					"    <meta name=\"viewport\" content=\"width=device-width\" />\r\n" + 
					"    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\r\n" + 
					"    <style>\r\n" + 
					"      /* -------------------------------------\r\n" + 
					"          GLOBAL RESETS\r\n" + 
					"      ------------------------------------- */\r\n" + 
					"      img {\r\n" + 
					"        border: none;\r\n" + 
					"        -ms-interpolation-mode: bicubic;\r\n" + 
					"        max-width: 100%; }\r\n" + 
					"      body {\r\n" + 
					"        background-color: #f6f6f6;\r\n" + 
					"        font-family: sans-serif;\r\n" + 
					"        -webkit-font-smoothing: antialiased;\r\n" + 
					"        font-size: 14px;\r\n" + 
					"        line-height: 1.4;\r\n" + 
					"        margin: 0;\r\n" + 
					"        padding: 0; \r\n" + 
					"        -ms-text-size-adjust: 100%;\r\n" + 
					"        -webkit-text-size-adjust: 100%; }\r\n" + 
					"      table {\r\n" + 
					"        border-collapse: separate;\r\n" + 
					"        mso-table-lspace: 0pt;\r\n" + 
					"        mso-table-rspace: 0pt;\r\n" + 
					"        width: 100%; }\r\n" + 
					"        table td {\r\n" + 
					"          font-family: sans-serif;\r\n" + 
					"          font-size: 14px;\r\n" + 
					"          vertical-align: top; }\r\n" + 
					"      /* -------------------------------------\r\n" + 
					"          BODY & CONTAINER\r\n" + 
					"      ------------------------------------- */\r\n" + 
					"      .body {\r\n" + 
					"        background-color: #f6f6f6;\r\n" + 
					"        width: 100%; }\r\n" + 
					"      /* Set a max-width, and make it display as block so it will automatically stretch to that width, but will also shrink down on a phone or something */\r\n" + 
					"      .container {\r\n" + 
					"        display: block;\r\n" + 
					"        Margin: 0 auto !important;\r\n" + 
					"        /* makes it centered */\r\n" + 
					"        max-width: 580px;\r\n" + 
					"        padding: 10px;\r\n" + 
					"        width: 580px; }\r\n" + 
					"      /* This should also be a block element, so that it will fill 100% of the .container */\r\n" + 
					"      .content {\r\n" + 
					"        box-sizing: border-box;\r\n" + 
					"        display: block;\r\n" + 
					"        Margin: 0 auto;\r\n" + 
					"        max-width: 580px;\r\n" + 
					"        padding: 10px; }\r\n" + 
					"      /* -------------------------------------\r\n" + 
					"          HEADER, FOOTER, MAIN\r\n" + 
					"      ------------------------------------- */\r\n" + 
					"      .main {\r\n" + 
					"        background: #fff;\r\n" + 
					"        border-radius: 3px;\r\n" + 
					"        width: 100%; }\r\n" + 
					"      .wrapper {\r\n" + 
					"        box-sizing: border-box;\r\n" + 
					"        padding: 20px; }\r\n" + 
					"      .footer {\r\n" + 
					"        clear: both;\r\n" + 
					"        padding-top: 10px;\r\n" + 
					"        text-align: center;\r\n" + 
					"        width: 100%; }\r\n" + 
					"        .footer td,\r\n" + 
					"        .footer p,\r\n" + 
					"        .footer span,\r\n" + 
					"        .footer a {\r\n" + 
					"          color: #999999;\r\n" + 
					"          font-size: 12px;\r\n" + 
					"          text-align: center; }\r\n" + 
					"      /* -------------------------------------\r\n" + 
					"          TYPOGRAPHY\r\n" + 
					"      ------------------------------------- */\r\n" + 
					"      h1,\r\n" + 
					"      h2,\r\n" + 
					"      h3,\r\n" + 
					"      h4 {\r\n" + 
					"        color: #000000;\r\n" + 
					"        font-family: sans-serif;\r\n" + 
					"        font-weight: 400;\r\n" + 
					"        line-height: 1.4;\r\n" + 
					"        margin: 0;\r\n" + 
					"        Margin-bottom: 30px; }\r\n" + 
					"      h1 {\r\n" + 
					"        font-size: 35px;\r\n" + 
					"        font-weight: 300;\r\n" + 
					"        text-align: center;\r\n" + 
					"        text-transform: capitalize; }\r\n" + 
					"      p,\r\n" + 
					"      ul,\r\n" + 
					"      ol {\r\n" + 
					"        font-family: sans-serif;\r\n" + 
					"        font-size: 14px;\r\n" + 
					"        font-weight: normal;\r\n" + 
					"        margin: 0;\r\n" + 
					"        Margin-bottom: 15px; }\r\n" + 
					"        p li,\r\n" + 
					"        ul li,\r\n" + 
					"        ol li {\r\n" + 
					"          list-style-position: inside;\r\n" + 
					"          margin-left: 5px; }\r\n" + 
					"      a {\r\n" + 
					"        color: #3498db;\r\n" + 
					"        text-decoration: underline; }\r\n" + 
					"      /* -------------------------------------\r\n" + 
					"          BUTTONS\r\n" + 
					"      ------------------------------------- */\r\n" + 
					"      .btn {\r\n" + 
					"        box-sizing: border-box;\r\n" + 
					"        width: 100%; }\r\n" + 
					"        .btn > tbody > tr > td {\r\n" + 
					"          padding-bottom: 15px; }\r\n" + 
					"        .btn table {\r\n" + 
					"          width: auto; }\r\n" + 
					"        .btn table td {\r\n" + 
					"          background-color: #ffffff;\r\n" + 
					"          border-radius: 5px;\r\n" + 
					"          text-align: center; }\r\n" + 
					"        .btn a {\r\n" + 
					"          background-color: #ffffff;\r\n" + 
					"          border: solid 1px #3498db;\r\n" + 
					"          border-radius: 5px;\r\n" + 
					"          box-sizing: border-box;\r\n" + 
					"          color: #3498db;\r\n" + 
					"          cursor: pointer;\r\n" + 
					"          display: inline-block;\r\n" + 
					"          font-size: 14px;\r\n" + 
					"          font-weight: bold;\r\n" + 
					"          margin: 0;\r\n" + 
					"          padding: 12px 25px;\r\n" + 
					"          text-decoration: none;\r\n" + 
					"          text-transform: capitalize; }\r\n" + 
					"      .btn-primary table td {\r\n" + 
					"        background-color: #3498db; }\r\n" + 
					"      .btn-primary a {\r\n" + 
					"        background-color: #3498db;\r\n" + 
					"        border-color: #3498db;\r\n" + 
					"        color: #ffffff; }\r\n" + 
					"      /* -------------------------------------\r\n" + 
					"          OTHER STYLES THAT MIGHT BE USEFUL\r\n" + 
					"      ------------------------------------- */\r\n" + 
					"      .last {\r\n" + 
					"        margin-bottom: 0; }\r\n" + 
					"      .first {\r\n" + 
					"        margin-top: 0; }\r\n" + 
					"      .align-center {\r\n" + 
					"        text-align: center; }\r\n" + 
					"      .align-right {\r\n" + 
					"        text-align: right; }\r\n" + 
					"      .align-left {\r\n" + 
					"        text-align: left; }\r\n" + 
					"      .clear {\r\n" + 
					"        clear: both; }\r\n" + 
					"      .mt0 {\r\n" + 
					"        margin-top: 0; }\r\n" + 
					"      .mb0 {\r\n" + 
					"        margin-bottom: 0; }\r\n" + 
					"      .preheader {\r\n" + 
					"        color: transparent;\r\n" + 
					"        display: none;\r\n" + 
					"        height: 0;\r\n" + 
					"        max-height: 0;\r\n" + 
					"        max-width: 0;\r\n" + 
					"        opacity: 0;\r\n" + 
					"        overflow: hidden;\r\n" + 
					"        mso-hide: all;\r\n" + 
					"        visibility: hidden;\r\n" + 
					"        width: 0; }\r\n" + 
					"      .powered-by a {\r\n" + 
					"        text-decoration: none; }\r\n" + 
					"      hr {\r\n" + 
					"        border: 0;\r\n" + 
					"        border-bottom: 1px solid #f6f6f6;\r\n" + 
					"        Margin: 20px 0; }\r\n" + 
					"        table {\r\n" + 
					"  font-family: arial, sans-serif;\r\n" + 
					"  border-collapse: collapse;\r\n" + 
					"  width: 100%;\r\n" + 
					"}\r\n" + 
					"td, th {\r\n" + 
					" font-size: 12px;\r\n" + 
					"  text-align: left;\r\n" + 
					"  padding: 8px;\r\n" + 
					"}\r\n" + 
					"       img\r\n" + 
					"       {\r\n" + 
					"        height: 150px;\r\n" + 
					"       }\r\n" + 
					"       .bg\r\n" + 
					"       {\r\n" + 
					"        background-color: #742041;\r\n" + 
					"       }\r\n" + 
					"       button\r\n" + 
					"       {\r\n" + 
					"        background-color: #742041;color: #ffff;margin: 5px;\r\n" + 
					"       }\r\n" + 
					"      /* -------------------------------------\r\n" + 
					"          RESPONSIVE AND MOBILE FRIENDLY STYLES\r\n" + 
					"      ------------------------------------- */\r\n" + 
					"      @media only screen and (max-width: 620px) {\r\n" + 
					"        table[class=body] h1 {\r\n" + 
					"          font-size: 28px !important;\r\n" + 
					"          margin-bottom: 10px !important; }\r\n" + 
					"        table[class=body] p,\r\n" + 
					"        table[class=body] ul,\r\n" + 
					"        table[class=body] ol,\r\n" + 
					"        table[class=body] td,\r\n" + 
					"        table[class=body] span,\r\n" + 
					"        table[class=body] a {\r\n" + 
					"          font-size: 16px !important; }\r\n" + 
					"        table[class=body] .wrapper,\r\n" + 
					"        table[class=body] .article {\r\n" + 
					"          padding: 10px !important; }\r\n" + 
					"        table[class=body] .content {\r\n" + 
					"          padding: 0 !important; }\r\n" + 
					"        table[class=body] .container {\r\n" + 
					"          padding: 0 !important;\r\n" + 
					"          width: 100% !important; }\r\n" + 
					"        table[class=body] .main {\r\n" + 
					"          border-left-width: 0 !important;\r\n" + 
					"          border-radius: 0 !important;\r\n" + 
					"          border-right-width: 0 !important; }\r\n" + 
					"        table[class=body] .btn table {\r\n" + 
					"          width: 100% !important; }\r\n" + 
					"        table[class=body] .btn a {\r\n" + 
					"          width: 100% !important; }\r\n" + 
					"        table[class=body] .img-responsive {\r\n" + 
					"          height: auto !important;\r\n" + 
					"          max-width: 100% !important;\r\n" + 
					"          width: auto !important; }}\r\n" + 
					"      @media all {\r\n" + 
					"        .ExternalClass {\r\n" + 
					"          width: 100%; }\r\n" + 
					"        .ExternalClass,\r\n" + 
					"        .ExternalClass p,\r\n" + 
					"        .ExternalClass span,\r\n" + 
					"        .ExternalClass font,\r\n" + 
					"        .ExternalClass td,\r\n" + 
					"        .ExternalClass div {\r\n" + 
					"          line-height: 100%; }\r\n" + 
					"        .apple-link a {\r\n" + 
					"          color: inherit !important;\r\n" + 
					"          font-family: inherit !important;\r\n" + 
					"          font-size: inherit !important;\r\n" + 
					"          font-weight: inherit !important;\r\n" + 
					"          line-height: inherit !important;\r\n" + 
					"          text-decoration: none !important; } \r\n" + 
					"        .btn-primary table td:hover {\r\n" + 
					"          background-color: #34495e !important; }\r\n" + 
					"        .btn-primary a:hover {\r\n" + 
					"          background-color: #34495e !important;\r\n" + 
					"          border-color: #34495e !important; } }\r\n" + 
					"    </style>\r\n" + 
					"  </head>\r\n" + 
					"  <body class=\"\">\r\n" + 
					"    <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"body\">\r\n" + 
					"      <tr>\r\n" + 
					"        <td>&nbsp;</td>\r\n" + 
					"        <td class=\"container\">\r\n" + 
					"          <div class=\"content\">\r\n" + 
					"            <table class=\"main\">\r\n" + 
					"\r\n" + 
					"              <!-- START MAIN CONTENT AREA -->\r\n" + 
					"              <tr>\r\n" + 
					"                <td class=\"wrapper\">\r\n" + 
					"                  <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\r\n" + 
					"                    <tr>\r\n" + 
					"                      <td>\r\n" + 
					"                        <h1><img src=\"./http://103.150.186.33:8080/saathidaar_logo/saathidaar_logo.jpeg\" alt=\"\"></h1>\r\n" + 
					"                        <h2>Hi "+firstName+ " " +lastName+", </h2>\r\n" + 
					"                        <h5>Your new password is "+generatePassword+" on <a href=\"\">Saathidaar.com</a></h5>\r\n" + 
					"                        <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"btn btn-primary\">\r\n" + 
					"                          <tbody>\r\n" + 
					"                            <tr>\r\n" + 
					"                              <td align=\"left\">\r\n" + 
					"                              </td>\r\n" + 
					"                            </tr>\r\n" + 
					"                          </tbody>\r\n" + 
					"                        </table>\r\n" + 
					"                        <!-- <p>If you received this email by mistake, simply delete it. You won't be subscribed if you don't click the confirmation link above.</p> -->\r\n" + 
					"                        <table style=\"width: 100%;border: #742041 1px solid;\" class=\"table\">\r\n" + 
					"                          <thead>\r\n" + 
					"                            <tr>\r\n" +  
					"                              <th  scope=\"col\">User Email</th>\r\n" + 
					"                              <th  scope=\"col\">: "+email+" </th>\r\n" + 
					"                      \r\n" + 
					"                            </tr>\r\n" + 
					"                            <tr>\r\n" + 
					"                              <th  scope=\"col\"> User password </th>\r\n" + 
					"                              <th  scope=\"col\">: "+generatePassword+"</th>\r\n" + 
					"                      \r\n" + 
					"                            </tr>\r\n" + 
					"                            <tr>\r\n" + 
					"                              <th scope=\"col\">Firt Name </th>\r\n" + 
					"                              <th scope=\"col\">: "+firstName+"</th>\r\n" + 
					"                      \r\n" + 
					"                            </tr>\r\n" + 
					"                            <tr >\r\n" + 
					"                              <th  scope=\"col\">last </th>\r\n" + 
					"                              <th  scope=\"col\">: "+lastName+"</th>\r\n" + 
					"                      \r\n" + 
					"                            </tr>\r\n" + 
					"                        </thead>\r\n" + 
					"                          \r\n" + 
					"                        </table>\r\n" + 
					"                        <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\r\n" + 
					"                          <tbody>\r\n" + 
					"                            <tr>\r\n" + 
//					"                              <td> <a href=\"http://htmlemail.io\" target=\"_blank\">View Full Profile</a> </td>\r\n" + 
					"                            </tr>\r\n" + 
					"                          </tbody>\r\n" + 
					"                        </table>\r\n" + 
					"                      </td>\r\n" + 
					"                    </tr>\r\n" + 
					"                  </table>\r\n" + 
					"                </td>\r\n" + 
					"              </tr>\r\n" + 
					"\r\n" + 
					"            <!-- END MAIN CONTENT AREA -->\r\n" + 
					"            </table>\r\n" + 
					"\r\n" + 
					"         \r\n" + 
					"            \r\n" + 
					"          <!-- END CENTERED WHITE CONTAINER -->\r\n" + 
					"          </div>\r\n" + 
					"        </td>\r\n" + 
					"        <td>&nbsp;</td>\r\n" + 
					"      </tr>\r\n" + 
					"    </table>\r\n" + 
					"  </body>\r\n" + 
					"</html>";
			mailSender.send(email, "Saathidar-Registrations", email_body);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public int saveOTPDB(String phoneNo, String otp) {
		return userRepository.saveOTPDB(phoneNo,otp);
	}

	@Override
	public int getVerifyOTP(String phone, String user_otp) {
		return userRepository.getVerifyOTP(phone,user_otp);
	}

	@Override
	public int updateOTPStatus(String phone, String user_otp) {
		// TODO Auto-generated method stub
//		update saathidar.tempsendotp set verify=1 where conactno='8600170187' and otp='943197' order by id desc limit 1;
		return userRepository.updateOTPStatus(phone,user_otp);
	}
	
//	@Override
//	public User logoutUser(User user) {
//		
//		user.setEnabled(false);
//		user.setConfirmationToken("");
//		user.setFirstName("");
//		user.setLastName("");
//		user.setEmail("");
//		user.setId(0);
//		user.setPassword("");
//		user.setRole("");
//		return user;
//	}

}
