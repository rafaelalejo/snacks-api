package com.applaudo.snacks.api.demo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties
public class YAMLConfig {
	private String dbConnectionString;

	/**
	 * @return the dbConnectionString
	 */
	public String getDbConnectionString() {
		return dbConnectionString;
	}

	/**
	 * @param dbConnectionString the dbConnectionString to set
	 */
	public void setDbConnectionString(String dbConnectionString) {
		this.dbConnectionString = dbConnectionString;
	}
}