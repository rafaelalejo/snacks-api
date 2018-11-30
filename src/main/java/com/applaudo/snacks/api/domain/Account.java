package com.applaudo.snacks.api.domain;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "public", name = "account")
public class Account {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Integer id;

	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;

	@Column(name = "adminRole")
	private Boolean adminRole;

	/**
	 * @return the id
	 */

	public Integer getId() {
		return id;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @return the role
	 */
	public Boolean isAdminRole() {
		return adminRole;
	}

	// Setters

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @param role the role to set
	 */
	public void setAdminRole(Boolean adminRole) {
		this.adminRole = adminRole;
	}

	// Spring Secutiry neccesary overrides

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
}