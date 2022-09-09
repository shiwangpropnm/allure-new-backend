package com.allure.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.allure.constants.AllureResponseStatus;
import com.allure.dto.AllureResponseDTO;
import com.allure.dto.LoggedInUserDTO;
import com.allure.service.AccountStatusService;

@RestController
@RequestMapping("v1/block")
public class AccountStatusController {

	@Autowired
	AccountStatusService accountStatusService;

	@Autowired
	MessageSource messageSource;

	@GetMapping("/deleteAccount")
	public ResponseEntity<AllureResponseDTO<Object>> deleteAccount(LoggedInUserDTO loggedInUserDTO) {
		accountStatusService.deleteAccount(loggedInUserDTO);
		return new ResponseEntity<AllureResponseDTO<Object>>(
				new AllureResponseDTO<Object>(null, AllureResponseStatus.SUCCESS.getCode(),
						messageSource.getMessage("delete.account.success", null, Locale.ENGLISH)),
				HttpStatus.OK);
	}

	@GetMapping("/suspendAccount")
	public ResponseEntity<AllureResponseDTO<Object>> suspendAccount(LoggedInUserDTO loggedInUserDTO) {
		
		accountStatusService.suspendAccount(loggedInUserDTO);
		return new ResponseEntity<AllureResponseDTO<Object>>(
				new AllureResponseDTO<Object>(null, AllureResponseStatus.SUCCESS.getCode(),
						messageSource.getMessage("delete.account.success", null, Locale.ENGLISH)),
				HttpStatus.OK);
	}

}
