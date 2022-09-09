package com.allure.dto.registration;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterMobileResponseDTO {


	UserRegisterMobileResponseDTO user;
	
	public RegisterMobileResponseDTO(int tempId, int otp) {
		this.user = new UserRegisterMobileResponseDTO(tempId, otp);
	}
	
	@Getter
	@Setter
	class UserRegisterMobileResponseDTO{
		private int tempId;

		private int otp;

		public UserRegisterMobileResponseDTO(int tempId, int otp) {
			this.tempId = tempId;
			this.otp = otp;
		}
	}
	
}
