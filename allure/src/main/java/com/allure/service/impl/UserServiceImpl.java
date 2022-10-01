package com.allure.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;
import com.twilio.rest.api.v2010.account.Message;
import javax.transaction.Transactional;

import org.apache.commons.io.FilenameUtils;
import org.apache.http.HttpResponse;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.allure.constants.DistanceUnit;
import com.allure.constants.DpUploadType;
import com.allure.constants.EmailType;
import com.allure.constants.Interests;
import com.allure.constants.LoginType;
import com.allure.constants.OTPVerificationType;
import com.allure.dao.EmailSubscriptionRepository;
import com.allure.dao.OtpVerificationRepository;
import com.allure.dao.PreferAddictionRepository;
import com.allure.dao.PreferBodyTypeRepository;
import com.allure.dao.PreferGenderRepository;
import com.allure.dao.PreferHairColorRepository;
import com.allure.dao.UserDevicesRepository;
import com.allure.dao.UserImagesRepository;
import com.allure.dao.UserPassionsRepository;
import com.allure.dao.UserRepository;
import com.allure.dao.UserSettingRepository;
import com.allure.dao.UserSexualOrientationsRepository;
import com.allure.dto.LoggedInUserDTO;
import com.allure.dto.UserAuthenticationPrincipalDTO;
import com.allure.dto.forgotpassword.ForgetPasswordChangeDTO;
import com.allure.dto.forgotpassword.ForgetPasswordDTO;
import com.allure.dto.forgotpassword.ForgetPasswordResponseDTO;
import com.allure.dto.forgotpassword.ForgetPasswordVerifyOtpDTO;
import com.allure.dto.login.LoginRequestDTO;
import com.allure.dto.login.LoginResponseDTO;
import com.allure.dto.login.ResendOtpRequestDTO;
import com.allure.dto.login.UserLoginResponseDTO;
import com.allure.dto.login.VerifyOtpDTO;
import com.allure.dto.login.VerifyOtpResponseDTO;
import com.allure.dto.registration.RegisterMobileDTO;
import com.allure.dto.registration.RegisterMobileResponseDTO;
import com.allure.dto.registration.RegistrationRequestDTO;
import com.allure.dto.registration.RegistrationResponseDTO;
import com.allure.dto.usersetting.GetDiscoverySettingDTO;
import com.allure.dto.usersetting.GetDiscoverySettingResponseDTO;
import com.allure.dto.usersetting.GetEmailSubscriptionResponseDTO;
import com.allure.dto.usersetting.GetShowMeResponseDTO;
import com.allure.dto.usersetting.GetUserSettingDTO;
import com.allure.dto.usersetting.GetUserSettingResponseDTO;
import com.allure.dto.usersetting.GetUsernameResponseDTO;
import com.allure.dto.usersetting.UpdateDiscoverySettingDTO;
import com.allure.dto.usersetting.UpdateEmailDTO;
import com.allure.dto.usersetting.UpdateMobileDTO;
import com.allure.dto.usersetting.UpdateShowMeDTO;
import com.allure.dto.usersetting.UpdateSubscriptionStatusDTO;
import com.allure.dto.usersetting.UpdateUserSettingDTO;
import com.allure.dto.usersetting.UpdateUsernameDTO;
import com.allure.entity.EmailSubscription;
import com.allure.entity.ListAddiction;
import com.allure.entity.ListBodyType;
import com.allure.entity.ListGender;
import com.allure.entity.ListHairColor;
import com.allure.entity.OtpVerification;
import com.allure.entity.PreferAddiction;
import com.allure.entity.PreferBodyType;
import com.allure.entity.PreferGender;
import com.allure.entity.PreferHairColor;
import com.allure.entity.UserDevices;
import com.allure.entity.UserImages;
import com.allure.entity.UserPassions;
import com.allure.entity.UserSetting;
import com.allure.entity.UserSexualOrientations;
import com.allure.entity.Users;
import com.allure.exception.InvalidDetailsException;
import com.allure.exception.InvalidOTPException;
import com.allure.exception.UserAlreadyExistException;
import com.allure.exception.UsernameAlreadyExistException;
import com.allure.mapper.EmailSubscriptionMapper;
import com.allure.mapper.PreferAddictionMapper;
import com.allure.mapper.PreferBodyTypeMapper;
import com.allure.mapper.PreferGenderMapper;
import com.allure.mapper.PreferHairColorMapper;
import com.allure.mapper.UserMapper;
import com.allure.mapper.UserSettingMapper;
import com.allure.model.SmsRequestModel;
import com.allure.service.CommonService;
import com.allure.service.EmailService;
import com.allure.service.ObjectStoreService;
import com.allure.service.UserService;
import com.allure.util.CommonUtils;
import com.allure.util.JwtTokenUtil;
import com.allure.util.SmsUtil;

