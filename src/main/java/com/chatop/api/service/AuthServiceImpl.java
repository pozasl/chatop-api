package com.chatop.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chatop.api.model.AuthInfo;
import com.chatop.api.model.User;
import com.chatop.api.model.UserEntity;
import com.chatop.api.model.UserMapper;
import com.chatop.api.repository.UserRepository;

@Service
public class AuthServiceImpl implements AuthService{

    private UserRepository userRepository;
    private UserMapper userMapper;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public User authenticate(AuthInfo auth) throws Exception {
        UserEntity user = userRepository.findByEmail(auth.getEmail());
        System.out.println(auth.toString());
        System.out.println(user.toString());
        return userMapper.entityToModel(user);
    }

    @Override
    public User isAuthenticated() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isAuthenticated'");
    }
    
}
