package com.allure.service;

import com.allure.dto.LoggedInUserDTO;
import com.allure.dto.block.BlockedMobileResponseDTO;
import com.allure.dto.block.PostBlockUnblockByMobileDTO;

public interface BlockService {

	BlockedMobileResponseDTO postBlockByMobile(LoggedInUserDTO loggedInUserDTO,
			PostBlockUnblockByMobileDTO postBlockByMobileDTO);

	BlockedMobileResponseDTO getBlockedMobiles(LoggedInUserDTO loggedInUserDTO);

	BlockedMobileResponseDTO postUnblockByMobile(LoggedInUserDTO loggedInUserDTO,
			PostBlockUnblockByMobileDTO postUnblockByMobileDTO);

}
