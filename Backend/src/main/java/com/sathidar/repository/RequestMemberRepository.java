package com.sathidar.repository;

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
}
