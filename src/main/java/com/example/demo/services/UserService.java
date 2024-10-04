package com.example.demo.services;

import com.example.demo.models.MyUser;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<MyUser> loadUserByUserName(String username) {
        return userRepository.findByUserName(username);
    }

    public void saveUser(MyUser user) {
        user.setRole("USER");

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(user);
    }

}
