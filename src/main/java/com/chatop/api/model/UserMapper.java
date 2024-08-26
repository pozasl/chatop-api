package com.chatop.api.model;

import org.springframework.stereotype.Component;

import com.chatop.api.entity.UserEntity;

@Component
public class UserMapper extends AbstractEntityToModelMapper{
    public User entityToModel(UserEntity userEntity) {
        return new User(
            userEntity.getId(),
            userEntity.getName(),
            userEntity.getEmail(),
            convertDateForModel(userEntity.getCreationDate()),
            convertDateForModel(userEntity.getModificationDate())
        );
    }
}