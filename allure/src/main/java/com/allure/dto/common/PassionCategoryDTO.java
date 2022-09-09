package com.allure.dto.common;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PassionCategoryDTO {

	private int id;
	
	@JsonProperty("passion_category")
	private String passionCategory;
	
	private List<PassionDTO> passions;
}
