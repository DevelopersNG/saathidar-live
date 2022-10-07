package com.sathidar.service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Service;

import com.sathidar.model.PlanDetailsModel;
import com.sathidar.model.ShortListsModel;
import com.sathidar.repository.PlanDetailsRepository;

@Service
public class PlanDetailsServiceImpl implements PlanDetailsService {

	@Autowired
	private  PlanDetailsRepository  planDetailsRepository;
	
	@Override
	public int setValidFeatureID(String feature_id) {
		return planDetailsRepository.setValidFeatureID(feature_id);
	}
	
	@Override
	public int setInValidFeatureID(String feature_id) {
		return planDetailsRepository.setInValidFeatureID(feature_id);
	}

	@Override
	public int updatePlanFeaturesByID(String feature_id, PlanDetailsModel planDetailsModel) {
		return planDetailsRepository.setInValidFeatureID(feature_id,planDetailsModel.getFeature_name());
	}

	@Override
	public int updatePlanDetails(PlanDetailsModel planDetailsModel) {
		int status=0;
		try {
			int plan_id=planDetailsModel.getId();
			String plan_name=planDetailsModel.getPlan_name();
			String plan_validity=planDetailsModel.getPlan_validity();
			String plan_price=planDetailsModel.getPlan_price();
			String plan_discount=planDetailsModel.getPlan_discount();
			String discount_price=planDetailsModel.getDiscount_price();
				
			int isAvailable=planDetailsRepository.isAvailablePlanForUpdate(plan_name,plan_id);
			if(isAvailable>0) {
				status=2;
			}else {
				status=planDetailsRepository.updatePlanDetails(plan_id,plan_name,plan_validity,plan_price,plan_discount,discount_price);
				   if(status!=0) {
//					   int getPlanID=planDetailsRepository.getPlanNameByID(plan_name);
					   if(planDetailsModel.getInformation()!=null) {
						   for(int i=0;i<planDetailsModel.getInformation().size();i++) {
							   String valid=planDetailsModel.getInformation().get(i).getValid();
							   String features_name=planDetailsModel.getInformation().get(i).getFeature_name();
							   int featureID=planDetailsModel.getInformation().get(i).getFeature_id();
							   System.out.println("valid- "+valid +", feature name- "+features_name);
							   int statusFeatures=planDetailsRepository.updateNewFeaturesName(valid,features_name,featureID);
						   }
						}
				   }
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}
	
//	@Override
//	public int addPlanDetails(PlanDetailsModel planDetailsModel) {
//		int status=0;
//		try {
////			int plan_id=planDetailsModel.getId();
//			String plan_name=planDetailsModel.getPlan_name();
//			String plan_validity=planDetailsModel.getPlan_validity();
//			String plan_price=planDetailsModel.getPlan_price();
//			String plan_discount=planDetailsModel.getPlan_discount();
//			String discount_price=planDetailsModel.getDiscount_price();
//			
//			int isAvailable=planDetailsRepository.isAvailablePlan(plan_name);
//			if(isAvailable>0) {
//				status=2;
//			}else {
//				   status=planDetailsRepository.insertPlanDetails(plan_name,plan_validity,plan_price,plan_discount,discount_price);
//				   
//				   if(status!=0) {
//					   int getPlanID=planDetailsRepository.getPlanNameByID(plan_name);
//					   if(planDetailsModel.getFeature_name()!=null && !planDetailsModel.getFeature_name().equals("")) {
//							if(planDetailsModel.getFeature_name().contains(",")) {
//								String[] arrayList= planDetailsModel.getFeature_name().split(",");
//								for(int i=0;i<arrayList.length;i++) {
//									int statusFeatures=planDetailsRepository.insertFeaturesName(arrayList[i],getPlanID);
//								}
//							}else {
//								int statusFeatures=planDetailsRepository.insertFeaturesName(planDetailsModel.getFeature_name(),getPlanID);
//							}
//						}
//				   }
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return status;
//	}

	@Override
	public int addPlanDetails(PlanDetailsModel planDetailsModel) {
		int status=0;
		try {
//			int plan_id=planDetailsModel.getId();
			String plan_name=planDetailsModel.getPlan_name();
			String plan_validity=planDetailsModel.getPlan_validity();
			String plan_price=planDetailsModel.getPlan_price();
			String plan_discount=planDetailsModel.getPlan_discount();
			String discount_price=planDetailsModel.getDiscount_price();
			
			int isAvailable=planDetailsRepository.isAvailablePlan(plan_name);
			if(isAvailable>0) {
				status=2;
			}else {
				   status=planDetailsRepository.insertPlanDetails(plan_name,plan_validity,plan_price,plan_discount,discount_price);
				   if(status!=0) {
					   int getPlanID=planDetailsRepository.getPlanNameByID(plan_name);
					   
					   if(planDetailsModel.getInformation()!=null) {
						   for(int i=0;i<planDetailsModel.getInformation().size();i++) {
							   String valid=planDetailsModel.getInformation().get(i).getValid();
							   String features_name=planDetailsModel.getInformation().get(i).getFeature_name();
							   
							   System.out.println("valid- "+valid +", feature name- "+features_name);
							   int statusFeatures=planDetailsRepository.insertNewFeaturesName(valid,features_name,getPlanID);
						   }
						}
				   }
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public int deletePlanDetails(int plan_id) {
		return planDetailsRepository.deletePlanDetails(plan_id);
	}

	@Override
	public JSONArray getAllFeatures() {
		JSONArray resultArray = new JSONArray();
			JSONObject json = new JSONObject();			
			json.put("features_id", 1);
			json.put("features_name","Accept/ Decline an interest request");
			resultArray.put(json);
		 	
			json = new JSONObject();
			json.put("features_id", 2);
			json.put("features_name","View Member Contact Details & Photos");
			resultArray.put(json);

			json = new JSONObject();
			json.put("features_id", 3);
			json.put("features_name","Privacy Protection Photos / Contacts");
			resultArray.put(json);
		 	
			json = new JSONObject();
			json.put("features_id", 4);
			json.put("features_name","Shortlist Profiles & Chat");
			resultArray.put(json);

			json = new JSONObject();
			json.put("features_id", 5);
			json.put("features_name","Block Members");
			resultArray.put(json);

			json = new JSONObject();
			json.put("features_id", 6);
			json.put("features_name","Profile Booster- Add on Feature");
			resultArray.put(json);

			json = new JSONObject();
			json.put("features_id", 7);
			json.put("features_name","Matchmaking Personalised Service");
			resultArray.put(json);

			json = new JSONObject();
			json.put("features_id", 8);
			json.put("features_name","Customer Support");
			resultArray.put(json);
		return resultArray;
	}

	@Override
	public int deleteFeaturesPlanDetails(int id) {
		return planDetailsRepository.deleteFeaturesPlanDetails(id);
	}

	
//	@Override
//	public int updatePlanDetails(PlanDetailsModel planDetailsModel) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
	
//	
//	@Override
//	public JSONArray AddPlans(PlanDetailsModel planDetailsModel) {
//		Object requestMemberObject = null;
//		JSONArray resultArray = new JSONArray();
//
//		try {
//			JSONObject json = new JSONObject();
//			Integer id=planDetailsModel.getId();
//			Integer plan_id = planDetailsModel.getPlanId();
//			String Plan_name = planDetailsModel.getPlanName();
//			String plan_validity = planDetailsModel.getPlanVaidity();
//			requestMemberObject = planDetailsModel.addPlans(plan_id, id,);
//			json.put("message", "added to shortlists");
//			json.put("results", "1");
//
//			if (requestMemberObject == null) {
//				json.put("message", "not added to shortlists");
//				json.put("results", "0");
//			}
//
//			resultArray.put(json);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		return resultArray;
//	}
//
//	@Override
//	public List<PlanDetailsModel> findAll() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public List<PlanDetailsModel> findAll(Sort sort) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public List<PlanDetailsModel> findAllById(Iterable<Integer> ids) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public <S extends PlanDetailsModel> List<S> saveAll(Iterable<S> entities) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public void flush() {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public <S extends PlanDetailsModel> S saveAndFlush(S entity) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public <S extends PlanDetailsModel> List<S> saveAllAndFlush(Iterable<S> entities) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public void deleteAllInBatch(Iterable<PlanDetailsModel> entities) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void deleteAllByIdInBatch(Iterable<Integer> ids) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void deleteAllInBatch() {
//		// TODO Auto-generated method stub
//	}
//
//	@Override
//	public PlanDetailsModel getOne(Integer id) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public PlanDetailsModel getById(Integer id) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public <S extends PlanDetailsModel> List<S> findAll(Example<S> example) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public <S extends PlanDetailsModel> List<S> findAll(Example<S> example, Sort sort) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Page<PlanDetailsModel> findAll(Pageable pageable) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Optional<PlanDetailsModel> findById(Integer id) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public boolean existsById(Integer id) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public long count() {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public void deleteById(Integer id) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void delete(PlanDetailsModel entity) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void deleteAllById(Iterable<? extends Integer> ids) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void deleteAll(Iterable<? extends PlanDetailsModel> entities) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void deleteAll() {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public <S extends PlanDetailsModel> Optional<S> findOne(Example<S> example) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public <S extends PlanDetailsModel> Page<S> findAll(Example<S> example, Pageable pageable) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public <S extends PlanDetailsModel> long count(Example<S> example) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public <S extends PlanDetailsModel> boolean exists(Example<S> example) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public <S extends PlanDetailsModel, R> R findBy(Example<S> example,
//			Function<FetchableFluentQuery<S>, R> queryFunction) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public int getpanDetails1(int plan_id) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public int getpanDetails(int plan_id) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public int getPlanDetails(int plan_id) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public PlanDetailsModel save(PlanDetailsModel planDetailsModel) {
//		// TODO Auto-generated method stub
//		return null;
//	}



	
}
