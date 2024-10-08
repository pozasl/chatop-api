package com.chatop.api.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import com.chatop.api.entity.RentalEntity;
import com.chatop.api.entity.UserEntity;
import com.chatop.api.exception.ResourceNotFoundException;
import com.chatop.api.model.NewMessage;
import com.chatop.api.repository.MessageRepository;
import com.chatop.api.repository.RentalRepository;
import com.chatop.api.repository.UserRepository;
import java.time.Instant;
import java.util.Date;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MessageServiceImplUnitTest {

  @Mock
  private MessageRepository messageRepository;

  @Mock
  private RentalRepository rentalRepository;

  @Mock
  private UserRepository userRepository;

  @InjectMocks
  private MessageServiceImpl messageService;

  @Test
  void createShoudWork() throws ResourceNotFoundException {

    Date now = Date.from(Instant.now());
    UserEntity user = new UserEntity();
    user.setId(2);
    user.setName("alice");
    user.setEmail("alice@test.com");
    user.setCreationDate(now);
    user.setModificationDate(now);
    RentalEntity rental = new RentalEntity();
    rental.setName("rental name");
    rental.setSurface(50);
    rental.setPrice(200);
    rental.setPicture("upload/test.jpg");
    rental.setDescription("Desc");
    rental.setUser(user);
    rental.setCreationDate(now);
    rental.setModificationDate(now);
    Optional<RentalEntity> opt = Optional.of(rental);
    Mockito.when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
    Mockito.when(rentalRepository.findById(1)).thenReturn(opt);
    NewMessage message = new NewMessage("yo", 2, 1);
    assertDoesNotThrow(() -> messageService.createMessage(message, user.getEmail()));
  }
}
