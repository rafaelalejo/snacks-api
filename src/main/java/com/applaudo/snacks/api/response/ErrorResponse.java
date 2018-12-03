package com.applaudo.snacks.api.response;

import java.util.Calendar;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.http.HttpStatus;

public class ErrorResponse {
	private String message;
	private HttpStatus status;
	private Calendar timestamp;
	private String url;

	public ErrorResponse() {

	}

	public ErrorResponse(HttpStatus status, String message) {
		this.status = status;
		this.message = message;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @return the status
	 */
	public HttpStatus getStatus() {
		return status;
	}

	/**
	 * @return the status code as integer
	 */
	@JsonProperty("status")
	public Integer getStatusCode() {
		return status.value();
	}

	/**
	 * @return the timestamp
	 */
	public Calendar getTimestamp() {
		return timestamp;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(Calendar timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
}