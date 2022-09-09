package com.allure.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.allure.dto.common.GetIdValueDTO;
import com.allure.entity.ListEthnicity;

@Mapper(componentModel = "spring")
public interface ListEthnicityMapper {

	GetIdValueDTO ListEthnicityToGetIdValueDTO(final ListEthnicity listEthinicity);

	List<GetIdValueDTO> listEthnicityListToGetIdValueDTOList(final List<ListEthnicity> listEthinicities);

	ListEthnicity idToListEthnicityNonEntity(final Integer id);

}
