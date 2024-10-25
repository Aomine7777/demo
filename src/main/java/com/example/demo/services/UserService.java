package com.example.demo.services;

import com.example.demo.models.MyUser;

import java.util.Optional;


public interface UserService {
    Optional<MyUser> loadUserByUserName(String username);

    void saveUser(MyUser user);

}