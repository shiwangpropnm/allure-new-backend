package com.allure.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.allure.constants.AllureResponseStatus;
import com.allure.constants.LoginType;
import com.allure.dto.AllureResponseDTO;
import com.allure.dto.forgotpassword.ForgetPasswordChangeDTO;
import com.allure.dto.forgotpassword.ForgetPasswordDTO;
import com.allure.dto.forgotpassword.ForgetPasswordResponseDTO;
import com.allure.dto.forgotpassword.ForgetPasswordVerifyOtpDTO;
import com.allure.dto.login.LoginRequestDTO;
import com.allure.dto.login.LoginResponseDTO;
import com.allure.dto.login.ResendOtpRequestDTO;
import com.allure.dto.login.VerifyOtpDTO;
import com.allure.dto.login.VerifyOtpResponseDTO;
import com.allure.dto.registration.RegisterMobileDTO;
import com.allure.dto.registration.RegisterMobileResponseDTO;
import com.allure.dto.registration.RegistrationRequestDTO;
import com.allure.dto.registration.RegistrationResponseDTO;
import com.allure.exception.UnprocessableEntityException;
import com.allure.service.UserService;

@RestController
@CrossOrigin
@RequestMapping("/v1/auth")
public class AuthController {

	@Autowired
	UserService userService;

	@Autowired
	private MessageSource messageSource;

	@PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AllureResponseDTO<Object>> login(@RequestBody LoginRequestDTO loginRequestDTO) {

		LoginType loginType = LoginType.valueOf(loginRequestDTO.getLoginType());
		if (loginType == null)
			throw new UnprocessableEntityException(
					messageSource.getMessage("exception.invalid.logintype", null, Locale.ENGLISH));
		AllureResponseDTO<Object> allureResponseDTO = null;
		switch (loginType) {
		case EMAIL:
			LoginResponseDTO loginResponseDTO = userService.loginByEmail(loginRequestDTO);
			allureResponseDTO = new AllureResponseDTO<>(loginResponseDTO, AllureResponseStatus.SUCCESS.getCode(),
					messageSource.getMessage("login.success", null, Locale.ENGLISH));
			break;
		case MOBILE:
			VerifyOtpResponseDTO verifyOtpResponseDTO = userService.loginByMobile(loginRequestDTO);
			allureResponseDTO = new AllureResponseDTO<>(verifyOtpResponseDTO, AllureResponseStatus.SUCCESS.getCode(),
					messageSource.getMessage("otp.sent.success", null, Locale.ENGLISH));

		}

		return new ResponseEntity<>(allureResponseDTO, HttpStatus.OK);

	}

	@PostMapping(value = "/registration", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AllureResponseDTO<RegistrationResponseDTO>> registration(
			@RequestBody RegistrationRequestDTO registrationRequestDTO) {

		RegistrationResponseDTO registraionResponseDTO = userService.registration(registrationRequestDTO);

		return new ResponseEntity<>(new AllureResponseDTO<RegistrationResponseDTO>(registraionResponseDTO,
				AllureResponseStatus.SUCCESS.getCode(),
				messageSource.getMessage("registration.success", null, Locale.ENGLISH)), HttpStatus.OK);

	}

	@PostMapping(value = "/verifyOTP", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AllureResponseDTO<LoginResponseDTO>> verifyOTP(@RequestBody VerifyOtpDTO verifyOtpDTO) {

		LoginResponseDTO loginResponseDTO = userService.verifyOTP(verifyOtpDTO);

		return new ResponseEntity<>(new AllureResponseDTO<>(loginResponseDTO, AllureResponseStatus.SUCCESS.getCode(),
				messageSource.getMessage("login.success", null, Locale.ENGLISH)), HttpStatus.OK);
	}

	@PostMapping(value = "/resendOTP", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AllureResponseDTO<VerifyOtpResponseDTO>> resendOTP(@RequestBody ResendOtpRequestDTO resendOtpDTO) {

		VerifyOtpResponseDTO verifyOtpResponseDTO = userService.resendOTP(resendOtpDTO);

		return new ResponseEntity<>(new AllureResponseDTO<>(verifyOtpResponseDTO, AllureResponseStatus.SUCCESS.getCode(),
				messageSource.getMessage("otp.sent.success", null, Locale.ENGLISH)), HttpStatus.OK);
	}

	@PostMapping(value = "/registerMobile", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AllureResponseDTO<RegisterMobileResponseDTO>> registerMobile(
			@RequestBody RegisterMobileDTO registerMobileDTO) {

		RegisterMobileResponseDTO registerMobileResponseDTO = userService.registerMobile(registerMobileDTO);
		return new ResponseEntity<>(
				new AllureResponseDTO<>(registerMobileResponseDTO, AllureResponseStatus.SUCCESS.getCode(),
						messageSource.getMessage("otp.sent.success", null, Locale.ENGLISH)),
				HttpStatus.OK);
	}

	@PostMapping(value = "/forgetPassword", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AllureResponseDTO> forgetPassword(@RequestBody ForgetPasswordDTO forgetPasswordDTO) {

		ForgetPasswordResponseDTO forgetPasswordResponseDTO = userService.forgotPassword(forgetPasswordDTO);
		AllureResponseDTO allureResponseDTO = new AllureResponseDTO<ForgetPasswordResponseDTO>(
				forgetPasswordResponseDTO, AllureResponseStatus.SUCCESS.getCode(),
				messageSource.getMessage("otp.sent.success", null, Locale.ENGLISH));

		return new ResponseEntity<AllureResponseDTO>(allureResponseDTO, HttpStatus.OK);
	}

	@PostMapping(value = "/forgotPassVerityOtp", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AllureResponseDTO> forgetPasswordVerifyOtp(
			@RequestBody ForgetPasswordVerifyOtpDTO forgetPasswordVerifyOtpDTO) {

		ForgetPasswordResponseDTO forgetPasswordVerifyOtpResponseDTO = userService
				.forgotPasswordVerifyOtp(forgetPasswordVerifyOtpDTO);
		AllureResponseDTO allureResponseDTO = new AllureResponseDTO<ForgetPasswordResponseDTO>(
				forgetPasswordVerifyOtpResponseDTO, AllureResponseStatus.SUCCESS.getCode(),
				messageSource.getMessage("otp.verify.success", null, Locale.ENGLISH));

		return new ResponseEntity<AllureResponseDTO>(allureResponseDTO, HttpStatus.OK);
	}

	@PostMapping(value = "/forgotPassChange", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AllureResponseDTO> forgetPasswordChange(
			@RequestBody ForgetPasswordChangeDTO forgetPasswordChangeDTO) {

		LoginResponseDTO loginResponseDTO = userService.forgotPasswordChange(forgetPasswordChangeDTO);
		AllureResponseDTO allureResponseDTO = new AllureResponseDTO<LoginResponseDTO>(loginResponseDTO,
				AllureResponseStatus.SUCCESS.getCode(),
				messageSource.getMessage("password.change.success", null, Locale.ENGLISH));

		return new ResponseEntity<AllureResponseDTO>(allureResponseDTO, HttpStatus.OK);
	}

}
