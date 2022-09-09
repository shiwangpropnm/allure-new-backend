package com.allure.service;

import com.allure.dto.LoggedInUserDTO;
import com.allure.dto.support.AskGeneralQuestionDTO;

public interface SupportService {

	void postAskGeneralQuestion(LoggedInUserDTO loggedInUserDTO, AskGeneralQuestionDTO askGeneralQuestionDTO);

}
