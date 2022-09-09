package com.allure.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.allure.dto.common.GetIdValueDTO;
import com.allure.entity.ListAddiction;

@Mapper(componentModel="spring")
public interface ListAddictionMapper {

	GetIdValueDTO listAddictionToGetIdValueDTO(final ListAddiction listAddiction);
	
	List<GetIdValueDTO> listAddictionListToGetIdValueDTOList(final List<ListAddiction> listAddictions);
	
	ListAddiction idToListAddictionNonEntity(final Integer id);
}
