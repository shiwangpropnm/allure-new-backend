package com.allure.dto.profile;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserProfileFormDataDTO {
	
	private MultipartFile dp1;
	
	private MultipartFile dp2;
	
	private MultipartFile dp3;
	
	private MultipartFile dp4;
	
	private MultipartFile dp5;
	
	private MultipartFile dp6;
	
	private UpdateUserProfileDTO profileInfo;
	
}
