package com.allure.service;

import java.util.List;

import com.allure.dto.LoggedInUserDTO;
import com.allure.dto.card.GetUserMatchesResponseDTO;
import com.allure.entity.Matches;
import com.allure.entity.ProfileLikeDislike;
import com.allure.entity.Users;


public interface MatchService {

	List<Users> getMatchedProfilesByUserId(int loggedInUserId);

	GetUserMatchesResponseDTO getMatchesOfUser(LoggedInUserDTO loggedInUserDTO);

	Matches postUserMatch(Users user, Users profile);

	void deleteUserMatch(Users user, Users profile);

	List<Matches> postMutualUserMatch(Users user1, Users user2);

	void deleteMutualUserMatch(Users user1, Users user2);

	void updateUserMatchesForProfileLikeDislike(ProfileLikeDislike profileLikeDislike);

}
