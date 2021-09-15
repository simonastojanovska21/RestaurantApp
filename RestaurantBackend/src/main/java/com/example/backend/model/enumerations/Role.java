package com.example.backend.model.enumerations;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_ADMIN,
    ROLE_CUSTOMER,
    ROLE_EMPLOYEE,
    ROLE_DELIVERY;

    @Override
    public String getAuthority() {
        return name();
    }
}
