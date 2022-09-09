package com.allure.dto.usersetting;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UpdateEmailVerifyOtpDTO {

	private Integer id;
	
	private int otp;
	
	private String newEmail;
	
	private String oldEmail;
}
