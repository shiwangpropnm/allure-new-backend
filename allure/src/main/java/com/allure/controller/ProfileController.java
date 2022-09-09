package com.allure.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.allure.constants.AllureResponseStatus;
import com.allure.dto.AllureResponseDTO;
import com.allure.dto.LoggedInUserDTO;
import com.allure.dto.common.GetIdValueDTO;
import com.allure.dto.profile.EditUserProfileFormDataDTO;
import com.allure.dto.profile.GetProfileResponseDTO;
import com.allure.dto.profile.UpdateUserProfileFormDataDTO;
import com.allure.service.CommonService;
import com.allure.service.ProfileService;

@RestController
@RequestMapping("/v1/profile")
public class ProfileController {

	@Autowired
	ProfileService profileService;

	@Autowired
	CommonService commonService;

	@Autowired
	MessageSource messageSource;

	@GetMapping(value = "/getProfile")
	public ResponseEntity<AllureResponseDTO<GetProfileResponseDTO>> getProfile(LoggedInUserDTO loggedInUserDTO) {

		GetProfileResponseDTO getProfileResponseDTO = profileService.getUserProfile(loggedInUserDTO);

		return new ResponseEntity<>(new AllureResponseDTO<GetProfileResponseDTO>(getProfileResponseDTO,
				AllureResponseStatus.SUCCESS.getCode(),
				messageSource.getMessage("user.profile.get.success", null, Locale.ENGLISH)), HttpStatus.OK);
	}

	// @PutMapping(value = "/updateProfile")
	// public ResponseEntity<AllureResponseDTO<Object>>
	// updateProfile(LoggedInUserDTO loggedInUserDTO,
	// @RequestBody UpdateUserProfileDTO updateUserProfileDTO) {
	//
	// profileService.updateUserProfile(loggedInUserDTO, updateUserProfileDTO);
	//
	// return new ResponseEntity<>(new AllureResponseDTO<>(null,
	// AllureResponseStatus.SUCCESS.getCode(),
	// messageSource.getMessage("user.profile.update.success", null,
	// Locale.ENGLISH)), HttpStatus.OK);
	//
	// }

	@PutMapping(value = "/updateProfile", consumes = { "multipart/form-data" })
	public ResponseEntity<AllureResponseDTO<Object>> updateProfile(LoggedInUserDTO loggedInUserDTO,
			@ModelAttribute UpdateUserProfileFormDataDTO updateUserProfileFormDataDTO) {

		profileService.updateUserProfile(loggedInUserDTO, updateUserProfileFormDataDTO);

		return new ResponseEntity<>(new AllureResponseDTO<>(null, AllureResponseStatus.SUCCESS.getCode(),
				messageSource.getMessage("user.profile.update.success", null, Locale.ENGLISH)), HttpStatus.OK);

	}

	@PostMapping(value = "/editProfile", consumes = { "multipart/form-data" })
	public ResponseEntity<AllureResponseDTO<Object>> editProfile(LoggedInUserDTO loggedInUserDTO,
			@ModelAttribute EditUserProfileFormDataDTO editUserProfileFormDataDTO) {

		profileService.editUserProfile(loggedInUserDTO, editUserProfileFormDataDTO);

		return new ResponseEntity<>(new AllureResponseDTO<>(null, AllureResponseStatus.SUCCESS.getCode(),
				messageSource.getMessage("user.profile.edit.success", null, Locale.ENGLISH)), HttpStatus.OK);

	}

	@GetMapping(value = "/getEducationLevelList")
	public ResponseEntity<AllureResponseDTO<Object>> getEducationLevelList() {

		List<GetIdValueDTO> result = commonService.getEducationLevelList();

		return new ResponseEntity<>(
				new AllureResponseDTO<>(result, AllureResponseStatus.SUCCESS.getCode(),
						messageSource.getMessage("get.education.level.list.success", null, Locale.ENGLISH)),
				HttpStatus.OK);
	}

	@GetMapping(value = "/getEyeColorList")
	public ResponseEntity<AllureResponseDTO<Object>> getEyeColorList() {

		List<GetIdValueDTO> result = commonService.getEyeColorList();

		return new ResponseEntity<>(new AllureResponseDTO<>(result, AllureResponseStatus.SUCCESS.getCode(),
				messageSource.getMessage("get.eye.color.list.success", null, Locale.ENGLISH)), HttpStatus.OK);
	}

	@GetMapping(value = "/getBodyTypeList")
	public ResponseEntity<AllureResponseDTO<Object>> getBodyTypeList() {

		List<GetIdValueDTO> result = commonService.getBodyTypeList();

		return new ResponseEntity<>(new AllureResponseDTO<>(result, AllureResponseStatus.SUCCESS.getCode(),
				messageSource.getMessage("get.body.type.list.success", null, Locale.ENGLISH)), HttpStatus.OK);
	}

	@GetMapping(value = "/getHairColorList")
	public ResponseEntity<AllureResponseDTO<Object>> getHairColorList() {

		List<GetIdValueDTO> result = commonService.getHairColorList();

		return new ResponseEntity<>(new AllureResponseDTO<>(result, AllureResponseStatus.SUCCESS.getCode(),
				messageSource.getMessage("get.hair.color.list.success", null, Locale.ENGLISH)), HttpStatus.OK);
	}

	@GetMapping(value = "/getRelationshipStatusList")
	public ResponseEntity<AllureResponseDTO<Object>> getRelationshipStatusList() {

		List<GetIdValueDTO> result = commonService.getRelationshipStatusList();

		return new ResponseEntity<>(
				new AllureResponseDTO<>(result, AllureResponseStatus.SUCCESS.getCode(),
						messageSource.getMessage("get.relationship.status.list.success", null, Locale.ENGLISH)),
				HttpStatus.OK);
	}

	@GetMapping(value = "/getEthnicityList")
	public ResponseEntity<AllureResponseDTO<Object>> getEthnicityList() {

		List<GetIdValueDTO> result = commonService.getEthnicityList();

		return new ResponseEntity<>(new AllureResponseDTO<>(result, AllureResponseStatus.SUCCESS.getCode(),
				messageSource.getMessage("get.ethnicity.list.success", null, Locale.ENGLISH)), HttpStatus.OK);
	}

	@GetMapping(value = "/getReligionList")
	public ResponseEntity<AllureResponseDTO<Object>> getReligionList() {

		List<GetIdValueDTO> result = commonService.getReligionList();

		return new ResponseEntity<>(new AllureResponseDTO<>(result, AllureResponseStatus.SUCCESS.getCode(),
				messageSource.getMessage("get.religion.list.success", null, Locale.ENGLISH)), HttpStatus.OK);
	}

	@GetMapping(value = "/getGenderList")
	public ResponseEntity<AllureResponseDTO<Object>> getGenderList() {

		List<GetIdValueDTO> result = commonService.getGenderList();

		return new ResponseEntity<>(new AllureResponseDTO<>(result, AllureResponseStatus.SUCCESS.getCode(),
				messageSource.getMessage("get.gender.list.success", null, Locale.ENGLISH)), HttpStatus.OK);
	}

}
