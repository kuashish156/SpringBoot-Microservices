package com.ashish.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

	
	private Integer userId;
	private String firstName;
	private String lastName;
	private String emailAdress;
	private String contectNumber;
	
	private CredentialDto credential;
}
