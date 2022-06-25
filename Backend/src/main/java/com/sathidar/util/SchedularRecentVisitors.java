package com.sathidar.util;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SchedularRecentVisitors {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private SendSMSAction sendSMSAction;
}
