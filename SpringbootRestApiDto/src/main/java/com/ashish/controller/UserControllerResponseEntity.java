


package com.ashish.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ashish.models.User;
import com.ashish.service.UserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserControllerResponseEntity {

	@Autowired
	private UserService service;

	Logger log = LoggerFactory.getLogger(UserControllerResponseEntity.class);

	@PostMapping
	public ResponseEntity<User> createdUser(@RequestBody User user) {

		log.info("UserControllerResponseEntity :: createdUser for {}", user.getEmail());
		User saveUser = service.save(user);

		return ResponseEntity.status(HttpStatus.CREATED).body(saveUser);

	}

	@GetMapping("/{userId}")
	public ResponseEntity<User> fetchedUser(@PathVariable Integer userId) {

		log.info("UserControllerResponseEntity :: fetchedUser for {}", userId);
		User byId = service.findById(userId);

		return ResponseEntity.status(HttpStatus.OK).body(byId);

	}

	@PutMapping("/{userId}")
	public ResponseEntity<User> updatedUser(@PathVariable Integer userId, @RequestBody User inputusers) {

		log.info("UserControllerResponseEntity :: updatedUser {} {} ", userId, inputusers.getEmail());
		User updateUser = service.update(userId, inputusers);

		return ResponseEntity.status(HttpStatus.OK).body(updateUser);

	}

	@DeleteMapping("/{userId}")
	public ResponseEntity<Void> deletedUserDetails(@PathVariable Integer userId) {
		
		log.info("UserControllerResponseEntity :: deletedUserDetails for {}", userId);
		service.deleteBy(userId);
		
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

	}

}


