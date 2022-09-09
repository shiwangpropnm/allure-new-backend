package com.allure.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.allure.dto.UserAuthenticationPrincipalDTO;
import com.allure.dto.card.GetLikesResponseDTO;
import com.allure.dto.card.SwipingCardsDTO;
import com.allure.dto.card.TopPicksDTO;
import com.allure.dto.login.UserLoginResponseDTO;
import com.allure.dto.profile.EditUserProfileDTO;
import com.allure.dto.profile.UpdateUserProfileDTO;
import com.allure.dto.profile.UserGetProfileResponseDTO;
import com.allure.entity.Users;

@Mapper(componentModel = "spring", uses = { UserPassionsMapper.class, UserSexualOrientationsMapper.class,
		ListAddictionMapper.class, ListBodyTypeMapper.class, ListDrinkingHabitMapper.class,
		ListEducationLevelMapper.class, ListEthnicityMapper.class, ListEyeColorMapper.class, ListGenderMapper.class,
		ListHairColorMapper.class, ListReligionMapper.class, ListSmokingHabitMapper.class,
		RelationshipStatusMapper.class })
public interface UserMapper {

	@Mapping(target = "otpVerified", expression = "java(user.isOtpVerified() ? \"1\":\"0\")")
	@Mapping(target = "accStatus", expression = "java((byte)(user.isAccStatus() ? 1:0))")
	@Mapping(target = "token", ignore = true)
	@Mapping(target = "existingUser", ignore = true)
	UserLoginResponseDTO userToUserLoginResponseDTO(final Users user);

	UserAuthenticationPrincipalDTO userToUserAuthenticationDTO(final Users user);

	@Mappings({ @Mapping(target = "showGender", expression = "java(user.isShowGender() ? \"1\":\"0\")"),
			@Mapping(target = "genderId", source = "user.listGender.id"),
			@Mapping(target = "gender", source = "user.listGender.value"),
			@Mapping(target = "bodyTypeId", source = "user.listBodyType.id"),
			@Mapping(target = "bodyType", source = "user.listBodyType.value"),
			@Mapping(target = "eyeColorId", source = "user.listEyeColor.id"),
			@Mapping(target = "eyeColor", source = "user.listEyeColor.value"),
			@Mapping(target = "hairColorId", source = "user.listHairColor.id"),
			@Mapping(target = "hairColor", source = "user.listHairColor.value"),
			@Mapping(target = "smokingId", source = "user.listSmokingHabit.id"),
			@Mapping(target = "smoking", source = "user.listSmokingHabit.value"),
			@Mapping(target = "drinkingId", source = "user.listDrinkingHabit.id"),
			@Mapping(target = "drinking", source = "user.listDrinkingHabit.value"),
			@Mapping(target = "ethnicityId", source = "user.listEthnicity.id"),
			@Mapping(target = "ethnicity", source = "user.listEthnicity.value"),
			@Mapping(target = "religionId", source = "user.listReligion.id"),
			@Mapping(target = "religion", source = "user.listReligion.value"),
			@Mapping(target = "relationshipStatusId", source = "user.relationshipStatus.id"),
			@Mapping(target = "relationshipStatus", source = "user.relationshipStatus.value"),
			@Mapping(target = "showSexualOrientations", expression = "java(user.isShowSexualOrientations()? \"1\":\"0\")"),
			@Mapping(target = "educationLevelId", source = "user.listEducationLevel.id"),
			@Mapping(target = "educationLevel", source = "user.listEducationLevel.value"),
			@Mapping(target = "isProfileCompleted", expression = "java(user.isProfileCompleted()? \"1\":\"0\")"), })
	UserGetProfileResponseDTO userToUserGetProfileResponseDTO(final Users user);

