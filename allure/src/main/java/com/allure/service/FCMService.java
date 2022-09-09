package com.allure.service;

import java.util.Map;

import com.google.firebase.messaging.FirebaseMessagingException;

public interface FCMService {

	String sendNotification(String title, String body, String imageUrl, String token, Map<String, String> data)
			throws FirebaseMessagingException;

}
