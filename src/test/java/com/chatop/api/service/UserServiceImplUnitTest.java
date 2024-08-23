package com.chatop.api.service;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

import com.chatop.api.entity.UserEntity;
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
    public void getIdShoudWork() throws Exception {
        UserEntity userEntity = new UserEntity().setId(1);
        User user = new User().setId(1);
        Optional<UserEntity> userOpt = Optional.of(userEntity);
        Mockito.when(userRepository.findById(1)).thenReturn(userOpt);
        Mockito.when(userMapper.entityToModel(userEntity)).thenReturn(user);
        assertThat(userService.getUserById(1).getId()).isEqualTo(user.getId());
    }
    
}
