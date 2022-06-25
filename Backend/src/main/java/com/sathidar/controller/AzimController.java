package com.sathidar.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sathidar.model.AzimStudent;
import com.sathidar.model.User;
import com.sathidar.service.AzimService;

@CrossOrigin(origins = "http://localhost:4200", methods = { RequestMethod.OPTIONS, RequestMethod.GET,
		RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE }, allowedHeaders = "*", allowCredentials = "true")
@RestController
@RequestMapping("/api")
public class AzimController {

	
	@Autowired
	private AzimService azimService;
	
	
	@PostMapping(path = "/student/registrations")
	public Map<String, String> studentRegistrations(@Validated @RequestBody AzimStudent student) {
		HashMap<String, String> map = new HashMap<>();
		Object obj = azimService.studentRegistrations(student);
		if (obj!=null) {
			map.put("message", "student add successfully");
		} else {
			map.put("message", "student not registered...");
		}
		return map;
	}
}
