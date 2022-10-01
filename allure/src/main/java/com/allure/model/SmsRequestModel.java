package com.allure.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SmsRequestModel {

	private String message;
	private String mobileNumber;
}
