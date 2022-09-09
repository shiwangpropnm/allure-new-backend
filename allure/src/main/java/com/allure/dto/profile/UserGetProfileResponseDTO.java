package com.allure.dto.profile;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserGetProfileResponseDTO {
	
	private Integer id;
	
	@JsonProperty("full_name")
	private String fullName;
	
	private String email;

	private String mobile;

	@JsonProperty("facebook_id")
	private String facebookId;
	
	@JsonProperty("google_id")
	private String googleId;
	
	@JsonProperty("login_type")
	private int loginType;

	private Date dob;

	@JsonProperty("gender_id")
	private Integer genderId;

	private String gender;
	
	@JsonProperty("show_gender")
	private String showGender;

	@JsonProperty("clean_date")
	private Date cleanDate;
	
	@JsonProperty("living_in")
	private String livingIn;

	@JsonProperty("job_title")
	private String jobTitle;

	@JsonProperty("company_name")
	private String companyName;

	@JsonProperty("height")
	private BigDecimal height;

	@JsonProperty("aboutMe")
	private String aboutMe;

	@JsonProperty("body_type_id")
	private Integer bodyTypeId;
	
	@JsonProperty("body_type")
	private String bodyType;
	
	@JsonProperty("eye_color_id")
	private Integer eyeColorId;
	
	@JsonProperty("eye_color")
	private String eyeColor;
	
	@JsonProperty("hair_color_id")
	private Integer hairColorId;
	
	@JsonProperty("hair_color")
	private String hairColor;
	
	@JsonProperty("smoking_id")
	private Integer smokingId;
	
	@JsonProperty("smoking")
	private String smoking;

	@JsonProperty("drinking_id")
	private Integer drinkingId;
	
	@JsonProperty("drinking")
	private String drinking;

	@JsonProperty("ethnicity_id")
	private Integer ethnicityId;
	
	@JsonProperty("ethnicity")
	private String ethnicity;
	
	@JsonProperty("religion_id")
	private Integer religionId;
	
	@JsonProperty("religion")
	private String religion;

	@JsonProperty("relationship_status_id")
	private Integer relationshipStatusId;
	
	@JsonProperty("relationship_status")
	private String relationshipStatus;
	
	@JsonProperty("show_sexual_orientations")
	private String showSexualOrientations;
	
	@JsonProperty("education_level_id")
	private Integer educationLevelId;
	
	@JsonProperty("education_level")
	private String educationLevel;
	
	@JsonProperty("university")
	private String university;

	@JsonProperty("interest")
	private String interest;
	
	@JsonProperty("is_profile_completed")
	private String isProfileCompleted;

	@JsonProperty("loc_lattitude")
	private String locLattitude;
	
	@JsonProperty("loc_longitude")
	private String locLongitude;

	@JsonProperty("sexual_orientation")
	private List<UserProfileSexualOrientationDTO> userSexualOrientations;
	
	@JsonProperty("passions")
	private List<UserProfilePassionsDTO> userPassions;
	
	@JsonProperty("dpUrls")
	private String[] dpUrls;
}
