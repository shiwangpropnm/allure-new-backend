package com.allure.dto.block;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BlockedMobileResponseDTO {
	
	@JsonProperty("blocked_mobiles")
	List<String> blockedMobiles;
}
