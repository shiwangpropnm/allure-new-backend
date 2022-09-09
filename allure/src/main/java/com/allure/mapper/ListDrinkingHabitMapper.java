package com.allure.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.allure.dto.common.GetIdValueDTO;
import com.allure.entity.ListDrinkingHabit;

@Mapper(componentModel="spring")
public interface ListDrinkingHabitMapper {

	GetIdValueDTO listDrinkingHabitToGetIdValueDTO(final ListDrinkingHabit listDrinkingHabit);
	
	List<GetIdValueDTO> listDrinkingHabitListToGetIdValueDTOList(final List<ListDrinkingHabit> listDrinkingHabits);
	
	ListDrinkingHabit idToListDrinkingHabitNonEntity(final Integer id);

}
