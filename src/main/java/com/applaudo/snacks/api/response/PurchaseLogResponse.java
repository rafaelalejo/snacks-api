package com.applaudo.snacks.api.response;

import java.util.List;

import com.applaudo.snacks.api.domain.Purchase;

public class PurchaseLogResponse {
	private List<Purchase> purchases;

	public PurchaseLogResponse(List<Purchase> purchases) {
		this.purchases = purchases;
	}

	/**
	 * @return the purchases
	 */
	public List<Purchase> getPurchases() {
		return purchases;
	}

	/**
	 * @param purchases the purchases to set
	 */
	public void setPurchases(List<Purchase> purchases) {
		this.purchases = purchases;
	}
}