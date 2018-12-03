package com.applaudo.snacks.api.repository;

import com.applaudo.snacks.api.domain.Purchase;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {

}