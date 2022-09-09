package com.allure.service;

import com.allure.dto.LoggedInUserDTO;
import com.allure.dto.profile.EditUserProfileFormDataDTO;
import com.allure.dto.profile.GetProfileResponseDTO;
import com.allure.dto.profile.UpdateUserProfileFormDataDTO;

public interface ProfileService {

	GetProfileResponseDTO getUserProfile(LoggedInUserDTO loggedInUserDTO);

	void updateUserProfile(LoggedInUserDTO loggedInUserDTO, UpdateUserProfileFormDataDTO updateUserProfileFormDataDTO);

	void editUserProfile(LoggedInUserDTO loggedInUserDTO, EditUserProfileFormDataDTO editUserProfileFormDataDTO);
	
	
}
