package com.allure.dto.usersetting;

import java.util.List;

import com.allure.dto.common.IdValueSelectionDTO;
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
public class GetDiscoverySettingDTO {

	private List<IdValueSelectionDTO> addiction;

	@JsonProperty("body_type")
	private List<IdValueSelectionDTO> bodyType;

	@JsonProperty("hair_color")
	private List<IdValueSelectionDTO> hairColor;

	@JsonProperty("gender")
	private List<IdValueSelectionDTO> gender;

	@JsonProperty("clean_month")
	private Integer cleanMonth;

	@JsonProperty("clean_year")
	private Integer cleanYear;
}
