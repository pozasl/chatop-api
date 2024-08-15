package com.chatop.api.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chatop.api.exception.ResourceNotFoundException;
import com.chatop.api.model.NewUser;
import com.chatop.api.model.User;
import com.chatop.api.model.UserEntity;
import com.chatop.api.model.UserMapper;
import com.chatop.api.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;
    private UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public User getUserById(int id) throws Exception {
        Optional<UserEntity> user = this.userRepository.findById(id);
        if (user.isEmpty()) throw new ResourceNotFoundException("Unknown user id");
        return userMapper.entityToModel(user.get());
    }

    @Override
    public User createUser(NewUser newUser) throws Exception {
        UserEntity entity = new UserEntity(newUser.getName(), newUser.getEmail(), newUser.getPassword());
        // TODO: Hash Password
        System.out.println(entity.toString());
        return userMapper.entityToModel(this.userRepository.save(entity));
    }
    
}
