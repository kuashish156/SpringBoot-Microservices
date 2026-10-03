package com.ashish.springjpa.service;

import com.ashish.springjpa.models.User;

public interface UserService {

    User save(User user);
    User findById(Integer userId);
    User update(Integer userId ,User user);
    void deleteBy(Integer userId);

}
