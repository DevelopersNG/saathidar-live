package com.sathidar.util;

import java.io.File;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SchedularRecentVisitors {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private SendSMSAction sendSMSAction;

//	@Scheduled(cron="19 15 * * * ?")
	public void backupDataBase() {

		try {
//			/******************************************************/
//			// Database Properties
//			/******************************************************/
//			String dbName = "saathidar";
//			String dbUser = "root";
//			String dbPass = "admin@123";
//			/***********************************************************/
//			// Execute Shell Command
//			/***********************************************************/
//			String executeCmd = "";
//
//			executeCmd = "mysqldump -u " + dbUser + " -p" + dbPass + " " + dbName + " -r saathidaar.sql";
//			
//			File dir = new File("D:/");
////			File dir = new File("D:/saathidaar_backup/");
//			Process runtimeProcess = Runtime.getRuntime().exec( executeCmd,null,dir);
//			int processComplete = runtimeProcess.waitFor();
//			if (processComplete == 0) {
//				System.out.print("success");
//			} else {
//				System.out.print(" failure success");
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
