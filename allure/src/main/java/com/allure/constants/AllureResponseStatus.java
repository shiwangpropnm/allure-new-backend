package com.allure.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AllureResponseStatus {
	SUCCESS(1),
	FAIL(0);
	
	private int code;
}
