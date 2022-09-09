package com.allure.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.allure.dto.common.GetIdValueDTO;
import com.allure.entity.ListReligion;

@Mapper(componentModel = "spring")
public interface ListReligionMapper {

	GetIdValueDTO listReligionToGetIdValueDTO(final ListReligion listReligion);

	List<GetIdValueDTO> listReligionListToGetIdValueDTOList(final List<ListReligion> listReligions);
	
	ListReligion idToListReligionNonEntity(final Integer id);

}
