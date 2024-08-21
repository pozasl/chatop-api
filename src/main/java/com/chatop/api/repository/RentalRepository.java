package com.chatop.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chatop.api.model.RentalEntity;

public interface RentalRepository extends JpaRepository<RentalEntity, Integer>{
    
}