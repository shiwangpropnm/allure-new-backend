package com.allure.dto.feed;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetFeedsResponseDTO {

	private List<GetFeedsDTO> feeds;

}
