package com.sathidar.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.sathidar.model.RequestMemberModel;

public interface RequestMemberRepository extends JpaRepository<RequestMemberModel, Integer> {

	@Transactional
	@Modifying
	@Query(value="insert into member_request (request_from_id,request_to_id,request_status) values (:request_from_id,:request_to_id,:request_status)",nativeQuery = true)
	Object sendRequestToMember(int request_from_id, int request_to_id, String request_status);

	@Transactional
	@Modifying
	@Query(value="update member_request set request_status= :requestStatus where request_from_id= :requestFromId and request_to_id= :requestToId",nativeQuery = true)
	Object requestAcceptedAndRejected(int requestFromId, int requestToId, String requestStatus);


	@Transactional
	@Modifying
	@Query(value="update member_request set block_status= :blockStatus,block_by_id= :blockById where request_from_id= :from_id and request_to_id= :to_id",nativeQuery = true)
	Object requestBlockToMember(int from_id, int to_id, int blockById, String blockStatus);

	@Transactional
	@Modifying
	@Query(value="SELECT email_id,first_name,last_name, m.member_id, "
			+ "height,md.age,md.marital_status,"
			+ "edu.highest_qualification,edu.working_with"
			+ "  FROM "
			+ "memberdetails as md join member as m on md.member_id=m.member_id "
			+ "join member_education_career as edu on m.member_id=edu.member_id "
			+ "where md.member_id= :request_from_id and m.status='ACTIVE'",nativeQuery = true)
	List<Object[]> getMemberDeyailsForMail(String request_from_id);

	
	@Transactional
	@Query(value="SELECT email_id FROM member where member_id= :request_to_id",nativeQuery = true)
	String getEmailId(Integer request_to_id);


}
