package com.sathidar.controller;

	
	import java.util.ArrayList;
	import java.util.HashMap;
	import java.util.List;

	import org.json.JSONArray;
	import org.json.JSONObject;
	import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
	import org.springframework.web.bind.annotation.GetMapping;
	import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
	import org.springframework.web.bind.annotation.RestController;

import com.sathidar.EntityMangerFactory.PlanDetailsManagerFactory;
import com.sathidar.model.PlanDetailsModel;
import com.sathidar.model.UpdateMember;
import com.sathidar.repository.PlanDetailsRepository;
import com.sathidar.service.PlanDetailsService;

	@CrossOrigin ("*")
	@RestController
	@RequestMapping("/api")
	public class PlanDetailsController {
//		
//		@Autowired
//		private  PlanDetailsRepository  planDetailsRepository;
//		
//		
//		@Autowired 
//		private PlanDetailsService planDetailsService;
//		
//		
//		List <PlanDetailsModel> planList = new ArrayList<PlanDetailsModel>();	
//		
//
//		// get plan details
//		
//
//		@GetMapping(value = "member/plandetails")
//		private String GetAllPlanDetails(@PathVariable String plan_id,String plan_name, String plan_validity,String plan_discount,String plan_price) {
//			JSONArray jsonResultsArray = new JSONArray();
//			JSONObject jsObject = new JSONObject();
//			jsonResultsArray = GetAllDetails(plan_id);
//			if (jsonResultsArray != null) {
//				jsObject.put("data", jsonResultsArray);
//				jsObject.put("results", "1");
//			} else {
//				jsObject.put("data", jsonResultsArray);
//	            jsObject.put("results", "0");
//			}
//			return jsObject.toString();
//		}
//		private JSONArray GetAllDetails(String plan_id) {
//			
//			return null;
//		}
//		
//		@PostMapping("/add/plans")
//		  public ResponseEntity<PlanDetailsModel> createPlan(@RequestBody PlanDetailsModel planDetailsModel1) {
//		    try {
//		    	 planDetailsModel1 = planDetailsRepository.save(
//		    			new PlanDetailsModel
//		    			(planDetailsModel1.getPlanId(), 
//		    					planDetailsModel1.getPlanName(),
//		    					planDetailsModel1.getPlanValidity()
//		    					,planDetailsModel1.getPlanDiscount(),
//		    					planDetailsModel1.getPlanPrice(), false));
//		      return new ResponseEntity<>(planDetailsModel1, HttpStatus.CREATED);
//		    } catch (Exception e) {
//		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//		    }
//		  }
//		@PostMapping(path = "/plan/update/{plan_id}")
//		public String updatePlan(@Validated @RequestBody PlanDetailsModel planDetailsModel, @PathVariable("plan_id") int plan_id, Object member_id) {
//
//			JSONObject jsObject = new JSONObject();
//			HashMap<String, String> map = new HashMap<>();
//			if (planDetailsService.UpdatePlanDetails(planDetailsModel, plan_id) != null) {
//				int login_id = 0;
//				map = PlanDetailsManagerFactory.getPlan(plan_id, member_id);
//				if(map!=null) {
//					jsObject.put("data", map);
//					JSONArray jsonResultsArray = new JSONArray();
//					jsonResultsArray = planDetailsService.getplanDetails(""+plan_id);
//					jsObject.put("images",jsonResultsArray);
//					jsObject.put("results", "1");
//					jsObject.put("message", "Member Updated...");
//				}else {
//					jsObject.put("results", "0");
//					jsObject.put("message", "records not found");
//				}
//			} else {
//				jsObject.put("results", "0");
//				jsObject.put("message", "Something wrong , please try again...");
////				map.put("message", "Something wrong , please try again....");
////				map.put("results", "0"); 
//			}
//			return jsObject.toString();
//		}
//		
//		
//		@PostMapping(value = "/plan/delete/{plan_id}")
//		public String deletePlanDetails(@PathVariable("plan_id") int plan_id) {
//			 JSONArray jsonResultArray = new JSONArray();
//			 jsonResultArray=PlanDetailsManagerFactory.deletePlanDetails(plan_id);
//			 return jsonResultArray.toString();
//		}
		
		
		
		
		
	
	

}
