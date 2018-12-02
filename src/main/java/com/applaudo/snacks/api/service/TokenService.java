package com.applaudo.snacks.api.service;

import com.applaudo.snacks.api.domain.Account;
import com.applaudo.snacks.api.domain.Token;

public interface TokenService {
	public Token createNewToken(Account account);

	public Token fromString(String tokenStr);

	public void destroyToken(Token token);

	public void destroyToken(String tokenStr);
}