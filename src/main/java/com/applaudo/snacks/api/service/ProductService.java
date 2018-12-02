package com.applaudo.snacks.api.service;

import java.math.BigDecimal;
import java.util.List;

import com.applaudo.snacks.api.domain.Product;
import com.applaudo.snacks.api.domain.Token;

public interface ProductService {

	public Product addProduct(Token token, Product product);

	public void removeProduct(Token token, Integer id);

	public Product updateProductPrice(Token token, Integer id, BigDecimal price);

	public Product updateProductStock(Token token, Integer id, Integer stock);

	public Product findById(Integer id);

	public Product findByName(String name);

	public List<Product> getAllProducts();
}