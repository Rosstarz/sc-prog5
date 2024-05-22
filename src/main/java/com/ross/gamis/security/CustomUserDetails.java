package com.ross.gamis.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class CustomUserDetails extends User {
    private final long userId;

    public CustomUserDetails(String username,
            String password,
            Collection<? extends GrantedAuthority> authorities,
            long userId) {
        super(username, password, authorities);
        this.userId = userId;
    }

    public long getDeveloperId() {
        return userId;
    }
}
