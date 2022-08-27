package com.sathidar.EntityMangerFactory;

import java.math.BigInteger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.sathidar.model.User;

import antlr.collections.List;

@Service
public class UserEntityManagerFactory {

	@PersistenceContext
	private EntityManager em;

	public int getRoleID(String roleName) {
		int ans = 0;
		try {
			Query q = em.createNativeQuery("select role_id from roles where name=:RoleName and role_status='ACTIVE'");
			q.setParameter("RoleName", roleName);
			ans = Integer.parseInt(q.getSingleResult().toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ans;
	}

	@Transactional
	public boolean saveRoleToMember(int getRoleID, int userID) {
		boolean status = false;
		try {
			Query q = em.createNativeQuery("insert into users_roles(user_id,role_id) values(:userID, :roleID)");
			q.setParameter("userID", userID);
			q.setParameter("roleID", getRoleID);
			q.executeUpdate();
//			em.getTransaction().commit();
			em.close();
			status = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	public int getLastInsertedID() {
		int ans = 0;
		try {
			Query q = em.createNativeQuery("SELECT id FROM users order by id DESC LIMIT 1");
			ans = Integer.parseInt(q.getSingleResult().toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ans;
	}

	@Transactional
	public int insertRecordToMemberTable(User user, int getRoleID, int getLastInsertedID) {
		int memberStatus=0,statsCount=0;
		try {
			
			String queryInsertMember="insert into member(first_name,last_name,gender,contact_number,email_id,profilecreatedby,user_id,role_id) values(:first_name,:last_name,:gender,:contact_number,:email_id,:profilecreatedby,:user_id,:role_id)";
			Query q = em.createNativeQuery(queryInsertMember);
			q.setParameter("first_name", user.getFirstName());
			q.setParameter("last_name", user.getLastName());
			q.setParameter("gender", user.getGender());
			q.setParameter("contact_number", user.getPhone());
			q.setParameter("email_id", user.getEmail());
			q.setParameter("profilecreatedby", user.getProfilecreatedby());
			q.setParameter("user_id", getLastInsertedID);
			q.setParameter("role_id", getRoleID);
			statsCount= q.executeUpdate();
			// em.getTransaction().commit();
			em.close();
			
			Query queryGetLastInsertedValue = em.createNativeQuery("SELECT LAST_INSERT_ID()");
			BigInteger biid = (BigInteger) queryGetLastInsertedValue.getSingleResult();
			int id = biid.intValue();
			
			String number="";
			int lengthOfID = String.valueOf(id).length(); 
			if(lengthOfID==1)
				number="00"+id;
			if(lengthOfID==2)
				number="0"+id;
			
			String memberNumber="";
			String mGender="";
			if(user.getGender().trim()!="" && user.getGender().trim()!=null)
			{
				mGender=user.getGender().trim();
				char ch= mGender.charAt(0);
				if(ch=='f' || ch=='F') {
					memberNumber="FSD"+number;
				}else if(ch=='m' || ch=='M') {
					memberNumber="MSD"+number;}
			}
			
			Query queryMemberNumber = em.createNativeQuery(
					"update member set member_number= :MemberNumber where member_id= :MemberID");
			queryMemberNumber.setParameter("MemberNumber",memberNumber);
			queryMemberNumber.setParameter("MemberID",id);
			statsCount= queryMemberNumber.executeUpdate();
			
			Query queryMemberDetails = em.createNativeQuery(
					"insert into memberdetails(member_id) values(:MemberID)");
			queryMemberDetails.setParameter("MemberID",id);
			statsCount= queryMemberDetails.executeUpdate();
			
			Query queryMemberPreference = em.createNativeQuery(
					"insert into member_preference(member_id) values(:MemberID)");
			queryMemberPreference.setParameter("MemberID",id);
			statsCount= queryMemberPreference.executeUpdate();
			
			Query queryMemberFamily = em.createNativeQuery(
					"insert into member_family_details(member_id) values(:MemberID)");
			queryMemberFamily.setParameter("MemberID",id);
			statsCount= queryMemberFamily.executeUpdate();
			
			Query queryMemberEducationCareer = em.createNativeQuery(
					"insert into member_education_career(member_id) values(:MemberID)");
			queryMemberEducationCareer.setParameter("MemberID",id);
			statsCount= queryMemberEducationCareer.executeUpdate();
			
			Query queryHoroscopeCareer = em.createNativeQuery(
					"insert into member_horoscope(member_id) values(:MemberID)");
			queryHoroscopeCareer.setParameter("MemberID",id);
			statsCount= queryHoroscopeCareer.executeUpdate();
			// em.getTransaction().commit();
			em.close();
			memberStatus=1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return memberStatus;
	}

	@Transactional
	public boolean saveTempOTP(String contactNumber, String otp) {
		boolean status=false;
		try {
			Query queryMemberDetails = em.createNativeQuery(
					"insert into tempsendotp(conactno,otp) values(:ContactNo,:OTP)");
			queryMemberDetails.setParameter("ContactNo",contactNumber);
			queryMemberDetails.setParameter("OTP",otp);
		    queryMemberDetails.executeUpdate();
		    
	
			// em.getTransaction().commit();
			em.close();
			status=true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	
	public boolean verifyMemberOtp(String mOtp, String contactNo) {
		boolean status=false;
		try {
			Query q = em.createNativeQuery("SELECT otp FROM tempsendotp where conactno= :ContactNo  order by id DESC LIMIT 1");
			q.setParameter("ContactNo", contactNo);
			int ans = Integer.parseInt(q.getSingleResult().toString());
			if(ans==Integer.parseInt(mOtp))
				status= true;
			else
				status= false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	@Transactional
	public boolean updateStatusActiveToMemberTable(String token) {
		boolean status=false;
		try {
			Query getIDQuery = em.createNativeQuery("SELECT id FROM users where confirmation_Token= :Token");
			getIDQuery.setParameter("Token", token);
			int user_id = Integer.parseInt(getIDQuery.getSingleResult().toString());
			
			if(user_id>0) {
				// update confirmation token enabled 1
			   Query updateEnableQuery = em.createNativeQuery(
						"update users set enabled= :Enabled where id= :UserID and confirmation_Token= :Token");
			   updateEnableQuery.setParameter("Enabled",1);
			   updateEnableQuery.setParameter("status","ACTIVE");
			   updateEnableQuery.setParameter("UserID",user_id);
			   updateEnableQuery.setParameter("Token", token);
			   updateEnableQuery.executeUpdate();
				
				
				Query memberIDQuery = em.createNativeQuery("SELECT member_id FROM member where user_id= :USERID and (status IS NULL or status='')");
				memberIDQuery.setParameter("USERID", user_id);
				int member_id = Integer.parseInt(memberIDQuery.getSingleResult().toString());
				
				if(member_id>0) { 	
				 // update user, member and member prefernece table status='ACTIVE'
						Query userQuery = em.createNativeQuery(
							"update users set status= :UserStatus where id= :UserID and (status IS NULL or status='')");
						userQuery.setParameter("UserStatus","ACTIVE");
						userQuery.setParameter("UserID",user_id);
						userQuery.executeUpdate();
					
							Query memberQuery = em.createNativeQuery(
							"update member set status= :MemberStatus where member_id= :MemberID and (status IS NULL or status='')");
							memberQuery.setParameter("MemberStatus","ACTIVE");
							memberQuery.setParameter("MemberID",member_id);
							memberQuery.executeUpdate();
					
							Query preferenceQuery = em.createNativeQuery(
							"update member_preference set status= :MemberStatus where member_id= :MemberID and (status IS NULL or status='')");
							preferenceQuery.setParameter("MemberStatus","ACTIVE");
							preferenceQuery.setParameter("MemberID",member_id);
							preferenceQuery.executeUpdate();
					status=true;		
				}
			}
			
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}
//
//	@Transactional
//	public int saveState(String state) {
//		int id = 0;
//		try {
//
//			Query q = em.createNativeQuery("insert into states(state_name,country_id) values(:StateName, :CountryID)");
//			q.setParameter("StateName", state);
//			q.setParameter("CountryID", 1);
//			q.executeUpdate();
////			em.getTransaction().commit();
//			em.close();
//
//			q = em.createNativeQuery("SELECT id FROM states order by state_id DESC LIMIT 1");
//			id = Integer.parseInt(q.getSingleResult().toString());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return id;
//	}
//
//	public void saveStateIDAndCity(String city, int saveAndGetLastInsertedID) {
//		try {
//			
//			Query q = em.createNativeQuery("insert into city(city_name,state_id) values(:CityName, :StateID)");
//			q.setParameter("CityName", city);
//			q.setParameter("StateID", saveAndGetLastInsertedID);
//			q.executeUpdate();
////			em.getTransaction().commit();
//			em.close();
//		} catch (Exception e) {
//		}
//	}
	public boolean isUserAvailableOrNot(String profile_id) {
		boolean status=false;
		try {
			Object checkObject=null;
			Query getIDQuery = em.createNativeQuery("SELECT count(*),user_id FROM member where member_number= :profile_id and status='ACTIVE'");
			getIDQuery.setParameter("profile_id", profile_id);
			
			java.util.List<Object[]> list = getIDQuery.getResultList();
			
			
				for(Object[] q1 : list){
				if(Integer.parseInt(q1[0].toString())>0) {
					    getIDQuery = em.createNativeQuery("SELECT count(*) FROM users where id= :user_id and enabled= :enabled and (status='ACTIVE' or status='Active')");
						getIDQuery.setParameter("user_id", q1[1].toString());
						getIDQuery.setParameter("enabled", 1);
						if(Integer.parseInt(getIDQuery.getSingleResult().toString()) > 0) {
							status=true;
						}
				}
		}
			
		} catch (Exception e) {
		e.printStackTrace();
		}
		return status;
	}

	public String getMemberIDByUserID(int userID) {
		String id="";
		try {
			Query q = em.createNativeQuery("SELECT member_id FROM member where user_id= :UserID");
			q.setParameter("UserID", userID);
			id = q.getSingleResult().toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}

	public String getMemberNumbersIDBy(int userID) {
		String id="";
		try {
			Query q = em.createNativeQuery("SELECT member_number FROM member where user_id= :UserID");
			q.setParameter("UserID", userID);
			id = q.getSingleResult().toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}
	
	public String getMemberNumbersMemberIDBy(int member_id) {
		String id="";
		try {
			Query q = em.createNativeQuery("SELECT member_number FROM member where member_id= :member_id");
			q.setParameter("member_id", member_id);
			id = q.getSingleResult().toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}
	
	public String getMemberGenderIDBy(int userID) {
		String id="";
		try {
			Query q = em.createNativeQuery("SELECT gender FROM member where user_id= :UserID");
			q.setParameter("UserID", userID);
			id = q.getSingleResult().toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}
	
	public String getDateIntervalForHideProfile(String hide_period_time) {
		String id="";
		try {
			Query q = em.createNativeQuery("SELECT DATE_ADD(curdate(), INTERVAL :hide_period_time month) as hide_period_time");
			q.setParameter("hide_period_time", hide_period_time);
			id = q.getSingleResult().toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}

	public String getMemberProdileCreatedIDBy(int userID) {
		String id="";
		try {
			Query q = em.createNativeQuery("SELECT profilecreatedby FROM member  where user_id= :UserID");
			q.setParameter("UserID", userID);
			id = q.getSingleResult().toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}

	public String getFranciseCodeByUserID(int userID) {
		String franchise_code="";
		try {
			Query q = em.createNativeQuery("SELECT franchise_code FROM users  where id= :UserID");
			q.setParameter("UserID", userID);
			franchise_code =q.getSingleResult().toString();
		} catch (Exception e) {
			e.printStackTrace();
			franchise_code="";
		}
		return franchise_code;
	}

	public String getMemberShortRegistStatus(int userID) {
		String franchise_code="";
		try {
			Query q = em.createNativeQuery("SELECT short_reg_status FROM users  where id= :UserID ");
			q.setParameter("UserID", userID);
			franchise_code =q.getSingleResult().toString();
		} catch (Exception e) {
			e.printStackTrace();
			franchise_code="";
		}
		return franchise_code;
	}

	
}
