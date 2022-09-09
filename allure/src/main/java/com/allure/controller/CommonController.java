package com.allure.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.allure.constants.AllureResponseStatus;
import com.allure.dto.AllureResponseDTO;
import com.allure.dto.common.GetPassionsResponseDTO;
import com.allure.dto.common.GetSexualOrientationsResponseDTO;
import com.allure.service.CommonService;

@RestController
@RequestMapping("/v1/common")
public class CommonController {

	@Autowired
	CommonService commonService;

	@Autowired
	MessageSource messageSource;

	@GetMapping(value = "/getSexualOrientations", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AllureResponseDTO<GetSexualOrientationsResponseDTO>> getSexualOrientations() {
		GetSexualOrientationsResponseDTO sexualOrientations = commonService.getSexualOrientations();
		return new ResponseEntity<AllureResponseDTO<GetSexualOrientationsResponseDTO>>(
				new AllureResponseDTO<GetSexualOrientationsResponseDTO>(sexualOrientations,
						AllureResponseStatus.SUCCESS.getCode(),
						messageSource.getMessage("get.sexualorientation.success", null, Locale.ENGLISH)),
				HttpStatus.OK);
	}

	@GetMapping(value = "/getPassions", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AllureResponseDTO<GetPassionsResponseDTO>> getPassions() {
		GetPassionsResponseDTO passions = commonService.getPassions();
		return new ResponseEntity<AllureResponseDTO<GetPassionsResponseDTO>>(
				new AllureResponseDTO<GetPassionsResponseDTO>(passions, AllureResponseStatus.SUCCESS.getCode(),
						messageSource.getMessage("get.passion.success", null, Locale.ENGLISH)),
				HttpStatus.OK);
	}

}
