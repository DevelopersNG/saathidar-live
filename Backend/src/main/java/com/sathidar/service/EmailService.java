package com.sathidar.service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

//	private JavaMailSender javaMailSender;
//
//	public EmailService(JavaMailSender javaMailSender) {
//        this.javaMailSender = javaMailSender;
//    }
//	
//
//	public void sendEmail(SimpleMailMessage message) {
//		javaMailSender.send(message);
//	}
	
	static Properties mailServerProperties;
	static Session getMailSession;
	static MimeMessage generateMailMessage;
    final static String fromEmail = "azimngdigital@gmail.com"; //requires valid gmail id
	final static String password = "wsqvtbapdgcfavba"; // correct password for gmail id

	public static void send(String to,String sub,String msg){  
        //Get properties object    
        Properties props = new Properties();   
        try {
    		
    		mailServerProperties = System.getProperties();
    		mailServerProperties.put("mail.smtp.port", "587");
    		mailServerProperties.put("mail.smtp.auth", "true");
    		mailServerProperties.put("mail.smtp.starttls.enable", "true");
    		System.out.println("Mail Server Properties have been setup successfully..");
     
    		// Step2
    		System.out.println("\n\n 2nd ===> get Mail Session..");
    		getMailSession = Session.getDefaultInstance(mailServerProperties, null);
    		generateMailMessage = new MimeMessage(getMailSession);
    		generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
    		generateMailMessage.addRecipient(Message.RecipientType.CC, new InternetAddress("ngdigitaldurga.0308@gmail.com"));
    		generateMailMessage.setSubject(sub);
    		String emailBody = msg;
    		generateMailMessage.setContent(emailBody, "text/html");
    		System.out.println("Mail Session has been created successfully..");
     
    		// Step3
    		System.out.println("\n\n 3rd ===> Get Session and Send mail");
    		Transport transport = getMailSession.getTransport("smtp");
     
    		// Enter your correct gmail UserID and Password
    		// if you have 2FA enabled then provide App Specific Password
    		transport.connect("smtp.gmail.com", fromEmail, password);
    		transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
    		transport.close();
    		
    		System.out.println("\n\n 4th ===> Mail Send");
    		   
//    	EmailUtil.sendAttachmentEmail(session, toEmail,"SSLEmail Testing Subject with Attachment", "SSLEmail Testing Body with Attachment");
//      EmailUtil.sendImageEmail(session, toEmail,"SSLEmail Testing Subject with Image", "SSLEmail Testing Body with Image");

		} catch (Exception e) {
		 e.printStackTrace();
		}
	}
}
