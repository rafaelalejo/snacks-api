package com.applaudo.snacks.api.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import com.applaudo.snacks.api.domain.Product;
import com.applaudo.snacks.api.domain.Token;
import com.applaudo.snacks.api.exception.AccessDeniedException;
import com.applaudo.snacks.api.exception.InvalidProductException;
import com.applaudo.snacks.api.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private EntityManager entityManager;

	@Transactional
	@Override
	public Product addProduct(Token token, Product product) {
		entityManager.persist(product);
		return product;
	}

	@Override
	public Product findById(Integer id) {
		Optional<Product> p = productRepository.findById(id);

		if (!p.isPresent()) {
			throw new InvalidProductException();
		}

		return p.get();
	}

	@Override
	public Product findByName(String name) {
		return null;
	}

	@Transactional
	@Override
	public void removeProduct(Token token, Integer id) {
		if (!token.getAccount().isAdminRole()) {
			throw new AccessDeniedException();
		}

		Product p = findById(id);

		entityManager.remove(entityManager.contains(p) ? p : entityManager.merge(p));
	}

	@Transactional
	@Override
	public Product updateProductPrice(Token token, Integer id, BigDecimal price) {
		if (!token.getAccount().isAdminRole()) {
			throw new AccessDeniedException();
		}

		if (price == null) {
			return null;
		}

		Product p = findById(id);

		p.setPrice(price);

		entityManager.persist(p);

		return p;
	}

	@Transactional
	@Override
	public Product updateProductStock(Token token, Integer id, Integer stock) {
		if (!token.getAccount().isAdminRole()) {
			throw new AccessDeniedException();
		}

		if (stock == null) {
			return null;
		}

		Product p = findById(id);

		p.setStock(stock);

		entityManager.persist(p);

		return p;
	}

	@Override
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

}