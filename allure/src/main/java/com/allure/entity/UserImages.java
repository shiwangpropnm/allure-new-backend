package com.allure.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "user_images")
@IdClass(UserImagesId.class)
public class UserImages {

	@Id
	@Column(name = "user_id")
	private Integer userId;

	@Id
	@Column(name = "value")
	private Integer value;

	@Column(name = "object_key", length = 45)
	private String objectKey;

	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", length = 19, nullable = false, updatable = false)
	private Date createdAt;
}
