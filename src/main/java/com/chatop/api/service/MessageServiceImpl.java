package com.chatop.api.service;

import com.chatop.api.entity.MessageEntity;
import com.chatop.api.entity.RentalEntity;
import com.chatop.api.exception.ResourceNotFoundException;
import com.chatop.api.model.NewMessage;
import com.chatop.api.repository.MessageRepository;
import com.chatop.api.repository.RentalRepository;
import com.chatop.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

/**
 * Message service implementation.
 */
@Service
public class MessageServiceImpl implements MessageService {

  private MessageRepository messageRepository;
  private RentalRepository rentalRepository;
  private UserRepository userRepository;

  /**
   * Message service.
   *
   * @param messageRepository users' messages repository
   * @param rentalRepository users' rentals repository
   * @param userRepository users repository
   */
  @Autowired
  public MessageServiceImpl(MessageRepository messageRepository,
      RentalRepository rentalRepository,
      UserRepository userRepository) {
    this.messageRepository = messageRepository;
    this.rentalRepository = rentalRepository;
    this.userRepository = userRepository;
  }

  @Override
  public void createMessage(NewMessage message, String userEmail)
      throws AccessDeniedException, ResourceNotFoundException {
    RentalEntity rental = rentalRepository.findById(message.rentalId()).orElseThrow(
        () -> new ResourceNotFoundException("Unknown rental id"));
    userRepository.findByEmail(userEmail).ifPresentOrElse((user) -> {
      if (user.getId() != message.userId()) {
        throw new AccessDeniedException("Trying to post message with another user id");
      }
      MessageEntity entity = new MessageEntity();
      entity.setMessage(message.message());
      entity.setUser(user);
      entity.setRental(rental);
      messageRepository.save(entity);
    }, () -> new ResourceNotFoundException("Unknow user email"));
  }

}
