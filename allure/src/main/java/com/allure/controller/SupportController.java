package com.allure.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.allure.constants.AllureResponseStatus;
import com.allure.dto.AllureResponseDTO;
import com.allure.dto.LoggedInUserDTO;
import com.allure.dto.support.AskGeneralQuestionDTO;
import com.allure.service.SupportService;

@RestController
@RequestMapping("/v1/support")
public class SupportController {

	@Autowired
	MessageSource messageSource;

	@Autowired
	SupportService supportService;

	@PostMapping(value = "/askGeneralQuestion", consumes = { "multipart/form-data" })
	public ResponseEntity<AllureResponseDTO<Object>> askGeneralQuestion(
			@ModelAttribute AskGeneralQuestionDTO askGeneralQuestionDTO, LoggedInUserDTO loggedInUserDTO) {

		supportService.postAskGeneralQuestion(loggedInUserDTO, askGeneralQuestionDTO);

		return new ResponseEntity<>(
				new AllureResponseDTO<Object>(null, AllureResponseStatus.SUCCESS.getCode(),
						messageSource.getMessage("post.ask.general.question.success", null, Locale.ENGLISH)),
				HttpStatus.OK);

	}
}
