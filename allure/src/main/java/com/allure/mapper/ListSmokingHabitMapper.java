package com.allure.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.allure.dto.common.GetIdValueDTO;
import com.allure.entity.ListSmokingHabit;

@Mapper(componentModel = "spring")
public interface ListSmokingHabitMapper {

	GetIdValueDTO listSmokingHabitToGetIdValueDTO(final ListSmokingHabit listSmokingHabit);

	List<GetIdValueDTO> listSmokingHabitListToGetIdValueDTOList(final List<ListSmokingHabit> listSmokingHabits);
	
	ListSmokingHabit idToListSmokingHabitNonEntity(final Integer id);

}
