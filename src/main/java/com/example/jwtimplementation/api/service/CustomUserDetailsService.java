package com.example.jwtimplementation.api.service;

import com.example.jwtimplementation.api.config.ApplicationUserRole;
import com.example.jwtimplementation.api.config.PasswordConfig;
import com.example.jwtimplementation.api.entities.RegisterUserDto;
import com.example.jwtimplementation.api.entities.User;
import com.example.jwtimplementation.api.repository.UserRepository;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private PasswordConfig passwordConfig;

    public CustomUserDetailsService(UserRepository userRepository, PasswordConfig passwordConfig) {
        this.userRepository = userRepository;
        this.passwordConfig = passwordConfig;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userInDb = userRepository.findByUsername(username);
        return new org.springframework.security.core.userdetails.User(userInDb.getUsername(), userInDb.getPassword(),userInDb.getRole().getSimpleGrantedAuthorities());
    }

    public UserDetails registerUser(RegisterUserDto registerUserDto) {
        int id = userRepository.findAll().size() + 1;
        String password = this.passwordConfig.passwordEncoder().encode(registerUserDto.getPassword());
        this.userRepository.save(new User(id,registerUserDto.getUsername(),password, registerUserDto.getEmail(), ApplicationUserRole.USER_ROLE));
        User userInDb = userRepository.findByUsername(registerUserDto.getUsername());
        return new org.springframework.security.core.userdetails.User(userInDb.getUsername(), userInDb.getPassword(),userInDb.getRole().getSimpleGrantedAuthorities());
    }
}
