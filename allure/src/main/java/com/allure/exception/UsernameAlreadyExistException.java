package com.allure.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.CONFLICT)
public class UsernameAlreadyExistException extends RuntimeException {

	public UsernameAlreadyExistException(String message) {
		super(message);
	}
}
