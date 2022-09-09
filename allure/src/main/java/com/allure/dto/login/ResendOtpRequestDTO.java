package com.allure.dto.login;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResendOtpRequestDTO {

	private int id;
	
	@JsonProperty("isExistingUser")
	private boolean isExistingUser;

}
