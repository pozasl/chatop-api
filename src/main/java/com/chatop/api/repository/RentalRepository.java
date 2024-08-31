package com.chatop.api.repository;

import com.chatop.api.entity.RentalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Rental repository.
 */
@Repository
public interface RentalRepository extends JpaRepository<RentalEntity, Integer> {}