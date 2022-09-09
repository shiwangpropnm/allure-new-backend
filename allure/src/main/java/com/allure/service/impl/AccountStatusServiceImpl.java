package com.allure.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.allure.dao.UserRepository;
import com.allure.dto.LoggedInUserDTO;
import com.allure.entity.Users;
import com.allure.mapper.UserMapper;
import com.allure.service.AccountStatusService;
import com.allure.service.UserService;
import com.allure.util.CommonUtils;

@Service
public class AccountStatusServiceImpl implements AccountStatusService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserService userService;

	@Autowired
	MessageSource messageSource;

	@Autowired
	UserMapper userMapper;

	@Value("${allure.suspend.account.days}")
	private int suspendedTillDays;

	@Override
	public void deleteAccount(LoggedInUserDTO loggedInUserDTO) {
		Users user = userService.getUserByUserId(loggedInUserDTO.getLoggedInUserId());
		user.setAccStatus(false);
		user.setUpdatedAt(new Date());
		user.setUpdatedBy(loggedInUserDTO.getLoggedInUserId());
		userRepository.save(user);
	}

	@Override
	public void suspendAccount(LoggedInUserDTO loggedInUserDTO) {
		Users user = userService.getUserByUserId(loggedInUserDTO.getLoggedInUserId());
		user.setAccStatus(false);
		user.setInactiveTill(CommonUtils.getSuspendedTillDate(new Date(), suspendedTillDays));
		user.setUpdatedAt(new Date());
		user.setUpdatedBy(loggedInUserDTO.getLoggedInUserId());
		userRepository.save(user);
	}

}
