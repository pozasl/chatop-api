package com.chatop.api.service;

import org.springframework.security.core.Authentication;

/**
 * JWT token provider service.
 */
public interface JwtService {
  public String generateToken(Authentication auth);
}
