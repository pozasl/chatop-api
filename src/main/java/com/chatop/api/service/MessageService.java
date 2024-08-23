package com.chatop.api.service;

import com.chatop.api.model.NewMessage;

public interface MessageService {
    public void create(NewMessage message, String userEmail) throws Exception;
}
