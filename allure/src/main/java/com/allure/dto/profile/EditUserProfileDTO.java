package com.allure.dto.profile;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.allure.constants.DpUploadType;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EditUserProfileDTO {

	@JsonProperty("about_me")
	private String aboutMe;

	private Date dob;

	@JsonProperty("clean_date")
	private Date cleanDate;

	@JsonProperty("job_title")
	private String jobTitle;

	@JsonProperty("company_name")
	private String companyName;

	private String university;

	@JsonProperty("education_level")
	private Integer educationLevel;

	private Integer gender;

	@JsonProperty("sexual_orientation")
	private List<Integer> sexualOrientations;

	@JsonProperty("interest_passion")
	private List<Integer> passions;

	@JsonProperty("living_in")
	private String livingIn;

	@JsonProperty("height_cm")
	private BigDecimal height;

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
