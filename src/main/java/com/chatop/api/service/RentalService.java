package com.chatop.api.service;

import java.util.List;

import com.chatop.api.model.NewRental;
import com.chatop.api.model.Rental;

public interface RentalService {
    List<Rental> getAllRentals();
    Rental createRental(NewRental newRental, String userEmail, String imgSrc) throws Exception;
    Rental getRentalById(int id)  throws Exception;
    void saveRentalById(int id, NewRental Rental, String userEmail) throws Exception;
}