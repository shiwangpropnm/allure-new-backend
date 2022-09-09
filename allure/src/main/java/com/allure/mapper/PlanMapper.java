package com.allure.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.allure.dto.plan.GetPlansDTO;
import com.allure.entity.Plans;

@Mapper(componentModel = "spring", uses= {PlanPaymentStrutureMapper.class})
public interface PlanMapper {

	List<GetPlansDTO> planListToGetPlansDTOList(final List<Plans> plans);

	@Mapping(target="paymentStructures", source="planPaymentStructures")
	GetPlansDTO plansToGetPlansDTO(final Plans plans);
	
	@Mapping(target="id", source="planId")
	Plans planIdToNonEntityPlans(final Integer planId);
}
