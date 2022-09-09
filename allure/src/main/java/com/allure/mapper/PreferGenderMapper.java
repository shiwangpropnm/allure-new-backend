package com.allure.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;

import com.allure.dto.common.IdValueSelectionDTO;
import com.allure.entity.ListGender;
import com.allure.entity.PreferGender;

@Mapper(componentModel = "spring")
public interface PreferGenderMapper {

	default List<Integer> preferGenderListToGenderIdList(List<PreferGender> userPreferGenders) {
		return userPreferGenders.stream().map(PreferGender::getGenderId).collect(Collectors.toList());
	}

	IdValueSelectionDTO preferGenderAndSelectionToIdValueSelectionDTO(ListGender gender, boolean isSelected);

	default List<IdValueSelectionDTO> preferGendersAndAllGendersToIdValueSelectionList(
			List<PreferGender> userPreferGenders, List<ListGender> allGenders) {
		List<Integer> userPreferGenderIds = userPreferGenders.stream().map(PreferGender::getGenderId)
				.collect(Collectors.toList());
		return allGenders.stream().map(gender -> {
			return preferGenderAndSelectionToIdValueSelectionDTO(gender, userPreferGenderIds.contains(gender.getId()));
		}).collect(Collectors.toList());
	}
}
