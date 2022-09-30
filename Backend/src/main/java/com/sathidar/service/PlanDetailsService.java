package com.sathidar.service;

import java.util.HashMap;

import org.json.JSONArray;
import org.springframework.stereotype.Service;

import com.sathidar.model.PlanDetailsModel;

@Service
public interface PlanDetailsService {

	int setValidFeatureID(String feature_id);

	int setInValidFeatureID(String feature_id);

	int updatePlanFeaturesByID(String feature_id, PlanDetailsModel planDetailsModel);

	int updatePlanDetails(PlanDetailsModel planDetailsModel);

	int addPlanDetails(PlanDetailsModel planDetailsModel);

	int deletePlanDetails(int plan_id);

	JSONArray getAllFeatures();

//	int updatePlanDetails(PlanDetailsModel planDetailsModel);

//	public JSONArray getplanDetails(String string);
//
//	public Object UpdatePlanDetails(PlanDetailsModel planDetailsModel, int plan_id);

}
