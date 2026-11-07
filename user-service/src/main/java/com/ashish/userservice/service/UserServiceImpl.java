package com.ashish.userservice.service;

import org.springframework.stereotype.Service;

import com.ashish.userservice.dto.UserDto;
import com.ashish.userservice.mapper.UserMapper;
import com.ashish.userservice.models.Credential;
import com.ashish.userservice.models.User;
import com.ashish.userservice.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	
	private final UserRepository userRepository;
	
	private final UserMapper userMapper;

	@Override
	public UserDto createUser(UserDto userDto) {

		// Convert UserDto (request data) into User Entity (database object)
		User user = userMapper.toEntity(userDto);

		// TODO: Encode the original password using PasswordEncoder (BCrypt)
		// Never store the plain-text password in the database.

		// Get the Credential object associated with the User
		Credential credential = user.getCredential();

		// Set the User reference in Credential to maintain the bidirectional
		// relationship
		// Credential is the owning side because it contains @JoinColumn(name =
		// "user_id")
		credential.setUser(user);

		// Save the User entity into the database
		// CascadeType.ALL automatically saves the associated Credential as well
		User dbUser = userRepository.save(user);

		// Convert the saved User Entity back into UserDto for the API response
		// Never expose the password in the response
		return userMapper.toDto(dbUser);
	}

	@Override
	public UserDto getUserById(Integer userId) {

		return userRepository.findById(userId)

				// If the User exists, convert the User Entity into UserDto.
				// We can use either a Lambda Expression or a Method Reference here.
				.map(userMapper::toDto)

				// If the User does not exist, throw a RuntimeException with the User ID.
				.orElseThrow(() -> new RuntimeException(" user not found in db" + userId));

	}

	@Override
	public UserDto updateUser(Integer userId, UserDto userDto) {

		// Step 1: Find the existing User from the database using userId
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new RuntimeException(" user not found in db" + userId));

		// Step 2: Update existing User fields with values from UserDto
		user.setFirstName(userDto.getFirstName());
		user.setLastName(userDto.getLastName());
		user.setEmail(userDto.getEmailAdress());
		user.setPhone(userDto.getContectNumber());

		// Step 3: Save the updated User Entity into the database
		User updatedUsers = userRepository.save(user);

		// Step 4: Convert updated User Entity into UserDto and return
		return userMapper.toDto(updatedUsers);
	}

	@Override
	public void deleteUserById(Integer userId) {

		// Step 1: Find the existing User from the database using userId
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new RuntimeException(" user not found in db" + userId));

		userRepository.delete(user);
	}

}
