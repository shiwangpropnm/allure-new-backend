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
import com.allure.dto.login.VerifyOtpDTO;
import com.allure.dto.login.VerifyOtpResponseDTO;
import com.allure.dto.usersetting.GetDiscoverySettingResponseDTO;
import com.allure.dto.usersetting.GetEmailSubscriptionResponseDTO;
import com.allure.dto.usersetting.GetShowMeResponseDTO;
import com.allure.dto.usersetting.GetUserSettingResponseDTO;
import com.allure.dto.usersetting.GetUsernameResponseDTO;
import com.allure.dto.usersetting.UpdateDiscoverySettingDTO;
import com.allure.dto.usersetting.UpdateEmailDTO;
import com.allure.dto.usersetting.UpdateMobileDTO;
import com.allure.dto.usersetting.UpdateShowMeDTO;
import com.allure.dto.usersetting.UpdateSubscriptionStatusDTO;
import com.allure.dto.usersetting.UpdateUserSettingDTO;
import com.allure.dto.usersetting.UpdateUsernameDTO;
import com.allure.service.UserService;

@RestController
@RequestMapping("v1/userSetting")
public class UserSettingController {

	@Autowired
	UserService userService;

	@Autowired
	MessageSource messageSource;

	@GetMapping("/getUsername")
	public ResponseEntity<AllureResponseDTO<GetUsernameResponseDTO>> getUsername(LoggedInUserDTO loggedInUserDTO) {

		GetUsernameResponseDTO getUsernameResponseDTO = userService.getUsername(loggedInUserDTO);
		return new ResponseEntity<AllureResponseDTO<GetUsernameResponseDTO>>(
				new AllureResponseDTO<GetUsernameResponseDTO>(getUsernameResponseDTO,
						AllureResponseStatus.SUCCESS.getCode(),
						messageSource.getMessage("get.user.setting.username.success", null, Locale.ENGLISH)),
				HttpStatus.OK);
	}

	@PostMapping("/updateUsername")
	public ResponseEntity<AllureResponseDTO<GetUsernameResponseDTO>> updateUsername(LoggedInUserDTO loggedInUserDTO,
			@RequestBody UpdateUsernameDTO updateUsernameDTO) {

		GetUsernameResponseDTO getUsernameResponseDTO = userService.updateUsername(loggedInUserDTO, updateUsernameDTO);
		return new ResponseEntity<AllureResponseDTO<GetUsernameResponseDTO>>(
				new AllureResponseDTO<GetUsernameResponseDTO>(getUsernameResponseDTO,
						AllureResponseStatus.SUCCESS.getCode(),
						messageSource.getMessage("update.user.setting.username.success", null, Locale.ENGLISH)),
				HttpStatus.OK);
	}

	@GetMapping("/getShowMe")
	public ResponseEntity<AllureResponseDTO<GetShowMeResponseDTO>> getShowMe(LoggedInUserDTO loggedInUserDTO) {

		GetShowMeResponseDTO getShowMeResponseDTO = userService.getShowMe(loggedInUserDTO);
		return new ResponseEntity<AllureResponseDTO<GetShowMeResponseDTO>>(
				new AllureResponseDTO<GetShowMeResponseDTO>(getShowMeResponseDTO,
						AllureResponseStatus.SUCCESS.getCode(),
						messageSource.getMessage("get.user.setting.show.me.success", null, Locale.ENGLISH)),
				HttpStatus.OK);
	}

	@PostMapping("/updateShowMe")
	public ResponseEntity<AllureResponseDTO<GetShowMeResponseDTO>> updateShowMe(LoggedInUserDTO loggedInUserDTO,
			@RequestBody UpdateShowMeDTO updateShowMeDTO) {

		GetShowMeResponseDTO getShowMeResponseDTO = userService.updateShowMe(loggedInUserDTO, updateShowMeDTO);
		return new ResponseEntity<AllureResponseDTO<GetShowMeResponseDTO>>(
				new AllureResponseDTO<GetShowMeResponseDTO>(getShowMeResponseDTO,
						AllureResponseStatus.SUCCESS.getCode(),
						messageSource.getMessage("update.user.setting.show.me.success", null, Locale.ENGLISH)),
				HttpStatus.OK);
	}

	@GetMapping("/getDiscoverySetting")
	public ResponseEntity<AllureResponseDTO<GetDiscoverySettingResponseDTO>> getDiscoverySetting(
			LoggedInUserDTO loggedInUserDTO) {

		GetDiscoverySettingResponseDTO getDiscoverySettingResponseDTO = userService
				.getDiscoverySetting(loggedInUserDTO);
		return new ResponseEntity<AllureResponseDTO<GetDiscoverySettingResponseDTO>>(
				new AllureResponseDTO<GetDiscoverySettingResponseDTO>(getDiscoverySettingResponseDTO,
						AllureResponseStatus.SUCCESS.getCode(),
						messageSource.getMessage("get.user.setting.discovery.setting.success", null, Locale.ENGLISH)),
				HttpStatus.OK);
	}

