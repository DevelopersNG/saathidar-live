package com.sathidar.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sathidar.model.UpdateMember;

@Repository
public interface UpdateMemberRepository extends JpaRepository<UpdateMember, Integer> {

	@Transactional
	@Modifying
	@Query(value = "update memberdetails set  membernative= :mNative, height= :mHeight, weight= :mWeight, lifestyles= :mLifeStyles, "
			+ " known_languages= :mKnown_Languages, education= :mEducation, job= :mJob, income= :mIncome, hobbies= :mHobbies , expectations= :mExpectation,"
			+ " mother_tounge= :mMother_tounge, marital_status= :mMarital_status, no_of_children= :mNoOfChildren, date_of_birth= :mDateOfBirth,  age= :mAge,"
			+ " religion_id= :mReligionID, cast_id= :mCasteID, sub_caste_name= :msubCast, state_id= :mStateID, city_id= :mCityID,"
			+ " health_info= :mhealth_info,blood_group= :mblood_group,gothra= :mgothra,ethnic_corigin= :methnic_corigin,pincode= :mpincode,about_ourself= :mabout_ourself,"
			+  " country_id= :mcountryID"
			+ " where member_id= :id  ", nativeQuery = true)
	Object UpdateMemberDetails(String mNative, String mHeight, String mWeight, String mLifeStyles,
			String mKnown_Languages, String mEducation, String mJob, String mIncome, String mHobbies,
			String mExpectation, int id,
			String mMother_tounge, String mMarital_status, String mNoOfChildren,
			String mDateOfBirth, int mAge, int mReligionID, int mCasteID, String msubCast, int mStateID, int mCityID,
			String mhealth_info, String mblood_group, String mgothra, String methnic_corigin, String mpincode,
			String mabout_ourself,String mcountryID);

	
	@Query(value="SELECT * FROM memberdetails as md join member as m on md.member_id=m.member_id where m.member_number= :member_number and m.status='ACTIVE'",nativeQuery=true)
	UpdateMember getDetailsByMemberID(String member_number);
	
	@Query(value="SELECT religion_id FROM religion where religion_name= :religionName and status='ACTIVE'",nativeQuery=true)
	int getReligionID(String religionName);

	@Query(value="SELECT subcast_id FROM subcasts where subcast_name= :subCasteName and cast_id= :casteID  and status='ACTIVE'",nativeQuery=true)
	int getSubCasteID(String subCasteName, int casteID);

	@Query(value="SELECT state_id FROM states where state_name= :stateName and status='ACTIVE'",nativeQuery=true)
	int getStateID(String stateName);

	@Query(value="SELECT city_id FROM city where city_name= :cityName and state_id= :stateID and status='ACTIVE'",nativeQuery=true)
	int getCityID(String cityName, int stateID);

	@Query(value="SELECT cast_id FROM cast where cast_name= :casteName and religion_id= :religionID  and status='ACTIVE'",nativeQuery=true)
	int getCasteIDByReligionID(String casteName, int religionID);

	@Transactional
	@Modifying
	@Query(value = "update member_family_details set father_status= :mfather_status, father_company_name= :mfather_company_name,father_designation= :mfather_designation,father_business_name= :mfather_business_name,"
			+ "mother_status= :mmother_status,mother_company_name= :mmother_company_name,mother_designation= :mmother_designation,mother_business_name= :mmother_business_name,"
			+ "family_location= :mfamily_location,native_place= :mnative_place,family_type= :mfamily_type,family_values= :mfamily_values,family_affluence= :mfamily_affluence,"
			+ "married_male= :married_male,unmarried_male= :unmarried_male,married_female= :married_female,unmarried_female= :unmarried_female "
			+ " where member_id= :id  ", nativeQuery = true)
	Object UpdatememberFamilyDetails(int id, String mfather_status, String mfather_company_name,
			String mfather_designation, String mfather_business_name, String mmother_status,
			String mmother_company_name, String mmother_designation, String mmother_business_name,
			String mfamily_location, String mnative_place, String mfamily_type, String mfamily_values,
			String mfamily_affluence,String married_male,String unmarried_male,String married_female,String unmarried_female);

	@Transactional
	@Modifying
	@Query(value = "update member_education_career set highest_qualification= :mhighest_qualification, college_attended= :mcollege_attended,working_with= :mworking_with,"
			+ "working_as= :mworking_as,employer_name=:memployer_name,annual_income= :mannual_income "
			+ " where member_id= :id  ", nativeQuery = true)
	Object UpdatememberEducationCareerDetails(int id, String mhighest_qualification, String mcollege_attended,
			String mworking_with, String mworking_as, String memployer_name, String mannual_income);

