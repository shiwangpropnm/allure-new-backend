package com.allure.dto;

import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter
public class AllureResponseDTO<T> {

	private CommandResultDTO<T>  commandResult;

	public AllureResponseDTO(T data, int status, String message) {
		this.commandResult = new CommandResultDTO<T>(data, status, message);
	}

}
