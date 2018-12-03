package com.applaudo.snacks.api.exception;

import java.util.HashMap;

import com.applaudo.snacks.api.response.ErrorResponse;

import org.springframework.http.HttpStatus;

public class BusinessLogicException extends RuntimeException {

	public static enum ErrorCode {
		INVALID_ACCOUNT, INVALID_PRODUCT, INVALID_TOKEN, USER_ACCESS_DENIED, ADMIN_ACCESS_DENIED, OUTOFSTOCK_PRODUCT,
	};

	private static HashMap<ErrorCode, ErrorResponse> errorMap = new HashMap<>();

	static {
		// Invalid account:
		errorMap.put(ErrorCode.INVALID_ACCOUNT,
				new ErrorResponse(HttpStatus.NOT_FOUND, "Invalid username or password"));

		// Invalid product:
		errorMap.put(ErrorCode.INVALID_PRODUCT, new ErrorResponse(HttpStatus.NOT_FOUND, "Invalid product id"));

		// Invalid token:
		errorMap.put(ErrorCode.INVALID_TOKEN, new ErrorResponse(HttpStatus.UNAUTHORIZED, "Invalid or expired token"));

		// Unauthorized user:
		errorMap.put(ErrorCode.USER_ACCESS_DENIED,
				new ErrorResponse(HttpStatus.UNAUTHORIZED, "Only registered users can access this feature"));

		// Admin access denied:
		errorMap.put(ErrorCode.ADMIN_ACCESS_DENIED,
				new ErrorResponse(HttpStatus.UNAUTHORIZED, "Only administrators can access this feature"));

		// Product out of stock
		errorMap.put(ErrorCode.OUTOFSTOCK_PRODUCT, new ErrorResponse(HttpStatus.CONFLICT, "Product out of stock"));
	}

	private static final long serialVersionUID = 1L;

	private HttpStatus httpStatus;

	public BusinessLogicException(ErrorCode code) {
		super(errorMap.get(code).getMessage());
		this.httpStatus = errorMap.get(code).getStatus();
	}

	/**
	 * @param httpStatus the httpStatus to set
	 */
	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	/**
	 * @return the httpStatus
	 */
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

}