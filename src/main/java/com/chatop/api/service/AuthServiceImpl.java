package com.chatop.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chatop.api.model.AuthInfo;
import com.chatop.api.model.User;
import com.chatop.api.repository.UserRepository;

@Service
public class AuthServiceImpl implements AuthService{

    private UserRepository userRepository;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User authenticate(AuthInfo auth) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'authenticate'");
    }

    @Override
    public User isAuthenticated() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isAuthenticated'");
    }
    
}
