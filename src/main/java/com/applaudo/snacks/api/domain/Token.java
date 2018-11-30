package com.applaudo.snacks.api.domain;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(schema = "public", name = "token")
public class Token {

	@Id
	private UUID id;

	// Fetch on object instanciation
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "userid", referencedColumnName = "id")
	private Account account;

	public Token(Account account) {
		this.id = genUUID();
		this.account = account;
	}

	// Token id generation using uuid standard
	// Default: random value
	private UUID genUUID() {
		return UUID.randomUUID();
	}

	/**
	 * @return the id
	 */
	public UUID getId() {
		return id;
	}

	/**
	 * @return the account
	 */
	public Account getAccount() {
		return account;
	}

	/**
	 * @param account the account to set
	 */
	public void setAccount(Account account) {
		this.account = account;
	}
}