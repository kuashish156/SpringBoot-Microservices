package com.ashish.userservice.mapper;

import org.mapstruct.Mapper;

import com.ashish.userservice.dto.CredentialDto;
import com.ashish.userservice.models.Credential;

@Mapper(componentModel = "spring")
public interface CredentialMapper {
	
	CredentialDto toDto (Credential  credential);

}
