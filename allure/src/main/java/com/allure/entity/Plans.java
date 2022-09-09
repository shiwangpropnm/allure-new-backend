package com.allure.entity;
// Generated 23 Oct, 2021 7:48:14 AM by Hibernate Tools 5.2.10.Final

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "plans", catalog = "db_allure")
@Getter
@Setter
public class Plans extends BaseEntity implements java.io.Serializable {

	private static final long serialVersionUID = -675827757909284338L;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "type", nullable = false)
	private String type;

	@Column(name = "message_before_match", nullable = false)
	private boolean messageBeforeMatch;

	@Column(name = "prioritized_likes", nullable = false)
	private boolean prioritizedLikes;
	
	@Column(name = "see_previous_likes_days", nullable = false)
	private int seePreviousLikesDays;
	
	@Column(name = "undo_count", nullable = false)
	private int undoCount;
	
	@Column(name = "see_liked_by", nullable = false)
	private boolean seeLikedBy;
	
	@Column(name = "new_top_picks", nullable = false)
	private boolean newTopPicks;
	
	@Column(name = "swipes_count", nullable = false)
	private int swipesCount;
	
	@Column(name = "superswipes_count", nullable = false)
	private int superswipesCount;
	
	@Column(name = "boost_count", nullable = false)
	private int boostCount;
	
	@Column(name = "passport_location", nullable = false)
	private boolean passportLocation;
	
	@Column(name = "no_ads_after_swipes", nullable = false)
	private int noAdsAfterSwipes;
	
	@Column(name = "is_deleted", nullable = false)
	private boolean isDeleted;

	@OneToMany(fetch=FetchType.LAZY, mappedBy="plans")
	@Where(clause = "is_deleted=false")
	private List<PlanPaymentStructures> planPaymentStructures;

}
