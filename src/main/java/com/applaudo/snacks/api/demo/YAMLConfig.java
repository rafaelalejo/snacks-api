package com.applaudo.snacks.api.demo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties
public class YAMLConfig {
	private String serverAddress;
	private String dbName;
	private String dbUser;
	private String dbPassword;

	/**
	 * @return the serverAddress
	 */
	public String getServerAddress() {
		return serverAddress;
	}

	/**
	 * @return the dbName
	 */
	public String getDbName() {
		return dbName;
	}

	/**
	 * @return the dbUser
	 */
	public String getDbUser() {
		return dbUser;
	}

	/**
	 * @return the dbPassword
	 */
	public String getDbPassword() {
		return dbPassword;
	}

	/**
	 * @param serverAddress the serverAddress to set
	 */
	public void setServerAddress(String serverAddress) {
		this.serverAddress = serverAddress;
	}

	/**
	 * @param dbName the dbName to set
	 */
	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	/**
	 * @param dbPassword the dbPassword to set
	 */
	public void setDbPassword(String dbPassword) {
		this.dbPassword = dbPassword;
	}

	/**
	 * @param dbUser the dbUser to set
	 */
	public void setDbUser(String dbUser) {
		this.dbUser = dbUser;
	}
}