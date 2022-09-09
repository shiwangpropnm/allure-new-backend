package com.allure.entity;
// Generated 18 Sep, 2021 10:44:47 AM by Hibernate Tools 5.2.10.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Getter;
import lombok.Setter;

/**
 * Blocklist generated by hbm2java
 */
@Entity
@Table(name = "blocklist", catalog = "db_allure", uniqueConstraints = @UniqueConstraint(columnNames = { "user_id",
		"blocked_mobile" }))
@Getter
@Setter
public class Blocklist extends AuditableBaseEntity implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private Users user;

	@Column(name = "blocked_mobile", nullable = false, length = 15)
	private String blockedMobile;
	
}
