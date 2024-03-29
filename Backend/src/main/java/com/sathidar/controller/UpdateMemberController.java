package com.sathidar.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.sql.Update;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
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

import com.sathidar.EntityMangerFactory.GetNameByIDMangerFactory;
import com.sathidar.EntityMangerFactory.UpdateMemberEntityMangerFactory;
import com.sathidar.config.APISecurityConfig;
import com.sathidar.model.FilterSearchModel;
import com.sathidar.model.UpdateMember;
import com.sathidar.model.User;
import com.sathidar.service.ServerEmailService;
import com.sathidar.service.UpdateMemberService;
import com.sathidar.service.UploadImagesService;

//@CrossOrigin(origins = "http://localhost:4200", methods = { RequestMethod.OPTIONS, RequestMethod.GET,
//		RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE }, allowedHeaders = "*", allowCredentials = "true")

//@Import(APISecurityConfig.class)
@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class UpdateMemberController {

	@Autowired
	private UpdateMemberService updateMemberService;
	
	@Autowired
	private UploadImagesService uploadImagesService;

	@Autowired
	private UpdateMemberEntityMangerFactory updateMemberEntityMangerFactory;
	
	@PostMapping(path = "/member/update/{id}")
	public String updateMember(@Validated @RequestBody UpdateMember updateMember, @PathVariable("id") int id) {

		JSONObject jsObject = new JSONObject();
		HashMap<String, String> map = new HashMap<>();
		if (updateMemberService.UpdateMemberDetails(updateMember, id) != null) {
			int login_id = 0;
			map = updateMemberEntityMangerFactory.getMember(id, login_id);
			if(map!=null) {
				jsObject.put("data", map);
				JSONArray jsonResultsArray = new JSONArray();
				jsonResultsArray = uploadImagesService.getMemberAppPhotos(""+id);
				jsObject.put("images",jsonResultsArray);
				jsObject.put("results", "1");
				jsObject.put("message", "Member Updated...");
			}else {
				jsObject.put("results", "0");
				jsObject.put("message", "Records not found");
			}
		} else {
			jsObject.put("results", "0");
			jsObject.put("message", "Something went wrong , please try again...");
//			map.put("message", "Something wrong , please try again....");
//			map.put("results", "0"); 
		}
		return jsObject.toString();
	}

	@PostMapping(path = "/member/app/basic-lifestyles/update/{id}")
	public String updateAppMember(@Validated @RequestBody UpdateMember updateMember, @PathVariable("id") int id) {
		JSONObject jsObject = new JSONObject();
		HashMap<String, String> map = new HashMap<>();
		if (updateMemberService.UpdateBasicLifeCycleMemberDetails(updateMember, id) != null) {
			int login_id = 0;
			map = updateMemberEntityMangerFactory.getMyProfileMember(id);
			if(map!=null) {
				jsObject.put("data", map);
				JSONArray jsonResultsArray = new JSONArray();
				jsonResultsArray = uploadImagesService.getMemberAppPhotos(""+id);
				jsObject.put("images",jsonResultsArray);
				jsObject.put("results", "1");
				jsObject.put("message", "Basic Lifestyles Updated...");
			}else {
				jsObject.put("results", "0");
				jsObject.put("message", "Records not found...");
			}
		} else {
			jsObject.put("results", "0");
			jsObject.put("message", "Something went wrong , please try again...");
		}
		return jsObject.toString();
	}

	@PostMapping(path = "/member/app/family-details/update/{id}")
	public String updateAppFamilyDetailsMember(@Validated @RequestBody UpdateMember updateMember,
			@PathVariable("id") int id) {
		JSONObject jsObject = new JSONObject();
		HashMap<String, String> map = new HashMap<>();
		if (updateMemberService.updateAppFamilyDetailsMember(updateMember, id) != null) {
			int login_id = 0;
			map = updateMemberEntityMangerFactory.getMyProfileMember(id);
			if(map!=null) {
			jsObject.put("data", map);
			JSONArray jsonResultsArray = new JSONArray();
			jsonResultsArray = uploadImagesService.getMemberAppPhotos(""+id);
			jsObject.put("images",jsonResultsArray);
			jsObject.put("results", "1");
			jsObject.put("message", "Family Details Updated...");
			}else {
				jsObject.put("results", "0");
				jsObject.put("message", "Records not found...");
			}
		} else {
			jsObject.put("results", "0");
			jsObject.put("message", "Something went wrong , please try again...");
		}
		return jsObject.toString();
	}

	@PostMapping(path = "/member/app/professional-details/update/{id}")
	public String updateAppProfessionalDetailsMember(@Validated @RequestBody UpdateMember updateMember,
			@PathVariable("id") int id) {
		JSONObject jsObject = new JSONObject();
		HashMap<String, String> map = new HashMap<>();
		if (updateMemberService.updateAppProfessionalDetailsMember(updateMember, id) != null) {
			int login_id = 0;
			map = updateMemberEntityMangerFactory.getMyProfileMember(id);
			if(map!=null) {
			jsObject.put("data", map);
			JSONArray jsonResultsArray = new JSONArray();
			jsonResultsArray = uploadImagesService.getMemberAppPhotos(""+id);
			jsObject.put("images",jsonResultsArray);
			jsObject.put("results", "1");
			jsObject.put("message", "Professional Details Updated...");
			}else {
				jsObject.put("results", "0");
				jsObject.put("message", "Records not found...");
			}
		} else {
			jsObject.put("results", "0");
			jsObject.put("message", "Something went wrong , please try again...");
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
	public String getMembers(@PathVariable("id") int id, @PathVariable("login_id") int login_id) {
		HashMap<String, String> map = new HashMap<>();
		JSONObject jsObject = new JSONObject();
		map = updateMemberEntityMangerFactory.getMember(id, login_id);
		if (map == null) {
			jsObject.put("data", map);
			jsObject.put("results", "0");
			jsObject.put("message", "Record not fetch...");
//			 map.put("message","something wrong ! record not fetch...");
		} else {
			jsObject.put("data", map);
			JSONArray jsonResultsArray = new JSONArray();
			jsonResultsArray = uploadImagesService.getMemberAppPhotos(""+id);
			jsObject.put("images",jsonResultsArray);
			jsObject.put("results", "1");
		}
		return jsObject.toString();
	}

	@GetMapping(value = "/member/my-profile/{id}")
	public String getMembersMyProfile(@PathVariable("id") int id) {
		HashMap<String, String> map = new HashMap<>();
		JSONObject jsObject = new JSONObject();
		map = updateMemberEntityMangerFactory.getMyProfileMember(id);
		if (map == null) {
			jsObject.put("data", map);
			jsObject.put("results", "0");
			jsObject.put("message", "Record not fetch...");
//			 map.put("message","something wrong ! record not fetch...");
		} else {
			jsObject.put("data", map);
			JSONArray jsonResultsArray = new JSONArray();
			jsonResultsArray = uploadImagesService.getMemberAppPhotos(""+id);
			jsObject.put("images",jsonResultsArray);
			jsObject.put("results", "1");
		}
		return jsObject.toString();
	}

	@GetMapping(value = "/member/get-details/{id}")
	public String getMembersForMobile(@PathVariable("id") int id) {
		HashMap<String, String> map = new HashMap<>();
		JSONObject jsObject = new JSONObject();
		map = updateMemberEntityMangerFactory.getMember(id, 0);
		if (map == null) {
			jsObject.put("data", map);
			jsObject.put("results", "0");
			jsObject.put("message", "Record not fetch...");
//			 map.put("message","something wrong ! record not fetch...");
		} else {
			jsObject.put("data", map);
			jsObject.put("results", "1");
		}
		return jsObject.toString();
	}

//	@GetMapping(value = "/member/get-details-by-member-id/{member_number}")
//	public UpdateMember getDetailsByMemberID(@PathVariable("member_number") String member_number) {
//		return updateMemberService.getDetailsByMemberID(member_number);
//	}

	@GetMapping(value = "/member/get-details-by-member-id/{member_number}")
	public Map<String, String> getDetailsByMemberID(@PathVariable("member_number") String member_number) {
		HashMap<String, String> map = new HashMap<>();
//		 System.out.println("sssssssssssssssssss-"+member_number);
		map = updateMemberEntityMangerFactory.getDetailsByMemberID(member_number);
		if (map.isEmpty()) {
			map.put("message", "Profile Id is not available");
			map.put("results", "0");
		}
		return map;
	}

	@GetMapping(value = "/member/get-details-by-member-id/{member_number}/{login_member_id}")
	public Map<String, String> getDetailsByMemberID(@PathVariable("member_number") String member_number,@PathVariable("login_member_id") String login_member_id) {
		HashMap<String, String> map = new HashMap<>();
//		 System.out.println("sssssssssssssssssss-"+member_number);
		map = updateMemberEntityMangerFactory.getDetailsByMemberWithLoginID(member_number,Integer.parseInt(login_member_id));
		if (map.isEmpty()) {
			map.put("message", "Profile Id is not available");
			map.put("results", "0");
		}
		return map;
	}
	
	@PostMapping(value = "/member/get-all-member/{id}")
	public String getAllMemberByFilter(@Validated @RequestBody UpdateMember updateMember, @PathVariable("id") int id) {
		JSONArray jsonResultArray = new JSONArray();
		jsonResultArray = updateMemberEntityMangerFactory.getAllMemberByFilter(updateMember, id, "");
		return jsonResultArray.toString();
	}

	@PostMapping(value = "/member/get-member-by-filter-data/{id}")
	public String getAllMemberByRefineSearch(@Validated @RequestBody UpdateMember updateMember,
			@PathVariable("id") int id) {
		JSONArray jsonResultArray = new JSONArray();
		JSONObject jsObject = new JSONObject();
		jsonResultArray = updateMemberEntityMangerFactory.getAllMemberByFilter(updateMember, id, "REFINE-SEARCH");
//		 jsonResultArray=updateMemberEntityMangerFactory.getAllMemberByRefineSearch(updateMember,id);
		if (jsonResultArray != null) {
			jsObject.put("data", jsonResultArray);
			jsObject.put("results", "1");
		} else {
			jsObject.put("data", jsonResultArray);
			jsObject.put("results", "0");
		}
		return jsObject.toString();
	}

	@GetMapping(value = "/member/get-all-member-inside-login/{id}")
	public String getAllMembers(@PathVariable("id") int id) {
		JSONArray jsonResultArray = new JSONArray();
		
		jsonResultArray = updateMemberEntityMangerFactory.getAllMembers(id);
		return jsonResultArray.toString();
	}

//	@PostMapping(path = "/member/deactivate/{id}")
//	public Map<String, String> deactivateMember(@PathVariable("id") int id) {
//		  HashMap<String, String> map = new HashMap<>();
//		if(updateMemberEntityMangerFactory.DeactivateMemberDetails(id) >0) {
//			 map.put("message", "member deactivated...");
//		}else {
//			map.put("message", "member already deactivated...");
//		}
//		return map;
//	}
//	
//	@PostMapping(path = "/member/activate")
//	public Map<String, String> activateMember(@Validated @RequestBody UpdateMember updateMember) {
//		  HashMap<String, String> map = new HashMap<>();
//		if(updateMemberEntityMangerFactory.activateMember(updateMember.getContact_number(),updateMember.getEmail_id()) >0) {
//			 map.put("message", "member activated...");
//		}else {
//			map.put("message", "member already activated...");
//		}
//		return map;
//	}

	@PostMapping(path = "/member/activate")
	public Map<String, String> activateMember(@Validated @RequestBody UpdateMember updateMember) {
		HashMap<String, String> map = new HashMap<>();
		String message="";
		int getID=updateMember.getActivate_id();
		if(getID==1) {
			message="Your profile is Activated";
		}else if(getID==0){
			message="Your profile is Deactivated";
		}
		if (updateMemberService.activateMember(updateMember) > 0) {
			map.put("results", "1");
			map.put("message", message);
		} else {
			map.put("results", "0");
			map.put("message", "Try again !");
		}
		
		return map;
	}

	@GetMapping(path = "/member/get/activate/{member_id}")
	public Map<String, String> getActivateMember(@PathVariable("member_id") int member_id) {
		HashMap<String, String> map = new HashMap<>();
		int status = updateMemberService.getActivateMember(member_id);
		map.put("results", status+"");
		return map;
	}

	@GetMapping(value = "/get-member-id-by-login/{id}")
	public Map<String, String> getMembersID(@PathVariable("id") int id) {
		HashMap<String, String> map = new HashMap<>();
		map = updateMemberEntityMangerFactory.getMemberIdByUserLoginId(id);
		if (map == null) {
			map.put("message", "Something went wrong ! Record not fetch...");
		}
		return map;
	}

	
	@PostMapping(value = "/member/plan/delete/{id}")
	public String deleteMemberPlanDetails(@PathVariable("id") int id) {
		JSONObject jsObject = new JSONObject();
		 int status=uploadImagesService.deleteMemberplanDetails(id);
		 if(status>0)
		 {
				jsObject.put("results", "1");
		 }
		 else {
				jsObject.put("results", "0");
		 }
		 return jsObject.toString();
		 
		 
	}
	
	@GetMapping(value = "/member/plans-details")
	public String getMembersPlanDetails() {
		JSONArray jsonResultArray = new JSONArray();
		JSONObject jsObject = new JSONObject();
		jsonResultArray = updateMemberEntityMangerFactory.getMembersPlanDetails();
		if (jsonResultArray == null) {
			jsObject.put("data", jsonResultArray);
			jsObject.put("results", "0");
			jsObject.put("message", "Something went wrong ! Record not fetch...");
		} else {
			jsObject.put("data", jsonResultArray);
			jsObject.put("results", "1");
		}
		return jsObject.toString();
	}

	@PostMapping(path = "/member/horoscope/update/{member_id}")
	public Map<String, String> updateHoroscopeDetails(@Validated @RequestBody UpdateMember updateMember,
			@PathVariable("member_id") int member_id) {
		HashMap<String, String> map = new HashMap<>();
		JSONObject jsObject = new JSONObject();
		if (updateMemberService.updateHoroscopeDetails(updateMember, member_id) != null) {
			map = updateMemberEntityMangerFactory.getHoroscopeDetails(member_id);
			if (map == null) {
				map.put("results", "0");
			} else {
				map.put("results", "1");
			}
			return map;
		} else {
			map.put("results", "0");
		}
		return map;
	}

//	@GetMapping(value = "/member/horoscope/get/{member_id}")
//	public String getHoroscopeDetails(@PathVariable("member_id") int member_id) {
//		 JSONArray jsonResultArray = new JSONArray();
//		 jsonResultArray=updateMemberEntityMangerFactory.getHoroscopeDetails(member_id);
//		 return jsonResultArray.toString();
//	}

	@GetMapping(value = "/member/horoscope/get/{id}")
	public Map<String, String> getHoroscopeDetails(@PathVariable("id") int id) {
		HashMap<String, String> map = new HashMap<>();
		JSONObject jsObject = new JSONObject();
		map = updateMemberEntityMangerFactory.getHoroscopeDetails(id);

		if (map == null) {
			map.put("results", "0");
			map.put("message", "Something went wrong ! Record not fetch...");
		} else {
			map.put("results", "1");
		}
		return map;
	}

	// match partner - preference
	@GetMapping(value = "/member/match/preference/{member_id}/{login_id}")
	public Map<String, String> getMatchPartnerPreference(@PathVariable("member_id") int member_id,
			@PathVariable("login_id") int login_id) {
		HashMap<String, String> map = new HashMap<>();
		map = updateMemberEntityMangerFactory.getMatchPartnerPreference(member_id, login_id);
		if (map.isEmpty()) {
			map.put("results", "0");
			map.put("message", "Something went wrong ! Record not fetch...");
		}
		return map;
	}

//	<------------------------------ matche s start -------------------->

	@GetMapping(value = "/member/new/matches/{id}")
	public String getNewMatches(@PathVariable("id") String id) {
		HashMap<String, String> map = new HashMap<>();
		JSONObject jsObject = new JSONObject();
		JSONArray jsonResultArray = new JSONArray();
		UpdateMember updateMember = new UpdateMember();
		jsonResultArray = updateMemberEntityMangerFactory.getAllMemberByFilter(updateMember, Integer.parseInt(id),
				"NEW_MATCHES");
//		 jsonResultArray=updateMemberEntityMangerFactory.getNewMatches(Integer.parseInt(id),"NEW_MATCHES");	
		if (jsonResultArray != null) {
			jsObject.put("data", jsonResultArray);
			jsObject.put("results", "1");
		} else {
			jsObject.put("data", jsonResultArray);
			jsObject.put("results", "0");
		}
//		System.out.println("data"+jsObject.toString());
		return jsObject.toString();
	}

	@GetMapping(value = "/member/my/matches/{id}")
	public String getmyMatches(@PathVariable("id") String id) {
		HashMap<String, String> map = new HashMap<>();
		JSONObject jsObject = new JSONObject();
		JSONArray jsonResultArray = new JSONArray();
		UpdateMember updateMember = new UpdateMember();
		jsonResultArray = updateMemberEntityMangerFactory.getAllMemberByFilter(updateMember, Integer.parseInt(id),
				"MY_MATCHES");
//		 jsonResultArray=updateMemberEntityMangerFactory.getNewMatches(Integer.parseInt(id),"MY_MATCHES");
		if (jsonResultArray != null) {
			jsObject.put("data", jsonResultArray);
			jsObject.put("results", "1");
		} else {
			jsObject.put("data", jsonResultArray);
			jsObject.put("results", "0");
		}
		return jsObject.toString();
	}

	@GetMapping(value = "/member/todays/matches/{id}")
	public String getTodaysMatches(@PathVariable("id") String id) {
		HashMap<String, String> map = new HashMap<>();
		JSONObject jsObject = new JSONObject();
		JSONArray jsonResultArray = new JSONArray();
		UpdateMember updateMember = new UpdateMember();
		jsonResultArray = updateMemberEntityMangerFactory.getAllMemberByFilter(updateMember, Integer.parseInt(id),
				"TODAYS_MATCHES");
//		 jsonResultArray=updateMemberEntityMangerFactory.getNewMatches(Integer.parseInt(id),"TODAYS_MATCHES");
		if (jsonResultArray != null) {
			jsObject.put("data", jsonResultArray);
			jsObject.put("results", "1");
		} else {
			jsObject.put("data", jsonResultArray);
			jsObject.put("results", "0");
		}
		return jsObject.toString();
	}

	@GetMapping(value = "/member/recent-visitors/{id}")
	public String getRecentVisitors(@PathVariable("id") String id) {
		HashMap<String, String> map = new HashMap<>();
		JSONObject jsObject = new JSONObject();
		JSONArray jsonResultArray = new JSONArray();
		UpdateMember updateMember = new UpdateMember();
		jsonResultArray = updateMemberEntityMangerFactory.getRecentVisitorsFilter(updateMember, Integer.parseInt(id),
				"Recently_Visitors");
//		 jsonResultArray=updateMemberEntityMangerFactory.getNewMatches(Integer.parseInt(id),"TODAYS_MATCHES");
		if (jsonResultArray != null) {
			jsObject.put("data", jsonResultArray);
			jsObject.put("results", "1");
		} else {
			jsObject.put("data", jsonResultArray);
			jsObject.put("results", "0");
		}
		return jsObject.toString();
	}

	@GetMapping(value = "/member/view-to/{id}")
	public String getViewTo(@PathVariable("id") String id) {
		HashMap<String, String> map = new HashMap<>();
		JSONObject jsObject = new JSONObject();
		JSONArray jsonResultArray = new JSONArray();
		UpdateMember updateMember = new UpdateMember();
		jsonResultArray = updateMemberEntityMangerFactory.getRecentVisitorsFilter(updateMember, Integer.parseInt(id),
				"View_To");
//		 jsonResultArray=updateMemberEntityMangerFactory.getNewMatches(Integer.parseInt(id),"TODAYS_MATCHES");
		if (jsonResultArray != null) {
			jsObject.put("data", jsonResultArray);
			jsObject.put("results", "1");
		} else {
			jsObject.put("data", jsonResultArray);
			jsObject.put("results", "0");
		}
		return jsObject.toString();
	}

//	<------------------------------ matches end  -------------------->

//	<------------------------------ search matches   -------------------->
	
	@GetMapping(value = "/member/search/more/{id}")
	public String getSearchMatchesMore(@PathVariable("id") String id) {
		HashMap<String, String> map = new HashMap<>();
		JSONObject jsObject = new JSONObject();
		JSONArray jsonResultArray = new JSONArray();
		UpdateMember updateMember = new UpdateMember();
		jsonResultArray = updateMemberEntityMangerFactory.getAllMemberByFilter(updateMember, Integer.parseInt(id),"");
//		 jsonResultArray=updateMemberEntityMangerFactory.getNewMatches(Integer.parseInt(id),"NEW_MATCHES");
		if (jsonResultArray != null) {
			jsObject.put("data", jsonResultArray);
			jsObject.put("results", "1");
		} else {
			jsObject.put("data", jsonResultArray);
			jsObject.put("results", "0");
		}
		return jsObject.toString();
	}

	
//	<------------------------------ add admin filter data -------------------->
	

	@PostMapping(value="/member/filterSearch")
	public String getNewMatches(@Validated @RequestBody FilterSearchModel filterSearchModel){
		HashMap<String, String> map = new HashMap<>();
		JSONObject jsObject = new JSONObject();
		JSONArray jsonResultArray = new JSONArray();
		UpdateMember updateMember = new UpdateMember();
		jsonResultArray = updateMemberEntityMangerFactory.getAllMemberByFilter(filterSearchModel);
		if (jsonResultArray != null) {
			jsObject.put("data", jsonResultArray);
			jsObject.put("results", "1");
		} else {
			jsObject.put("data", jsonResultArray);
			jsObject.put("results", "0");
		}
		return jsObject.toString();
	}
	
	@GetMapping(value = "/member/send-email-server")
	public String getNewMatches(){
		String success="false";
		try {
			ServerEmailService serverEmailService=new ServerEmailService();
			String email_body="test mail by java code";
			String emailId_to="vikasuttamaher@gmail.com";
			serverEmailService.send(emailId_to, "Accept Request- Saathidaar", email_body);
			success="true";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return success;
	}
	

}
