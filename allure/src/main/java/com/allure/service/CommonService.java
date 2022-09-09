package com.allure.service;

import java.util.List;

import com.allure.dto.common.GetIdValueDTO;
import com.allure.dto.common.GetPassionsResponseDTO;
import com.allure.dto.common.GetSexualOrientationsResponseDTO;
import com.allure.entity.ListAddiction;
import com.allure.entity.ListBodyType;
import com.allure.entity.ListGender;
import com.allure.entity.ListHairColor;

public interface CommonService {

	public GetSexualOrientationsResponseDTO getSexualOrientations();

	public GetPassionsResponseDTO getPassions();

	public List<GetIdValueDTO> getEducationLevelList();

	public List<GetIdValueDTO> getEyeColorList();

	public List<GetIdValueDTO> getBodyTypeList();

	public List<GetIdValueDTO> getHairColorList();

	public List<GetIdValueDTO> getRelationshipStatusList();

	public List<GetIdValueDTO> getEthnicityList();

	public List<GetIdValueDTO> getReligionList();

	public List<GetIdValueDTO> getGenderList();

	public List<GetIdValueDTO> getAddictionList();

	public List<ListBodyType> getBodyTypeEntityList();

	public List<ListHairColor> getHairColorEntityList();

	public List<ListGender> getGenderEntityList();

	public List<ListAddiction> getAddictionEntityList();
	
	public ListGender getGenderById(Integer genderId);

}
