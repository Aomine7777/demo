package com.example.demo.configuration;

import com.example.demo.models.MyUser;
import com.example.demo.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class MyUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUser userObj = userRepository.findByUserName(username).orElseThrow(() -> new UsernameNotFoundException(username));

        return User.builder().username(userObj.getUserName()).password(userObj.getPassword()).roles(getRoles(userObj)).build();
    }

    private String[] getRoles(MyUser user) {
        return new String[]{"USER"};
    }
}