	@PostMapping("/updateDiscoverySetting")
	public ResponseEntity<AllureResponseDTO<GetDiscoverySettingResponseDTO>> updateDiscoverySetting(
			LoggedInUserDTO loggedInUserDTO, @RequestBody UpdateDiscoverySettingDTO discoverySettingDTO) {

		GetDiscoverySettingResponseDTO getDiscoverySettingResponseDTO = userService
				.updateDiscoverySetting(loggedInUserDTO, discoverySettingDTO);
		return new ResponseEntity<AllureResponseDTO<GetDiscoverySettingResponseDTO>>(
				new AllureResponseDTO<GetDiscoverySettingResponseDTO>(getDiscoverySettingResponseDTO,
						AllureResponseStatus.SUCCESS.getCode(), messageSource
								.getMessage("update.user.setting.discovery.setting.success", null, Locale.ENGLISH)),
				HttpStatus.OK);
	}

	@GetMapping("/getUserSetting")
	public ResponseEntity<AllureResponseDTO<GetUserSettingResponseDTO>> getUserSetting(
			LoggedInUserDTO loggedInUserDTO) {

		GetUserSettingResponseDTO getUserSettingResponseDTO = userService.getUserSetting(loggedInUserDTO);
		return new ResponseEntity<AllureResponseDTO<GetUserSettingResponseDTO>>(
				new AllureResponseDTO<GetUserSettingResponseDTO>(getUserSettingResponseDTO,
						AllureResponseStatus.SUCCESS.getCode(),
						messageSource.getMessage("get.user.setting.success", null, Locale.ENGLISH)),
				HttpStatus.OK);
	}

	@PostMapping("/updateUserSetting")
	public ResponseEntity<AllureResponseDTO<GetUserSettingResponseDTO>> updateUserSetting(
			LoggedInUserDTO loggedInUserDTO, @RequestBody UpdateUserSettingDTO updateUserSettingDTO) {

		GetUserSettingResponseDTO getUserSettingResponseDTO = userService.updateUserSetting(loggedInUserDTO,
				updateUserSettingDTO);
		return new ResponseEntity<AllureResponseDTO<GetUserSettingResponseDTO>>(
				new AllureResponseDTO<GetUserSettingResponseDTO>(getUserSettingResponseDTO,
						AllureResponseStatus.SUCCESS.getCode(),
						messageSource.getMessage("update.user.setting.success", null, Locale.ENGLISH)),
				HttpStatus.OK);
	}

	@GetMapping("/getEmailSubscription")
	public ResponseEntity<AllureResponseDTO<GetEmailSubscriptionResponseDTO>> getEmailSubscription(
			LoggedInUserDTO loggedInUserDTO) {

		GetEmailSubscriptionResponseDTO getEmailSubscriptionResponseDTO = userService
				.getUserEmailSubscription(loggedInUserDTO);
		return new ResponseEntity<AllureResponseDTO<GetEmailSubscriptionResponseDTO>>(
				new AllureResponseDTO<GetEmailSubscriptionResponseDTO>(getEmailSubscriptionResponseDTO,
						AllureResponseStatus.SUCCESS.getCode(),
						messageSource.getMessage("get.user.email.subscription.success", null, Locale.ENGLISH)),
				HttpStatus.OK);
	}

	@PostMapping("/setEmailNewMatchSubscription")
	public ResponseEntity<AllureResponseDTO<GetEmailSubscriptionResponseDTO>> setEmailNewMatchSubscription(
			LoggedInUserDTO loggedInUserDTO, @RequestBody UpdateSubscriptionStatusDTO updateSubscriptionStatusDTO) {

		GetEmailSubscriptionResponseDTO getEmailSubscriptionResponseDTO = userService
				.updateEmailNewMatchSubscription(loggedInUserDTO, updateSubscriptionStatusDTO);
		return new ResponseEntity<AllureResponseDTO<GetEmailSubscriptionResponseDTO>>(
				new AllureResponseDTO<GetEmailSubscriptionResponseDTO>(getEmailSubscriptionResponseDTO,
						AllureResponseStatus.SUCCESS.getCode(), messageSource
								.getMessage("update.user.email.newmatch.subscription.success", null, Locale.ENGLISH)),
				HttpStatus.OK);
	}

	@PostMapping("/setEmailNewMessageSubscription")
	public ResponseEntity<AllureResponseDTO<GetEmailSubscriptionResponseDTO>> setEmailNewMessageSubscription(
			LoggedInUserDTO loggedInUserDTO, @RequestBody UpdateSubscriptionStatusDTO updateSubscriptionStatusDTO) {

		GetEmailSubscriptionResponseDTO getEmailSubscriptionResponseDTO = userService
				.updateEmailNewMessageSubscription(loggedInUserDTO, updateSubscriptionStatusDTO);
		return new ResponseEntity<AllureResponseDTO<GetEmailSubscriptionResponseDTO>>(
				new AllureResponseDTO<GetEmailSubscriptionResponseDTO>(getEmailSubscriptionResponseDTO,
						AllureResponseStatus.SUCCESS.getCode(), messageSource
								.getMessage("update.user.email.newmessage.subscription.success", null, Locale.ENGLISH)),
				HttpStatus.OK);
	}

