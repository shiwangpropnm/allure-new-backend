package com.allure.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.allure.dto.common.SexualOrientationDTO;
import com.allure.entity.SexualOrientations;

@Mapper(componentModel="spring")
public interface SexualOrientationMapper {

	SexualOrientationDTO sexualOrientationToSexualOrientationDTO(final SexualOrientations sexualOrientations);

	SexualOrientations sexualOrientationsIdToSexualOrientationsNonEntity(final Integer id); 

	List<SexualOrientations> sexualOrientationsIdToSexualOrientationsNonEntity(final List<Integer> id);

}
