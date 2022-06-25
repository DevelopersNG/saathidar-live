package com.sathidar.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sathidar.EntityMangerFactory.UpdateMemberEntityMangerFactory;
import com.sathidar.model.UpdateMember;
import com.sathidar.model.User;
import com.sathidar.service.UpdateMemberService;

@CrossOrigin(origins = "http://localhost:4200" , methods = {RequestMethod.OPTIONS, RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE}, allowedHeaders = "*", allowCredentials = "true")
@RestController
@RequestMapping("/api")
public class UpdateMemberController {

	@Autowired
	private UpdateMemberService updateMemberService;
	
	@Autowired
	private UpdateMemberEntityMangerFactory updateMemberEntityMangerFactory;
	 
	@PostMapping(path = "/member/update/{id}")
	public String updateMember(@Validated @RequestBody UpdateMember updateMember,@PathVariable("id") int id) {
		
		JSONObject jsObject = new JSONObject();  
		HashMap<String, String> map = new HashMap<>();
		  if(updateMemberService.UpdateMemberDetails(updateMember,id) !=null) {
			 int login_id=0;
			 map=updateMemberEntityMangerFactory.getMember(id,login_id);
			 jsObject.put("data",map);
			 jsObject.put("results","1");
			 jsObject.put("message","Member Updated...");
		}else {
			jsObject.put("results","0");
			jsObject.put("message","Something wrong , please try again...");
//			map.put("message", "Something wrong , please try again....");
//			map.put("results", "0"); 
		}
		return jsObject.toString();
	}
	
	@PostMapping(path = "/member/app/basic-lifestyles/update/{id}")
	public String updateAppMember(@Validated @RequestBody UpdateMember updateMember,@PathVariable("id") int id) {
		JSONObject jsObject = new JSONObject();  
		HashMap<String, String> map = new HashMap<>();
		  if(updateMemberService.UpdateBasicLifeCycleMemberDetails(updateMember,id) !=null) {
			 int login_id=0;
			 map=updateMemberEntityMangerFactory.getMember(id,login_id);
			 jsObject.put("data",map);
			 jsObject.put("results","1");
			 jsObject.put("message","Basic Lifestyles Updated...");
		}else {
			jsObject.put("results","0");
			jsObject.put("message","Something wrong , please try again...");
		}
		return jsObject.toString();
	}
	
	
	@PostMapping(path = "/member/app/family-details/update/{id}")
	public String updateAppFamilyDetailsMember(@Validated @RequestBody UpdateMember updateMember,@PathVariable("id") int id) {
		JSONObject jsObject = new JSONObject();  
		HashMap<String, String> map = new HashMap<>();
		  if(updateMemberService.updateAppFamilyDetailsMember(updateMember,id) !=null) {
			 int login_id=0;
			 map=updateMemberEntityMangerFactory.getMember(id,login_id);
			 jsObject.put("data",map);
			 jsObject.put("results","1");
			 jsObject.put("message","Family Details Updated...");
		}else {
			jsObject.put("results","0");
			jsObject.put("message","Something wrong , please try again...");
		}
		return jsObject.toString();
	}
	
	@PostMapping(path = "/member/app/professional-details/update/{id}")
	public String updateAppProfessionalDetailsMember(@Validated @RequestBody UpdateMember updateMember,@PathVariable("id") int id) {
		JSONObject jsObject = new JSONObject();  
		HashMap<String, String> map = new HashMap<>();
		  if(updateMemberService.updateAppProfessionalDetailsMember(updateMember,id) !=null) {
			 int login_id=0;
			 map=updateMemberEntityMangerFactory.getMember(id,login_id);
			 jsObject.put("data",map);
			 jsObject.put("results","1");
			 jsObject.put("message","Family Details Updated...");
		}else {
			jsObject.put("results","0");
			jsObject.put("message","Something wrong , please try again...");
		}
		return jsObject.toString();
	}
	
	
//	@GetMapping(value = "/member/get-details/{id}")
//	public String getMembers(@PathVariable("id") int id) {
//		 HashMap<String, String> map = new HashMap<>();
//		 JSONObject jsObject = new JSONObject();  
//		 map=updateMemberEntityMangerFactory.getMember(id,0);
//		 if(map==null) {
//			 jsObject.put("data",map);
//			 jsObject.put("results","1");
//			 jsObject.put("message","something wrong ! record not fetch...");
////			 map.put("message","something wrong ! record not fetch...");
//		 }else {
//			 jsObject.put("data",map);
//			 jsObject.put("results","1");
//		 }
//		 return jsObject.toString();
//	}
	
