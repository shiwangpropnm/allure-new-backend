package com.allure.dto.card;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetLikesDTO {

	@JsonProperty("like_status")
	private String likeStatus;
}
