package com.allure.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class UserImagesId implements Serializable {

	private Integer userId;
	
	private Integer value;
}
