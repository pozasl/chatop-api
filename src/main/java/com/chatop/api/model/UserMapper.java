package com.chatop.api.model;

import com.chatop.api.entity.UserEntity;
import org.springframework.stereotype.Component;

/**
 * User entity to model mapper.
 */
@Component
public class UserMapper extends AbstractEntityToModelMapper
    implements EntityToModelMapper<UserEntity, User> {
  
  /**
   * Converts a user entity to user model.
   */
  public User entityToModel(UserEntity userEntity) {
    return new User(
        userEntity.getId(),
        userEntity.getName(),
        userEntity.getEmail(),
        convertDateForModel(userEntity.getCreationDate()),
        convertDateForModel(userEntity.getModificationDate()));
  }
}