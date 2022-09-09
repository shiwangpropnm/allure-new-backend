package com.allure.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.allure.dto.common.GetIdValueDTO;
import com.allure.entity.ListGender;

@Mapper(componentModel="spring")
public interface ListGenderMapper {

	GetIdValueDTO listGenderToGetIdValueDTO(final ListGender listGender);
	
	List<GetIdValueDTO> listGenderListToGetIdValueDTOList(final List<ListGender> listGenders);
	
	ListGender idToListGenderNonEntity(final Integer id);

}
