package com.allure.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.allure.dto.usersetting.GetUserSettingDTO;
import com.allure.dto.usersetting.UpdateUserSettingDTO;
import com.allure.entity.UserSetting;

@Mapper(componentModel = "spring")
public interface UserSettingMapper {

	@Mapping(target = "phone", ignore = true)
	GetUserSettingDTO userSettingToGetUserSettingDTO(UserSetting userSetting);

	@Mappings({ @Mapping(target = "topPicks", source = "updateUserSettingDTO.topPicks"),
			@Mapping(target = "showMeOnline", source = "updateUserSettingDTO.showMeOnline"),
			@Mapping(target = "readReceipt", source = "updateUserSettingDTO.readReceipt"),
			@Mapping(target = "activityStatus", source = "updateUserSettingDTO.activityStatus"),
			@Mapping(target = "showMe", source = "updateUserSettingDTO.showMe"),
			@Mapping(target = "hereTo", source = "updateUserSettingDTO.hereTo"),
			@Mapping(target = "minAge", source = "updateUserSettingDTO.minAge"),
			@Mapping(target = "maxAge", source = "updateUserSettingDTO.maxAge"),
			@Mapping(target = "distanceUnit", source = "updateUserSettingDTO.distanceUnit"),
			@Mapping(target = "maxDistance", source = "updateUserSettingDTO.maxDistance") })
	UserSetting updateUserSettingDTOToUserSetting(UpdateUserSettingDTO updateUserSettingDTO, UserSetting userSetting);

}
