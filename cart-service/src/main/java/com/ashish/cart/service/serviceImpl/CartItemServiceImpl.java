package com.ashish.cart.service.serviceImpl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.ashish.cart.service.dto.CartItemRequestDto;
import com.ashish.cart.service.dto.CartItemResponseDto;
import com.ashish.cart.service.model.CartItem;
import com.ashish.cart.service.repository.CartItemRepository;
import com.ashish.cart.service.service.CartItemService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService {

	private final CartItemRepository cartItemRepository;

	@Override
	public CartItemResponseDto addToCartItem(CartItemRequestDto cartItemRequestDto) {

		// TODO: product services api need to call to fetch product information
		// RestTempalte
		// TODO: user service api seed to call to fetch user information

		CartItem cartItem = new CartItem();
		BeanUtils.copyProperties(cartItemRequestDto, cartItem);

		CartItem dbCartItem = cartItemRepository.save(cartItem);
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
