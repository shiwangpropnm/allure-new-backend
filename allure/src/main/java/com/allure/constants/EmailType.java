package com.allure.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EmailType {
	
	FORGOT_PASSWORD("FORGOT-PASSWORD"),
	UPDATE_EMAIL_WARNING("UPDATE-EMAIL-WARNING"),
	UPDATE_EMAIL_OTP("UPDATE-EMAIL-OTP");
	
	private String type;
}
