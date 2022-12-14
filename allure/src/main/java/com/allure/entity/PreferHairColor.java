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
 * PreferHairColor generated by hbm2java
 */
@Entity
@Table(name = "prefer_hair_color", catalog = "db_allure")
@Getter
@Setter
public class PreferHairColor extends BaseEntity implements java.io.Serializable {

	@Column(name = "user_id", nullable = false)
	private int userId;

	@Column(name = "hair_color_id", nullable = false)
	private int hairColorId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "hair_color_id", nullable = false, insertable = false, updatable = false)
	private ListHairColor listHairColor;

}
