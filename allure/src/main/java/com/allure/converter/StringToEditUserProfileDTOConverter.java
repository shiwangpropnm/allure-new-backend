package com.allure.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.allure.dto.profile.EditUserProfileDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.SneakyThrows;

@Component
public class StringToEditUserProfileDTOConverter implements Converter<String, EditUserProfileDTO> {


	@Autowired
	ObjectMapper objectMapper;
	
	@Override
	@SneakyThrows
	public EditUserProfileDTO convert(String input) {
		return objectMapper.readValue(input, EditUserProfileDTO.class);
	}

}
