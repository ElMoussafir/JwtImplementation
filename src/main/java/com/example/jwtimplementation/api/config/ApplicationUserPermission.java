package com.example.jwtimplementation.api.config;

public enum ApplicationUserPermission {
    USER_READ("user:read"),
    USER_POST("user:post"),
    USER_PUT("user:put"),
    USER_DELETE("user:delete");

    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
