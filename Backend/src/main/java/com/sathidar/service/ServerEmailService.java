package com.sathidar.service;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

@Service
public class ServerEmailService {

	final static String from_mail="info@saathidaar.com";
	
	public static void send(String to_email,String sub,String msg){ 
		Properties props = new Properties();  
	       props.put("mail.smtp.auth", "false");  
	       props.put("mail.smtp.starttls.enable", "false");  
	       props.put("mail.smtp.host", "host");  
	       props.put("mail.smtp.port", "25");  
	           try {  
	           Session session = Session.getInstance(props);  
	           Message message = new MimeMessage(session);  
	           message.setFrom(new InternetAddress(from_mail));  
	           message.setRecipients(Message.RecipientType.TO,  
	               InternetAddress.parse(to_email));  
	           message.setSubject(sub);  
	           message.setText(msg);  
	           Transport.send(message);  
	           System.out.println("Done");  
	       } catch (MessagingException e) {  
	           throw new RuntimeException(e);  
	       }  
	}
}
