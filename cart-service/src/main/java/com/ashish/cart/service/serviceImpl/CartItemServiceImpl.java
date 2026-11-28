package com.ashish.cart.service.serviceImpl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ashish.cart.service.dto.CartItemRequestDto;
import com.ashish.cart.service.dto.CartItemResponseDto;
import com.ashish.cart.service.dto.UserDto;
import com.ashish.cart.service.fegin.client.ProductFeginClient;
import com.ashish.cart.service.fegin.client.UserFeginClient;
import com.ashish.cart.service.model.CartItem;
import com.ashish.cart.service.repository.CartItemRepository;
import com.ashish.cart.service.service.CartItemService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CartItemServiceImpl implements CartItemService {

	private final CartItemRepository cartItemRepository;

	private final ProductFeginClient productFeginClient;

	private final UserFeginClient userFeginClient;

	@Override
	public CartItemResponseDto addToCartItem(CartItemRequestDto cartItemRequestDto) {

		// product services API need to call to fetch product information weather
		// product exit or not
		// Synchronous communication

		// Step 1: Validate product using FeignClient
		log.info("Calling PRODUCT-SERVICE | Product ID: {}", cartItemRequestDto.getId());

		ResponseEntity<Boolean> productExits = productFeginClient.productExits(cartItemRequestDto.getId());
		Boolean exists = productExits.getBody();
		if (!Boolean.TRUE.equals(exists)) {

			throw new RuntimeException("Product does not exist in database. Product ID: " + cartItemRequestDto.getId());
		}
		// TODO: user service api seed to call to fetch user information
		// Step 2: Validate user using FeignClient
		log.info("Calling USER-SERVICE | User ID: {}", cartItemRequestDto.getUserId());

		ResponseEntity<UserDto> fetchUser = userFeginClient.fetchUser(cartItemRequestDto.getUserId().intValue());

		if (fetchUser == null) {
			throw new RuntimeException("user  does not exist in database. User ID: " + cartItemRequestDto.getUserId());
		}

		CartItem cartItem = new CartItem();
		BeanUtils.copyProperties(cartItemRequestDto, cartItem);

		CartItem dbCartItem = cartItemRepository.save(cartItem);

		log.info("Cart item saved | Product ID: {} | Quantity: {} | User ID: {}", dbCartItem.getId(),
				dbCartItem.getQuantity(), dbCartItem.getUserId());
		return mapToResponseDto(dbCartItem);
	}

	private CartItemResponseDto mapToResponseDto(CartItem dbCartItem) {
		CartItemResponseDto response = new CartItemResponseDto();
		BeanUtils.copyProperties(dbCartItem, response);
		return response;
	}

	@Override
	public List<CartItemResponseDto> getUserCartItem(Integer userId) {
		// TODO Auto-generated method stub
		return cartItemRepository.findByUserId(userId).stream().map(this::mapToResponseDto).toList();

	}

	@Override
	@Transactional
	public void removeCartItem(Integer userId, Long id) {
		cartItemRepository.deleteByUserIdAndId(userId, id);

	}

	@Override
	public CartItemResponseDto updateQuantity(CartItemRequestDto cartItemRequestDto) {
		// userid and product id requred to update cart so i dont have inbuild method i
		// create custum method in repo

		CartItem cartItem = cartItemRepository
				.findByUserIdAndId(cartItemRequestDto.getUserId(), cartItemRequestDto.getId())
				.orElseThrow(() -> new RuntimeException("item not in the cart "));

		cartItem.setQuantity(cartItemRequestDto.getQuantity());
		CartItem dbCart = cartItemRepository.save(cartItem);
		return mapToResponseDto(dbCart);
	}

	@Override
	@Transactional
	public void clearCartItem(Integer userId) {
		cartItemRepository.deleteByUserId(userId);

	}

}