	@Mappings({ @Mapping(target = "listGender", source = "updateUserProfileDTO.gender"),
			@Mapping(target = "listBodyType", source = "updateUserProfileDTO.bodyType"),
			@Mapping(target = "listEyeColor", source = "updateUserProfileDTO.eyeColor"),
			@Mapping(target = "listHairColor", source = "updateUserProfileDTO.hairColor"),
			@Mapping(target = "listSmokingHabit", source = "updateUserProfileDTO.smoking"),
			@Mapping(target = "listDrinkingHabit", source = "updateUserProfileDTO.drinking"),
			@Mapping(target = "listEthnicity", source = "updateUserProfileDTO.ethnicity"),
			@Mapping(target = "listReligion", source = "updateUserProfileDTO.religion"),
			@Mapping(target = "relationshipStatus", source = "updateUserProfileDTO.relationshipStatus"),
			@Mapping(target = "listEducationLevel", source = "updateUserProfileDTO.educationLevel"),
			@Mapping(target = "locLattitude", expression = "java(updateUserProfileDTO.getLocation() != null ? updateUserProfileDTO.getLocation().get(0) : null)"),
			@Mapping(target = "locLongitude", expression = "java(updateUserProfileDTO.getLocation() != null ? updateUserProfileDTO.getLocation().get(1) : null)"),
			@Mapping(target = "university", source = "updateUserProfileDTO.university"),
			@Mapping(target = "showSexualOrientations", source = "updateUserProfileDTO.showSexualOrientations"),
			@Mapping(target = "showGender", source = "updateUserProfileDTO.showGender"),
			@Mapping(target = "interest", source = "updateUserProfileDTO.interest"),
			@Mapping(target = "fullName", source = "updateUserProfileDTO.fullName"),
			@Mapping(target = "dob", source = "updateUserProfileDTO.dob"),
			@Mapping(target = "cleanDate", source = "updateUserProfileDTO.cleanDate"),
			@Mapping(target = "height", source = "updateUserProfileDTO.height") })
	Users updateUserProfileDTOToUsers(final UpdateUserProfileDTO updateUserProfileDTO, Users users);

	@Mappings({ @Mapping(target = "aboutMe", source = "editUserProfileDTO.aboutMe"),
			@Mapping(target = "dob", source = "editUserProfileDTO.dob"),
			@Mapping(target = "cleanDate", source = "editUserProfileDTO.cleanDate"),
			@Mapping(target = "jobTitle", source = "editUserProfileDTO.jobTitle"),
			@Mapping(target = "companyName", source = "editUserProfileDTO.companyName"),
			@Mapping(target = "university", source = "editUserProfileDTO.university"),
			@Mapping(target = "listEducationLevel", source = "editUserProfileDTO.educationLevel"),
			@Mapping(target = "listGender", source = "editUserProfileDTO.gender"),
			@Mapping(target = "livingIn", source = "editUserProfileDTO.livingIn"),
			@Mapping(target = "height", source = "editUserProfileDTO.height"),
			@Mapping(target = "listBodyType", source = "editUserProfileDTO.bodyType"),
			@Mapping(target = "listEyeColor", source = "editUserProfileDTO.eyeColor"),
			@Mapping(target = "listHairColor", source = "editUserProfileDTO.hairColor"),
			@Mapping(target = "relationshipStatus", source = "editUserProfileDTO.relationshipStatus"),
			@Mapping(target = "listSmokingHabit", source = "editUserProfileDTO.smoking"),
			@Mapping(target = "listDrinkingHabit", source = "editUserProfileDTO.drinking"),
			@Mapping(target = "listEthnicity", source = "editUserProfileDTO.ethnicity"),
			@Mapping(target = "listReligion", source = "editUserProfileDTO.religion") })
	Users editUserProfileDTOToUsers(final EditUserProfileDTO editUserProfileDTO, Users users);

	Users idToUsersNonEntity(final Integer id);

	@Mappings({
			@Mapping(target = "showSexualOrientations", expression = "java(user.isShowSexualOrientations()? \"1\":\"0\")"),
			@Mapping(target = "showGender", expression = "java(user.isShowGender() ? \"1\":\"0\")"),
			@Mapping(target = "isProfileCompleted", expression = "java(user.isProfileCompleted()? \"1\":\"0\")"),
			@Mapping(target = "genderId", source = "user.listGender.id"),
			@Mapping(target = "gender", source = "user.listGender.value") })
	SwipingCardsDTO userToSwipingCardDTO(Users user);

	@Mappings({
			@Mapping(target = "showSexualOrientations", expression = "java(user.isShowSexualOrientations()? \"1\":\"0\")"),
			@Mapping(target = "showGender", expression = "java(user.isShowGender() ? \"1\":\"0\")"),
			@Mapping(target = "isProfileCompleted", expression = "java(user.isProfileCompleted()? \"1\":\"0\")"),
			@Mapping(target = "genderId", source = "user.listGender.id"),
			@Mapping(target = "gender", source = "user.listGender.value") })
	TopPicksDTO userToTopPicksDTO(Users user);

	@Mappings({
			@Mapping(target = "showSexualOrientations", expression = "java(user.isShowSexualOrientations()? \"1\":\"0\")"),
			@Mapping(target = "showGender", expression = "java(user.isShowGender() ? \"1\":\"0\")"),
			@Mapping(target = "isProfileCompleted", expression = "java(user.isProfileCompleted()? \"1\":\"0\")"),
			@Mapping(target = "genderId", source = "user.listGender.id"),
			@Mapping(target = "gender", source = "user.listGender.value") })
	GetLikesResponseDTO userToGetLikeResponseDTO(Users user);
}
