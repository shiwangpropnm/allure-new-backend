package com.allure.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.allure.dto.profile.UpdateUserProfileDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.SneakyThrows;

@Component
public class StringToUpdateUserProfileDTOConverter implements Converter<String, UpdateUserProfileDTO> {


	@Autowired
	ObjectMapper objectMapper;
	
	@Override
	@SneakyThrows
	public UpdateUserProfileDTO convert(String input) {
		return objectMapper.readValue(input, UpdateUserProfileDTO.class);
	}

}
