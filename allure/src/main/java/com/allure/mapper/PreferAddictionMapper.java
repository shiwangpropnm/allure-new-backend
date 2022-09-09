package com.allure.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;

import com.allure.dto.common.IdValueSelectionDTO;
import com.allure.entity.ListAddiction;
import com.allure.entity.PreferAddiction;

@Mapper(componentModel = "spring")
public interface PreferAddictionMapper {

	default List<Integer> preferAddictionListToAddctionIdList(List<PreferAddiction> userPreferAddictions) {
		return userPreferAddictions.stream().map(PreferAddiction::getAddictionId).collect(Collectors.toList());
	}

	IdValueSelectionDTO preferAddictionAndSelectionToIdValueSelectionDTO(ListAddiction addiction, boolean isSelected);

	default List<IdValueSelectionDTO> preferAddictionsAndAllAddictionsToIdValueSelectionList(
			List<PreferAddiction> userPreferAddictions, List<ListAddiction> allAddictions) {
		List<Integer> userPreferAddictionIds = userPreferAddictions.stream().map(PreferAddiction::getAddictionId)
				.collect(Collectors.toList());
		return allAddictions.stream().map(addiction -> {
			return preferAddictionAndSelectionToIdValueSelectionDTO(addiction,
					userPreferAddictionIds.contains(addiction.getId()));
		}).collect(Collectors.toList());
	}
}
