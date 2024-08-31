package com.chatop.api.service;

import org.springframework.security.access.AccessDeniedException;

import com.chatop.api.exception.ResourceNotFoundException;
import com.chatop.api.model.NewMessage;

public interface MessageService {
    public void createMessage(NewMessage message, String userEmail) throws AccessDeniedException, ResourceNotFoundException ;
}
