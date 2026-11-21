package com.ashish.product.service.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestDto {

	@NotBlank(message = "Product name is required")
	@Size(min = 3, max = 100, message = "Product name must be between 3 and 100 characters")
	private String name;
	@NotBlank(message = "Product description is required")
	@Size(max = 500, message = "Product description cannot exceed 500 characters")
	private String description;
	@NotNull(message = "Product price is required")
	@DecimalMin(value = "0.01", message = "Product price must be greater than zero")
	private BigDecimal price;
	@NotNull(message = "Product stock is required")
	@PositiveOrZero(message = "Product stock cannot be negative")
	private Integer stock;

}
