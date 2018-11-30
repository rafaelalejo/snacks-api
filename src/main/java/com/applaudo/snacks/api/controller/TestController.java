package com.applaudo.snacks.api.controller;

import com.applaudo.snacks.api.exception.InvalidAccountException;

import javax.persistence.EntityManager;

import com.applaudo.snacks.api.domain.Account;
import com.applaudo.snacks.api.domain.Token;
import com.applaudo.snacks.api.repository.AccountRepository;
import com.applaudo.snacks.api.response.AuthenticationResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private EntityManager em;

	@RequestMapping("/login")
	public AuthenticationResponse login(@RequestHeader(value = "username") String username,
			@RequestHeader(value = "password") String password) {

		Account c = accountRepository.findByUsernameAndPassword(username, password);

		if (c == null) {
			throw new InvalidAccountException(username);
		}

		Token t = new Token(c);

		em.persist(t);

		return new AuthenticationResponse(t);
	}
}