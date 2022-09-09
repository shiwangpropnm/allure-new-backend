package com.allure.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.allure.constants.AllureResponseStatus;
import com.allure.dto.AllureResponseDTO;
import com.allure.dto.LoggedInUserDTO;
import com.allure.dto.plan.GetPlansPurchaseHistoryResponseDTO;
import com.allure.dto.plan.GetPlansResponseDTO;
import com.allure.dto.plan.GetUserPlanResponseDTO;
import com.allure.dto.plan.PostPurchasePlanDTO;
import com.allure.service.CommonService;
import com.allure.service.PlanService;

@RestController
@RequestMapping("/v1/plans")
public class PlanController {

	@Autowired
	PlanService planService;

	@Autowired
	CommonService commonService;

	@Autowired
	MessageSource messageSource;

	@GetMapping()
	public ResponseEntity<AllureResponseDTO<GetPlansResponseDTO>> getPlans() {

		GetPlansResponseDTO getPlansResponseDTO = planService.getPlans();

		return new ResponseEntity<>(
				new AllureResponseDTO<GetPlansResponseDTO>(getPlansResponseDTO, AllureResponseStatus.SUCCESS.getCode(),
						messageSource.getMessage("plans.get.success", null, Locale.ENGLISH)),
				HttpStatus.OK);
	}

	@PostMapping("/purchasePlan")
	public ResponseEntity<AllureResponseDTO<Object>> postPurchasePlan(LoggedInUserDTO loggedInUserDTO,
			@RequestBody PostPurchasePlanDTO postPurchasePlanDTO) {

		planService.userPurchasePlan(loggedInUserDTO, postPurchasePlanDTO);

		return new ResponseEntity<>(
				new AllureResponseDTO<Object>(null, AllureResponseStatus.SUCCESS.getCode(),
						messageSource.getMessage("plans.user.plan.purchase.success", null, Locale.ENGLISH)),
				HttpStatus.OK);
	}

	@GetMapping("/userPlan")
	public ResponseEntity<AllureResponseDTO<GetUserPlanResponseDTO>> getUserPlan(LoggedInUserDTO loggedInUserDTO) {

		GetUserPlanResponseDTO getUserPlanResponseDTO = planService.getUserPlan(loggedInUserDTO);

		return new ResponseEntity<>(new AllureResponseDTO<GetUserPlanResponseDTO>(getUserPlanResponseDTO,
				AllureResponseStatus.SUCCESS.getCode(),
				messageSource.getMessage("plans.get.user.plan.success", null, Locale.ENGLISH)), HttpStatus.OK);
	}

	@GetMapping("/userPurchaseHistory")
	public ResponseEntity<AllureResponseDTO<GetPlansPurchaseHistoryResponseDTO>> getUserPurchaseHistory(
			LoggedInUserDTO loggedInUserDTO) {

		GetPlansPurchaseHistoryResponseDTO getPlansPurchaseHistoryResponseDTO = planService
				.getUserPlanPurchaseHistory(loggedInUserDTO);

		return new ResponseEntity<>(new AllureResponseDTO<GetPlansPurchaseHistoryResponseDTO>(
				getPlansPurchaseHistoryResponseDTO, AllureResponseStatus.SUCCESS.getCode(),
				messageSource.getMessage("plans.get.purchase.history.success", null, Locale.ENGLISH)), HttpStatus.OK);
	}

}
