package com.sathidar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sathidar.model.EmailSMSAlertModel;
import com.sathidar.model.UpdateMember;

@Repository
public interface EmailSMSRepository extends JpaRepository<EmailSMSAlertModel, Integer> {

	@Query(value="SELECT * FROM email_sms_alert",nativeQuery=true)
	List<EmailSMSAlertModel> getEmailAlert();

}
