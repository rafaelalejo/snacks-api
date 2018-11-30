package com.applaudo.snacks.api.repository;

import com.applaudo.snacks.api.domain.Account;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Integer> {
	public Account findByUsernameAndPassword(String username, String password);
}