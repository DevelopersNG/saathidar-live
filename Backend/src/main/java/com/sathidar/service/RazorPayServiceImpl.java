package com.sathidar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sathidar.EntityMangerFactory.GetNameByIDMangerFactory;
import com.sathidar.model.RazorPayModel;
import com.sathidar.repository.RazorPayRepository;

@Service
public class RazorPayServiceImpl implements RazorPayService {

	@Autowired
	private RazorPayRepository razorPayRepository;
	
	@Autowired
	private GetNameByIDMangerFactory getNameByIDMangerFactory;
	
	@Override
	public int updatePremiumMemberDetails(RazorPayModel razorPayModel) {
		
		String member_id=razorPayModel.getMember_id();
		String plan_name=razorPayModel.getPlan_name();
		String plan_amount=razorPayModel.getPlan_amount();
		
		int plan_id= getNameByIDMangerFactory.getUpgradePlanIdByName(plan_name);
		
		return razorPayRepository.updatePremiumMemberDetails(member_id,plan_id);
	}

}

