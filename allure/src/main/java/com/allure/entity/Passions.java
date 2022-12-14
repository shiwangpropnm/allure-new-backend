package com.allure.entity;
// Generated 18 Sep, 2021 10:44:47 AM by Hibernate Tools 5.2.10.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * Passions generated by hbm2java
 */
@Entity
@Table(name = "passions", catalog = "db_allure")
@Getter
@Setter
public class Passions extends BaseEntity implements java.io.Serializable {

	@Column(name = "passion_name", nullable = false)
	private String passionName;

	@Column(name = "is_deleted", nullable = false)
	private boolean isDeleted;

	@Column(name = "icon_url")
	private String iconUrl;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="category_id")
	private PassionCategories passionCategories;
}
