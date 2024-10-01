package com.example.demo.services;

import com.example.demo.models.MyUser;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<MyUser> loadUserByUserName(String username) {
        return userRepository.findByUserName(username);
    }

    public Optional<MyUser> saveUser(MyUser myUser) {
        return Optional.of(userRepository.save(myUser));
    }
}
