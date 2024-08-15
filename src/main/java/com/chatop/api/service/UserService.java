package com.chatop.api.service;

import com.chatop.api.model.NewUser;
import com.chatop.api.model.User;
public interface UserService {
  public User getUserById(int id) throws Exception;
  public User createUser(NewUser newUser) throws Exception ;
}
