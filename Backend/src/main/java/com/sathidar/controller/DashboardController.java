package com.sathidar.controller;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sathidar.service.DashboardService;

//@CrossOrigin(origins = "http://localhost:4200" , methods = {RequestMethod.OPTIONS, RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE}, allowedHeaders = "*", allowCredentials = "true")
@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class DashboardController {

	@Autowired
	private DashboardService dashboardService;
	
	@GetMapping(value="/request/count/accept-request/{member_id}")
	private String GetAllCountDetails(@PathVariable String member_id ) {
		JSONArray jsonResultsArray=new JSONArray();
		jsonResultsArray= dashboardService.GetAllCountDetails(member_id);
		
		JSONObject jsObject = new JSONObject();  
		jsObject.put("data",jsonResultsArray);
		return jsObject.toString();
	}
	
	@GetMapping(value="/new/matches/dashboard/{member_id}")
	private String GetNewMatchesDashboard(@PathVariable String member_id ) {
		JSONArray jsonResultsArray=new JSONArray();
		JSONObject jsObject = new JSONObject();
		jsonResultsArray= dashboardService.GetNewMatchesDashboard(member_id);
		if (jsonResultsArray != null) {
			jsObject.put("data", jsonResultsArray);
			jsObject.put("results", "1");
		} else {
			jsObject.put("data", jsonResultsArray);
			jsObject.put("results", "0");
		}
		return jsObject.toString();
	}
	
	@GetMapping(value="/new/premium/matches/dashboard/{member_id}")
	private String GetNewPremiumMatchesDashboard(@PathVariable String member_id ) {
		JSONArray jsonResultsArray=new JSONArray();
		JSONObject jsObject = new JSONObject();
		jsonResultsArray= dashboardService.GetNewPremiumMatchesDashboard(member_id,"PREMIUM_MATCHES");
		if (jsonResultsArray != null) {
			jsObject.put("data", jsonResultsArray);
			jsObject.put("results", "1");
		} else {
			jsObject.put("data", jsonResultsArray);
			jsObject.put("results", "0");
		}
		return jsObject.toString();
	}
	
	@GetMapping(value="/admin/dashboard/count")
	private String GetTotalUserRegister() {
		JSONArray jsonResultsArray=new JSONArray();
		jsonResultsArray= dashboardService.GetTotalCountAdminDashboard();
		JSONObject jsObject = new JSONObject();  
		jsObject.put("data",jsonResultsArray);
		return jsObject.toString();
	}
}
