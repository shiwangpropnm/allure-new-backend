package com.allure.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.allure.service.FCMService;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;

@Service
public class FCMServiceImpl implements FCMService {

	@Autowired
	private FirebaseMessaging firebaseMessaging;
	
	@Override
	public String sendNotification(String title, String body, String imageUrl, String token, Map<String,String> data) throws FirebaseMessagingException {
		Notification notification = Notification
                .builder()
                .setTitle(title)
                .setBody(body)
                .setImage(imageUrl)
                .build();

        Message message = Message
                .builder()
                .setToken(token)
                .setNotification(notification)
                .putAllData(data)
                .build();
        
        return firebaseMessaging.send(message);
	}
	
}
