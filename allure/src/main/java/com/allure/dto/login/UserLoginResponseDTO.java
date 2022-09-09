package com.allure.dto.login;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginResponseDTO {

	private int id;
	
	private int loginType;

	private String email;

	private String mobile;

	private String otp;

	@JsonProperty("otp_verified")
	private String otpVerified;

	@JsonProperty("google_id")
	private String googleId;

	@JsonProperty("facebook_id")
	private String facebookId;

	@JsonProperty("acc_status")
	private byte accStatus;

	@JsonProperty("inactive_till")
	private Date inactiveTill;

	@JsonProperty("isExistingUser")
	private boolean isExistingUser;

	private String token;

}
