package com.applaudo.snacks.api.controller;

import com.applaudo.snacks.api.domain.Token;
import com.applaudo.snacks.api.response.AccountResponse;
import com.applaudo.snacks.api.response.AuthenticationResponse;
import com.applaudo.snacks.api.response.CustomMessageResponse;
import com.applaudo.snacks.api.service.AccountService;
import com.applaudo.snacks.api.service.TokenService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

	@Autowired
	private TokenService tokenService;

	@Autowired
	private AccountService accountService;

	@RequestMapping("/login")
	public AuthenticationResponse login(@RequestHeader(value = "username") String username,
			@RequestHeader(value = "password") String password) {

		Token t = tokenService.createNewToken(accountService.getAccountFromCredentials(username, password));
		return new AuthenticationResponse(t);
	}

	@RequestMapping("/logout")
	public CustomMessageResponse logout(@RequestHeader(value = "token") String tokenStr) {
		tokenService.destroyToken(tokenStr);

		return new CustomMessageResponse("Logged out");
	}

	@RequestMapping("/account")
	public AccountResponse accountInfo(@RequestHeader(value = "token") String tokenStr) {
		Token t = tokenService.fromString(tokenStr);
		return new AccountResponse(t.getAccount());
	}
}