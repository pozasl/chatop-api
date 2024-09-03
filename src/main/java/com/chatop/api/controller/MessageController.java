package com.chatop.api.controller;

import com.chatop.api.model.NewMessage;
import com.chatop.api.model.ResponseMessage;
import com.chatop.api.model.ResponseMessageFactory;
import com.chatop.api.model.ResponseMessageFactoryImpl;
import com.chatop.api.service.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Message Controller endpoints.
 */
@RestController
@RequestMapping("/api/messages")
public class MessageController {

  private MessageService messageService;
  private ResponseMessageFactory responseMessageFactory;

  @Autowired
  public MessageController(MessageService messageService) {
    this.messageService = messageService;
    responseMessageFactory = ResponseMessageFactoryImpl.create();
  }

  @Operation(summary = "Post a new message")
  @SecurityRequirement(name = "Authorization")
  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseMessage createMessage(
      @Valid @RequestBody NewMessage message,
      Authentication auth) {
    messageService.createMessage(message, auth.getName());
    return responseMessageFactory.makeResponseMessage("Message send with success");
  }

}
