package com.ashish.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashish.userservice.models.Credential;

public interface CredentialRepository  extends JpaRepository<Credential, Integer>{

}
