package com.allure.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.allure.dto.common.GetIdValueDTO;
import com.allure.entity.ListEducationLevel;

@Mapper(componentModel="spring")
public interface ListEducationLevelMapper {

	GetIdValueDTO listEducationLevelToGetIdValueDTO(final ListEducationLevel listEducationLevel);
	
	List<GetIdValueDTO> listEducationLevelListToGetIdValueDTOList(final List<ListEducationLevel> listEducationLevels);

	ListEducationLevel idToListBodyTypeNonEntity(final Integer id);

}
