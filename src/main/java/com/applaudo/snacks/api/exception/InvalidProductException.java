package com.applaudo.snacks.api.exception;

public class InvalidProductException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidProductException() {
		super("Requested product not found.");
	}
}