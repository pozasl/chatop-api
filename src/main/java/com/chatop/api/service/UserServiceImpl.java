package com.chatop.api.service;


import java.util.Optional;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.chatop.api.entity.UserEntity;
import com.chatop.api.exception.ErrorCode;
import com.chatop.api.exception.ResourceNotFoundException;
import com.chatop.api.exception.UserAlreadyExistsException;
import com.chatop.api.model.NewUser;
import com.chatop.api.model.User;
import com.chatop.api.model.UserMapper;
import com.chatop.api.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;
    private UserMapper userMapper;
    private BCryptPasswordEncoder encoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        encoder = new BCryptPasswordEncoder();
    }

    @Override
    public User getUserById(int id) throws ResourceNotFoundException {
        return userMapper.entityToModel(this.userRepository.findById(id).orElseThrow(
            ()->new ResourceNotFoundException("Unknown user id")
        ));
    }

    @Override
    public User createUser(NewUser newUser) throws UserAlreadyExistsException {
        if (this.userRepository.findByEmail(newUser.email()).isPresent())
            throw new UserAlreadyExistsException(ErrorCode.USER_ALREADY_EXISTS);
        UserEntity entity = new UserEntity();
        entity.setName(newUser.name());
        entity.setEmail(newUser.email());
        entity.setPassword(encoder.encode(newUser.password()));
        return userMapper.entityToModel(this.userRepository.save(entity));
    }

    @Override
    public User getUserByEmail(String email) throws ResourceNotFoundException {
        return userMapper.entityToModel(
            this.userRepository.findByEmail(email)
            .orElseThrow(()->new ResourceNotFoundException("Unknown user email"))
        );
    }
    
}
