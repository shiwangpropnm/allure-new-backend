package com.allure.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.allure.constants.DistanceUnit;
import com.allure.dto.LoggedInUserDTO;
import com.allure.dto.card.GetSwipingCardsResponseDTO;
import com.allure.dto.card.GetTopPicksResponseDTO;
import com.allure.dto.card.SwipingCardsDTO;
import com.allure.dto.card.TopPicksDTO;
import com.allure.entity.Users;
import com.allure.mapper.UserMapper;
import com.allure.service.CardService;
import com.allure.service.UserService;

@Service
public class CardServiceImpl implements CardService {

	@Autowired
	private UserService userService;

	@Autowired
	private UserMapper userMapper;

	@Override
	public GetSwipingCardsResponseDTO getSwipingCards(LoggedInUserDTO loggedInUserDTO, Integer page, Integer pageSize) {
		int loggedInUserId = loggedInUserDTO.getLoggedInUserId();
		Users loggedInUser = userService.getUserByUserId(loggedInUserId);


		List<Users> users = userService.getSwipingCardUsersForUser(loggedInUser, page, pageSize);

		DistanceUnit distanceUnit = userService.getUserSettingDistanceUnit(loggedInUser);

		List<SwipingCardsDTO> swipingCardsDTOs = users.stream().map(user -> {
			SwipingCardsDTO swipingCardsDTO = userMapper.userToSwipingCardDTO(user);
			swipingCardsDTO.setDistance(userService.getDistanceBetweenUsers(loggedInUser, user, distanceUnit));
			swipingCardsDTO.setDistanceUnit(distanceUnit.getUnit());
			swipingCardsDTO.setDpUrls(userService.getDpUrls(user.getId()));
			return swipingCardsDTO;
		}).collect(Collectors.toList());

		return new GetSwipingCardsResponseDTO(swipingCardsDTOs);
	}

	@Override
	public GetTopPicksResponseDTO getTopPicks(LoggedInUserDTO loggedInUserDTO, Integer page, Integer pageSize) {
		int loggedInUserId = loggedInUserDTO.getLoggedInUserId();
		Users loggedInUser = userService.getUserByUserId(loggedInUserId);

		List<Users> users = userService.getTopPicksUsersForUser(loggedInUser, page, pageSize);

		DistanceUnit distanceUnit = userService.getUserSettingDistanceUnit(loggedInUser);

		List<TopPicksDTO> topPicksDTOs = users.stream().map(user -> {
			TopPicksDTO topPicksDTO = userMapper.userToTopPicksDTO(user);
			topPicksDTO.setDistance(userService.getDistanceBetweenUsers(loggedInUser, user, distanceUnit));
			topPicksDTO.setDpUrls(userService.getDpUrls(user.getId()));
			return topPicksDTO;
		}).collect(Collectors.toList());

		return new GetTopPicksResponseDTO(topPicksDTOs);
	}

	
	
}
