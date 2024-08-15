package com.chatop.api.service;

import com.chatop.api.model.AuthInfo;
import com.chatop.api.model.User;

public interface AuthService {
    public User authenticate(AuthInfo auth) throws Exception;
    public User isAuthenticated();
}
