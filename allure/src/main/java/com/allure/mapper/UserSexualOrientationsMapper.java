package com.allure.mapper;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.allure.dto.profile.UserProfileSexualOrientationDTO;
import com.allure.entity.SexualOrientations;
import com.allure.entity.UserSexualOrientations;
import com.allure.entity.Users;

@Mapper(componentModel = "spring")
public interface UserSexualOrientationsMapper {
	@Mappings({
		@Mapping(target="id", expression="java(userSexualOrientations.getSexualOrientations().getId())"),
		@Mapping(target="value", expression="java(userSexualOrientations.getSexualOrientations().getSexualOrientaion())")
	})
	UserProfileSexualOrientationDTO userSexualOrientationsToUserProfileSexualOrientationDTO(final UserSexualOrientations userSexualOrientations);
	
	default List<UserSexualOrientations> sexualOrientationListAndUserToUserSexualOrientationNonEntityList(final List<SexualOrientations> sexualOrientations, Users user){
		return sexualOrientations.stream().map(sexualOrientation-> {
			UserSexualOrientations userSexualOrientation = new UserSexualOrientations();
			userSexualOrientation.setSexualOrientations(sexualOrientation);
			userSexualOrientation.setUsers(user);
			return userSexualOrientation;
		}).collect(Collectors.toList());
	}

	default List<UserSexualOrientations> sexualOrientationIdListAndUserIdToUserSexualOrientationsNonEntityList(final List<Integer> sexualOrientationIds, Integer userId){
		SexualOrientationMapper sexualOrientationMapper = Mappers.getMapper(SexualOrientationMapper.class);
		UserMapper userMapper = Mappers.getMapper(UserMapper.class);
		Users user = userMapper.idToUsersNonEntity(userId);
		return sexualOrientationIds.stream().map(sexualOrientationId-> {
			UserSexualOrientations userSexualOrientation = new UserSexualOrientations();
			userSexualOrientation.setSexualOrientations(sexualOrientationMapper.sexualOrientationsIdToSexualOrientationsNonEntity(sexualOrientationId));
			userSexualOrientation.setUsers(user);
			userSexualOrientation.setCreatedAt(new Date());
			return userSexualOrientation;
		}).collect(Collectors.toList());
	}

}
