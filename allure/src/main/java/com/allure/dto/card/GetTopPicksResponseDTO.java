package com.allure.dto.card;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetTopPicksResponseDTO {

	List<TopPicksDTO> topPicks;
}
