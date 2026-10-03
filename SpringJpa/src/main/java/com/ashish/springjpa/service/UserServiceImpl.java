package com.ashish.springjpa.service;

import com.ashish.springjpa.models.User;
import com.ashish.springjpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
     private UserRepository userRepository;

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findById(Integer userId) {
        Optional<User> optional = userRepository.findById(userId);
        if (optional.isPresent()) {
          return optional.get();
        } else {
            throw new RuntimeException("user not found in db");
        }

    }

    public User findByIdUsingJava8(Integer userId) {
        return userRepository.findById(userId)
                .orElseThrow(()-> new RuntimeException("user not found in Db"));

    }

    @Override
    public User update(Integer userId, User user) {

        User dbUser = findById(userId);
        dbUser.setFirstName(user.getFirstName());
        dbUser.setLastName(user.getLastName());
        dbUser.setEmail(user.getEmail());
        dbUser.setPhone(user.getPhone());

        userRepository.save(dbUser);
        return dbUser;
    }

    @Override
    public void deleteBy(Integer userId) {
        User dbUser = findById(userId);
        userRepository.delete(dbUser);

    }
}
