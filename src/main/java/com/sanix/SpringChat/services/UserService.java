package com.sanix.SpringChat.services;

import com.sanix.SpringChat.models.User;

import java.util.List;

public interface UserService {
    void save(User user);
    User findByUsername(String username);
    List<User> findAll();
}
