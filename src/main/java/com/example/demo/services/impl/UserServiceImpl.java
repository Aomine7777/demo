package com.example.demo.services.impl;

import com.example.demo.models.MyUser;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public Optional<MyUser> loadUserByUserName(String username) {
        return userRepository.findByUserName(username);
    }

    public void saveUser(MyUser user) {
        user.setRole("USER");

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(user);
    }

}