package com.sathidar.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.hibernate.jpa.TypedParameterValue;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sathidar.repository.PrivacyPolicyRepository;
import com.sathidar.service.EmailService;

@Service
public class MembersDetailsAction {

	@Autowired
	private PrivacyPolicyRepository privacyPolicyRepository;

	@PersistenceContext
	private EntityManager em;

//	map.put(2,"Visible to all Premium Members");
//	map.put(3,"Keep this private");
	
	@Transactional
	public String getPhonePrivacy(int loginPremiumStatus, String thisMemberID, String contact_number) {
		try {
			int getStatus = privacyPolicyRepository.findByMember_Id(Integer.parseInt(thisMemberID));

			if (getStatus > 0) {
				String results = privacyPolicyRepository.getPhoneRecords(Integer.parseInt(thisMemberID));

				if (results != null) {
					// login user and current member are premium member
					if (Integer.parseInt(results) == 2 && loginPremiumStatus > 0) {
						return contact_number;
					} else if (Integer.parseInt(results) == 3) {
						// current member are set keep this private
						StringBuffer buf = new StringBuffer(contact_number);
						String number = buf.replace(4, buf.length() - 1, "******").toString();
						return number;
					} else {
						return contact_number;
					}
				} else {
					return contact_number;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	
	
	@Transactional
	public String getEmailPrivacy(int loginPremiumStatus, String thisMemberID, String email) {
		try {
			int getStatus = privacyPolicyRepository.findByMember_Id(Integer.parseInt(thisMemberID));
			if (getStatus > 0) {
				String results = privacyPolicyRepository.getEmailRecords(Integer.parseInt(thisMemberID));
				if (results != null) {
					// login user and current member are premium member
					if (Integer.parseInt(results) == 2 && loginPremiumStatus > 0) {
						return email;
					} else if (Integer.parseInt(results) == 3) {
						StringBuffer buf = new StringBuffer(email);
						String resemail = buf.replace(4, buf.length() - 1, "******").toString();
						return resemail;
					} else {
						return email;
					}
				} else {
					return email;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	@Transactional
	public String getAnnualIncomePrivacy(int loginPremiumStatus, String thisMemberID, String annual_income) {
		try {
			int getStatus = privacyPolicyRepository.findByMember_Id(Integer.parseInt(thisMemberID));
			if (getStatus > 0) {
				String results = privacyPolicyRepository.getAnnualIncomeRecords(Integer.parseInt(thisMemberID));
				if (results != null) {
					// login user and current member are premium member
					if (Integer.parseInt(results) == 2 && loginPremiumStatus > 0) {
						return annual_income;
					} else if (Integer.parseInt(results) == 3) {
						return "Keep This Private";
					} else {
						return annual_income;
					}
				} else {
					return annual_income;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	
	@Transactional
	public String getDateOfBirthPrivacy(int loginPremiumStatus, String thisMemberID, String date_of_birth) {
		try {
			Date date = new Date();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			int getStatus = privacyPolicyRepository.findByMember_Id(Integer.parseInt(thisMemberID));
			if (getStatus > 0) {
				String results = privacyPolicyRepository.getDateOfBirthRecords(Integer.parseInt(thisMemberID));
				if (results != null) {
					// dd/mm/yyyy format
					if (Integer.parseInt(results) == 1) {
						date = df.parse(date_of_birth);
						DateFormat df1 = new SimpleDateFormat("dd/MM/yyyy");
						return df1.format(date);
					} else if (Integer.parseInt(results) == 2) {
						date = df.parse(date_of_birth);
						DateFormat df1 = new SimpleDateFormat("MM/yyyy");
						return df1.format(date);
					} else {
						return date_of_birth;
					}
				} else {
					return date_of_birth;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	@Transactional
	public List getDetailsMemberByMember_id(Integer request_from_id) {
		List lstAdd=new ArrayList();
//		try {
//			String columnName = "email_id,first_name,last_name, m.member_id, height,md.age,md.marital_status as maritalStatus,edu.highest_qualification as highest_qualification,edu.working_with as working_with";
//
//			List<Object[]> results = privacyPolicyRepository.getMemberDeyailsForMail(""+request_from_id);
//			if (results != null) {
//				for (Object[] obj : results) {
//				int i=0;
//				String email_id = convertNullToBlank(String.valueOf(obj[i]));
//				String first_name = convertNullToBlank(String.valueOf(obj[++i]));
//				String last_name = convertNullToBlank(String.valueOf(obj[++i]));
//				String member_id = convertNullToBlank(String.valueOf(obj[++i]));
//				String height = convertNullToBlank(String.valueOf(obj[++i]));
//				String age = convertNullToBlank(String.valueOf(obj[++i]));
//				String marital_status = convertNullToBlank(String.valueOf(obj[++i]));
//				String education = convertNullToBlank(String.valueOf(obj[++i]));
//				String profession = convertNullToBlank(String.valueOf(obj[++i]));
//				
//				lstAdd.add(email_id);
//				lstAdd.add(first_name);
//				lstAdd.add(last_name);
//				lstAdd.add(member_id);
//				lstAdd.add(height);
//				lstAdd.add(age);
//				lstAdd.add(marital_status);
//				lstAdd.add(education);
//				lstAdd.add(profession);
//				
//				}
//			}
//			return lstAdd;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		// TODO Auto-generated method stub
		return lstAdd;
	}
	
	public String convertNullToBlank(String value) {
		if (value != null && !value.equals("") && !value.equals("null")) {
			return value;
		}
		return "";
	}

	public String getEmailId(Integer request_to_id) {
		String response="";
		try {  
			//member_number
			Query q = em.createNativeQuery("SELECT email_id FROM member "
					+ " where member_id= :member_id");
			q.setParameter("member_id", ""+request_to_id);
			response = q.getSingleResult().toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
}
