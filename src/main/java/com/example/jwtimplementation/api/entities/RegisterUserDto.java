package com.example.jwtimplementation.api.entities;

public class RegisterUserDto {

    private String username;
    private String password;
    private String email;

    public RegisterUserDto(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public RegisterUserDto() {
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}
