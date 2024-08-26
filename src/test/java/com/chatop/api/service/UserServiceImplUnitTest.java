package com.chatop.api.service;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

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

@ExtendWith(MockitoExtension.class)
public class UserServiceImplUnitTest {

    @Mock
    UserRepository userRepository;

    @Mock
    UserMapper userMapper;

    @InjectMocks
    UserServiceImpl userService;


    @Test
    public void getUserByIdShoudWork() throws Exception {
        UserEntity userEntity = new UserEntity().setId(1);
        User user = new User().setId(1);
        Optional<UserEntity> userOpt = Optional.of(userEntity);
        Mockito.when(userRepository.findById(1)).thenReturn(userOpt);
        Mockito.when(userMapper.entityToModel(userEntity)).thenReturn(user);
        assertThat(userService.getUserById(1)).isEqualTo(user);
    }

    @Test
    public void getUserByIdShoudThrow() throws Exception {
        int userId = 999999999;
        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.empty());
        assertThrows(
            ResourceNotFoundException.class,
            () -> userService.getUserById(userId),
            "Unknown user id"
        );
    }

    @Test
    public void createUserShoudWork() throws Exception {
        NewUser newUser = new NewUser("alice", "alice@test.com", "pass1234");
        User user = new User().setId(2).setName("alice").setEmail("alice@test.com").setCreated("2024/08/25").setUpdated("2024/08/25");
        Mockito.when(userRepository.findByEmail("alice@test.com")).thenReturn(null);
        Mockito.when(userMapper.entityToModel(any())).thenReturn(user);
        User userSaved = userService.createUser(newUser);
        assertThat(userSaved).isEqualTo(user);
    }

    @Test
    public void createUserShoudThrow() throws Exception {
        NewUser newUser = new NewUser("alice", "alice@test.com", "pass1234");
        Mockito.when(userRepository.findByEmail("alice@test.com")).thenReturn(new UserEntity());
        assertThrows(
            UserAlreadyExistsException.class,
            () -> userService.createUser(newUser),
            ErrorCode.USER_ALREADY_EXISTS.getErrMsg()
        );
    }

    @Test
    public void getUserByEmailShouldWork() throws Exception {
        UserEntity userEntity = new UserEntity().setId(2).setName("alice").setEmail("alice@test.com")
            .setCreationDate(Date.from(Instant.now()))
            .setModificationDate(Date.from(Instant.now()));
        User user = new User().setId(2).setName("alice").setEmail("alice@test.com").setCreated("2024/08/25").setUpdated("2024/08/25");
        Mockito.when(userRepository.findByEmail("alice@test.com")).thenReturn(userEntity);
        Mockito.when(userMapper.entityToModel(userEntity)).thenReturn(user);
        assertThat(userService.getUserByEmail("alice@test.com")).isEqualTo(user);
    }
    
}
