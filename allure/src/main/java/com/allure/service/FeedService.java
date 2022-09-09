package com.allure.service;

import com.allure.dto.LoggedInUserDTO;
import com.allure.dto.feed.GetFeedsResponseDTO;
import com.allure.entity.Users;

public interface FeedService {

	public GetFeedsResponseDTO getUserFeeds(LoggedInUserDTO loggedInUserDTO);

	public void postFeed(Users usersByUserId, Users usersByProfileId, byte type, String value);
	
}
