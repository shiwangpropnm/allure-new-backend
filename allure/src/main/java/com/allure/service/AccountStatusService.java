package com.allure.service;

import com.allure.dto.LoggedInUserDTO;

public interface AccountStatusService {

	void deleteAccount(LoggedInUserDTO loggedInUserDTO);

	void suspendAccount(LoggedInUserDTO loggedInUserDTO);

}
