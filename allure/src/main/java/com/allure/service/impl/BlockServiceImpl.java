package com.allure.service.impl;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.allure.dao.BlockListRepository;
import com.allure.dao.ProfileLikeDislikeRepository;
import com.allure.dto.LoggedInUserDTO;
import com.allure.dto.block.BlockedMobileResponseDTO;
import com.allure.dto.block.PostBlockUnblockByMobileDTO;
import com.allure.entity.Blocklist;
import com.allure.entity.Users;
import com.allure.mapper.UserMapper;
import com.allure.service.BlockService;
import com.allure.service.UserService;

@Service
public class BlockServiceImpl implements BlockService {

	@Autowired
	BlockListRepository blockRepository;

	@Autowired
	ProfileLikeDislikeRepository profileLikeDislikeRepository;

	@Autowired
	UserService userService;

	@Autowired
	MessageSource messageSource;

	@Autowired
	UserMapper userMapper;

	@Override
	public BlockedMobileResponseDTO postBlockByMobile(LoggedInUserDTO loggedInUserDTO,
			PostBlockUnblockByMobileDTO postBlockByMobileDTO) {
		Users user = userMapper.idToUsersNonEntity(loggedInUserDTO.getLoggedInUserId());
		List<String> existingblockedMobiles = blockRepository.findAllBlockedMobileByUserAndBlockedMobileIn(user,
				postBlockByMobileDTO.getMobileNumbers());
		List<String> newBlockedMobiles = postBlockByMobileDTO.getMobileNumbers();
		newBlockedMobiles.removeAll(existingblockedMobiles);
		newBlockedMobiles.forEach(blockedMobile -> {
			Blocklist blocklist = new Blocklist();
			blocklist.setUser(user);
			blocklist.setBlockedMobile(blockedMobile);
			blocklist.setCreatedAt(new Date());
			blockRepository.save(blocklist);
		});
		return getBlockedMobiles(loggedInUserDTO.getLoggedInUserId());
	}

	@Override
	public BlockedMobileResponseDTO getBlockedMobiles(LoggedInUserDTO loggedInUserDTO) {
		return getBlockedMobiles(loggedInUserDTO.getLoggedInUserId());
	}

	private BlockedMobileResponseDTO getBlockedMobiles(Integer loggedInUserId) {
		Users user = userMapper.idToUsersNonEntity(loggedInUserId);
		List<String> existingblockedMobiles = blockRepository.findBlockedMobileByUser(user);
		return new BlockedMobileResponseDTO(existingblockedMobiles);
	}

	@Override
	@Transactional
	public BlockedMobileResponseDTO postUnblockByMobile(LoggedInUserDTO loggedInUserDTO,
			PostBlockUnblockByMobileDTO postUnblockByMobileDTO) {
		Users user = userMapper.idToUsersNonEntity(loggedInUserDTO.getLoggedInUserId());
		List<Blocklist> existingblockedMobiles = blockRepository.findAllByUserAndBlockedMobileIn(user,
				postUnblockByMobileDTO.getMobileNumbers());
		existingblockedMobiles.forEach(blocklist -> {
			blockRepository.delete(blocklist);
		});
		return getBlockedMobiles(loggedInUserDTO.getLoggedInUserId());
	}

}
