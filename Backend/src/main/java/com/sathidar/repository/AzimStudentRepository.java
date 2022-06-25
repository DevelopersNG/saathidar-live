package com.sathidar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sathidar.model.AzimStudent;

@Repository
public interface AzimStudentRepository extends JpaRepository<AzimStudent, Integer> {

}
