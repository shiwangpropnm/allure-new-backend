package com.allure.dto.notification;

import java.util.Map;

import com.allure.constants.NotificationDeviceType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificationDTO {

	private String title;
	
	private String body;
	
	private String imageUrl;
	
	private Map<String, String> data;
	
	private NotificationDeviceType notificationDeviceType;
}
