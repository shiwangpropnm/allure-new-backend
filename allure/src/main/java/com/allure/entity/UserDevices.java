package com.allure.entity;
// Generated 23 Oct, 2021 7:48:14 AM by Hibernate Tools 5.2.10.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user_devices", catalog = "db_allure")
@Getter
@Setter
public class UserDevices extends AuditableBaseEntity implements java.io.Serializable {

	private static final long serialVersionUID = -159539896639762350L;

	@Column(name = "user_id", nullable = false)
	private int userId;

	@Column(name = "type", nullable = false)
	private String type;

	@Column(name = "reg_id")
	private String regId;
}
