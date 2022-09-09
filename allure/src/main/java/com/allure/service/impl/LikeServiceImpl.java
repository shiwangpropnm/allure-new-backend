package com.allure.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.allure.dao.ProfileLikeDislikeRepository;
import com.allure.dto.LoggedInUserDTO;
import com.allure.dto.card.GetLikesDTO;
import com.allure.dto.card.GetLikesResponseDTO;
import com.allure.dto.card.GetResourceLeftResponseDTO;
import com.allure.dto.card.LikeByUserResponseDTO;
import com.allure.dto.card.PostCardLikeDTO;
import com.allure.dto.card.UserLikeReceivedResponseDTO;
import com.allure.entity.ProfileLikeDislike;
import com.allure.entity.Users;
import com.allure.exception.InvalidDetailsException;
import com.allure.mapper.UserMapper;
import com.allure.service.LikeService;
import com.allure.service.MatchService;
import com.allure.service.NotificationService;
import com.allure.service.UserService;

@Service
public class LikeServiceImpl implements LikeService {

	@Autowired
	private ProfileLikeDislikeRepository profileLikeDislikeRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private MatchService matchService;
	
	@Autowired
	private NotificationService notificationService;

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private UserMapper userMapper;

	@Override
	public void postProfileLike(LoggedInUserDTO loggedInUserDTO, PostCardLikeDTO postCardLikeDTO) {
		Users loggedInUser = userService.getUserByUserId(loggedInUserDTO.getLoggedInUserId())
;		Users profileLikedUser = userService.getUserByUserId(postCardLikeDTO.getProfileId());
		if (profileLikedUser == null)
			throw new InvalidDetailsException(messageSource.getMessage("exception.invalid.profile.details",
					new Object[] { postCardLikeDTO.getProfileId() }, Locale.ENGLISH));

		ProfileLikeDislike profileLikeDislike = getProfileLikeDislike(loggedInUser, profileLikedUser);
		if (profileLikeDislike == null) {
			profileLikeDislike = new ProfileLikeDislike();
			profileLikeDislike.setUsersByUserId(loggedInUser);
			profileLikeDislike.setUsersByProfileId(profileLikedUser);
			profileLikeDislike.setCreatedAt(new Date());
		}
		profileLikeDislike.setLikeStatus(postCardLikeDTO.getLikeStatus());
		profileLikeDislike.setUpdatedAt(new Date());
		profileLikeDislikeRepository.save(profileLikeDislike);
		matchService.updateUserMatchesForProfileLikeDislike(profileLikeDislike);
		notificationService.sendProfileLikeNotification(profileLikeDislike);
	}

	@Override
	public LikeByUserResponseDTO getLikesByUser(LoggedInUserDTO loggedInUserDTO, GetLikesDTO getLikesDTO) {
		List<Users> userLikes = profileLikeDislikeRepository
				.findUsersProfileSwipedByUser(loggedInUserDTO.getLoggedInUserId(), getLikesDTO.getLikeStatus());
		List<GetLikesResponseDTO> getLikesResponseDTOs = userLikes.stream().map(user -> {
			return getGetLikesResponseDTOFromUser(user);
		}).collect(Collectors.toList());
		return new LikeByUserResponseDTO(getLikesResponseDTOs);
	}

	@Override
	public UserLikeReceivedResponseDTO getUserLikes(LoggedInUserDTO loggedInUserDTO, GetLikesDTO getLikesDTO) {
		List<Users> userLikesRecieved = profileLikeDislikeRepository
				.findUsersProfilesWhoSwipedUser(loggedInUserDTO.getLoggedInUserId(), getLikesDTO.getLikeStatus());
		List<GetLikesResponseDTO> getLikesResponseDTOs = userLikesRecieved.stream().map(user -> {
			return getGetLikesResponseDTOFromUser(user);
		}).collect(Collectors.toList());
		return new UserLikeReceivedResponseDTO(getLikesResponseDTOs);
	}

	@Override
	public GetLikesResponseDTO getGetLikesResponseDTOFromUser(Users user) {
		GetLikesResponseDTO getLikesResponseDTO = userMapper.userToGetLikeResponseDTO(user);
		getLikesResponseDTO.setDpUrls(userService.getDpUrls(user.getId()));
		return getLikesResponseDTO;
	}

	@Override
	public GetResourceLeftResponseDTO getResourceLeft(LoggedInUserDTO loggedInUserDTO) {
		// changes to be done
		return new GetResourceLeftResponseDTO(9, 1);
	}

	@Override
	public ProfileLikeDislike getProfileLikeDislike(Users usersByUserId, Users usersByProfileId) {
		return profileLikeDislikeRepository
		.findByUsersByUserIdAndUsersByProfileId(usersByUserId, usersByProfileId);
	}

}
