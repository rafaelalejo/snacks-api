package com.applaudo.snacks.api.exception;

public class InvalidTokenException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidTokenException() {
		super("Invalid or expired token.");
	}
}