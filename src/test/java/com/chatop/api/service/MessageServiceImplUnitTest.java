package com.chatop.api.service;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.chatop.api.entity.RentalEntity;
import com.chatop.api.entity.UserEntity;
import com.chatop.api.model.NewMessage;
import com.chatop.api.repository.MessageRepository;
import com.chatop.api.repository.RentalRepository;
import com.chatop.api.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class MessageServiceImplUnitTest {
    
    @Mock
    private MessageRepository messageRepository;

    @Mock
    private RentalRepository rentalRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private MessageServiceImpl employeeService;

    @Test
    public void createShoudWork() throws Exception {
        String email = "alice@test.com";
        UserEntity user = new UserEntity().setId(2);
        RentalEntity rental = new RentalEntity().setId(1);
        Optional<RentalEntity> opt = Optional.of(rental);
        Mockito.when(userRepository.findByEmail(email)).thenReturn(user);
        Mockito.when(rentalRepository.findById(1)).thenReturn(opt);
        NewMessage message = new NewMessage().setMessage("yo").setRentalId(1).setUserId(2);
        employeeService.create(message, email);
    }
}
