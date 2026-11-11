package com.ashish.product.service.service;

import java.util.List;

import com.ashish.product.service.dto.ProductRequestDto;
import com.ashish.product.service.dto.ProductResponseDto;

public interface ProductService {

	ProductResponseDto addProduct(ProductRequestDto productRequestDto);

	boolean isProductExits(Long productId);

	ProductResponseDto getProductById(Long productId);

	List<ProductResponseDto> getAllProducts();

	ProductResponseDto updateProduct(Long productId, ProductRequestDto productRequestDto);

	void deleteProduct(Long productId);

}
