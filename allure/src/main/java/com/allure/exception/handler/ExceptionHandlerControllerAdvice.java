package com.allure.exception.handler;

import static com.allure.util.HttpUtils.getResponseHeaders;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import com.allure.constants.AllureResponseStatus;
import com.allure.dto.AllureResponseDTO;
import com.allure.exception.InvalidDetailsException;
import com.allure.exception.InvalidOTPException;
import com.allure.exception.UnprocessableEntityException;
import com.allure.exception.UserAlreadyExistException;
import com.allure.exception.UsernameAlreadyExistException;

@ControllerAdvice
public class ExceptionHandlerControllerAdvice {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<AllureResponseDTO<Object>> handleException(final Exception exception,
			final HttpServletRequest httpRequest) {
		exception.printStackTrace();
		final HttpHeaders responseHeaders = getResponseHeaders(httpRequest);

		return new ResponseEntity<>(
				new AllureResponseDTO<>(null, AllureResponseStatus.FAIL.getCode(), exception.getMessage()),
				responseHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(ResponseStatusException.class)
	public ResponseEntity<AllureResponseDTO<Object>> handleResponseStatusException(
			final ResponseStatusException responseStatusException, final HttpServletRequest httpRequest) {
		final HttpHeaders responseHeaders = getResponseHeaders(httpRequest);

		return new ResponseEntity<>(
				new AllureResponseDTO<>(null, AllureResponseStatus.FAIL.getCode(), responseStatusException.getReason()),
				responseHeaders, responseStatusException.getStatus());
	}

	@ExceptionHandler(UserAlreadyExistException.class)
	public ResponseEntity<AllureResponseDTO<Object>> handleUserAlreadyExistException(
			final UserAlreadyExistException userAlreadyExistException, final HttpServletRequest httpRequest) {
		final HttpHeaders responseHeaders = getResponseHeaders(httpRequest);

		return new ResponseEntity<>(new AllureResponseDTO<>(null, AllureResponseStatus.FAIL.getCode(),
				userAlreadyExistException.getMessage()), responseHeaders, HttpStatus.CONFLICT);
	}

	@ExceptionHandler(UsernameAlreadyExistException.class)
	public ResponseEntity<AllureResponseDTO<Object>> handleUsernameAlreadyExistException(
			final UsernameAlreadyExistException usernameAlreadyExistException, final HttpServletRequest httpRequest) {
		final HttpHeaders responseHeaders = getResponseHeaders(httpRequest);
		
		return new ResponseEntity<>(new AllureResponseDTO<>(null, AllureResponseStatus.FAIL.getCode(),
				usernameAlreadyExistException.getMessage()), responseHeaders, HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(UnprocessableEntityException.class)
	public ResponseEntity<AllureResponseDTO<Object>> handleUnprocessableEntityException(
			final UnprocessableEntityException unprocessableEntityException, final HttpServletRequest httpRequest) {
		final HttpHeaders responseHeaders = getResponseHeaders(httpRequest);

		return new ResponseEntity<>(new AllureResponseDTO<>(null, AllureResponseStatus.FAIL.getCode(),
				unprocessableEntityException.getMessage()), responseHeaders, HttpStatus.UNPROCESSABLE_ENTITY);
	}

	@ExceptionHandler(InvalidDetailsException.class)
	public ResponseEntity<AllureResponseDTO<Object>> handleInvalidDetailsException(
			final InvalidDetailsException invalidDetailsException, final HttpServletRequest httpRequest) {
		final HttpHeaders responseHeaders = getResponseHeaders(httpRequest);

		return new ResponseEntity<>(new AllureResponseDTO<>(null, AllureResponseStatus.FAIL.getCode(),
				invalidDetailsException.getMessage()), responseHeaders, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(InvalidOTPException.class)
	public ResponseEntity<AllureResponseDTO<Object>> handleInvalidOTPException(
			final InvalidOTPException invalidOTPException, final HttpServletRequest httpRequest) {
		final HttpHeaders responseHeaders = getResponseHeaders(httpRequest);
		
		return new ResponseEntity<>(new AllureResponseDTO<>(null, AllureResponseStatus.FAIL.getCode(),
				invalidOTPException.getMessage()), responseHeaders, HttpStatus.UNAUTHORIZED);
	}
	
}