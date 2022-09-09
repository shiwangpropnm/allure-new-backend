package com.allure.dto.usersetting;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetShowMeResponseDTO {

	private ShowMeDTO getShowMe;
	
	public GetShowMeResponseDTO(Byte showMeId, String showMeValue) {
		getShowMe = new ShowMeDTO(showMeId, showMeValue);
	}
}
