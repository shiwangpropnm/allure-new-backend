package com.allure.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.allure.dto.common.GetIdValueDTO;
import com.allure.entity.ListHairColor;

@Mapper(componentModel = "spring")
public interface ListHairColorMapper {

	GetIdValueDTO listHairColorToGetIdValueDTO(final ListHairColor listHairColor);

	List<GetIdValueDTO> listHairColorListToGetIdValueDTOList(final List<ListHairColor> listHairColors);
	
	ListHairColor idToListHairColorNonEntity(final Integer id);

}
