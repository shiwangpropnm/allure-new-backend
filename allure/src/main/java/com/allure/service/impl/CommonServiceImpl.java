package com.allure.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.allure.dao.ListAddictionRepository;
import com.allure.dao.ListBodyTypeRepository;
import com.allure.dao.ListDrinkingHabitRepository;
import com.allure.dao.ListEducationLevelRepository;
import com.allure.dao.ListEthnicityRepository;
import com.allure.dao.ListEyeColorRepository;
import com.allure.dao.ListGenderRepository;
import com.allure.dao.ListHairColorRepository;
import com.allure.dao.ListReligionRepository;
import com.allure.dao.ListSmokingHabitRepository;
import com.allure.dao.PassionCategoriesRepository;
import com.allure.dao.RelationshipStatusRepository;
import com.allure.dao.SexualOrientationsRepository;
import com.allure.dto.common.GetIdValueDTO;
import com.allure.dto.common.GetPassionsResponseDTO;
import com.allure.dto.common.GetSexualOrientationsResponseDTO;
import com.allure.entity.ListAddiction;
import com.allure.entity.ListBodyType;
import com.allure.entity.ListGender;
import com.allure.entity.ListHairColor;
import com.allure.entity.PassionCategories;
import com.allure.mapper.ListAddictionMapper;
import com.allure.mapper.ListBodyTypeMapper;
import com.allure.mapper.ListDrinkingHabitMapper;
import com.allure.mapper.ListEducationLevelMapper;
import com.allure.mapper.ListEthnicityMapper;
import com.allure.mapper.ListEyeColorMapper;
import com.allure.mapper.ListGenderMapper;
import com.allure.mapper.ListHairColorMapper;
import com.allure.mapper.ListReligionMapper;
import com.allure.mapper.ListSmokingHabitMapper;
import com.allure.mapper.PassionCategoryMapper;
import com.allure.mapper.RelationshipStatusMapper;
import com.allure.mapper.SexualOrientationMapper;
import com.allure.service.CommonService;

@Service
public class CommonServiceImpl implements CommonService {

	@Autowired
	SexualOrientationsRepository sexualOrientationsRepository;

	@Autowired
	PassionCategoriesRepository passionCategoriesRepository;

	@Autowired
	ListBodyTypeRepository listBodyTypeRepository;

	@Autowired
	ListDrinkingHabitRepository listDrinkingHabitRepository;

	@Autowired
	ListEducationLevelRepository listEducationLevelRepository;

	@Autowired
	ListEthnicityRepository listEthnicityRepository;

	@Autowired
	ListEyeColorRepository listEyeColorRepository;

	@Autowired
	ListGenderRepository listGenderRepository;

	@Autowired
	ListAddictionRepository listAddictionRepository;

	@Autowired
	ListHairColorRepository listHairColorRepository;

	@Autowired
	ListReligionRepository listReligionRepository;

	@Autowired
	ListSmokingHabitRepository listSmokingHabitRepository;

	@Autowired
	RelationshipStatusRepository relationshipStatusRepository;

	@Autowired
	ListBodyTypeMapper listBodyTypeMapper;

	@Autowired
	ListDrinkingHabitMapper listDrinkingHabitMapper;

	@Autowired
	ListEducationLevelMapper listEducationLevelMapper;

	@Autowired
	ListEthnicityMapper listEthnicityMapper;

	@Autowired
	ListEyeColorMapper listEyeColorMapper;

	@Autowired
	ListGenderMapper listGenderMapper;

	@Autowired
	ListAddictionMapper listAddictionMapper;

	@Autowired
	ListHairColorMapper listHairColorMapper;

	@Autowired
	ListReligionMapper listReligionMapper;

	@Autowired
	ListSmokingHabitMapper listSmokingHabitMapper;

	@Autowired
	PassionCategoryMapper passionCategoryMapper;

	@Autowired
	SexualOrientationMapper sexualOrientationMapper;

	@Autowired
	RelationshipStatusMapper relationshipStatusMapper;

	@Override
	public GetSexualOrientationsResponseDTO getSexualOrientations() {
		return new GetSexualOrientationsResponseDTO(sexualOrientationsRepository.findAllByIsDeleted(false).stream()
				.map(sexualOrientationMapper::sexualOrientationToSexualOrientationDTO).collect(Collectors.toList()));
	}

	@Override
	public GetPassionsResponseDTO getPassions() {

		List<PassionCategories> passionCategories = passionCategoriesRepository.findAllByIsDeleted(false);

		return new GetPassionsResponseDTO(passionCategories.stream()
				.map(passionCategoryMapper::passionCategoriesToPassionCategoryDTO).collect(Collectors.toList()));
	}

	@Override
	public List<GetIdValueDTO> getEducationLevelList() {
		return listEducationLevelMapper
				.listEducationLevelListToGetIdValueDTOList(listEducationLevelRepository.findAll());
	}

	@Override
	public List<GetIdValueDTO> getEyeColorList() {
		return listEyeColorMapper.listEyeColorListToGetIdValueDTOList(listEyeColorRepository.findAll());
	}

	@Override
	public List<GetIdValueDTO> getBodyTypeList() {
		return listBodyTypeMapper.listBodyTypeListToGetIdValueDTOList(getBodyTypeEntityList());
	}

	@Override
	public List<GetIdValueDTO> getHairColorList() {
		return listHairColorMapper.listHairColorListToGetIdValueDTOList(getHairColorEntityList());
	}

	@Override
	public List<GetIdValueDTO> getRelationshipStatusList() {
		return relationshipStatusMapper
				.relationshipStatusListToGetIdValueDTOList(relationshipStatusRepository.findAll());
	}

	@Override
	public List<GetIdValueDTO> getEthnicityList() {
		return listEthnicityMapper.listEthnicityListToGetIdValueDTOList(listEthnicityRepository.findAll());
	}

	@Override
	public List<GetIdValueDTO> getReligionList() {
		return listReligionMapper.listReligionListToGetIdValueDTOList(listReligionRepository.findAll());
	}

	@Override
	public List<GetIdValueDTO> getGenderList() {
		return listGenderMapper.listGenderListToGetIdValueDTOList(getGenderEntityList());
	}

	@Override
	public List<GetIdValueDTO> getAddictionList() {
		return listAddictionMapper.listAddictionListToGetIdValueDTOList(getAddictionEntityList());
	}

	@Override
	public List<ListBodyType> getBodyTypeEntityList() {
		return listBodyTypeRepository.findAll();
	}

	@Override
	public List<ListHairColor> getHairColorEntityList() {
		return listHairColorRepository.findAll();
	}

	@Override
	public List<ListGender> getGenderEntityList() {
		return listGenderRepository.findAll();
	}

	@Override
	public List<ListAddiction> getAddictionEntityList() {
		return listAddictionRepository.findAll();
	}

	@Override
	public ListGender getGenderById(Integer genderId) {
		if(genderId == null || genderId <= 0)
			return null;
		return listGenderRepository.getById(genderId);
	}

}
