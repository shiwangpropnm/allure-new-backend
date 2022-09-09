package com.allure.dto.usersetting;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetEmailSubscriptionDTO {

	private String email;

	@JsonProperty("new_matches")
	private int newMatches;

	@JsonProperty("new_messages")
	private int newMessages;
	
	private int promotions;
}
