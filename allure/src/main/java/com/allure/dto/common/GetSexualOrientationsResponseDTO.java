package com.allure.dto.common;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetSexualOrientationsResponseDTO {
	
	public GetSexualOrientationsResponseDTO(List<SexualOrientationDTO> sexualOrientations) {
		this.sexualOrientations = sexualOrientations;
	}

	List<SexualOrientationDTO> sexualOrientations;
	
	
}
