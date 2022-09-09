package com.allure.dto.usersetting;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetUsernameResponseDTO {

	private UsernameDTO getUserName;

	public GetUsernameResponseDTO(String username) {
		getUserName = new UsernameDTO(username);
	}

}
