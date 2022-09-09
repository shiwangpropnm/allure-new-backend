package com.allure.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum NotificationDeviceType {
	
	ANDROID("android"),
	IOS("ios"),
	WEB("web");
	
	private String type;
}
