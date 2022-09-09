package com.allure.dto.login;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginByMobileResponseDTO {

	private VerifyOtpResponseDTO user;
	
	public LoginByMobileResponseDTO(VerifyOtpResponseDTO user) {
		this.user = user;
	}
}

