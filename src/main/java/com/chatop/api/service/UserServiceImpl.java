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
    public User getUserById(int id) throws Exception {
        Optional<UserEntity> user = this.userRepository.findById(id);
        if (user.isEmpty()) throw new ResourceNotFoundException("Unknown user id");
        return userMapper.entityToModel(user.get());
    }

    @Override
    public User createUser(NewUser newUser) throws Exception {
        if (!Objects.isNull(this.userRepository.findByEmail(newUser.email())))
            throw new UserAlreadyExistsException(ErrorCode.USER_ALREADY_EXISTS);
        UserEntity entity = new UserEntity(newUser.name(), newUser.email(), encoder.encode(newUser.password()));
        return userMapper.entityToModel(this.userRepository.save(entity));
    }

    @Override
    public User getUserByEmail(String email) throws Exception {
        UserEntity user = this.userRepository.findByEmail(email);
        if (Objects.isNull(user)) throw new ResourceNotFoundException("Unknown user email");
        return userMapper.entityToModel(user);
    }
    
}
