package com.sathidar.service;

import org.json.JSONArray;

import com.sathidar.model.HideContentModel;

public interface HideContentService {

	JSONArray setHideContent(HideContentModel hideContentModel, int member_id);

	JSONArray deleteHideContent(int member_id);

}
