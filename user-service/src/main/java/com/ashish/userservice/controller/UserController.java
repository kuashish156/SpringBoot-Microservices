package com.ashish.userservice.controller;

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

import com.ashish.userservice.dto.UserDto;
import com.ashish.userservice.service.CredentialService;
import com.ashish.userservice.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Tag(name = "User Management", description = "APIs for creating, retrieving, updating and deleting users.")
public class UserController {

	private final UserService userService;

	Logger log = LoggerFactory.getLogger(UserController.class);

	@PostMapping
	@Operation(summary = "create new user ", description = "Creates a new user account in the User Service.")
	public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {

		UserDto createdUser = userService.createUser(userDto);

		log.info(" User created successfully with email: {}", userDto.getEmailAdress());
		return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);

	}

	@GetMapping("/{userId}")
	@Operation(summary = "Get User By ID", description = " Retrieves user details from the User Service using the unique user ID.")
	public ResponseEntity<UserDto> fetchUser(@PathVariable Integer userId) {

		UserDto fetchedUser = userService.getUserById(userId);
		log.info(" User fetched successfully with ID: {}", userId);
		return ResponseEntity.status(HttpStatus.OK).body(fetchedUser);
	}

	@PutMapping("/{userId}")
	@Operation(summary = "Update User", description = "Updates existing user details using the unique user ID.")
	public ResponseEntity<UserDto> updateUser(@PathVariable Integer userId, @RequestBody UserDto userDto) {

		UserDto updatedUser = userService.updateUser(userId, userDto);
		log.info("User updated successfully with ID: {} {} ", userId, userDto.getEmailAdress());
		return ResponseEntity.status(HttpStatus.OK).body(updatedUser);

	}

	@DeleteMapping("/{userId}")
	@Operation(summary = "Delete User", description = "Deletes an existing user using the unique user ID.")
	public ResponseEntity<Void> deleteUser(@PathVariable Integer userId) {

		userService.deleteUserById(userId);
		log.info("User deleted successfully with ID: {}", userId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

	}

}
