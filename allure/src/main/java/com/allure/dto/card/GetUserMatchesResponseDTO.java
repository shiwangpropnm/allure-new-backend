package com.allure.dto.card;

import java.util.List;

import com.allure.dto.profile.UserGetProfileResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetUserMatchesResponseDTO {

	List<UserGetProfileResponseDTO> userMatches;
}
