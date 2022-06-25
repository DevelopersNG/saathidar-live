package com.sathidar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sathidar.model.AzimStudent;
import com.sathidar.repository.AzimStudentRepository;
@Service
public class AzimServiceImpl implements AzimService{

	
	@Autowired
	private AzimStudentRepository azimStudentRepository;
	
	@Override
	public AzimStudent studentRegistrations(AzimStudent student) {
		
		return azimStudentRepository.save(student);
	}

}
