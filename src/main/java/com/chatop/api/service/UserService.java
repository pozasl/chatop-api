package com.chatop.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chatop.api.model.UserEntity;
import com.chatop.api.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Iterable<UserEntity> getUsers() {
        return this.userRepository.findAll();
    }

    public Optional<UserEntity> getUserById(int id) {
        return this.userRepository.findById(id);
    }

    public void deleteUser(int id) {
        this.userRepository.deleteById(id);
    }

    public UserEntity saveUser(UserEntity user) {
            UserEntity savedUser = this.userRepository.save(user);
            return savedUser;
    }
    
}
