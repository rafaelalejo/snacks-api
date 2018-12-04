package com.applaudo.snacks.api.repository;

import com.applaudo.snacks.api.domain.PriceUpdate;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceUpdateRepository extends JpaRepository<PriceUpdate, Integer> {

}