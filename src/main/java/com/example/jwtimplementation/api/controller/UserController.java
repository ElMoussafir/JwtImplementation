package com.example.jwtimplementation.api.controller;

import com.example.jwtimplementation.api.entities.RegisterUserDto;
import com.example.jwtimplementation.api.entities.UserCredentials;
import com.example.jwtimplementation.api.service.CustomUserDetailsService;
import com.example.jwtimplementation.api.util.JwtUtil;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private JwtUtil jwtUtil;
    private AuthenticationManager authenticationManager;
    private CustomUserDetailsService customUserDetailsService;

    public UserController(JwtUtil jwtUtil, AuthenticationManager authenticationManager, CustomUserDetailsService customUserDetailsService) {
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
        this.customUserDetailsService = customUserDetailsService;
    }

    @GetMapping("/")
    public String welcomeMessage() {
        return "Welcome to Anvers";
    }

    @GetMapping("/getSecuredMessage")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String welcomeMessageForSecuredUsers() {
        return "Welcome to Brussel";
    }

    @PostMapping("/authenticate")
    public String generateToken(@RequestBody UserCredentials userCredentials) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                userCredentials.getUsername(), userCredentials.getPassword()
            ));
        }catch (Exception e){
            throw new Exception(e.getCause());
        }
       return jwtUtil.generateToken(userCredentials.getUsername());
    }

    @PostMapping("/register")
    public UserDetails registerUser(@RequestBody RegisterUserDto registerUserDto){
        UserDetails userDetails = this.customUserDetailsService.registerUser(registerUserDto);
        return userDetails;
    }
}
