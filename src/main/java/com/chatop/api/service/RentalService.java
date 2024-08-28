package com.chatop.api.service;

import java.util.List;

import org.springframework.security.access.AccessDeniedException;

import com.chatop.api.exception.ResourceNotFoundException;
import com.chatop.api.model.NewRental;
import com.chatop.api.model.Rental;

public interface RentalService {
    List<Rental> getAllRentals();
    Rental createRental(NewRental newRental, String userEmail, String imgSrc);
    Rental getRentalById(int id)  throws ResourceNotFoundException;
    Rental saveRentalById(int id, NewRental rental, String userEmail) throws AccessDeniedException, ResourceNotFoundException ;
}