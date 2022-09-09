package com.allure.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.allure.constants.AllureResponseStatus;
import com.allure.dto.AllureResponseDTO;
import com.allure.dto.LoggedInUserDTO;
import com.allure.dto.block.BlockedMobileResponseDTO;
import com.allure.dto.block.PostBlockUnblockByMobileDTO;
import com.allure.service.BlockService;

@RestController
@RequestMapping("v1/block")
public class BlockController {

	@Autowired
	BlockService blockService;

	@Autowired
	MessageSource messageSource;

	@PostMapping("/blockByMobile")
	public ResponseEntity<AllureResponseDTO<BlockedMobileResponseDTO>> blockByMobile(LoggedInUserDTO loggedInUserDTO,
			@RequestBody PostBlockUnblockByMobileDTO postBlockByMobileDTO) {

		BlockedMobileResponseDTO blockedMobileResponseDTO = blockService.postBlockByMobile(loggedInUserDTO,
				postBlockByMobileDTO);

		return new ResponseEntity<AllureResponseDTO<BlockedMobileResponseDTO>>(
				new AllureResponseDTO<BlockedMobileResponseDTO>(blockedMobileResponseDTO,
						AllureResponseStatus.SUCCESS.getCode(),
						messageSource.getMessage("get.blocked.mobile.success",
								new Object[] { blockedMobileResponseDTO.getBlockedMobiles().size() }, Locale.ENGLISH)),
				HttpStatus.OK);
	}

	@PostMapping("/unblockByMobile")
	public ResponseEntity<AllureResponseDTO<BlockedMobileResponseDTO>> unblockByMobile(LoggedInUserDTO loggedInUserDTO,
			@RequestBody PostBlockUnblockByMobileDTO postUnblockByMobileDTO) {

		BlockedMobileResponseDTO blockedMobileResponseDTO = blockService.postUnblockByMobile(loggedInUserDTO,
				postUnblockByMobileDTO);

		return new ResponseEntity<AllureResponseDTO<BlockedMobileResponseDTO>>(
				new AllureResponseDTO<BlockedMobileResponseDTO>(blockedMobileResponseDTO,
						AllureResponseStatus.SUCCESS.getCode(),
						messageSource.getMessage("get.blocked.mobile.success",
								new Object[] { blockedMobileResponseDTO.getBlockedMobiles().size() }, Locale.ENGLISH)),
				HttpStatus.OK);
	}

	@GetMapping("/getBlockedMobiles")
	public ResponseEntity<AllureResponseDTO<BlockedMobileResponseDTO>> getBlockedMobiles(
			LoggedInUserDTO loggedInUserDTO) {

		BlockedMobileResponseDTO blockedMobileResponseDTO = blockService.getBlockedMobiles(loggedInUserDTO);

		return new ResponseEntity<AllureResponseDTO<BlockedMobileResponseDTO>>(
				new AllureResponseDTO<BlockedMobileResponseDTO>(blockedMobileResponseDTO,
						AllureResponseStatus.SUCCESS.getCode(),
						messageSource.getMessage("get.blocked.mobile.success",
								new Object[] { blockedMobileResponseDTO.getBlockedMobiles().size() }, Locale.ENGLISH)),
				HttpStatus.OK);
	}

}
