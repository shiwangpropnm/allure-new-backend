package com.allure.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.allure.dto.common.GetIdValueDTO;
import com.allure.entity.ListBodyType;

@Mapper(componentModel="spring")
public interface ListBodyTypeMapper {

	GetIdValueDTO listBodyTypeToGetIdValueDTO(final ListBodyType listBodyType);
	
	List<GetIdValueDTO> listBodyTypeListToGetIdValueDTOList(final List<ListBodyType> listBodyTypes);
	
	ListBodyType idToListBodyTypeNonEntity(final Integer id);

}
