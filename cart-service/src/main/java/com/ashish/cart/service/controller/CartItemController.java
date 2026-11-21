package com.ashish.cart.service.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ashish.cart.service.dto.CartItemRequestDto;
import com.ashish.cart.service.dto.CartItemResponseDto;
import com.ashish.cart.service.service.CartItemService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
@Tag(name = "cart managemet", description = "APIs for adding, viewing, updating and removing cart items.")
public class CartItemController {

	private final CartItemService cartItemService;

	@PostMapping
	@Operation(summary = "Add item to cart", description = "User can add item in cart ")
	public ResponseEntity<CartItemResponseDto> addToCart(@Valid @RequestBody CartItemRequestDto cartItemRequestDto) {
		CartItemResponseDto addedToCart = cartItemService.addToCartItem(cartItemRequestDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(addedToCart);

	}

	@GetMapping("/{userId}")
	@Operation(summary = "Fetch user cart items ", description = " Retrieves all cart items associated with a specific user.")
	public ResponseEntity<List<CartItemResponseDto>> fetchUserCartItem(@PathVariable Integer userId) {
		List<CartItemResponseDto> fetchedUserCartItem = cartItemService.getUserCartItem(userId);
		return ResponseEntity.status(HttpStatus.OK).body(fetchedUserCartItem);
	}

	@PutMapping
	@Operation(summary = "Update user cart items ", description = " update  cart items associated with a specific user.")
	public ResponseEntity<CartItemResponseDto> updateQuantity(@RequestBody CartItemRequestDto cartItemRequestDto) {
		CartItemResponseDto updatedQuantity = cartItemService.updateQuantity(cartItemRequestDto);
		return ResponseEntity.status(HttpStatus.OK).body(updatedQuantity);

	}

	@DeleteMapping("/clear{userId}")
	@Operation(summary = "clear cart item", description = "clear a specific cart item belonging to a user.")
	public ResponseEntity<Void> clearCartItem(@PathVariable Integer userId) {
		cartItemService.clearCartItem(userId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@DeleteMapping("/{userId}/{id}")
	@Operation(summary = "Remove cart item", description = "Removes a specific cart item belonging to a user.")
	public ResponseEntity<Void> removeCartItem(@PathVariable Integer userId,@PathVariable Long id) {
		cartItemService.removeCartItem(userId, id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}
