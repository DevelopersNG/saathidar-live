package com.sathidar.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="member_preference")
public class MemberPreferenceModel {

	@Id
	@Column(name = "member_preference_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String gender;
	
	private String	lifestyle;
	
	private String job;
	
	private String education;
	
	private int cast_id;

	private int subcaste_id;
		
	private int religion_id;
		
	private int state_id;
		
	private int city_id;
	
	private int fromage;
	
	private int toage;
	
	@Transient
	private String	cast_name;
	
	@Transient
	private String	state_name;

	@Transient
	private String religion_name;

	@Transient
	private String	sub_cast_name;
	
	@Transient
	private String city_name;
	
	
//	--------------------- new update partner preferenece 27-4-21------------------------------------
	
	private String	partner_marital_status;
	private String	partner_mother_tongue;
	private String	partner_qualification;
	private String	partner_working_with;
	private String	partner_professional_area;
	private String	partner_religions;
	private String	partner_cast;
	private String	partner_country;
	private String	partner_state;
	private String	partner_city;	
	private String	partner_from_age;
	private String	partner_to_age;
	private String	partner_from_height;
	private String	partner_to_height;
	private String	partner_manglik_all;
	private String	partner_annual_income;
	private String	partner_profile_created;
	private String	partner_lifestyles;
	
	
	public String getPartner_marital_status() {
		return partner_marital_status;
	}

	public void setPartner_marital_status(String partner_marital_status) {
		this.partner_marital_status = partner_marital_status;
	}

	public String getPartner_mother_tongue() {
		return partner_mother_tongue;
	}

	public void setPartner_mother_tongue(String partner_mother_tongue) {
		this.partner_mother_tongue = partner_mother_tongue;
	}

	public String getPartner_qualification() {
		return partner_qualification;
	}

	public void setPartner_qualification(String partner_qualification) {
		this.partner_qualification = partner_qualification;
	}

	public String getPartner_working_with() {
		return partner_working_with;
	}

	public void setPartner_working_with(String partner_working_with) {
		this.partner_working_with = partner_working_with;
	}

	public String getPartner_professional_area() {
		return partner_professional_area;
	}

	public void setPartner_professional_area(String partner_professional_area) {
		this.partner_professional_area = partner_professional_area;
	}

	public String getPartner_religions() {
		return partner_religions;
	}

	public void setPartner_religions(String partner_religions) {
		this.partner_religions = partner_religions;
	}

	public String getPartner_cast() {
		return partner_cast;
	}

	public void setPartner_cast(String partner_cast) {
		this.partner_cast = partner_cast;
	}

	public String getPartner_country() {
		return partner_country;
	}

	public void setPartner_country(String partner_country) {
		this.partner_country = partner_country;
	}

	public String getPartner_state() {
		return partner_state;
	}

	public void setPartner_state(String partner_state) {
		this.partner_state = partner_state;
	}

	public String getPartner_city() {
		return partner_city;
	}

	public void setPartner_city(String partner_city) {
		this.partner_city = partner_city;
	}

	public String getPartner_from_age() {
		return partner_from_age;
	}

	public void setPartner_from_age(String partner_from_age) {
		this.partner_from_age = partner_from_age;
	}

	public String getPartner_to_age() {
		return partner_to_age;
	}

	public void setPartner_to_age(String partner_to_age) {
		this.partner_to_age = partner_to_age;
	}

	public String getPartner_from_height() {
		return partner_from_height;
	}

	public void setPartner_from_height(String partner_from_height) {
		this.partner_from_height = partner_from_height;
	}

	public String getPartner_to_height() {
		return partner_to_height;
	}

	public void setPartner_to_height(String partner_to_height) {
		this.partner_to_height = partner_to_height;
	}

	public String getPartner_manglik_all() {
		return partner_manglik_all;
	}

	public void setPartner_manglik_all(String partner_manglik_all) {
		this.partner_manglik_all = partner_manglik_all;
	}

	public String getPartner_annual_income() {
		return partner_annual_income;
	}

	public void setPartner_annual_income(String partner_annual_income) {
		this.partner_annual_income = partner_annual_income;
	}

	public String getPartner_profile_created() {
		return partner_profile_created;
	}

	public void setPartner_profile_created(String partner_profile_created) {
		this.partner_profile_created = partner_profile_created;
	}

	public String getPartner_lifestyles() {
		return partner_lifestyles;
	}

	public void setPartner_lifestyles(String partner_lifestyles) {
		this.partner_lifestyles = partner_lifestyles;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getLifestyle() {
		return lifestyle;
	}

	public void setLifestyle(String lifestyle) {
		this.lifestyle = lifestyle;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getCast_name() {
		return cast_name;
	}

	public void setCast_name(String cast_name) {
		this.cast_name = cast_name;
	}

	public int getCast_id() {
		return cast_id;
	}

	public void setCast_id(int cast_id) {
		this.cast_id = cast_id;
	}

	public String getSub_cast_name() {
		return sub_cast_name;
	}

	public void setSub_cast_name(String sub_cast_name) {
		this.sub_cast_name = sub_cast_name;
	}

	public int getSubcaste_id() {
		return subcaste_id;
	}

	public void setSubcaste_id(int subcaste_id) {
		this.subcaste_id = subcaste_id;
	}

	public String getReligion_name() {
		return religion_name;
	}

	public void setReligion_name(String religion_name) {
		this.religion_name = religion_name;
	}

	public int getReligion_id() {
		return religion_id;
	}

	public void setReligion_id(int religion_id) {
		this.religion_id = religion_id;
	}

	public String getState_name() {
		return state_name;
	}

	public void setState_name(String state_name) {
		this.state_name = state_name;
	}

	public int getState_id() {
		return state_id;
	}

	public void setState_id(int state_id) {
		this.state_id = state_id;
	}

	public String getCity_name() {
		return city_name;
	}

	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}

	public int getCity_id() {
		return city_id;
	}

	public void setCity_id(int city_id) {
		this.city_id = city_id;
	}

	public int getFromage() {
		return fromage;
	}

	public void setFromage(int fromage) {
		this.fromage = fromage;
	}

	public int getToage() {
		return toage;
	}

	public void setToage(int toage) {
		this.toage = toage;
	}

	public MemberPreferenceModel(Integer id, String gender, String lifestyle, String job, String education,
			String cast_name, int cast_id, String sub_cast_name, int subcaste_id, String religion_name, int religion_id,
			String state_name, int state_id, String city_name, int city_id, int fromage, int toage) {
		super();
		this.id = id;
		this.gender = gender;
		this.lifestyle = lifestyle;
		this.job = job;
		this.education = education;
		this.cast_name = cast_name;
		this.cast_id = cast_id;
		this.sub_cast_name = sub_cast_name;
		this.subcaste_id = subcaste_id;
		this.religion_name = religion_name;
		this.religion_id = religion_id;
		this.state_name = state_name;
		this.state_id = state_id;
		this.city_name = city_name;
		this.city_id = city_id;
		this.fromage = fromage;
		this.toage = toage;
	}

	public MemberPreferenceModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	

}
