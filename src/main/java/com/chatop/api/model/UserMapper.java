package com.chatop.api.model;

import org.springframework.stereotype.Component;

import com.chatop.api.entity.UserEntity;

@Component
public class UserMapper extends GenericEntityToModelMapper<UserEntity, User>{
    public User entityToModel(UserEntity userEntity) {
        return super.entityToModel(userEntity, new User());
    }
}
