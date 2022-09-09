package com.allure.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.allure.dto.plan.GetPlanPaymentStructuresDTO;
import com.allure.entity.PlanPaymentStructures;

@Mapper(componentModel = "spring")
public interface PlanPaymentStrutureMapper {

	List<GetPlanPaymentStructuresDTO> planPaymentStructureListToGetPlanPaymentStructuresDTOList(
			final List<PlanPaymentStructures> planPaymentStructures);

	@Mapping(target = "paymentStructureId", source = "id")
	GetPlanPaymentStructuresDTO planPaymentStructureToGetPlanPaymentStructuresDTO(
			final PlanPaymentStructures planPaymentStructures);
	
	@Mapping(target="id", source="planPaymentStructureId")
	PlanPaymentStructures planPaymentStructureIdToNonEntityPlanPaymentStructures(final Integer planPaymentStructureId);
	
}
