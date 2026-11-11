package com.ashish.product.service.serviceimpl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.ashish.product.service.dto.ProductRequestDto;
import com.ashish.product.service.dto.ProductResponseDto;
import com.ashish.product.service.model.Product;
import com.ashish.product.service.repository.ProductRepository;
import com.ashish.product.service.service.ProductService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

	private final ProductRepository productRepository;

	@Override
	public ProductResponseDto addProduct(ProductRequestDto productRequestDto) {

		Product product = new Product();
		BeanUtils.copyProperties(productRequestDto, product);

		Product dbProduct = productRepository.save(product);

		return mapToDto(dbProduct);
	}

	public ProductResponseDto mapToDto(Product product) {
		ProductResponseDto response = new ProductResponseDto();
		BeanUtils.copyProperties(product, response);
		return response;
	}

	@Override
	public boolean isProductExits(Long productId) {

		return productRepository.existsById(productId);
	}

	@Override
	public ProductResponseDto getProductById(Long productId) {
		// Step 1: Find the existing product in the database using productId
		return productRepository.findById(productId)
				// Step 2: If the product exists, convert Product Entity into ProductResponseDto
				.map(p -> mapToDto(p))
				// Step 3: If the product does not exist, throw an exception
				.orElseThrow(() -> new RuntimeException("product not found in db" + productId));
	}

	@Override
	public List<ProductResponseDto> getAllProducts() {
		return productRepository.findAll()
				.stream()
				.map(this::mapToDto)
				.toList();
	}

	@Override
	public ProductResponseDto updateProduct(Long productId, ProductRequestDto productRequestDto) {
		// Step 1: Find existing product by ID
		Product product = productRepository.findById(productId)
				.orElseThrow(() -> new RuntimeException("product not found in db" + productId));
		// Step 2: Copy request DTO properties into existing Product entity
		BeanUtils.copyProperties(productRequestDto, product);
		// Step 3: Save updated product into database
		Product saveProduct = productRepository.save(product);
		// Step 4: Convert updated Product entity into Response DTO
		return mapToDto(saveProduct);
	}

	@Override
	public void deleteProduct(Long productId) {
		// Step 1: Find existing product by ID
		Product product = productRepository.findById(productId)
				.orElseThrow(() -> new RuntimeException("product not found in db" + productId));
		// Step 2: delete product into database
		productRepository.delete(product);
	}

}
