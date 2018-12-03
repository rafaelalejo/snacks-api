package com.applaudo.snacks.api.repository;

import java.util.List;

import com.applaudo.snacks.api.domain.Product;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	public List<Product> findByNameContaining(String name);
}