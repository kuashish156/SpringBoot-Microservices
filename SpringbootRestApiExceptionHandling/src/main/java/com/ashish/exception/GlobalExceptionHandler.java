package com.ashish.exception;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	// oops overloading concept

	Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	
	//1-  Handles cases where the requested user does not exist in the database.
	// Returns HTTP 404 Not Found.
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ErrorApi> handleException(UserNotFoundException ex) {
		log.error("User_Not_Found in Db {}", ex.getMessage());

		ErrorApi errorApi = new ErrorApi();
		errorApi.setLocalDateTime(LocalDateTime.now());
		errorApi.setMessage(ex.getMessage());
		errorApi.setError("validation error ");
		errorApi.setStatus(HttpStatus.BAD_REQUEST.getReasonPhrase());

		return new ResponseEntity<>(errorApi, HttpStatus.BAD_REQUEST);

	}

	//2-  Handles duplicate record conflicts, such as an email that already exists.
	// Returns HTTP 409 Conflict.
	@ExceptionHandler(RecordAlreadyExict.class)
	public ResponseEntity<ErrorApi> handleException(RecordAlreadyExict ex) {
		log.error("Record already exists: {}", ex.getMessage());

		ErrorApi errorApi = new ErrorApi();
		errorApi.setLocalDateTime(LocalDateTime.now());
		errorApi.setMessage(ex.getMessage());
		errorApi.setError(" CONFLICT in DB  ");
		errorApi.setStatus(HttpStatus.CONFLICT.getReasonPhrase());

		return new ResponseEntity<>(errorApi, HttpStatus.CONFLICT);

	}

	//3-  Handles DTO field validation failures triggered by @Valid.
	// Collects field-level validation messages and returns HTTP 400 Bad Request.
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorApi> handleException(MethodArgumentNotValidException ex) {

		String errorMessgae = ex.getBindingResult().getFieldErrors().stream()
				.map(obj -> obj.getField() + ": " + obj.getDefaultMessage())

				.collect(Collectors.joining(", "));

		ErrorApi errorApi = new ErrorApi();
		errorApi.setLocalDateTime(LocalDateTime.now());
		errorApi.setMessage(errorMessgae);
		errorApi.setError("validation error ");
		errorApi.setStatus(HttpStatus.BAD_REQUEST.getReasonPhrase());

		return new ResponseEntity<>(errorApi, HttpStatus.BAD_REQUEST);

	}

	
	//4-  Handles request-body parsing failures, such as malformed JSON,
	// invalid JSON types, or unreadable request content.
	// Returns HTTP 400 Bad Request.
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ErrorApi> handleException(HttpMessageNotReadableException ex) {
		log.error("HttpMessageNotReadableException :: {}", ex.getMessage());

		ErrorApi errorApi = new ErrorApi();
		errorApi.setLocalDateTime(LocalDateTime.now());
		errorApi.setMessage(ex.getMessage());
		errorApi.setError("malformed json data  ");
		errorApi.setStatus(HttpStatus.BAD_REQUEST.getReasonPhrase());

		return new ResponseEntity<>(errorApi, HttpStatus.BAD_REQUEST);

	}
	
	
	
	//5-  Fallback handler for unexpected exceptions that do not have
	// a more specific handler. Returns HTTP 500 Internal Server Error.
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorApi> handleException(Exception ex) {
		log.error("Internal server Error :: {}", ex.getMessage());

		ErrorApi errorApi = new ErrorApi();
		errorApi.setLocalDateTime(LocalDateTime.now());
		errorApi.setMessage(ex.getMessage());
		errorApi.setError("Something went wrong please check log for moreinformation  ");
		errorApi.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());

		return new ResponseEntity<>(errorApi, HttpStatus.INTERNAL_SERVER_ERROR);

	}

}
