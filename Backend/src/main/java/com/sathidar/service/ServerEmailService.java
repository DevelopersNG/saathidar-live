package com.sathidar.service;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service
public class ServerEmailService {

	final static String from_mail="info@saathidaar.com";
	final static String smtpHostServer="www.saathidaar.com";
	
	public static void send(String to_email,String subject,String body){ 
		
	    
		String host = "www.saathidaar.com";
		final String username = "info@saathidaar.com";
		final String password = "b?!A0j[AowjJ";

		Properties props = new Properties();

		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		// go to account setting -> smtp setting and get server name and port
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", 587);
		props.put("mail.debug", "true");

		try {
		    Session session = Session.getInstance(props,
		            new javax.mail.Authenticator() {
		                protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
		                    return new javax.mail.PasswordAuthentication(username, password);
		                }
		            });

		    Message msg = new MimeMessage(session);

		    msg.setFrom(new InternetAddress(from_mail));
		    msg.setContent(body, "text/html; charset=utf-8");
		    msg.setRecipients(Message.RecipientType.TO,
		            InternetAddress.parse("vikasuttamaher@gmail.com"));
		    msg.setSubject("Test");
//		    msg.setText(body);
		    Transport.send(msg,username,password);
		    System.out.println("Sent message successfully...");
	}catch(Exception e) {
		e.printStackTrace();
	}
	}
}
