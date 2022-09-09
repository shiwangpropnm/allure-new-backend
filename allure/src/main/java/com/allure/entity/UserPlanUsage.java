package com.allure.entity;
// Generated 23 Oct, 2021 7:48:14 AM by Hibernate Tools 5.2.10.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user_plan_usage", catalog = "db_allure")
@Getter
@Setter
public class UserPlanUsage extends BaseEntity implements java.io.Serializable {


	private static final long serialVersionUID = -4659811810799682301L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "plan_id", nullable = false)
	private Plans plans;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private Users users;

	@Column(name = "undo_done", nullable = false)
	private int undoDone;

	@Column(name = "swipes_done", nullable = false)
	private int swipesDone;

	@Column(name = "superswipes_done", nullable = false)
	private int superswipesDone;

	@Column(name = "boost_done", nullable = false)
	private int boostDone;

}