	@GetMapping(value = "/member/get-details/{id}/{login_id}")
	public String getMembers(@PathVariable("id") int id,@PathVariable("login_id") int login_id) {
		 HashMap<String, String> map = new HashMap<>();
		 JSONObject jsObject = new JSONObject();  
		 map=updateMemberEntityMangerFactory.getMember(id,login_id);
		 if(map==null) {
			 jsObject.put("data",map);
			 jsObject.put("results","1");
			 jsObject.put("message","something wrong ! record not fetch...");
//			 map.put("message","something wrong ! record not fetch...");
		 }else {
			 jsObject.put("data",map);
			 jsObject.put("results","1");
		 }
		 return jsObject.toString();
	}
	
	@GetMapping(value = "/member/get-details/{id}")
	public String getMembersForMobile(@PathVariable("id") int id) {
		 HashMap<String, String> map = new HashMap<>();
		 JSONObject jsObject = new JSONObject();  
		 map=updateMemberEntityMangerFactory.getMember(id,0);
		 if(map==null) {
			 jsObject.put("data",map);
			 jsObject.put("results","1");
			 jsObject.put("message","something wrong ! record not fetch...");
//			 map.put("message","something wrong ! record not fetch...");
		 }else {
			 jsObject.put("data",map);
			 jsObject.put("results","1");
		 }
		 return jsObject.toString();
	}
	
//	@GetMapping(value = "/member/get-details-by-member-id/{member_number}")
//	public UpdateMember getDetailsByMemberID(@PathVariable("member_number") String member_number) {
//		return updateMemberService.getDetailsByMemberID(member_number);
//	}
	
//	
	@GetMapping(value = "/member/get-details-by-member-id/{member_number}")
	public Map<String, String> getDetailsByMemberID(@PathVariable("member_number") String member_number) {
		 HashMap<String, String> map = new HashMap<>();
//		 System.out.println("sssssssssssssssssss-"+member_number);
		 map=updateMemberEntityMangerFactory.getDetailsByMemberID(member_number);
		 if(map==null) {
			 map.put("message","something wrong ! record not fetch...");
		 }
		 return map;
	}
	
	
	@PostMapping(value = "/member/get-all-member/{id}")
	public String getAllMemberByFilter(@Validated @RequestBody UpdateMember updateMember,@PathVariable("id") int id) {
		 JSONArray jsonResultArray = new JSONArray();
		 jsonResultArray=updateMemberEntityMangerFactory.getAllMemberByFilter(updateMember,id,"");
		 return jsonResultArray.toString();
	}
	
	@PostMapping(value = "/member/get-member-by-filter-data/{id}")
	public String getAllMemberByRefineSearch(@Validated @RequestBody UpdateMember updateMember,@PathVariable("id") int id) {
		 JSONArray jsonResultArray = new JSONArray();
		 jsonResultArray=updateMemberEntityMangerFactory.getAllMemberByFilter(updateMember,id,"REFINE-SEARCH");
//		 jsonResultArray=updateMemberEntityMangerFactory.getAllMemberByRefineSearch(updateMember,id);
		 return jsonResultArray.toString();
	}

	@GetMapping(value = "/member/get-all-member-inside-login/{id}")
	public String getAllMembers(@PathVariable("id") int id) {
		 JSONArray jsonResultArray = new JSONArray();
		 jsonResultArray=updateMemberEntityMangerFactory.getAllMembers(id);
		 return jsonResultArray.toString();
	}

	@PostMapping(path = "/member/deactivate/{id}")
	public Map<String, String> deactivateMember(@PathVariable("id") int id) {
		  HashMap<String, String> map = new HashMap<>();
		if(updateMemberEntityMangerFactory.DeactivateMemberDetails(id) >0) {
			 map.put("message", "member deactivated...");
		}else {
			map.put("message", "member already deactivated...");
		}
		return map;
	}
	
