package com.allure.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.allure.constants.LikeStatus;
import com.allure.constants.NotificationType;
import com.allure.dao.PushNotificationsLogRepository;
import com.allure.dao.UserNotificationsRepository;
import com.allure.entity.ProfileLikeDislike;
import com.allure.entity.PushNotificationsLog;
import com.allure.entity.UserDevices;
import com.allure.entity.UserNotifications;
import com.allure.entity.Users;
import com.allure.service.FCMService;
import com.allure.service.NotificationService;
import com.allure.service.UserService;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.MessagingErrorCode;

@Service
public class NotificationServiceImpl implements NotificationService {

	@Autowired
	FCMService fcmService;

	@Autowired
	UserService userService;

	@Autowired
	UserNotificationsRepository userNotificationsRepository;

	@Autowired
	PushNotificationsLogRepository pushNotificationsLogRepository;

	@Autowired
	MessageSource messageSource;

	@Async
	@Override
	public void sendProfileLikeNotification(ProfileLikeDislike profileLikeDislike) {
		if (profileLikeDislike != null) {
			LikeStatus likeStatus = LikeStatus.getLikeStatus(profileLikeDislike.getLikeStatus());
			if (likeStatus == LikeStatus.LIKE || likeStatus == LikeStatus.SUPERLIKE) {
				Users likedByUser = profileLikeDislike.getUsersByUserId();
				Users likedUser = profileLikeDislike.getUsersByProfileId();

				Integer likedByUserId = likedByUser.getId();
				List<UserDevices> userDevices = userService.getUserDevices(likedByUserId);
				String likedByUserName = StringUtils.isNotEmpty(likedByUser.getFullName()) ? likedByUser.getFullName()
						: "Someone";
				String likedUserName = StringUtils.isNotEmpty(likedUser.getFullName()) ? likedUser.getFullName()
						: "Someone";

				String title = messageSource.getMessage("notification.title.liked.user",
						new Object[] { likedByUserName }, Locale.ENGLISH);
				String body = messageSource.getMessage("notification.body.liked.user", new Object[] { likedByUserName, likedUserName },
						Locale.ENGLISH);
				
				String imageUrl = userService.getUserDpUrlForNotification(likedByUserId);

				UserNotifications userNotifications = new UserNotifications();
				userNotifications.setTitle(title);
				userNotifications.setBody(body);
				userNotifications.setImageUrl(imageUrl);
				userNotifications.setUserId(likedByUserId);
				userNotifications.setType(NotificationType.LIKEDBYUSER.getType());
				userNotifications.setTypeId(profileLikeDislike.getId());
				userNotifications.setFcm(true);
				userNotifications.setCreatedAt(new Date());
				userNotificationsRepository.save(userNotifications);
				Map<String, String> data = new HashMap<>();
				data.put("profileLikeDislikeId", String.valueOf(profileLikeDislike.getId()));
				userDevices.forEach(userDevice -> {

					String response = "";
					boolean isPushed = false;
					boolean isUserDeviceDeleted = false;
					try {
						response = fcmService.sendNotification(title, body, imageUrl, userDevice.getRegId(), data);
						isPushed = true;
					} catch (FirebaseMessagingException e) {
						response = e.getMessagingErrorCode().name();
//						isUserDeviceDeleted = deleteUserDevice(userDevice, e.getMessagingErrorCode());
					}

					if(!isUserDeviceDeleted) {
						PushNotificationsLog pushNotificationsLog = new PushNotificationsLog();
						pushNotificationsLog.setPushResponse(response);
						pushNotificationsLog.setUserDevicesId(userDevice.getId());
						pushNotificationsLog.setUserId(likedByUserId);
						pushNotificationsLog.setUserNotificationsId(userNotifications.getId());
						pushNotificationsLog.setPushed(isPushed);
						pushNotificationsLog.setCreatedAt(new Date());
						pushNotificationsLogRepository.save(pushNotificationsLog);
					}

				});
			}
		}
	}

	private boolean deleteUserDevice(UserDevices userDevice, MessagingErrorCode messagingErrorCode) {
		switch(messagingErrorCode) {
		case UNREGISTERED:
			userService.deleteUserDevice(userDevice);
			return true;
		default:
			return false;
		}
	}

}
