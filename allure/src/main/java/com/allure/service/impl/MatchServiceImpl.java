package com.allure.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.allure.constants.FeedType;
import com.allure.constants.LikeStatus;
import com.allure.dao.MatchesRepository;
import com.allure.dto.LoggedInUserDTO;
import com.allure.dto.card.GetUserMatchesResponseDTO;
import com.allure.dto.profile.UserGetProfileResponseDTO;
import com.allure.entity.Matches;
import com.allure.entity.ProfileLikeDislike;
import com.allure.entity.Users;
import com.allure.mapper.UserMapper;
import com.allure.service.FeedService;
import com.allure.service.LikeService;
import com.allure.service.MatchService;
import com.allure.service.UserService;

@Service
public class MatchServiceImpl implements MatchService {

	@Autowired
	MatchesRepository matchesRepository;

	@Autowired
	UserService userService;

	@Autowired
	FeedService feedService;

	@Autowired
	@Lazy
	LikeService likeService;

	@Autowired
	UserMapper userMapper;
	
	@Override
	public List<Users> getMatchedProfilesByUserId(int loggedInUserId) {
		
		return matchesRepository.findMatchedUserProfiles(loggedInUserId);
	}

	@Override
	public GetUserMatchesResponseDTO getMatchesOfUser(LoggedInUserDTO loggedInUserDTO) {
		List<Users> userMatches = getMatchedProfilesByUserId(loggedInUserDTO.getLoggedInUserId());
		List<UserGetProfileResponseDTO> userGetProfileResponseDTOs = userMatches.stream().map(user -> {
			UserGetProfileResponseDTO userGetProfileResponseDTO = userMapper.userToUserGetProfileResponseDTO(user);
			userGetProfileResponseDTO.setDpUrls(userService.getDpUrls(user.getId()));
			return userGetProfileResponseDTO;
		}).collect(Collectors.toList());

		return new GetUserMatchesResponseDTO(userGetProfileResponseDTOs);
	}

	@Override
	public List<Matches> postMutualUserMatch(Users user1, Users user2) {
		return Arrays.asList(postUserMatch(user1, user2), postUserMatch(user2, user1));
	}

	@Override
	public Matches postUserMatch(Users user, Users profile) {
		Matches matches = new Matches();
		matches.setUsersByUserId(user);
		matches.setUsersByProfileId(profile);
		matches.setCreatedAt(new Date());
		matchesRepository.save(matches);
		return matches; 
	}

	@Override
	public void deleteMutualUserMatch(Users user1, Users user2) {
		deleteUserMatch(user1, user2);
		deleteUserMatch(user2, user1);
	}

	@Override
	public void deleteUserMatch(Users user, Users profile) {
			
		Matches matches = matchesRepository.findByUsersByUserIdAndUsersByProfileId(user, profile);
		matchesRepository.delete(matches);
	}

	@Override
	@Async
	public void updateUserMatchesForProfileLikeDislike(ProfileLikeDislike userProfileLikeDislike) {
		Users usersByProfileId = userProfileLikeDislike.getUsersByProfileId();
		Users usersByUserId = userProfileLikeDislike.getUsersByUserId();
		switch(LikeStatus.valueOf(userProfileLikeDislike.getLikeStatus())) {
		case DISLIKE:
			deleteMutualUserMatch(usersByUserId, usersByProfileId);
			break;
		case LIKE:
		case SUPERLIKE:
			ProfileLikeDislike otherUserProfileLikeDislike = likeService.getProfileLikeDislike(usersByProfileId, usersByUserId);
			switch(LikeStatus.valueOf(otherUserProfileLikeDislike.getLikeStatus())) {
			case DISLIKE:
				break;
			case LIKE:
			case SUPERLIKE:
				postMutualUserMatch(usersByUserId, usersByProfileId);
				postMutualUserMatchFeed(usersByUserId, usersByProfileId);
				break;
			}
			break;
		}
	}

	private void postMutualUserMatchFeed(Users usersByUserId, Users usersByProfileId) {
		feedService.postFeed(usersByUserId, usersByProfileId, FeedType.MATCH.getType(), "1");
		feedService.postFeed( usersByProfileId, usersByUserId, FeedType.MATCH.getType(), "1");
	}
}
