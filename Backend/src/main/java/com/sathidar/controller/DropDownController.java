package com.sathidar.controller;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sathidar.EntityMangerFactory.DropDownEntityManagerFactory;

//@CrossOrigin(origins = "http://localhost:4200", methods = { RequestMethod.OPTIONS, RequestMethod.GET,
//		RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE }, allowedHeaders = "*", allowCredentials = "true")

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class DropDownController {

	
	@Autowired
	private DropDownEntityManagerFactory dropDownEntityManagerFactory;
	
	@GetMapping(value = "/get/country")
	public String getCountryName() {
		 JSONObject jsObject = new JSONObject();
		 JSONArray jsonResultArray = new JSONArray();
		 jsonResultArray=dropDownEntityManagerFactory.getCountryName();
		 if(jsonResultArray!=null)
			 jsObject.put("results",1);
		 else
			 jsObject.put("results",0);
		 jsObject.put("country",jsonResultArray);
		 return jsObject.toString();
	}
	
	@GetMapping(value = "/get/state")
	public String getStateList() {
//		 JSONArray jsonResultArray = new JSONArray();
//		 jsonResultArray=dropDownEntityManagerFactory.getStateList();
//		 return jsonResultArray.toString();
		 JSONObject jsObject = new JSONObject();
		 JSONArray jsonResultArray = new JSONArray();
		 jsonResultArray=dropDownEntityManagerFactory.getStateList();
		 if(jsonResultArray!=null)
			 jsObject.put("results",1);
		 else
			 jsObject.put("results",0);
		 jsObject.put("state",jsonResultArray);
		 return jsObject.toString();
		
	}
	
	@GetMapping(value = "/get/all/city")
	public String getAllCityList() {
		 JSONObject jsObject = new JSONObject();
		 JSONArray jsonResultArray = new JSONArray();
		 jsonResultArray=dropDownEntityManagerFactory.getAllCityList();
		 if(jsonResultArray!=null)
			 jsObject.put("results",1);
		 else
			 jsObject.put("results",0);
		 jsObject.put("city",jsonResultArray);
		 return jsObject.toString();
	}
	
	@GetMapping(value = "/get/all/cast")
	public String getAllCastList() {
		 JSONObject jsObject = new JSONObject();
		 JSONArray jsonResultArray = new JSONArray();
		 jsonResultArray=dropDownEntityManagerFactory.getAllCastList();
		 if(jsonResultArray!=null)
			 jsObject.put("results",1);
		 else
			 jsObject.put("results",0);
		 jsObject.put("cast",jsonResultArray);
		 return jsObject.toString();
	}
	// ********************************country-state-city by id******************************
	
	
	@GetMapping(value = "/get/state-name/{country_id}")
	public String getStateName(@PathVariable("country_id") int country_id) {
		 JSONObject jsObject = new JSONObject();
		 JSONArray jsonResultArray = new JSONArray();
		 jsonResultArray=dropDownEntityManagerFactory.getStateName(country_id);
		 if(jsonResultArray!=null)
			 jsObject.put("results",1);
		 else
			 jsObject.put("results",0);
		 jsObject.put("state",jsonResultArray);
		 return jsObject.toString();
	}
	
	@GetMapping(value = "/get/city-name/{state_id}")
	public String getCityName(@PathVariable("state_id") int state_id) {
		 JSONObject jsObject = new JSONObject();
		 JSONArray jsonResultArray = new JSONArray();
		 jsonResultArray=dropDownEntityManagerFactory.getCityName(state_id);
		 if(jsonResultArray!=null)
			 jsObject.put("results",1);
		 else
			 jsObject.put("results",0);
		 jsObject.put("cities",jsonResultArray);
		 return jsObject.toString();
	}
	
	// ********************************country-state-city by name******************************

//	@GetMapping(value = "/get/city-name/by/state-name/{state_name}")
	@RequestMapping(value = "/get/city-name/by/state-name/{state_name}", method = RequestMethod.GET)
	public String getCityNameByStateName(@PathVariable("state_name") String state_name) {
		 JSONObject jsObject = new JSONObject();
		 JSONArray jsonResultArray = new JSONArray();
		 System.out.println("****************************"+state_name);
		 jsonResultArray=dropDownEntityManagerFactory.getCityNameByStateName(state_name);
		 if(jsonResultArray!=null)
			 jsObject.put("results",1);
		 else
			 jsObject.put("results",0);
		 jsObject.put("cities",jsonResultArray);
		 
		 return jsObject.toString();
	}
	
	
	
	@RequestMapping(value = "/get/state-name/by/country-name/{country_name}", method = RequestMethod.GET)
	public String getStateNameByCountryName(@PathVariable("country_name") String country_name) {
		 JSONObject jsObject = new JSONObject();
		JSONArray jsonResultArray = new JSONArray();
		 System.out.println("****************************"+country_name);
		 jsonResultArray=dropDownEntityManagerFactory.getStateNameByCountryName(country_name);
		 if(jsonResultArray!=null)
			 jsObject.put("results",1);
		 else
			 jsObject.put("results",0);
		 jsObject.put("states",jsonResultArray);
		 return jsObject.toString();
	}
	// ********************************religion-cast by id******************************
	
	@GetMapping(value = "/get/religion-name")
	public String getReligionName() {
		 JSONObject jsObject = new JSONObject();
		 JSONArray jsonResultArray = new JSONArray();
		 jsonResultArray=dropDownEntityManagerFactory.getReligionName();
		 if(jsonResultArray!=null)
			 jsObject.put("results",1);
		 else
			 jsObject.put("results",0);
		 jsObject.put("religion",jsonResultArray);
		 return jsObject.toString();
	}
	
	@GetMapping(value = "/get/cast-name/{religion_id}")
	public String getCastName(@PathVariable("religion_id") String religion_id) {
		 JSONObject jsObject = new JSONObject();
		 JSONArray jsonResultArray = new JSONArray();
		 jsonResultArray=dropDownEntityManagerFactory.getCastName(Integer.parseInt(religion_id));
		 if(jsonResultArray!=null)
			 jsObject.put("results",1);
		 else
			 jsObject.put("results",0);
		 jsObject.put("cast",jsonResultArray);
		 return jsObject.toString();
	}
	
	// ********************************religion-cast by name******************************
	
//	@GetMapping(value = "/get/cast-name/by/religion_name/{religion_name}")
	@RequestMapping(value = "/get/cast-name/by/religion_name/{religion_name}", method = RequestMethod.GET)
	public String getCastNameBYReligionName(@PathVariable("religion_name") String religion_name) {
		JSONObject jsObject = new JSONObject();
		JSONArray jsonResultArray = new JSONArray();
		 jsonResultArray=dropDownEntityManagerFactory.getCastNameByReligionName(religion_name);
		 if(jsonResultArray!=null)
			 jsObject.put("results",1);
		 else
			 jsObject.put("results",0);
		 jsObject.put("cast",jsonResultArray);
		 return jsObject.toString();
	}
	
}
