package com.allure.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.allure.constants.DpUploadType;
import com.allure.dao.UserPassionsRepository;
import com.allure.dao.UserRepository;
import com.allure.dao.UserSexualOrientationsRepository;
import com.allure.dto.LoggedInUserDTO;
import com.allure.dto.profile.EditUserProfileDTO;
import com.allure.dto.profile.EditUserProfileFormDataDTO;
import com.allure.dto.profile.GetProfileResponseDTO;
import com.allure.dto.profile.UpdateUserProfileDTO;
import com.allure.dto.profile.UpdateUserProfileFormDataDTO;
import com.allure.dto.profile.UserGetProfileResponseDTO;
import com.allure.entity.UserPassions;
import com.allure.entity.UserSexualOrientations;
import com.allure.entity.Users;
import com.allure.mapper.PassionMapper;
import com.allure.mapper.UserMapper;
import com.allure.mapper.UserPassionsMapper;
import com.allure.mapper.UserSexualOrientationsMapper;
import com.allure.service.ProfileService;
import com.allure.service.UserService;

@Service
public class ProfileServiceImpl implements ProfileService {

	@Autowired
	MessageSource messageSource;

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserPassionsRepository userPassionsRepository;

	@Autowired
	UserSexualOrientationsRepository userSexualOrientationsRepository;

	@Autowired
	UserService userService;

	@Autowired
	UserMapper userMapper;

	@Autowired
	PassionMapper passionMapper;

	@Autowired
	UserPassionsMapper userPassionsMapper;

	@Autowired
	UserSexualOrientationsMapper userSexualOrientationsMapper;

	@Override
	public GetProfileResponseDTO getUserProfile(LoggedInUserDTO loggedInUserDTO) {
		Users loggedInUser = userService.getUserByUserId(loggedInUserDTO.getLoggedInUserId());
		UserGetProfileResponseDTO userGetProfileResponseDTO = userMapper.userToUserGetProfileResponseDTO(loggedInUser);
		userGetProfileResponseDTO.setDpUrls(userService.getDpUrls(loggedInUserDTO.getLoggedInUserId()));
		return new GetProfileResponseDTO(userGetProfileResponseDTO);
	}

	@Override
	@Transactional
	public void updateUserProfile(LoggedInUserDTO loggedInUserDTO,
			UpdateUserProfileFormDataDTO updateUserProfileFormDataDTO) {
		Users loggedInUser = userService.getUserByUserId(loggedInUserDTO.getLoggedInUserId());
		UpdateUserProfileDTO updateUserProfileDTO = updateUserProfileFormDataDTO.getProfileInfo();
		loggedInUser = userMapper.updateUserProfileDTOToUsers(updateUserProfileDTO, loggedInUser);

		userRepository.save(loggedInUser);

		updateUserPassions(updateUserProfileDTO.getPassions(), loggedInUserDTO.getLoggedInUserId());
		updateUserSexualOrientations(updateUserProfileDTO.getSexualOrientations(), loggedInUserDTO.getLoggedInUserId());
		userService.updateUserImages(loggedInUserDTO.getLoggedInUserId(),
				new MultipartFile[] { updateUserProfileFormDataDTO.getDp1(), updateUserProfileFormDataDTO.getDp2(),
						updateUserProfileFormDataDTO.getDp3(), updateUserProfileFormDataDTO.getDp4(),
						updateUserProfileFormDataDTO.getDp5(), updateUserProfileFormDataDTO.getDp6() },
				new DpUploadType[] { updateUserProfileDTO.getDp1(), updateUserProfileDTO.getDp2(),
						updateUserProfileDTO.getDp3(), updateUserProfileDTO.getDp4(), updateUserProfileDTO.getDp5(),
						updateUserProfileDTO.getDp6() });
	}

