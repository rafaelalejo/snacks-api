package com.applaudo.snacks.api.repository;

import java.util.UUID;

import com.applaudo.snacks.api.domain.Token;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token, UUID> {
}