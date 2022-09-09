package com.allure.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;

import com.allure.dto.common.IdValueSelectionDTO;
import com.allure.entity.ListHairColor;
import com.allure.entity.PreferHairColor;

@Mapper(componentModel = "spring")
public interface PreferHairColorMapper {

	default List<Integer> preferHairColorListToHairColorIdList(List<PreferHairColor> userPreferHairColors) {
		return userPreferHairColors.stream().map(PreferHairColor::getHairColorId).collect(Collectors.toList());
	}

	IdValueSelectionDTO preferHairColorAndSelectionToIdValueSelectionDTO(ListHairColor hairColor, boolean isSelected);

	default List<IdValueSelectionDTO> preferHairColorsAndAllHairColorsToIdValueSelectionList(
			List<PreferHairColor> userPreferHairColors, List<ListHairColor> allHairColors) {
		List<Integer> userPreferHairColorIds = userPreferHairColors.stream().map(PreferHairColor::getHairColorId)
				.collect(Collectors.toList());
		return allHairColors.stream().map(hairColor -> {
			return preferHairColorAndSelectionToIdValueSelectionDTO(hairColor,
					userPreferHairColorIds.contains(hairColor.getId()));
		}).collect(Collectors.toList());
	}

}
