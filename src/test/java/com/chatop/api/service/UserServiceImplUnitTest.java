package com.chatop.api.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

import com.chatop.api.entity.UserEntity;
import com.chatop.api.exception.ErrorCode;
import com.chatop.api.exception.ResourceNotFoundException;
import com.chatop.api.exception.UserAlreadyExistsException;
import com.chatop.api.model.NewUser;
import com.chatop.api.model.User;
import com.chatop.api.model.UserMapper;
import com.chatop.api.repository.UserRepository;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserServiceImplUnitTest {

  @Mock
  UserRepository userRepository;

  @Mock
  UserMapper userMapper;

  @InjectMocks
  UserServiceImpl userService;

  private DateFormat df = new SimpleDateFormat("yyyy/MM/dd");

  @Test
  void getUserByIdShoudWork() throws Exception {
    Date now = df.parse("2024/08/25");
    UserEntity userEntity = new UserEntity();
    userEntity.setId(1);
    userEntity.setName("bob");
    userEntity.setEmail("bob@test.com");
    userEntity.setCreationDate(now);
    userEntity.setModificationDate(now);
    User user = new User(1, "bob", "bob@test.com", "2024/08/25", "2024/08/25");
    Optional<UserEntity> userOpt = Optional.of(userEntity);
    Mockito.when(userRepository.findById(1)).thenReturn(userOpt);
    Mockito.when(userMapper.entityToModel(userEntity)).thenReturn(user);
    assertThat(userService.getUserById(1)).isEqualTo(user);
  }

  @Test
  void getUserByIdShoudThrow() throws ResourceNotFoundException {
    int userId = 999999999;
    Mockito.when(userRepository.findById(userId)).thenReturn(Optional.empty());
    assertThrows(
        ResourceNotFoundException.class,
        () -> userService.getUserById(userId),
        "Unknown user id");
  }

  @Test
  void createUserShoudWork() throws Exception {
    NewUser newUser = new NewUser("alice", "alice@test.com", "pass1234");
    User user = new User(2, "alice", "alice@test.com", "2024/08/25", "2024/08/25");
    Mockito.when(userRepository.findByEmail("alice@test.com")).thenReturn(Optional.empty());
    Mockito.when(userMapper.entityToModel(any())).thenReturn(user);
    User userSaved = userService.createUser(newUser);
    assertThat(userSaved).isEqualTo(user);
  }

  @Test
  void createUserShoudThrow() throws UserAlreadyExistsException {
    NewUser newUser = new NewUser("alice", "alice@test.com", "pass1234");
    Mockito.when(userRepository.findByEmail("alice@test.com"))
        .thenReturn(Optional.of(new UserEntity()));
    assertThrows(
        UserAlreadyExistsException.class,
        () -> userService.createUser(newUser),
        ErrorCode.USER_ALREADY_EXISTS.getErrMsg());
  }

  @Test
  void getUserByEmailShouldWork() throws Exception {
    Date now = df.parse("2024/08/25");
    UserEntity userEntity = new UserEntity();
    userEntity.setId(2);
    userEntity.setName("alice");
    userEntity.setEmail("alice@test.com");
    userEntity.setCreationDate(now);
    userEntity.setModificationDate(now);
    User user = new User(
        2,
        "alice",
        "alice@test.com",
        "2024/08/25",
        "2024/08/25");
    Mockito.when(userRepository.findByEmail("alice@test.com")).thenReturn(Optional.of(userEntity));
    Mockito.when(userMapper.entityToModel(userEntity)).thenReturn(user);
    assertThat(userService.getUserByEmail("alice@test.com")).isEqualTo(user);
  }
}