package com.example.jwtimplementation.api.service;

import com.example.jwtimplementation.api.entities.User;
import com.example.jwtimplementation.api.repository.UserRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userInDb = userRepository.findByUsername(username);
        return new org.springframework.security.core.userdetails.User(userInDb.getUsername(), userInDb.getPassword(),userInDb.getRole().getSimpleGrantedAuthorities());
    }
}
