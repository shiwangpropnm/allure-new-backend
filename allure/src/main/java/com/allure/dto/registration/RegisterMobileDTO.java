package com.allure.dto.registration;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterMobileDTO {

	private int id;

	private String mobile;
	
	@JsonProperty("isExistingUser")
	private boolean isExistingUser;

}
