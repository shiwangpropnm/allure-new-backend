package com.allure.dto.profile;

import java.util.Date;
import java.util.List;

import com.allure.constants.DpUploadType;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserProfileDTO {

	private String fullName;

	private Date dob;

	private Integer gender;

	private Boolean showGender;

	private Date cleanDate;

	private Boolean showSexualOrientations;

	private String interest;

	private List<Integer> sexualOrientations;

	private List<Integer> passions;

	private List<Double> location;

	private String university;

	@JsonProperty("education_level")
	private Integer educationLevel;

	private Double height;

	@JsonProperty("body_type")
	private Integer bodyType;

	@JsonProperty("eye_color")
	private Integer eyeColor;

	@JsonProperty("hair_color")
	private Integer hairColor;

	@JsonProperty("relationship_status")
	private Integer relationshipStatus;

	private Integer smoking;

	private Integer drinking;

	private Integer ethnicity;

	private Integer religion;
	
	private DpUploadType dp1;

	private DpUploadType dp2;

	private DpUploadType dp3;

	private DpUploadType dp4;
	
	private DpUploadType dp5;

	private DpUploadType dp6;
}
