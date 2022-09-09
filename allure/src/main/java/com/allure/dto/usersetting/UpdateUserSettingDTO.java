package com.allure.dto.usersetting;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserSettingDTO {

	@JsonProperty("topPicks")
	private Byte topPicks;

	@JsonProperty("showMeOnline")
	private Byte showMeOnline;

	@JsonProperty("readReceipt")
	private Byte readReceipt;

	@JsonProperty("activityStatus")
	private Byte activityStatus;

	@JsonProperty("showMe")
	private Byte showMe;

	@JsonProperty("hereTo")
	private Byte hereTo;

	@JsonProperty("minAge")
	private Byte minAge;

	@JsonProperty("maxAge")
	private Byte maxAge;

	@JsonProperty("distanceUnit")
	private Byte distanceUnit;

	@JsonProperty("maxDistance")
	private Integer maxDistance;
}
