package com.ashish.product.service.exception;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHnadler {

	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<ErrorAPI> handleException(ProductNotFoundException ex) {

		log.error("product not found in db: {}", ex.getMessage());

		ErrorAPI errorAPI = new ErrorAPI();
		errorAPI.setLocalDateTime(LocalDateTime.now());
		errorAPI.setMessage(ex.getMessage());
		errorAPI.setError("product not abilable ");
		errorAPI.setStatus(HttpStatus.NOT_FOUND.getReasonPhrase());

		return new ResponseEntity<>(errorAPI, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(NoResourceFoundException.class)
	public ResponseEntity<ErrorAPI> handleException(NoResourceFoundException ex) {

		log.error("API endpoint not found: {}", ex.getMessage());

		ErrorAPI errorAPI = new ErrorAPI();

		errorAPI.setLocalDateTime(LocalDateTime.now());
		errorAPI.setMessage("Requested API endpoint does not exist");
		errorAPI.setError("API endpoint not found");
		errorAPI.setStatus(HttpStatus.NOT_FOUND.getReasonPhrase());

		return new ResponseEntity<>(errorAPI, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ErrorAPI> handleException(HttpMessageNotReadableException ex) {

		log.error("HttpMessageNotReadableException :: Invalid request body: {}", ex.getMessage());

		ErrorAPI errorAPI = new ErrorAPI();

		errorAPI.setLocalDateTime(LocalDateTime.now());
		errorAPI.setMessage("Invalid request body. Please provide valid JSON with correct field values.");
		errorAPI.setError("Malformed or unreadable request body");
		errorAPI.setStatus(HttpStatus.BAD_REQUEST.getReasonPhrase());

		return new ResponseEntity<>(errorAPI, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorAPI> handleException(MethodArgumentNotValidException ex) {

		String errorMessgae = ex.getBindingResult().getFieldErrors().stream()
				.map(obj -> obj.getField() + ": " + obj.getDefaultMessage())

				.collect(Collectors.joining(", "));

		ErrorAPI errorAPI = new ErrorAPI();
		errorAPI.setLocalDateTime(LocalDateTime.now());
		errorAPI.setMessage(errorMessgae);
		errorAPI.setError("validation error ");
		errorAPI.setStatus(HttpStatus.BAD_REQUEST.getReasonPhrase());

		return new ResponseEntity<>(errorAPI, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<ErrorAPI> handleMethodNotSupported(HttpRequestMethodNotSupportedException ex) {

		log.error("HTTP method not supported: {}", ex.getMessage());

		ErrorAPI errorAPI = new ErrorAPI();
		errorAPI.setLocalDateTime(LocalDateTime.now());
		errorAPI.setMessage("The requested HTTP method is not supported for this endpoint.");
		errorAPI.setError("Method not allowed");
		errorAPI.setStatus(HttpStatus.METHOD_NOT_ALLOWED.getReasonPhrase());

		return new ResponseEntity<>(errorAPI, HttpStatus.METHOD_NOT_ALLOWED);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorAPI> handleException(Exception ex) {

		log.error(" Internal Server Error: {}", ex.getMessage());

		ErrorAPI errorAPI = new ErrorAPI();
		errorAPI.setLocalDateTime(LocalDateTime.now());
		errorAPI.setMessage("something went wrong plzz check log for more info");
		errorAPI.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());

		return new ResponseEntity<>(errorAPI, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