	private void updateUserSexualOrientations(List<Integer> newSexualOrientationsSexualOrientationIds, int userId) {
		List<Integer> oldUserSexualOrientationsSexualOrientationIds = userSexualOrientationsRepository
				.getSexualOrientationIdsByUsersId(userId);
		List<Integer> commonUserSexualOrientationsSexualOrientationIds = new ArrayList<Integer>(
				newSexualOrientationsSexualOrientationIds);
		commonUserSexualOrientationsSexualOrientationIds.retainAll(oldUserSexualOrientationsSexualOrientationIds);
		oldUserSexualOrientationsSexualOrientationIds.removeAll(commonUserSexualOrientationsSexualOrientationIds);
		newSexualOrientationsSexualOrientationIds.removeAll(commonUserSexualOrientationsSexualOrientationIds);
		List<UserSexualOrientations> newUserSexualOrientations = userSexualOrientationsMapper
				.sexualOrientationIdListAndUserIdToUserSexualOrientationsNonEntityList(
						newSexualOrientationsSexualOrientationIds, userId);
		oldUserSexualOrientationsSexualOrientationIds.forEach(sexualOrientationId -> {
			userSexualOrientationsRepository.deleteBySexualOrientationsIdAndUsersId(sexualOrientationId, userId);
		});

		newUserSexualOrientations.forEach(userSexualOrientation -> {
			userSexualOrientationsRepository.save(userSexualOrientation);
		});
	}

	private void updateUserPassions(List<Integer> newUserPassionsPassionIds, int userId) {
		List<Integer> oldUserPassionsPassionIds = userPassionsRepository.getPassionsIdByUsersId(userId);
		List<Integer> commonUserPassionsPassionIds = new ArrayList<Integer>(newUserPassionsPassionIds);
		commonUserPassionsPassionIds.retainAll(oldUserPassionsPassionIds);
		oldUserPassionsPassionIds.removeAll(commonUserPassionsPassionIds);
		newUserPassionsPassionIds.removeAll(commonUserPassionsPassionIds);
		List<UserPassions> newUserPassions = userPassionsMapper
				.passionIdListAndUserIdToUserPassionsNonEntityList(newUserPassionsPassionIds, userId);
		oldUserPassionsPassionIds.forEach(passionId -> {
			userPassionsRepository.deleteByPassionsIdAndUsersId(passionId, userId);
		});

		newUserPassions.forEach(userPassion -> {
			userPassionsRepository.save(userPassion);
		});
	}

	@Override
	@Transactional
	public void editUserProfile(LoggedInUserDTO loggedInUserDTO,
			EditUserProfileFormDataDTO editUserProfileFormDataDTO) {
		EditUserProfileDTO editUserProfileDTO = editUserProfileFormDataDTO.getProfileInfo();
		Users loggedInUser = userService.getUserByUserId(loggedInUserDTO.getLoggedInUserId());
		loggedInUser = userMapper.editUserProfileDTOToUsers(editUserProfileDTO, loggedInUser);
		userRepository.save(loggedInUser);

		updateUserPassions(editUserProfileDTO.getPassions(), loggedInUserDTO.getLoggedInUserId());
		updateUserSexualOrientations(editUserProfileDTO.getSexualOrientations(), loggedInUserDTO.getLoggedInUserId());
		userService.updateUserImages(loggedInUserDTO.getLoggedInUserId(),
				new MultipartFile[] { editUserProfileFormDataDTO.getDp1(), editUserProfileFormDataDTO.getDp2(),
						editUserProfileFormDataDTO.getDp3(), editUserProfileFormDataDTO.getDp4(),
						editUserProfileFormDataDTO.getDp5(), editUserProfileFormDataDTO.getDp6() },
				new DpUploadType[] { editUserProfileDTO.getDp1(), editUserProfileDTO.getDp2(),
						editUserProfileDTO.getDp3(), editUserProfileDTO.getDp4(), editUserProfileDTO.getDp5(),
						editUserProfileDTO.getDp6() });
	}

}
