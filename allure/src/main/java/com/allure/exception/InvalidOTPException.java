package com.allure.exception;

public class InvalidOTPException extends RuntimeException {

	public InvalidOTPException(String reason) {
		super(reason);
	}

}
