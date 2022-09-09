package com.allure.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.allure.dto.common.GetIdValueDTO;
import com.allure.entity.RelationshipStatus;

@Mapper(componentModel = "spring")
public interface RelationshipStatusMapper {

	GetIdValueDTO relationshipStatusToGetIdValueDTO(final RelationshipStatus relationshipStatus);

	List<GetIdValueDTO> relationshipStatusListToGetIdValueDTOList(final List<RelationshipStatus> relationshipStatuses);
	
	RelationshipStatus idToRelationshipStatusNonEntity(final Integer id);

}
