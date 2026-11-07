package com.ashish.userservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashish.userservice.models.Credential;

public interface CredentialRepository extends JpaRepository<Credential, Integer> {
	
      // domain specification language method 
	Optional<Credential> findByusername(String username);
	
}
