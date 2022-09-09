package com.allure.dto.feed;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetFeedsDTO {

	@JsonProperty("profile_id")
	private Integer profileId;

	@JsonProperty("full_name")
	private String fullName;

	private Integer distance;

	@JsonProperty("clean_date")
	private Date cleanDate;

	@JsonProperty("loc_lattitude")
	private Double locLattitude;

	@JsonProperty("loc_longitude")
	private Double locLongitude;
	
	@JsonProperty("gender_id")
	private Integer genderId;
	
	private String gender;
	
	private String interest;
	
	private Byte feedType;
	
	private String value;
	
	private Date createdAt;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String dpUrl;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String[] dpUrls;

	private Integer distanceUnit;
	
	private String location;
	
}
