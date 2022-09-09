package com.allure.dto.plan;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetPlansPurchaseHistoryResponseDTO {

	private List<GetPlanPurchaseHistoryDTO> purchases;
}
