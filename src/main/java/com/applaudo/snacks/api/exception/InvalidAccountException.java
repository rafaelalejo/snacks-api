package com.applaudo.snacks.api.exception;

public class InvalidAccountException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidAccountException(String username) {
		super("Wrong password or invalid account for username: " + username);
	}
}