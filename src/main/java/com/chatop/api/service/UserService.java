package com.chatop.api.service;

import com.chatop.api.exception.ResourceNotFoundException;
import com.chatop.api.exception.UserAlreadyExistsException;
import com.chatop.api.model.NewUser;
import com.chatop.api.model.User;

/**
 * User service interface.
 */
public interface UserService {
  public User getUserById(int id) throws ResourceNotFoundException;

  public User getUserByEmail(String email) throws ResourceNotFoundException;

  public User createUser(NewUser newUser) throws UserAlreadyExistsException;
}
