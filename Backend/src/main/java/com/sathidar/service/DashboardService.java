package com.sathidar.service;

import org.json.JSONArray;

public interface DashboardService {

	JSONArray GetAllCountDetails(String member_id);

	JSONArray GetNewMatchesDashboard(String member_id);

	JSONArray GetNewPremiumMatchesDashboard(String member_id, String string);

	JSONArray GetTotalCountAdminDashboard();

}
