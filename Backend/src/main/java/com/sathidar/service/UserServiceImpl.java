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
		User tempUser = userRepository.save(user);
		int getLastInsertedID = userEntityManagerFactory.getLastInsertedID();
		if (tempUser == null) {
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
		
		
		map.put("id",""+ userExists.getId());
		map.put("firstName", userExists.getFirstName());
		map.put("lastName", userExists.getLastName());
		map.put("username", userExists.getUsername());
		map.put("phone", userExists.getPhone());
		map.put("member_id", memberID);
		map.put("email", userExists.getEmail());
		map.put("enabled",""+ userExists.getEnabled());
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

	@Override
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
