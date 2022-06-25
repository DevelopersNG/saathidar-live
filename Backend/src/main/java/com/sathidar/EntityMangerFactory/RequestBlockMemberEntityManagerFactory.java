package com.sathidar.EntityMangerFactory;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

@Service
public class RequestBlockMemberEntityManagerFactory {

	
	@PersistenceContext
	private EntityManager em;

	
	public List<Object[]> getFromRequestAndToRequest(int request_from_id, int request_to_id) {
		List<Object[]> results =null;
		try {
			
			Query q = em.createNativeQuery("select request_from_id,request_to_id from member_request"
					+ " where (request_from_id= :requestFromID and request_to_id= :requestToID)"
					+ " or (request_from_id= :requestToID and request_to_id= :requestFromID)");
			q.setParameter("requestFromID", request_from_id);
			q.setParameter("requestToID", request_to_id);
			results = q.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}
	
	
}
