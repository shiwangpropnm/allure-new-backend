package com.allure.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;

import com.allure.dto.common.IdValueSelectionDTO;
import com.allure.entity.ListBodyType;
import com.allure.entity.PreferBodyType;

@Mapper(componentModel = "spring")
public interface PreferBodyTypeMapper {

	default List<Integer> preferBodyTypeListToBodyTypeIdList(List<PreferBodyType> userPreferBodyTypes) {
		return userPreferBodyTypes.stream().map(PreferBodyType::getBodyTypeId).collect(Collectors.toList());
	}

	IdValueSelectionDTO preferBodyTypeAndSelectionToIdValueSelectionDTO(ListBodyType bodyType, boolean isSelected);

	default List<IdValueSelectionDTO> preferBodyTypesAndAllBodyTypesToIdValueSelectionList(
			List<PreferBodyType> userPreferBodyTypes, List<ListBodyType> allBodyTypes) {
		List<Integer> userPreferBodyTypeIds = userPreferBodyTypes.stream().map(PreferBodyType::getBodyTypeId)
				.collect(Collectors.toList());
		return allBodyTypes.stream().map(bodyType -> {
			return preferBodyTypeAndSelectionToIdValueSelectionDTO(bodyType,
					userPreferBodyTypeIds.contains(bodyType.getId()));
		}).collect(Collectors.toList());
	}

}
