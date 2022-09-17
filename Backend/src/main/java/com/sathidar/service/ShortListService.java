package com.sathidar.service;

import org.json.JSONArray;

import com.sathidar.model.ShortListsModel;

public interface ShortListService {

	int AddToShortLists(ShortListsModel shortListsModel);

	JSONArray GetShortListsMember(String member_id);

	int RemoveToShortLists(ShortListsModel shortListsModel);

}
