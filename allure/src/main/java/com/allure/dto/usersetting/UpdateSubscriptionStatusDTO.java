package com.allure.dto.usersetting;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateSubscriptionStatusDTO {
	
	@JsonProperty("subscription_status")
	private Byte subscriptionStatus; 
}
