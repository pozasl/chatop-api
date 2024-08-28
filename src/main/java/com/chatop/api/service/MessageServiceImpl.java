package com.chatop.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import com.chatop.api.entity.MessageEntity;
import com.chatop.api.entity.RentalEntity;
import com.chatop.api.entity.UserEntity;
import com.chatop.api.exception.ResourceNotFoundException;
import com.chatop.api.model.NewMessage;
import com.chatop.api.repository.MessageRepository;
import com.chatop.api.repository.RentalRepository;
import com.chatop.api.repository.UserRepository;

@Service
public class MessageServiceImpl implements MessageService{

    private MessageRepository messageRepository;
    private RentalRepository rentalRepository;
    private UserRepository userRepository;

    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository,
        RentalRepository rentalRepository,
        UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.rentalRepository = rentalRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void create(NewMessage message, String userEmail) throws AccessDeniedException, ResourceNotFoundException {
        UserEntity user = userRepository.findByEmail(userEmail);
        if (user.getId() != message.userId())
            throw new AccessDeniedException("Trying to post message with another user id");
        Optional<RentalEntity> rentalOpt = rentalRepository.findById(message.rentalId());
        if (rentalOpt.isEmpty()) 
            throw new ResourceNotFoundException("Unknown rental id");
        MessageEntity entity = new MessageEntity();
        entity.setMessage(message.message());
        entity.setUser(user);
        entity.setRental(rentalOpt.get());
        messageRepository.save(entity);
    }
    
}
