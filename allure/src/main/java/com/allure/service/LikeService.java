package com.allure.service;

import com.allure.dto.LoggedInUserDTO;
import com.allure.dto.card.GetLikesDTO;
import com.allure.dto.card.GetLikesResponseDTO;
import com.allure.dto.card.GetResourceLeftResponseDTO;
import com.allure.dto.card.LikeByUserResponseDTO;
import com.allure.dto.card.PostCardLikeDTO;
import com.allure.dto.card.UserLikeReceivedResponseDTO;
import com.allure.entity.ProfileLikeDislike;
import com.allure.entity.Users;

public interface LikeService {

	void postProfileLike(LoggedInUserDTO loggedInUserDTO, PostCardLikeDTO postCardLikeDTO);

	LikeByUserResponseDTO getLikesByUser(LoggedInUserDTO loggedInUserDTO, GetLikesDTO getLikesDTO);

	UserLikeReceivedResponseDTO getUserLikes(LoggedInUserDTO loggedInUserDTO, GetLikesDTO getLikesDTO);

	GetResourceLeftResponseDTO getResourceLeft(LoggedInUserDTO loggedInUserDTO);

	GetLikesResponseDTO getGetLikesResponseDTOFromUser(Users user);

	ProfileLikeDislike getProfileLikeDislike(Users usersByUserId, Users usersByProfileId);

}
