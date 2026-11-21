package com.ashish.product.service.exception;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ErrorAPI {
	
	private LocalDateTime localDateTime;
	private String status;
	private String error;
	private String message;
	
	
	

}
