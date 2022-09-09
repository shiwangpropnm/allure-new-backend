package com.allure.service;

import com.allure.dto.LoggedInUserDTO;
import com.allure.dto.plan.GetPlansPurchaseHistoryResponseDTO;
import com.allure.dto.plan.GetPlansResponseDTO;
import com.allure.dto.plan.GetUserPlanResponseDTO;
import com.allure.dto.plan.PostPurchasePlanDTO;

public interface PlanService {

	GetPlansResponseDTO getPlans();

	void userPurchasePlan(LoggedInUserDTO loggedInUserDTO, PostPurchasePlanDTO postPurchasePlanDTO);

	GetUserPlanResponseDTO getUserPlan(LoggedInUserDTO loggedInUserDTO);

	GetPlansPurchaseHistoryResponseDTO getUserPlanPurchaseHistory(LoggedInUserDTO loggedInUserDTO);

}
