package com.ashish.product.service.controller;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ashish.product.service.dto.ProductRequestDto;
import com.ashish.product.service.dto.ProductResponseDto;
import com.ashish.product.service.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Product Miceroservicess ", description = "APIs for creating, retrieving, updating and deleting products.")
public class ProductController {

	private final ProductService productService;
	Logger log = LoggerFactory.getLogger(ProductController.class);

	@PostMapping
	@Operation(summary = "Create new product", description = "Creates a new product and saves it in the database.")
	public ResponseEntity<ProductResponseDto> createProduct(@RequestBody ProductRequestDto productRequestDto) {

		ProductResponseDto createdProduct = productService.addProduct(productRequestDto);

		log.info("ProductController :: Product created successfully with name: {}", productRequestDto.getName());

		return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);

	}

	@GetMapping("/exits/{productId}")
	@Operation(summary = "Check product existence", description = "Checks whether a product exists using its product ID.")
	public ResponseEntity<Boolean> productExits(@PathVariable Long productId) {

		boolean productExits = productService.isProductExits(productId);
		log.info("ProductController :: Product existence check completed. ProductId: {}, Exists: {}", productId,
				productExits);

		return ResponseEntity.status(HttpStatus.OK).body(productExits);
	}

	@GetMapping("/{productId}")
	@Operation(summary = "Fetch product by ID", description = "Retrieves product details using the product ID.")
	public ResponseEntity<ProductResponseDto> fetchProdcutById(@PathVariable Long productId) {

		ProductResponseDto fetchedProductById = productService.getProductById(productId);
		log.info("ProductController :: Product fetch successfully with id: {}", productId);
		return ResponseEntity.status(HttpStatus.OK).body(fetchedProductById);
	}

	@GetMapping
	@Operation(summary = "Fetch all products", description = "Retrieves the list of all products available in the database.")
	public ResponseEntity<List<ProductResponseDto>> fetchAllProducts() {

		List<ProductResponseDto> allProducts = productService.getAllProducts();

		log.info("ProductController :: Successfully fetched all products. Total products: {}", allProducts.size());
		return ResponseEntity.status(HttpStatus.OK).body(allProducts);

	}

	@PutMapping("/{productId}")
	@Operation(summary = "Update product", description = "Updates an existing product using its ID and the supplied product details.")
	public ResponseEntity<ProductResponseDto> updateProduct(@PathVariable Long productId,
			@RequestBody ProductRequestDto productRequestDto) {

		ProductResponseDto updatedProduct = productService.updateProduct(productId, productRequestDto);
		log.info("ProductController :: product update Successfully. productId:{}, productName:{} ", productId,
				productRequestDto.getName());
		return ResponseEntity.status(HttpStatus.OK).body(updatedProduct);
	}

	@DeleteMapping("/{productId}")
	@Operation(summary = "Delete product", description = "Deletes an existing product using its product ID.")
	public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {

		productService.deleteProduct(productId);
		log.info("ProductController :: Product delete successfully with id: {}", productId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

	}

}
