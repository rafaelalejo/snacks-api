package com.applaudo.snacks.api.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.applaudo.snacks.api.repository")
@EntityScan(basePackages = "com.applaudo.snacks.api.domain")
public class JpaConfiguration {}