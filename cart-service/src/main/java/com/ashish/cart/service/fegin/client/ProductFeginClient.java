package com.ashish.cart.service.fegin.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "PRODUCT-SERVICE", path = "/api/v1/products")
public interface ProductFeginClient {

	@GetMapping("/exits/{productId}")
	public ResponseEntity<Boolean> productExits(@PathVariable("productId") Long productId);
}
