package com.allure.dto.usersetting;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetUserSettingDTO {

	@JsonProperty("user_id")
	private int userId;

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

	@JsonProperty("showMeValue")
	private String showMeValue;

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

	@JsonProperty("prefer_clean_months")
	private Integer preferCleanMonths;

	private Date createdAt;
	
	private String phone;
}
