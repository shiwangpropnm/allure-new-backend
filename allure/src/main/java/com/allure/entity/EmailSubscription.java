package com.allure.entity;
// Generated 18 Sep, 2021 10:44:47 AM by Hibernate Tools 5.2.10.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * EmailSubscription generated by hbm2java
 */
@Entity
@Table(name = "email_subscription", catalog = "db_allure")
@Getter
@Setter
public class EmailSubscription extends BaseEntity implements java.io.Serializable {

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private Users users;

	@Column(name = "new_matches")
	private Byte newMatches;
	
	@Column(name = "new_messages")
	private Byte newMessages;

	@Column(name = "promotions")
	private Byte promotions;

}
