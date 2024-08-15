package com.chatop.api.service;

import com.chatop.api.model.NewUser;
import com.chatop.api.model.User;
import com.chatop.api.model.UserEntity;

public interface UserService {
  public UserEntity getUserById(int id) throws Exception;

  public void deleteUser(int id);

  public UserEntity saveUser(UserEntity user);

  public User entityToModel(UserEntity entity);

  public UserEntity createUser(NewUser newUser) throws Exception ;
}
