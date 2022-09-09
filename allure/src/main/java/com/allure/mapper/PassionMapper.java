package com.allure.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.allure.dto.common.PassionDTO;
import com.allure.entity.Passions;

@Mapper(componentModel = "spring")
public interface PassionMapper {

	@Mapping(target="categoryId", source="passions.passionCategories.id")
	PassionDTO passionsToPassionDTO(final Passions passions);
	
	Passions passionsIdToPassionsNonEntity(final Integer id); 
	
	List<Passions> passionsIdListToPassionsNonEntityList(final List<Integer> id);
}