	@Transactional
	@Modifying
	@Query(value = "update member set profilecreatedby= :prodile_created, gender= :gender "
			+ " where member_id= :id  ", nativeQuery = true)
	Object UpdateInMemberTable(String prodile_created, String gender,int id);

	@Transactional
	@Modifying
	@Query(value = "update member set gender= :gender "
			+ " where member_id= :id  ", nativeQuery = true)
	Object UpdateAppInMemberTable(String gender,int id);

	
	@Transactional
	@Modifying
	@Query(value = "update member_horoscope set country_of_birth= :country_of_birth, city_of_birth= :city_of_birth,hours= :hours,"
			+ "minutes= :minutes,time=:time,time_status= :time_status,time_of_birth= :time_of_birth,"
			+ "manglik= :manglik , hr_dob=:date_of_birth"
			+ " where member_id= :member_id  ", nativeQuery = true)
	Object updateHoroscopeDetails(int member_id, String country_of_birth, String city_of_birth, String hours,
			String minutes, String time, String time_status, String time_of_birth, String manglik,String date_of_birth);
	
	@Transactional
	@Modifying
	@Query(value = "insert into member_horoscope (member_id,country_of_birth,city_of_birth,time_of_birth,time_status,manglik,hours,minutes,time,hr_dob)"
			+ "values ("
			+ ":member_id,:country_of_birth,:city_of_birth,:time_of_birth,:time_status,:manglik,:hours,:minutes,:time,:date_of_birth"
			+ ")", nativeQuery = true)
	Object insertHoroscopeDetails(int member_id, String country_of_birth, String city_of_birth, String hours,
			String minutes, String time, String time_status, String time_of_birth, String manglik , String date_of_birth);

	
	@Query(value="SELECT count(*) FROM member_horoscope where member_id= :member_id and status='ACTIVE'",nativeQuery=true)
	int isAvailablerHoroscopeRecords(int member_id);

	@Transactional
	@Modifying
	@Query(value = "update member_horoscope set manglik= :manglik, nakshatra= :nakshatra "
			+ " where member_id= :member_id and status='ACTIVE' ", nativeQuery = true)
	Object updateMemberHoroscopeRepository(int member_id, String manglik, String nakshatra);


	@Transactional
	@Modifying
	@Query(value = "insert into member_horoscope (member_id,manglik,nakshatra)"
			+ "values ("
			+ ":member_id,:manglik,:nakshatra"
			+ ")", nativeQuery = true)
	Object insertMemberHoroscopeRepository(int member_id, String manglik, String nakshatra);
//
//
//	void saveRecentVisitors(int login_id, int visite_id);

	@Transactional
	@Modifying
	@Query(value = "update memberdetails set age= :age,date_of_birth= :dateOfBirth,marital_status= :marital_status,no_of_children= :noOfChildren,"
			+ " height= :mHeight, blood_group= :mblood_group,lifestyles= :mLifeStyles, "
			+ " mother_tounge= :mother_tounge,health_info= :mhealth_info,"
			+ "religion_id= :religionID,cast_id= :casteID, sub_caste_name= :subCast,gothra= :mgothra,about_ourself= :mabout_ourself"
			+ " where member_id= :id ", nativeQuery = true)
	Object UpdateAppBasicInfoMemberDetails(int id,int age, String dateOfBirth, String marital_status, String noOfChildren,
			String mHeight, String mblood_group, String mLifeStyles, String mother_tounge, String mhealth_info,
			int religionID, int casteID, String subCast, String mgothra, String mabout_ourself);
	
	@Transactional
	@Modifying
	@Query(value = "update member_family_details set father_status= :mfather_status, father_company_name= :mfather_company_name,father_designation= :mfather_designation,father_business_name= :mfather_business_name,"
			+ "mother_status= :mmother_status,mother_company_name= :mmother_company_name,mother_designation= :mmother_designation,mother_business_name= :mmother_business_name,"
			+ "family_location= :mfamily_location,native_place= :mnative_place,family_type= :mfamily_type,family_affluence= :mfamily_affluence,"
			+ "married_male= :married_male,unmarried_male= :unmarried_male,married_female= :married_female,unmarried_female= :unmarried_female "
			+ " where member_id= :id  ", nativeQuery = true)
	Object UpdatememberAppFamilyDetails(int id, String mfather_status, String mfather_company_name,
			String mfather_designation, String mfather_business_name, String mmother_status,
			String mmother_company_name, String mmother_designation, String mmother_business_name,
			String mfamily_location, String mnative_place, String mfamily_type,
			String mfamily_affluence, String married_male, String unmarried_male, String married_female,
			String unmarried_female);
	