	@PostMapping("/setPromotionSubscription")
	public ResponseEntity<AllureResponseDTO<GetEmailSubscriptionResponseDTO>> setPromotionSubscription(
			LoggedInUserDTO loggedInUserDTO, @RequestBody UpdateSubscriptionStatusDTO updateSubscriptionStatusDTO) {

		GetEmailSubscriptionResponseDTO getEmailSubscriptionResponseDTO = userService
				.updateEmailPromotionSubscription(loggedInUserDTO, updateSubscriptionStatusDTO);
		return new ResponseEntity<AllureResponseDTO<GetEmailSubscriptionResponseDTO>>(
				new AllureResponseDTO<GetEmailSubscriptionResponseDTO>(getEmailSubscriptionResponseDTO,
						AllureResponseStatus.SUCCESS.getCode(), messageSource
								.getMessage("update.user.email.promotion.subscription.success", null, Locale.ENGLISH)),
				HttpStatus.OK);
	}

	@PostMapping("/emailUnsubscribeAll")
	public ResponseEntity<AllureResponseDTO<GetEmailSubscriptionResponseDTO>> emailUnsubscribeAll(
			LoggedInUserDTO loggedInUserDTO) {

		GetEmailSubscriptionResponseDTO getEmailSubscriptionResponseDTO = userService
				.updateEmailSubscriptionUnsubscribeAll(loggedInUserDTO);
		return new ResponseEntity<AllureResponseDTO<GetEmailSubscriptionResponseDTO>>(
				new AllureResponseDTO<GetEmailSubscriptionResponseDTO>(getEmailSubscriptionResponseDTO,
						AllureResponseStatus.SUCCESS.getCode(), messageSource.getMessage(
								"update.user.email.subscription.unsubscribe.all.success", null, Locale.ENGLISH)),
				HttpStatus.OK);
	}

	@PostMapping("/updateEmail")
	public ResponseEntity<AllureResponseDTO<VerifyOtpResponseDTO>> updateEmail(LoggedInUserDTO loggedInUserDTO,
			@RequestBody UpdateEmailDTO updateEmailDTO) {

		VerifyOtpResponseDTO updateEmailVerifyOtpDTO = userService.updateEmail(loggedInUserDTO, updateEmailDTO);
		return new ResponseEntity<>(
				new AllureResponseDTO<>(updateEmailVerifyOtpDTO, AllureResponseStatus.SUCCESS.getCode(),
						messageSource.getMessage("update.user.email.send.otp.success", null, Locale.ENGLISH)),
				HttpStatus.OK);
	}

	@PostMapping("/updateEmailVerifyOTP")
	public ResponseEntity<AllureResponseDTO<GetEmailSubscriptionResponseDTO>> updateEmailVerifyOTP(LoggedInUserDTO loggedInUserDTO,
			@RequestBody VerifyOtpDTO verifyOtpDTO) {
		
		GetEmailSubscriptionResponseDTO getEmailSubscriptionResponseDTO = userService.updateEmailVerifyOTP(loggedInUserDTO, verifyOtpDTO);
		return new ResponseEntity<>(
				new AllureResponseDTO<>(getEmailSubscriptionResponseDTO, AllureResponseStatus.SUCCESS.getCode(),
						messageSource.getMessage("update.user.email.otp.verify.success", null, Locale.ENGLISH)),
				HttpStatus.OK);
	}

	@PostMapping("/updateMobile")
	public ResponseEntity<AllureResponseDTO<VerifyOtpResponseDTO>> updateMobile(LoggedInUserDTO loggedInUserDTO,
			@RequestBody UpdateMobileDTO updateMobileDTO) {
		
		VerifyOtpResponseDTO updateEmailVerifyOtpDTO = userService.updateMobile(loggedInUserDTO, updateMobileDTO);
		return new ResponseEntity<>(
				new AllureResponseDTO<>(updateEmailVerifyOtpDTO, AllureResponseStatus.SUCCESS.getCode(),
						messageSource.getMessage("update.user.mobile.send.otp.success", null, Locale.ENGLISH)),
				HttpStatus.OK);
	}
	
	@PostMapping("/updateMobileVerifyOTP")
	public ResponseEntity<AllureResponseDTO<Object>> updateMobileVerifyOTP(LoggedInUserDTO loggedInUserDTO,
			@RequestBody VerifyOtpDTO verifyOtpDTO) {
		
		userService.updateMobileVerifyOTP(loggedInUserDTO, verifyOtpDTO);
		return new ResponseEntity<>(
				new AllureResponseDTO<>(null, AllureResponseStatus.SUCCESS.getCode(),
						messageSource.getMessage("update.user.mobile.otp.verify.success", null, Locale.ENGLISH)),
				HttpStatus.OK);
	}
	
}
