package com.chatop.api.service;

import com.chatop.api.model.Message;

public interface MessageService {
    public void create(Message message, String userEmail) throws Exception;
}
