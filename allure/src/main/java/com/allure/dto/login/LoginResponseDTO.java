package com.allure.dto.login;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponseDTO {

	private UserLoginResponseDTO user;
	
	public LoginResponseDTO(UserLoginResponseDTO user) {
		this.user = user;
	}
}

