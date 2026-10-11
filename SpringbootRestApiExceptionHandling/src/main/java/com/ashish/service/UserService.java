package com.ashish.service;

import com.ashish.dto.UserDto;
import com.ashish.models.User;

public interface UserService {

	UserDto save(UserDto userDto);
	User findById(Integer userId);
    User update(Integer userId ,User user);
    void deleteBy(Integer userId);

}


