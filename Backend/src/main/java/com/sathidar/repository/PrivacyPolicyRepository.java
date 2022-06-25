package com.sathidar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sathidar.model.PrivacyOptionsModel;

@Repository
public interface PrivacyPolicyRepository extends JpaRepository<PrivacyOptionsModel, Integer>{

}
