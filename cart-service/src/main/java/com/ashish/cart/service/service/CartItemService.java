package com.ashish.cart.service.service;

import java.util.List;

import com.ashish.cart.service.dto.CartItemRequestDto;
import com.ashish.cart.service.dto.CartItemResponseDto;

public interface CartItemService {

	CartItemResponseDto addToCartItem(CartItemRequestDto cartItemRequestDto);

	List<CartItemResponseDto> getUserCartItem(Integer userId);
	
	void removeCartItem(Integer userId ,Long id);
	
	CartItemResponseDto	updateQuantity(CartItemRequestDto cartItemRequestDto);
	
	void clearCartItem(Integer userId );
	
	// Note:
	// Integer userId represents the unique identifier of a User in the User Microservice.
	// Long id represents the unique identifier of a Product in the Product Microservice.
}
