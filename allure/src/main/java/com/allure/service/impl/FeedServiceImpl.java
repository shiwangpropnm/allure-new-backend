package com.allure.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.allure.constants.DistanceUnit;
import com.allure.constants.FeedType;
import com.allure.dao.FeedListRepository;
import com.allure.dto.LoggedInUserDTO;
import com.allure.dto.feed.GetFeedsDTO;
import com.allure.dto.feed.GetFeedsResponseDTO;
import com.allure.entity.FeedList;
import com.allure.entity.Users;
import com.allure.mapper.FeedListMapper;
import com.allure.mapper.UserMapper;
import com.allure.service.FeedService;
import com.allure.service.UserService;

@Service
public class FeedServiceImpl implements FeedService {

	@Autowired
	FeedListRepository feedListRepository;

	@Autowired
	UserService userService;

	@Autowired
	UserMapper userMapper;

	@Autowired
	FeedListMapper feedListMapper;

	@Override
	public GetFeedsResponseDTO getUserFeeds(LoggedInUserDTO loggedInUserDTO) {
		Users loggedInUser = userService.getUserByUserId(loggedInUserDTO.getLoggedInUserId());
		List<FeedList> userFeeds = feedListRepository.findAllByUsersByUserIdOrderByCreatedAtDesc(loggedInUser);

		DistanceUnit distanceUnit = userService.getUserSettingDistanceUnit(loggedInUser);

		return new GetFeedsResponseDTO(userFeeds.stream().map(feed -> {
			Users profile = feed.getUsersByProfileId();
			Double distance = userService.getDistanceBetweenUsers(loggedInUser, profile, distanceUnit);
			String dpUrl = null;
			String[] dpUrls = null;
			switch (FeedType.valueOf(feed.getFeedType())) {
			case IMAGE_UPLOAD:
				dpUrl = userService.getUserDpUrl(profile.getId(), Integer.parseInt(feed.getValue()));
				break;
			case MATCH:
				dpUrls = userService.getDpUrls(profile.getId());
				break;
			}

			GetFeedsDTO getFeedsDTO = feedListMapper.feedAndUserToGetFeedsDTO(feed, profile, distanceUnit.getUnit(),
					distance, dpUrl, dpUrls);
			return getFeedsDTO;
		}).collect(Collectors.toList()));
	}

	@Override
	public void postFeed(Users usersByUserId, Users usersByProfileId, byte type, String value) {
		FeedList feedList = new FeedList();
		feedList.setUsersByUserId(usersByUserId);
		feedList.setUsersByProfileId(usersByProfileId);
		feedList.setFeedType(type);
		feedList.setValue(value);
		feedList.setCreatedAt(new Date());
		feedListRepository.save(feedList);
	}

}
