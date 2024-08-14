package com.chatop.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chatop.api.exception.ResourceNotFoundException;
import com.chatop.api.model.UserEntity;
import com.chatop.api.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Iterable<UserEntity> getUsers() {
        return this.userRepository.findAll();
    }

    public UserEntity getUserById(int id) throws Exception {
        Optional<UserEntity> user = this.userRepository.findById(id);
        if (user.isEmpty()) throw new ResourceNotFoundException("Unknown user id");
        return user.get();
    }

    public void deleteUser(int id) {
        this.userRepository.deleteById(id);
    }

    public UserEntity saveUser(UserEntity user) {
            UserEntity savedUser = this.userRepository.save(user);
            return savedUser;
    }
    
}
