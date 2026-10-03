package com.ashish.service;

import com.ashish.models.User;

public interface UserService {

    User save(User user);
    User findById(Integer userId);
    User update(Integer userId ,User user);
    void deleteBy(Integer userId);

}
