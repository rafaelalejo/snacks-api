package com.applaudo.snacks.api.advice;

import com.applaudo.snacks.api.exception.InvalidAccountException;
import com.applaudo.snacks.api.exception.InvalidProductException;
import com.applaudo.snacks.api.exception.InvalidTokenException;
import com.applaudo.snacks.api.response.CustomMessageResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class RestExceptionAdvice {

	@ExceptionHandler(value = { InvalidAccountException.class, InvalidTokenException.class,
			InvalidProductException.class })
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public CustomMessageResponse tokenNotFoundHandler(Exception ac, WebRequest webRequest) {
		return new CustomMessageResponse(ac.getMessage());
	}
}