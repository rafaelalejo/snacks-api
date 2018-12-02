package com.applaudo.snacks.api.service;

import com.applaudo.snacks.api.domain.Account;

public interface AccountService {
	public Account getAccountFromCredentials(String username, String password);
}