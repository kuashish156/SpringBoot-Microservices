package com.ashish.userservice.dto;

import com.ashish.userservice.models.RoleBasedAuthority;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CredentialDto {

	
	private String username;
	private String password;
	
	private RoleBasedAuthority roleBasedAuthority;
}
