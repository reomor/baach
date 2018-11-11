package com.reomor.impl.entity;

import org.springframework.security.core.GrantedAuthority;

public enum UserRoles implements GrantedAuthority {
    ROLE_ANONYMOUS,
    ROLE_USER,
    ROLE_ADMIN,
    ROLE_UBER;

    @Override
    public String getAuthority() {
        return name();
    }
}
