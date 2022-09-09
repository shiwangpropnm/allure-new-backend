package com.allure.service;

import com.allure.dto.LoggedInUserDTO;
import com.allure.dto.card.GetSwipingCardsResponseDTO;
import com.allure.dto.card.GetTopPicksResponseDTO;

public interface CardService {

	GetSwipingCardsResponseDTO getSwipingCards(LoggedInUserDTO loggedInUserDTO, Integer page, Integer pageSize);

	GetTopPicksResponseDTO getTopPicks(LoggedInUserDTO loggedInUserDTO, Integer page, Integer pageSize);

}
