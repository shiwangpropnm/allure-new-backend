package com.allure.service;

import com.allure.entity.ProfileLikeDislike;

public interface NotificationService {

	void sendProfileLikeNotification(ProfileLikeDislike profileLikeDislike);

}
