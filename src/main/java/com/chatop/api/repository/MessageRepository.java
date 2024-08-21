package com.chatop.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chatop.api.model.MessageEntity;

public interface MessageRepository extends JpaRepository<MessageEntity, Integer>{
    
}