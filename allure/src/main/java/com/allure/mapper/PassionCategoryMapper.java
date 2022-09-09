package com.allure.mapper;

import org.mapstruct.Mapper;

import com.allure.dto.common.PassionCategoryDTO;
import com.allure.entity.PassionCategories;

@Mapper(componentModel = "spring", uses= {PassionMapper.class} )
public interface PassionCategoryMapper {

	PassionCategoryDTO passionCategoriesToPassionCategoryDTO(final PassionCategories passionCategories);
}
