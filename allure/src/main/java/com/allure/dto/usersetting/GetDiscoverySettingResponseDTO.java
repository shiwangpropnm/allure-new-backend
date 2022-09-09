package com.allure.dto.usersetting;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetDiscoverySettingResponseDTO {

	@JsonProperty("discovery_Setting")
	private GetDiscoverySettingDTO discoverySettingDTO;
}
