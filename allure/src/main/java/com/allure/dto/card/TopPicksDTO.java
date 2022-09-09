package com.allure.dto.card;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TopPicksDTO {

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

	@JsonProperty("aboutMe")
	private String aboutMe;

	@JsonProperty("show_sexual_orientations")
	private String showSexualOrientations;

	@JsonProperty("interest")
	private String interest;

	@JsonProperty("is_profile_completed")
	private String isProfileCompleted;

	@JsonProperty("distance")
	private Double distance;

	@JsonProperty("total_likes")
	private int totalLikes;

	@JsonProperty("dpUrls")
	private String[] dpUrls;

	@JsonProperty("passions")
	private List<CardsPassionsDTO> userPassions;

}
