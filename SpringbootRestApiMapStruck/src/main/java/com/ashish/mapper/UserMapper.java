package com.ashish.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.ashish.dto.UserDto;
import com.ashish.models.User;


@Mapper(componentModel = "spring")
public interface UserMapper {

	// Note- source = where data is coming FROM
	// target = where data is going TO

	// note - If Entity and DTO have the same field name, MapStruct maps
	// automatically.

	@Mapping(target = "yourFirstName", source = "firstName")
	@Mapping(target = "yourLastName", source = "lastName")
	@Mapping(target = "emailAddress", source = "email")
	UserDto toDto(User User); // Entity -> DTO

	@Mapping(source = "yourFirstName", target = "firstName")
	@Mapping(source = "yourLastName", target = "lastName")
	@Mapping(source = "emailAddress", target = "email")
	User toEntity(UserDto userDto); // DTO -> Entity

}
