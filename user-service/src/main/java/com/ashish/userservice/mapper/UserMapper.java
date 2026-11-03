package com.ashish.userservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.ashish.userservice.dto.UserDto;
import com.ashish.userservice.models.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
	
	@Mapping(source = "email" ,target = "emailAdress")
	@Mapping(source = "phone" ,target = "contectNumber")
	UserDto toDto (User user);
	
	@Mapping(target = "email" ,source = "emailAdress")
	@Mapping(target = "phone" ,source = "contectNumber")
	User toEntity(UserDto userDto);

  
}
