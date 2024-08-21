package com.chatop.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chatop.api.model.Message;
import com.chatop.api.model.ResponseMessageInfo;
import com.chatop.api.service.MessageService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class MessageController {

    private MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/messages")
    public ResponseMessageInfo createMessage(@Valid @RequestBody Message message, Authentication auth) throws Exception{
        messageService.create(message, auth.getName());
        return new ResponseMessageInfo("Message send with success");
    }
    
}
