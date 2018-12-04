package com.applaudo.snacks.api.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;

import com.applaudo.snacks.api.domain.Product;
import com.applaudo.snacks.api.domain.Token;
import com.applaudo.snacks.api.response.PaginatedProductsResponse;
import com.applaudo.snacks.api.service.ProductService;
import com.applaudo.snacks.api.service.TokenService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
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

	// Product search

	@GetMapping("/products")
	public List<Product> getAllProducts() {
		return productService.getAllProducts();
	}

	// Pageable responses
	@GetMapping("/products/page")
	public PaginatedProductsResponse getAllProductsByPage(@RequestParam(name = "page") Integer page,
			@RequestParam(name = "size") Integer size) {
		return new PaginatedProductsResponse(productService.getAllProducts(page, size));
	}

	@GetMapping("/products/page/sorted/name")
	public PaginatedProductsResponse getAllProductsPaginatedSortedByNameAsc(
			@Min(1) @RequestParam(name = "page") Integer page, @Min(1) @RequestParam(name = "size") Integer size) {
		return new PaginatedProductsResponse(productService.getAllProductsSorted(page, size, Direction.ASC, "name"));
	}

	@GetMapping("/products/page/sorted/likes")
	public PaginatedProductsResponse getAllProductsPaginatedSortedByLikesAsc(
			@Min(1) @RequestParam(name = "page") Integer page, @Min(1) @RequestParam(name = "size") Integer size) {
		return new PaginatedProductsResponse(productService.getAllProductsSorted(page, size, Direction.ASC, "likes"));
	}

	@GetMapping("/products/page/sorted/name/desc")
	public PaginatedProductsResponse getAllProductsPaginatedSortedByNameDesc(
			@Min(1) @RequestParam(name = "page") Integer page, @Min(1) @RequestParam(name = "size") Integer size) {
		return new PaginatedProductsResponse(productService.getAllProductsSorted(page, size, Direction.DESC, "name"));
	}

	@GetMapping("/products/page/sorted/likes/desc")
	public PaginatedProductsResponse getAllProductsPaginatedSortedByLikesDesc(
			@Min(1) @RequestParam(name = "page") Integer page, @Min(1) @RequestParam(name = "size") Integer size) {
		return new PaginatedProductsResponse(productService.getAllProductsSorted(page, size, Direction.DESC, "likes"));
	}

	@GetMapping("/products/{id}")
	public Product getProduct(@PathVariable Integer id) {
		return productService.findById(id);
	}

	@GetMapping("/products/search")
	public List<Product> getProductsFromSearch(@RequestParam(name = "name") String name) {
		return productService.findByName(name);
	}

	// Product update, admin related task

	@PutMapping("/products/{id}/price")
	public Product updateProductPrice(@RequestHeader(name = "token") String tokenStr, @PathVariable Integer id,
			@DecimalMin(value = "0.0", inclusive = false) @RequestParam(name = "price") BigDecimal price) {

		Token token = tokenService.fromString(tokenStr);

		return productService.updateProductPrice(token, id, price);
	}

	@PutMapping("/products/{id}/stock")
	public Product updateProductStock(@RequestHeader(name = "token") String tokenStr, @PathVariable Integer id,
			@Min(0) @RequestParam(name = "stock") Integer stock) {

		Token token = tokenService.fromString(tokenStr);

		return productService.updateProductStock(token, id, stock);
	}

	@PostMapping("/products")
	public Product newProduct(@RequestHeader(name = "token") String tokenStr, @Valid @RequestBody Product product) {

		Token token = tokenService.fromString(tokenStr);
		return productService.addProduct(token, product);
	}

	// Client related tasks

	@PostMapping("/products/{id}/like")

	public Product likeProduct(@RequestHeader(name = "token") String tokenStr, @PathVariable Integer id) {

		Token token = tokenService.fromString(tokenStr);
		productService.likeProduct(token, id);

		// product with updated likes counter
		return productService.findById(id);
	}

	@PostMapping("/products/{id}/purchase")
	public Product purchaseProduct(@RequestHeader(name = "token") String tokenStr, @PathVariable Integer id,
			@Min(1) @RequestParam(name = "quantity") Integer quantity) {
		Token token = tokenService.fromString(tokenStr);

		return productService.purchaseProduct(token, id, quantity);
	}
}