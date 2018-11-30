package com.applaudo.snacks.api.response;

import com.applaudo.snacks.api.domain.Token;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthenticationResponse {
	private String UUIDString;

	public AuthenticationResponse(String UUIDString) {
		this.UUIDString = UUIDString;
	}

	public AuthenticationResponse(Token token) {
		this.UUIDString = token.getId().toString();
	}

	/**
	 * @param uUIDString the uUIDString to set
	 */
	public void setUUIDString(String uUIDString) {
		UUIDString = uUIDString;
	}

	/**
	 * @return the uUIDString
	 */

	@JsonProperty("token")
	public String getUUIDString() {
		return UUIDString;
	}
}