package com.applaudo.snacks.api.response;

import java.util.List;

import com.applaudo.snacks.api.domain.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.data.domain.Page;

public class PaginatedProductsResponse {
	Page<Product> page;

	public PaginatedProductsResponse(Page<Product> page) {
		this.page = page;
	}

	/**
	 * @return the page
	 */
	@JsonIgnore
	public Page<Product> getPage() {
		return page;
	}

	/**
	 * @param page the page to set
	 */
	public void setPage(Page<Product> page) {
		this.page = page;
	}

	@JsonProperty("page_number")
	public Integer getNumber() {
		return page.getNumber() + 1;
	}

	@JsonProperty("pages")
	public Integer getTotalPages() {
		return page.getTotalPages();
	}

	@JsonProperty("size")
	public Integer getSize() {
		return page.getSize();
	}

	@JsonProperty("products")
	public List<Product> getProducts() {
		return page.getContent();
	}

}