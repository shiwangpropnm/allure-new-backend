package com.allure.dto.common;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PassionDTO {
	
	private int id;
	
	@JsonProperty("category_id")
	private int categoryId;

	@JsonProperty("passion_name")
	private String passionName;


	@JsonProperty("icon_url")
	private String iconUrl;
}
