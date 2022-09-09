package com.allure.config;

import java.io.IOException;
import java.io.Serializable;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.allure.constants.AllureResponseStatus;
import com.allure.dto.AllureResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

	private static final long serialVersionUID = -347313708612238047L;

	@Autowired
	MessageSource messageSource;

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException {
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		ObjectMapper objectMapper = new ObjectMapper();
		AllureResponseDTO<Object> allureResponseDTO = new AllureResponseDTO<Object>(null,
				AllureResponseStatus.FAIL.getCode(),
				messageSource.getMessage("exception.authorization", null, Locale.ENGLISH));
		response.getOutputStream().print(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(allureResponseDTO));
	}
}
