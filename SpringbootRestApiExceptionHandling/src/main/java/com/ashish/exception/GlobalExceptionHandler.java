package com.ashish.exception;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class GlobalExceptionHandler {
	
	 //oops overloading concept 
	
	Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	@ExceptionHandler(UserNotFoundException.class)
	public  ResponseEntity<ErrorApi> handleException(UserNotFoundException ex) {
		log.error("User_Not_Found in Db {}",ex.getMessage());
		
		ErrorApi errorApi = new ErrorApi();
		errorApi.setLocalDateTime(LocalDateTime.now());
		errorApi.setMessage(ex.getMessage());
		errorApi.setError("validation error ");
		errorApi.setStatus(HttpStatus.BAD_REQUEST.getReasonPhrase());
		
		return new  ResponseEntity<>(errorApi,HttpStatus.BAD_REQUEST);
		
	}

	 

	
	// HTTP 409 Conflict is appropriate for duplicate records. eg- email id already exit in db 
	@ExceptionHandler(RecordAlreadyExict.class)
	public  ResponseEntity<ErrorApi> handleException(RecordAlreadyExict ex) {
		log.error("Record already exists: {}",ex.getMessage());
		
		ErrorApi errorApi = new ErrorApi();
		errorApi.setLocalDateTime(LocalDateTime.now());
		errorApi.setMessage(ex.getMessage());
		errorApi.setError(" CONFLICT in DB  ");
		errorApi.setStatus(HttpStatus.CONFLICT.getReasonPhrase());
		
		return new  ResponseEntity<>(errorApi,HttpStatus.CONFLICT);
		
	}
	
	
	
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorApi> handleException(Exception ex) {
		log.error("Internal server Error :: {}",ex.getMessage());
		
		ErrorApi errorApi = new ErrorApi();
		errorApi.setLocalDateTime(LocalDateTime.now());
		errorApi.setMessage(ex.getMessage());
		errorApi.setError("Something went wrong please check log for moreinformation  ");
		errorApi.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
		
		return new  ResponseEntity<>(errorApi,HttpStatus.INTERNAL_SERVER_ERROR);
		
	}

}
