package com.ashish.product.service.exception;

public class ProductNotFoundException  extends RuntimeException{

	public ProductNotFoundException( String message) {
		
		super(message);
	}
}
