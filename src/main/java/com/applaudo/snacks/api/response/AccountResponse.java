package com.applaudo.snacks.api.response;

import com.applaudo.snacks.api.domain.Account;

public class AccountResponse {
	private String username;
	private String role;

	public AccountResponse(Account account) {
		this.username = account.getUsername();
		this.role = account.isAdminRole() ? "administrator" : "user";
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}
}