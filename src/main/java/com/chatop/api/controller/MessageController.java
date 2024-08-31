package com.chatop.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chatop.api.model.NewMessage;
import com.chatop.api.model.ResponseMessage;
import com.chatop.api.model.ResponseMessageFactory;
import com.chatop.api.model.ResponseMessageFactoryImpl;
import com.chatop.api.service.MessageService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class MessageController {

    private MessageService messageService;
    private ResponseMessageFactory responseMessageFactory;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
        responseMessageFactory = ResponseMessageFactoryImpl.create();
    }

    @Operation(summary = "Post a new message")
    @PostMapping("/messages")
    public ResponseMessage createMessage(@Valid @RequestBody NewMessage message, Authentication auth) {
        messageService.createMessage(message, auth.getName());
        return responseMessageFactory.setMessage("Message send with success").build();
    }
    
}
