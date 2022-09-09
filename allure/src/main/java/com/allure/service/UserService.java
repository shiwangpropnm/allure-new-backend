package com.allure.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.multipart.MultipartFile;

import com.allure.constants.DistanceUnit;
import com.allure.constants.DpUploadType;
import com.allure.dto.LoggedInUserDTO;
import com.allure.dto.UserAuthenticationPrincipalDTO;
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
import com.allure.entity.UserDevices;
import com.allure.entity.UserPassions;
import com.allure.entity.UserSetting;
import com.allure.entity.UserSexualOrientations;
import com.allure.entity.Users;

public interface UserService extends UserDetailsService {

	public String[] getDpUrls(int userId);

	public String getUserDpUrlForNotification(int userId);

	Double getDistanceBetweenUsers(Users user1, Users user2, DistanceUnit distanceUnit);

	Users getUserByUserId(int userId);

	public GetUsernameResponseDTO getUsername(LoggedInUserDTO loggedInUserDTO);

	public GetUsernameResponseDTO updateUsername(LoggedInUserDTO loggedInUserDTO, UpdateUsernameDTO updateUsernameDTO);

	public GetShowMeResponseDTO getShowMe(LoggedInUserDTO loggedInUserDTO);

	public GetShowMeResponseDTO updateShowMe(LoggedInUserDTO loggedInUserDTO, UpdateShowMeDTO updateShowMeDTO);

	public GetDiscoverySettingResponseDTO getDiscoverySetting(LoggedInUserDTO loggedInUserDTO);

	public GetDiscoverySettingResponseDTO updateDiscoverySetting(LoggedInUserDTO loggedInUserDTO,
			UpdateDiscoverySettingDTO discoverySettingDTO);

	public GetUserSettingResponseDTO getUserSetting(LoggedInUserDTO loggedInUserDTO);

	public GetUserSettingResponseDTO updateUserSetting(LoggedInUserDTO loggedInUserDTO,
			UpdateUserSettingDTO updateUserSettingDTO);

	public void updateUserImages(int loggedInUserId, MultipartFile[] multipartFiles);

	public void updateUserImages(int loggedInUserId, MultipartFile[] multipartFiles, DpUploadType[] dpUploadTypes);

	public GetEmailSubscriptionResponseDTO getUserEmailSubscription(LoggedInUserDTO loggedInUserDTO);

	public GetEmailSubscriptionResponseDTO updateEmailNewMatchSubscription(LoggedInUserDTO loggedInUserDTO,
			UpdateSubscriptionStatusDTO updateSubscriptionStatusDTO);

	public GetEmailSubscriptionResponseDTO updateEmailNewMessageSubscription(LoggedInUserDTO loggedInUserDTO,
			UpdateSubscriptionStatusDTO updateSubscriptionStatusDTO);

	public GetEmailSubscriptionResponseDTO updateEmailPromotionSubscription(LoggedInUserDTO loggedInUserDTO,
			UpdateSubscriptionStatusDTO updateSubscriptionStatusDTO);

	public GetEmailSubscriptionResponseDTO updateEmailSubscriptionUnsubscribeAll(LoggedInUserDTO loggedInUserDTO);

	public List<UserDevices> getUserDevices(Integer userId);

	public void deleteUserDevice(UserDevices userDevice);

	public VerifyOtpResponseDTO updateEmail(LoggedInUserDTO loggedInUserDTO, UpdateEmailDTO updateEmailDTO);

	public LoginResponseDTO loginByEmail(LoginRequestDTO loginRequestDTO);

	public RegistrationResponseDTO registration(RegistrationRequestDTO registrationRequestDTO)
			throws UsernameNotFoundException;

	public VerifyOtpResponseDTO loginByMobile(LoginRequestDTO loginRequestDTO);

	public LoginResponseDTO verifyOTP(VerifyOtpDTO verifyOtpDTO);

	RegisterMobileResponseDTO registerMobile(RegisterMobileDTO registerMobileDTO);

	public VerifyOtpResponseDTO resendOTP(ResendOtpRequestDTO resendOtpDTO);

	public ForgetPasswordResponseDTO forgotPassword(ForgetPasswordDTO forgetPasswordDTO);

	public ForgetPasswordResponseDTO forgotPasswordVerifyOtp(ForgetPasswordVerifyOtpDTO forgetPasswordVerifyOtpDTO);

	public LoginResponseDTO forgotPasswordChange(ForgetPasswordChangeDTO forgetPasswordChangeDTO);

	public UserAuthenticationPrincipalDTO loadUserByUserId(Integer userId);

	public GetEmailSubscriptionResponseDTO updateEmailVerifyOTP(LoggedInUserDTO loggedInUserDTO,
			VerifyOtpDTO verifyOtpDTO);

	VerifyOtpResponseDTO updateMobile(LoggedInUserDTO loggedInUserDTO, UpdateMobileDTO updateMobileDTO);

	void updateMobileVerifyOTP(LoggedInUserDTO loggedInUserDTO, VerifyOtpDTO verifyOtpDTO);

	public List<UserSexualOrientations> getUserSexualOrientations(Users user);

	public List<UserPassions> getUserPassions(Users user);

	UserSetting getUserSettings(Users user);

	UserSetting getUserSettings(Integer userId);
	
	DistanceUnit getUserSettingDistanceUnit(Users user);

	public List<Users> getSwipingCardUsersForUser(Users loggedInUser, int page, int pageSize);

	public List<Users> getTopPicksUsersForUser(Users loggedInUser, int page, int pageSize);

	String getUserDpUrl(int userId, int imageNumber);

}
