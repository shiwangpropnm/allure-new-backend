package com.allure.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LikeStatus {

	SUPERLIKE("2"),
	LIKE("1"),
	DISLIKE("-1");
	
	private String value;

	public static LikeStatus getLikeStatus(String value) {
        for (LikeStatus likeStatus : LikeStatus.values()) {
        	if(likeStatus.getValue().equals(value))
        		return likeStatus;
        }
        return null;
    }
}
