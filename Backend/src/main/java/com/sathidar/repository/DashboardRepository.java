package com.sathidar.repository;

import javax.transaction.Transactional;

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
	@Query(value="SELECT count(member_id) FROM recently_visitors where  visit_to_id= :member_id and date(visitdatetime) >= DATE_SUB(CURDATE(), INTERVAL 30 DAY)",nativeQuery = true)
	int getRecentVisitorsCount(String member_id);

}
