package com.applaudo.snacks.api.controller;

import java.math.BigDecimal;
import java.util.List;

import com.applaudo.snacks.api.domain.Product;
import com.applaudo.snacks.api.domain.Token;
import com.applaudo.snacks.api.service.ProductService;
import com.applaudo.snacks.api.service.TokenService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

	@Autowired
	private ProductService productService;

	@Autowired
	private TokenService tokenService;

	@GetMapping("/products")

	// TODO: pagination.
	public List<Product> getAllProducts() {
		return productService.getAllProducts();
	}

	@GetMapping("/products/{id}")
	public Product getProduct(@PathVariable Integer id) {
		return productService.findById(id);
	}

	@PutMapping("/products/{id}")
	public Product updateProduct(@RequestHeader(name = "token") String tokenStr, @PathVariable Integer id,
			@RequestParam(name = "price", required = false) BigDecimal price,
			@RequestParam(name = "stock", required = false) Integer stock) {

		Token token = tokenService.fromString(tokenStr);

		// both can be updated in the same PUT query
		productService.updateProductPrice(token, id, price);
		productService.updateProductStock(token, id, stock);

		// in any case, return the updated product
		return productService.findById(id);
	}

	@PostMapping("/products")
	public Product newProduct(@RequestHeader(name = "token") String tokenStr, @RequestBody Product product) {

		Token token = tokenService.fromString(tokenStr);
		return productService.addProduct(token, product);
	}
}