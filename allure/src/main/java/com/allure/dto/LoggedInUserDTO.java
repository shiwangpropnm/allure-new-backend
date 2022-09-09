package com.allure.dto;

import org.springframework.security.core.context.SecurityContextHolder;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoggedInUserDTO {

	@JsonIgnore
	private final int loggedInUserId = ((UserAuthenticationPrincipalDTO)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
}
