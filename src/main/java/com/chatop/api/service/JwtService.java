package com.chatop.api.service;

import org.springframework.security.core.Authentication;

public interface JwtService {
    public String generateToken(Authentication auth);
}
