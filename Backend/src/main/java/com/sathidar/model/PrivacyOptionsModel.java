package com.sathidar.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

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
	private Integer member_id;
	
	@Transient
	private String valPhone;
	
	@Transient
	private String valEmail;
	
	@Transient
	private String valPhoto;
	
	@Transient
	private String valDob;
	
	@Transient
	private String valAnnual_income;
	
	@Transient
	private String valHoroscope;
	
	@Transient
	private String valVisitors_setting;
	
	@Transient
	private String valShortlist_setting;
	
	@Transient
	private String valProfile_privacy;
	
	public String getValPhone() {
		return valPhone;
	}
	public void setValPhone(String valPhone) {
		this.valPhone = valPhone;
	}
	public String getValEmail() {
		return valEmail;
	}
	public void setValEmail(String valEmail) {
		this.valEmail = valEmail;
	}
	public String getValPhoto() {
		return valPhoto;
	}
	public void setValPhoto(String valPhoto) {
		this.valPhoto = valPhoto;
	}
	public String getValDob() {
		return valDob;
	}
	public void setValDob(String valDob) {
		this.valDob = valDob;
	}
	public String getValAnnual_income() {
		return valAnnual_income;
	}
	public void setValAnnual_income(String valAnnual_income) {
		this.valAnnual_income = valAnnual_income;
	}
	public String getValHoroscope() {
		return valHoroscope;
	}
	public void setValHoroscope(String valHoroscope) {
		this.valHoroscope = valHoroscope;
	}
	public String getValVisitors_setting() {
		return valVisitors_setting;
	}
	public void setValVisitors_setting(String valVisitors_setting) {
		this.valVisitors_setting = valVisitors_setting;
	}
	public String getValShortlist_setting() {
		return valShortlist_setting;
	}
	public void setValShortlist_setting(String valShortlist_setting) {
		this.valShortlist_setting = valShortlist_setting;
	}
	public String getValProfile_privacy() {
		return valProfile_privacy;
	}
	public void setValProfile_privacy(String valProfile_privacy) {
		this.valProfile_privacy = valProfile_privacy;
	}
	public Integer getMember_id() {
		return member_id;
	}
	public void setMember_id(Integer member_id) {
		this.member_id = member_id;
	}
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
