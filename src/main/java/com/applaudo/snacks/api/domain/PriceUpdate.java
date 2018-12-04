package com.applaudo.snacks.api.domain;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(schema = "public", name = "price_update")
public class PriceUpdate {
	@Id
	@GeneratedValue(generator = "price_update_id_seq", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "price_update_id_seq", sequenceName = "public.price_update_id_seq", initialValue = 1, allocationSize = 1)
	private Integer id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "accountid", referencedColumnName = "id")
	private Account account;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "productid", referencedColumnName = "id")
	private Product product;

	@Column(name = "oldprice")
	private BigDecimal oldPrice;

	@Column(name = "newprice")
	private BigDecimal newPrice;

	@Column(name = "timestamp")
	private Calendar timestamp;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @return the account
	 */
	public Account getAccount() {
		return account;
	}

	/**
	 * @return the product
	 */
	public Product getProduct() {
		return product;
	}

	/**
	 * @return the oldPrice
	 */
	public BigDecimal getOldPrice() {
		return oldPrice;
	}

	/**
	 * @return the newPrice
	 */
	public BigDecimal getNewPrice() {
		return newPrice;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the timestamp
	 */
	public Calendar getTimestamp() {
		return timestamp;
	}

	/**
	 * @param account the account to set
	 */
	public void setAccount(Account account) {
		this.account = account;
	}

	/**
	 * @param product the product to set
	 */
	public void setProduct(Product product) {
		this.product = product;
	}

	/**
	 * @param oldPrice the oldPrice to set
	 */
	public void setOldPrice(BigDecimal oldPrice) {
		this.oldPrice = oldPrice;
	}

	/**
	 * @param newPrice the newPrice to set
	 */
	public void setNewPrice(BigDecimal newPrice) {
		this.newPrice = newPrice;
	}

	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(Calendar timestamp) {
		this.timestamp = timestamp;
	}
}