import kong.unirest.Unirest;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserSettingRepository userSettingRepository;

	@Autowired
	UserDevicesRepository userDevicesRepository;

	@Autowired
	PreferAddictionRepository preferAddictionRepository;

	@Autowired
	PreferBodyTypeRepository preferBodyTypeRepository;

	@Autowired
	PreferHairColorRepository preferHairColorRepository;

	@Autowired
	PreferGenderRepository preferGenderRepository;

	@Autowired
	UserImagesRepository userImagesRepository;

	@Autowired
	UserSexualOrientationsRepository userSexualOrientationsRepository;

	@Autowired
	UserPassionsRepository userPassionsRepository;

	@Autowired
	PreferAddictionMapper preferAddictionMapper;

	@Autowired
	PreferGenderMapper preferGenderMapper;

	@Autowired
	PreferBodyTypeMapper preferBodyTypeMapper;

	@Autowired
	PreferHairColorMapper preferHairColorMapper;

	@Autowired
	UserSettingMapper userSettingMapper;

	@Autowired
	UserMapper userMapper;

	@Autowired
	EmailSubscriptionMapper emailSubscriptionMapper;

	@Autowired
	EmailSubscriptionRepository emailSubscriptionRepository;

	@Autowired
	MessageSource messageSource;

	@Autowired
	ObjectStoreService objectStoreService;

	@Autowired
	CommonService commonService;

	@Autowired
	UserRepository usersRepository;

	@Autowired
	OtpVerificationRepository otpVerificationRepository;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Lazy
	@Autowired
	EmailService emailService;

	@Value("${allure.allowed.dps.count}")
	private int allowedDpsCount;

	@Value("${allure.dp.path}")
	private String dpPath;

	@Value("${allure.default.dp.url}")
	private String defaultDpUrl;
	
	@Autowired
	SmsUtil sms;

	@Override
	public String[] getDpUrls(int loggedInUserId) {
		String[] dpUrls = new String[allowedDpsCount];
		List<UserImages> userImages = userImagesRepository.findByUserId(loggedInUserId);
		userImages.forEach(userImage -> {
			if (userImage.getValue() <= allowedDpsCount)
				dpUrls[userImage.getValue() - 1] = getUserDpUrl(loggedInUserId, userImage.getValue());
		});
		return dpUrls;
	}

	@Override
	public void updateUserImages(int loggedInUserId, MultipartFile[] multipartFiles) {
		List<UserImages> userImages = userImagesRepository.findByUserId(loggedInUserId);
		userImages.forEach(userImage -> {
			int value = userImage.getValue();
			MultipartFile file = multipartFiles[value - 1];
			String existingFileName = String.valueOf(loggedInUserId) + "-" + String.valueOf(value);
			if (file == null || file.isEmpty()) {
				userImagesRepository.delete(userImage);
			} else {
				if (FilenameUtils.getBaseName(file.getOriginalFilename()) != (existingFileName)
						&& file.getOriginalFilename().startsWith("temp")) {
					objectStoreService.uploadFile(dpPath, existingFileName, file, false);
					userImage.setCreatedAt(new Date());
					userImagesRepository.save(userImage);
				}
			}
			multipartFiles[value - 1] = null;
		});
		for (int i = 0; i < multipartFiles.length; i++) {
			if (multipartFiles[i] != null) {
				MultipartFile file = multipartFiles[i];
				addUserImage(loggedInUserId, i + 1, file);
			}
		}
	}

	@Override
	public void updateUserImages(int loggedInUserId, MultipartFile[] multipartFiles, DpUploadType[] dpUploadTypes) {
		List<UserImages> userImages = userImagesRepository.findByUserId(loggedInUserId);
		userImages.forEach(userImage -> {
			int value = userImage.getValue();
			MultipartFile file = multipartFiles[value - 1];
			DpUploadType dpUploadType = dpUploadTypes[value - 1];
			switch (dpUploadType) {
			case EMPTY:
				userImagesRepository.delete(userImage);
				break;
			case CHANGED:
				objectStoreService.uploadFile(dpPath, (String.valueOf(loggedInUserId) + "-" + String.valueOf(value)),
						file, false);
				userImage.setCreatedAt(new Date());
				userImagesRepository.save(userImage);
				break;
			default:
				break;
			}
			multipartFiles[value - 1] = null;
		});
		for (int i = 0; i < multipartFiles.length; i++) {
			if (multipartFiles[i] != null) {
				MultipartFile file = multipartFiles[i];
				addUserImage(loggedInUserId, i + 1, file);
			}
		}
	}

	public void addUserImage(int loggedInUserId, int value, MultipartFile file) {
		if (!file.isEmpty()) {
			String fileName = String.valueOf(loggedInUserId) + "-" + String.valueOf(value);
			UserImages userImage = new UserImages();
			userImage.setUserId(loggedInUserId);
			userImage.setValue(value);
			userImage.setObjectKey(dpPath + fileName);
			userImage.setCreatedAt(new Date());
			objectStoreService.uploadFile(dpPath, fileName, file, false);
			userImagesRepository.save(userImage);
		}
	}

	@Override
	public Double getDistanceBetweenUsers(Users user1, Users user2, DistanceUnit distanceUnit) {

		if ((user1.getLocLattitude() != null && user1.getLocLongitude() != null)
				&& (user2.getLocLattitude() != null && user2.getLocLongitude() != null)) {
			return CommonUtils.getDistanceBetweenTwoLocations(user1.getLocLattitude(), user1.getLocLongitude(),
					user2.getLocLattitude(), user2.getLocLongitude(), distanceUnit);
		}
		return null;
	}

	@Override
	public Users getUserByUserId(int userId) {
		Users loggedInUser = userRepository.findByIdAndIsRegisteredAndAccStatus(userId, true, true);
		if (loggedInUser == null)
			throw new InvalidDetailsException(
					messageSource.getMessage("exception.invalid.user.details", null, Locale.ENGLISH));
		return loggedInUser;
	}

	@Override
	public GetUsernameResponseDTO getUsername(LoggedInUserDTO loggedInUserDTO) {
		return new GetUsernameResponseDTO(getUserByUserId(loggedInUserDTO.getLoggedInUserId()).getUsername());
	}

	@Override
	public GetUsernameResponseDTO updateUsername(LoggedInUserDTO loggedInUserDTO, UpdateUsernameDTO updateUsernameDTO) {
		Users existingUserWithUsername = userRepository.findByUsername(updateUsernameDTO.getUsername());
		if (existingUserWithUsername != null) {
			throw new UsernameAlreadyExistException(
					messageSource.getMessage("exception.username.already.exists", null, Locale.ENGLISH));
		}
		Users loggedInUser = getUserByUserId(loggedInUserDTO.getLoggedInUserId());
		loggedInUser.setUsername(updateUsernameDTO.getUsername());
		userRepository.save(loggedInUser);
		return getUsername(loggedInUserDTO);
	}

	@Override
	public GetShowMeResponseDTO getShowMe(LoggedInUserDTO loggedInUserDTO) {
		UserSetting userSetting = getUserSettings(loggedInUserDTO.getLoggedInUserId());
		ListGender gender = commonService.getGenderById(userSetting.getShowMe() * 1);
		return new GetShowMeResponseDTO(userSetting != null ? userSetting.getShowMe() : null,
				gender != null ? gender.getValue() : "");
	}

	@Override
	public UserSetting getUserSettings(Integer userId) {
		UserSetting userSetting = userSettingRepository.findByUserId(userId);
		return userSetting;
	}

	@Override
	public GetShowMeResponseDTO updateShowMe(LoggedInUserDTO loggedInUserDTO, UpdateShowMeDTO updateShowMeDTO) {
		UserSetting userSetting = getUserSettings(loggedInUserDTO.getLoggedInUserId());
		if (userSetting == null) {
			userSetting = new UserSetting();
		}
		userSetting.setShowMe(updateShowMeDTO.getShowMe());
		userSettingRepository.save(userSetting);
		return getShowMe(loggedInUserDTO);
	}

	@Override
	public GetDiscoverySettingResponseDTO getDiscoverySetting(LoggedInUserDTO loggedInUserDTO) {
		int userId = loggedInUserDTO.getLoggedInUserId();
		UserSetting userSetting = userSettingRepository.findByUserId(userId);
		return getDiscoverySettingResponseDTOFromUserSetting(userSetting, userId);
	}

	private GetDiscoverySettingResponseDTO getDiscoverySettingResponseDTOFromUserSetting(UserSetting userSetting,
			int userId) {
		Integer userPreferCleanMonths = userSetting != null ? userSetting.getPreferCleanMonths() : null;
		List<PreferAddiction> userPreferAddictions = preferAddictionRepository.findByUserId(userId);
		List<PreferBodyType> userPreferBodyTypes = preferBodyTypeRepository.findByUserId(userId);
		List<PreferHairColor> userPreferHairColors = preferHairColorRepository.findByUserId(userId);
		List<PreferGender> userPreferGenders = preferGenderRepository.findByUserId(userId);
		List<ListBodyType> allBodyTypes = commonService.getBodyTypeEntityList();
		List<ListHairColor> allHairColors = commonService.getHairColorEntityList();
		List<ListGender> allGenders = commonService.getGenderEntityList();
		List<ListAddiction> allAddictions = commonService.getAddictionEntityList();

		GetDiscoverySettingDTO discoverySettingDTO = GetDiscoverySettingDTO.builder()
				.hairColor(preferHairColorMapper
						.preferHairColorsAndAllHairColorsToIdValueSelectionList(userPreferHairColors, allHairColors))
				.bodyType(preferBodyTypeMapper.preferBodyTypesAndAllBodyTypesToIdValueSelectionList(userPreferBodyTypes,
						allBodyTypes))
				.addiction(preferAddictionMapper
						.preferAddictionsAndAllAddictionsToIdValueSelectionList(userPreferAddictions, allAddictions))
				.gender(preferGenderMapper.preferGendersAndAllGendersToIdValueSelectionList(userPreferGenders,
						allGenders))
				.cleanMonth(userPreferCleanMonths != null ? userPreferCleanMonths % 12 : null)
				.cleanYear(userPreferCleanMonths != null ? userPreferCleanMonths / 12 : null).build();
		return new GetDiscoverySettingResponseDTO(discoverySettingDTO);

	}

	@Override
	@Transactional
	public GetDiscoverySettingResponseDTO updateDiscoverySetting(LoggedInUserDTO loggedInUserDTO,
			UpdateDiscoverySettingDTO discoverySettingDTO) {
		int userId = loggedInUserDTO.getLoggedInUserId();
		UserSetting userSetting = userSettingRepository.findByUserId(userId);
		if (userSetting == null) {
			userSetting = new UserSetting();
			userSetting.setUsers(userMapper.idToUsersNonEntity(userId));
			userSetting.setCreatedAt(new Date());
		}
		userSetting.setPreferCleanMonths(discoverySettingDTO.getCleanYear() * 12 + discoverySettingDTO.getCleanMonth());
		userSettingRepository.save(userSetting);
		updatePreferAddiction(userId, discoverySettingDTO.getAddiction());
		updatePreferBodyType(userId, discoverySettingDTO.getBodyType());
		updatePreferHairColor(userId, discoverySettingDTO.getHairColor());
		updatePreferGender(userId, discoverySettingDTO.getGender());
		return getDiscoverySettingResponseDTOFromUserSetting(userSetting, userId);
	}

	private void updatePreferGender(int userId, List<Integer> newPreferGenders) {
		List<PreferGender> existingUserPreferGenders = preferGenderRepository.findByUserId(userId);
		// Update existingUserPreferGenders => delete from database if old not in
		// newPreferGenders and update the newPreferGenders accordingly
		existingUserPreferGenders.forEach(preferGender -> {
			if (!newPreferGenders.contains(preferGender.getGenderId())) {
				preferGenderRepository.delete(preferGender);
			}
			newPreferGenders.remove(new Integer(preferGender.getGenderId()));
		});
		// Insert remaining newPreferGenders
		newPreferGenders.forEach(genderId -> {
			PreferGender preferGender = new PreferGender();
			preferGender.setUserId(userId);
			preferGender.setGenderId(genderId);
			preferGenderRepository.save(preferGender);
		});
	}

	private void updatePreferBodyType(int userId, List<Integer> newPreferbodyTypes) {
		List<PreferBodyType> existingUserPreferBodyTypes = preferBodyTypeRepository.findByUserId(userId);
		// Update existingUserPreferBodyTypes => delete from database if old not in
		// newPreferbodyTypes and update the newPreferBodyTypes accordingly
		existingUserPreferBodyTypes.forEach(preferBodyType -> {
			if (!newPreferbodyTypes.contains(preferBodyType.getBodyTypeId())) {
				preferBodyTypeRepository.delete(preferBodyType);
			}
			newPreferbodyTypes.remove(new Integer(preferBodyType.getBodyTypeId()));
		});
		// Insert remaining newPreferbodyTypes
		newPreferbodyTypes.forEach(bodyTypeId -> {
			PreferBodyType preferBodyType = new PreferBodyType();
			preferBodyType.setUserId(userId);
			preferBodyType.setBodyTypeId(bodyTypeId);
			preferBodyTypeRepository.save(preferBodyType);
		});
	}

	private void updatePreferHairColor(int userId, List<Integer> newPreferHairColors) {
		List<PreferHairColor> existingUserPreferHairColors = preferHairColorRepository.findByUserId(userId);
		// Update existingUserPreferHairColors => delete from database if old not in
		// newPreferHairColors and update the newPreferHairColors accordingly
		existingUserPreferHairColors.forEach(preferHairColor -> {
			if (!newPreferHairColors.contains(preferHairColor.getHairColorId())) {
				preferHairColorRepository.delete(preferHairColor);
			}
			newPreferHairColors.remove(new Integer(preferHairColor.getHairColorId()));
		});
		// Insert remaining newPreferHairColors
		newPreferHairColors.forEach(hairColorId -> {
			PreferHairColor preferHairColor = new PreferHairColor();
			preferHairColor.setUserId(userId);
			preferHairColor.setHairColorId(hairColorId);
			preferHairColorRepository.save(preferHairColor);
		});

	}

	private void updatePreferAddiction(int userId, List<Integer> newPreferAddictions) {
		List<PreferAddiction> existingUserPreferAddictions = preferAddictionRepository.findByUserId(userId);
		// Update existingUserPreferAddictions => delete from database if old not in
		// newPreferAddictions and update the newPreferAddictions accordingly
		existingUserPreferAddictions.forEach(preferAddiction -> {
			if (!newPreferAddictions.contains(preferAddiction.getAddictionId())) {
				preferAddictionRepository.delete(preferAddiction);
			}
			newPreferAddictions.remove(new Integer(preferAddiction.getAddictionId()));
		});
		// Insert remaining newPreferHairColors
		newPreferAddictions.forEach(addictionId -> {
			PreferAddiction preferAddiction = new PreferAddiction();
			preferAddiction.setUserId(userId);
			preferAddiction.setAddictionId(addictionId);
			preferAddictionRepository.save(preferAddiction);
		});
	}

	@SuppressWarnings("unused")
	private Date getDiscoverySettingUserCleanDate(Date existingUserCleanDate,
			UpdateDiscoverySettingDTO updateDiscoverySettingDTO) {
		if (updateDiscoverySettingDTO.getCleanYear() > 0 && updateDiscoverySettingDTO.getCleanMonth() > 0) {
			Calendar cleanDate = Calendar.getInstance();
			cleanDate.setTime(existingUserCleanDate);
			cleanDate.set(Calendar.YEAR, updateDiscoverySettingDTO.getCleanYear());
			cleanDate.set(Calendar.MONTH, updateDiscoverySettingDTO.getCleanMonth());
			return cleanDate.getTime();
		}
		return null;
	}

	@Override
	public GetUserSettingResponseDTO getUserSetting(LoggedInUserDTO loggedInUserDTO) {
		Users user = getUserByUserId(loggedInUserDTO.getLoggedInUserId());
		UserSetting userSetting = getUserSettings(user);
		return getUserSettingResponseDTOFromUserSetting(userSetting, user);
	}

	@Override
	public UserSetting getUserSettings(Users user) {
		UserSetting userSetting = userSettingRepository.findByUsers(user);
		return userSetting;
	}

	private GetUserSettingResponseDTO getUserSettingResponseDTOFromUserSetting(UserSetting userSetting, Users user) {
		GetUserSettingDTO getUserSettingDTO = userSettingMapper.userSettingToGetUserSettingDTO(userSetting);
		if (getUserSettingDTO == null) {
			getUserSettingDTO = new GetUserSettingDTO();
			getUserSettingDTO.setUserId(user.getId());
		}
		getUserSettingDTO.setPhone(user.getMobile());
		ListGender gender = null;
		if (userSetting != null && userSetting.getShowMe() != null) {
			gender = commonService.getGenderById(userSetting.getShowMe() * 1);
		}
		getUserSettingDTO.setShowMeValue(gender != null ? gender.getValue() : "");

		return new GetUserSettingResponseDTO(getUserSettingDTO);
	}

	@Override
	public GetUserSettingResponseDTO updateUserSetting(LoggedInUserDTO loggedInUserDTO,
			UpdateUserSettingDTO updateUserSettingDTO) {
		Users user = getUserByUserId(loggedInUserDTO.getLoggedInUserId());
		UserSetting userSetting = getUserSettings(user);
		if (userSetting == null) {
			userSetting = new UserSetting();
			userSetting.setUsers(user);
		}
		userSetting = userSettingMapper.updateUserSettingDTOToUserSetting(updateUserSettingDTO, userSetting);
		userSettingRepository.save(userSetting);
		return getUserSettingResponseDTOFromUserSetting(userSetting, user);
	}

	@Override
	public GetEmailSubscriptionResponseDTO getUserEmailSubscription(LoggedInUserDTO loggedInUserDTO) {
		Users user = getUserByUserId(loggedInUserDTO.getLoggedInUserId());
		return getEmailSubscriptionResponseDTO(user);
	}

	private GetEmailSubscriptionResponseDTO getEmailSubscriptionResponseDTO(Users user) {
		EmailSubscription emailSubscription = emailSubscriptionRepository.findByUsers(user);
		if (emailSubscription == null) { // if in case there doesnot exists the email subscription for user then first
											// get will set it to default
			setDefaultEmailSubscription(user);
		}
		return new GetEmailSubscriptionResponseDTO(
				emailSubscriptionMapper.emailSubscriptionAndUserToGetEmailSubscriptionDTO(emailSubscription, user));
	}

	@Override
	public GetEmailSubscriptionResponseDTO updateEmailNewMatchSubscription(LoggedInUserDTO loggedInUserDTO,
			UpdateSubscriptionStatusDTO updateSubscriptionStatusDTO) {
		Users user = getUserByUserId(loggedInUserDTO.getLoggedInUserId());
		EmailSubscription emailSubscription = emailSubscriptionRepository.findByUsers(user);
		emailSubscription.setNewMatches(updateSubscriptionStatusDTO.getSubscriptionStatus());
		emailSubscriptionRepository.save(emailSubscription);
		return new GetEmailSubscriptionResponseDTO(
				emailSubscriptionMapper.emailSubscriptionAndUserToGetEmailSubscriptionDTO(emailSubscription, user));
	}

	@Override
	public GetEmailSubscriptionResponseDTO updateEmailNewMessageSubscription(LoggedInUserDTO loggedInUserDTO,
			UpdateSubscriptionStatusDTO updateSubscriptionStatusDTO) {
		Users user = getUserByUserId(loggedInUserDTO.getLoggedInUserId());
		EmailSubscription emailSubscription = emailSubscriptionRepository.findByUsers(user);
		emailSubscription.setNewMessages(updateSubscriptionStatusDTO.getSubscriptionStatus());
		emailSubscriptionRepository.save(emailSubscription);
		return new GetEmailSubscriptionResponseDTO(
				emailSubscriptionMapper.emailSubscriptionAndUserToGetEmailSubscriptionDTO(emailSubscription, user));
	}

	@Override
	public GetEmailSubscriptionResponseDTO updateEmailPromotionSubscription(LoggedInUserDTO loggedInUserDTO,
			UpdateSubscriptionStatusDTO updateSubscriptionStatusDTO) {
		Users user = getUserByUserId(loggedInUserDTO.getLoggedInUserId());
		EmailSubscription emailSubscription = emailSubscriptionRepository.findByUsers(user);
		emailSubscription.setPromotions(updateSubscriptionStatusDTO.getSubscriptionStatus());
		emailSubscriptionRepository.save(emailSubscription);
		return new GetEmailSubscriptionResponseDTO(
				emailSubscriptionMapper.emailSubscriptionAndUserToGetEmailSubscriptionDTO(emailSubscription, user));
	}

	@Override
	public GetEmailSubscriptionResponseDTO updateEmailSubscriptionUnsubscribeAll(LoggedInUserDTO loggedInUserDTO) {
		Users user = getUserByUserId(loggedInUserDTO.getLoggedInUserId());
		EmailSubscription emailSubscription = emailSubscriptionRepository.findByUsers(user);
		emailSubscription.setNewMatches((byte) 0);
		emailSubscription.setNewMessages((byte) 0);
		emailSubscription.setPromotions((byte) 0);
		emailSubscriptionRepository.save(emailSubscription);
		return new GetEmailSubscriptionResponseDTO(
				emailSubscriptionMapper.emailSubscriptionAndUserToGetEmailSubscriptionDTO(emailSubscription, user));
	}

	private void setDefaultEmailSubscription(Users users) {
		EmailSubscription emailSubscription = new EmailSubscription();
		emailSubscription.setUsers(users);
		emailSubscription.setNewMatches((byte) 1);
		emailSubscription.setNewMessages((byte) 1);
		emailSubscription.setPromotions((byte) 1);
		emailSubscriptionRepository.save(emailSubscription);
	}

	@Override
	public String getUserDpUrlForNotification(int userId) {
		String[] dpUrls = getDpUrls(userId);
		for (int i = 0; i < dpUrls.length; i++) {
			if (dpUrls[i] != null)
				return dpUrls[i];
		}
		return defaultDpUrl;
	}

	@Override
	public String getUserDpUrl(int userId, int imageNumber) {
		return objectStoreService.getBlobUrl(dpPath, String.valueOf(userId) + "-" + String.valueOf(imageNumber));
	}

	@Override
	public List<UserDevices> getUserDevices(Integer userId) {
		return userDevicesRepository.findByUserId(userId);
	}

	@Override
	public void deleteUserDevice(UserDevices userDevice) {
		userDevicesRepository.delete(userDevice);
	}

	@Override
	public VerifyOtpResponseDTO updateEmail(LoggedInUserDTO loggedInUserDTO, UpdateEmailDTO updateEmailDTO) {
		Users existingUser = usersRepository.findByEmailAndIsRegistered(updateEmailDTO.getNewEmail(), true);
		if (existingUser != null) {
			throw new UserAlreadyExistException(
					messageSource.getMessage("exception.user.email.already.exists", null, Locale.ENGLISH));
		}

		Users user = getUserByUserId(loggedInUserDTO.getLoggedInUserId());
		int otp = CommonUtils.generateOTP();
		OtpVerification otpVerification = initializeOtpVerification(user, otp,
				OTPVerificationType.UPDATE_EMAIL.getType(), updateEmailDTO.getNewEmail());
		emailService.sendUpdateEmailMail(user.getId(), updateEmailDTO.getNewEmail(), user.getEmail(), otp);
		return new VerifyOtpResponseDTO(otpVerification.getId(), otp, true, null);
	}

	@Override
	public GetEmailSubscriptionResponseDTO updateEmailVerifyOTP(LoggedInUserDTO loggedInUserDTO,
			VerifyOtpDTO verifyOtpDTO) {
		Users user = getUserByUserId(loggedInUserDTO.getLoggedInUserId());
		OtpVerification otpVerification = verifyRegisteredUserOtp(verifyOtpDTO.getOtp(), verifyOtpDTO.getId());
		user.setEmail(otpVerification.getValue());
		userRepository.save(user);
		return getEmailSubscriptionResponseDTO(user);
	}

	@Override
	public VerifyOtpResponseDTO updateMobile(LoggedInUserDTO loggedInUserDTO, UpdateMobileDTO updateMobileDTO) {
		Users existingUser = usersRepository.findByMobile(updateMobileDTO.getNewMobile());
		if (existingUser != null) {
			throw new UserAlreadyExistException(
					messageSource.getMessage("exception.user.already.exists", null, Locale.ENGLISH));
		}

		Users user = getUserByUserId(loggedInUserDTO.getLoggedInUserId());
		int otp = CommonUtils.generateOTP();
		OtpVerification otpVerification = initializeOtpVerification(user, otp,
				OTPVerificationType.UPDATE_MOBILE.getType(), updateMobileDTO.getNewMobile());
		return new VerifyOtpResponseDTO(otpVerification.getId(), otp, true, null);
	}

	@Override
	public void updateMobileVerifyOTP(LoggedInUserDTO loggedInUserDTO, VerifyOtpDTO verifyOtpDTO) {
		Users user = getUserByUserId(loggedInUserDTO.getLoggedInUserId());
		OtpVerification otpVerification = verifyRegisteredUserOtp(verifyOtpDTO.getOtp(), verifyOtpDTO.getId());
		user.setMobile(otpVerification.getValue());
		userRepository.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user = usersRepository.findByEmailAndIsRegistered(username, true);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		UserAuthenticationPrincipalDTO userAuthenticationPrincipalDTO = Mappers.getMapper(UserMapper.class)
				.userToUserAuthenticationDTO(user);
		return userAuthenticationPrincipalDTO;
	}

	// @Override
	// public UserAuthenticationPrincipalDTO loadUserByUserId(Integer userId) {
	// Users user = usersRepository.findByIdAndIsRegisteredAndAccStatus(userId,
	// true, false);
	// if (user == null) {
	// throw new UsernameNotFoundException("User not found with userid: " + userId);
	// }
	// UserAuthenticationPrincipalDTO userAuthenticationPrincipalDTO =
	// Mappers.getMapper(UserMapper.class)
	// .userToUserAuthenticationDTO(user);
	// return userAuthenticationPrincipalDTO;
	// }

	@Override
	public UserAuthenticationPrincipalDTO loadUserByUserId(Integer userId) {
		Users user = usersRepository.findByIdAndIsRegisteredAndAccStatus(userId, true, true);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with userid: " + userId);
		}
		UserAuthenticationPrincipalDTO userAuthenticationPrincipalDTO = Mappers.getMapper(UserMapper.class)
				.userToUserAuthenticationDTO(user);
		return userAuthenticationPrincipalDTO;
	}

	@Override
	public LoginResponseDTO loginByEmail(LoginRequestDTO loginRequestDTO) {
		String userName = loginRequestDTO.getEmail();
		String password = loginRequestDTO.getPassword();
		authenticate(userName, password);
		Users user = usersRepository.findByEmailAndIsRegistered(userName, true);
		if (user == null)
			throw new InvalidDetailsException(
					messageSource.getMessage("exception.invalid.user.details", null, Locale.ENGLISH));

		return getLoginResponseDTO(user);
	}

	private void authenticate(String username, String password) {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new DisabledException("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new BadCredentialsException("INVALID_CREDENTIALS", e);
		}
	}

	@Override
	public RegistrationResponseDTO registration(RegistrationRequestDTO registrationRequestDTO)
			throws UsernameNotFoundException {
		String email = registrationRequestDTO.getEmail();
		String password = registrationRequestDTO.getPassword();
		Users user = usersRepository.findByEmailAndIsRegistered(email, true);
		if (user != null) {
			throw new UserAlreadyExistException(
					messageSource.getMessage("exception.user.email.already.exists", null, Locale.ENGLISH));
		}
		user = new Users();
		user.setEmail(email);
		user.setPassword(passwordEncoder.encode(password));
		user.setMobile("");
		user.setGoogleId("");
		user.setFacebookId("");
		user.setOtpVerified(true);
		user.setOtp(0);
		user.setLoginType(LoginType.EMAIL.getValue());
		user.setAccStatus(true);
		user.setCreatedAt(new Date());
		user.setLastActivity(new Date());
		user.setInterest(Interests.all.getValue());
		usersRepository.save(user);
		return new RegistrationResponseDTO(user.getId(), false);
	}

	@Override
	public RegisterMobileResponseDTO registerMobile(RegisterMobileDTO registerMobileDTO) {
		String mobile = registerMobileDTO.getMobile();
		Users user = usersRepository.findByMobile(mobile);
		if (user != null) {
			throw new UserAlreadyExistException(
					messageSource.getMessage("exception.user.already.exists", null, Locale.ENGLISH));
		}

		int id = registerMobileDTO.getId();
		try {
			user = usersRepository.findById(id).get();
		} catch (NoSuchElementException e) {
			throw new InvalidDetailsException(
					messageSource.getMessage("exception.invalid.user.details", null, Locale.ENGLISH));
		}

		int otp = CommonUtils.generateOTP();
		SmsRequestModel sm=new SmsRequestModel();
		sm.setMobileNumber(mobile);
		sm.setMessage(EmailType.SMS_MESSAGE.getType().concat(Integer.toString(otp)));
		Message msg=sms.sendSms(sm);
		user.setMobile(mobile);
		user.setOtp(otp);
		user.setOtpVerified(false);
		usersRepository.save(user);
		return new RegisterMobileResponseDTO(user.getId(), otp);
	}

	@Override
	public VerifyOtpResponseDTO loginByMobile(LoginRequestDTO loginRequestDTO) {
		String phoneNumber = loginRequestDTO.getPhoneNumber();
		Users user = usersRepository.findByMobile(phoneNumber);
		if (user == null)
			throw new InvalidDetailsException(
					messageSource.getMessage("exception.invalid.user.details", null, Locale.ENGLISH));
		int otp = CommonUtils.generateOTP();
		int verifyOTPId = -1;
		boolean isExistingUser = true;
		if (user.isRegistered()) {
			if (isUserAccStatusActive(user)) {
				user.setAccStatus(true);
				user.setInactiveTill(null);
				OtpVerification otpVerification = initializeOtpVerification(user, otp,
						OTPVerificationType.LOGIN_MOBILE.getType(), user.getMobile());
				verifyOTPId = otpVerification.getId();
			} else {
				throw new InvalidDetailsException(
						messageSource.getMessage("exception.invalid.user.details", null, Locale.ENGLISH));
			}
		} else {
			user.setOtp(otp);
			user.setOtpVerified(false);
			usersRepository.save(user);
			isExistingUser = false;
		}

		return new VerifyOtpResponseDTO(verifyOTPId, otp, isExistingUser, null);
	}

	private boolean isUserAccStatusActive(Users user) {
		return user.isAccStatus() || (user.getInactiveTill() != null && user.getInactiveTill().before(new Date()));
	}

	private OtpVerification initializeOtpVerification(Users user, int otp, byte type, String value) {
		OtpVerification otpVerification = otpVerificationRepository.findByUsersAndOtpAndType(user, otp, type);
		if (otpVerification == null) {
			otpVerification = new OtpVerification();
			otpVerification.setUsers(user);
			otpVerification.setOtp(otp);
			otpVerification.setOtpAttempts((byte) 0);
			otpVerification.setType(type);
		}
		otpVerification.setValue(value);
		otpVerification.setStatus((byte) 0);
		otpVerification.setCreatedAt(new Date());

		otpVerificationRepository.save(otpVerification);
		return otpVerification;
	}

	@Override
	public LoginResponseDTO verifyOTP(VerifyOtpDTO verifyOtpDTO) {
		int id = verifyOtpDTO.getId();
		boolean isExistingUser = verifyOtpDTO.isExistingUser();
		Users user = null;
		if (!isExistingUser) { // Existing user => verify otp for register case
			try {
				user = usersRepository.findById(id).get();
			} catch (NoSuchElementException e) {
				throw new InvalidDetailsException(
						messageSource.getMessage("exception.invalid.user.details", null, Locale.ENGLISH));
			}
			if (verifyOtpDTO.getOtp() != user.getOtp()) {
				throw new InvalidOTPException(messageSource.getMessage("exception.invalid.otp", null, Locale.ENGLISH));
			}
			user.setOtpVerified(true);
			user.setRegistered(true);
			usersRepository.save(user);
			setDefaultEmailSubscription(user);
		} else {
			OtpVerification otpVerification = verifyRegisteredUserOtp(verifyOtpDTO.getOtp(), id);
			user = otpVerification.getUsers();
		}

		return getLoginResponseDTO(user);
	}

	private OtpVerification verifyRegisteredUserOtp(int otp, int id) {
		OtpVerification otpVerification = getOtpVerification(id);
		otpVerification.setOtpAttempts((byte) (otpVerification.getOtpAttempts() + 1));
		boolean isOtpVerified = otp == otpVerification.getOtp();
		if (isOtpVerified) {
			otpVerification.setStatus((byte) 1);
		}
		otpVerificationRepository.save(otpVerification);
		if (!isOtpVerified) {
			throw new InvalidOTPException(messageSource.getMessage("exception.invalid.otp", null, Locale.ENGLISH));
		}
		return otpVerification;
	}

	private OtpVerification getOtpVerification(int id) {
		OtpVerification otpVerification = otpVerificationRepository.findById(id)
				.<InvalidOTPException>orElseThrow(() -> {
					throw new InvalidOTPException(
							messageSource.getMessage("exception.invalid.otp", null, Locale.ENGLISH));
				});
		return otpVerification;
	}

	@Override
	public VerifyOtpResponseDTO resendOTP(ResendOtpRequestDTO resendOtpDTO) {
		int id = resendOtpDTO.getId();
		int otp = CommonUtils.generateOTP();
		int verifyOTPId = -1;
		if (resendOtpDTO.isExistingUser()) {
			OtpVerification prevOtpVerification = getOtpVerification(id);
			OtpVerification otpVerification = initializeOtpVerification(prevOtpVerification.getUsers(), otp,
					prevOtpVerification.getType(), prevOtpVerification.getValue());
			verifyOTPId = otpVerification.getId();
		} else {
			Users user = null;
			try {
				user = usersRepository.findById(id).get();
			} catch (NoSuchElementException e) {
				throw new InvalidDetailsException(
						messageSource.getMessage("exception.invalid.user.details", null, Locale.ENGLISH));
			}
			SmsRequestModel sm=new SmsRequestModel();
			sm.setMobileNumber(user.getMobile());
			sm.setMessage(EmailType.SMS_MESSAGE.getType().concat(Integer.toString(otp)));
			Message msg=sms.sendSms(sm);
			user.setOtp(otp);
			user.setOtpVerified(false);
			usersRepository.save(user);
			verifyOTPId = user.getId();
		}

		return new VerifyOtpResponseDTO(verifyOTPId, otp, resendOtpDTO.isExistingUser(), verifyOTPId);
	}

	@Override
	public ForgetPasswordResponseDTO forgotPassword(ForgetPasswordDTO forgetPasswordDTO) {
		String email = forgetPasswordDTO.getEmail();
		Users user = usersRepository.findByEmailAndIsRegisteredAndAccStatus(email, true, true);
		if (user == null)
			throw new InvalidDetailsException(
					messageSource.getMessage("exception.invalid.user.details", null, Locale.ENGLISH));
		int otp = CommonUtils.generateOTP();
		OtpVerification otpVerification = initializeOtpVerification(user, otp,
				OTPVerificationType.FORGOT_PASSWORD_EMAIL.getType(), user.getEmail());
		ForgetPasswordResponseDTO forgetPasswordResponseDTO = new ForgetPasswordResponseDTO();
		forgetPasswordResponseDTO.setTempId(otpVerification.getId());
		emailService.sendForgotPasswordOTPMail(user.getId(), user.getEmail(), otp);
		return forgetPasswordResponseDTO;
	}

	@Override
	public ForgetPasswordResponseDTO forgotPasswordVerifyOtp(ForgetPasswordVerifyOtpDTO forgetPasswordVerifyOtpDTO) {
		OtpVerification otpVerification = verifyRegisteredUserOtp(forgetPasswordVerifyOtpDTO.getOtp(),
				forgetPasswordVerifyOtpDTO.getTempId());

		ForgetPasswordResponseDTO forgetPasswordResponseDTO = new ForgetPasswordResponseDTO();
		forgetPasswordResponseDTO.setTempId(otpVerification.getUsers().getId()); // will be passed to
																					// forgotPasswordChange request
		return forgetPasswordResponseDTO;
	}

	@Override
	public LoginResponseDTO forgotPasswordChange(ForgetPasswordChangeDTO forgetPasswordChangeDTO) {

		int id = forgetPasswordChangeDTO.getTempId();
		Users user = null;
		try {
			user = usersRepository.findById(id).get();
		} catch (NoSuchElementException e) {
			throw new InvalidDetailsException(
					messageSource.getMessage("exception.invalid.user.details", null, Locale.ENGLISH));
		}
		user.setPassword(bcryptEncoder.encode(forgetPasswordChangeDTO.getNewPassword()));
		usersRepository.save(user);

		return getLoginResponseDTO(user);

	}

	private LoginResponseDTO getLoginResponseDTO(Users user) {
		UserLoginResponseDTO userLoginResponseDTO = Mappers.getMapper(UserMapper.class)
				.userToUserLoginResponseDTO(user);
		userLoginResponseDTO.setExistingUser(true);
		userLoginResponseDTO.setToken(jwtTokenUtil.generateToken(user.getId()));
		return new LoginResponseDTO(userLoginResponseDTO);
	}

	@Override
	public List<UserSexualOrientations> getUserSexualOrientations(Users user) {
		return userSexualOrientationsRepository.findAllByUsers(user);
	}

	@Override
	public List<UserPassions> getUserPassions(Users user) {
		return userPassionsRepository.findAllByUsers(user);
	}

	@Override
	public DistanceUnit getUserSettingDistanceUnit(Users user) {
		UserSetting userSetting = getUserSettings(user);

		return (userSetting != null && userSetting.getDistanceUnit() != null) ? DistanceUnit.valueOf(userSetting.getDistanceUnit()) : DistanceUnit.KILOMETERS;

	}

	@Override
	public List<Users> getSwipingCardUsersForUser(Users loggedInUser, int page, int pageSize) {
		Pageable pageable = PageRequest.of(page - 1, pageSize);
		List<Users> users = userRepository.findAllByIsRegistered(true, pageable);
		return users;
	}

	@Override
	public List<Users> getTopPicksUsersForUser(Users loggedInUser, int page, int pageSize) {
		Pageable pageable = PageRequest.of(page - 1, pageSize);
		List<Users> users = userRepository.findAllByIsRegistered(true, pageable);
		return users;
	}

}
