package com.applaudo.snacks.api.service;

import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import com.applaudo.snacks.api.domain.Account;
import com.applaudo.snacks.api.domain.Token;
import com.applaudo.snacks.api.exception.InvalidTokenException;
import com.applaudo.snacks.api.repository.TokenRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenServiceImpl implements TokenService {
	private final HashMap<String, Token> tempDB = new HashMap<>();

	@Autowired
	private EntityManager entityManager;

	@Autowired
	private TokenRepository tokenRepository;

	// @Autowired
	// private AccountService accountService;

	@Override
	@Transactional
	public Token createNewToken(Account account) {
		Token t = new Token(account);

		// tempDB.put(t.getId().toString(), t);
		entityManager.persist(t);

		return t;
	}

	@Override
	@Transactional
	public void destroyToken(Token token) {
		entityManager.remove(entityManager.contains(token) ? token : entityManager.merge(token));
	}

	@Override
	@Transactional
	public void destroyToken(String tokenStr) {
		Token t = fromString(tokenStr);

		// Cache
		if (tempDB.containsKey(tokenStr)) {
			tempDB.remove(tokenStr);
		}

		destroyToken(t);
	}

	@Override
	public Token fromString(String tokenStr) {
		UUID uuidFrom;

		try {
			uuidFrom = UUID.fromString(tokenStr);
		} catch (Exception e) {
			throw new InvalidTokenException();
		}

		if (tempDB.containsKey(tokenStr)) {
			System.out.println("[CACHE][Token]: hit!");
			return tempDB.get(tokenStr);
		}

		System.out.println("[CACHE][Token]: fail!");

		Optional<Token> t = tokenRepository.findById(uuidFrom);

		if (!t.isPresent()) {
			throw new InvalidTokenException();
		}

		// Cache entry
		tempDB.put(tokenStr, t.get());

		return t.get();
	}
}