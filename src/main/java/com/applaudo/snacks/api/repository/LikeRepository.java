package com.applaudo.snacks.api.repository;

import com.applaudo.snacks.api.domain.Account;
import com.applaudo.snacks.api.domain.Like;
import com.applaudo.snacks.api.domain.Product;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Integer> {
	public Like findByAccountAndProduct(Account Account, Product product);
}