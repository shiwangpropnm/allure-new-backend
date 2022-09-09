package com.allure.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.allure.dto.card.CardsPassionsDTO;
import com.allure.dto.profile.UserProfilePassionsDTO;
import com.allure.entity.Passions;
import com.allure.entity.UserPassions;
import com.allure.entity.Users;

@Mapper(componentModel = "spring")
public interface UserPassionsMapper {
	@Mappings({
		@Mapping(target="id", expression="java(userPassions.getPassions().getId())"),
		@Mapping(target="value", expression="java(userPassions.getPassions().getPassionName())")
	})
	UserProfilePassionsDTO userPassionsToUserProfilePassionsDTO(final UserPassions userPassions);
	
	List<UserProfilePassionsDTO> userPassionsListToUserProfilePassionDTOList(final List<UserPassions> userPassions);

	@Mappings({
		@Mapping(target="passionId", expression="java(userPassions.getPassions().getId())"),
		@Mapping(target="passionName", expression="java(userPassions.getPassions().getPassionName())")
	})
	CardsPassionsDTO userPassionsToCardsPassionsDTO(final UserPassions userPassions);

	List<CardsPassionsDTO> userPassionsListToCardsPassionsDTOList(final List<UserPassions> userPassions);
	
	default List<UserPassions> passionListAndUserToUserPassionsNonEntityList(final List<Passions> passions, Users user){
		return passions.stream().map(passion-> {
			UserPassions userPassion = new UserPassions();
			userPassion.setPassions(passion);
			userPassion.setUsers(user);
			return userPassion;
		}).collect(Collectors.toList());
	}

	default List<UserPassions> passionIdListAndUserIdToUserPassionsNonEntityList(final List<Integer> passionIds, Integer userId){
		PassionMapper passionMapper = Mappers.getMapper(PassionMapper.class);
		UserMapper userMapper = Mappers.getMapper(UserMapper.class);
		Users user = userMapper.idToUsersNonEntity(userId);
		return passionIds.stream().map(passionId-> {
			UserPassions userPassion = new UserPassions();
			userPassion.setPassions(passionMapper.passionsIdToPassionsNonEntity(passionId));
			userPassion.setUsers(user);
			return userPassion;
		}).collect(Collectors.toList());
	}
	
}
