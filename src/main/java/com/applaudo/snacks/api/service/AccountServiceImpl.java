package com.applaudo.snacks.api.service;

import com.applaudo.snacks.api.domain.Account;
import com.applaudo.snacks.api.exception.BusinessLogicException;
import com.applaudo.snacks.api.exception.BusinessLogicException.ErrorCode;
import com.applaudo.snacks.api.repository.AccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
	@Autowired
	private AccountRepository accountRepository;

	@Override
	public Account getAccountFromCredentials(String username, String password) {
		Account ac = accountRepository.findByUsernameAndPassword(username, password);

		if (ac == null) {
			throw new BusinessLogicException(ErrorCode.INVALID_ACCOUNT);
		}

		return ac;
	}
}