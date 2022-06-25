package com.sathidar.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="")
public class PrivacyOptionsModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private Integer phone;
	private Integer email;
	private Integer photo;
	private Integer dob;
	private Integer annual_income;
	private Integer horoscope;
	private Integer visitors_setting;
	private Integer shortlist_setting;
	private Integer profile_privacy;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPhone() {
		return phone;
	}
	public void setPhone(Integer phone) {
		this.phone = phone;
	}
	public Integer getEmail() {
		return email;
	}
	public void setEmail(Integer email) {
		this.email = email;
	}
	public Integer getPhoto() {
		return photo;
	}
	public void setPhoto(Integer photo) {
		this.photo = photo;
	}
	public Integer getDob() {
		return dob;
	}
	public void setDob(Integer dob) {
		this.dob = dob;
	}
	public Integer getAnnual_income() {
		return annual_income;
	}
	public void setAnnual_income(Integer annual_income) {
		this.annual_income = annual_income;
	}
	public Integer getHoroscope() {
		return horoscope;
	}
	public void setHoroscope(Integer horoscope) {
		this.horoscope = horoscope;
	}
	public Integer getVisitors_setting() {
		return visitors_setting;
	}
	public void setVisitors_setting(Integer visitors_setting) {
		this.visitors_setting = visitors_setting;
	}
	public Integer getShortlist_setting() {
		return shortlist_setting;
	}
	public void setShortlist_setting(Integer shortlist_setting) {
		this.shortlist_setting = shortlist_setting;
	}
	public Integer getProfile_privacy() {
		return profile_privacy;
	}
	public void setProfile_privacy(Integer profile_privacy) {
		this.profile_privacy = profile_privacy;
	}
	public PrivacyOptionsModel(Integer id, Integer phone, Integer email, Integer photo, Integer dob,
			Integer annual_income, Integer horoscope, Integer visitors_setting, Integer shortlist_setting,
			Integer profile_privacy) {
		super();
		this.id = id;
		this.phone = phone;
		this.email = email;
		this.photo = photo;
		this.dob = dob;
		this.annual_income = annual_income;
		this.horoscope = horoscope;
		this.visitors_setting = visitors_setting;
		this.shortlist_setting = shortlist_setting;
		this.profile_privacy = profile_privacy;
	}
	public PrivacyOptionsModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

}
