package com.ashish.userservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ashish.userservice.dto.CredentialDto;
import com.ashish.userservice.service.CredentialService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/credential")
@RequiredArgsConstructor
//@Tag(name = "Credential Management", description = "APIs for retrieving user credentials.")
public class CredentialController {

	private final CredentialService credentialService;

	@GetMapping("username/{uname}")
	@Operation(summary = "Get Credential By Username", description = "Retrieves user credential details using the username.")
	public ResponseEntity<CredentialDto> getCredential(@PathVariable("uname") String username) {

		CredentialDto credential = credentialService.findByusername(username);

		return ResponseEntity.status(HttpStatus.OK).body(credential);

	}

}
