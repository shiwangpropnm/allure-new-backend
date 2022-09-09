package com.allure.dto.plan;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetUserPlanDTO {

	private int userPlanPurchasedId;
	
	private String planName;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date transactionOn;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date validtill;
	
	private int validity;
	
	private double price;

	private String type;

	private boolean messageBeforeMatch;

	private boolean prioritizedLikes;

	private int seePreviousLikesDays;

	private int undoCount;

	private boolean seeLikedBy;

	private boolean newTopPicks;

	private int swipesCount;

	private int superswipesCount;

	private int boostCount;

	private boolean passportLocation;

	private int noAdsAfterSwipes;
	
}
