package com.sathidar.service;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sathidar.exception.BadRequestException;
import com.sathidar.model.HideContentModel;
import com.sathidar.repository.HideContentRepository;

@Service
public class HideContentServiceImpl implements HideContentService {

	@Autowired
	private HideContentRepository hideContentRepository;

	@Override
	public JSONArray setHideContent(HideContentModel hideContentModel, int member_id) { 
		String columnName="";
		Object hideContentObject=null;
		JSONArray resultArray = new JSONArray();
		try { 
			columnName=hideContentModel.getColumn_name().trim();
			JSONObject json = new JSONObject();
			int checkDuplicate=hideContentRepository.checkDuplicate(member_id);
			
			if(checkDuplicate>0) {
				// update record
				hideContentObject=hideContentRepository.updateHideContent(columnName,member_id);
				json.put("message", "hide content are updated..");
			}else {
				//insert new record
				hideContentObject=hideContentRepository.setHideContent(columnName,member_id);
				json.put("message", "hide content are inserted..");
			}
			if(hideContentObject==null) {
				throw new BadRequestException("hide content not updated..");
			} 
		

			resultArray.put(json);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultArray;
	}

	@Override
	public JSONArray deleteHideContent(int member_id) {
		Object hideContentObject=null;
		JSONArray resultArray = new JSONArray();
		try { 
			hideContentObject=hideContentRepository.deleteHideContent(member_id);
			if(hideContentObject==null) {
				throw new BadRequestException("hide content not deleted..");
			} 
		
			JSONObject json = new JSONObject();
			json.put("message", "hide content are deleted..");
			resultArray.put(json);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultArray;
	}
}