	@PostMapping(path = "/member/activate")
	public Map<String, String> activateMember(@Validated @RequestBody UpdateMember updateMember) {
		  HashMap<String, String> map = new HashMap<>();
		if(updateMemberEntityMangerFactory.activateMember(updateMember.getContact_number(),updateMember.getEmail_id()) >0) {
			 map.put("message", "member activated...");
		}else {
			map.put("message", "member already activated...");
		}
		return map;
	}
	
	@GetMapping(value = "/get-member-id-by-login/{id}")
	public Map<String, String> getMembersID(@PathVariable("id") int id) {
		 HashMap<String, String> map = new HashMap<>();
		 map=updateMemberEntityMangerFactory.getMemberIdByUserLoginId(id);
		 if(map==null) {
			 map.put("message","something wrong ! record not fetch...");
		 }
		 return map;
	}
	 
	@GetMapping(value = "/member/plans-details")
	public String getMembersPlanDetails() {
		 JSONArray jsonResultArray = new JSONArray();
		 JSONObject jsObject = new JSONObject();  
		 jsonResultArray=updateMemberEntityMangerFactory.getMembersPlanDetails();
		 if(jsonResultArray==null) {
			 jsObject.put("data",jsonResultArray);
			 jsObject.put("results","0");
			 jsObject.put("message","something wrong ! record not fetch...");
		 }else {
			 jsObject.put("data",jsonResultArray);
			 jsObject.put("results","1");
		 }
		 return jsObject.toString();
	}
	
		@PostMapping(path = "/member/horoscope/update/{member_id}")
		public String updateHoroscopeDetails(@Validated @RequestBody UpdateMember updateMember,@PathVariable("member_id") int member_id) {
			  HashMap<String, String> map = new HashMap<>();
			  JSONObject jsObject = new JSONObject();  
			if(updateMemberService.updateHoroscopeDetails(updateMember,member_id)!=null) {
				map=updateMemberEntityMangerFactory.getHoroscopeDetails(member_id);
				 if(map==null) {
					 jsObject.put("data",map);
					 jsObject.put("results","0");
					 jsObject.put("message","something wrong ! record not fetch...");
				 }else {
					 jsObject.put("data",map);
					 jsObject.put("results","1");
				 }
				 return jsObject.toString();
			}else {
				 jsObject.put("data",map);
				 jsObject.put("results","1");
				 jsObject.put("message","something wrong ! record not fetch...");
			}
			return jsObject.toString();
		}
	
//	@GetMapping(value = "/member/horoscope/get/{member_id}")
//	public String getHoroscopeDetails(@PathVariable("member_id") int member_id) {
//		 JSONArray jsonResultArray = new JSONArray();
//		 jsonResultArray=updateMemberEntityMangerFactory.getHoroscopeDetails(member_id);
//		 return jsonResultArray.toString();
//	}

	@GetMapping(value = "/member/horoscope/get/{id}")
	public String getHoroscopeDetails(@PathVariable("id") int id) {
		 HashMap<String, String> map = new HashMap<>();
		 JSONObject jsObject = new JSONObject();  
		 map=updateMemberEntityMangerFactory.getHoroscopeDetails(id);
		 
		 if(map==null) {
			 jsObject.put("data",map);
			 jsObject.put("results","1");
			 jsObject.put("message","something wrong ! record not fetch...");
		 }else {
			 jsObject.put("data",map);
			 jsObject.put("results","1");
		 }
		 return jsObject.toString();
	}
	
	// match partner - preference
	@GetMapping(value = "/member/match/preference/{member_id}/{login_id}")
	public Map<String, String> getMatchPartnerPreference(@PathVariable("member_id") int member_id,@PathVariable("login_id") int login_id) {
		 HashMap<String, String> map = new HashMap<>();
		 map=updateMemberEntityMangerFactory.getMatchPartnerPreference(member_id,login_id);
		 if(map==null) {
			 map.put("message","something wrong ! record not fetch...");
		 }
		 return map;
	}
	
//	<------------------------------ matches start -------------------->
	
