package com.sathidar.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.json.JSONArray;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sathidar.model.UploadDocumentModel;
import com.sathidar.model.UploadImagesModel;

@Repository
public interface UploadImagesRepository extends JpaRepository<UploadImagesModel,Integer> {

	@Transactional
	@Modifying
	@Query(value="insert into member_photo (member_id,image_name,image_url) values (:member_id,:image_name,:image_blob)",nativeQuery=true)
	int savePhoto(int member_id, String image_name, byte[] image_blob);

	@Query(value="select image_url from member_photo where member_id= :member_id and deleteflag='N'",nativeQuery = true)
	List getMemberPhotos(String member_id);

	@Query(value="select *  from member_photo where member_id= :member_id and status='approved' and deleteflag='N'",nativeQuery = true)
	List<UploadImagesModel> getByMember_Id(String member_id);

	@Transactional
	@Modifying
	@Query(value="delete from member_photo where id= :id and deleteflag='N'",nativeQuery = true)
	int deleteByPhotoID(Integer id);

	@Transactional
	@Modifying
	@Query(value="insert into member_photo (member_id,image_name,image_path) values (:member_id,:image_name,:image_path)",nativeQuery=true)
	int saveMemberPhotos(String image_name, String image_path, Integer member_id);

	@Transactional
	@Modifying
	@Query(value="update member_photo set deleteflag='Y' where id= :id ",nativeQuery = true)
	int deleteByPhotoIDDeleteFlagY(Integer id);

	@Transactional
	@Modifying
	@Query(value="update memberdetails set profile_photo_id= :image_id where member_id= :member_id ",nativeQuery = true)
	int saveProfilePhto(Integer member_id, String image_id);

	@Query(value="select image_path from member_photo where id= :image_id and deleteflag='N'",nativeQuery = true)
	String getMemberProfilePhotoPath(String image_id);

	@Query(value="select profile_photo_id from memberdetails where member_id= :member_id",nativeQuery = true)
	String getMemberProfilePhotoID(String member_id);

	@Query(value="select count(*) from premium_member where member_id= :memberID and deleteflag='N' ",nativeQuery = true)
	int getPremiumMemberStatus(String memberID);

	@Query(value="select photo from privacy_options where member_id= :memberID",nativeQuery = true)
	String getPhotoPrivacySettings(String memberID);

	@Query(value="select count(*) from member_shortlists where shortlist_from_id= :from_id  and shortlist_to_id= :memberID and shortlist_status='add' ",nativeQuery = true)
	int getShortListStatus(String from_id,String memberID);

	@Query(value="select count(*) from recently_visitors where member_id= :login_id and visit_to_id= :id",nativeQuery = true)
	int getVisitorsStatus(int login_id, int id);

	@Transactional
	@Modifying
	@Query(value="update plans set deleteflag='Y' where plan_id= :member_id ",nativeQuery = true)
	int deleteByPlanID(int member_id);

	@Query(value="select date_of_birth from memberdetails where member_id= :member_id",nativeQuery = true)
	String getDateOfBirthFromMemberDetailsTable(int member_id);

	@Query(value="select phone from privacy_options where member_id= :memberID",nativeQuery = true)
	String getPhonePrivacySettings(String memberID);

	@Query(value="select email from privacy_options where member_id= :memberID",nativeQuery = true)
	String getEmailPrivacySettings(String memberID);

	@Query(value="select dob from privacy_options where member_id= :memberID",nativeQuery = true)
	String getDOBPrivacySettings(String memberID);

	@Query(value="select annual_income from privacy_options where member_id= :memberID",nativeQuery = true)
	String getAnnualIncomePrivacySettings(String memberID);

	@Query(value="SELECT count(*) FROM memberdetails as md join member as m on md.member_id=m.member_id where m.member_number= :member_number and m.status='ACTIVE'",nativeQuery=true)
	int checkMemberIdAvailable(String member_number);

	@Query(value="select plan_name from plans where plan_id= :plan_id and plan_status='ACTIVE'",nativeQuery = true)
	String getPlanNamebyMemberID(String plan_id);

	@Query(value="select *  from member_photo where member_id= :member_id and deleteflag='N'",nativeQuery = true)
	List<UploadImagesModel> getMyPhotoByMember_Id(String member_id);
}
