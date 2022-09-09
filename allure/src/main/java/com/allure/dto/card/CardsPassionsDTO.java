package com.allure.dto.card;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CardsPassionsDTO {

	@JsonProperty("passion_id")
	private int passionId;

	@JsonProperty("passion_name")
	private String passionName;
}
