package com.chatop.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;

import com.chatop.api.exception.ResourceNotFoundException;
import com.chatop.api.model.Message;
import com.chatop.api.model.MessageEntity;
import com.chatop.api.model.RentalEntity;
import com.chatop.api.model.UserEntity;
import com.chatop.api.repository.MessageRepository;
import com.chatop.api.repository.RentalRepository;
import com.chatop.api.repository.UserRepository;

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
    public void create(Message message, String userEmail) throws Exception {
        UserEntity user = userRepository.findByEmail(userEmail);
        if (user.getId() != message.getUserId())
            throw new AccessDeniedException("Trying to post message with another user id");
        Optional<RentalEntity> rentalOpt = rentalRepository.findById(message.getRentalId());
        if (rentalOpt.isEmpty()) 
            throw new ResourceNotFoundException("Unknown rental id");
        MessageEntity entity = new MessageEntity(message.getMessage(), user, rentalOpt.get());
        messageRepository.save(entity);
    }
    
}
