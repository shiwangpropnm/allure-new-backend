package com.allure.dto.registration;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationResponseDTO {

	private UserRegistrationResponseDTO user;

	public RegistrationResponseDTO(int tempId, boolean isExistingUser) {
		this.user = new UserRegistrationResponseDTO(tempId, isExistingUser);
	}
	
	@Getter
	@Setter
	class UserRegistrationResponseDTO {

		private int tempId;
		
		private boolean isExistingUser; 
		
		public UserRegistrationResponseDTO(int tempId, boolean isExistingUser) {
			this.tempId = tempId;
			this.isExistingUser = isExistingUser;
		}
	}
}
