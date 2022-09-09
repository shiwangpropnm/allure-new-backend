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
import com.allure.dto.feed.GetFeedsResponseDTO;
import com.allure.service.CommonService;
import com.allure.service.FeedService;

@RestController
@RequestMapping("/v1/feed")
public class FeedController {

	@Autowired
	FeedService feedService;

	@Autowired
	CommonService commonService;

	@Autowired
	MessageSource messageSource;

	@GetMapping(value = "/getFeeds")
	public ResponseEntity<AllureResponseDTO<GetFeedsResponseDTO>> getFeeds(LoggedInUserDTO loggedInUserDTO) {

		GetFeedsResponseDTO getFeedsResponseDTO = feedService.getUserFeeds(loggedInUserDTO);

		return new ResponseEntity<>(new AllureResponseDTO<>(getFeedsResponseDTO,
				AllureResponseStatus.SUCCESS.getCode(),
				messageSource.getMessage("user.feeds.get.success", null, Locale.ENGLISH)), HttpStatus.OK);
	}

}
