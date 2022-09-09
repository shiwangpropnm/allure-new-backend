package com.allure.entity;
// Generated 23 Oct, 2021 7:48:14 AM by Hibernate Tools 5.2.10.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "push_notifications_log", catalog = "db_allure")
@Getter
@Setter
public class PushNotificationsLog extends AuditableBaseEntity implements java.io.Serializable {

	private static final long serialVersionUID = -159539896639762350L;

	@Column(name = "user_id", nullable = false)
	private int userId;

	@Column(name = "user_notifications_id")
	private Integer userNotificationsId;

	@Column(name = "user_devices_id")
	private Integer userDevicesId;

	@Column(name = "is_pushed")
	private boolean isPushed;

	@Column(name = "push_response")
	private String pushResponse;
}
