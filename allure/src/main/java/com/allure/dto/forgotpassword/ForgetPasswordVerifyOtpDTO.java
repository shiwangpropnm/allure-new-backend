package com.allure.dto.forgotpassword;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ForgetPasswordVerifyOtpDTO {

	private int tempId;
	
	private int otp;
}
