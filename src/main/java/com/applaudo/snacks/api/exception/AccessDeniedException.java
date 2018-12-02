package com.applaudo.snacks.api.exception;

public class AccessDeniedException extends SecurityException {
	private static final long serialVersionUID = 1L;

	public AccessDeniedException() {
		super("You don't have access to this resource.");
	}
}