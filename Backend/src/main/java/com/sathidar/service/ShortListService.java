package com.sathidar.service;

import org.json.JSONArray;

import com.sathidar.model.ShortListsModel;

public interface ShortListService {

	JSONArray AddToShortLists(ShortListsModel shortListsModel);

	JSONArray GetShortListsMember(String member_id);

	JSONArray RemoveToShortLists(ShortListsModel shortListsModel);

}
