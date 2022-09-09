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
@Table(name = "plan_payment_structures", catalog = "db_allure")
@Getter
@Setter
public class PlanPaymentStructures extends BaseEntity implements java.io.Serializable {

	private static final long serialVersionUID = 757318859449016130L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "plan_id", nullable = false)
	private Plans plans;
	
	@Column(name = "validity", nullable = false)
	private int validity;
	
	@Column(name = "price", nullable = false, precision = 22, scale = 0)
	private double price;
	
	@Column(name = "is_popular", nullable = false)
	private boolean isPopular;
	
	@Column(name = "is_deleted", nullable = false)
	private boolean isDeleted;
	
}
