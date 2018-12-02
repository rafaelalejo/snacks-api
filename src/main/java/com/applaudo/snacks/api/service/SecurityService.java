package com.applaudo.snacks.api.service;

import java.math.BigDecimal;

import com.applaudo.snacks.api.domain.Token;

public interface SecurityService {
	public void logProductUpdate(Token token, Integer id, BigDecimal newPrice);
}
