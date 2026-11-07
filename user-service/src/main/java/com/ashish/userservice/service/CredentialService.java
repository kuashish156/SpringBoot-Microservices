package com.ashish.userservice.service;

import org.springframework.stereotype.Service;

import com.ashish.userservice.dto.CredentialDto;
import com.ashish.userservice.mapper.CredentialMapper;
import com.ashish.userservice.repository.CredentialRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class CredentialService {

	private final CredentialRepository credentialRepository;

	private final CredentialMapper credentialMapper;

	public CredentialDto findByusername(String username) {

		return credentialRepository.findByusername(username).map(credentialMapper::toDto)
				.orElseThrow(() -> new RuntimeException("user name not found in db"))

		;

	}

}
