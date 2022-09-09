package com.allure.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum NotificationType {
	
	LIKEDBYUSER("LIKED-BY-USER"),
	LIKEDUSER("LIKED-USER"),
	PROMOTION("PROMOTION");
	
	private String type;
}
