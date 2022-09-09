package com.allure.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.allure.constants.PlanType;
import com.allure.dao.PlanPaymentStructureRepository;
import com.allure.dao.PlanRepository;
import com.allure.dao.UserPurchasedPlanRepository;
import com.allure.dto.LoggedInUserDTO;
import com.allure.dto.plan.GetPlansPurchaseHistoryResponseDTO;
import com.allure.dto.plan.GetPlansResponseDTO;
import com.allure.dto.plan.GetUserPlanDTO;
import com.allure.dto.plan.GetUserPlanResponseDTO;
import com.allure.dto.plan.PostPurchasePlanDTO;
import com.allure.entity.PlanPaymentStructures;
import com.allure.entity.Plans;
import com.allure.entity.UserPurchasedPlans;
import com.allure.mapper.PlanMapper;
import com.allure.mapper.UserMapper;
import com.allure.mapper.UserPurchasedPlansMapper;
import com.allure.service.PlanService;

@Service
public class PlanServiceImpl implements PlanService {

	@Autowired
	PlanRepository planRepository;

	@Autowired
	PlanPaymentStructureRepository planPaymentStructuresRepository;

	@Autowired
	MessageSource messageSource;

	@Autowired
	PlanMapper planMapper;

	@Autowired
	UserMapper userMapper;

	@Autowired
	UserPurchasedPlansMapper userPurchasedPlansMapper;

	@Autowired
	UserPurchasedPlanRepository userPurchasedPlanRepository;

	@Override
	public GetPlansResponseDTO getPlans() {
		List<Plans> plans = planRepository.findAllByIsDeleted(false);

		return new GetPlansResponseDTO(planMapper.planListToGetPlansDTOList(plans));

	}

	@Override
	public void userPurchasePlan(LoggedInUserDTO loggedInUserDTO, PostPurchasePlanDTO postPurchasePlanDTO) {
		Plans plan = planRepository.getById(postPurchasePlanDTO.getPlanId());
		if (plan == null)
			throw new EntityNotFoundException(messageSource.getMessage("exception.plan.not.found",
					new Object[] { postPurchasePlanDTO.getPlanId() }, Locale.ENGLISH));
		PlanPaymentStructures paymentStructure = planPaymentStructuresRepository
				.getById(postPurchasePlanDTO.getPaymentStructureId());
		if (paymentStructure == null)
			throw new EntityNotFoundException(messageSource.getMessage("exception.plan.payment.structure.not.found",
					new Object[] { postPurchasePlanDTO.getPaymentStructureId() }, Locale.ENGLISH));
		UserPurchasedPlans userPurchasedPlans = userPurchasedPlansMapper
				.postPurchasePlanDTOToUserPurchasedPlans(postPurchasePlanDTO, loggedInUserDTO.getLoggedInUserId());
		if (plan.getType().equalsIgnoreCase(PlanType.PLAN.getValue())) {
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.MONTH, paymentStructure.getValidity());
			userPurchasedPlans.setValidtill(calendar.getTime());
		}
		userPurchasedPlanRepository.save(userPurchasedPlans);
	}

	@Override
	public GetUserPlanResponseDTO getUserPlan(LoggedInUserDTO loggedInUserDTO) {
		UserPurchasedPlans purchasedPlan = userPurchasedPlanRepository
				.findByUsersAndPlansTypeAndTransactionOnBeforeAndValidtillAfterAndIsSuccess(
						userMapper.idToUsersNonEntity(loggedInUserDTO.getLoggedInUserId()), PlanType.PLAN.toString(),
						new Date(), new Date(), true);
		if (purchasedPlan == null)
			throw new EntityNotFoundException(
					messageSource.getMessage("exception.user.no.active.plan", null, Locale.ENGLISH));

		GetUserPlanDTO userPlanDTO = userPurchasedPlansMapper.userPurchasedPlanToGetUserPlanDTO(purchasedPlan);
		return new GetUserPlanResponseDTO(userPlanDTO);
	}

	@Override
	public GetPlansPurchaseHistoryResponseDTO getUserPlanPurchaseHistory(LoggedInUserDTO loggedInUserDTO) {
		List<UserPurchasedPlans> userPurchasedPlans = userPurchasedPlanRepository
				.findAllByUsers(userMapper.idToUsersNonEntity(loggedInUserDTO.getLoggedInUserId()));
		return new GetPlansPurchaseHistoryResponseDTO(
				userPurchasedPlansMapper.userPurchasedPlanListToGetPlanPurchaseHistoryDTOList(userPurchasedPlans));
	}

}
