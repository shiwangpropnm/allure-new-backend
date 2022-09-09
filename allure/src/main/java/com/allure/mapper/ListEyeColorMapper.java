package com.allure.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.allure.dto.common.GetIdValueDTO;
import com.allure.entity.ListEyeColor;

@Mapper(componentModel = "spring")
public interface ListEyeColorMapper {

	GetIdValueDTO listEyeColorToGetIdValueDTO(final ListEyeColor listEyeColor);

	List<GetIdValueDTO> listEyeColorListToGetIdValueDTOList(final List<ListEyeColor> listEyeColors);
	
	ListEyeColor idToListEyeColorNonEntity(final Integer id);

}
