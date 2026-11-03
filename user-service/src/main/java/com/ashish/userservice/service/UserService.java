package com.ashish.userservice.service;

import com.ashish.userservice.dto.UserDto;

public interface UserService {

	//create new user in Database 
	UserDto createUser(UserDto userDto);
	
	// Retrieve user by ID 
	UserDto  getUserById(Integer userId);
	
	// update user data 
	UserDto updateUser(Integer userId ,UserDto userDto); 
	
	// delete user based on id 
	 void deleteUserById(Integer userId) ;
	
	
	
}
