package com.example.jwtimplementation.api.entities;

import com.example.jwtimplementation.api.config.ApplicationUserPermission;
import com.example.jwtimplementation.api.config.ApplicationUserRole;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USER_TBL")
public class User {
    @Id
    private Integer id;
    private String username;
    private String password;
    private String email;
    private ApplicationUserRole role;

    public User() {
    }

    public User(Integer id, String username, String password, String email, ApplicationUserRole role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public Integer getId() {
        return id;
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

    public ApplicationUserRole getRole() {
        return role;
    }
}
