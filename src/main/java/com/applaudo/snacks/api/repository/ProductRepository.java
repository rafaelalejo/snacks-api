package com.applaudo.snacks.api.repository;

import com.applaudo.snacks.api.domain.Product;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}