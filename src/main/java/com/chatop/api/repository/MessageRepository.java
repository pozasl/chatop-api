package com.chatop.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chatop.api.model.MessageEntity;

@Repository
public interface MessageRepository extends JpaRepository<MessageEntity, Integer>{
    
}