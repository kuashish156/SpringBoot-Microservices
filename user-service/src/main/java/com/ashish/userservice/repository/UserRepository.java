package com.ashish.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashish.userservice.models.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
