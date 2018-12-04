package com.applaudo.snacks.api.advice;

import java.sql.SQLException;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import com.applaudo.snacks.api.exception.BusinessLogicException;
import com.applaudo.snacks.api.response.ErrorResponse;

import org.springframework.core.NestedExceptionUtils;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionAdvice {

	// Business logic related errors
	@ExceptionHandler(value = { BusinessLogicException.class })
	@Order(Ordered.HIGHEST_PRECEDENCE)
	public ErrorResponse tokenNotFoundHandler(HttpServletRequest request, HttpServletResponse response,
			BusinessLogicException exception) {

		ErrorResponse errorRes = new ErrorResponse();

		// url must be converted from StringBuffer

		// error body
		errorRes.setMessage(exception.getMessage());
		errorRes.setStatus(exception.getHttpStatus());
		errorRes.setTimestamp(Calendar.getInstance());
		errorRes.setUrl(request.getRequestURL().toString());

		response.setStatus(errorRes.getStatusCode());

		return errorRes;
	}

	// Constraint violation errors
	@ExceptionHandler(value = ConstraintViolationException.class)
	public ErrorResponse constraintViolationHandler(HttpServletRequest request, HttpServletResponse response,
			ConstraintViolationException exception) {

		ErrorResponse errorRes = new ErrorResponse();

		// error body
		String constraintsMessage = new String();

		// for any type of object
		for (ConstraintViolation<?> violation : exception.getConstraintViolations()) {
			constraintsMessage = constraintsMessage
					.concat(violation.getPropertyPath().toString() + ": " + violation.getMessage() + "; ");
		}

		errorRes.setMessage(constraintsMessage);
		errorRes.setStatus(HttpStatus.BAD_REQUEST);
		errorRes.setTimestamp(Calendar.getInstance());
		errorRes.setUrl(request.getRequestURL().toString());

		response.setStatus(errorRes.getStatusCode());
		return errorRes;
	}

	// Constraint violation errors
	@ExceptionHandler(value = SQLException.class)
	public ErrorResponse databaseExceptionHandler(HttpServletRequest request, HttpServletResponse response,
			SQLException exception) {

		ErrorResponse errorRes = new ErrorResponse();

		errorRes.setMessage(exception.getMessage());
		errorRes.setStatus(HttpStatus.CONFLICT);
		errorRes.setTimestamp(Calendar.getInstance());
		errorRes.setUrl(request.getRequestURL().toString());

		response.setStatus(errorRes.getStatusCode());
		return errorRes;
	}

	// Default handler for unknown errors
	@ExceptionHandler
	@Order(Ordered.LOWEST_PRECEDENCE)
	public ErrorResponse defaultHandler(HttpServletRequest request, HttpServletResponse response, Exception exception) {

		// if the root cause is a constraint violation
		if (NestedExceptionUtils.getRootCause(exception) instanceof ConstraintViolationException) {
			return constraintViolationHandler(request, response,
					(ConstraintViolationException) NestedExceptionUtils.getRootCause(exception));
		}

		// if the root cause is JPA related
		if (NestedExceptionUtils.getRootCause(exception) instanceof SQLException) {
			return databaseExceptionHandler(request, response,
					(SQLException) NestedExceptionUtils.getRootCause(exception));
		}

		// otherwise, any kind of unhandled internal error
		ErrorResponse errorRes = new ErrorResponse();

		// error body
		errorRes.setMessage("Internal server error");
		errorRes.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		errorRes.setTimestamp(Calendar.getInstance());
		errorRes.setUrl(request.getRequestURL().toString());

		response.setStatus(errorRes.getStatusCode());

		return errorRes;
	}
}