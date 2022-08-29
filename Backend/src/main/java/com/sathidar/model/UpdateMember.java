package com.sathidar.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class UpdateMember {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String membernative;
	
	private String height;
	
	private String weight;
	
	private String lifestyles;
	
	private String known_languages;
	
	private String education;
	
	private String job;
	
	private String income;
	
	private String hobbies;
	
	private String expectations;

	private String first_name;
	
	private String last_name;
	
	private String gender;
	
	private Integer age;
	
	private String marital_status;
	
	private String contact_number;
	
	private String email_id;
	
	private String profilecreatedby;
	
	private Integer cast_id;
	
	private Integer subcaste_id;
	
	private String married_male;
	
	private String unmarried_male;

	private String married_female;
	
	private String unmarried_female;

	private Integer member_id;
	
	private Integer activate_id;

	
//	------- 23-3-2022 start------------

	private Integer religion_id;
	
	@Transient
	private String religion;
	
	@Transient
	private String religion_name;

	@Transient
	private String caste_name;
	
	@Transient
	private String sub_caste_name;
	
	@Transient
	private String state_name;
	
	private String state_Id;

	@Transient
	private String city_name;

	private String city_Id;
	
	private String mother_tounge;
	
	private String no_of_children;
	
	private String date_of_birth;
	
	@Transient
	private String from_age;

	@Transient
	private String to_age;

	@Transient
	private String lookingFor;
	
	private String country_name;
	
	private String country_Id;
	
//	------- 23-3-2022 end------------

//	----------16-4-2022 start-----------

	private String health_info;
	private String blood_group;
	private String gothra;
	private String ethnic_corigin;
	private String pincode;
	private String about_ourself;
	
//	----------16-4-2022 end-----------
	
	
//	----------- family details start-------------------
	private String father_status;
	private String father_company_name;
	private String father_designation;
	private String father_business_name;
	private String mother_status;
	private String mother_company_name;
	private String mother_designation;
	private String mother_business_name;
	private String family_location;
	private String native_place;
	private String family_type;
	private String family_values;
	private String family_affluence;
//	----------- family details end-------------------
	
//	------ education details start--------------------
	
	private String highest_qualification;
	private String college_attended;
	private String working_with;
	private String working_as;
	private String employer_name;
	private String annual_income;
	
//	------ education details end --------------------

	
//	--------------- refine filter start -------------------------------
	@Transient
	private String religion_list;
	
	@Transient
	private String state_list;
	
	@Transient
	private String all;	
	
	@Transient
	private String recentlyJoined;	
	
	@Transient
	private String annualIncome;	
	
	@Transient
	private String maritalStatus;	
	
	@Transient
	private String country;	
	
	@Transient
	private String workingWith;	
	
	@Transient
	private String profileCreadtedBy;	
	
	@Transient
	private String lifeStyles;
	
	@Transient
	private String educationalLevels;
	
	
//	--------------- Horoscope details start  -------------------------------	
	private String country_of_birth;
	private String city_of_birth;
	private String time_of_birth;
	private String time_status;
	private String manglik;
	private String hours;
	private String minutes;
	private String time;
	private String nakshatra;
	
	public Integer getActivate_id() {
		return activate_id;
	}

	public void setActivate_id(Integer activate_id) {
		this.activate_id = activate_id;
	}

	public Integer getMember_id() {
		return member_id;
	}

	public void setMember_id(Integer member_id) {
		this.member_id = member_id;
	}

	public String getNakshatra() {
		return nakshatra;
	}

	public void setNakshatra(String nakshatra) {
		this.nakshatra = nakshatra;
	}

	public String getHours() {
		return hours;
	}

	public void setHours(String hours) {
		this.hours = hours;
	}

	public String getMinutes() {
		return minutes;
	}

	public void setMinutes(String minutes) {
		this.minutes = minutes;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getCountry_of_birth() {
		return country_of_birth;
	}

	public void setCountry_of_birth(String country_of_birth) {
		this.country_of_birth = country_of_birth;
	}

	public String getCity_of_birth() {
		return city_of_birth;
	}

	public void setCity_of_birth(String city_of_birth) {
		this.city_of_birth = city_of_birth;
	}

	public String getTime_of_birth() {
		return time_of_birth;
	}

	public void setTime_of_birth(String time_of_birth) {
		this.time_of_birth = time_of_birth;
	}

	public String getTime_status() {
		return time_status;
	}

	public void setTime_status(String time_status) {
		this.time_status = time_status;
	}

	public String getManglik() {
		return manglik;
	}

	public String getReligion() {
		return religion;
	}

	public void setReligion(String religion) {
		this.religion = religion;
	}

	public void setManglik(String manglik) {
		this.manglik = manglik;
	}

	public Integer getId() {
		return id;
	}

	public String getReligion_list() {
		return religion_list;
	}

	public void setReligion_list(String religion_list) {
		this.religion_list = religion_list;
	}

	public String getState_list() {
		return state_list;
	}

	public void setState_list(String state_list) {
		this.state_list = state_list;
	}

	public String getAll() {
		return all;
	}

	public void setAll(String all) {
		this.all = all;
	}

	public String getRecentlyJoined() {
		return recentlyJoined;
	}

	public void setRecentlyJoined(String recentlyJoined) {
		this.recentlyJoined = recentlyJoined;
	}

	
	public String getAnnualIncome() {
		return annualIncome;
	}

	public void setAnnualIncome(String annualIncome) {
		this.annualIncome = annualIncome;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getWorkingWith() {
		return workingWith;
	}

	public void setWorkingWith(String workingWith) {
		this.workingWith = workingWith;
	}

	public String getProfileCreadtedBy() {
		return profileCreadtedBy;
	}

	public void setProfileCreadtedBy(String profileCreadtedBy) {
		this.profileCreadtedBy = profileCreadtedBy;
	}

	public String getLifeStyles() {
		return lifeStyles;
	}

	public void setLifeStyles(String lifeStyles) {
		this.lifeStyles = lifeStyles;
	}

	public String getEducationalLevels() {
		return educationalLevels;
	}

	public void setEducationalLevels(String educationalLevels) {
		this.educationalLevels = educationalLevels;
	}

	

	public String getMarried_male() {
		return married_male;
	}

	public void setMarried_male(String married_male) {
		this.married_male = married_male;
	}

	public String getUnmarried_male() {
		return unmarried_male;
	}

	public void setUnmarried_male(String unmarried_male) {
		this.unmarried_male = unmarried_male;
	}

	public String getMarried_female() {
		return married_female;
	}

	public void setMarried_female(String married_female) {
		this.married_female = married_female;
	}

	public String getUnmarried_female() {
		return unmarried_female;
	}

	public void setUnmarried_female(String unmarried_female) {
		this.unmarried_female = unmarried_female;
	}

	public String getCountry_name() {
		return country_name;
	}

	public void setCountry_name(String country_name) {
		this.country_name = country_name;
	}

	public String getCountry_Id() {
		return country_Id;
	}

	public void setCountry_Id(String country_Id) {
		this.country_Id = country_Id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMembernative() {
		return membernative;
	}

	public void setMembernative(String membernative) {
		this.membernative = membernative;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getLifestyles() {
		return lifestyles;
	}

	public void setLifestyles(String lifestyles) {
		this.lifestyles = lifestyles;
	}

	public String getKnown_languages() {
		return known_languages;
	}

	public void setKnown_languages(String known_languages) {
		this.known_languages = known_languages;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getIncome() {
		return income;
	}

	public void setIncome(String income) {
		this.income = income;
	}

	public String getHobbies() {
		return hobbies;
	}

	public void setHobbies(String hobbies) {
		this.hobbies = hobbies;
	}

	public String getExpectations() {
		return expectations;
	}

	public void setExpectations(String expectations) {
		this.expectations = expectations;
	}

	
	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}


	public String getMarital_status() {
		return marital_status;
	}

	public void setMarital_status(String marital_status) {
		this.marital_status = marital_status;
	}

	public String getContact_number() {
		return contact_number;
	}

	public void setContact_number(String contact_number) {
		this.contact_number = contact_number;
	}

	public String getEmail_id() {
		return email_id;
	}

	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}

	public String getProfilecreatedby() {
		return profilecreatedby;
	}

	public void setProfilecreatedby(String profilecreatedby) {
		this.profilecreatedby = profilecreatedby;
	}

	public Integer getCast_id() {
		return cast_id;
	}

	public void setCast_id(Integer cast_id) {
		this.cast_id = cast_id;
	}

	public Integer getSubcaste_id() {
		return subcaste_id;
	}

	public void setSubcaste_id(Integer subcaste_id) {
		this.subcaste_id = subcaste_id;
	}
	
	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getReligion_id() {
		return religion_id;
	}

	public void setReligion_id(Integer religion_id) {
		this.religion_id = religion_id;
	}

	public String getMother_tounge() {
		return mother_tounge;
	}

	public void setMother_tounge(String mother_tounge) {
		this.mother_tounge = mother_tounge;
	}
	public String getState_Id() {
		return state_Id;
	}

	public void setState_Id(String state_Id) {
		this.state_Id = state_Id;
	}

	public String getCity_Id() {
		return city_Id;
	}

	public void setCity_Id(String city_Id) {
		this.city_Id = city_Id;
	}

	public String getReligion_name() {
		return religion_name;
	}

	public void setReligion_name(String religion_name) {
		this.religion_name = religion_name;
	}

	public String getCaste_name() {
		return caste_name;
	}

	public void setCaste_name(String caste_name) {
		this.caste_name = caste_name;
	}

	public String getSub_caste_name() {
		return sub_caste_name;
	}

	public void setSub_caste_name(String sub_caste_name) {
		this.sub_caste_name = sub_caste_name;
	}

	public String getState_name() {
		return state_name;
	}

	public void setState_name(String state_name) {
		this.state_name = state_name;
	}

	public String getCity_name() {
		return city_name;
	}

	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}

	public String getNo_of_children() {
		return no_of_children;
	}

	public void setNo_of_children(String no_of_children) {
		this.no_of_children = no_of_children;
	}

	public String getDate_of_birth() {
		return date_of_birth;
	}

	public void setDate_of_birth(String date_of_birth) {
		this.date_of_birth = date_of_birth;
	}
	
	public String getFrom_age() {
		return from_age;
	}

	public void setFrom_age(String from_age) {
		this.from_age = from_age;
	}

	public String getTo_age() {
		return to_age;
	}

	public void setTo_age(String to_age) {
		this.to_age = to_age;
	}

	public String getLookingFor() {
		return lookingFor;
	}

	public void setLookingFor(String lookingFor) {
		this.lookingFor = lookingFor;
	}

	public String getHealth_info() {
		return health_info;
	}

	public void setHealth_info(String health_info) {
		this.health_info = health_info;
	}

	public String getBlood_group() {
		return blood_group;
	}

	public void setBlood_group(String blood_group) {
		this.blood_group = blood_group;
	}

	public String getGothra() {
		return gothra;
	}

	public void setGothra(String gothra) {
		this.gothra = gothra;
	}

	public String getEthnic_corigin() {
		return ethnic_corigin;
	}

	public void setEthnic_corigin(String ethnic_corigin) {
		this.ethnic_corigin = ethnic_corigin;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getAbout_ourself() {
		return about_ourself;
	}

	public void setAbout_ourself(String about_ourself) {
		this.about_ourself = about_ourself;
	}

	public String getFather_status() {
		return father_status;
	}

	public void setFather_status(String father_status) {
		this.father_status = father_status;
	}

	public String getFather_company_name() {
		return father_company_name;
	}

	public void setFather_company_name(String father_company_name) {
		this.father_company_name = father_company_name;
	}

	public String getFather_designation() {
		return father_designation;
	}

	public void setFather_designation(String father_designation) {
		this.father_designation = father_designation;
	}

	public String getFather_business_name() {
		return father_business_name;
	}

	public void setFather_business_name(String father_business_name) {
		this.father_business_name = father_business_name;
	}

	public String getMother_status() {
		return mother_status;
	}

	public void setMother_status(String mother_status) {
		this.mother_status = mother_status;
	}

	public String getMother_company_name() {
		return mother_company_name;
	}

	public void setMother_company_name(String mother_company_name) {
		this.mother_company_name = mother_company_name;
	}

	public String getMother_designation() {
		return mother_designation;
	}

	public void setMother_designation(String mother_designation) {
		this.mother_designation = mother_designation;
	}

	public String getMother_business_name() {
		return mother_business_name;
	}

	public void setMother_business_name(String mother_business_name) {
		this.mother_business_name = mother_business_name;
	}

	public String getFamily_location() {
		return family_location;
	}

	public void setFamily_location(String family_location) {
		this.family_location = family_location;
	}

	public String getNative_place() {
		return native_place;
	}

	public void setNative_place(String native_place) {
		this.native_place = native_place;
	}

	public String getFamily_type() {
		return family_type;
	}

	public void setFamily_type(String family_type) {
		this.family_type = family_type;
	}

	public String getFamily_values() {
		return family_values;
	}

	public void setFamily_values(String family_values) {
		this.family_values = family_values;
	}

	public String getFamily_affluence() {
		return family_affluence;
	}

	public void setFamily_affluence(String family_affluence) {
		this.family_affluence = family_affluence;
	}

	public String getHighest_qualification() {
		return highest_qualification;
	}

	public void setHighest_qualification(String highest_qualification) {
		this.highest_qualification = highest_qualification;
	}

	public String getCollege_attended() {
		return college_attended;
	}

	public void setCollege_attended(String college_attended) {
		this.college_attended = college_attended;
	}

	public String getWorking_with() {
		return working_with;
	}

	public void setWorking_with(String working_with) {
		this.working_with = working_with;
	}

	public String getWorking_as() {
		return working_as;
	}

	public void setWorking_as(String working_as) {
		this.working_as = working_as;
	}

	public String getEmployer_name() {
		return employer_name;
	}

	public void setEmployer_name(String employer_name) {
		this.employer_name = employer_name;
	}

	public String getAnnual_income() {
		return annual_income;
	}

	public void setAnnual_income(String annual_income) {
		this.annual_income = annual_income;
	}
	
	@Override
	public String toString() {
		return "UpdateMember [id=" + id + ", membernative=" + membernative + ", height=" + height + ", weight=" + weight
				+ ", lifestyles=" + lifestyles + ", known_languages=" + known_languages + ", education=" + education
				+ ", job=" + job + ", income=" + income + ", hobbies=" + hobbies + ", expectations=" + expectations
				+ ", first_name=" + first_name + ", last_name=" + last_name + ", gender=" + gender + ", age=" + age
				+ ", marital_status=" + marital_status + ", contact_number=" + contact_number + ", email_id=" + email_id
				+ ", profilecreatedby=" + profilecreatedby + ", cast_id=" + cast_id + ", subcaste_id=" + subcaste_id
				+ ", religion_id=" + religion_id + ", religion_name=" + religion_name + ", caste_name=" + caste_name
				+ ", sub_caste_name=" + sub_caste_name + ", state_name=" + state_name + ", state_Id=" + state_Id
				+ ", city_name=" + city_name + ", city_Id=" + city_Id + ", mother_tounge=" + mother_tounge
				+ ", no_of_children=" + no_of_children + ", date_of_birth=" + date_of_birth + ", from_age=" + from_age
				+ ", to_age=" + to_age + ", lookingFor=" + lookingFor + ", health_info=" + health_info
				+ ", blood_group=" + blood_group + ", gothra=" + gothra + ", ethnic_corigin=" + ethnic_corigin
				+ ", pincode=" + pincode + ", about_ourself=" + about_ourself + ", father_status=" + father_status
				+ ", father_company_name=" + father_company_name + ", father_designation=" + father_designation
				+ ", father_business_name=" + father_business_name + ", mother_status=" + mother_status
				+ ", mother_company_name=" + mother_company_name + ", mother_designation=" + mother_designation
				+ ", mother_business_name=" + mother_business_name + ", family_location=" + family_location
				+ ", native_place=" + native_place + ", family_type=" + family_type + ", family_values=" + family_values
				+ ", family_affluence=" + family_affluence + ", highest_qualification=" + highest_qualification
				+ ", college_attended=" + college_attended + ", working_with=" + working_with + ", working_as="
				+ working_as + ", employer_name=" + employer_name + ", annual_income=" + annual_income + "]";
	}

	public UpdateMember() {
		super();
		// TODO Auto-generated constructor stub
	}

	
}
