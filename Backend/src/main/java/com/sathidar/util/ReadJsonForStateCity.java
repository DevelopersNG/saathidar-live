package com.sathidar.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sathidar.EntityMangerFactory.UserEntityManagerFactory;

@Service
public class ReadJsonForStateCity {

	@Autowired
	private static UserEntityManagerFactory userEntityManagerFactory;
	
	public static void main(String[] args) {
	
        try {        
        	InputStream inputStream=  new FileInputStream("D:\\NG Digital\\India-State-city.xlsx");
        	XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        	Sheet sheet = workbook.getSheet("Sheet1");
        	Iterator<Row> rows = sheet.iterator();
        	int saveAndGetLastInsertedID=0;
        	 String active_state="",active_city="",field_state_name="",field_city_name="";
        	while (rows.hasNext()) {
        	  Row currentRow = rows.next();
        	  Iterator<Cell> cellsInRow = currentRow.iterator();
        	  while (cellsInRow.hasNext()) {
        	     Cell currentCell = cellsInRow.next();
        	     // each cell case
        	     String country = currentCell.getStringCellValue();
        	     currentCell = cellsInRow.next();
        	     String state = currentCell.getStringCellValue();
        	    
        	     if(!state.equals(active_state)) {
        	    	 // record saved
//        	    	  saveAndGetLastInsertedID=userEntityManagerFactory.saveState(state);
        	    	 field_state_name=field_state_name + "('" +  state + "','"+ 1+"'),";
        	    	 //get last inserted id -state_id
        	    	 ++saveAndGetLastInsertedID;
        	    	 active_state=state;
        	     }
        	     String city ="";
        	     if(saveAndGetLastInsertedID>0) {
        	    	  currentCell = cellsInRow.next();
        	    	  city = currentCell.getStringCellValue();
             	      if(!city.equals(active_city)) {	
             	    	 // record saved
//             	    	 userEntityManagerFactory.saveStateIDAndCity(city,saveAndGetLastInsertedID);
             	    	 field_city_name=field_city_name + "('" +  city + "','"+ saveAndGetLastInsertedID+"'),";
             	    	 active_city=city;
             	      }
    	    	 }
        	   
//        	     System.out.println("Country- "+country + ", state- "+ state+", city- "+city);
        	  }
        	}    
        	
        	
//        	String stateQuery="insert into states (state_name,country_id) values ";
//        	stateQuery=stateQuery+field_state_name;
//        	System.out.println(stateQuery);
        	
        	String cityQuery="insert into city (city_name,state_id) values ";
        	cityQuery=cityQuery+field_city_name;
        	System.out.println(cityQuery);
        	workbook.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }  
	}
}
