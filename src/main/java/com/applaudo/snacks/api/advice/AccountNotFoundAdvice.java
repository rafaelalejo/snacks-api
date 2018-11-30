package com.applaudo.snacks.api.advice;

import com.applaudo.snacks.api.exception.InvalidAccountException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class AccountNotFoundAdvice {
	@ResponseBody
	@ExceptionHandler(InvalidAccountException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)

	String accountNotFoundHandler(InvalidAccountException ac) {
		return ac.getMessage();
	}
}