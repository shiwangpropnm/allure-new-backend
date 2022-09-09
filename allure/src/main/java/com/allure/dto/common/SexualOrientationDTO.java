package com.allure.dto.common;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SexualOrientationDTO {
	
	private int id;
	
	@JsonProperty("sexual_orientation")
	private String sexualOrientaion;
	
}
