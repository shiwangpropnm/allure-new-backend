package com.allure.dto.plan;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetPlansDTO {

	private Integer id;

	private String name;

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

	private List<GetPlanPaymentStructuresDTO> paymentStructures;

}
