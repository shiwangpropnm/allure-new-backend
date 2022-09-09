package com.allure.dto.usersetting;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateDiscoverySettingDTO {
	
	private List<Integer> addiction;

	@JsonProperty("body_type")
	private List<Integer> bodyType;

	@JsonProperty("hair_color")
	private List<Integer> hairColor;
	
	@JsonProperty("gender")
	private List<Integer> gender;
	
	@JsonProperty("clean_month")
	private Integer cleanMonth;

	@JsonProperty("clean_year")
	private Integer cleanYear;
}
