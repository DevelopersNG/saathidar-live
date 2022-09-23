package com.sathidar.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class EmailSMSAlertModel {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String premium_match_mail;
	
	private String recent_visitors_email;
	
	private String today_match_email;

	private String member_id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPremium_match_mail() {
		return premium_match_mail;
	}

	public void setPremium_match_mail(String premium_match_mail) {
		this.premium_match_mail = premium_match_mail;
	}

	public String getRecent_visitors_email() {
		return recent_visitors_email;
	}

	public void setRecent_visitors_email(String recent_visitors_email) {
		this.recent_visitors_email = recent_visitors_email;
	}

	public String getToday_match_email() {
		return today_match_email;
	}

	public void setToday_match_email(String today_match_email) {
		this.today_match_email = today_match_email;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public EmailSMSAlertModel(Integer id, String premium_match_mail, String recent_visitors_email,
			String today_match_email, String member_id) {
		super();
		this.id = id;
		this.premium_match_mail = premium_match_mail;
		this.recent_visitors_email = recent_visitors_email;
		this.today_match_email = today_match_email;
		this.member_id = member_id;
	}

	public EmailSMSAlertModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
