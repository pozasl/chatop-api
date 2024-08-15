package com.chatop.api.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chatop.api.exception.ResourceNotFoundException;
import com.chatop.api.model.NewUser;
import com.chatop.api.model.User;
import com.chatop.api.model.UserEntity;
import com.chatop.api.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

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

    public User entityToModel(UserEntity entity) {
        User user = new User();
        if(Objects.isNull(entity)) {
            return user;
        }
        BeanUtils.copyProperties(entity, user);
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

        user.setCreated(df.format(entity.getCreationDate()));
        user.setUpdated(df.format(entity.getModificationDate()));
        return user;
    }

    public UserEntity createUser(NewUser newUser) throws Exception {
        UserEntity entity = new UserEntity();
        BeanUtils.copyProperties(newUser, entity);
        // TODO: Hash Password
        return this.userRepository.save(entity);
    }
    
}
