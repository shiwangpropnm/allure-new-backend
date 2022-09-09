package com.allure.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.allure.dto.feed.GetFeedsDTO;
import com.allure.entity.FeedList;
import com.allure.entity.Users;

@Mapper(componentModel="spring")
public interface FeedListMapper {

	@Mappings({
			@Mapping(target="profileId", source = "profile.id"),
			@Mapping(target="createdAt", source = "feed.createdAt"),	
			@Mapping(target="genderId", source = "profile.listGender.id"),
			@Mapping(target="gender", source = "profile.listGender.value")
	})
	GetFeedsDTO feedAndUserToGetFeedsDTO(FeedList feed, Users profile, Integer distanceUnit, Double distance, String dpUrl, String[] dpUrls);

}
