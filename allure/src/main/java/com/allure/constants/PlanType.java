package com.allure.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PlanType {

	PLAN("plan"),
	BOOST("boost");
	
	String value;
	
}
