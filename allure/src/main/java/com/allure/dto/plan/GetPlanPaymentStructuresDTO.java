package com.allure.dto.plan;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetPlanPaymentStructuresDTO {
	
	private Integer paymentStructureId;
	
	private int validity;
	
	private double price;
	
	private boolean isPopular;

}
