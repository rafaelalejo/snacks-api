package com.applaudo.snacks.api.service;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import com.applaudo.snacks.api.domain.Like;
import com.applaudo.snacks.api.domain.Product;
import com.applaudo.snacks.api.domain.Purchase;
import com.applaudo.snacks.api.domain.Token;
import com.applaudo.snacks.api.exception.BusinessLogicException;
import com.applaudo.snacks.api.exception.BusinessLogicException.ErrorCode;
import com.applaudo.snacks.api.repository.LikeRepository;
import com.applaudo.snacks.api.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private LikeRepository likeRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private EntityManager entityManager;

	@Transactional
	@Override
	public Product addProduct(Token token, Product product) {
		if (!token.getAccount().isAdminRole()) {
			throw new BusinessLogicException(ErrorCode.ADMIN_ACCESS_DENIED);
		}

		// just in case, avoids duplicated keys and non-contiguous ids
		// null id means that the id generation is handled by the JPA SequenceGenerator
		product.setId(null);

		// override for safety
		product.setLikes(0);

		entityManager.persist(product);
		return product;
	}

	@Override
	public Product findById(Integer id) {
		Optional<Product> p = productRepository.findById(id);

		if (!p.isPresent()) {
			throw new BusinessLogicException(ErrorCode.INVALID_PRODUCT);
		}

		return p.get();
	}

	@Override
	public List<Product> findByName(String name) {
		return productRepository.findByNameContaining(name);
	}

	@Transactional
	@Override
	public void removeProduct(Token token, Integer id) {
		if (!token.getAccount().isAdminRole()) {
			throw new BusinessLogicException(ErrorCode.ADMIN_ACCESS_DENIED);
		}

		Product p = findById(id);

		entityManager.remove(entityManager.contains(p) ? p : entityManager.merge(p));
	}

	@Transactional
	@Override
	public Product updateProductPrice(Token token, Integer id, BigDecimal price) {
		if (!token.getAccount().isAdminRole()) {
			throw new BusinessLogicException(ErrorCode.ADMIN_ACCESS_DENIED);
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
			throw new BusinessLogicException(ErrorCode.ADMIN_ACCESS_DENIED);
		}

		Product p = findById(id);

		p.setStock(stock);

		entityManager.persist(p);

		return p;
	}

	// Products query
	@Override
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	@Override
	public Page<Product> getAllProducts(Integer page, Integer size) {
		PageRequest request = PageRequest.of(page - 1, size, Sort.Direction.ASC, "id");

		return productRepository.findAll(request);
	}

	@Override
	public Page<Product> getAllProductsSorted(Integer page, Integer size, Direction direction, String field) {
		PageRequest request = PageRequest.of(page - 1, size, direction, field);

		return productRepository.findAll(request);

	}

	@Transactional
	@Override
	public void likeProduct(Token token, Integer id) {

		// admins can't increment the like counter of a product
		if (token.getAccount().isAdminRole()) {
			throw new BusinessLogicException(ErrorCode.USER_ACCESS_DENIED);
		}

		Product p = findById(id);

		// a user account can only make one like per product
		Like l = likeRepository.findByAccountAndProduct(token.getAccount(), p);

		if (l == null) {
			l = new Like();

			l.setAccount(token.getAccount());
			l.setProduct(p);

			entityManager.persist(l);

			p.setLikes(p.getLikes() + 1);
			entityManager.persist(p);
		}
	}

	@Transactional
	@Override
	public Product purchaseProduct(Token token, Integer id, Integer quantity) {
		// admins can't buy products
		if (token.getAccount().isAdminRole()) {
			throw new BusinessLogicException(ErrorCode.USER_ACCESS_DENIED);
		}

		Product p = findById(id);

		if (p.getStock() < quantity) {
			throw new BusinessLogicException(ErrorCode.OUTOFSTOCK_PRODUCT);
		}

		Purchase purchase = new Purchase();
		purchase.setAccount(token.getAccount());
		purchase.setProduct(p);
		purchase.setQuantity(quantity);

		// now
		purchase.setTimestamp(Calendar.getInstance());

		p.setStock(p.getStock() - quantity);

		entityManager.persist(p);
		entityManager.persist(purchase);

		return p;
	}
}