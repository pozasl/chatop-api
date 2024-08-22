package com.chatop.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chatop.api.model.RentalEntity;

@Repository
public interface RentalRepository extends JpaRepository<RentalEntity, Integer>{
    
}