package com.example.jwtimplementation.api.config;

import com.google.common.collect.Sets;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import static com.example.jwtimplementation.api.config.ApplicationUserPermission.*;

public enum ApplicationUserRole {
    USER_ROLE(Sets.newHashSet()),
    ADMIN(Sets.newHashSet(USER_READ, USER_POST, USER_PUT, USER_DELETE));

    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getSimpleGrantedAuthorities() {
        Set<SimpleGrantedAuthority> authorities =
            getPermissions().stream().map(permission -> new SimpleGrantedAuthority(permission.getPermission())).collect(Collectors.toSet());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;

    }

}
