package com.allure.dto.login;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class VerifyOtpDTO {
	
	private Integer id;
	
	private int otp;
	
	@JsonProperty("isExistingUser")
	boolean isExistingUser;
	
	private Integer tempId;
	
}
