package com.ems.employeemanagement.entity;

import org.springframework.security.authentication.jaas.AuthorityGranter;
import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    EMPLOYEE,ADMIN;

    @Override
    public String getAuthority() {
        return this.name();
    }
}