	@Transactional
	@Modifying
	@Query(value = "update memberdetails set "
			+ "  state_id= :stateID, city_id= :cityID,"
			+ "ethnic_corigin= :methnic_corigin,pincode= :mpincode,"
			+  "country_id= :countryID"
			+ " where member_id= :id  ", nativeQuery = true)
	Object updateLocationOfGroom(int id,int countryID, int stateID, int cityID, String methnic_corigin, String mpincode);

	@Query(value="SELECT count(*) FROM member_activated where member_id= :member_id",nativeQuery=true)
	int isMemberAvailable(Integer member_id);

	@Transactional
	@Modifying
	@Query(value = "update member_activated set "
			+ "  activate_id= :activate_id where member_id= :member_id  ", nativeQuery = true)
	int updateMemberCurrentStatus(Integer member_id, Integer activate_id);

	@Query(value="SELECT activate_id FROM member_activated where member_id= :member_id",nativeQuery=true)
	int getActivateMember(Integer member_id);

	@Transactional
	@Modifying
	@Query(value = "insert into member_activated (member_id,activate_id)"
			+ "values ("
			+ ":member_id,:activate_id"
			+ ")", nativeQuery = true)
	int insertMemberCurrentStatus(Integer member_id, Integer activate_id);

	@Query(value ="SELECT gender FROM member where member_id= :id", nativeQuery = true)
	String getGenderByMemberID(int id);

	
	
	@Transactional
	@Modifying
	@Query(value = "update memberdetails set "
			+ "  date_of_birth= :date_of_birth where member_id= :id  ", nativeQuery = true)
	Object updateDateOfBirthInMemberTable(int id, String date_of_birth);


	@Transactional
	@Modifying
	@Query(value = "update memberdetails set height= :mHeight, lifestyles= :mLifeStyles, "
			+ "marital_status= :marital_status, date_of_birth= :dateOfBirth,"
			+ " religion_id= :religionID,"
			+  " country_id= :countryID"
			+ " where member_id= :member_id  ", nativeQuery = true)
	int UpdateRegistrationDetails(int member_id, String dateOfBirth, String marital_status, String mHeight,
			int religionID, int countryID, String mLifeStyles);

	@Query(value="SELECT user_id FROM member where member_id= :member_id ",nativeQuery=true)
	int getUserIDByMemberID(int member_id);

	@Transactional
	@Modifying
	@Query(value = "update users set "
			+ "  short_reg_status=1 where id= :user_id  ", nativeQuery = true)
	int updateShortRegstInUserTable(int user_id);

	@Query(value="SELECT short_reg_status FROM users where id= :getUserID ",nativeQuery=true)
	String getShortRegistrationStatus(int getUserID);

	@Query(value="SELECT member_id FROM member where user_id= :user_id ",nativeQuery=true)
	int getMemberIDByUserIDByMemberID(int user_id);

	@Query(value="SELECT request_status FROM member_request where  request_from_id= :login_member_id and request_to_id= :memberID ",nativeQuery=true)
	String getMemberStatus(int login_member_id, String memberID);

	@Query(value="SELECT block_status FROM member_request where  request_from_id= :login_member_id and request_to_id= :memberID ",nativeQuery=true)
	String getMemberBlockStatus(int login_member_id, String memberID);


	@Transactional
	@Modifying
	@Query(value = "insert into tempsendotp (conactno,otp,user_id) values (:phone_no,:otp,:user_id)", nativeQuery = true)
	int saveOTPOfUserID(String phone_no, int user_id,String otp);

	@Query(value="SELECT block_status FROM member_request where  request_from_id= :login_member_id and request_to_id= :memberID ",nativeQuery=true)
	int getLastInsertedValueInMemberTable();

	@Query(value="SELECT id FROM users where confirmation_Token= :token",nativeQuery=true)
	String getUserID(String token);

	@Transactional
	@Modifying
	@Query(value = "update users set enabled= :Enabled where id= :UserID and confirmation_Token= :Token", nativeQuery = true)
	int updateStatusActiveToMemberTable1(String UserID, boolean Enabled, String Token);

//	String getDetailsFromOfInboxIDs(int id);
//
//	String getDetailsOfToInboxIDs(int id);

}