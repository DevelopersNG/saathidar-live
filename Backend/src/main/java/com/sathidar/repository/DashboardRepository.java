package com.sathidar.repository;

import javax.transaction.Transactional;

import org.json.JSONArray;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sathidar.model.DashboardModel;

@Repository
public interface DashboardRepository extends JpaRepository<DashboardModel, Integer>{

	@Transactional
	@Query(value="SELECT count(request_from_id) FROM member_request where  request_to_id= :member_id and request_status= :accepted",nativeQuery = true)
	int getTotalCountAcceptRequest(String member_id,String accepted);

	@Transactional
	@Query(value="SELECT count(request_to_id) FROM member_request where  request_from_id= :member_id",nativeQuery = true)
	int getTotalCountSentRequest(String member_id);

	@Transactional
	@Query(value="SELECT count(request_from_id) FROM member_request where  request_to_id= :member_id and block_by_id= :member_id and block_status= :blocksStatus",nativeQuery = true)
	int getTotalBlockSentRequest(String member_id, String blocksStatus);

	@Transactional
	@Query(value="SELECT count(request_from_id) FROM member_request where  request_to_id= :member_id and request_status= :deleteStatus",nativeQuery = true)
	int getTotalDeletedSentRequest(String member_id, String deleteStatus);

	@Transactional
	@Query(value="SELECT count(member_id) FROM recently_visitors where  visit_to_id= :member_id and date(visitdatetime) >= DATE_SUB(CURDATE(), INTERVAL 30 DAY)",nativeQuery = true)
	int getRecentVisitorsCount(String member_id);

	@Transactional
	@Query(value="SELECT count(*) FROM memberdetails as md join member as m on md.member_id=m.member_id join member_education_career as edu on m.member_id=edu.member_id where md.member_id!= :member_id and m.status='ACTIVE' and md.member_id in (:ids)",nativeQuery = true)
	int getMatchesCount(String member_id, String ids);

	@Transactional
	@Query(value="SELECT count(*) FROM member_request where  request_to_id= :member_id and request_status= :member_request_status",nativeQuery = true)
	int getInvitations(String member_id, String member_request_status);

	@Transactional
	@Query(value="SELECT count(*) FROM member_shortlists where shortlist_from_id= :member_id and shortlist_status= :shortlist_status",nativeQuery = true)
	int getShortlistsCount(String member_id, String shortlist_status);

	@Query(value="SELECT count(*) FROM users where otp_verified=1",nativeQuery=true)
	int GetTotalUserRegister();

	@Query(value="SELECT count(*) FROM premium_member where deleteflag='N'",nativeQuery=true)
	int GetTotalPremiumMemberCount();

	@Query(value="SELECT group_concat(member_id) FROM premium_member where deleteflag='N'",nativeQuery=true)
	String GetTotalPremiumMemberIds();

	@Query(value="SELECT count(*) FROM member where member_id not in(:premium_member_ids) and status='ACTIVE'",nativeQuery=true)
	int GetTotalNonPremiumMemberCount(String premium_member_ids);
}
