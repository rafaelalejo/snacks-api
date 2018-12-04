package com.applaudo.snacks.api.service;

import java.math.BigDecimal;
import java.util.List;

import com.applaudo.snacks.api.domain.PriceUpdate;
import com.applaudo.snacks.api.domain.Product;
import com.applaudo.snacks.api.domain.Purchase;
import com.applaudo.snacks.api.domain.Token;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort.Direction;

public interface ProductService {

	public Product addProduct(Token token, Product product);

	public void removeProduct(Token token, Integer id);

	public Product updateProductPrice(Token token, Integer id, BigDecimal price);

	public Product updateProductStock(Token token, Integer id, Integer stock);

	public Product findById(Integer id);

	public List<Product> findByName(String name);

	public Page<Product> getAllProducts(Integer page, Integer size);

	public Page<Product> getAllProductsSorted(Integer page, Integer size, Direction direction, String field);

	public List<Product> getAllProducts();

	public void likeProduct(Token token, Integer id);

	public Product purchaseProduct(Token token, Integer id, Integer quantity);

	public List<Purchase> purchaseLogs(Token token);

	public List<PriceUpdate> priceUpdateLogs(Token token);
}