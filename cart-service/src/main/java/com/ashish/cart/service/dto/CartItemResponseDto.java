package com.ashish.cart.service.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CartItemResponseDto {
	
	private Long cartId;
	private Integer userId; // user Miceroservice id
	private Long id; // product id
	private Integer quantity;
}
