package com.allure.dto.login;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDTO {

	private int loginType;

	private String email;

	private String password;

	private String phoneNumber;

	private String facebookId;

	private String googleId;

}
