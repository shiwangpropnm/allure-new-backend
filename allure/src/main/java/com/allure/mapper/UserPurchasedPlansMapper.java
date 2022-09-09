package com.allure.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.allure.dto.plan.GetPlanPurchaseHistoryDTO;
import com.allure.dto.plan.GetUserPlanDTO;
import com.allure.dto.plan.PostPurchasePlanDTO;
import com.allure.entity.UserPurchasedPlans;

@Mapper(componentModel = "spring", uses = { UserMapper.class, PlanMapper.class, PlanPaymentStrutureMapper.class })
public interface UserPurchasedPlansMapper {

	@Mappings({ @Mapping(target = "users", source = "userId"),
			@Mapping(target = "plans", source = "postPurchasePlanDTO.planId"),
			@Mapping(target = "planPaymentStructures", source = "postPurchasePlanDTO.paymentStructureId"),
			@Mapping(target = "isSuccess", source = "postPurchasePlanDTO.isSuccess"),
			@Mapping(target = "id", ignore = true), @Mapping(target = "validtill", ignore = true) })
	UserPurchasedPlans postPurchasePlanDTOToUserPurchasedPlans(final PostPurchasePlanDTO postPurchasePlanDTO,
			final int userId);

	@Mappings({ 
		@Mapping(target = "userPlanPurchasedId", source = "purchasedPlan.id"),
		@Mapping(target = "boostCount", source = "purchasedPlan.plans.boostCount"),
		@Mapping(target = "messageBeforeMatch", source = "purchasedPlan.plans.messageBeforeMatch"),
		@Mapping(target = "newTopPicks", source = "purchasedPlan.plans.newTopPicks"),
		@Mapping(target = "noAdsAfterSwipes", source = "purchasedPlan.plans.noAdsAfterSwipes"),
		@Mapping(target = "passportLocation", source = "purchasedPlan.plans.passportLocation"),
		@Mapping(target = "planName", source = "purchasedPlan.plans.name"),
		@Mapping(target = "prioritizedLikes", source = "purchasedPlan.plans.prioritizedLikes"),
		@Mapping(target = "seeLikedBy", source = "purchasedPlan.plans.seeLikedBy"),
		@Mapping(target = "seePreviousLikesDays", source = "purchasedPlan.plans.seePreviousLikesDays"),
		@Mapping(target = "superswipesCount", source = "purchasedPlan.plans.superswipesCount"),
		@Mapping(target = "swipesCount", source = "purchasedPlan.plans.swipesCount"),
		@Mapping(target = "type", source = "purchasedPlan.plans.type"),
		@Mapping(target = "undoCount", source = "purchasedPlan.plans.undoCount"),
		@Mapping(target = "price", source = "purchasedPlan.planPaymentStructures.price"),
		@Mapping(target = "validity", source = "purchasedPlan.planPaymentStructures.validity")
		})
	GetUserPlanDTO userPurchasedPlanToGetUserPlanDTO(UserPurchasedPlans purchasedPlan);

	List<GetPlanPurchaseHistoryDTO> userPurchasedPlanListToGetPlanPurchaseHistoryDTOList(
			List<UserPurchasedPlans> userPurchasedPlans);
	@Mappings({ @Mapping(target = "userPlanPurchaseId", source = "id"),
		@Mapping(target = "name", source = "plans.name"),
		@Mapping(target = "type", source = "plans.type"),
		@Mapping(target = "price", source = "planPaymentStructures.price")}
	)
	GetPlanPurchaseHistoryDTO userPurchasedPlanToGetPlanPurchaseHistoryDTO(UserPurchasedPlans userPurchasedPlans);
}
