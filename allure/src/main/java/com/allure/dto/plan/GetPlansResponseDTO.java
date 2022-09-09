package com.allure.dto.plan;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetPlansResponseDTO {
	
	private List<GetPlansDTO> plans;
	
}
