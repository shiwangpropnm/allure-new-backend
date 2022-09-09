package com.allure.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommandResultDTO<T> implements java.io.Serializable  {


	private static final long serialVersionUID = 3232495283834322395L;

	private int status;
	
	private String message;

	private T data;
	
	public CommandResultDTO(T data, int status, String message) {
		this.status = status;
		this.message = message;
		this.data = data;
	}
}
