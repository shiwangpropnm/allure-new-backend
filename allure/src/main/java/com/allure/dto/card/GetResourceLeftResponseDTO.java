package com.allure.dto.card;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetResourceLeftResponseDTO {

	private ResourceLeftDTO resourceLeftDTO;

	public GetResourceLeftResponseDTO(int swipesLeft, int superSwipeLeft) {
		resourceLeftDTO = new ResourceLeftDTO(swipesLeft, superSwipeLeft);
	}

	@AllArgsConstructor
	class ResourceLeftDTO {

		@JsonProperty("swipes_left")
		private int swipesLeft;

		@JsonProperty("superSwipe_left")
		private int superSwipeLeft;
	}
}