	@GetMapping(value = "/member/new/matches/{id}")
	public String getNewMatches(@PathVariable("id") String id) {
		 HashMap<String, String> map = new HashMap<>();
		 JSONObject jsObject = new JSONObject();  
		 JSONArray jsonResultArray = new JSONArray();
		 UpdateMember updateMember=new UpdateMember();
		 jsonResultArray=updateMemberEntityMangerFactory.getAllMemberByFilter(updateMember,Integer.parseInt(id),"NEW_MATCHES");
//		 jsonResultArray=updateMemberEntityMangerFactory.getNewMatches(Integer.parseInt(id),"NEW_MATCHES");
		 if(map!=null) {
			 jsObject.put("data",jsonResultArray);
			 jsObject.put("results","1");
		 }else {
			 jsObject.put("data",map);
			 jsObject.put("results","0");
		 }
		 return jsObject.toString();
	}
	
	@GetMapping(value = "/member/my/matches/{id}")
	public String getmyMatches(@PathVariable("id") String id) {
		 HashMap<String, String> map = new HashMap<>();
		 JSONObject jsObject = new JSONObject();  
		 JSONArray jsonResultArray = new JSONArray();
		 UpdateMember updateMember=new UpdateMember();
		 jsonResultArray=updateMemberEntityMangerFactory.getAllMemberByFilter(updateMember,Integer.parseInt(id),"MY_MATCHES");
//		 jsonResultArray=updateMemberEntityMangerFactory.getNewMatches(Integer.parseInt(id),"MY_MATCHES");
		 if(map!=null) {
			 jsObject.put("data",jsonResultArray);
			 jsObject.put("results","1");
		 }else {
			 jsObject.put("data",map);
			 jsObject.put("results","0");
		 }
		 return jsObject.toString();
	}
	
	@GetMapping(value = "/member/todays/matches/{id}")
	public String getTodaysMatches(@PathVariable("id") String id) {
		 HashMap<String, String> map = new HashMap<>();
		 JSONObject jsObject = new JSONObject();  
		 JSONArray jsonResultArray = new JSONArray();
		 UpdateMember updateMember=new UpdateMember();
		 jsonResultArray=updateMemberEntityMangerFactory.getAllMemberByFilter(updateMember,Integer.parseInt(id),"TODAYS_MATCHES");
//		 jsonResultArray=updateMemberEntityMangerFactory.getNewMatches(Integer.parseInt(id),"TODAYS_MATCHES");
		 if(map!=null) {
			 jsObject.put("data",jsonResultArray);
			 jsObject.put("results","1");
		 }else {
			 jsObject.put("data",map);
			 jsObject.put("results","0");
		 }
		 return jsObject.toString();
	}
	
	
	@GetMapping(value = "/member/recent-visitors/{id}")
	public String getRecentVisitors(@PathVariable("id") String id) {
		 HashMap<String, String> map = new HashMap<>();
		 JSONObject jsObject = new JSONObject();  
		 JSONArray jsonResultArray = new JSONArray();
		 UpdateMember updateMember=new UpdateMember();
		 jsonResultArray=updateMemberEntityMangerFactory.getRecentVisitorsFilter(updateMember,Integer.parseInt(id),"Recently_Visitors");
//		 jsonResultArray=updateMemberEntityMangerFactory.getNewMatches(Integer.parseInt(id),"TODAYS_MATCHES");
		 if(map!=null) {
			 jsObject.put("data",jsonResultArray);
			 jsObject.put("results","1");
		 }else {
			 jsObject.put("data",map);
			 jsObject.put("results","0");
		 }
		 return jsObject.toString();
	}
	
	@GetMapping(value = "/member/view-to/{id}")
	public String getViewTo(@PathVariable("id") String id) {
		 HashMap<String, String> map = new HashMap<>();
		 JSONObject jsObject = new JSONObject();  
		 JSONArray jsonResultArray = new JSONArray();
		 UpdateMember updateMember=new UpdateMember();
		 jsonResultArray=updateMemberEntityMangerFactory.getRecentVisitorsFilter(updateMember,Integer.parseInt(id),"View_To");
//		 jsonResultArray=updateMemberEntityMangerFactory.getNewMatches(Integer.parseInt(id),"TODAYS_MATCHES");
		 if(map!=null) {
			 jsObject.put("data",jsonResultArray);
			 jsObject.put("results","1");
		 }else {
			 jsObject.put("data",map);
			 jsObject.put("results","0");
		 }
		 return jsObject.toString();
	}
	
//	<------------------------------ matches end  -------------------->
}
