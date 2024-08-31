package com.chatop.api.service;

import com.chatop.api.exception.ResourceNotFoundException;
import com.chatop.api.model.NewMessage;
import org.springframework.security.access.AccessDeniedException;

/**
 * Message service interface.
 */
public interface MessageService {
  public void createMessage(NewMessage message, String userEmail)
      throws AccessDeniedException, ResourceNotFoundException;
}
